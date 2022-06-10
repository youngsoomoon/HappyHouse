<template>
  <div id="app">
    <header-navi-bar></header-navi-bar>
    <router-view class="mx-auto w-75 mt-3" v-cloak />
  </div>
</template>

<script>
import HeaderNaviBar from "@/components/layout/HeaderNaviBar.vue";
export default {
  name: "App",
  components: {
    HeaderNaviBar,
  },
};
</script>

<style>
@font-face {
  font-family: "GmarketSansTTFBold";
  src: url("./assets/fonts/GmarketSansTTFBold.ttf") format("truetype");
  font-weight: bold;
}
@font-face {
  font-family: "GmarketSansTTFMedium";
  src: url("./assets/fonts/GmarketSansTTFMedium.ttf") format("truetype");
  font-weight: normal;
}
@font-face {
  font-family: "GmarketSansTTFLight";
  src: url("./assets/fonts/GmarketSansTTFLight.ttf") format("truetype");
  font-weight: lighter;
}

#app {
  font-family: GmarketSansTTFMedium, GmarketSansTTFLight, GmarketSansTTFBold;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

a {
  text-decoration: none;
  font-weight: bold;
}

a.router-link-exact-active {
  color: #42b983;
}

[v-cloak]::before {
  content: "로딩중...";
}
[v-cloak] > * {
  display: none;
}
</style>
