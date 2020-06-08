<template>
    <v-layout row wrap>
        <v-flex v-for="category in categories" :key="category.id">
            <v-btn style="position: relative" @click="addFilter(category)" :color="filter.includes(category) ? 'primary' : 'secondary'">
                {{category.category}}
                <div @click="deleteCategory(category)"
                     style="color: white; cursor:pointer;position: absolute;bottom: 0px;right: -20px;font-size: 20px;transform: rotate(-45deg);padding: 5px;">
                    <b>+</b></div>
            </v-btn>
            <div @click="deleteCategory(category)"
                 style="color: white; cursor:pointer;position: absolute;top: 0px;right: 0px;font-size: 20px;transform: rotate(-45deg);padding: 5px;">
                <b>+</b></div>
        </v-flex>
    </v-layout>
</template>

<script>
    import {AXIOS} from '../variables.js'
    import {mapGetters} from "vuex"

    export default {
        name: "Category",
        data() {
            return {
                filter: [],
            }
        },
        methods: {
            async addFilter(category) {
                if (!this.filter.includes(category)) {
                    await this.filter.push(category);
                } else {
                    this.filter = await this.filter.filter(item => item !== category)
                }
            },
            deleteCategory(category) {
                AXIOS.delete("/categories/" + category.id).then((response) => {
                    if (response.status === 200) {
                        this.categories = this.categories.filter(item => item !== category);
                    }
                })
            }
        },
        watch: {
            filter: function (newValue) {
                let headers = {
                    'Content-Type': 'application/json'
                };
                if (newValue.length === 0) {
                    AXIOS.get("/image?page=0").then((resp) => {
                        this.$store.state.photos = resp.data.data;
                    });
                } else {
                    AXIOS.post("/image/filter", JSON.stringify(newValue), {headers: headers}).then((response) => {
                        this.$store.state.photos = response.data.data;
                    })
                }
            }
        },
        computed: {
            ...mapGetters(["categories"])
        }
    }
</script>

<style scoped>

</style>