<template></template>

<script setup>
///demo/public/demoJWT
import axios from "axios";
import { ref, onMounted } from "vue";

let count = ref(0);

async function getStudents() {
  try {
    const resp = await axios.get("/demo/public/demoJWT");
    count.value = resp.data;
    console.log(count.value);
  } catch (e) {
    console.log(e);
  }
}

onMounted(() => {
  getStudents();
});
</script>

<style></style>
