import http from "@/api/http";
import router from "@/router";

const noticeStore = {
  namespaced: true,
  state: {
    notices: [],
    notice: null,
  },
  getters: {},
  mutations: {
    SET_NOTICE(state, payload) {
      state.notice = payload;
    },
    SET_NOTICE_DETAIL(state, payload) {
      state.notice = payload;
    },
  },
  actions: {
    noticeList({ commit }) {
      http.get(`/notice`).then((res) => {
        console.log(res);
        const notices = res.data;
        commit("SET_NOTICE", notices);
      });
    },
    noticeDetail({ commit }, payload) {
      http.get(`/notice/${payload}`, payload).then((res) => {
        console.log(res);
        const notice = res.data;
        commit("SET_NOTICE", notice);
      });
    },
    noticePost(store, payload) {
      http.post(`/notice`, payload).then((res) => {
        console.log(res);
      });
    },
    noticeUpdate(store, notice) {
      http.put(`/notice/${notice.noticeNo}`, notice).then((res) => {
        console.log(res);
      });
    },
    noticeDelete(store, noticeNo) {
      http.delete(`/notice/${noticeNo}`).then((res) => {
        console.log(noticeNo);
        console.log(res);
        router.push({ name: "NoticeView" });
      });
    },
  },
};

export default noticeStore;
