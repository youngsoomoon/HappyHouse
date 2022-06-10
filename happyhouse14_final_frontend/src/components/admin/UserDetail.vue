<template>
  <b-container class="bv-example-row">
    <b-alert variant="secondary" show><h3>회원 정보 관리</h3></b-alert>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                readonly
                :value="userObj.id"
                placeholder="아이디"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                id="userpwd"
                :readonly="!updatePage"
                v-model="userObj.pwd"
                placeholder="비밀번호"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                type="text"
                id="username"
                :value="userObj.name"
                readonly
                placeholder="이름 (ex. 홍길동)"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="생년월일:" label-for="userdate">
              <b-form-input
                type="date"
                id="userdate"
                readonly
                :value="userObj.birthDate"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="주소:" label-for="userpwd">
              <b-form-input
                type="text"
                id="useraddress"
                :readonly="!updatePage"
                v-model="userObj.address"
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
                @click="deleteUser(userObj.id)"
                :to="{ name: 'HomeView' }"
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
import { mapActions, mapState } from "vuex";
import router from "@/router";
const userStore = "userStore";
export default {
  name: "UserDetail",
  data() {
    const userId = this.$route.params.userId;
    console.log("id: ", userId);
    return {
      updatePage: false,
      userId,
    };
  },

  created() {
    this.userDetail(this.userId);
  },

  methods: {
    ...mapActions(userStore, ["userDetail", "deleteUser"]),
    updateUser() {
      this.toUpdate();
      console.log("user: ", this.userObj);
      this.$store.dispatch("userStore/updateUser", this.userObj);
      router.push({ name: "UserList" });
    },
    toUpdate() {
      this.updatePage = !this.updatePage;
    },
  },
  components: {},
  computed: {
    ...mapState(userStore, ["user"]),
    userObj() {
      return { ...this.user };
    },
  },
};
</script>
<style></style>
