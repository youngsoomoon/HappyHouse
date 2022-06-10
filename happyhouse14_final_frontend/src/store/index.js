import Vue from "vue";
import Vuex from "vuex";

import aptStore from "@/store/modules/aptStore.js";
import noticeStore from "@/store/modules/noticeStore.js";
import userStore from "@/store/modules/userStore.js";
import qnaStore from "@/store/modules/qnaStore.js";
import freeboardStore from "@/store/modules/freeboardStore.js";
import qnaCommentStore from "@/store/modules/qnaCommentStore.js";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    userStore,
    aptStore,
    noticeStore,
    qnaStore,
    freeboardStore,
    qnaCommentStore,
  },
});
