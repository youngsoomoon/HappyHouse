<template>
  <b-container class="bv-example-row" fluid="sm">
    <b-card border-variant="secondary">
      <b-row>
        <b-col class="p-3 mb-3 bg-light text-secondary"
          ><b>등록 번호: </b> {{ freeboard.freeboardNo }}</b-col
        >
        <b-col class="p-3 mb-3 bg-light text-secondary"
          ><b>글 제목: </b> {{ freeboard.title }}</b-col
        >
        <b-col>
          <b-row class="p-3 mb-3 bg-light text-secondary"
            ><b>글쓴이: </b> {{ freeboard.id }}</b-row
          >
        </b-col>
        <b-col>
          <b-row class="p-3 mb-3 bg-light text-secondary">
            <b>등록일: </b> {{ freeboard.regtime }}</b-row
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
            :value="freeboard.content"
          >
          </b-form-textarea>
        </b-col>
      </b-row>

      <b-button-group class="mt-3" v-if="freeboard.id === id || id === 'admin'">
        <b-button variant="primary" @click="updateData">수정</b-button>&nbsp;
        <b-button variant="success" @click="freeboardDelete(contentId)"
          >삭제</b-button
        >&nbsp;
      </b-button-group>
      <b-button class="mt-3" variant="success" @click="toList"
        >목록으로</b-button
      >
    </b-card>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const freeboardStore = "freeboardStore";
const userStore = "userStore";
export default {
  name: "FreeBoardDetail",
  data() {
    const contentId = Number(this.$route.params.contentId);
    console.log(contentId);
    return {
      contentId,
    };
  },

  created() {
    console.log(this.$route.params);
    this.freeboardDetail(this.contentId);
  },

  methods: {
    ...mapActions(freeboardStore, ["freeboardDetail", "freeboardDelete"]),
    updateData() {
      this.$router.push({
        path: `/freeboard/create/${this.contentId}`,
      });
    },
    toList() {
      this.$router.push({
        path: `/freeboard`,
      });
    },
  },
  components: {},
  computed: {
    ...mapState(freeboardStore, ["freeboard"]),
    ...mapState(userStore, ["userInfo"]),
    id() {
      return this.userInfo.id;
    },
  },
};
</script>
<style></style>
