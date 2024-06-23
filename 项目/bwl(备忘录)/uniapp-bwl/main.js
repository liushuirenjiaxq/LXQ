import App from './App'

//引入cuCustom组件
import cuCustom from './colorui/components/cu-custom.vue'
Vue.component('cu-custom',cuCustom)

//使用uview-ui插件
import uView from "uview-ui";
Vue.use(uView);

//引入图标css
import './static/css/iconfont.css'

import store from './store/index'
//全局属性
Vue.prototype.$api = "http://localhost:8099/api"
Vue.prototype.$store = store

// #ifndef VUE3
import Vue from 'vue'
import './uni.promisify.adaptor'
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
  ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif