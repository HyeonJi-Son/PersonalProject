<template>
  <div>
    <sign-up-form @submit="onSubmit"></sign-up-form>
  </div>
</template>

<script>
import SignUpForm from "@/components/member/SignUpForm.vue";
import axios from "axios";

export default {
  name: "SignUpPage.vue",
  components: {
    SignUpForm,
  },
  methods: {
    onSubmit(payload) {
      const { id, pw, name, nickName, email, auth } = payload;
      axios
        .post("http://localhost:7777/member/register", {
          id,
          pw,
          name,
          nickName,
          email,
          auth,
        })
        .then((res) => {
          alert("회원가입 완료" + res);
          this.$router.push("/login");
        })
        .catch((res) => {
          alert(res.response.data.message);
        });
    },
  },
};
</script>
