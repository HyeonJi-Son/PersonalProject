<template>
    <v-container style="width: 643px; margin-top: 85px; font-size: 14px">
      <v-flex>
            <v-card style="width: 643px; height: 415px; text-align: center;" v-if="!followBoards || (Array.isArray(followBoards) && followBoards.length === 0)">
                <br/><br/><br/>
                <div style="font-size: 18px">Vuestagram에 오신 것을 환영합니다</div>
                <div style="color: gray">다른 사람을 팔로우하면 피드에서 상대방의 사진을 볼 수 있습니다.</div>
            </v-card>
            <v-card style="margin-bottom: 30px;" v-else v-for="board in followBoards" :key="board.boardNo">
                <form @submit.prevent="onSubmit(board.boardNo)">
                    <table >
                        <tr >
                            <td style="width: 32px; padding: 14px 14px 14px 16px; color: black">
                                <div style="border-radius: 70%; overflow: hidden; width: 32px ">
                                    <router-link style="text-decoration: none;" :to="{
                                        name: 'MyProfilePage',
                                        params: {memberNo: board.member.memberNo.toString()}}">
                                        <v-img v-if="board.member.imageName" width="32" :src="require(`@/assets/mImage/${board.member.imageName}`)"/>
                                        <v-img v-else width="32" src="@/assets/profile.jpg"/>
                                    </router-link>
                                </div>
                            </td>
                            <td style="font-weight: bold;">
                                <router-link style="text-decoration: none; color: black" :to="{
                                        name: 'MyProfilePage',
                                        params: {memberNo: board.member.memberNo.toString()}}">
                                        {{ board.member.memberId }}
                                </router-link>
                            </td>
                            <td v-if="loginInfo.memberNo==board.member.memberNo" align="right" style="padding-right: 12px;"> 
                                <v-menu offset-y min-width="100">
                                    <template v-slot:activator="{ on }">
                                        <v-btn icon v-on="on">
                                            <v-icon>
                                                mdi-dots-horizontal
                                            </v-icon>
                                        </v-btn> 
                                    </template>
                                    <v-list>
                                        <v-list-item-title> 
                                            <v-btn text block color="red" @click="onDelete(board.boardNo)">삭제</v-btn>
                                        </v-list-item-title>
                                        <v-list-item-title> 
                                            <router-link style="text-decoration: none;" :to="{ name: 'BoardModifyPage', params: { boardNo: board.boardNo } }">
                                                <v-btn text block>수정</v-btn>
                                            </router-link>
                                        </v-list-item-title>
                                        <v-list-item-title> 
                                            <router-link style="text-decoration: none;" :to="{ name: 'BoardReadPage', params: { boardNo: board.boardNo } }">
                                                <v-btn text block>게시물로 이동</v-btn>
                                            </router-link>
                                        </v-list-item-title>
                                    </v-list>
                                </v-menu>
                            </td>
                            <td v-else align="right" style="padding-right: 12px;"> 
                                <v-menu offset-y min-width="100">
                                    <template v-slot:activator="{ on }">
                                        <v-btn icon v-on="on">
                                            <v-icon>
                                                mdi-dots-horizontal
                                            </v-icon>
                                        </v-btn> 
                                    </template>
                                    <v-list>
                                        <v-list-item-title> 
                                            <follow-button :board="board" :myFollows="myFollows"/>
                                        </v-list-item-title>
                                        <v-list-item-title> 
                                            <router-link style="text-decoration: none;" :to="{ name: 'BoardReadPage', params: { boardNo: board.boardNo } }">
                                                <v-btn text block>게시물로 이동</v-btn>
                                            </router-link>
                                        </v-list-item-title>
                                    </v-list>
                                </v-menu>
                            </td>
                        </tr>
                        <tr align="center" >
                            <td colspan="3" >
                                <div class="slide-3d " >
                                    <!-- <v-app id="inspire"> -->
                                        <v-container style="padding: 0px">
                                            <swiper class="swiper position-absolute" :options="swiperOption">
                                                <swiper-slide><v-img  width="615px" height="615px" :src="require(`@/assets/mImage/${board.boardImage}`)"/></swiper-slide>
                                                <swiper-slide v-if="board.boardImage2"><v-img width="615px" height="615px" :src="require(`@/assets/mImage/${board.boardImage2}`)"/></swiper-slide>
                                                <swiper-slide v-if="board.boardImage3"><v-img width="615px" height="615px" :src="require(`@/assets/mImage/${board.boardImage3}`)"/></swiper-slide>
                                                <div class="swiper-pagination" slot="pagination"></div>
                                                <div class="swiper-button-prev" slot="button-prev"></div>
                                                <div class="swiper-button-next" slot="button-next"></div>
                                            </swiper>
                                        </v-container>
                                    <!-- </v-app> -->
                                </div>
                            </td>
                        </tr >
                        <like-button :board="board" :myLikes="myLikes"/>
                        <tr v-if="board.likes.length" align="left">
                            <td colspan="3" style="padding-left: 16px">
                                {{ board.likes.length }}명이 좋아합니다
                            </td>
                        </tr>
                        <tr align="left">
                            <td colspan="3" style="padding-left: 16px">
                                <b>{{ board.member.memberId }}</b>&nbsp;{{ board.content }}
                            </td>
                        </tr>
                        <tr v-if="board.comments.length"> 
                            <td colspan="3" style="padding-left: 16px;">
                                <router-link style="text-decoration: none; color: grey" :to="{
                                    name: 'BoardReadPage',
                                    params: {boardNo: board.boardNo.toString()}}">
                                    댓글 {{ board.comments.length}}개 모두 보기
                                </router-link>
                            </td>
                        </tr>
                        <!-- <tr v-if="board.comments.length>1"> 
                            <td colspan="3" style="padding-left: 16px;">
                                <b v-if="board.comments[board.comments.length-2]">{{ board.comments[board.comments.length-2].member.memberId }}</b>&nbsp;{{board.comments[board.comments.length-2].content}} <br/>
                                <b v-if="board.comments[board.comments.length-1]">{{ board.comments[board.comments.length-1].member.memberId }}</b>&nbsp;{{board.comments[board.comments.length-1].content}}
                            </td>
                        </tr> -->
                        <tr align="left" style="font-size: 10px">
                            <td colspan="3" style="padding: 8px 0px 16px 16px; color: grey">
                                {{ board.regDate }}
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                            <hr style="border: 0; height: 1px; background: #d8d8d8; "/>
                            </td>
                        </tr>
                        <tr align="left">
                            <td colspan="2" style="padding: 14px 0px 14px 16px;">
                                <textarea type="text" style="width: 100%; height: 18px"  placeholder="댓글 달기..." v-model="content"/>
                            </td>
                            <td align="right"> 
                                <v-btn text color="primary" type="submit">
                                    게시
                                </v-btn>
                            </td>
                        </tr>
                        
                    </table>
                </form>
            </v-card>
        </v-flex>
    </v-container>
</template>

<script>
import FollowButton from '@/components/likes/FollowButton'
import LikeButton from '@/components/likes/LikeButton'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
import { Swiper, SwiperSlide } from 'vue-awesome-swiper'
import 'swiper/css/swiper.css'
export default {
    name: 'MyFollowList',
    components : {
        FollowButton,
        LikeButton,
        Swiper,
        SwiperSlide
    },
    props: {
        followBoards: {
            type: Array
        },
    },
    data() {
        return {
            loginInfo: JSON.parse(localStorage.getItem('loginInfo')),
            content: '',
            swiperOption: {
                effect: 'coverflow',
                grabCursor: true,
                // pagination: {
                //     el: '.swiper-pagination',
                //     dynamicBullets: false
                // },
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                    },
            }
        }
    },
    computed: {
        ...mapState(['myLikes']),
        ...mapState(['myFollows']),
    },
    mounted () {
        this.fetchMyLikesList(this.loginInfo.memberNo)
        this.fetchMyFollowList(this.loginInfo.memberNo)
    },
    methods: {
        ...mapActions(['fetchMyLikesList']),
        ...mapActions(['fetchMyFollowList']),
        onDelete(boardNo) {
            console.log(boardNo)
            this.$emit('click', {boardNo})
        },
        onSubmit(boardNo) {
            const { content } = this
            this.$emit('submit', { boardNo, memberNo: this.loginInfo.memberNo, content })
        },
        onFollow(memberNo) {
            axios.post(`http://localhost:7777/follow/${this.loginInfo.memberNo}/${memberNo}`, {loginNo: this.loginInfo.memberNo, memberNo})
                .then(() => {
                    history.go(0);
                })
                .catch(() => {
                    alert('문제 발생!')
                })
        },
        onLikes(boardNo) {
            axios.post(`http://localhost:7777/likes/${boardNo}/${this.loginInfo.memberNo}`, {boardNo, memberNo: this.loginInfo.memberNo})
                .then(() => {
                    history.go(0);
                })
                .catch(() => {
                    alert('문제 발생!')
                })
        },
    }
}
</script>

<style lang="scss" scoped>
.slide-3d {
    width: 615px;
}
.swiper {
    .swiper-slide {

        width: 615px;
        // height: 648px;
        height: 615px;
        background-color: white;
        background-position: center;
        background-size: cover;
    }
    
}
.swiper-button-prev, .swiper-button-next {
  --swiper-theme-color: #ffffff;
}
.swiper-container {
  z-index: auto !important;
}
.swiper-wrapper {
  z-index: auto !important;
}
</style>