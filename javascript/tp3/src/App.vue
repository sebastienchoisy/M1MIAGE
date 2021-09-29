<template>
  <div id="app">
    <restaurantsList></restaurantsList>
  </div>
</template>

<script>
import restaurantsList from './components/restaurantsList.vue'

export default {
  name: 'App',
  components: {
    restaurantsList,
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
