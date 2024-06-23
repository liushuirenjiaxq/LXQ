<template>
	<view class="content">
		<view class="header">
			<!-- :isBack="false" 取消返回键位 -->
			<cu-custom bgColor="bg-gradual-blue" :isBack="false">
			    <block slot="content">备忘录</block>
			</cu-custom>
		</view>
		<view class="content-box" v-if="userId !== 0">
			<uni-card :isShadow="true" v-for="(item,index) in diary" 
			:key="index" :title="item.title"  mode="basic" :extra="item.time"
			@click="edit(item)" >
			<!-- 手机软件触发@touchstart="gtouchstart(index)" @touchend="cleartouchend()" @touchmove="gtouchmove()"> -->
				<view>
					<text style="font-size: 12px;">{{item.text}}</text>
					<u-icon @click="del(item.id)" class="icon-del" name="trash" color="#2979ff" size="12"></u-icon>
				</view>
			</uni-card>
		</view>
		<view class="none" v-else>
			<!-- 居中样式 -->
			<u-loadmore status="nomore" nomore-text="——已加载全部——" />
		</view>
		<uni-fab ref="fab" :pattern="pattern" horizontal="right" vertical="bottom" @fabClick="fabClick" />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				pattern: {
					color: 'gray',
					backgroundColor: '#FFFFFF',
					buttonColor:'orange'
				},
				lastTapDiffTime: 0,
				lastTapTimeoutFunc: null,
			}
		},
		methods: {
			fabClick() {
				if (!this.userId) {
					uni.showToast({
						title: '请先登录',
						icon: 'none'
					});
					return;
				}
				//跳转添加界面
				uni.navigateTo({
					url:'/pages/active/add?id=0'
				});
			},
			//双击事件
			async edit(obj){	
				const _this = this;
				const curTime = new Date().getTime();
				const lastTime = _this.lastTapDiffTime;
				_this.lastTapDiffTime = curTime;
				const diff = curTime - lastTime;
				if (diff < 300) {
					// 成功触发双击事件时，取消单击事件的执行
					clearTimeout(_this.lastTapTimeoutFunc);
					uni.navigateTo({
						//只能单个单个数据传送 onLoad获取一个对象
						url:'/pages/active/add?id='+obj.id+'&text='+encodeURIComponent(obj.text)+'&title='+encodeURIComponent(obj.title)+''
					});
				} 
			},
			del(index){
				this.$store.dispatch('delDiaryById',index)
				console.log(index)
			}
			/* //手机软件触发
			gtouchstart(index) {
				console.log(1)
			  // 设置定时器，500毫秒触发长按事件
			  this.time = setTimeout(() => {
				this.showxx(index) // 在定时器中要做的事 调用
			  }, 500)
			  return false
			},
			// 触摸屏幕离开
			cleartouchend() {
				console.log(2)
			  //  如果在500秒内释放就取消长按事件，清除定时器
			  clearTimeout(this.time)
			  // if(this.item!=0) return false
			},
			// 手指在屏幕上移动
			gtouchmove() {
				console.log(3)
			  // 如果手指移动就 清除定时器
			  clearTimeout(this.item)
			  // this.item=0
			},
			showxx(index){
				console.log("触发成功")
			} */
		},
		computed: {
			userId() {
				return this.$store.state.userInfo.id;
			},
			diary() {
				if(this.userId !== 0){
					return this.$store.state.diary;
				}
			}                                                                                                                                  
		}
	}
</script>

<style lang="scss" scoped>
	//设置整个page的样式
	page {
		background-color: #FFE4B5;
	}
	.content-box{
		display: flex;
		flex-wrap: wrap;
		justify-content: space-around;
		.uni-card{
			max-width: 400px;
			min-width: 300px;
			height: 100px;
			border-radius: 20px;
		}
	}
	.icon-del{
		position: absolute;
		bottom: 8px;
		right: 8px;
	}
</style>