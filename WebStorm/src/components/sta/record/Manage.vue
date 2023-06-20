<template>
	<div>
	  <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">
	    <el-tab-pane label="假期管理" name="Leave"><Leave /></el-tab-pane>
		<el-tab-pane label="调动申请" name="Transfer"><Transfer /></el-tab-pane>
		<el-tab-pane label="报销申请" name="Reimbursement"><Reimbursement /></el-tab-pane>
	  </el-tabs>
	</div>
</template>

<script>
import Leave from "/src/components/sta/record/manage/Leave.vue";
import Transfer from "/src/components/sta/record/manage/Transfer.vue";
import Reimbursement from "/src/components/sta/record/manage/Reimbursement.vue";
	export default {
		name: "Manage",
		data() {
		  return {
		    activeName: "Leave",
		  };
		},
		components: {Leave,Reimbursement, Transfer,},
	}
</script>

<style>
</style>
