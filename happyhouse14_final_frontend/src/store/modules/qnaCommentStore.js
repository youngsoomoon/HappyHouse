import http from "@/api/http";

const qnaStore = {
  namespaced: true,
  state: {
    qnaComments: [],
    qnaComment: null,
  },
  mutations: {
    SET_QNA_COMMENTS(state, payload) {
      state.qnaComments = payload;
    },
    SET_QNA_COMMENT_DETAIL(state, payload) {
      state.qnaComment = payload;
    },
  },
  actions: {
    qnaCommentList({ commit }, qnaNo) {
      http.get(`/qna/${qnaNo}/comment`).then((res) => {
        console.log(res);
        commit("SET_QNA_COMMENTS", res.data);
      });
    },
    qnaCommentDetail({ commit }, payload) {
      http
        .get(`/qna/${payload.qnaNo}/comment/${payload.commentNo}`)
        .then((res) => {
          console.log(res);
          commit("SET_QNA_COMMENT_DETAIL", res.data);
        });
    },
    qnaCommentPost(store, payload) {
      http.post(`/qna/${payload.qnaNo}`, payload).then((res) => {
        console.log(res);
        store.dispatch("qnaCommentList", payload.qnaNo);
      });
    },
    qnaCommentUpdate(store, payload) {
      http
        .put(`/qna/${payload.qnaNo}/comment/${payload.commentNo}`, payload)
        .then((res) => {
          console.log(res);
          store.dispatch("qnaCommentList", payload.qnaNo);
        });
    },
    qnaCommentDelete(store, payload) {
      http
        .delete(`/qna/${payload.qnaNo}/comment/${payload.commentNo}`)
        .then((res) => {
          console.log(res);
          store.dispatch("qnaCommentList", payload.qnaNo);
        });
    },
    qnaCommentDeleteAll(store, payload) {
      http.delete(`/qna/${payload.qnaNo}/comment`).then((res) => {
        console.log(res);
      });
    },
  },
};

export default qnaStore;
