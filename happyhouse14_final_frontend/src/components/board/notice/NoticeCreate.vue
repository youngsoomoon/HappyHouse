<template>
  <div>
    <h3>
      {{ updateMode ? "공지 수정" : "공지 등록" }}
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
const noticeStore = "noticeStore";
export default {
  name: "NoticeCreate",
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
      this.updateObject = this.notice;
      this.no = contentId;
      this.title = this.updateObject.title;
      this.content = this.updateObject.content;
    }
  },
  computed: {
    ...mapState(noticeStore, ["notice"]),
  },
  methods: {
    ...mapActions(noticeStore, ["noticePost", "noticeUpdate"]),

    uploadContent() {
      // 저장
      this.noticePost(this.$data);

      this.$router.push({
        name: "NoticeList",
      });
    },
    updateContent() {
      // 수정
      this.updateObject.title = this.title;
      this.updateObject.content = this.content;
      this.noticeUpdate(this.updateObject);
      this.$router.push({
        name: "NoticeDetail",
      });
    },
    cancle() {
      this.$router.push({
        name: "NoticeList",
      });
    },
  },
};
</script>

<style></style>
