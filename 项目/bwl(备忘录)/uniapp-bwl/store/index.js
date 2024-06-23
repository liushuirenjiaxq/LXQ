//引用Vuex
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

//实例store对象
const store = new Vuex.Store({
	state: {
		userInfo: {
			id: 0,
			name: "",
			password: "",
			tele: ""
		},
		diary: [
			{
				id: 0,
				ownId: 0,
				text: "",
				time: "",
				title: ""
			}
		],
		daiban: [
			{
				id: 0,
				ownId: 0,
				text: "",
				state: 0
			}
		]
	},
	//更新数据
	mutations: {
		addDiary(state,value){
			state.diary.push(value);
		},
		addDaiban(state,value){
			state.daiban.push(value);
		},
		updataUserInfo(state,value){
			state.userInfo = value;
		},
		updataUserDiary(state,value){
			state.diary = value;
			//console.log(state.diary)
		},
		updataUserDaiban(state,value){
			state.daiban = value;
		},
		updataDaiBanState(state,value){
			state.daiban[value[0]].state = value[1];
		},
		exit(state,value){
			//退出登录 清除数据
			state.userInfo.id = 0;
		},
		delDiaryById(state,value){
			let arr = state.diary;
			for(let i=0;i<arr.length;i++){
				if (arr[i].id === value-'0') {
					arr.splice(i, 1);		  
				}
			}
			//触发响应式
			arr[0].ownId = state.userInfo.id;
		},
		deldaibanById(state,value){
			let arr = state.daiban;
			for(let i=0;i<arr.length;i++){
				if (arr[i].id === value-'0') {
					arr.splice(i, 1);		  
				}
			}
			//触发响应式
			arr[0].ownId = state.userInfo.id;
		}
	},
	//处理事务
	actions: {
		deldaibanById(context,value){
			uni.request({
					url: Vue.prototype.$api + '/backlog/del/' + value,
					method: 'get',
					dataType: 'json',
					header: {
						"Content-Type": "application/x-www-form-urlencoded",
						"Token": uni.getStorageSync('Token')
					},
					success: (res) => {
						if (res.data.code === 200) {
							context.commit('deldaibanById',value);
							//console.log(res.data.data)
						}else{
							console.log(res)
							console.log("请求错误")
						}
					},
				fail: (err) => {
					uni.showToast({
						title: '网络、接口出现错误',
						icon: 'none'
					});
				}
			})
		},
		editDaiban(context,value){
			console.log(value)
			uni.request({
					url: Vue.prototype.$api + '/backlog/uptext',
					method: 'post',
					dataType: 'json',
					header: {
						"Content-Type": "application/json",
						"Token": uni.getStorageSync('Token')
					},
					data: JSON.stringify({
						  "id": value.id,
						  "text": value.text
					}),
					success: (res) => {
						if (res.data.code === 200) {
							//context.commit('updataDaiBanState',[value,temp]);
							console.log("修改成功")
						}else{
							console.log(res)
							console.log("请求错误")
						}
					},
				fail: (err) => {
					uni.showToast({
						title: '网络、接口出现错误',
						icon: 'none'
					});
				}
			})
		},
		delDiaryById(context,value){
			uni.request({
					url: Vue.prototype.$api + '/content/del/' + value,
					method: 'get',
					dataType: 'json',
					header: {
						"Content-Type": "application/x-www-form-urlencoded",
						"Token": uni.getStorageSync('Token')
					},
					success: (res) => {
						if (res.data.code === 200) {
							context.commit('delDiaryById',value);
							//console.log(res.data.data)
						}else{
							console.log(res)
							console.log("请求错误")
						}
					},
				fail: (err) => {
					uni.showToast({
						title: '网络、接口出现错误',
						icon: 'none'
					});
				}
			})
		},
		updateDiary(context,value){
			//value中的数据id为字符串
			let arr = context.state.diary;
			for (let item of arr) {
			  if (item.id === value.id-'0') {
				  item.text = value.text;
				  item.title = value.title;
				  item.time = value.time;
				  break;
			  }
			}
		},
		addDaiban(context,value){
			uni.request({
				//vuex无法使用this   Vue.prototype.$api方式使用全局属性
					url: Vue.prototype.$api + '/backlog/in',
					method: 'post',
					dataType: 'json',
					data: JSON.stringify(value),					
					header: {
						"Content-Type": "application/json",
						"Token": uni.getStorageSync('Token')
					},
					success: (res) => {
						if (res.data.code === 200) {
							//console.log(res.data.data)
							value.id = res.data.data;
							context.commit('addDaiban',value);
						}else{
							console.log(res)
							console.log("请求错误")
						}
					},
				fail: (err) => {
					//console.log(err,value)
					uni.showToast({
						title: '网络、接口出现错误',
						icon: 'none'
					});
				}
			})
		},
		updataDaiBanState(context,value){
			let temp;
			if(context.state.daiban[value].state === 1){
				temp = 0;
			}else{
				temp = 1;
			}
			uni.request({
				//vuex无法使用this   Vue.prototype.$api方式使用全局属性
					url: Vue.prototype.$api + '/backlog/upstate',
					method: 'post',
					dataType: 'json',
					header: {
						"Content-Type": "application/json",
						"Token": uni.getStorageSync('Token')
					},
					data: JSON.stringify({
						  "id": context.state.daiban[value].id,
						  "state": temp
					}),
					success: (res) => {
						if (res.data.code === 200) {
							context.commit('updataDaiBanState',[value,temp]);
						}else{
							console.log(res)
							console.log("请求错误")
						}
					},
				fail: (err) => {
					uni.showToast({
						title: '网络、接口出现错误',
						icon: 'none'
					});
				}
			})
		},
		updataUserDaiban(context,value){
			uni.request({
				//vuex无法使用this   Vue.prototype.$api方式使用全局属性
					url: Vue.prototype.$api + '/backlog/' + value.data.id,
					method: 'get',
					dataType: 'json',
					header: {
						"Content-Type": "application/x-www-form-urlencoded",
						"Token": uni.getStorageSync('Token')
					},
					success: (res) => {
						if (res.data.code === 200) {
							context.commit('updataUserDaiban',res.data.data);
							//console.log(res.data.data)
						}else{
							console.log(res)
							console.log("请求错误")
						}
					},
				fail: (err) => {
					uni.showToast({
						title: '网络、接口出现错误',
						icon: 'none'
					});
				}
			})
		},
		updataUserDiary(context,value){
			uni.request({
				//vuex无法使用this   Vue.prototype.$api方式使用全局属性
					url: Vue.prototype.$api + '/content/' + value.data.id,
					method: 'get',
					dataType: 'json',
					header: {
						"Content-Type": "application/x-www-form-urlencoded",
						"Token": value.msg
					},
					success: (res) => {
						if (res.data.code === 200) {
							//console.log(res.data.data)
							context.commit('updataUserDiary',res.data.data);
						}else{
							console.log(res)
							console.log("请求错误")
						}
					},
				fail: (err) => {
					//console.log(err,value)
					uni.showToast({
						title: '网络、接口出现错误',
						icon: 'none'
					});
				}
			})
		}
	}

})

//导出store对象
export default store