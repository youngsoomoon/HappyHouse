<template>
  <b-container class="bv-example-row">
    <b-alert variant="secondary" show><h3>회원 정보</h3></b-alert>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                readonly
                :value="user.id"
                placeholder="아이디"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                id="userpwd"
                :readonly="!updatePage"
                v-model="user.pwd"
                placeholder="비밀번호"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                type="text"
                id="username"
                :value="user.name"
                readonly
                placeholder="이름 (ex. 홍길동)"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="생년월일:" label-for="userdate">
              <b-form-input
                type="date"
                id="userdate"
                readonly
                :value="user.birthDate"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="주소:" label-for="userpwd">
              <b-form-input
                type="text"
                id="useraddress"
                :readonly="!updatePage"
                v-model="user.address"
                placeholder="주소 입력...."
              ></b-form-input>
            </b-form-group>
            <b-row align-h="center" v-if="!updatePage">
              <b-button
                type="button"
                variant="primary"
                class="m-1 w-25"
                @click="toUpdate"
                >정보 수정</b-button
              >
              <b-button
                type="button"
                variant="success"
                class="m-1 w-25"
                @click="deleteUser"
                >회원 탈퇴</b-button
              >
            </b-row>
            <b-row align-h="center" v-else>
              <b-button
                type="button"
                variant="primary"
                class="m-1 w-25"
                @click="toUpdate"
                >수정 취소</b-button
              >
              <b-button
                type="button"
                variant="success"
                class="m-1 w-25"
                @click="updateUser"
                >수정 완료</b-button
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
import { mapState } from "vuex";
const userStore = "userStore";
export default {
  data() {
    return {
      updatePage: false,
      user: null,
    };
  },
  created() {
    this.user = { ...this.userInfo };
    console.log(this.user, this.userInfo);
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    updateUser() {
      this.toUpdate();
      this.$store.dispatch("userStore/updateUser", this.user);
      this.$store.dispatch("userStore/getUser", this.user.id);
    },
    deleteUser() {
      this.$store.dispatch("userStore/deleteUser", this.user.id);
      this.$store.dispatch("userStore/logout");
    },
    toUpdate() {
      this.updatePage = !this.updatePage;
    },
  },
};
</script>

<style></style>
