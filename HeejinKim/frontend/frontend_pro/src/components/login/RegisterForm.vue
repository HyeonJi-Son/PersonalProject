<template>
    <v-dialog v-model="register_dialog" max-width="350">
        <template v-slot:activator="{ on, attrs }">
            <v-btn text color="black" class="btn-flat" v-on="on" v-bind="attrs">
                Register
            </v-btn>
        </template>
        <v-card>
            <v-container >
                <form @submit.prevent="onRegister">
                    
                    <v-card class="card_box">
                        <h1 class="text-center"> Create Account </h1>
                        <div class="input" justify-center>
                            
                            <div class="input_area">
                            <v-text-field class="pl-4 pr-4" flat solo v-model="userId" :disabled="valid" type="text" label="userId" :rules="rulesId">
                                 
                            </v-text-field><v-btn @click="checkValid" font-weight>check</v-btn> </div>

                            <div class="input_area">
                            <v-text-field class="pl-4 pr-4 pt-3" flat solo v-model="password" type="password" label="password"
                            :rules="rulesPassword"> </v-text-field></div>

                            <div class="input_area">
                            <v-text-field class="pl-4 pr-4" flat solo v-model="passwordCheck" type="password" label="passwordCheck" 
                            :rules="rulesPWCheck"> </v-text-field></div>

                            <div class="input_area">
                            <v-text-field class="pl-4 pr-4" flat solo v-model="email" type="text" label="email" 
                            :rules="rulesEmail"> </v-text-field></div>

                            
                            <div class="row">
                                <v-radio-group v-model="radioGroup" row>
                                  <v-radio :label="`${kindsOfMember[0]}`" :value="`${kindsOfMember[0]}`"></v-radio>
                                  <v-radio :label="`${kindsOfMember[1]}`" :value="`${kindsOfMember[1]}`"></v-radio>
                                </v-radio-group>
                            </div>


                            <div class="btn-size pt-5">
                                <v-btn width="250" height="40px" type="submit" color="black" style="margin-top:-4%;"
                                class="white--text" rounded >
                                SIGN UP
                                </v-btn>
                            </div>
                        </div>
                    </v-card>
                </form>
            </v-container>
        </v-card>
    </v-dialog>
</template>

<script>

import axios from "axios";

export default {
    name: 'RegisterForm',

    props:{
        members:{
            type:Array
        }
    },

        data(){
            return {
                register_dialog: false,
                userId : '',
                password: '',
                passwordCheck : '',
                email : '',
                valid: false,
                radioGroup: 1,
                kindsOfMember: [
                    'Individual',
                    'Manager'
                ],
                rulesId:[
                    v => !!v || 'Enter your id'
                ],

                rulesPassword:[
                    v => !!v || 'Enter your password',
                    v => v.length >= 3 || 'Password must be more than 3 characters'     
                ],

                rulesPWCheck:[
                    v => !!v || 'Confirm your password',
                    v => v === this.password || 'Wrong password'
                ],

                rulesEmail:[
                v => !!v ||  'Enter your email',
                //v => /.+@.+\..+/.test(v) || 'Email must be valid',
                
                ]
                
            }
        },
    methods: {
        onRegister () {
                      
            const {  userId, password, passwordCheck, email, radioGroup } = this
            const auth = (radioGroup == 'Individual' ? 'Individual' : 'Manager')
            this.$emit('submit', { userId, password, passwordCheck, email, auth})
            
        },
        checkValid() {
            const { userId } = this
            let temp;
            axios.get(`http://localhost:7777/jpaMember/check/${userId}`)
                .then(res => {
                    this.temp = res.data;
                    if(this.temp){
                        alert("Valid Id")
                        this.valid = res.data;
                    }else {
                        alert("Duplicate Id")
                        this.valid = false;
                    }
                })
            return temp
        }
    },
    
}
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Poiret+One&display=swap');

.theme--light.v-card {
    background-color:#f3f3f3;
}
.card_box{
    
    height: 700px;
    text-align: center;
    position: relative;
    display: center;
    padding: 5% 0% 30% 0%;
    width: 500px;
    
}

.card_box h1{

    font-family: 'Poiret One', cursive;
    font-size: 50px;
    margin-top: 20px;
    color: black;
    text-align: center;
    
}
.input{
    
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 40px;  

}
.v-input{  
    font-size: 20px;
}




/* 입력칸 배경색
.theme--light.v-text-field--solo > .v-input__control > .v-input__slot {
    background: #583d3d;
}*/





</style>
