<template>
  <b-input-group :prepend="id">
    <b-form-textarea
      id="textarea"
      v-model="content"
      placeholder="답변을 달아주세요"
      rows="3"
      max-rows="6"
    ></b-form-textarea>
    <b-input-group-append>
      <b-button
        variant="info"
        @click="updateMode ? updateComment() : createComment()"
        >작성 완료</b-button
      >
    </b-input-group-append>
  </b-input-group>
</template>

<script>
import { mapActions, mapState } from "vuex";
const userStore = "userStore";
const qnaCommentStore = "qnaCommentStore";
export default {
  name: "CommentCreate",
  props: {
    contentId: Number,
    updateMode: Boolean,
    commentObj: Object,
  },
  created() {
    console.log(this.commentObj);
  },
  data() {
    return {
      content: this.updateMode ? this.commentObj.content : "",
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    id() {
      return this.userInfo.id;
    },
  },
  methods: {
    ...mapActions(qnaCommentStore, ["qnaCommentPost", "qnaCommentUpdate"]),
    createComment() {
      console.log(this.content, this.contentId, this.id);
      if (this.content) {
        this.qnaCommentPost({
          qnaNo: this.contentId,
          id: this.id,
          content: this.content,
        });
      }
      this.content = "";
    },
    updateComment() {
      this.qnaCommentUpdate({ ...this.commentObj, content: this.content });
      this.$emit("toComment");
    },
  },
};
</script>

<style></style>
