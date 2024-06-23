tabBar中使用iconfont-阿里巴巴矢量库：
"tabBar": {
"color": "#7A7E83",
"selectedColor": "#3cc51f",
"borderStyle": "black",
"backgroundColor": "#ffffff",
"iconfontSrc": "static/fonts/iconfont.ttf",// 引入字体图标库
"list": [{
"pagePath": "pages/index/index",
"text": "首页",
"iconfont": { 
"text": "\ue602", // &#xe602; 这是图标原始的编码
"selectedText": "\ue602", //&#xe602;
"fontSize": "20px",  // 图标大小
"color": "#333", // 未选中时，图标的颜色
"selectedColor": "red" // 选中时，图标的颜色
}}]}

colorUi的使用：vue2和vue3有所不同（main.js文件、App.vue文件都有不同）
//vue2 注册为全局组件---Vue.component('cu-custom',cuCustom)
//vue3 使用app.component 注册为全局组件---app.component('cu-custom',cuCustom)
//不要使用Vue3(很多vue3不可用) 使用Vue2(轻松)

API跳转:
uni.navigateTo(OBJECT):保留当前页面，跳转到应用内的某个页面，使用uni.navigateBack可以返回到原页面。
uni.redirectTo(OBJECT):关闭当前页面，跳转到应用内的某个页面。
uni.reLaunch(OBJECT):关闭所有页面，打开到应用内的某个页面
uni.switchTab(OBJECT):跳转到 tabBar 页面，并关闭其他所有非 tabBar 页面。
uni.navigateBack(OBJECT):关闭当前页面，返回上一页面或多级页面。可通过 getCurrentPages() 获取当前的页面栈，决定需要返回几层。
发起请求 uni.request(OBJECT)
上传和下载 uni.uploadFile(OBJECT)

vue界面中使用vuex中的数据,通过mapState直接将store中的数据映射到计算属性中,界面中直接使用。
使用方式:
	import {mapState} from 'vuex'
	computed: {
		...mapState({
			userInfo: 'userInfo'
		})
	}

注意：	
	1、vuex无法使用this   Vue.prototype.$api方式使用全局属性
	2、cashbook中的uview-ui是修改之后的文档和官方的文档(uview)不同——uview-ui文件看官方文档没有用，直接看文档中的vue文件
	
前端中的深浅拷贝:
	1、浅拷贝为普通赋值
	2、当推送到store中的数据还是与你的数据绑定着，则需要使用深拷贝
		对象深拷贝：let newObj = new Object();Object.assign(newObj, obj);
					let newObj = JSON.parse(JSON.stringify(obj))
		数组深拷贝：let arr = [1, 2, 3, 4];
					let arrx = arr.concat();
					let arry = arr.slice(1);