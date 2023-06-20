<style>
</style>
<template>
	<div>
		<div>
			<div style="margin-bottom: 20px;">
				<el-select v-model="month" clearable placeholder="请选择月份查询">
					<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"
						@keydown.enter.native="doSearch">
					</el-option>
				</el-select>
				<el-button icon="el-icon-search" @click="doSearch" circle style="margin-left: 20px;"></el-button>
			</div>
			<el-descriptions title="工资表" direction="vertical" :column="4" border>
				<el-descriptions-item label="姓名"><el-tag>韦梅</el-tag></el-descriptions-item>
				<el-descriptions-item label="工号"><el-tag>1</el-tag></el-descriptions-item>
				<el-descriptions-item label="实发工资" :span="2"><el-tag color="#FDE2E2">8500</el-tag></el-descriptions-item>
				<el-descriptions-item label="工资账套">
					<el-descriptions direction="vertical" :column="4" border>
					  <el-descriptions-item label="基本工资">5000</el-descriptions-item>
					  <el-descriptions-item label="交通补助">500</el-descriptions-item>
					  <el-descriptions-item label="午餐补助">500</el-descriptions-item>
					  <el-descriptions-item label="奖金">500</el-descriptions-item>
					  <el-descriptions-item label="养老金比率">0.06</el-descriptions-item>
					  <el-descriptions-item label="养老金基数">500</el-descriptions-item>
					  <el-descriptions-item label="医疗保险比率">0.06</el-descriptions-item>
					  <el-descriptions-item label="医疗保险基数">500</el-descriptions-item>
					  <el-descriptions-item label="公积金比率">0.06</el-descriptions-item>
					  <el-descriptions-item label="公积金基数">500</el-descriptions-item>
					  <el-descriptions-item label="应发工资" span=2><el-tag color="#FDE2E2">8000</el-tag></el-descriptions-item>
					</el-descriptions>
				</el-descriptions-item>
				
			</el-descriptions>
		</div>
	</div>
</template>

<script>
	export default {
		name: "SalSearch",
		data() {
			return {
				ses: [],
				month: "",
				options: [{
					value: '1',
					label: '1月'
				}, {
					value: '2',
					label: '2月'
				}, {
					value: '3',
					label: '3月'
				}, {
					value: '4',
					label: '4月'
				}, {
					value: '5',
					label: '5月'
				}, {
					value: '6',
					label: '6月'
				}, {
					value: '7',
					label: '7月'
				}, {
					value: '8',
					label: '8月'
				}, {
					value: '9',
					label: '9月'
				}, {
					value: '10',
					label: '10月'
				}, {
					value: '11',
					label: '11月'
				}, {
					value: '12',
					label: '12月'
				}],
			}
		},
		methods: {
			initSes() {
				alert("已更新");
			},
			doSearch() {
				this.initSes();
				this.month = "";
			}
		}
	};
</script>
