<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Comradeship</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">
                {{profile.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon size="30">{{logout}}</v-icon>
            </v-btn>
        </v-app-bar>
        <v-main>
            <v-container v-if="!profile">Необходимо авторизоваться через
                <a href="/login">Google</a>
            </v-container>
            <v-container v-if="profile">
                <messages-list/>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
    import MessagesList from 'components/messages/MessagesList.vue'
    import { addHandler } from "util/websocket";
    import { mapState, mapMutations } from 'vuex';
    import {mdiExitToApp} from "@mdi/js";

    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                logout: mdiExitToApp
            }
        },
        computed: mapState(['profile']),
        methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
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
        }
    }
</script>

<style>
</style>