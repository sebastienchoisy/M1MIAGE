import Vue from 'vue'
import App from './App.vue'

// Initialize Cloud Firestore through Firebase
import { initializeApp } from "firebase/app"
import { getFirestore } from "firebase/firestore"

initializeApp({
  apiKey: "AIzaSyAdy0BiRyk7NO1cJOcUtzN7_b1gMp4H29Q",
  authDomain: "miagem1tp1.firebaseapp.com",
  projectId: "miagem1tp1",
  storageBucket: "miagem1tp1.appspot.com",
  messagingSenderId: "133342316870",
  appId: "1:133342316870:web:41daf25a36ee57c945bd47"
});

// On stocke la bd dans une "propriété globale de VueJS"
// sera accessible par this.$db dans n'importe quel fichier
Vue.prototype.$db = getFirestore();

// configuration globale
Vue.config.productionTip = false

// démarre l'affichage
new Vue({
  render: h => h(App),
}).$mount('#app')