<template>
 <div>
 	  <div style="display: flex; justify-content: space-between">
 	  <div>
 	    <el-input
 	      prefix-icon="el-icon-search"
 	      placeholder="请输入工号进行搜索..."
 	      v-model="Name"
 	      @keydown.enter.native=""
 	      clearable
 	      @clear=""
 	      style="width: 300px"
 	    ></el-input>
 	    <el-button
 	      style="margin-left: 10px"
 	      icon="el-icon-search"
 	      type="primary"
 	      @click=""
 	      >搜索</el-button
 	    >

 	  </div>
 	  <div style="display: inline-flex; margin-right: 194px">
 		  <el-button
 		    type="primary"
 		    icon="el-buttton-plus"
 		    @click=""
 		    >添加培训记录</el-button>
 			<el-button
 			  type="danger"
 			  :disabled="this.multipleSelection.length == 0"
 			  @click="deleteMany"
 			  >批量删除</el-button>
 	  </div>
 	</div>
   <div>
     <el-table
       :data="mvs"
       size="small"
       stripe
       border
       style="width: 85%; margin-top: 10px"
       @selection-change="handleSelectionChange"
     >
       <el-table-column type="selection" width="45"> </el-table-column>
       <el-table-column prop="eid" label="工号" width="50"> </el-table-column>
       <el-table-column prop="afterDepId" label="调动后部门" width="100">
       </el-table-column>
       <el-table-column prop="afterJobId" label="调动后职位" width="100">
       </el-table-column>
       <el-table-column prop="removeDate" label="调动日期" width="100">
 		</el-table-column>
		<el-table-column prop="reason" label="调动原因" width="300">
		</el-table-column>
		<el-table-column prop="remark" label="备注" width="300">
		</el-table-column>
 		<el-table-column label="操作" width="150">
         <template slot-scope="scope">
           <el-button size="small" @click="showEditView(scope.row)"
             >编辑</el-button>
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
  name: "PerMv",
  data() {
    return {
      mvs: [],
      showSearch: false,
  	  multipleSelection:[]
    };
  },
  mounted() {
    this.initMvs();
  },
  methods: {
    initMvs(type) {
      this.getRequest("/employee-Remove/list").then((resp) => {
        if (resp) {
          this.mvs = resp;
        }
      });
    },
  	handleSelectionChange(val) {
  	  this.multipleSelection = val;
  	}
  }
};
</script>

<style>
</style>