<template>
  <div>
    <h2>유저 리스트</h2>
    <b-table
      striped
      hover
      :items="users"
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
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const userStore = "userStore";
export default {
  name: "UserList",
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
          key: "pwd",
          label: "패스워드",
        },
        {
          key: "name",
          label: "이름",
        },
        {
          key: "birthDate",
          label: "생년월일",
        },
        {
          key: "address",
          label: "주소",
        },
      ],
    };
  },
  created() {
    this.userList();
  },
  methods: {
    ...mapActions(userStore, ["userList"]),
    rowClick(item) {
      this.$router.push({
        path: `/usermanagement/detail/${item.id}`,
      });
    },
  },
  computed: {
    ...mapState(userStore, ["users"]),
    rows() {
      return this.users.length;
    },
  },
};
</script>
