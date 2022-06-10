<template>
  <b-card
    overlay
    text-variant="title"
    title="보금자리 검색"
    class="main-title overflow-auto"
  >
    <b-row>
      <b-col cols="2">
        <b-form-select v-model="searchOpt" :options="options"></b-form-select>
      </b-col>
      <b-col>
        <b-row v-if="searchOpt === 'dong'">
          <b-col class="sm-3">
            <b-form-select
              v-model="sidoCode"
              :options="sidos"
              @change="gugunList"
            ></b-form-select>
          </b-col>
          <b-col class="sm-3">
            <b-form-select
              v-model="gugunCode"
              :options="guguns"
              @change="dongList"
            ></b-form-select>
          </b-col>
          <b-col class="sm-3">
            <b-form-select
              v-model="dongCode"
              :options="dongs"
              @change="aptList"
            ></b-form-select>
          </b-col>
        </b-row>
        <b-row v-else>
          <b-input-group class="mr-3">
            <b-form-input
              placeholder="아파트 이름으로 검색"
              v-model="keyword"
              @keypress.enter="getAptListByName(keyword)"
            ></b-form-input>
            <b-input-group-append>
              <b-button variant="success" @click="getAptListByName(keyword)"
                >검색</b-button
              >
            </b-input-group-append>
          </b-input-group>
        </b-row>
      </b-col>
    </b-row>
    <div class="d-flex flex-wrap">
      <div
        v-for="(interest, index) in interests"
        :key="index"
        class="mx-1 mt-2"
      >
        <b-button variant="primary" @click="aptList(interest.dongCode)">
          {{ interest.sidoName }} {{ interest.gugunName }}
          {{ interest.dongName }}
        </b-button>
        <b-badge
          pill
          variant="dark"
          @click="interestDelete({ id, dongCode: interest.dongCode })"
          >x</b-badge
        >
      </div>
    </div>
  </b-card>
</template>

<script>
import { mapActions, mapState } from "vuex";

const aptStore = "aptStore";
const userStore = "userStore";

export default {
  name: "AptSearchBar",
  components: {},
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      searchOpt: "dong",
      options: [
        { value: "dong", text: "동 검색" },
        { value: "apt", text: "아파트 검색" },
      ],
      keyword: "",
    };
  },
  created() {
    this.getSido();
  },
  computed: {
    ...mapState(aptStore, ["sidos", "guguns", "dongs", "apts"]),
    ...mapState(userStore, ["interests", "userInfo"]),
    id() {
      return this.userInfo.id;
    },
  },
  methods: {
    ...mapActions(aptStore, ["getSido", "getGugun", "getDong", "getAptList"]),
    ...mapActions(userStore, ["interestDelete"]),
    getAptListByName() {
      this.$store.dispatch("aptStore/getAptListByName", this.keyword);
      this.keyword = "";
    },
    gugunList() {
      console.log(this.sidoCode);
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    aptList(dongCode = null) {
      console.log(this.dongCode);
      if (dongCode) this.dongCode = dongCode;
      if (this.dongCode) this.getAptList(this.dongCode);
      this.sidoCode = null;
      this.gugunCode = null;
      this.dongCode = null;
    },
  },
};
</script>

<style scoped>
.main-title {
  /* background: url("../../assets/house-image.jpg") center no-repeat; */
  /* background-color: darkolivegreen; */
  background-size: cover;
  height: 200px;
}
.card-title {
  font-size: 2em;
  font-weight: bold;
  color: black;
  /* text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black; */
}
</style>
