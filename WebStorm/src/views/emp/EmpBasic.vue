<template>
  <div>
    <div>
      <div style="display: flex; justify-content: space-between">
        <div>
          <el-input
            prefix-icon="el-icon-search"
            placeholder="请输入员工姓名进行搜索..."
            v-model="empName"
            @keydown.enter.native="initEmps"
            clearable
            @clear="initEmps"
            style="width: 300px"
          ></el-input>
          <el-button
            style="margin-left: 10px"
            icon="el-icon-search"
            type="primary"
            @click="initEmps"
            >搜索</el-button
          >
          <el-button type="primary" @click="showSearch = !showSearch">
            <i :class="showSearch ? 'fa fa-angle-double-up' : 'fa fa-angle-double-down'" aria-hidden="true"></i>
            高级搜索
          </el-button>
        </div>
      </div>
    </div>
    <transition name="slide-fade">
      <div
        v-show="showSearch"
        style="
          border: 1px solid #409eff;
          border-radius: 5px;
          box-sizing: border-box;
          padding: 7px;
          margin: 15px 0px;
        "
      >
        <el-row>
          <el-col :span="5">
            政治面貌:
            <el-select
              v-model="searchValue.politicId"
              size="mini"
              placeholder="政治面貌"
              style="width: 130px"
            >
              <el-option
                v-for="item in politicsstatus"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="4">
            民族:
            <el-select
              v-model="searchValue.nationId"
              size="mini"
              placeholder="民族"
              style="width: 130px"
            >
              <el-option
                v-for="item in nations"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="4">
            职位:
            <el-select
              v-model="searchValue.posId"
              size="mini"
              placeholder="职位"
              style="width: 130px"
            >
              <el-option
                v-for="item in positions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="4">
            职称:
            <el-select
              v-model="searchValue.jobLevelId"
              size="mini"
              placeholder="职称"
              style="width: 130px"
            >
              <el-option
                v-for="item in joblevels"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="7">
            聘用形式:
            <el-radio-group v-model="searchValue.engageForm">
              <el-radio label="劳动合同">劳动合同</el-radio>
              <el-radio label="劳务合同">劳务合同</el-radio>
            </el-radio-group>
          </el-col>
        </el-row>
        <el-row style="margin-top: 10px">
          <el-col :span="5">
            所属部门:
            <el-popover
              placement="bottom"
              title="请选择部门"
              width="200"
              trigger="manual"
              v-model="visible2"
            >
              <el-tree
                :data="allDeps"
                :props="defaultProps"
                default-expand-all
                @node-click="searchhandleNodeClick"
              ></el-tree>
              <div
                slot="reference"
                style="
                  width: 130px;
                  height: 24px;
                  display: inline-flex;
                  border: 1px solid #dedede;
                  border-radius: 5px;
                  cursor: pointer;
                  align-items: center;
                  font-size: 13px;
                  padding-left: 8px;
                  box-sizing: border-box;
                "
                @click="showDepView2"
              >
                {{ inputDepName }}
              </div>
            </el-popover>
          </el-col>
          <el-col :span="10">
            入职日期:
            <el-date-picker
              size="mini"
              v-model="searchValue.beginDateScope"
              type="daterange"
              value-format="yyy-MM-dd"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker>
          </el-col>
          <el-col :span="5" :offset="4">
            <el-button
              size="mini"
              icon="el-icon-search"
              type="primary"
              @click="initEmps('advanced')"
              >搜索</el-button
            >
            <el-button size="mini" >取消</el-button>
          </el-col>
        </el-row>
      </div>
    </transition>

    <div style="margin-top: 20px">
      <el-table
        :data="emps"
        border
        stripe
        v-loading="loading"
        element-loading-text="拼命加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        style="width: 100%"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          fixed="left"
          align="left"
          width="90"
        >
        </el-table-column>
        <el-table-column prop="workID" label="工号" align="left" width="85">
        </el-table-column>
        <el-table-column prop="gender" label="性别" width="50">
        </el-table-column>
        <el-table-column
          prop="birthday"
          label="出生日期"
          align="left"
          width="95"
        >
        </el-table-column>
        <el-table-column
          prop="idCard"
          label="身份证号"
          align="left"
          width="150"
        >
        </el-table-column>
        <el-table-column prop="wedlock" label="婚姻状况" width="70">
        </el-table-column>
        <el-table-column prop="nation.name" label="民族" width="50">
        </el-table-column>
        <el-table-column prop="nativePlace" label="籍贯" width="88">
        </el-table-column>
        <el-table-column
          prop="politicsStatus.name"
          label="政治面貌"
          width="100"
        >
        </el-table-column>
        <el-table-column prop="email" label="邮箱" align="left" width="180">
        </el-table-column>
        <el-table-column prop="phone" label="电话号码" width="100">
        </el-table-column>
        <el-table-column
          prop="address"
          label="联系地址"
          align="left"
          width="270"
        >
        </el-table-column>
        <el-table-column
          prop="joblevel.name"
          label="职称"
          align="left"
          width="100"
        >
        </el-table-column>
        <el-table-column
          prop="department.name"
          label="所属部门"
          align="left"
          width="100"
        >
        </el-table-column>
        <el-table-column prop="position.name" label="职位" width="100">
        </el-table-column>
        <el-table-column
          prop="engageForm"
          label="聘用形式"
          align="left"
          width="100"
        >
        </el-table-column>
        <el-table-column prop="tiptopDegree" label="最高学历" width="80">
        </el-table-column>
        <el-table-column
          prop="school"
          label="毕业院校"
          align="left"
          width="150"
        >
        </el-table-column>
        <el-table-column prop="specialty" label="专业" align="left" width="150">
        </el-table-column>
        <el-table-column
          prop="workState"
          label="在职状态"
          align="left"
          width="70"
        >
        </el-table-column>
        <el-table-column
          prop="beginDate"
          label="入职日期"
          align="left"
          width="95"
        >
        </el-table-column>
        <el-table-column
          prop="conversionTime"
          label="转正日期"
          align="left"
          width="95"
        >
        </el-table-column>
        <el-table-column
          prop="beginContract"
          label="合同起始日期"
          align="left"
          width="95"
        >
        </el-table-column>
        <el-table-column
          prop="endContract"
          label="合同截止日期"
          align="left"
          width="95"
        >
        </el-table-column>
        <el-table-column
          prop="contractTerm"
          label="合同期限"
          align="left"
          width="100"
        >
          <template slot-scope="scope">
            <el-tag>{{ scope.row.contractTerm }}</el-tag
            >年
          </template>
        </el-table-column>
      </el-table>
      <div style="display: flex; justify-content: flex-end">
        <el-pagination
          background
          @current-change="currentChange"
          @size-change="sizeChange"
          layout="sizes, prev, pager, next, jumper, ->, total"
          :total="total"
        >
        </el-pagination>
      </div>
    </div>
   <el-backtop target=".page-component__scroll .el-scrollbar__wrap"></el-backtop>
  </div>
</template>

<script>
export default {
  name: "EmpBasic",
  data() {
    return {
      searchValue: {
        politicId: null,
        nationId: null,
        posId: null,
        jobLevelId: null,
        engageForm: "",
        departmentId: "",
        beginDateScope: null,
      },
      showSearch: false,
      headers: {
        Authorization: window.sessionStorage.getItem("tokenStr"),
      },
      importDataBtnText: "导入数据",
      importDataBtnIcon: "el-icon-upload2",
      importDataDisabled: false,
      defaultProps: {
        children: "children",
        label: "name",
      },
      emps: [],
      loading: false,
      total: 0,
      currentPage: 1,
      size: 10,
      empName: "",
      dialogVisible: false,
      emp: {
        id: null,
        name: "",
        gender: "",
        birthday: "",
        idCard: "",
        wedlock: "",
        nationId: null,
        nativePlace: "",
        politicId: null,
        email: "",
        phone: "",
        address: "",
        departmentId: null,
        jobLevelId: null,
        posId: null,
        engageForm: "",
        tiptopDegree: "",
        specialty: "",
        school: "",
        beginDate: "",
        workState: "在职",
        workID: "",
        contractTerm: 9.31,
        conversionTime: "",
        notWorkDate: null,
        beginContract: "",
        endContract: "",
        workAge: null,
        salaryId: 2,
        nation: {
          id: null,
          name: "",
        },
        politicsStatus: {
          id: null,
          name: "",
        },
        department: {
          id: null,
          name: "",
          parentId: null,
          depPath: null,
          enabled: null,
          isParent: null,
          children: null,
          result: null,
        },
        joblevel: {
          id: null,
          name: "",
          titleLevel: null,
          createDate: null,
          enabled: null,
        },
        position: {
          id: null,
          name: "",
          createDate: null,
          enabled: null,
        },
        s: null,
      },

      nations: [],
      joblevels: [],
      visible: false,
      visible2: false,
      politicsstatus: [],
      title: "",
      positions: [],
      allDeps: [],
      tiptopDegrees: ["博士", "硕士", "本科", "大专", "高中", "初中", "小学"],
      inputDepName: "",
      rules: {
        name: [{ required: true, message: "请输入员工姓名", trigger: "blur" }],
        gender: [
          { required: true, message: "请输入员工性别", trigger: "blur" },
        ],
        birthday: [
          { required: true, message: "请输入出生日期", trigger: "blur" },
        ],
        idCard: [
          { required: true, message: "请输入身份证号码", trigger: "blur" },
          {
            pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            message: "身份证号码不正确",
            trigger: "blur",
          },
        ],
        wedlock: [
          { required: true, message: "请输入婚姻状况", trigger: "blur" },
        ],
        nationId: [{ required: true, message: "请输入民族", trigger: "blur" }],
        nativePlace: [
          { required: true, message: "请输入籍贯", trigger: "blur" },
        ],
        politicId: [
          { required: true, message: "请输入政治面貌", trigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          { type: "email", message: "邮箱地址格式不正确", trigger: "blur" },
        ],
        phone: [{ required: true, message: "请输入电话号码", trigger: "blur" }],
        address: [
          { required: true, message: "请输入员工地址", trigger: "blur" },
        ],
        departmentId: [
          { required: true, message: "请选择部门", trigger: "blur" },
        ],
        jobLevelId: [
          { required: true, message: "请输入职称", trigger: "blur" },
        ],
        posId: [{ required: true, message: "请选择职位", trigger: "blur" }],
        engageForm: [
          { required: true, message: "请选择聘用形式", trigger: "blur" },
        ],
        tiptopDegree: [
          { required: true, message: "请选择学历", trigger: "blur" },
        ],
        specialty: [{ required: true, message: "请输入专业", trigger: "blur" }],
        school: [
          { required: true, message: "请输入毕业院校", trigger: "blur" },
        ],
        beginDate: [
          { required: true, message: "请输入入职日期", trigger: "blur" },
        ],
        workState: [
          { required: true, message: "请输入工作状态", trigger: "blur" },
        ],
        wordkID: [{ required: true, message: "请输入工号", trigger: "blur" }],
        contractTerm: [
          { required: true, message: "请输入合同期限", trigger: "blur" },
        ],
        conversionTime: [
          { required: true, message: "请输入转正日期", trigger: "blur" },
        ],
        notWorkDate: [
          { required: true, message: "请输入离职日期", trigger: "blur" },
        ],
        beginContract: [
          { required: true, message: "请输入合同起始日期", trigger: "blur" },
        ],
        endContract: [
          { required: true, message: "请输入合同结束日期", trigger: "blur" },
        ],
        workAge: [{ required: true, message: "请输入工龄", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.initEmps();
    this.initData();
    this.initPositions();
  },
  methods: {
    searchhandleNodeClick(data) {
      this.inputDepName = data.name;
      this.searchValue.departmentId = data.id;
      this.visible2 = !this.visible2;
    },
    showDepView2() {
      this.visible2 = !this.visible2;
    },
    doAddEmp() {
      if (this.emp.id) {
        this.$refs["empForm"].validate((valid) => {
          if (valid) {
            this.putRequest("/employee/basic/update", this.emp).then((resp) => {
              if (resp) {
                this.dialogVisible = false;
                this.initEmps();
              }
            });
          }
        });
      } else {
        this.$refs["empForm"].validate((valid) => {
          if (valid) {
            this.postRequest("/employee/basic/addEmp", this.emp).then(
              (resp) => {
                if (resp) {
                  this.dialogVisible = false;
                  this.initEmps();
                }
              }
            );
            console.log(this.emp);
          }
        });
      }
    },
    handleNodeClick(data) {
      this.inputDepName = data.name;
      this.emp.departmentId = data.id;
      this.visible = !this.visible;
    },
    showDepView() {
      this.visible = true;
    },
    getMaxworkID() {
      this.getRequest("/employee/basic/get-max-WorkID").then((resp) => {
        if (resp) {
          this.emp.workID = resp.obj;
        }
      });
    },
    initPositions() {
      this.getRequest("/employee/basic/get-all-Position").then((resp) => {
        if (resp) {
          this.positions = resp;
        }
      });
    },
    initData() {
      if (!window.sessionStorage.getItem("nations")) {
        this.getRequest("/employee/basic/get-all-Nation").then((resp) => {
          if (resp) {
            this.nations = resp;
            window.sessionStorage.setItem("nations", JSON.stringify(resp));
          }
        });
      } else {
        this.nations = JSON.parse(window.sessionStorage.getItem("nations"));
      }
      if (!window.sessionStorage.getItem("joblevels")) {
        this.getRequest("/employee/basic/get-all-job").then((resp) => {
          if (resp) {
            this.joblevels = resp;
            window.sessionStorage.setItem("joblevels", JSON.stringify(resp));
          }
        });
      } else {
        this.joblevels = JSON.parse(window.sessionStorage.getItem("joblevels"));
      }
      if (!window.sessionStorage.getItem("politicsstatus")) {
        this.getRequest("/employee/basic/get-politics-status").then((resp) => {
          if (resp) {
            this.politicsstatus = resp;
            window.sessionStorage.setItem(
              "politicsstatus",
              JSON.stringify(resp)
            );
          }
        });
      } else {
        this.politicsstatus = JSON.parse(
          window.sessionStorage.getItem("politicsstatus")
        );
      }
      if (!window.sessionStorage.getItem("allDeps")) {
        this.getRequest("/system/basic/department/list").then((resp) => {
          if (resp) {
            this.allDeps = resp;
            window.sessionStorage.setItem("allDeps", JSON.stringify(resp));
          }
        });
      } else {
        this.allDeps = JSON.parse(window.sessionStorage.getItem("allDeps"));
      }
    },
    sizeChange(size) {
      this.size = size;
      this.initEmps();
    },
    currentChange(currentPage) {
      this.currentPage = currentPage;
      this.initEmps();
    },
    initEmps(type) {
      this.loading = true;
      console.log(this.searchValue.beginDateScope);
      let url =
        "/employee/basic/?currentPage=" +
        this.currentPage +
        "&size=" +
        this.size;
      if (type && type == "advanced") {
        if (this.searchValue.politicId) {
          url += "&politicId=" + this.searchValue.politicId;
        }
        if (this.searchValue.nationId) {
          url += "&nationId=" + this.searchValue.nationId;
        }
        if (this.searchValue.posId) {
          url += "&posId=" + this.searchValue.posId;
        }
        if (this.searchValue.jobLevelId) {
          url += "&jobLevelId=" + this.searchValue.jobLevelId;
        }
        if (this.searchValue.engageForm) {
          url += "&engageForm=" + this.searchValue.jobLevelId;
        }
        if (this.searchValue.departmentId) {
          url += "&departmentId=" + this.searchValue.departmentId;
        }
        if (this.searchValue.beginDateScope) {
          url +=
            "&beginDateScope=" +
            this.searchValue.beginDateScope[0];
        }

      }else {
        url += "&name=" + this.empName;
      }
      this.getRequest(url).then((resp) => {
        this.loading = false;
        if (resp) {
          this.emps = resp.date;
          this.total = resp.total;
        }
      });
    },
  },
};
</script>

<style>
.slide-fade-enter-active {
  transition: all 0.3s ease;
}
.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}
</style>