<template>
    <v-container style="text-align: center; width: 350px; height: 382px; margin-top: 120px;">
            <v-flex>
                <v-card>
                    <form @submit.prevent="onSubmit"  >
                        <img style="margin: 40px 0px 40px 0px;" src="@/assets/vuelogo.png"/>
                        <div>
                            <input style="background-color: #fafafa; 
                                margin: 3px;
                                border: 1px solid #d6d6d6; 
                                border-radius: 3px; 
                                width: 268px; 
                                height: 38px;
                                padding-left: 5px;" 
                            type="text" placeholder="아이디" v-model="memberId"/>
                        </div>
                        <div>
                            <input style="background-color: #fafafa; 
                                margin: 3px;
                                border: 1px solid #d6d6d6; 
                                border-radius: 3px; 
                                width: 268px; 
                                height: 38px;
                                padding-left: 5px;" 
                            type="password" placeholder="비밀번호" v-model="password"/>
                        </div>
                        <div><v-btn style="margin: 10px; width: 268px; height: 30px;" color="blue" class="white--text" type="submit">로그인</v-btn></div>
                    </form>
                    <div>
                        <router-link style="text-decoration: none;" :to="{name: 'ForgetPasswordPage'}">
                            <button style="margin-top: 50px;" class="indigo--text" type="button">비밀번호를 잊으셨나요?</button>
                        </router-link>
                    </div>
                    <br/>
                </v-card>
                <br/>
                <v-card style="width: 350px; height: 63px; display: table-cell; vertical-align: middle;">
                    <div>
                        계정이 없으신가요?
                        <router-link style="text-decoration: none;" :to="{name: 'RegisterPage'}">
                        <button class="blue--text" type="button">&nbsp;가입하기</button>
                        </router-link>
                    </div>
                </v-card>
            </v-flex>

        </v-container>
</template>

<script>
export default {
    name: "LoginForm.vue",
    data() {
        return {
            memberId: '',
            password: ''
        }
    },
    methods: {
        onSubmit() {
            const {memberId, password} = this
            this.$emit('submit', {memberId, password})
        }
    }
}
</script>