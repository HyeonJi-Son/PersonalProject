<template>
<div>
  <v-card v-if="isLogin" outlined>
    <v-card-text>
      <v-list-item-title>
        My Q & A
      </v-list-item-title>
      <v-divider></v-divider>

      <v-list-item-title   
        v-if="!qnaList || (Array.isArray(qnaList) && qnaList.length ===0)">
        <p>작성한 게시물이 없습니다.</p>
      </v-list-item-title>

      <v-list v-else v-for="list in qnaList" :key="list.index">
        <v-list-item-group>
          <v-list-item @click="readQnAPage(list.qnaNo)">
            <v-list-item-title>
              {{ list.title }}
            </v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-card-text>
  </v-card>

  <br>

  <v-card>
    <v-card-text>
      <v-list-item-title>
        주문내역
      </v-list-item-title>
      <v-divider></v-divider>
      <v-list v-for="cartItem in cartItems" :key="cartItem.id">
        <v-list-item-title>
        {{ cartItem.product.name }}
        </v-list-item-title>
      </v-list>
      <v-list-item-title v-model="totalCost"></v-list-item-title>
      <v-list-item-title v-model="address"></v-list-item-title>
    </v-card-text>
  </v-card>
</div>
</template>


<script>
import axios from 'axios'
import { mapState } from 'vuex'
import EventBus from '@/eventBus.js'

export default {
  data () {
    return {
      qnaList: [],
      cartItems: [],
      totalCost: '',
      address:'',
    }
  },
  mounted () {
    const id = this.userInfo.id

    axios.get(`http://localhost:7777/qna/userRead/${id}`)
    .then(res => {
        console.log(res)
        const list = res.data.reverse()
        if (list.length >= 3) {
            for (let i = 0; i < 3; i++) {
            this.qnaList.push({ qnaNo: list[i].qnaNo, title: list[i].title })
            }
        } else {
            for (let i = 0; i < list.length; i++) {
            this.qnaList.push({ qnaNo: list[i].qnaNo, title: list[i].title })
            }
        } 
    })
  },
  created() {
   EventBus.$on('btnConfirm', (payload) => {
      console.log(payload)
   })
  },
  methods: {
    readQnAPage(qnaNo) {
      this.$router.push(
        { name: 'QnAReadPage', params: { qnaNo: qnaNo } }
      )
    }
  },
  computed: {
    ...mapState([ 'userInfo', 'isLogin' ])
  }
}
</script>