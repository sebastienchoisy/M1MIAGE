window.onload=init;

function init() {
    new Vue({
        el: "#app",
        data: {
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
            id:'',
            pageSize: 5,
            pageNumber: 0,
            restaurantsNumber: 0,
            restaurantNameQuery: ''
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
    });
}