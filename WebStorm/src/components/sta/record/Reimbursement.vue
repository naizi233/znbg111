<template>
	<div>
		<div style="height: 50px;">
			<div style="float: left;width: 800px;">
				<el-input v-model="le.content" type="textarea" size="small" placeholder="报销项目"
					prefix-icon="el-icon-plus" style="width: 500px" :autosize="{ minRows: 2, maxRows: 3}"
					clearable="true" maxlength="200" show-word-limit></el-input>
				<el-button style="margin-left: 10px;" @click="addApprove" type="primary" icon="el-icon-check" circle>
				</el-button>
				<el-button type="success" icon="el-icon-edit" circle></el-button>
				<el-button type="danger" icon="el-icon-delete" @click="deleteApprove" circle></el-button>
			</div>
		</div>
		<div style="margin-top: 50px;">
			<el-steps :active="number" align-center>
				<el-step title="步骤1" description="提交申请"></el-step>
				<el-step title="步骤2" description="待审批"></el-step>
				<el-step title="步骤3" description="已通过"></el-step>
			</el-steps>
		</div>
		<div class="big-contain" v-if="isNull">
			<el-empty description="暂无记录"></el-empty>
		</div>
		<div class="big-contain" style="margin-top: 50px;margin-bottom: 20px;" v-else>
			<el-descriptions border title="申请信息">
				<el-descriptions-item label="编号">{{les.id}}</el-descriptions-item>
				<el-descriptions-item label="申请人工号">{{les.eid}}</el-descriptions-item>
				<el-descriptions-item label="申请时间">{{les.approveDate}}</el-descriptions-item>
				<el-descriptions-item label="状态">{{les.status}}</el-descriptions-item>
				<el-descriptions-item label="原因">{{les.content}}</el-descriptions-item>
			</el-descriptions>
		</div>
		<el-upload class="upload-demo" ref="upload" action="https://jsonplaceholder.typicode.com/posts/"
			:on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :auto-upload="false">
			<el-button slot="trigger" size="small" type="primary">选取文件</el-button>
			<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
		</el-upload>
		<div class="demo-image__preview" v-if="isExits">
			<el-empty :image-size="100" description="暂无记录"></el-empty>
		</div>
		<div class="demo-image__preview" v-else>
			<el-image style="width: 100px; height: 100px" :src="url" :preview-src-list="srcList">
			</el-image>
		</div>
	</div>
</template>

<script>
	export default {
		name: "Reimbursement",
		data() {
			return {
				content: "",
				les: [],
				number: 1,
				a: '2',
				le: {
					content: "",
					imgUrl: ''
				},
				isNull: true,
				isExits: true,
				dep: "",
				url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
				srcList: ['https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'],
				fileList:[]
			};
		},
		mounted() {
			this.init();
		},
		methods: {
			init() {
				this.getRequest("/approve/get?a=" + this.a).then((resp) => {
					if (resp) {
						this.les = resp;
						if (this.les.status == "已提交")
							this.number = 1;
						else if (this.les.status == "待审批")
							this.number = 2;
						else this.number = 3;
						this.isNull = false;
					} else {
						this.number = 0;
						this.isNull = true;
					}
				});
				this.le.content = "";
			},

			addApprove() {
				if (this.le.content) {
					this.postRequest("/approve/addR", this.le).then((resp) => {
						if (resp) {
							this.init();
						} else {
							this.$message.error("添加字段不能为空!");
						}
					});
				}
			},

			deleteApprove() {
				this.$confirm("删除一条记录, 是否继续?", "提示", {
						confirmButtonText: "确定",
						cancelButtonText: "取消",
						type: "warning",
					})
					.then(() => {
						this.deleteRequest("/approve/delete", {
							id: this.les.id,
						}).then((res) => {
							if (res) {
								this.init();
							}
						});
					})
					.catch(() => {
						this.$message({
							type: "info",
							message: "已取消删除",
						});
					});
			},
			handleRemove(file, fileList) {
				console.log(file, fileList);
				this.isExits = false;
			},
			handlePreview(file) {
				console.log(file);
				this.isExits = false;
			},
			change() {
				this.isExits = false;
			}
		}
	}
</script>

<style>
	.my-label {
		background: #aaff7f;
	}

	.my-content {
		background: #FDE2E2;
	}
</style>
