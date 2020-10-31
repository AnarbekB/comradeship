import Vue from 'vue';
import 'api/resource';
import App from 'pages/App.vue';
import store from 'store/store';
import { connect } from "util/websocket";
import Vuetify from 'vuetify';
import '@babel/polyfill';
import 'vuetify/dist/vuetify.min.css';

if (frontendData.profile) {
    connect();
}

Vue.use(Vuetify, { iconfont: 'mdiSvg' })

new Vue({
    el: '#app',
    store,
    vuetify: new Vuetify(),
    render: a => a(App)
});