import axios from 'axios'
axios.defaults.withCredentials = true;
export const AXIOS = axios.create({
    baseURL: `http://localhost:9099/api`
})

export const settings = {
    apiKey: '111c1599-e243-4b32-834d-8596b6dac7c3',
    lang: 'ru_RU',
    coordorder: 'latlong',
    version: '2.1'
}