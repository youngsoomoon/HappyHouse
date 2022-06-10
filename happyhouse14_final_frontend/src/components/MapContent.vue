<template>
  <b-container class="d-flex mt-3 mb-3 map-area">
    <b-container fluid id="map"></b-container>
    <b-list-group
      v-if="showList"
      class="apt-list-side overflow-auto"
      style="cursor: pointer"
    >
      <h3 v-if="!dongMode">
        {{ apts[0].dongName }}
        <b-button v-if="userInfo" @click="addInterest">관심 지역 추가</b-button>
      </h3>
      <b-list-group-item
        class="flex-column align-items-start"
        v-for="(apt, index) in apts"
        :key="index"
        @click="setAptInfo({ lat: apt.lat, lng: apt.lng })"
      >
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-1">{{ apt.aptName }}</h5>
        </div>
      </b-list-group-item>
    </b-list-group>
    <b-list-group v-else-if="showAptInfo" class="apt-list-side overflow-auto">
      <h5>{{ aptDetail.aptName.trim() }}</h5>
      <b-card v-for="(el, index) in apt" :key="index">
        <b-card-text>
          <div>
            준공 연도 : {{ el.buildYear }} | 거래날짜 :
            {{ el.dealYear.trim() }}. {{ el.dealMonth.trim() }}.
            {{ el.dealDay.trim() }}.
          </div>
          {{ el.floor.trim() }}층 | 거래가 : {{ el.dealAmount.trim() }} | 면적 :
          {{ el.area.trim() }}
        </b-card-text>
      </b-card>
      <b-button @click="showSidebar" class="float-left w-25">&laquo;</b-button>
    </b-list-group>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
//출처 : https://codesandbox.io/s/nervous-keldysh-87yxg?file=/src/components/KakaoMap.vue:0-3718
const aptStore = "aptStore";
const userStore = "userStore";
export default {
  name: "KakaoMap",
  data() {
    return {
      markers: [],
      infowindow: null,
      showList: false,
      showAptInfo: false,
      aptDetail: null,
    };
  },
  created() {
    console.log(this.id);
    if (this.id) {
      this.$store.dispatch("userStore/getInterestAll", this.id);
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      const SERVICE_KEY = process.env.VUE_APP_KAKAO_MAP_API_KEY;
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${SERVICE_KEY}`;
      document.head.appendChild(script);
    }
    // select box 선택 완료시 mutation의 변화 감지
    this.$store.subscribe((mutation) => {
      // console.log(state, mutation);
      if (mutation.type === "aptStore/SET_APT_LIST") {
        if (this.apts.length === 0) {
          alert(
            "해당 이름을 가진 또는 해당 지역에는 아파트는 존재하지 않습니다."
          );
        } else {
          this.showSidebar();
        }
      }
    });
  },
  computed: {
    ...mapState(aptStore, ["apt", "apts", "dongMode"]),
    dongCode() {
      return this.apts[0].dongCode;
    },
    ...mapState(userStore, ["interests", "userInfo"]),
    id() {
      return this.userInfo.id;
    },
  },
  methods: {
    ...mapActions(aptStore, ["getAptInfo"]),
    ...mapActions(userStore, ["getInterestAll", "interestPost"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
      // 인포윈도우를 생성합니다
      this.infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable,
      });
    },
    changeSize() {
      const container = document.getElementById("map");
      container.style.width = `100%`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      console.log(positions);

      if (positions.length > 0) {
        this.markers = positions.map((position) => {
          const marker = new kakao.maps.Marker({
            map: this.map,
            position,
            clickable: true,
          });

          //마커 클릭 이벤트를 등록합니다.
          kakao.maps.event.addListener(marker, "click", () => {
            //마커 position을 출력합니다.
            console.log(marker, position);
            // this.displayInfoWindow(position);

            if (!this.showAptInfo)
              this.setAptInfo({ lat: position.Ma, lng: position.La });
          });

          return marker;
        });

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    showSidebar() {
      this.showList = true;
      this.showAptInfo = false;
      this.changeSize();
      this.displayMarker(this.apts.map((apt) => [apt.lat, apt.lng]));
    },
    showDetail() {
      this.showAptInfo = true;
      this.showList = false;
    },
    setAptInfo({ lat, lng }) {
      console.log(lat, lng);
      this.changeSize();
      this.displayMarker([[lat, lng]]);
      for (const apt of this.apts) {
        if (apt.lat == lat && apt.lng == lng) {
          this.aptDetail = apt;
          break;
        }
      }
      this.getAptInfo(this.aptDetail.aptCode);
      this.showDetail();
    },
    addInterest() {
      console.log(this.dongCode);
      this.interestPost({
        id: this.id,
        dongCode: this.dongCode,
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.map-area {
  height: 600px;
}
.apt-list-side {
  width: 60%;
}
</style>
