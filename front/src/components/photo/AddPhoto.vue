<template>
    <v-card style="padding: 20px;">
        <v-container style="text-align: center;">
            Add new Photo
        </v-container>
        <v-text-field
                name="lat"
                v-model="img.lat"
                label="Lat"
                required
        ></v-text-field>
        <v-text-field
                name="lng"
                v-model="img.lng"
                label="Lng"
                required
        ></v-text-field>
        <v-select v-model="img.categoryId" :items="this.$store.state.categories" item-text="category" item-value="id"
                  label="Choose categories" single-line></v-select>
        <v-file-input
                @change="toBase64($event)"
                label="Choose image"
                filled
                prepend-icon="mdi-camera"
        ></v-file-input>
        <v-btn text block @click="savePhoto">
            Add Photo
        </v-btn>
    </v-card>
</template>

<script>
    import {AXIOS} from "../../variables";

    export default {
        name: "AddPhoto",
        data() {
            return {
                img: {
                    lat: null,
                    lng: null,
                    base64Image: null,
                    date: null,
                    categoryId: null
                }
            }
        },
        methods: {
            async savePhoto() {
                this.img.date = parseInt((Date.now()/1000).toString(), 10);
                let headers = {
                    'Content-Type': 'application/json;charset=UTF-8'
                };
                await AXIOS.post("/image", JSON.stringify(this.img), {headers: headers}).then((response) => {
                    this.$store.state.photos.push(response.data.data)
                })
            },
            toBase64(event) {
                const vm = this;
                let reader = new FileReader();
                reader.onloadend = function() {
                    vm.img.base64Image = reader.result.split(",")[1];
                };
                reader.readAsDataURL(event);
            }
        }
    }
</script>

<style scoped>

</style>