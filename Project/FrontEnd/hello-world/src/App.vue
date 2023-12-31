<template>
  <div>
    <!-- 其他组件内容 -->

    <!-- 弹窗按钮 -->
    <button @click="showDialog = true">打开弹窗</button>

    <!-- 弹窗 -->
    <div v-if="showDialog" class="dialog">
      <!-- 弹窗内容 -->
      <h2>弹窗标题</h2>
      <p>弹窗内容</p>

      <!-- 关闭弹窗按钮 -->
      <button @click="showDialog = false">关闭弹窗</button>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  setup() {
    const showDialog = ref(false);

    return {
      showDialog,
    };
  },
};
</script>

<style scoped>
.dialog {
  /* 弹窗样式 */
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
}
</style>
