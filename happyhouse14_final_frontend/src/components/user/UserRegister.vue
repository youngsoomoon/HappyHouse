<template>
  <b-container class="bv-example-row">
    <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                required
                v-model="userInfo.id"
                @keyup="checkId"
                placeholder="아이디"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                required
                v-model="userInfo.pwd"
                placeholder="비밀번호"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                type="text"
                id="username"
                required
                v-model="userInfo.name"
                placeholder="이름 (ex. 홍길동)"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="생년월일:" label-for="userdate">
              <b-form-input
                type="date"
                id="userdate"
                v-model="userInfo.birthDate"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="주소:" label-for="userpwd">
              <b-form-input
                type="text"
                id="useraddress"
                required
                v-model="userInfo.address"
                placeholder="주소 입력...."
              ></b-form-input>
            </b-form-group>
            <b-alert show variant="danger" v-if="isWrong"
              >올바른 정보를 입력해주세요.</b-alert
            >
            <b-row align-h="center">
              <b-button
                type="button"
                variant="primary"
                class="m-1 w-25"
                :to="{ name: 'UserLogin' }"
                >가입 취소</b-button
              >
              <b-button
                type="button"
                variant="success"
                class="m-1 w-25"
                @click="tryRegister"
                >가입 완료</b-button
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
import { mapActions } from "vuex";
export default {
  name: "MemberRegister",
  data() {
    return {
      userInfo: {
        id: null,
        pwd: null,
        name: null,
        birthDate: null,
        address: null,
      },
      isWrong: false,
    };
  },

  methods: {
    ...mapActions("userStore", ["register"]),
    async tryRegister() {
      const user = this.userInfo;
      if (user.id && user.pwd && user.name && user.birthDate && user.address) {
        this.isWrong = false;
        console.log(this.userInfo);
        await this.register(this.userInfo);
      } else {
        this.isWrong = true;
      }
    },
    async checkId() {
      console.log(this.userInfo.id);
    },
  },
};
</script>

<style></style>
