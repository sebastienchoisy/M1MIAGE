<template>
  <div>
  <form @submit.prevent="ajouterRestaurant($event)">
    <md-field>
      <label> Nom </label>
      <md-input type="text" required v-model="nom" name="nom"></md-input>
    </md-field>
    <md-field>
      <label> Cuisine </label>
      <md-input type="text" required v-model="cuisine" name="cuisine"></md-input>
    </md-field>
    <md-button type="submit" class="md-raised">Ajouter</md-button>
  </form>

  <h1>Nombre de restaurants : {{restaurantsNumber}}<br><br>
    Nombre de restaurants dans la réponse : {{pageSize}}</h1>
  <md-field>
    <label>Rechercher</label>
    <md-input type="text" v-model="restaurantNameQuery" class="search"></md-input>
  </md-field>
  <md-button class="md-raised" type="submit" v-on:click="getRestaurantsFromServer">Rechercher</md-button>
  <md-button class="md-raised" v-on:click="resetQuery">Reset</md-button>
  <table>
    <tr>
      <th>Nom</th>
      <th>Cuisine </th>
    </tr>
    <tbody>
    <tr v-for="(r,index) in restaurants" :key="index" v-bind:style="{backgroundColor:getColor(index)}"
                  v-bind:class="{bordureRouge:(index === 2)}">
      <td>{{r.nom}}</td>
      <td> {{r.cuisine}}</td>
      <td><button v-on:click="supprimerRestaurant(r.id)">Delete</button></td>
    </tr>
    </tbody>
  </table>
  <div class="queryselector">
    <input type="range" min="5" max="100" v-model="pageSize" step="5" id="slider">
    <md-button class="md-raised" :disabled="pageNumber===0" v-on:click="goPrevious">Précèdent</md-button>
    <md-button class="md-raised" v-on:click="goNext">Suivant</md-button>
  </div>
  </div>
</template>

<script>

export default {
  name: "restaurantsList",
  props: {
    msg: String,
  },
  data:function(){
    return {
      restaurants: [
        {
          nom: 'café de Paris',
          cuisine: 'Française',
        },
        {
          nom: 'Sun City Café',
          cuisine: 'Américaine'
        }
      ],
      nom: '',
      cuisine: '',
      id: '',
      pageSize: 5,
      pageNumber: 0,
      restaurantsNumber: 0,
      restaurantNameQuery: ''
    }
  },
  methods: {
    supprimerRestaurant(index) {
      let url = 'http://localhost:8080/api/restaurants/'+index;
      fetch(url, {
        method: 'DELETE',
      }).then((response) => response.json())
          .then((res)=>console.log(res))
          .catch(function (err) {
            console.log(err);
          });
      this.getRestaurantsFromServer();
    },
    ajouterRestaurant(event) {
      console.log(event)
      event.preventDefault();
      let formulaire = event.target;
      let formData = new FormData(formulaire);
      let url = "http://localhost:8080/api/restaurants";
      fetch(url, {
        method: "POST",
        body: formData
      })
          .then((response) => response.json())
          .then((res) => console.log(res))
          .catch(function (err) {
            console.log(err);
          });
      this.getRestaurantsFromServer();
    },
    getColor(index) {
      return (index % 2) ? 'lightBlue' : 'pink';
    },
    resetQuery(){
      this.restaurantNameQuery = '';
      this.getRestaurantsFromServer();
    },
    getRestaurantsFromServer(){
      this.restaurants = [];
      let url = 'http://localhost:8080/api/restaurants?'+'page='+this.pageNumber+'&pagesize='+this.pageSize+'&name='+this.restaurantNameQuery
      fetch(url)
          .then((response) => response.json())
          .then((res) => {
                for(let i=0;i<res.data.length;i++){
                  this.restaurants.push(
                      {
                        nom: res.data[i].name,
                        cuisine: res.data[i].cuisine,
                        id: res.data[i]._id
                      }
                  )
                }
              }
          )
          .catch(function (err) {
            console.log(err);
          });
    },
    goNext(){
      this.pageNumber++;
      this.getRestaurantsFromServer();
    },
    goPrevious(){
      if(this.pageNumber>0){
        this.pageNumber--;
        this.getRestaurantsFromServer();
      }
    },
    getRestaurantsCount(){
      let url = 'http://localhost:8080/api/restaurants/count';
      fetch(url)
          .then((response) => response.json())
          .then((res) => {
                this.restaurantsNumber = res.data;
              }
          )
          .catch(function (err) {
            console.log(err);
          });
    },
  },
  watch: {
    pageSize: function (val) {
      if (val) {
        this.restaurants = [];
        this.getRestaurantsFromServer();
      }
    }
  },
  mounted(){
    this.getRestaurantsFromServer();
    this.getRestaurantsCount();
  }
}
</script>

<style scoped>
table {
  border: 1px solid black;
  width: 100%;
  border-collapse: collapse;
}

tr,
td {
  border: 1px solid black;
}

td {
  padding: 5px;
}

tr:hover {
  background-color: yellow;
}

input:invalid {
  background-color: pink;
}

input:valid {
  background-color: lightGreen;
}

.bordureRouge {
  border: 2px dashed red;
}
.search {
  margin-bottom: 2%;
}

form {
  margin: 6px 8px;
}

.md-field {
  width:30%;
  margin-left:1%;
}

.queryselector{
  margin-top:5%;
}
</style>