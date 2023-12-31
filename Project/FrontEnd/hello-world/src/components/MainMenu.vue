<template>
  <div class="container">
    <a-layout class="container2">
      <a-layout-header class="parent">
        <p class="p1">题库系统</p>
      </a-layout-header>
      <a-layout>
        <!-- 左侧菜单栏 -->
        <a-layout-sider>
          <a-menu theme="dark" mode="inline">
            <a-layout-sider>
              <a-menu theme="dark" mode="inline">
                <!-- 产品管理 -->
                <a-menu-item :key="1">
                  <router-link to="/menu/ProductionInquiry"
                    >题目浏览</router-link
                  >
                </a-menu-item>
                <a-menu-item :key="2">
                  <router-link to="/menu/addtitle">添加题目</router-link>
                </a-menu-item>
              </a-menu>
            </a-layout-sider>
          </a-menu>
        </a-layout-sider>

        <!-- 右侧容器 -->
        <a-layout-content>
          <div class="rightcontent">
            <router-view></router-view>
          </div>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </div>
</template>

<script setup lang="js"></script>

<style scoped>
.container {
  width: 100vw;
  /* height: 100vh; */
  min-height: 100vh;
}

.container2 {
  /* height: 100vh; */
  min-height: 100vh;
}
.rightcontent {
  height: 8000px;
}

.ant-layout-header {
  height: 50px;
  background-color: rgb(84, 194, 245);
}

/* .ant-layout-sider {
  background-color: lightsalmon;
} */

.ant-layout-content {
  background-color: aliceblue;
}

.ant-layout {
  height: 100%;
}

.parent {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  height: 10vh; /* 可根据实际情况调整高度 */
}

.p1 {
  font-size: 25px; /* 调整字体大小，可以根据需要修改数值 */
  font-family: Arial, sans-serif; /* 改变字体类型，可以根据需要修改字体名称 */
  margin-top: 25px; /* 设置距离父容器上方 10px 的距离 */
}
</style>
