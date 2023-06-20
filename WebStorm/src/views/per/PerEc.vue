<template>
  <div>
	  <div style="display: flex; justify-content: space-between">
	  <div>
	    <el-input
	      prefix-icon="el-icon-search"
	      placeholder="请输入工号进行搜索..."
	      v-model="empId"
	      @keydown.enter.native="initEcs"
	      clearable
	      @clear="initEcs"
	      style="width: 300px"
	    ></el-input>
	    <el-button
	      style="margin-left: 10px"
	      icon="el-icon-search"
	      type="primary"
	      @click="initEcs"
	      >搜索</el-button
	    >
	  </div>
	  <div style="display: inline-flex; margin-right: 194px">
		  <el-button
		    type="primary"
		    icon="el-buttton-plus"
		    @click=""
		    >添加奖惩记录</el-button>
			<el-button
			  type="danger"
			  :disabled="this.multipleSelection.length == 0"
			  @click="deleteMany"
			  >批量删除</el-button>
	  </div>
	</div>
    <div>
      <el-table
        :data="ecs"
        size="small"
        stripe
        border
        style="width: 85%; margin-top: 10px"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="45"> </el-table-column>
        <el-table-column prop="eid" label="工号" width="65"> </el-table-column>
        <el-table-column prop="ecDate" label="奖惩日期" width="120">
        </el-table-column>
        <el-table-column prop="ecReason" label="奖惩原因" width="300">
        </el-table-column>
        <el-table-column prop="ecPoint" label="奖惩分" width="65">
        </el-table-column>
        <el-table-column prop="ecType" label="奖惩类别" width="75">
        </el-table-column>
        <el-table-column prop="remark" label="备注" width="300">
		</el-table-column>
		<el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button size="small" @click="showEditView(scope.row)"
              >编辑</el-button
            >
            <el-button
              size="small"
              type="danger"
              @click="deleteJobLevel(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "PerEc",
  data() {
    return {
       ecs: [],
      empId: "",
      showSearch: false,
	  multipleSelection:[]
    };
  },
  mounted() {
    this.initEcs();
  },
  methods: {
    initEcs() {
      this.getRequest("/employeeEc/list").then((resp) => {
        if (resp) {
          this.ecs = resp;
        }
      });
    },
	handleSelectionChange(val) {
	  this.multipleSelection = val;
	}
  }
}
</script>

<style>
</style>