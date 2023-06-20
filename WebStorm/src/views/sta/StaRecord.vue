<template>
  <div>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="假期申请" name="Leave"><Leave /></el-tab-pane>
      <el-tab-pane label="调动申请" name="Transfer"><Transfer /></el-tab-pane>
	  <el-tab-pane label="报销申请" name="Reimbursement"><Reimbursement /></el-tab-pane>
	  <el-tab-pane label="审批管理" name="Manage"><Manage /></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Leave from "../../components/sta/record/Leave.vue";
import Reimbursement from "../../components/sta/record/Reimbursement.vue";
import Transfer from "../../components/sta/record/Transfer.vue";
import Manage from "../../components/sta/record/Manage.vue";
export default {
  name: "StaRecord",
  data() {
    return {
      activeName: "Leave",
    };
  },
  components: { Leave, Reimbursement, Transfer,Manage},
};
</script>

<style>
</style>