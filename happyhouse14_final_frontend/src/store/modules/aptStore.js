import http from "@/api/http";

const aptStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도" }],
    guguns: [{ value: null, text: "구/군" }],
    dongs: [{ value: null, text: "동" }],
    apts: [],
    apt: null,
    dongMode: false,
  },
  getters: {},
  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_APT_LIST: (state, apts) => {
      apts.forEach((apt) => {
        state.apts.push(apt);
      });
      console.log("여기에 걸리나");
      state.apts.sort((a, b) => {
        return a.aptCode - b.aptCode;
      });
    },
    SET_APT_INFO: (state, apt) => {
      for (const el of state.apts) {
        if (apt.aptCode === el.aptCode) {
          apt = Object.assign(apt, el);
          break;
        }
      }
      state.apt = apt;
      // state.apt.sort((a, b) => {
      //   return Number(a.dealAmount) - Number(b.dealAmount);
      // });
    },
    SET_DONG_MODE: (state, payload) => {
      state.dongMode = payload;
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동" }];
    },
    CLEAR_APT_LIST: (state) => {
      state.apts = [];
    },
    CLEAR_APT_INFO: (state) => {
      state.apt = null;
    },
  },
  actions: {
    getSido({ commit }) {
      http.get(`/sido`).then((res) => {
        console.log(res);
        commit("SET_SIDO_LIST", res.data);
      });
    },
    getGugun({ commit }, sidoCode) {
      commit("CLEAR_GUGUN_LIST");
      http.get(`/gugun`, { params: { sido: sidoCode } }).then((res) => {
        commit("SET_GUGUN_LIST", res.data);
      });
    },
    getDong({ commit }, gugunCode) {
      commit("CLEAR_DONG_LIST");
      http.get(`/dong`, { params: { gugun: gugunCode } }).then((res) => {
        console.log("동리스트", res);
        commit("SET_DONG_LIST", res.data);
      });
    },
    getAptList({ commit }, dongCode) {
      commit("CLEAR_APT_LIST");
      http.get(`/apt`, { params: { dong: dongCode } }).then((res) => {
        console.log("아파트 리스트", res);
        commit("SET_DONG_MODE", false);
        commit("SET_APT_LIST", res.data);
        commit("CLEAR_GUGUN_LIST");
        commit("CLEAR_DONG_LIST");
      });
    },
    getAptListByName({ commit }, aptName) {
      commit("CLEAR_APT_LIST");
      console.log(aptName);
      http.get(`/apt/${aptName}`).then((res) => {
        console.log("검색 리스트", res);
        commit("SET_DONG_MODE", true);
        commit("SET_APT_LIST", res.data);
      });
    },
    getAptInfo({ commit }, aptCode) {
      commit("CLEAR_APT_INFO");
      http.get(`/aptList/detail/${aptCode}`).then((res) => {
        console.log("아파트 거래 정보", res);
        commit("SET_APT_INFO", res.data);
      });
    },
  },
};
export default aptStore;
