<template>
	<div>
		<div class="big-contain" v-if="isNull">
			<el-empty description="暂无记录"></el-empty>
		</div>
		<div class="big-contain" v-else>
			<el-descriptions border title="申请信息">
				<el-descriptions-item label="编号">{{les.id}}</el-descriptions-item>
				<el-descriptions-item label="申请时间" :span="2">{{les.approveDate}}</el-descriptions-item>
				<el-descriptions-item label="原因" :span="3">{{les.content}}</el-descriptions-item>
				<el-descriptions-item label="状态" :span="1">{{les.status}}</el-descriptions-item>
				<el-descriptions-item label="申请人工号" :span="1">{{les.eid}}</el-descriptions-item>
				<el-descriptions-item label="操作" :span="1" v-if="ishandled">
					<el-button-group>
						<el-button size="mini" type="primary" icon="el-icon-arrow-left" @click="updateApprove">同意</el-button>
						<el-button size="mini" type="danger">拒绝<i class="el-icon-arrow-right el-icon--right"></i></el-button>
					</el-button-group>
				</el-descriptions-item>
				<el-descriptions-item label="处理人" :span="1" v-else>
					{{les.approvePerson}}
				</el-descriptions-item>
			</el-descriptions>
		</div>
	</div>
</template>

<script>
	export default {
		name: "Leave",
		data() {
			return {
				content: "",
				les: [],
				number: 1,
				a: '1',
				le: {
					content: ""
				},
				isNull: true,
				ishandled:true,
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
						if (this.les.status == "已提交"||this.les.status == "待审批")
							this.ishandled = true;
						else 
							this.ishandled = false;
						this.isNull = false;
					} else {
						this.isNull = true;
					}
				});
				this.le.content = "";
			},

			updateApprove(){
				this.putRequest("/approve/update", this.les).then((resp) => {
					if(resp){
						this.init();
					}
				});
			},
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
