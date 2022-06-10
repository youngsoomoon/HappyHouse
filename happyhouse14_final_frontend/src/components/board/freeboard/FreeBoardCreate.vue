<template>
  <div>
    <h3>
      {{ updateMode ? "게시글 수정" : "게시글 등록" }}
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
const freeboardStore = "freeboardStore";
export default {
  name: "FreeBoardCreate",
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
      this.updateObject = this.freeboard;
      console.log("coid", contentId, this.freeboard);
      this.no = contentId;
      this.title = this.updateObject.title;
      this.content = this.updateObject.content;
    }
    console.log();
  },
  computed: {
    ...mapState(freeboardStore, ["freeboard"]),
  },
  methods: {
    ...mapActions(freeboardStore, ["freeboardPost", "freeboardUpdate"]),
    uploadContent() {
      // 저장

      console.log(this.$data);
      this.freeboardPost(this.$data);

      this.$router.push({
        name: "FreeBoardList",
      });
    },
    updateContent() {
      // 수정
      this.updateObject.title = this.title;
      this.updateObject.content = this.content;
      console.log("updateContent", this.updateObject);
      this.freeboardUpdate(this.updateObject);
      this.$router.push({
        name: "FreeBoardDetail",
      });
    },
    cancle() {
      this.$router.push({
        name: "FreeBoardList",
      });
    },
  },
};
</script>

<style></style>
