import { createApp } from 'vue'

import main from './components/main.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'


import antd from 'ant-design-vue'
import '../node_modules/ant-design-vue/dist/reset.css'

import router from './router/router'

// 导入组件
import dialog from './components/Tools/dialogTool.vue'
import card from "./components/Tools/ACard.vue"

createApp(main).use(router).use(antd).component('component-dialog', dialog).component('component-card', card).use(ElementPlus).mount('#app')
