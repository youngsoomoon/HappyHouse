<template>
  <b-navbar toggleable="lg" type="dark" variant="secondary">
    <b-navbar-brand :to="{ name: 'HomeView' }">
      <img
        src="../../assets/logo3.jpg"
        class="d-inline-block align-middle"
        width="200px"
        alt="ssafy"
      />
    </b-navbar-brand>
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item
          :to="{ name: 'NoticeView' }"
          :active="
            $route.name === 'NoticeList' ||
            $route.name === 'NoticeDetail' ||
            $route.name === 'NoticeCreate'
          "
        >
          공지사항
        </b-nav-item>
        <b-nav-item
          :to="{ name: 'QnaView' }"
          :active="
            $route.name === 'QnaList' ||
            $route.name === 'QnaDetail' ||
            $route.name === 'QnaCreate'
          "
        >
          QnA
        </b-nav-item>
        <b-nav-item
          :to="{ name: 'FreeBoardView' }"
          :active="
            $route.name === 'FreeBoardList' ||
            $route.name === 'FreeBoardDetail' ||
            $route.name === 'FreeBoardCreate'
          "
        >
          자유게시판
        </b-nav-item>
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto" v-if="userInfo">
        <b-nav-item
          :to="{ name: 'UserManagementView' }"
          :active="
            $route.name === 'UserList' ||
            $route.name === 'FreeBoardDetail' ||
            $route.name === 'FreeBoardCreate'
          "
          v-if="adminInfo"
        >
          회원관리
        </b-nav-item>
        <b-nav-item-dropdown right>
          <template #button-content>
            <em>{{ userInfo.name }}님, 환영합니다.</em>
          </template>
          <b-dropdown-item :to="{ name: 'UserMyPage' }"
            >회원정보</b-dropdown-item
          >
          <b-dropdown-item @click="logout">로그아웃</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
      <b-navbar-nav v-else class="ml-auto">
        <b-nav-item :to="{ name: 'UserLogin' }" right> Login </b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
import { mapActions, mapState } from "vuex";
const userStore = "userStore";
export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(userStore, ["userInfo", "adminInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["logout"]),
  },
};
</script>

<style></style>
