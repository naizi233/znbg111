<template>
	<div class="login-register">
		<vue-particles color="#39AFFD" :particleOpacity="0.7" :particlesNumber="200" shapeType="circle" :particleSize="4"
			linesColor="#555" :linesWidth="1" :lineLinked="true" :lineOpacity="0.4" :linesDistance="150" :moveSpeed="2"
			:hoverEffect="true" hoverMode="grab" :clickEffect="true" clickMode="push" class="lizi"></vue-particles>
		<div class="contain">
			<div class="big-box" :class="{active:isLogin}">
				<div class="big-contain" v-if="isLogin">
					<div class="btitle">账户登录</div>
					<div class="bformlogin">
						<el-form :rules="rules" v-loading="loading" element-loading-text="登录中...."
							element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)"
							ref="loginForm" :model="loginForm" class="loginContainer">
							<el-form-item prop="username">
								<el-input class="input" type="text" v-model="loginForm.username" placeholder="请输入用户名">
								</el-input>
							</el-form-item>
							<el-form-item prop="password">
								<el-input class="input" type="password" v-model="loginForm.password" placeholder="请输入密码"
									@keydown.enter.native="submitLogin"></el-input>
							</el-form-item>
							<el-form-item prop="captcha">
								<el-input style="vertical-align:middle;" class="captcha" type="text"
									v-model="loginForm.captcha" placeholder="点击图片更换验证码"
									@keydown.enter.native="submitLogin"></el-input>
								<img style="vertical-align:middle;" :src="captchaUrl" alt="" @click="updataCaptcha" />
							</el-form-item>
							<el-checkbox v-model="checked" class="loginRemember">记住我</el-checkbox>

							<el-button style="margin-left: 140px;" class="bbutton" @click="submitLogin">登录</el-button>
						</el-form>
					</div>
				</div>
				<div class="big-contain" v-else>
					<div class="btitle">创建账户</div>
					<div class="bform">
						<input type="text" placeholder="用户名" v-model="form.username">
						<span class="errTips" v-if="existed">* 用户名已经存在！ *</span>
						<input type="email" placeholder="邮箱" v-model="form.useremail">
						<input type="password" placeholder="密码" v-model="form.userpwd">
					</div>
					<button class="bbutton" @click="register">注册</button>
				</div>
			</div>
			<div class="small-box" :class="{active:isLogin}">
				<div class="small-contain" v-if="isLogin">
					<div class="stitle">你好，朋友!</div>
					<p class="scontent">开始注册，和我们一起旅行</p>
					<button class="sbutton" @click="changeType">注册</button>
				</div>
				<div class="small-contain" v-else>
					<div class="stitle">欢迎回来!</div>
					<p class="scontent">与我们保持联系，请登录你的账户</p>
					<button class="sbutton" @click="changeType">登录</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'Login',
		data() {
			return {
				captchaUrl: "/captcha?time=" + new Date(),
				isLogin: true,
				existed: false,
				loginForm: {
					username: "admin",
					password: "123",
					captcha: "",
				},
				form: {
					username: '',
					useremail: '',
					userpwd: ''
				},
				loading: false,
				checked: true,
				rules: {
					username: [{
						required: true,
						message: "请输入用户名",
						trigger: "blur"
					}, ],
					password: [{
						required: true,
						message: "请输入密码",
						trigger: "blur"
					}],
					code: [{
						required: true,
						message: "请输入验证码",
						trigger: "blur"
					}],
				},
			}
		},
		methods: {
			updataCaptcha() {
				this.captchaUrl = "/captcha?time=" + new Date();
			},
			changeType() {
				this.isLogin = !this.isLogin
				this.form.username = ''
				this.form.useremail = ''
				this.form.userpwd = ''
			},
			submitLogin() {
				this.$refs.loginForm.validate((valid) => {
					if (valid) {
						this.loading = true;
						this.postRequest("/login", this.loginForm).then((resp) => {
							if (resp) {
								this.loading = false;
								const tokenStr = resp.obj.tokenHead + resp.obj.token;
								window.sessionStorage.setItem("tokenStr", tokenStr);
								let path = this.$route.query.redirect;
								this.$router.replace(
									path == "/" || path == undefined ? "/home" : path
								);
							} else {
								this.loading = false;
								this.loginForm.captcha = "";
								this.captchaUrl = "/captcha?time=" + new Date();
							}
						});
					} else {
						this.$message.error("请输入全部信息");

						return false;
					}
				});
			},
			register() {
				const self = this;
				if (self.form.username != "" && self.form.useremail != "" && self.form.userpwd != "") {
					self.$axios({
							method: 'post',
							url: 'http://127.0.0.1:10520/api/user/add',
							data: {
								username: self.form.username,
								email: self.form.useremail,
								password: self.form.userpwd
							}
						})
						.then(res => {
							switch (res.data) {
								case 0:
									alert("注册成功！");
									this.login();
									break;
								case -1:
									this.existed = true;
									break;
							}
						})
						.catch(err => {
							console.log(err);
						})
				} else {
					alert("填写不能为空！");
				}
			}
		}
	}
</script>

<style scoped="scoped">
	.login-register {
		width: 100vw;
		height: 100vh;
		box-sizing: border-box;
	}

	.contain {
		width: 60%;
		height: 60%;
		position: relative;
		top: 50%;
		left: 50%;

		background-color: #fff;
		border-radius: 20px;
		box-shadow: 0 0 3px #f0f0f0,
			0 0 6px #f0f0f0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		overflow: hidden;
	}

	.big-box {
		width: 70%;
		height: 100%;
		position: absolute;
		top: 0;
		left: 30%;
		transform: translateX(0%);
		transition: all 1s;
	}

	.big-contain {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.btitle {
		font-size: 1.5em;
		font-weight: bold;
		margin-bottom: 50px;
		color: rgb(57, 167, 176);
	}

	.bform {
		width: 100%;
		height: 40%;
		padding: 2em 0;
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		align-items: center;
	}

	.bform .errTips {
		display: block;
		width: 50%;
		text-align: left;
		color: red;
		font-size: 0.7em;
		margin-left: 1em;
	}

	.bform input {
		width: 50%;
		height: 30px;
		border: none;
		outline: none;
		border-radius: 10px;
		padding-left: 2em;
		background-color: #f0f0f0;
	}

	.bbutton {
		width: 20%;
		height: 40px;
		border-radius: 24px;
		border: none;
		outline: none;
		background-color: rgb(57, 167, 176);
		color: #fff;
		font-size: 0.9em;
		cursor: pointer;
	}

	.small-box {
		width: 30%;
		height: 100%;
		background: linear-gradient(135deg, rgb(57, 167, 176), rgb(56, 183, 145));
		position: absolute;
		top: 0;
		left: 0;
		transform: translateX(0%);
		transition: all 1s;
		border-top-left-radius: inherit;
		border-bottom-left-radius: inherit;
	}

	.small-contain {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.stitle {
		font-size: 1.5em;
		font-weight: bold;
		color: #fff;
	}

	.scontent {
		font-size: 0.8em;
		color: #fff;
		text-align: center;
		padding: 2em 4em;
		line-height: 1.7em;
	}

	.sbutton {
		width: 60%;
		height: 40px;
		border-radius: 24px;
		border: 1px solid #fff;
		outline: none;
		background-color: transparent;
		color: #fff;
		font-size: 0.9em;
		cursor: pointer;
	}

	.big-box.active {
		left: 0;
		transition: all 0.5s;
	}

	.small-box.active {
		left: 100%;
		border-top-left-radius: 0;
		border-bottom-left-radius: 0;
		border-top-right-radius: inherit;
		border-bottom-right-radius: inherit;
		transform: translateX(-100%);
		transition: all 1s;
	}

	.loginRemember {
		text-align: left;
		margin: 0px 0px 15px 0px;
	}

	.captcha {
		width: 250px;
		margin-right: 10px;
	}

	.el-form-item__content {
		display: flex;
		align-items: center;
	}
</style>
