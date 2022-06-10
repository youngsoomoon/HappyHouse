<template>
  <div>
    <h3>
      {{ updateMode ? "질문 수정" : "질문 등록" }}
    </h3>
    <b-input v-model="title" placeholder="제목을 입력해주세요."></b-input>
    <b-form-textarea
      v-model="content"
      placeholder="내용을 입력해 주세요"
      rows="3"
      max-rows="6"
    ></b-form-textarea>
    <br />
    <b-button @click="updateMode ? updateContent() : uploadContent()"
      >저장</b-button
    >&nbsp;
    <b-button @click="cancle">취소</b-button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const qnaStore = "qnaStore";
export default {
  name: "QnaCreate",
  data() {
    return {
      no: "",
      title: "",
      content: "",
      updateObject: null,
      id: JSON.parse(sessionStorage.getItem("user-info")).id,
      updateMode: this.$route.params.contentId > 0 ? true : false,
    };
  },
  created() {
    if (this.$route.params.contentId > 0) {
      const contentId = Number(this.$route.params.contentId);
      this.updateObject = this.qna;
      this.no = contentId;
      this.title = this.updateObject.title;
      this.content = this.updateObject.content;
    }
  },
  computed: {
    ...mapState(qnaStore, ["qna"]),
  },
  methods: {
    ...mapActions(qnaStore, ["qnaPost", "qnaUpdate"]),
    uploadContent() {
      // 저장
      this.qnaPost(this.$data);
      this.$router.push({
        name: "QnaList",
      });
    },
    updateContent() {
      // 수정
      this.updateObject.title = this.title;
      this.updateObject.content = this.content;
      this.qnaUpdate(this.updateObject);
      this.$router.push({
        name: "QnaDetail",
      });
    },
    cancle() {
      this.$router.push({
        name: "QnaList",
      });
    },
  },
};
</script>

<style></style>
