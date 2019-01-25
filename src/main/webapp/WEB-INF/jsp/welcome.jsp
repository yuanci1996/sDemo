<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<!-- 引入样式 -->
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<!-- 引入组件库 -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script type="text/javascript">
var power="${sessionScope.user.account}"; 
</script>
</head>
<body>
<h2>Hello World!</h2>
<a href="${pageContext.request.contextPath}/logout.do">logout</a>
<input id="sds">
<div> username : <sec:authentication property="name"/></div>
</body>
</html>
