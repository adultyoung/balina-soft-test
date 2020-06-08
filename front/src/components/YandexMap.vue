<template>
    <yandex-map @click="onClick" :settings="settings" :coords="[53.90150541364391,27.5595052484206]">
        <ymap-marker v-for="photo in photos" :key="photo.id"
                     :marker-id="photo.id"
                     :coords="[photo.lat,photo.lng]"
                     :balloon-template="balloonTemplate(photo)"
        />
<!--        <ymap-marker-->
<!--            marker-id="123"-->
<!--            :coords="coords"-->
<!--            :balloon-template="balloonTemplate"/>-->
    </yandex-map>
</template>

<script>
    import {settings} from "../variables.js";
    import { yandexMap, ymapMarker } from 'vue-yandex-maps'
    import {mapGetters} from "vuex"

    export default {
        name: 'YandexMap',
        components: { yandexMap, ymapMarker },
        data() {
            return {
                settings,
                coords: [53.90150541364391,27.5595052484206],
            }
        },
        computed: {
            ...mapGetters(["photos"]),
        },
        methods: {
            onClick(e) {
                this.coords = e.get('coords');
            },
            balloonTemplate(photo) {
                return `
        <img style="width: 100%;" src="${photo.url}">
      `
            }
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .ymap-container {
        height: 100%;
    }
</style>
