<template>
    <v-card class="my-2">
        <v-card-text primary-title >
            <i>{{ message.id }}</i> {{ message.text }}
        </v-card-text>
        <v-card-actions>
            <v-btn @click="edit" small text rounded>Edit</v-btn>
            <v-btn icon @click="del" small>
                <v-icon>{{delete_icon}}</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import { mdiDelete } from '@mdi/js';

    export default {
        data() {
            return {
                delete_icon: mdiDelete
            }
        },
        props: ['message', 'editMessage', 'deleteMessage', 'messages'],
        methods: {
            edit() {
                this.editMessage(this.message);
            },
            del() {
                this.deleteMessage(this.message);
            }
        }
    }
</script>

<style>

</style>