<template>
  <el-dialog
    :model-value="bo"
    draggable
    :close-on-click-modal="false"
    :show-close="false"
  >
    <div class="father">
      <div class="header">
        <div class="firstrow">
          <span style="font-size: 14px">题目编号：{{ title.id }}</span>
          <span style="font-size: 14px">课程名称：{{ title.projectName }}</span>
        </div>
      </div>
      <div class="secondrow">
        <span style="font-size: 14px">题目名称：{{ title.titleName }}</span>
      </div>
      <div class="description" v-if="title.description">
        <span>题目描述：{{ title.description }}</span>
      </div>
      <!-- 文件展示部分 -->
      <div class="file">
        <div v-if="title.textData">
          <span>题干：{{ title.textData }}</span>
        </div>

        <div v-if="title.imgPath">
          <p class="tip">图像展示：</p>
          <img
            :src="'http://localhost:7078' + title.imgPath"
            alt="Image"
            class="img-container"
          />
        </div>
        <div v-if="title.voicePath">
          <p class="tip">音频展示：</p>
          <audio controls>
            <source
              :src="'http://localhost:7078' + title.voicePath"
              type="audio/mp3"
            />
          </audio>
        </div>
        <div v-if="title.svgPath">
          <span class="tip">图形展示：</span>
          <!-- 使用 <object> 标签 -->
          <object
            :data="'http://localhost:7078' + title.svgPath"
            class="img-container"
            type="image/svg+xml"
          ></object>
        </div>
      </div>
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handledownload">下载</el-button>
        <el-button @click="handleExport">导出</el-button>
        <el-button type="primary" @click="handelConfirm"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import axios from "@/api/request";
import { message } from "ant-design-vue";
import { ref, defineProps, defineEmits, onMounted } from "vue";

const props = defineProps(["id", "url"]);
const emits = defineEmits(["ifShow"]);
const bo = ref(true);

const title = ref({
  id: "",
  titleName: "",
  projectName: "",
  description: "",

  textData: "",
  imgPath: "",
  svgPath: "",
  voicePath: "",
});

const handelConfirm = () => {
  emits("ifShow", false);
};
const handledownload = () => {
  window.location.href = props.url;
};
const handleExport = () => {
  console.log("http://localhost:7078/data/");

  // 发送请求到后端导出接口
  axios
    .post(`/title/exportWord/${props.id}`)
    .then((response) => {
      message.success("导出成功", 2);
    })
    .catch((error) => {
      console.error("Error exporting document:", error);
      // 处理错误
    });
  emits("ifShow", false);
};
onMounted(() => {
  axios
    .get(`/title/getById/${props.id}`)
    .then((response) => {
      const data = response.data.data;
      if (response.data.code == 200) {
        title.value.id = data.id;
        title.value.projectName = data.projectName;
        title.value.titleName = data.titleName;
        title.value.description = data.description;
        title.value.textData = data.textData;
        title.value.imgPath = data.imgPath;
        title.value.svgPath = data.svgPath;
        title.value.voicePath = data.voicePath;
      } else {
        emits("ifShow", false);
      }
    })
    .catch((error) => {
      console.error("Error fetching titles:", error);
    });
});
</script>

<style scoped>
.father {
  display: flex;
  padding: 10px; /* 上下左右内边距为 10px */
  flex-direction: column;

  gap: 15px; /* 元素之间的间距为 10px */
}

.file {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  gap: 10px; /* 元素之间的间距为 10px */
  justify-content: flex-start;
}

.img-container {
  max-width: 60%; /* 图像最大宽度为父容器宽度 */
  max-height: 25%; /* 图像最大高度为父容器高度 */
  display: block; /* 设置为块级元素，使max-width和max-height生效 */
  margin: auto; /* 居中显示 */
}
.firstrow {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  gap: 50px; /* 元素之间的间距为 10px */
}
.description {
  display: flex;
  flex-direction: column;
}
.secondrow {
  display: flex;
  flex-direction: column;
}
</style>
