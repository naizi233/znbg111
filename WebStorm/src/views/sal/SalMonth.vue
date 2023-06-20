<template>
  <div>
    <div>
  		<div>
  			  <el-select v-model="month" clearable placeholder="请选择月份查询">
  			    <el-option
  			      v-for="item in options"
  			      :key="item.value"
  			      :label="item.label"
  			      :value="item.value"
  				  @keydown.enter.native="doSearch">
  			    </el-option>
  			  </el-select>
  			  <el-button 
  			  icon="el-icon-search" 
  			  @click="doSearch" 
  			  circle
  			  style="margin-left: 20px;"></el-button>
  		</div>
      <el-table
        :data="sms"
        size="small"
        stripe
        border
        style="width: 78%; margin-top: 10px"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection"  width="55"></el-table-column>
        <el-table-column prop="id" label="编号" width="50"> </el-table-column>
  		<el-table-column prop="type" label="类别" width="100"> </el-table-column>
        <el-table-column prop="money" label="金额" width="100">
        </el-table-column>
        <el-table-column prop="status" label="状态" width="150">
        </el-table-column>
		<el-table-column prop="remark" label="备注" width="150">
		</el-table-column>
        <el-table-column prop="msDate" label="日期" width="150">
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button size="small" @click="showEditView(scope.row)"
              >编辑</el-button>
            <el-button
              size="small"
              type="danger"
              @click="deleteJobLevel(scope.$index, scope.row)"
              >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "SalMonth",
  data() {
  	  return{
  		  sms: [],
  		  month:"",
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
  		 },{
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
  mounted() {
    this.initSms();
  },
  methods:{
  	  initSms() {
  	    this.getRequest("monthSalary/get-All?month="+this.month).then((resp) => {
  	      if (resp) {
  	        this.sms = resp;
  	      }
  	    });
  	  },
  	  doSearch(){
  		  this.initSms();
  		  this.month="";
  	  }
  }
};
</script>

<style>
</style>