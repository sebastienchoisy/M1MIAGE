import * as firebase from 'firebase';

const config = {
    apiKey: "AIzaSyC2ANh61p61arWP_wQP9Lupgq_hA34iWag",
    authDomain: "localhost",
    databaseURL: "https://vue-chat-app-16443-default-rtdb.europe-west1.firebasedatabase.app/",
    projectId: "vue-chat-app-16443",
    storageBucket: "gs://vue-chat-app-16443.appspot.com"
};

firebase.initializeApp(config);

export default firebase;