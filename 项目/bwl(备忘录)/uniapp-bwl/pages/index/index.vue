<template>
	<view class="content">
		<view class="header">
			<cu-custom bgColor="bg-gradual-blue" :isBack="false">
			    <block slot="content">待办</block>
			</cu-custom>
		</view>
		<!-- id: "",
		ownId: "",
		text: "",
		state: "" -->
		<view class="content-box" v-if="userId !== 0">
			<uni-card :isShadow="true" v-for="(item,index) in daiban" :key="index" mode="basic" @click="edit(index)">
				<view class="card-view">
					<label class="radio"><radio class="card-radio" :checked="item.state === 1" @click="changeState(index)" /></label>
					<text class="card-text" style="font-size: 12px;">{{item.text}}</text>
					<u-icon @click="del(item.id)" class="icon-del" name="trash" color="#2979ff" size="12"></u-icon>
				</view>
			</uni-card>
		</view>
		<view class="none" v-else>
			<!-- 居中样式 -->
			<u-loadmore status="nomore" nomore-text="——已加载全部——" />
		</view>
		<uni-fab ref="fab" :pattern="pattern" horizontal="right" vertical="bottom" @fabClick="open()" />
		
		<!-- 弹出层 -->
		<u-popup :round="10" :show="show"
			@close="close()" mode="bottom" 
			 :overlay="true" :closeable="true"
			 close-icon-pos="top-right"
		>
			<!-- u-popup__content__close大小自适应 -->
			<u-icon @click="addDb()" class="u-popup__content__close icon-dui" name="checkmark" color="#909399" size="24px"></u-icon>
			<view style="height: 200px;">
				<view class="font" v-if="state">新建待办</view>
				<view class="font" v-else>编辑待办</view>
				<u-textarea class="pup-input" v-model="daibanInfo.text" placeholder="添加事项..." :confirmType="'done'" />
			</view>
		</u-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				state: true,
				show: false,
				pattern: {
					color: 'gray',
					backgroundColor: '#FFFFFF',
					buttonColor:'orange'
				},
				daibanInfo: {
					id: 0,
					ownId: 0,
					text: "",
					state: 0
				}
			}
		},
		onLoad() {
			
		},
		methods: {
			del(index){
				this.$store.dispatch('deldaibanById',index)
				console.log(index)
			},
			async edit(index){	
				const _this = this;
				const curTime = new Date().getTime();
				const lastTime = _this.lastTapDiffTime;
				_this.lastTapDiffTime = curTime;
				const diff = curTime - lastTime;
				if (diff < 300) {
					// 成功触发双击事件时，取消单击事件的执行
					clearTimeout(_this.lastTapTimeoutFunc);
					this.show = true;
					this.state = false;
					this.daibanInfo = this.daiban[index];
				} 
			},
			fabClick(){
				uni.navigateTo({
					url:'/pages/active/add_db'
				});
			},
			/* 编辑待办的state */
			changeState(index){
				this.$store.dispatch('updataDaiBanState',index)
			},
			open() {
				if (this.userId === 0) {
					uni.showToast({
						title: '请先登录',
						icon: 'none'
					});
					return;
				}
				this.show = true;
				this.state = true;
			},
			close(){
				this.show = false;
			},
			addDb(){
				if(this.daibanInfo.text == ""){
					uni.showToast({
						title: '内容为空',
						icon: 'none'
					});
					return;
				}
				this.close();
				this.daibanInfo.ownId = this.userId;
				let obj = new Object();
				Object.assign(obj, this.daibanInfo);
				if(this.state === false){
					this.$store.dispatch('editDaiban',obj);
				}else{
					this.$store.dispatch('addDaiban',obj);
					this.daibanInfo.text = '';
				}
			}
		},
		computed:{
			/* ...mapState({
				daiban: 'daiban'
			}), */
			userId() {
				return this.$store.state.userInfo.id;
			},
			daiban() {
				if(this.userId !== 0){
					return this.$store.state.daiban;
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		background-color: #FFE4B5;
	}
	
	.icon-del{
		position: absolute;
		bottom: 8px;
		right: 8px;
	}
	
	.pup-input {
		background-color: white;
		margin: 0px 5px;
		margin-top: 8px;
		height: 60px;
		border-radius: 10px;
	}
	
	.font{
		text-align: center;
		margin: 12px 0px;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 20px;
		vertical-align: middle;
		font-weight: normal
	}
	
	.icon-dui{
		position: absolute;
		top: 10px;
		left: 10px;
		
	}
	
	.content-box{
		display: flex;
		flex-wrap: wrap;
		justify-content: space-around;
		.uni-card{
			max-width: 400px;
			min-width: 300px;
			height: 50px;
			border-radius: 20px;
			.card-view{
				display: flex;
				justify-content: center;
				align-items: center;
				.card-radio{
					flex: 1;
				}
				.card-text{
					flex: 12;
					padding-left: 4px;
				}
			}
		}
	}
</style>
