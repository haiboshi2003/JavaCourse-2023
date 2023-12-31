<template>
  <div class="father">
    <el-tabs type="border-card" style="height: 600px">
      <el-tab-pane label="自定义导入">
        <div class="contain">
          <div class="section" id="basic">
            <!-- 题目编号 -->

            <el-input
              v-model="requestdata.id"
              class="w-50 m-2"
              size="large"
              style="width: 250px; height: 50px"
              placeholder="请输入题目编号"
            />
            <!-- 学科名称 -->
            <el-input
              v-model="requestdata.projectName"
              size="large"
              style="width: 250px; height: 50px"
              placeholder="请输入学科名称"
            />
            <!-- 题目名称 -->
            <el-input
              v-model="requestdata.titleName"
              size="large"
              style="width: 250px; height: 50px"
              placeholder="请输入题目名称"
            />
          </div>

          <div class="section" id="textdata">
            <!-- 文字题干 -->
            <el-input
              v-model="requestdata.textdata"
              :autosize="{ minRows: 4, maxRows: 4 }"
              type="textarea"
              style="width: 600px; height: 100px"
              placeholder="请输入题干"
            />
          </div>

          <div class="section" id="description">
            <!-- 题目额外信息 -->
            <el-input
              v-model="requestdata.description"
              :autosize="{ minRows: 4, maxRows: 4 }"
              type="textarea"
              style="width: 600px; height: 100px"
              placeholder="请输入题目额外的描述信息"
            />
          </div>
          <div class="section" id="file">
            <div class="filein">
              <label for="imgFileInput" class="custom-file-upload">
                选择JPG文件
              </label>
              <!-- 上传图像文件 -->
              <input
                type="file"
                id="imgFileInput"
                ref="imgFileInput"
                style="display: none"
                accept=".jpg"
                @change="handleImgFileChange"
              />
              <p class="selectedFileName">
                {{ requestdata.imgfile[0] ? requestdata.imgfile[0].name : "" }}
              </p>
            </div>

            <div class="filein">
              <label for="svgFileInput" class="custom-file-upload">
                选择SVG文件
              </label>
              <!-- 上传图形文件 -->
              <input
                type="file"
                id="svgFileInput"
                ref="svgFileInput"
                style="display: none"
                accept=".svg"
                @change="handleSvgFileChange"
              />
              <p class="selectedFileName">
                {{ requestdata.svgfile[0] ? requestdata.svgfile[0].name : "" }}
              </p>
            </div>
            <div class="filein">
              <label for="voiceFileInput" class="custom-file-upload">
                选择MP3文件
              </label>
              <!-- 上传语音文件 -->
              <input
                type="file"
                id="voiceFileInput"
                ref="voiceFileInput"
                @change="handleVoiceFileChange"
                style="display: none"
                accept=".mp3"
              />
              <p class="selectedFileName">
                {{
                  requestdata.voicefile[0] ? requestdata.voicefile[0].name : ""
                }}
              </p>
            </div>
          </div>
          <div class="section" id="mybutton">
            <button @click="sendToBackend" class="button">自定义导入</button>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="模板导入">
        <div class="contain">
          <!-- 题目编号 -->
          <el-input
            v-model="docxdata.id"
            size="large"
            style="width: 250px; height: 50px"
            placeholder="请输入题目编号"
          />
          <div class="filein">
            <!-- 上传docx文件 -->
            <label for="docxFileInput" class="custom-file-upload">
              选择DOCX文件
            </label>
            <input
              type="file"
              id="docxFileInput"
              ref="docxFileInput"
              @change="handleDocxFileChange"
              style="display: none"
              accept=".docx"
            />
            <p class="selectedFileName">
              {{ docxdata.docxFile[0] ? docxdata.docxFile[0].name : "" }}
            </p>
          </div>

          <button @click="docxsend" class="button">模板导入</button>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "@/api/request";
import { message } from "ant-design-vue";
import { notNullish } from "@vueuse/core";

const svgFileInput = ref(null); // 定义文件输入的引用
const imgFileInput = ref(null); // 定义文件输入的引用
const voiceFileInput = ref(null); // 定义文件输入的引用
const docxFileInput = ref(null); // 定义文件输入的引用

const requestdata = ref({
  id: "",
  titleName: "",
  projectName: "",
  description: "",

  textdata: "",
  imgfile: "",
  svgfile: "",
  voicefile: "",
});
const docxdata = ref({
  id: "",
  docxFile: "",
});

const handleImgFileChange = () => {
  requestdata.value.imgfile = Array.from(imgFileInput.value.files);
};

const handleSvgFileChange = () => {
  requestdata.value.svgfile = Array.from(svgFileInput.value.files);
};

const handleVoiceFileChange = () => {
  requestdata.value.voicefile = Array.from(voiceFileInput.value.files);
};
const handleDocxFileChange = () => {
  docxdata.value.docxFile = Array.from(docxFileInput.value.files);
};
const sendToBackend = () => {
  if (requestdata.value.id == null || requestdata.value.id == "") {
    message.error("题目编号不能为空", 2);
    return;
  } else {
    requestdata.value.id = parseInt(requestdata.value.id, 10);
  }
  if (!requestdata.value.titleName.trim()) {
    message.error("题目名称不能为空", 2);
    return;
  }
  if (!requestdata.value.projectName.trim()) {
    message.error("学科不能为空", 2);
    return;
  }

  // 构建FormData对象
  const formData = new FormData();

  formData.append("id", requestdata.value.id);
  formData.append("titleName", requestdata.value.titleName);
  formData.append("textData", requestdata.value.textdata);
  formData.append("projectName", requestdata.value.projectName);
  formData.append("description", requestdata.value.description);
  if (requestdata.value.imgfile[0]) {
    formData.append("imgFile", requestdata.value.imgfile[0]);
  }
  if (requestdata.value.svgfile[0]) {
    formData.append("svgFile", requestdata.value.svgfile[0]);
  }
  if (requestdata.value.voicefile[0]) {
    formData.append("voiceFile", requestdata.value.voicefile[0]);
  }

  console.log(requestdata.value.file);
  console.log(formData);
  // 发送POST请求
  axios
    .post("/title/add", formData)
    .then((response) => {
      console.log("success Response from backend:", response.data);
      if (response.data.success == "success") {
        message.success("添加题目成功", 2);
        requestdata.value.id = "";
        requestdata.value.description = "";
        requestdata.value.projectName = "";
        requestdata.value.titleName = "";
        requestdata.value.textdata = "";
        requestdata.value.imgfile = "";
        requestdata.value.svgfile = "";
        requestdata.value.voicefile = "";
      } else {
        message.error(response.data.message);
      }
    })
    .catch((error) => {
      console.error("Error sending data to backend:", error.message);
    });
};

const docxsend = () => {
  if (docxdata.value.id == notNullish) {
    message.error("题目编号不能为空", 2);
    return;
  } else {
    docxdata.value.id = parseInt(docxdata.value.id, 10);
  }
  // 构建FormData对象
  const formData = new FormData();

  formData.append("id", docxdata.value.id);
  if (docxdata.value.docxFile[0]) {
    formData.append("docxFile", docxdata.value.docxFile[0]);
  } else {
    message.error("docx文件不能为空");
    return;
  }
  // 发送POST请求
  axios
    .post("/title/addModel", formData)
    .then((response) => {
      console.log("success Response from backend:", response.data);
      if (response.data.success == "success") {
        message.success("添加题目成功", 2);
        docxdata.value.id = "";
        docxdata.value.docxFile = "";
      } else {
        message.error(response.data.message);
      }
    })
    .catch((error) => {
      console.error("Error sending data to backend:", error.message);
    });
};
</script>

<style scoped>
.father {
  padding: 10px; /* 上下左右内边距为 10px */
}

.contain {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  gap: 30px; /* 元素之间的间距为 10px */
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}
.section {
  display: flex;
  flex-direction: row; /* 垂直布局 */
  align-items: center; /* 水平居中 */
  gap: 20px; /* 内部元素之间的间距为 20px */
}
.filein {
  display: flex;
  flex-direction: column; /* 垂直布局 */
  align-items: center; /* 水平居中 */
  gap: 20px; /* 内部元素之间的间距为 20px */
}
.button {
  background-color: rgb(32, 229, 212);
  color: white;
  width: 100px;
  height: 50px;
  border: 0;
  font-size: 14px;
  border-radius: 30px;
  transition: background-color 0.3s ease; /* 添加过渡效果 */
  cursor: pointer; /* 将鼠标光标设为手型 */
  margin: 0 20px; /* 调整按钮之间的水平间隔 */
}

.button:active {
  background-color: #08ab9e; /* 按下时的背景色 */
}

.custom-file-upload {
  border: 1px solid rgb(32, 229, 212);
  border-radius: 30px;
  display: inline-block;
  padding: 6px 12px;
  cursor: pointer;
}

.custom-file-upload:hover {
  background-color: #08ab9e;
}
.selectedFileName {
  height: 5px;
}
</style>
