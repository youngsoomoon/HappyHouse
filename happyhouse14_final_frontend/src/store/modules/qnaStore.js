import http from "@/api/http";
import router from "@/router";

const qnaStore = {
  namespaced: true,
  state: {
    qnas: [],
    qna: null,
  },
  mutations: {
    SET_QNA(state, payload) {
      state.qnas = payload;
    },
    SET_QNA_DETAIL(state, payload) {
      state.qna = payload;
    },
  },
  actions: {
    qnaList({ commit }) {
      http.get(`/qna`).then((res) => {
        console.log(res);
        const qnas = res.data;
        commit("SET_QNA", qnas);
      });
    },
    qnaDetail({ commit }, qnaNo) {
      http.get(`/qna/${qnaNo}`).then((res) => {
        // console.log(res);
        const qna = res.data;
        console.log(qna);
        commit("SET_QNA_DETAIL", qna);
      });
    },
    qnaPost(store, payload) {
      http.post(`/qna`, payload).then((res) => {
        console.log(res);
      });
    },
    qnaUpdate(store, qna) {
      http.put(`/qna/${qna.qnaNo}`, qna).then((res) => {
        console.log(res);
      });
    },
    async qnaDelete(store, qnaNo) {
      await store.dispatch(
        "qnaCommentStore/qnaCommentDeleteAll",
        { qnaNo },
        { root: true }
      );
      await http.delete(`/qna/${qnaNo}`).then((res) => {
        console.log(res);
        router.push({ name: "QnaView" });
      });
    },
  },
};

export default qnaStore;
