<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<!-- 引入样式 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<!-- 引入组件库 -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<h2>Hello World!</h2>
<a href="${pageContext.request.contextPath}/logout.do">logout</a>
<input id="sds">
<div> username : <sec:authentication property="name"/></div>

<br/><br/><br/>
用户姓名：<input type="text" id="username" />
用户密码：<input type="text" id="password" />
用户性别：<input type="text" id="sex" />
 <input type="button" value="推送用户信息" id="pushUser" /> 
 

<script type="text/javascript">
	$("#pushUser").click(function(){
		var data = {
				username : $("#username").val(),
				password : $("#password").val(),
				sex 	 : $("#sex").val()
		};
		ajaxDo("/sDemo/push/user",data);
	});
	
function ajaxDo(url,data){
	 $.ajax({
	        url:url ,
	        type: "post",
	        dataType: "json",
	        data: data,
	        success:function(result){
	           if(result.success){
	        	   var obj = JSON.stringify(result.data);
	        	   alert(obj);
	           }else{
	        	   alert(result.msg);
	           }
	        }
	    });
}	
 
</script>
<script type="text/javascript">
 //测试controller是否可以进入
// ajaxDo("/activemq-client/index",null);
 
// function ajaxDo(url,data){
// 	 $.ajax({
// 	        url:url ,
// 	        type: "post",
// 	        dataType: "json",
// 	        data: data,
// 	        success:function(result){
// 	           if(result.success){
// 	        	   alert(result.data);
// 	           }else{
// 	        	   alert(result.msg);
// 	           }
// 	        }
// 	    });
// }	
 
 
//--------------------------------- webSocket ----------------------------------------------
  initSocket("user");
  
 
function initSocket(myWebsocket) {
	
	var webSocket = null;
	
    window.onbeforeunload = function () {
        //离开页面时的其他操作
    };
 
    if (!window.WebSocket) {
        console("您的浏览器不支持websocket！");
        return false;
    }
 
    var target = 'ws://' + window.location.host + "/sDemo/websocket/"+myWebsocket;  
		  
		if ('WebSocket' in window) {  
			webSocket = new WebSocket(target);  
		} else if ('MozWebSocket' in window) {  
			webSocket = new MozWebSocket(target);  
		} else {  
		    alert('WebSocket is not supported by this browser.');  
		    return;  
		}  
    
    
    // 收到服务端消息
    webSocket.onmessage = function (msg) {
            alert(msg.data);
        // 关闭连接
        webSocket.onclose();
        console.log(msg);
    };
 
    // 异常
    webSocket.onerror = function (event) {
        console.log(event);
    };
 
    // 建立连接
    webSocket.onopen = function (event) {
        console.log(event);
    };
 
    // 断线
    webSocket.onclose = function () {
		
        console.log("websocket断开连接");
    };
}
 
 
</script>
</body>
</html>
