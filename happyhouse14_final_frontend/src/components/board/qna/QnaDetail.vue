<template>
  <b-container class="bv-example-row" fluid="sm">
    <b-card border-variant="secondary">
      <b-row>
        <b-col class="p-3 mb-3 bg-light text-secondary"
          ><b>등록 번호: </b> {{ qna.qnaNo }}</b-col
        >
        <b-col class="p-3 mb-3 bg-light text-secondary"
          ><b>글 제목: </b> {{ qna.title }}</b-col
        >
        <b-col>
          <b-row class="p-3 mb-3 bg-light text-secondary"
            ><b>글쓴이: </b> {{ qna.id }}</b-row
          >
        </b-col>
        <b-col>
          <b-row class="p-3 mb-3 bg-light text-secondary">
            <b>등록일: </b> {{ qna.regtime }}</b-row
          >
        </b-col>
      </b-row>
      <b-row class="border-top">
        <b-col sm="1" class="p-3 mb-1 bg-light text-secondary">
          <label for="textarea-large"><b>내용</b></label>
        </b-col>
        <b-col sm="10">
          <b-form-textarea
            id="textarea-no-resize"
            rows="7"
            no-resize
            :value="qna.content"
          >
          </b-form-textarea>
        </b-col>
      </b-row>
      <b-button-group class="mt-3" v-if="qna.id === id || id === 'admin'">
        <b-button variant="primary" @click="updateData">수정</b-button>&nbsp;
        <b-button variant="warning" @click="qnaDelete(contentId)">삭제</b-button
        >&nbsp;
      </b-button-group>
      <b-button class="mt-3" variant="success" @click="toList"
        >목록으로</b-button
      >
    </b-card>
    <b-card title="답변" class="content-detail-comment mt-3">
      <qna-comment-list :contentId="contentId" />
      <qna-comment-create :contentId="contentId" class="my-3" />
    </b-card>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import QnaCommentList from "@/components/board/qna/comment/QnaCommentList.vue";
import QnaCommentCreate from "@/components/board/qna/comment/QnaCommentCreate.vue";
const qnaStore = "qnaStore";
const userStore = "userStore";

export default {
  name: "QnaDetail",
  data() {
    const contentId = Number(this.$route.params.contentId);
    console.log(contentId);
    return {
      contentId,
    };
  },

  created() {
    console.log(this.$route.params);
    this.qnaDetail(this.contentId);
  },

  methods: {
    ...mapActions(qnaStore, ["qnaDetail", "qnaDelete"]),
    updateData() {
      this.$router.push({
        path: `/qna/create/${this.contentId}`,
      });
    },
    toList() {
      this.$router.push({
        path: `/qna`,
      });
    },
  },
  components: {
    QnaCommentList,
    QnaCommentCreate,
  },
  computed: {
    ...mapState(qnaStore, ["qna"]),
    ...mapState(userStore, ["userInfo"]),
    id() {
      return this.userInfo.id;
    },
  },
};
</script>
<style></style>
