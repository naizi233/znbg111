<template>
	<div class="signinDashboard">
		<div class="stitle" v-if="isSignin">
			你好，{{name}}！新的一天也要记得打卡哦！(●'◡'●)
	    </div>
		<div class="stitle" v-else>
			你好，{{name}}！离期待的生活又进一步哦！(●'◡'●)
		</div>
		<el-progress width="200" type="dashboard" :percentage="percentage" :color="colors"></el-progress>
		<div>
			<el-button-group>
				<el-button  @click="update" :type="showmode ? 'danger' : 'success'" style="width: 50px;height: 50px; margin-top: 50px;" icon="el-icon-check" circle></el-button>
			</el-button-group>
		</div>
	</div>
</template>

<script>
	export default {
		name: "EmpAdv",
		data() {
			return {
				percentage: 10,
				name:'',
				isSignin:true,
				showmode:true,
				colors: [{
						color: '#f56c6c',
						percentage: 20
					},
					{
						color: '#e6a23c',
						percentage: 40
					},
					{
						color: '#5cb87a',
						percentage: 60
					},
					{
						color: '#1989fa',
						percentage: 80
					},
					{
						color: '#6f7ad3',
						percentage: 100
					}
				]
			};
		},
		mounted(){
			this.init();
		},
		methods: {
			init(){
				this.getRequest("/signIn/admin").then((resp) =>{
					if(resp){
						this.percentage=resp.percentage;
						this.name=resp.name;
					}
				});
			},
			update(){
				this.putRequest("/signIn/update").then((resp) =>{
					if(resp){
						this.init();
						if(resp.obj=="1"){
						this.isSignin=!this.isSignin;
						this.showmode=!this.showmode;
						}
					}
				});
			}
		},
	}
</script>

<style>
	.signinDashboard {
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		width: 100%;
		height: 40%;
		padding: 2em 0;
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		align-items: center;
	}
	.stitle {
		height: 65px;
		width: 450px;
		font-size: 1.5em;
		font-weight: bold;
		color: #4b9eff;
		background-color: #ecf5ff ;
		text-align: center;
		margin-bottom: 50px;
		border-radius: 10px;
	}
</style>
