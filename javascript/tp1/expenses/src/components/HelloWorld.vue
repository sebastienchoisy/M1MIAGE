<template>
  <div>
    <div v-if="expenses.length">
      <ol>
        <ol>
          <li v-for="(expense, index) in expenses" :key="index">
            id: {{expense.id}}, {{ expense.name }} - {{ expense.amount }}
            <button @click="removeExpense(expense)">Remove</button>
          </li>
        </ol>
      </ol>
      <p>Total: {{ total }}</p>
    </div>
    <div v-else>
      <p>Hooray! You didn't spend anything.</p>
    </div>
    <hr />
    <p>New Expense:</p>
    <form v-on:submit.prevent>
      <div>
        <input type="text" v-model="name" placeholder="What did you buy?" />
      </div>
      <div>
        <input type="text" v-model="amount" placeholder="How much is it?" />
      </div>
      <div>
        <button @click="addExpense()">Submit</button>
      </div>
    </form>
  </div>
</template>

<script>
import _ from "lodash";

import { collection, addDoc, doc, deleteDoc, onSnapshot } from "firebase/firestore";

export default {
  name: "HelloWorld",
  data: () => ({
    name: "",
    amount: "",
    total: 0,
    expenses: [],
  }),
  created() {
    onSnapshot(collection(this.$db, "expenses"), (snapshot) => {
      snapshot.docChanges().forEach((change) => {
        console.dir(change.type);
        console.dir(change.doc.id);
        console.dir(change.doc.data());

        let r = change.doc.data();
        let id = change.doc.id;
        let pos;

        switch (change.type) {
          case "added":
            r.id = id;
            this.expenses.push(r);
            break;
          case "modified":
            pos = this.expenses.findIndex((element) => element.id === id);
            console.log("POS = " + pos + " id=" + id);
            //console.dir(this.expenses);
            r.id = id;
            this.$set(this.expenses, pos, r);
            // this.expenses[pos] = r; // marche pas, la vue ne se met pas à jour, il faut $set !!
            break;
          case "removed":
            console.log("removed")
            pos = this.expenses.findIndex((element) => element.id === id);
            this.expenses.splice(pos, 1);
            break;
        }
      });
    });
  },
  methods: {
    async addExpense() {
      try {
        const docRef = await addDoc(collection(this.$db, "expenses"), {
          name: this.name,
          amount: this.amount,
          dateCreated: new Date(),
        });
        this.amount = "";
        this.name = "";

        console.log("Document written with ID: ", docRef.id);
      } catch (e) {
        console.error("Error adding document: ", e);
      }
    },
    async removeExpense(expense) {
      await deleteDoc(doc(this.$db, "expenses", expense.id));
      console.log('je supprime doc id = ' + expense.id)
    },
  },
  watch: {
    expenses() {
      this.total = _.sumBy(this.expenses, function (expense) {
        return parseFloat(expense.amount);
      });
    },
  },
};
</script>

<style scoped>
div {
  margin-bottom: 20px;
}
</style>
