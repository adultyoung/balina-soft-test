<template>
    <v-container style="position:relative;">
        <v-img :src="img.url" contain></v-img>
        <div @click="deleteImg(img)"
             style="cursor:pointer;position: absolute;top: 0px;right: 0px;font-size: 20px;transform: rotate(-45deg);padding: 5px;">
            <b>+</b></div>
    </v-container>
</template>

<script>
    import {AXIOS} from '../../variables.js'
    export default {
        props: ["img"],
        name: "PhotoComponent",
        methods: {
            deleteImg(img) {
                AXIOS.delete("/image/" + img.id).then((response) => {
                    if (response.status === 200)
                    this.$store.state.photos.filter(item => item !== img)
                })
            }
        },
    }
</script>

<style scoped>

</style>