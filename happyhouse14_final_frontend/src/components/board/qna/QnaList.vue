<template>
  <div>
    <h2>QnA 리스트</h2>
    <b-table
      striped
      hover
      :items="qnas"
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
    <b-button @click="writeContent">글쓰기</b-button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const qnaStore = "qnaStore";
const userStore = "userStore";
export default {
  name: "QnaList",
  data() {
    return {
      currentPage: 1, // 현재 페이지
      perPage: 10, // 페이지당 보여줄 갯수
      // bootstrap 'b-table' 필드 설정
      fields: [
        "번호",
        {
          key: "id",
          label: "아이디",
        },
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
      this.qnaList();
    }
  },
  methods: {
    ...mapActions(qnaStore, ["qnaList"]),
    rowClick(item) {
      this.$router.push({
        path: `/qna/detail/${item.qnaNo}`,
      });
    },
    writeContent() {
      this.$router.push({
        path: `/qna/create`,
      });
    },
  },
  computed: {
    ...mapState(qnaStore, ["qnas"]),
    rows() {
      return this.qnas.length;
    },
    ...mapState(userStore, ["userInfo"]),
    id() {
      return this.userInfo.id;
    },
  },
};
</script>
