import http from "@/api/http";
import router from "@/router";

export default {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: JSON.parse(sessionStorage.getItem("user-info")),
    interests: [],
    adminInfo: JSON.parse(sessionStorage.getItem("admin-info")),
    users: [],
    user: null,
  },
  getters: {},
  mutations: {
    SET_USER_INFO(state, payload) {
      state.userInfo = payload;
    },
    SET_ADMIN_INFO(state, payload) {
      state.adminInfo = payload;
    },
    SET_IS_LOGIN_ERROR(state, payload) {
      state.isLoginError = payload;
    },
    SET_IS_LOGIN(state, payload) {
      state.isLogin = payload;
    },
    SET_USER_INTEREST(state, payload) {
      state.interests = payload;
    },
    CLEAR_USER_INTEREST(state) {
      state.interests = [];
    },
    SET_USER_LIST(state, payload) {
      state.users = payload;
    },
    SET_USER_DETAIL(state, payload) {
      state.user = payload;
    },
  },
  actions: {
    register({ commit }, payload) {
      console.log(payload);
      http.post("/register", payload).then(() => {
        commit("SET_IS_LOGIN_ERROR", false);
        router.push({ name: "UserLogin" }).catch(() => {});
      });
    },
    async login({ commit, dispatch }, payload) {
      await http.post("/login", payload).then((res) => {
        if (res.data.message === "SUCCESS") {
          let user = res.data.loginUser;
          commit("SET_USER_INFO", user);
          commit("SET_IS_LOGIN_ERROR", false);
          commit("SET_IS_LOGIN", true);
          sessionStorage.setItem("user-info", JSON.stringify(user));
          dispatch("getInterestAll", user.id);
          if (user.id === "admin") {
            commit("SET_ADMIN_INFO", user);
            sessionStorage.setItem("admin-info", JSON.stringify(user));
          }
          router.push({ name: "HomeView" }).catch(() => {});
        } else {
          commit("SET_IS_LOGIN_ERROR", true);
          commit("SET_IS_LOGIN", false);
        }
      });
    },
    getUser({ commit }, payload) {
      http.get(`/member/${payload}`).then((res) => {
        console.log(res);
        const user = res.data;
        sessionStorage.setItem("user-info", JSON.stringify(user));
        commit("SET_USER_INFO", user);
      });
    },
    logout({ commit }) {
      const user = JSON.parse(sessionStorage.getItem("user-info"));
      http.get(`/logout/${user.id}`).then(() => {});
      commit("SET_USER_INFO", null);
      commit("SET_ADMIN_INFO", null);
      commit("SET_IS_LOGIN", false);
      sessionStorage.removeItem("user-info");
      commit("CLEAR_USER_INTEREST");
      sessionStorage.removeItem("admin-info");
      router.push({ name: "HomeView" }).catch(() => {});
    },
    updateUser(store, payload) {
      console.log(payload);
      http.put(`/memberUpdate/${payload.id}`, payload).then(() => {
        console.log("업데이트 완료");
      });
    },
    async deleteUser(store, payload) {
      await store.dispatch("interestDeleteAll", payload);
      http.delete(`/memberDelete/${payload}`, payload).then(() => {
        alert("회원 삭제 완료");
      });
    },
    // 관심 지역 관련
    getInterestAll(store, id) {
      http.get(`/member/${id}/interest`).then((res) => {
        console.log(res.data, id);
        const interest = res.data.map((el) => {
          const obj = {
            ...el,
            id,
          };
          return obj;
        });
        console.log(interest);
        store.commit("SET_USER_INTEREST", interest);
      });
    },
    interestPost(store, payload) {
      http.post(`/member/${payload.id}/interest`, payload).then((res) => {
        console.log(res);
        store.dispatch("getInterestAll", payload.id);
      });
    },
    interestDeleteAll(store, payload) {
      http.delete(`/member/${payload}/interest`).then(() => {});
    },
    interestDelete(store, payload) {
      http
        .delete(`/member/${payload.id}/interest/${payload.dongCode}`)
        .then(() => {
          store.dispatch("getInterestAll", payload.id);
        });
    },
    userList({ commit }) {
      http.get(`/member`).then((res) => {
        console.log(res);
        const users = res.data;
        commit("SET_USER_LIST", users);
      });
    },
    userDetail({ commit }, userId) {
      http.get(`/member/${userId}`).then((res) => {
        // console.log(res);
        const user = res.data;
        console.log("user: ", user);
        commit("SET_USER_DETAIL", user);
      });
    },
  },
};
