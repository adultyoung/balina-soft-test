<template>
    <v-card style="padding: 20px">
        <v-container style="text-align: center">My photo</v-container>
        <v-container>
            <v-layout>
                <v-btn color="primary" @click="addPhoto = true">
                    Add img +
                </v-btn>
                <v-btn v-if="!addCat" @click="addCat = true">Add category</v-btn>
                <v-text-field v-if="addCat" name="category" v-model="category" label="category"></v-text-field>
                <v-btn v-if="addCat" @click="addCategory()" text>Add</v-btn>
                <v-dialog max-width="30%" v-model="addPhoto">
                    <add-photo></add-photo>
                </v-dialog>
            </v-layout>
            <v-container style="text-align: center;">Categories</v-container>
                    <category></category>
            <v-layout row wrap>
                <v-flex xs3 v-for="photo in photos" :key="photo.id">
                    <photo-component :img="photo"></photo-component>
                </v-flex>
            </v-layout>
            <v-btn v-if="hasPrev" align="left" @click="prev()">
                <- Back
            </v-btn>
            <v-btn v-if="hasNext" align="right" @click="next()">
                Next ->
            </v-btn>
        </v-container>
    </v-card>
</template>

<script>
    import PhotoComponent from "./photo/PhotoComponent.vue"
    import {AXIOS} from "../variables";
    import AddPhoto from "./photo/AddPhoto.vue";
    import {mapGetters} from 'vuex'
    import Category from "./Category";

    export default {
        name: "PhotoDialog",
        components: {
            Category,
            AddPhoto,
            PhotoComponent
        },
        data() {
            return {
                page: 1,
                addPhoto: false,
                hasPrev: false,
                hasNext: true,
                addCat: false,
                category: null
            }
        },
        methods: {
            prev() {
                if (this.hasPrev && this.page > 1) {
                    let data = null;
                    AXIOS.get("/image?page=" + (this.page - 2)).then(resp => {
                        data = resp.data;
                    });
                    if (data) {
                        this.$store.state.photos = JSON.parse(data);
                        this.page--;
                    }
                } else {
                    this.hasPrev = false;
                }
            },
            next() {
                if (this.hasNext) {
                    let data = null;
                    AXIOS.get("/image?page=" + (this.page)).then(resp => {
                        data = resp.data;
                    });
                    if (data) {
                        this.$store.state.photos = JSON.parse(data);
                        this.page++;
                    } else {
                        this.hasNext = false;
                    }
                }
            },
            addCategory() {
                let data = null;
                let headers = {
                    'Content-Type': 'application/json'
                };
                AXIOS.post("/categories", JSON.stringify(this.category), {headers: headers}).then(resp => {
                    data = resp.data.data;
                });
                if (data) {
                    this.$store.state.categories.push(data);
                }
                this.addCat = false;
            }
        },
        computed: {
            ...mapGetters(["photos", "categories"])
        }
    }
</script>

<style scoped>

</style>