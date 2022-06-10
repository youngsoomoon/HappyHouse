<template>
  <div>
    <qna-comment-create
      v-if="updateMode"
      :updateMode="updateMode"
      :commentObj="commentObj"
      @toComment="toUpdate"
    />
    <b-container v-else fluid>
      <b-row align-v="center" class="border border-info">
        <b-col cols="2" class="border-right border-info py-3">
          <div>
            {{ commentObj.id }}
          </div>
          <div>
            {{ commentObj.regtime }}
          </div>
        </b-col>
        <b-col>
          {{ commentObj.content }}
        </b-col>
        <b-col
          v-if="commentObj.id === id || id === 'admin'"
          cols="2"
          class="pt-3 d-xl-flex justify-content-between"
        >
          <b-button v-if="commentObj.id === id" variant="info" @click="toUpdate"
            >수정</b-button
          >
          <b-button variant="info" @click="qnaCommentDelete(commentObj)"
            >삭제</b-button
          >
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import QnaCommentCreate from "@/components/board/qna/comment/QnaCommentCreate.vue";
const userStore = "userStore";
const qnaCommentStore = "qnaCommentStore";
export default {
  name: "CommentListItem",
  props: {
    commentObj: Object,
  },
  data() {
    return {
      name: "",
      updateMode: false,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    id() {
      return this.userInfo.id;
    },
  },
  components: { QnaCommentCreate },
  methods: {
    ...mapActions(qnaCommentStore, ["qnaCommentDelete"]),
    toUpdate() {
      this.updateMode = !this.updateMode;
    },
  },
};
</script>

<style scoped>
.btn {
  margin-bottom: 1em;
}
</style>
