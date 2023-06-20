<template>
	<div>
		<div style="margin-bottom: 10px;margin-left: 20px;">
			<el-tooltip class="item" effect="dark" content="点击更新" placement="bottom">
				<el-button @click="init" >总人数：{{zongNumber}}人</el-button>
			</el-tooltip>
			<el-tooltip class="item" effect="dark" content="点击更新" placement="bottom">
				<el-button @click="init" >本月新增：{{newPerson}}人</el-button>
			</el-tooltip>
			<el-tooltip class="item" effect="dark" content="点击更新" placement="bottom">
				<el-button @click="init" >本月辞职：{{oldPerson}}人</el-button>
			</el-tooltip>
			<el-tooltip class="item" effect="dark" content="点击更新" placement="bottom">
				<el-button @click="init" >本月休假：{{stopPerson}}人</el-button>
			</el-tooltip>
			<el-tooltip class="item" effect="dark" content="点击更新" placement="bottom">
				<el-button @click="init" >本月裁员：{{deletePerson}}人</el-button>
			</el-tooltip>
		</div>
		<el-row :gutter="20" style="margin-left: 20px;">
			<el-col :span="10">
				<div class="grid-content bg-purple">
					<div style="margin-bottom: 80px;">
						性别：</br></br>
						<el-tag>男性：{{mannumber}}人</el-tag>
						<el-progress :percentage="manNumber"></el-progress>
						<el-tag>女性：{{womannumber}}人</el-tag>
						<el-progress :percentage="womanNumber" color="#F56C6C"></el-progress>
					</div>
					<div id="myChart1" style="height: 400px;"></div>
				</div>
			</el-col>
			<el-col :span="13">
				<div class="grid-content bg-purple">
					<div style="margin-bottom: 40px;">
						年龄：</br></br>
						<el-tag>18-25岁：{{sage}}人</el-tag>
						<el-progress :percentage="sAge" color="#00ffb7"></el-progress>
						<el-tag>25-35岁：{{mage}}人</el-tag>
						<el-progress :percentage="mAge" color="#0dff01"></el-progress>
						<el-tag>35岁：{{lage}}人</el-tag>
						<el-progress :percentage="lAge" color="#88ff00"></el-progress>
					</div>
					<div id="myChart2" style="height: 400px;"></div>
				</div>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	export default {
		name: "StaPers",
		data() {
			return {
				zongNumber: "",
				manNumber: "",
				womanNumber: "",
				mannumber: "",
				womannumber: "",
				sAge: "",
				sage: "",
				mAge: "",
				sage: "",
				lAge: "",
				lage: "",
				statisticsNumber:[],
				newPerson:"",
				oldPerson:"",
				deletePerson:"",
				stopPerson:""
			}
		},
		mounted() {
			this.init();
			this.initChart1();
			this.initChart2();
		},
		methods: {
			initChart1() {
				let myChart1 = this.$echarts.init(document.getElementById("myChart1"));
				this.getRequest("/statistics/getTiptopDegreeNumbers").then((resp) => {
					if(resp){
						this.dataset=resp;
					}
					myChart1.setOption({
						title: {
							text: '学历分布',
							left: 'center'
						},
						tooltip: {
							trigger: 'item'
						},
						legend: {
							top: '5%',
							left: 'center'
						},
						series: [{
							name: 'Access From',
							type: 'pie',
							radius: ['40%', '70%'],
							avoidLabelOverlap: false,
							itemStyle: {
								borderRadius: 10,
								borderColor: '#fff',
								borderWidth: 2
							},
							label: {
								show: false,
								position: 'center'
							},
							emphasis: {
								label: {
									show: true,
									fontSize: '40',
									fontWeight: 'bold'
								}
							},
							labelLine: {
								show: false
							},
							data: this.dataset,
							}]
				});
			});
			},
			initChart2() {
				let myChart2 = this.$echarts.init(document.getElementById("myChart2"));
				this.getRequest("/statistics/getDepartmentNumbers").then((resp) => {
					if(resp){
						this.dataset=resp;
					}
					myChart2.setOption({
						title: {
							text: '部门分布',
							left: 'center'
						},
						tooltip: {
							trigger: 'item'
						},
						legend: {
							orient: 'vertical',
							left: 'left'
						},
						series: [{
							name: 'Access From',
							type: 'pie',
							radius: '50%',
							data:this.dataset ,
							emphasis: {
								itemStyle: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}]
					});
				});
			},
			init() {
				this.getRequest("/statistics/getEmployeeNumbers").then((resp) => {
					if (resp)
						this.zongNumber = resp;
				});
				this.getRequest("/employee/basic/getPerson").then((resp) => {
					if (resp)
						this.newPerson = resp[0];
						this.oldPerson = resp[1];
						this.stopPerson = resp[2];
						this.deletePerson = resp[3];
				});
				this.getRequest("/statistics/getSexNumbers").then((resp) => {
					if (resp) {
						this.mannumber = resp[0];
						this.womannumber = resp[1];
						this.manNumber = Math.round(resp[0] / this.zongNumber * 100);
						this.womanNumber = Math.round(resp[1] / this.zongNumber * 100);
					}
				});
				this.getRequest("/statistics/getAgeNumbers").then((resp) => {
					if (resp) {
						this.sage = resp[0];
						this.mage = resp[1];
						this.lage = resp[2];
						this.sAge = Math.round(resp[0] / this.zongNumber * 100);
						this.mAge = Math.round(resp[1] / this.zongNumber * 100);
						this.lAge = Math.round(resp[2] / this.zongNumber * 100);
					}
				});
			}
		},
	}
</script>

<style>
</style>
