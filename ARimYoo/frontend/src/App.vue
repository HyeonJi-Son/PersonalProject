<template>
  <v-app>
    <div v-if="$route.name !=='EnterVellup' && $route.name !== 'JoinPage' && $route.name !== 'JoinPage2'">
        <v-container v-if="$store.state.token">
            <v-layout row>
                <div class="btnSet2">
                    <div class="float">
                        <router-link to="/myPage">
                        <img v-if="user.profile" :src="require(`@/assets/back/member/${user.profile}`)" width="30">
                        <img v-else src="@/assets/profile.png" width="30"/></router-link>
                    </div>
                    <div class="float">
                        <v-menu offset-y>
                                <template v-slot:activator="{ on}">
                                    <button depressed color="white" height="30" 
                                    v-on="on" class="myBtn" width="140">{{ user.message }} 님</button>
                                </template>
                            <v-list class="black">
                                <v-list-item
                                    dark
                                    v-for="(item,idx) in items"
                                    :key="idx"
                                    :to="item.route"
                                    >
                                    {{ item.title }}
                                </v-list-item>
                            </v-list>
                            </v-menu>
                    </div>
                    <div  class="float">
                        <logout-button/>
                    </div>
                </div>
            </v-layout>
        </v-container>
        <v-container v-else>
            <v-row>
                <div class="btnSet"> 
                        <div class="float">
                            <login-button />
                        </div>
                        <div  class="float">
                            <join-button/>
                        </div>
                </div>
            </v-row>
        </v-container>

      <div class="header">
          <button><img src ="@/assets/main/banner.png" width="220" @click="goToMain"></button>
      </div>
      <div class="menuBar">  
          <main-toolbar/>
      </div>
    </div>

    <v-main>
      <v-container fluid justify-center class="py-0 px-0">
        <router-view></router-view>
      </v-container>
    </v-main>

    <div v-if="$route.name !=='EnterVellup' && $route.name !== 'JoinPage' && $route.name !== 'JoinPage2'">
        <footer-vellup/>
    </div>
  </v-app>
</template>

<script>
import { mapState } from 'vuex';
import JoinButton from './components/button/JoinButton.vue';
import LoginButton from './components/button/LoginButton.vue';
import LogoutButton from './components/button/LogoutButton.vue';
import FooterVellup from './components/FooterVellup.vue';
import MainToolbar from './components/MainToolbar.vue';

export default {
  components: { MainToolbar, LoginButton, JoinButton, LogoutButton, FooterVellup },
  name: 'App',

  data () {
    return {
     user: {
         message: decodeURIComponent(this.$store.state.userInfo.name),
         profile: this.$store.state.userInfo.profilePic,
     },
     items: [
         {title: 'My Page', route:'/myPage'},
         {title: 'My With', route:'/myWith'}
     ]
    }
  },
  computed: {
      ...mapState(['userInfo'])
  },
  methods : {
      goToMain () {
          this.$router.push("/main")
          history.go(0)
      }

  }
}
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');

.header {
    position: relative;
    left: 17%;
    padding-top: 5px;
    padding-bottom: 5px;
}
.btnSet{
  position: absolute;
  left: 75%;
  padding-top:1%;
}
.btnSet2{
  position: absolute;
  left: 72%;
  padding-top:1%;
}
.v-footer{
    font-family: 'Noto Sans KR', sans-serif;
}
.myBtn{
   font-family: 'Noto Sans KR', sans-serif;
    font-size: 16px;
    padding-top:2%;
}
.float {
  float: left;
  margin-right:7px;
}
</style>

<style>
a{
    text-decoration: none;
    color:white;
}
</style>
