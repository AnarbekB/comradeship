<template>
    <v-card class="my-2">
        <v-card-text primary-title >
            <i>{{ message.id }}</i> {{ message.text }}
        </v-card-text>
        <media v-if="message.link" :message="message"></media>
        <v-card-actions>
            <v-btn @click="edit" small text rounded>Edit</v-btn>
            <v-btn icon @click="del" small>
                <v-icon>{{delete_icon}}</v-icon>
            </v-btn>
            <v-btn icon small>
                <v-icon>{{like}}</v-icon>
            </v-btn>
        </v-card-actions>
        <comment-list :comments="message.comments" :message-id="message.id"></comment-list>
    </v-card>
</template>

<script>
    import { mdiDelete } from '@mdi/js';
    import { mdiThumbUpOutline } from '@mdi/js';
    // import { mdiThumbUp } from '@mdi/js';
    import {mapActions} from 'vuex';
    import Media from "components/media/Media.vue";
    import CommentList from "components/comment/CommentList.vue";

    export default {
        data() {
            return {
                delete_icon: mdiDelete,
                like: mdiThumbUpOutline
            }
        },
        props: ['message', 'editMessage'],
        components: { CommentList, Media },
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message);
            },
            del() {
                this.removeMessageAction(this.message);
            }
        }
    }
</script>

<style scoped>

</style>