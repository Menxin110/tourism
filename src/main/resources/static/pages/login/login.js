var vm = new Vue({
	el: '#login',
	data() {
		return {
			ruleForm: {
				username: '',
				password: ''
			},
			rules: {
				username: {
					required: true,
					message: '请输入用户名',
					trigger: 'blur'
				},
				password: {
					required: true,
					message: '请输入密码',
					trigger: 'blur'
				},
			},

		}
	},
	methods: {
		submitForm(formName) {
			console.log(formName);
			// console.log(this.$refs[formName]);
			this.$refs[formName].validate((valid) => {
				console.log(valid);
				if (valid) {
					console.log(this.ruleForm);
					axios.post("/user/login", this.ruleForm).then((res) => {
						console.log(res.data)
						if (res.data.code == 200) {
							window.location.href = "../mdList.html"
							sessionStorage.setItem("userId", res.data.data.id)
						}
						layer.msg(res.data.message)
					})
				} else {
					console.log('error submit!!');
					return false;
				}
			});
		},
	}
})
