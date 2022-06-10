<template>
  <div>
    <h2>공지사항</h2>
    <b-table
      striped
      hover
      :items="notice"
      :per-page="perPage"
      :current-page="currentPage"
      :fields="fields"
      @row-clicked="rowClick"
    >
      <template #cell(번호)="data">
        {{ data.index + 1 + (currentPage - 1) * 10 }}
      </template>
    </b-table>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      align="center"
    ></b-pagination>
    <b-button v-if="id === 'admin'" @click="writeContent">글쓰기</b-button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const noticeStore = "noticeStore";
const userStore = "userStore";
export default {
  name: "NoticeList",
  data() {
    return {
      currentPage: 1, // 현재 페이지
      perPage: 10, // 페이지당 보여줄 갯수
      // bootstrap 'b-table' 필드 설정
      fields: [
        "번호",
        {
          key: "title",
          label: "제목",
        },
        {
          key: "hit",
          label: "조회수",
        },
        {
          key: "regtime",
          label: "작성날짜",
        },
      ],
    };
  },
  created() {
    if (!this.userInfo) {
      alert("로그인이 필요한 서비스 입니다.");
      this.$router.push({ name: "UserLogin" });
    } else {
      this.noticeList();
    }
  },
  methods: {
    ...mapActions(noticeStore, ["noticeList"]),
    rowClick(notice) {
      this.$router.push({
        path: `/notice/detail/${notice.noticeNo}`,
      });
    },
    writeContent() {
      this.$router.push({
        path: `/notice/create`,
      });
    },
  },
  computed: {
    ...mapState(noticeStore, ["notice"]),
    ...mapState(userStore, ["userInfo"]),
    id() {
      return this.userInfo.id;
    },
    rows() {
      return this.notice.length;
    },
  },
};
</script>
