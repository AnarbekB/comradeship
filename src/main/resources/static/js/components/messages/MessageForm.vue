<template>
    <v-layout row>
        <v-text-field
                label="New message"
                placeholder="Write something"
                v-model="text"
                @keyup.enter="save"/>
        <v-btn @click="save">Save</v-btn>
    </v-layout>
</template>

<script>
    import { mapActions } from 'vuex';

    export default {
        props: ['messageAttr'],
        data() {
            return {
                text: '',
                id: ''
            }
        },
        watch: {
            messageAttr: function(newValue, oldValue) {
                this.text = newValue.text;
                this.id = newValue.id;
            }
        },
        methods: {
            ...mapActions(['updateMessageAction', 'addMessageAction']),
            save() {
                const message = {
                    id: this.id,
                    text: this.text
                };

                if (this.id) {
                    this.updateMessageAction(message);
                } else {
                    this.addMessageAction(message);
                }

                this.text = '';
                this.id = '';
            }
        }
    }
</script>

<style>

</style>