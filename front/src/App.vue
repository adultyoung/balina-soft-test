<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-app>
        <v-toolbar v-if="profile">
            <v-spacer></v-spacer>
            <v-menu top closeOnClick>
                <template v-slot:activator="{ on }">
                    <v-btn
                            color="primary"
                            dark
                            v-on="on"
                    >
                        {{profile.login}}
                    </v-btn>
                </template>

                <v-list>
                    <v-list-item @click="logout()"
                    >
                        <v-list-item-title>Log out</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>
        </v-toolbar>
        <v-layout style="padding: 20px;" row v-if="profile">
            <v-flex xs5>
                <photo-dialog></photo-dialog>
            </v-flex>
            <v-flex xs7>
                <yandex-map></yandex-map>
            </v-flex>
        </v-layout>
        <login></login>
    </v-app>
</template>

<script>
    import {mapGetters} from 'vuex'
    import YandexMap from './components/YandexMap.vue'
    import Login from './components/Login.vue'
    import PhotoDialog from "./components/PhotoDialog.vue"

    export default {
        name: 'App',

        components: {
            YandexMap,
            Login,
            PhotoDialog
        },

        data() {
            return {
                showPhoto: false,
            }
        },
        methods: {
            logout() {
                sessionStorage.clear();
                this.$store.commit("logout");
                window.history.go()
            }
        },

        computed: {
            ...mapGetters(["profile"])
        }
    };
</script>
