
<template>
<div class="p-3 mb-2 bg-light text-dark">
<div>
  <v-card color="basil">

    <div class=" big ">
   &emsp;&emsp; <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    </div>

       <v-navigation-drawer
      v-model="drawer"
      absolute
      bottom
      temporary
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item>

          <router-link style="text-decoration: none;" :to="{ name: 'IntentionPlan' }">
      <h5>기획의도</h5>
    </router-link>

          </v-list-item>

          <v-list-item>

         <router-link style="text-decoration: none;" :to="{ name: 'PlanningStage' }">
      <h5>기획단계</h5>
    </router-link>

          </v-list-item>

          <v-list-item>

                <router-link style="text-decoration: none;" :to="{ name: 'LoginTestPage' }">
      <h5>로그인</h5>
    </router-link>

          </v-list-item>

          <v-list-item>

            <router-link style="text-decoration: none;" :to="{ name: 'VuetifyMemberJoinColumnTestPage' }">
      <h5>회원가입</h5>
    </router-link>
    
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>



    <v-card-title class="text-center justify-center py-6">
      <h1 class="font-weight-bold text-h2 basil--text"  >
      Note
      </h1>
    </v-card-title>
  


     
    
  
    <v-tabs
      v-model="tab"
      background-color="transparent"
      color="basil"
      grow
    >

    
    
   
    </v-tabs>
  </v-card>


 <v-carousel>
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
      reverse-transition="fade-transition"
      transition="fade-transition"
    ></v-carousel-item>
  </v-carousel>

 <v-row>



    <v-col
      cols="6"
      sm="4"
    >
      <v-img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxODEwMTFfNDAg%2FMDAxNTM5MjQzNjE0Mjc2.E9f6MHo6N_DyAZq8HCyAVdtUqPk_c3zI_yy3whF4eWEg.zr9BDHXx5mnBfAGdz_KKxXjebDZnqWx_fMbI9b5ueo4g.JPEG.gnsdl_-09%2F20181011163635.jpg&type=sc960_832">
        <div class="fill-height bottom-gradient"></div>
      </v-img>
    </v-col>

    <v-col
      cols="6"
      sm="4"
    >
      <v-img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxODEwMTBfMjI5%2FMDAxNTM5MTczNTUxOTg3.MGtfKnJC0IjFFLN68qUgVbzV3o-1iNTVz2g2nfFT4wEg.wTgpBemaSs7AiIv_iRAMwnktvZZxjHOG9aO_IUDzHhMg.JPEG.gnsdl_-09%2F20181010211025.jpg&type=sc960_832">
        <div class="fill-height bottom-gradient"></div>
      </v-img>
    </v-col>

    <v-col
      cols="6"
      sm="4"
    >
     
      <v-img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxODEwMDhfODYg%2FMDAxNTM4OTk4NDgxODgz.RpGphxHu-T06qio_-bPBpi9huczJPBVUs_B8XzMwd4Ag.5yNi3qRPnlsMP2W_k68jhXReZROIRqP4VfIstNzmbgQg.JPEG.gnsdl_-09%2F20181008203144.jpg&type=sc960_832">
        <div class="fill-height bottom-gradient"></div>
      </v-img>
    </v-col>
  </v-row>


   <v-footer
    color="primary lighten-1"
    padless
  >
    <v-row
      justify="center"
      no-gutters
    >
      <v-btn
        v-for="link in links"
        :key="link"
        color="white"
        text
        rounded
        class="my-2"
      >
        {{ link }}
      </v-btn>
      <v-col
        class="basil py-4 text-center green--text"
        cols="12"
      >
        {{ new Date().getFullYear() }} — <strong>project</strong>
      </v-col>
    </v-row>
  </v-footer>
</div>
</div>
</template>



<script>
  export default {
    data: () => ({
      drawer: false,
      group: null,
       items: [
          {
            src: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F584%2F2021%2F08%2F28%2F0000015734_001_20210828101023352.jpg&type=sc960_832',
          },
          {
            src: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2FMjAxNzA5MTdfODUg%2FMDAxNTA1NjAzMTQzMTYw.AYaWuWvqDossv-68BKOtChp-_A9AaEKsYUiOXXWpzQcg.GnW8lbFJWhu-w7TgNBSskLpjcj29I1F7IneE6jHG6EYg.JPEG.shlope%2Fp1020650.jpg&type=sc960_832',
          },
          {
            src: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDAxMDVfMTgx%2FMDAxNTc4MTg0NTc0OTYy.VQk-pMUR_AqOyVRiGvqaeBHpuOH94GuFd0jgz5aO1PYg.KzhkGMas0KN1SzZ608tTYRXpwvvVpPr51SnuU77ekHQg.JPEG.musicalian%2Fbook-730479_1920.jpg&type=sc960_832',
          },
          {
            src: 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20120120_262%2Fsusemi_9_1327031445583vMlxz_JPEG%2F%25C7%25CF%25B4%25C311.jpg&type=sc960_832',
          },
        ],
    }),
       
      
    watch: {
      group () {
        this.drawer = false
      },
    },
  }
    
  
</script>

<style>
/* Helper classes */

h5{color: green;}

.basil {
  background-color: #FFFBE6 !important;
}
.basil--text {
  color: #356859 !important;
}

.big {
 width: 300;
 height: 500;
}


</style>
