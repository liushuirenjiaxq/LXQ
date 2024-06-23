<template>
	<view class="content">
		<view class="header">
			<cu-custom bgColor="bg-gradual-blue" :isBack="false">
			    <block slot="content">个人中心</block>
			</cu-custom>
		</view>
		<view class="content-box">
			<!-- 项目后期设置条件渲染 -->
			<uni-card v-if="userInfo.id === 0" :is-shadow="true" spacing="10px" type="line">
				<text class="text">尊贵的用户 请您先登录!</text>
				<span class="iconfont icon-jiantou hou"></span>
			</uni-card>
			<uni-card v-else :is-shadow="true" spacing="10px" type="line">
				<text class="text">{{userInfo.name}}</text>
				<span class="iconfont icon-jiantou hou"></span>
			</uni-card>
		
			<uni-card :is-shadow="true" spacing="5px" type="line">
				<span class="iconfont icon-shezhi"></span>
				<text class="text">设置</text>	
				<span class="iconfont icon-jiantou hou"></span>
			</uni-card>
			
			<u-button type="primary" @click="toLogin()">登录</u-button>
			<u-button type="success" @click="toRegister()">注册</u-button>
			<u-button type="warning" @click="exit()">退出登录</u-button>
		</view>
	</view>
</template>

<script>
	import {mapState} from 'vuex'
	export default {
		data() {
			return {
				
			}
		},
		computed: {
			...mapState({
				userInfo: 'userInfo'
			})
		},
		methods: {
			toLogin(){
				uni.navigateTo({
					url:'/pages/active/login'
				});
			},
			toRegister(){
				uni.navigateTo({
					url:'/pages/active/register'
				});
			},
			exit(){
				if(this.userInfo.id == 0){
					uni.showToast({
						title: '未登录',
						icon: 'error'
					});
					return;
				}
				//清除Token
				uni.removeStorageSync('Token');
				uni.switchTab({
					url:'/pages/index/index'
				});
				this.$store.commit('exit',null);
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import url("../../static/css/cashbook_add.css");
	page{
		background-color: white;
	}
	.content-box{
		.uni-card{
			.hou{
				float: right;
			}
		}
		.text{
			font-size: 16px;
			font-weight: bold;
		}
		.u-button{
			max-width: 98%;
			margin-bottom: 5px;
			margin-top: 5px;
		}
	}
</style>
