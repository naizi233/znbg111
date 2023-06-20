<template style="width: 100%;height: 100%">
	<div class="topDiv">
		<el-row class="el" :gutter="10">
			<el-col :span="8">
				<div id="dataTable2" class="grid-content bg-purple">
					<el-tag style="margin-left: 170px;margin-top: 20px;" type="success">本月支出</el-tag>
					<div id="myChart2" style="float: left;width: 400px;height: 400px;"></div>
				</div>
			</el-col>
			<el-col :span="8">
				<div id="dataTable3" class="grid-content bg-purple">
					<el-tag style="margin-left: 170px;margin-top: 20px;" type="danger">本月收入</el-tag>
					<div id="myChart3" style="float: left;width: 400px;height: 400px;"></div>
				</div>
			</el-col>
			<el-col :span="8">
				<div id="ExIn" class="grid-content bg-purple">
					<div class="panel_body1" style="color: #67C23A;">
						<div class="summary">
							<div><span class="textGreen">YEARLY EXPENSE</span></div>
							<div class="green-txt" style="margin-left: 38px;">
								<el-tag class="ex" color="#67C23A">
									$ {{ money[0] }}
								</el-tag>
							</div>
						</div>
					</div>
					<div class="panel_body2" style="color: #F56C6C;">
						<div class="summary">
							<div><span class="textRed">YEARLY INCOME</span></div>
							<div class="red-txt" style="margin-left: 33px;">
								<el-tag class="in" color="#F56C6C">$ {{ money[1] }}</el-tag>
							</div>
						</div>
					</div>
					<div>
						<el-progress style="margin-left: 160px;" type="circle" :percentage="25"></el-progress>
						</br>
						<el-tag style="margin-left: 185px;margin-top: 10px;margin-bottom: 10px;">本周出勤率</el-tag>
					</div>
					<div>
						<el-progress style="margin-left: 160px;" type="circle" :percentage="10">
						</el-progress>
						</br>
						<el-tag style="margin-left: 185px;margin-top: 10px;margin-bottom: 10px;">本月出勤率</el-tag>
					</div>
				</div>
			</el-col>
		</el-row>
		<el-row class="el" :gutter="20">
			<el-col :span="18">
				<div class="grid-content bg-purple">
					<div id="dataTable1">
						<div id="myChart1" style="float: left;width: 800px;height: 400px;"></div>
					</div>
				</div>
			</el-col>
			<el-col :span="6">
				<div class="grid-content bg-purple"></div>
			</el-col>
		</el-row>
		<el-row class="el" :gutter="20">
			<el-col :span="18">
				<div class="grid-content bg-purple">
					<div id="dataTable4">
						<div id="chart-test2" style="width: 900px;height: 500px;"></div>
					</div>
				</div>
			</el-col>
			<el-col :span="6">
				<div class="grid-content bg-purple">
				</div>
			</el-col>
		</el-row>
	</div>
</template>
<script>
	import echarts from "echarts";
	require("echarts/theme/macarons");
	export default {
		data() {
			return {
				testOption2: {
					backgroundColor: '', //设置背景色透明
					xAxis: {
						type: 'category',
						data: ['苹果', '香蕉', '火龙果', '西瓜', '猕猴桃', '哈密瓜', '黄瓜', '西红柿', '水蜜桃', '橘子'],
						inverse: true,
						animationDuration: 300,
						animationDurationUpdate: 300,
						max: 10
					},
					yAxis: {
						max: 'dataMax',
					},
					series: [{
						realtimeSort: true,
						name: '产品',
						type: 'bar',
						data: [],
						label: {
							show: true,
							position: 'right',
							valueAnimation: true
						}
					}],
					legend: {
						show: true
					},
					animationDuration: 0,
					animationDurationUpdate: 3000,
					animationEasing: 'linear',
					animationEasingUpdate: 'linear'
				},
				money: [],
			};
		},
		mounted() {
			this.initChartEx();
			this.initChartIn();
			this.update();
			this.initChart1();
			this.getMoney();
		},
		methods: {
			//模拟获取数据
			getData() {
				let chartTest2 = this.$echarts.init(document.getElementById("chart-test2"), 'dark');
				var data = this.testOption2.series[0].data;
				for (var i = 0; i < data.length; ++i) {
					if (Math.random() > 0.9) {
						data[i] += Math.round(Math.random() * 2000);
					} else {
						data[i] += Math.round(Math.random() * 200);
					}
				}
				this.testOption2.series[0].data = data
				chartTest2.setOption(this.testOption2); //得到数据后重新渲染图表
			},
			update() {
				//测试二
				let chartTest2 = this.$echarts.init(document.getElementById("chart-test2"), 'dark');
				//初始化数据
				let test2_Data = [];
				for (let i = 0; i < 10; ++i) {
					test2_Data.push(Math.round(Math.random() * 200));
				}
				this.testOption2.series[0].data = test2_Data //初始化数据
				chartTest2.setOption(this.testOption2) //初始化图表

				let that = this;
				setTimeout(function() {
					that.getData();
				}, 0);
				setInterval(function() {
					that.getData();
				}, 3000);
			},
			initChartEx() {
				this.charEx = this.$echarts.init(document.getElementById("myChart2"));
				this.charEx.setOption({
					roseType: 'angle',
					tooltip: {},
					series: [{
						type: 'pie',
						radius: '55%',
						data: []
					}]
				});

				this.getRequest("/echarts/getAreaEx").then((resp) => {
					if (resp) {
						console.log('访问后台');
						this.labList = resp;
						console.log(this.labList);
						this.charEx.setOption({
							series: [{
								name: '访问来源',
								type: 'pie',
								radius: '55%',
								data: this.labList,
								emphasis: {
									focus: 'self'
								},
							}]
						})
					}
				});
			},
			initChartIn() {
				this.charIn = this.$echarts.init(document.getElementById("myChart3"));
				this.charIn.setOption({
					roseType: 'angle',
					tooltip: {},
					series: [{
						type: 'pie',
						radius: '55%',
						data: []
					}]
				});

				this.getRequest("/echarts/getAreaIn").then((resp) => {
					if (resp) {
						console.log('访问后台');
						this.labList = resp;
						console.log(this.labList);
						this.charIn.setOption({
							series: [{
								name: '访问来源',
								type: 'pie',
								radius: '55%',
								data: this.labList,
								emphasis: {
									focus: 'self'
								},
							}]
						})
					}
				});
			},
			initChart1() {
				let myChart = this.$echarts.init(document.getElementById("myChart1"));
				let exMoney = [];
				let inMoney = [];
				this.getRequest("/echarts/getAreaInEx").then((resp) => {
					if (resp) {
						for (var i = 0; i < resp.length; i++) {
							exMoney.push(resp[i][0]);
							inMoney.push(resp[i][1]);
						}
					}
					myChart.setOption({
						title: {
							text: '月份账单统计'
						},
						tooltip: {
							trigger: 'axis'
						},
						legend: {
							data: ['支出', '收入']
						},
						grid: {
							left: '3%',
							right: '4%',
							bottom: '3%',
							containLabel: true
						},
						toolbox: {
							feature: {
								saveAsImage: {}
							}
						},
						xAxis: {
							type: 'category',
							boundaryGap: false,
							data: ['一月', '二月', '三月', '四月',
								'五月', '六月', '七月', '八月',
								'九月', '十月', '十一月', '十二月'
							]
						},
						yAxis: {
							type: 'value'
						},
						series: [{
								name: '支出',
								type: 'line',
								stack: 'Total',
								data: exMoney,
								emphasis: {
									focus: 'self'
								}
							},
							{
								name: '收入',
								type: 'line',
								stack: 'Total',
								data: inMoney,
								emphasis: {
									focus: 'self'
								}
							},
						],
					});
				});
			},
			getMoney() {
				this.getRequest("/echarts/getYear").then((resp) => {
					if (resp) {
						this.money = resp;
					}
				});
			}
		}
	}
</script>

<style>
	.panel_body1 {
		float: right;
		width: 150px;
		height: 50px;
	}

	.panel_body2 {
		float: right;
		width: 150px;
		height: 50px;
		margin-bottom: 20px;
		margin-right: 10px;
	}

	.textGreen {
		margin-top: 11.285px;
		margin-left: 10px;
	}

	.textRed {
		margin-top: 4.765px;
		margin-left: 10px;
	}

	.topDiv {
		width: 100%;
		height: 100%;
	}

	#dataTable2 {
		width: 300px;
		float: left;
	}

	#dataTable3 {
		width: 300px;
		float: left;
	}

	.demoTop {
		margin-top: -50px;
	}

	#dataTable1 {
		float: left;
	}

	#dataTable4 {
		float: left;
	}


	.el {
		height: 400px;
	}

	.ex {
		color: #FFFFFF;
	}

	.in {
		color: #FFFFFF;
	}
</style>
