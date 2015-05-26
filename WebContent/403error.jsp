<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>403Error</title>
<script>     
function countDown(secs,surl){     
 var jumpTo = document.getElementById('jumpTo');
 jumpTo.innerHTML=secs;  
 if(--secs>0){     
     setTimeout("countDown("+secs+",'"+surl+"')",5000);     
     }     
 else{       
     location.href=surl;     
     }     
 }     
</script> 
</head>
<body>
<div style="width:60%;margin:0 auto">
<h1>403错误:你没有登陆或者没有权限访问该页面～～～</h1>
<h1>那么既然错了那就留下来看看图片吧～</h1>
<h1><span id="jumpTo" style="color:red">3</span>秒后自动跳转到登陆页面</h1>
<div style="width:60%;margin:0 auto"><img src="<s:url value='/common/image/contain.jpeg'/>" alt="Logo"></div>
</div>
<script>countDown(3,'<s:url value="/home/login.jsp"/>');</script>
</html>