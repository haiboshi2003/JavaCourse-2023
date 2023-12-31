<template>
  <div class="root">
    <div class="card">
      <component-card
        v-for="title in titleList"
        :key="title.id"
        :titleName="title.titleName"
        :id="title.id"
        :projectName="title.projectName"
        :description="title.description"
        @clickView="handleView"
        @clickDelete="handleDelete"
      />
    </div>
  </div>
  <div class="dialog">
    <component-dialog
      :key="selectedTitleId"
      :id="selectedTitleId"
      :url="downloadurl"
      v-if="showDialog"
      @ifShow="handleDialog"
    ></component-dialog>
  </div>
</template>

<script setup>
import { message } from "ant-design-vue";
import axios from "../api/request";
import { computed, onMounted, ref } from "vue";

const selectedTitleId = ref(null); // 用于存储选中的题目的 id
const showDialog = ref(false); // 控制对话框的显示和隐藏
const titleList = ref([]);
const downloadurl = computed(() => {
  console.log(
    `http://localhost:7078/data/${selectedTitleId.value}/${selectedTitleId.value}.docx`
  );
  return `http://localhost:7078/data/${selectedTitleId.value}/${selectedTitleId.value}.docx`;
});

const fetchTitles = () => {
  // Make a GET request to your backend API endpoint
  axios
    .get("/title/getAll")
    .then((response) => {
      // Set the titles data with the response data
      console.log(response.data);
      message.success("获取成功", 2);
      titleList.value = response.data.data;
      console.log(titleList.value);
      console.log(typeof titleList.value);
    })
    .catch((error) => {
      console.error("Error fetching titles:", error);
    });
};

onMounted(() => {
  // 这里是组件挂载后执行的代码
  fetchTitles();
});
const handleView = (data) => {
  selectedTitleId.value = data.id;
  showDialog.value = data.isShow;
};
const handleDelete = (data) => {
  // 处理查看题目详情的逻辑
  if (data) {
    axios
      .get("/title/getAll")
      .then((response) => {
        titleList.value = response.data.data;
      })
      .catch((error) => {
        console.error("Error fetching titles:", error);
      });
  }
};

const handleDialog = (data) => {
  showDialog.value = data;
};
</script>

<style scoped>
.root {
  padding: 20px; /* 上下左右内边距为 10px */
}
.card {
  display: flex;
  flex-wrap: wrap;

  gap: 20px; /* 内部元素之间的间距为 20px */
}
</style>
