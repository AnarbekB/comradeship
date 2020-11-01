<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Comradeship</v-toolbar-title>
            <v-btn text  v-if="profile" :disabled="$route.path === '/'" @click="showMessages">
                Messages
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn text v-if="profile" :disabled="$route.path === '/profile'" @click="showProfile">
                {{profile.name}}
            </v-btn>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon size="30">{{logout}}</v-icon>
            </v-btn>
        </v-app-bar>
        <v-main>
            <router-view></router-view>
        </v-main>
    </v-app>
</template>

<script>
    import { addHandler } from "util/websocket";
    import { mapState, mapMutations } from 'vuex';
    import {mdiExitToApp} from "@mdi/js";

    export default {
        data() {
            return {
                logout: mdiExitToApp
            }
        },
        computed: mapState(['profile']),
        methods: {
            ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
            showMessages() {
                this.$router.push('/');
            },
            showProfile() {
                this.$router.push('/profile');
            },
        },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body);
                            break;
                        case 'UPDATE':
                            this.updateMessageMutation(data.body);
                            break;
                        case 'DELETE':
                            this.removeMessageMutation(data.body);
                            break;
                        default:
                            console.error(`Event type unknown: "${data.eventType}"`);
                    }
                } else {
                    console.error(`Object type unknown: "${data.objectType}"`);
                }
            });
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth');
            }
        }
    }
</script>

<style>
</style>