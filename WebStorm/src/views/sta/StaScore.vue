<template>
	<div>
		<div style="margin-bottom: 10px;">
			<el-input prefix-icon="el-icon-search" placeholder="请输入工号进行搜索..." v-model="Name" @keydown.enter.native=""
				clearable @clear="" style="width: 200px;margin-right: 20px;"></el-input>
			<el-select v-model="month" clearable placeholder="请选择月份查询">
				<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"
					@keydown.enter.native="doSearch">
				</el-option>
			</el-select>
			<el-button style="margin-left: 10px" icon="el-icon-search" type="primary" @click="">搜索</el-button>
		</div>
		<el-table height="450px" border="true" :data="tableData" style="width: 100%" :row-class-name="tableRowClassName"
			:header-cell-style="{
    'background-color': '#ffffff',
    'color': 'rgb(85, 170, 255)'
}">
			<el-table-column prop="eid" label="工号" width="180">
			</el-table-column>
			<el-table-column prop="name" label="姓名" width="180">
			</el-table-column>
			<el-table-column prop="appDate" label="日期" width="180">
			</el-table-column>
			<el-table-column prop="appContent" label="考评内容">
			</el-table-column>
			<el-table-column label="考评结果">
				<template slot-scope="scope">
					<el-rate v-model="scope.row.appResult" disabled show-score
						:colors="['#99A9BF', '#F7BA2A', '#FF9900']" score-template="{value}">
					</el-rate>
				</template>
			</el-table-column>
			<el-table-column prop="remark" label="备注">
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	export default {
		name: "StaScore",
		methods: {
			tableRowClassName({
				row,
				rowIndex
			}) {
				if (row.appResult > 3.5) {
					return 'success-row';
				} else {
					return 'warning-row';
				}
			},
			init() {
				this.getRequest("/apprise/list").then((resp) => {
					if (resp) {
						this.tableData = resp;
					}
				});
			}
		},
		mounted() {
			this.init();
		},
		data() {
			return {
				tableData: [],
				iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'],
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
				}]
			}
		}
	}
</script>

<style>
	.el-table .warning-row {
		background: oldlace;
	}

	.el-table .success-row {
		background: #f0f9eb;
	}
</style>
