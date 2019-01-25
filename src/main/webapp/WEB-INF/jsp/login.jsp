<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入样式 -->
  <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
  <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
  <style>
.el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
	.login-box {
		margin-top:20%;
		margin-left:35%;
	}
</style>
</head>
<body>
<div class="login-box" id="app" >
 <el-row>
	<el-col :span="8">
		<el-input id="name"  v-model="name" placeholder="请输入帐号" @keyup.enter="check"> 
			<template slot="prepend">帐号</template>
		</el-input> 
	</el-col>
 </el-row>
 <el-row>
	<el-col :span="8">
		<el-input id="password" v-model="password" type="password" placeholder="请输入密码" @keyup.enter="check">
			<template slot="prepend">密码</template>
		</el-input>
	</el-col>
 </el-row>
 <el-row>
	<el-col :span="8">
		<el-button id="login" v-on:click="check" style="width:100%" type="primary">登录</el-button>
	</el-col>
 </el-row>
</div> 

</body>
  <!-- import Vue before Element -->
  <script src="https://unpkg.com/vue/dist/vue.js"></script>
  <!-- import JavaScript -->
  <script src="https://unpkg.com/element-ui/lib/index.js"></script>
  <script type="text/javascript">
  new Vue({
		el : '#app',
		data : {
			name : '',
			password : ''
		},
		methods : {
			check : function(event){
				//获取值
				var name = this.name;
				var password = this.password;
				if(name == '' || password == ''){
					this.$message({
						message : '账号或密码为空！',
						type : 'error'
					})
					return;
				}
				$.ajax({
					url : '${pageContext.request.contextPath}/login.do',
					type : 'post',
					data : {
						username : name,
						password : password
					},
					dataType : 'json',
				   success :function(data){
					   if (data) {
						   window.location.href = '${pageContext.request.contextPath}/user/main.html'
						} else {
							   this.$message({
									message : '账号或密码错误！',
									type : 'error'
								})
								return;
						} 
				  },
				   error:function(){
				
				   }
			});
			}
		}
	})

  </script>