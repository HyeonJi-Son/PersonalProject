<template>
      <v-card color="basil">
          <v-card-title>
            <h1 plain class="font-weight-bold text-h2 basil--text" @click="btnMainPage">
              SELECTSHOP
            </h1>
          
            <v-spacer></v-spacer>

          <!-- 검색 네모창을 만들어야함 --> 
          <!-- <v-btn icon type="submit" color="basil">
             <v-icon>mdi-magnify</v-icon>
            <span></span>
            <v-input type="text" size="sm" class="mr-sm-2" placeholder="Search">search</v-input> 
            <v-text-field hidden-details flat label="Search" prepend-inner-icon="mdi-magnify" solo-inverted></v-text-field>
          </v-btn> 
          
          <v-container>
    <v-text-field v-model="search" single-line></v-text-field>
    <v-data-table
            style="width: 50%"
            :headers="headers"
            :items="contents"
            :items-per-page="5"
            :search="search"
    ></v-data-table>
  </v-container>
          -->


            <!-- 로그인, 장바구니, 마이페이지, 게시판--> 

            <div id="cart" style="position:relative">
            <v-btn v-if="isLogin === true" router :to="{path: '/cart'}" icon>
              <span id="nav-cart-count">{{ cartCount }}</span>
              <v-icon>mdi-cart</v-icon>
            </v-btn>
            </div>

            <v-btn v-if="isLogin === false" router :to="{path: '/loginPage'}" plain background-color="transparent" color="basil"> 
              LOGIN 
            </v-btn>
            <v-btn v-else @click="logout" router :to="{path: '/mainPage'}" plain background-color="transparent" color="basil"> 
              LOGOUT 
            </v-btn>

            <v-btn router :to="{path: '/myPage'}" plain background-color="transparent" color="basil">
              MY PAGE
            </v-btn>   

            <v-btn plain background-color="transparent" color="basil" router :to="{path: '/noticeListPage'}">
              NOTICE
            </v-btn>

            <v-btn plain background-color="transparent" color="basil" router :to="{path: '/qnaListPage'}">
              Q&A
            </v-btn>

            <v-btn v-if="this.userInfo.id === 'admin'" router :to="{ path : '/adminPage'}" plain background-color="transparent" color="basil">
            ADMIN
            </v-btn>
              
          </v-card-title>

        <!-- 카테고리 메뉴 탭 (코드 너무 지저분하다 리팩토링 필요한데 v-btn에 v-for적용하면 women이랑 men이랑 같이 나옴) --> 
          <v-tabs v-model="tab" background-color="transparent" color="basil" grow>
            <v-tab>
              <v-menu open-on-hover bottom offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn plain background-color="transparent" color="basil" v-bind="attrs" v-on="on">
                    WOMEN
                  </v-btn>
                </template>

                <v-list background-color="transparent" color="basil">
                  <v-list-item v-for="(wcategory, i) in womenCategories" :key="i" :to="wcategory.route">
                    <v-list-item-title>
                      {{ wcategory.item }}
                    </v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </v-tab>

            <v-tab>
              <v-menu open-on-hover bottom offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn plain background-color="transparent" color="basil" v-bind="attrs" v-on="on">
                    MEN
                  </v-btn>
                </template>

                <v-list background-color="transparent" color="basil">
                  <v-list-item v-for="(mcategory, i) in menCategories" :key="i" :to="mcategory.route">
                    <v-list-item-title>
                      {{ mcategory.item }}
                    </v-list-item-title>
                  </v-list-item>
                </v-list> 
            </v-menu>
            </v-tab>


             <v-tab>
              <v-menu open-on-hover bottom offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn plain background-color="transparent" color="basil" v-bind="attrs" v-on="on" router :to="{path: '/lookbookPage'}">
                    LOOKBOOK
                  </v-btn>
                </template>
              </v-menu>
            </v-tab>
          </v-tabs>
        

      </v-card>
</template>

<script>

// import axios from 'axios'
import { mapState, mapActions } from 'vuex'

export default {
  props: {
    cartCount: {

    }
  },
   data () {
        return {
            tab: null,
            menus: [
               'women', 'men'
            ],
            links: [
              { icon: 'mdi-login-variant', text: 'LOGIN', name: 'login', route: '/loginPage' },
              { icon: 'mdi-cart', text: 'CART', name: 'cart', route: '/cartPage' },
              { icon: 'mdi-account-cowboy-hat-outline', text: 'MYPAGE', name: 'myPage', route: '/myPage' },
            ],
            womenCategories: [
              { item: 'Outer', route: '/womenOuterCategoryPage'},
              { item: 'Top', route: '/womenOuterCategoryPage'},
              { item: 'Bottom', route: '/womenOuterCategoryPage'},
              { item: 'Accessories', route: '/womenOuterCategoryPage'},
            ],
            menCategories: [
              { item: 'Outer'},
              { item: 'Top'},
              { item: 'Bottom'},
              { item: 'Accessories'},
            ]
        }

    },
    computed: {
      ...mapState(['userInfo','isLogin'])
    },
    methods: {
      ...mapActions(['fetchSession']),
        btnMainPage() {
          this.$router.push({name: 'MainPage'})
        },
        logout() {
            // axios.post('http://localhost:7777/member/logout')
            // .then(res => {
            // this.$store.commit('USER_LOGIN', res.data)
            // this.fetchSession(this.$cookies.remove('session'))
            // this.$store.commit('FETCH_USER_INFO', [])
            // })

            this.$store.commit('USER_LOGIN', !this.isLogin)
            this.fetchSession(this.$cookies.remove('session'))
            this.$store.commit('FETCH_USER_INFO', [])
            this.$emit('resetCartCount')
            alert("로그아웃 되었습니다.")
            
        }
    }
}
</script>

<style scoped>
#nav-cart-count {
  background-color: rgba(249, 11, 11);
  color: white;
  border-radius: 50%;
  height: 15px;
  width: 15px;
  font-size: 14px;
  align-items: center;
  display: flex;
  justify-content: center;
  position: absolute;
  margin-left: 15px;
  margin-bottom: 25px;
}
</style>