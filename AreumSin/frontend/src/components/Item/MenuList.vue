<template>
  <v-col>
    <v-card class="mx-auto" width="500px" tile >
      <v-navigation-drawer permanent width="500px" height="880px">
        <v-list nav dense>
          <v-list-item width="500px" style="font-size: 55px; margin-bottom: 15px; ">
            마이페이지
          </v-list-item>

          <v-list-item-group>
            <v-list-item v-for="link in links" :key="link.icon" :to="link.route">
              <v-list-item-icon style="align-self: center">
                <v-icon style="font-size: 35px" v-text="link.icon"></v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item v-text="link.text" style="font-size: 35px"></v-list-item>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-navigation-drawer>
    </v-card>
  </v-col>
</template>

<script>
export default {
  name: "MenuList",
  data(){
    return{
      links: [
        { icon: 'mdi-account', text: '회원 관리', name: 'MyPage', route: '/myPage' },
        { icon: 'mdi-book', text: '일기', name: 'MyPageDiary', route: '/myPageDiary' },
        { icon: 'mdi-map-marker', text: '저장된 장소', name: 'MyPagePlaceList', route: '/myPagePlaceList' },
      ],
    }
  }
}
</script>

<style scoped>

</style>