<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>用户登陆</title>
<script type="text/javascript">    
function changeValidateCode(obj) {    
var timenow = new Date().getTime();    
obj.src="randPic.action?d="+timenow;    
}    
</script>
<style type="text/css">
body{
background-image: url(<s:url value='/common/image/login.jpg' />);
}
p{color:red}
#head {
	border:2px dotted gray;
	margin: 10% auto;
	width:500px;
	text-align:left;
	padding: 15px;
	background: pink;
}
.grid{
	margin: 5px;
	font-size: 1.1em;
}
.right{
text-align: center;}
input {
	font-size:1.1em;
}
</style>
</head>
<body>
<div id="head">
	<h1>用户登陆</h1>
	<form action="<s:url value='/userlogin' />" method="post">
	<div class="grid">
		<label>用户名:</label>
		<input type="text" name="userName" autofocus required>
	</div>
	<div class="grid">
		 <label>密&nbsp;&nbsp;码:</label>
		<input type="password" name="password1" autofocus required>
	</div>
	<div class="grid">
		<label>验证码:</label>
		<input type="text" name="code" autofocus required>
		<img src="randPic" onclick="changeValidateCode(this)" title="点击图片刷新验证码" />
	</div>
	<div class="grid right">
		<input type="submit" value="submit"> 
		<input type="reset" value="reset">
		<p>${message}</p>
	</div>
	</form>
</div>
</body>
</html>