import Vue from 'vue'
import App from './App.vue'
import store from './store'
import YmapPlugin from 'vue-yandex-maps'
import {settings} from "./variables.js";
import vuetify from './plugins/vuetify';



Vue.use(YmapPlugin, settings)

Vue.config.productionTip = false

new Vue({
    store,
    vuetify,
    render: h => h(App)
}).$mount('#app')
