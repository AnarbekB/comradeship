import Vue from 'vue';
import VueRouter from 'vue-router';
import MessagesList from 'pages/MessagesList.vue'
import Auth from 'pages/Auth.vue';
import Profile from "pages/Profile.vue";

Vue.use(VueRouter);

const routes = [
    { path: '/', component: MessagesList },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '*', component: Auth },
];

export default new VueRouter({
    mode: 'history',
    routes
});
