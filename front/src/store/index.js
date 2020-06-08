import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
      profile: null,
    photos: [],
    categories: [],
  },
    getters: {
      profile: state => state.profile,
      photos: state => state.photos,
      categories: state => state.categories,
    },
  mutations: {
    logout(state) {
      state.profile = null;
      state.photos = null;
      state.categories = null;
    }
  },
  actions: {
  },
  modules: {
  }
})
