<template>
  <b-container class="bv-example-row">
    <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.id"
                @keyup.enter="tryLogin"
                required
                placeholder="아이디 입력...."
                ref="id"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                v-model="user.pwd"
                type="password"
                id="userpwd"
                required
                @keyup.enter="tryLogin"
                placeholder="비밀번호 입력...."
              ></b-form-input>
            </b-form-group>
            <div class="overflow-hidden mb-3">
              <b-form-checkbox
                id="idRemember"
                name="idRemember"
                class="float-right"
                v-model="idSave"
              >
                아이디 기억하기
              </b-form-checkbox>
            </div>
            <b-row align-h="center">
              <b-button
                type="button"
                variant="primary"
                class="m-1 w-25"
                @click="tryLogin"
                >로그인</b-button
              >
              <b-button
                type="button"
                variant="success"
                class="m-1 w-25"
                @click="toRegister"
                >회원가입</b-button
              >
            </b-row>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
const userStore = "userStore";
export default {
  data() {
    return {
      user: {
        id: this.$cookies.get("idCookie"),
        pwd: null,
      },
      idSave: this.$cookies.get("idSave"),
    };
  },
  computed: {
    ...mapState(userStore, ["isLoginError", "isLogin"]),
  },
  methods: {
    ...mapActions(userStore, ["login"]),
    async tryLogin() {
      await this.login(this.user);
      this.afterLogin();
    },
    afterLogin() {
      if (this.idSave) {
        if (this.isLogin) {
          this.$cookies.set("idCookie", this.user.id);
          this.$cookies.set("idSave", this.idSave);
        }
      } else {
        this.$cookies.remove("idCookie");
        this.$cookies.remove("idSave");
      }
      if (this.isLoginError) {
        this.user = {};
        this.idSave = false;
        this.$refs.id.focus();
      }
    },
    toRegister() {
      this.$router.push({ name: "UserRegister" });
    },
  },
};
</script>

<style></style>
