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
                <messages-list :messages="messages"/>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
    import MessagesList from 'components/messages/MessagesList.vue'
    import {addHandler} from "util/websocket";
    import {mdiExitToApp} from '@mdi/js';

    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile,
                logout: mdiExitToApp
            }
        },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    const index = this.messages.findIndex(item => item.id === data.body.id);
                    switch (data.eventType) {
                        case 'CREATE':
                        case 'UPDATE':
                            if (index > -1) {
                                this.messages.splice(index, 1, data.body);
                            } else {
                                this.messages.push(data.body);
                            }
                            break;
                        case 'DELETE':
                            this.messages.splice(index, 1);
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