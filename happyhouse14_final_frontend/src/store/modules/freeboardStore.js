import http from "@/api/http";
import router from "@/router";

const freeboardStore = {
  namespaced: true,
  state: {
    freeboards: [],
    freeboard: null,
  },
  getters: {},
  mutations: {
    SET_FREEBOARD(state, payload) {
      state.freeboard = payload;
    },
    SET_FREEBOARD_DETAIL(state, payload) {
      state.freeboard = payload;
    },
  },
  actions: {
    freeboardList({ commit }) {
      http.get(`/freeboard`).then((res) => {
        console.log(res);
        const freeboards = res.data;
        commit("SET_FREEBOARD", freeboards);
      });
    },
    freeboardDetail({ commit }, freeboardNo) {
      http.get(`/freeboard/${freeboardNo}`).then((res) => {
        // console.log(res);
        const freeboard = res.data;
        console.log(freeboard);
        commit("SET_FREEBOARD_DETAIL", freeboard);
      });
    },
    freeboardPost(store, payload) {
      http.post(`/freeboard`, payload).then((res) => {
        console.log(res);
      });
    },
    freeboardUpdate(store, freeboard) {
      console.log("freeboard: ", freeboard);
      http.put(`/freeboard/${freeboard.freeboardNo}`, freeboard).then((res) => {
        console.log(res);
      });
    },
    freeboardDelete(store, freeboardNo) {
      http.delete(`/freeboard/${freeboardNo}`).then((res) => {
        console.log(res);
        router.push({ name: "FreeBoardView" });
      });
    },
  },
};

export default freeboardStore;
