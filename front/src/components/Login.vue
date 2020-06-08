<template>
    <v-dialog persistent no-click-animation max-width="30%" v-model="login">
        <v-card style="padding: 25px">
            <v-window v-model="isReg">
                <v-window-item :value="1">
                    <v-container style="text-align: center">Sign Up</v-container>
                        <v-text-field
                                name="login"
                                v-model="user.login"
                                label="Login"
                                required
                        ></v-text-field>
                    <v-text-field
                            name="password"
                            v-model="user.password"
                            label="Password"
                            required
                    ></v-text-field>
                    <v-btn block @click="signUp()">Sign Up</v-btn>
                    <v-btn block text @click="isReg=2">Already have an account?</v-btn>
                </v-window-item>
                <v-window-item :value="2">
                    <v-container style="text-align: center">Sign In</v-container>
                    <v-text-field
                            name="login"
                            v-model="user.login"
                            label="Login"
                            required
                    ></v-text-field>
                    <v-text-field
                            name="password"
                            v-model="user.password"
                            label="Password"
                            required
                    ></v-text-field>
                    <v-btn block @click="signIn()">Sign In</v-btn>
                    <v-btn block text @click="isReg=1">Doesn't have account yet?</v-btn>
                </v-window-item>
            </v-window>
        </v-card>
    </v-dialog>
</template>

<script>
    import {AXIOS} from '../variables.js'

    export default {
        name: "Login",
        data() {
            return {
                login: true,
                user: {
                    login: null,
                    password: null
                },
                isReg: 1,
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        },
        methods: {
            async signIn() {
                let profile = null;
                await AXIOS.post("/account/signin", JSON.stringify(this.user), {headers: this.headers}).then(resp => {
                    profile = resp.data.data;
                });
                if (profile) {
                    this.$store.state.profile = profile;
                    sessionStorage.setItem("profile", JSON.stringify(profile));
                    AXIOS.defaults.headers["Access-Token"] = this.$store.state.profile.token;
                    this.login = false;
                    this.loadData()
                }
            },
            async signUp() {
                let profile = null;
                await AXIOS.post("/account/signup", JSON.stringify(this.user), {headers: this.headers}).then(resp => {
                    profile = resp.data.data;
                });
                if (profile) {
                    this.$store.state.profile = profile;
                    sessionStorage.setItem("profile", JSON.stringify(profile));
                    AXIOS.defaults.headers["Access-Token"] = this.$store.state.profile.token;
                    this.login = false;
                    this.loadData()
                }
            },
            async loadData() {
                let data = null;
                await AXIOS.get("/image?page=0").then(resp => {
                    data = resp.data.data;
                });
                if (data) {
                    this.$store.state.photos = data;
                }
                await AXIOS.get("/categories").then(resp => {
                    data = resp.data.data
                });
                if (data) {
                    this.$store.state.categories = data;
                }
            },
        },
        async mounted() {
            let profile = sessionStorage.getItem("profile");
            if (profile) {
                this.$store.state.profile = JSON.parse(profile);
                AXIOS.defaults.headers["Access-Token"] = this.$store.state.profile.token;
                this.login = false;
                this.loadData();
            }
        }
    }
</script>

<style scoped>

</style>