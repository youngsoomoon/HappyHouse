<template>
  <div>
    <div :key="index" v-for="(item, index) in qnaComments">
      <qna-comment-list-item :commentObj="item" />
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import QnaCommentListItem from "./QnaCommentListItem.vue";

const qnaCommentStore = "qnaCommentStore";
export default {
  name: "CommentList",
  props: {
    contentId: Number,
  },
  data() {
    return {
      comments: "",
    };
  },
  components: {
    QnaCommentListItem,
  },
  computed: {
    ...mapState(qnaCommentStore, ["qnaComments", "qnaComment"]),
  },
  created() {
    this.qnaCommentList(this.contentId);
  },
  methods: {
    ...mapActions(qnaCommentStore, ["qnaCommentList"]),
  },
};
</script>

<style></style>
