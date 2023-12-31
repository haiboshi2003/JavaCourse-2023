<template>
  <el-card class="box-card" shadow="always">
    <template #header>
      <div class="card-header">
        <div class="firstrow">
          <span style="font-size: 14px">课程名称：{{ projectName }}</span>

          <span style="font-size: 14px">题目编号：{{ id }}</span>
        </div>
        <div class="secondrow">
          <span style="font-size: 14px">题目名称：{{ titleName }}</span>
        </div>
      </div>
    </template>
    <div class="description">
      <span>{{ description }}</span>
    </div>
    <template #footer>
      <div class="footer">
        <el-button @click="viewTitle">查看题目详情</el-button>
        <el-button @click="deleteTitle">删除题目</el-button>

        <!-- <el-button>导出题目</el-button> -->
      </div>
    </template>
  </el-card>
</template>
<script setup>
import axios from "@/api/request";
import { message } from "ant-design-vue";
import { ref, defineProps, defineEmits } from "vue";

const props = defineProps(["titleName", "id", "projectName", "description"]);
const emits = defineEmits(["clickView", "clickDelete", "clickChange"]);

// 删除题目
function deleteTitle() {
  axios
    .delete(`/title/delete/${props.id}`)
    .then((response) => {
      console.log("Delete operation response:", response.data);
      if (response.data.code == 200) {
        message.success("删除成功", 2);
        emits("clickDelete", true);
      } else message.error("删除失败", 2);

      // 在这里处理删除成功后的逻辑
    })
    .catch((error) => {
      console.error("Error during delete operation:", error);
      message.error("删除失败", 2);

      // 在这里处理请求失败的逻辑
    });
}
const viewTitle = () => {
  emits("clickView", { id: props.id, isShow: true });
};
const changeTitle = () => {
  emits("clickChange", true);
};
</script>
<style scoped>
.card-header {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  height: 50px;
  gap: 20px; /* 元素之间的间距为 10px */
}
.footer {
  display: flex;
  justify-content: center;
  flex-direction: row;
  align-items: center;
  gap: 10px; /* 元素之间的间距为 10px */
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 400px;
}

.firstrow {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 50px; /* 元素之间的间距为 10px */
}
.description {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
