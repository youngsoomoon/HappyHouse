<template>
  <div class="home">
    <apt-search-bar></apt-search-bar>
    <map-component></map-component>
  </div>
</template>

<script>
import AptSearchBar from "@/components/apt/AptSearchBar.vue";
import MapComponent from "@/components/MapContent.vue";
export default {
  name: "HomeView",
  components: {
    AptSearchBar,
    MapComponent,
  },
};
</script>

<style></style>
