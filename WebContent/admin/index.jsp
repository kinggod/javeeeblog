<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>后台管理</title>
	<link rel="stylesheet" type="text/css" href="<s:url value='/common/themes/default/easyui.css'/>">
	<link rel="stylesheet" type="text/css" href="<s:url value='/common/themes/icon.css'/>">
	<link rel="stylesheet" type="text/css" href="<s:url value='/common/css/demo.css'/>">
	<script type="text/javascript" src="<s:url value='/common/js/jquery-1.11.2.min.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/common/js/jquery.easyui.min.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/common/js/admin.js'/>"></script>
</head>
<body id="cc" class="easyui-layout">
	<!-- 北部开始 -->
	<div data-options="region:'north',border:false" style="height:40px;background:#B3DFDA;padding:10px">
	<span style="float:right;padding-right:25px;font-size: 1.5em"><a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span> 
	<span><a href="<s:url value='/'/>" style="font-size: 1.5em">首页，欢迎您：${userName}</a></span>
	</div>
	<!-- 北部结束 -->
	<!-- 南部开始 -->
	<div data-options="region:'south',border:false" style="height:40px;background:#A9FACD;padding:10px;">south region</div>
	<!-- 南部结束 -->
	<!-- 东部开始 -->
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:150px;padding:10px;">east region</div>
	<!-- 东部结束 -->
	
	<!-- 导航开始 -->
	<div data-options="region:'west',split:true,title:'导航菜单'"  style="width:200px;padding:10px;">
    <div id="nav"></div>
	</div>  
	<!-- 导航结束 -->
	
	
	<!-- 主体开始 -->
	<div data-options="region:'center',title:'Center'">
		<div class="easyui-layout" data-options="fit:true">
			<!-- 副导航开始 -->
            <div data-options="region:'west',collapsed:true,title:'查看其他机构'" style="width:180px">什么都没有</div>
            <!-- 副导航结束-->
            <!-- 选项卡开始 -->
            <div data-options="region:'center'"  id="tabs" class="easyui-tabs">
	            <div title="使用指南"  data-options="iconCls:'icon-reload'" style="padding:20px;">
	            <h1>后台</h1>
				</div>
				<div title="使用指南2"  data-options="iconCls:'icon-reload'" style="padding:20px;"></div>
            </div>
             <!-- 选项卡开始 -->
	</div>
</div>
<!-- 主题内容结束 -->
<!-- 导航关闭区域-->
<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
<!-- 导航关闭区域结束-->	
<!-- 修改密码窗口 -->
<div id="w" class="easyui-window" title="修改密码"  data-options="collapsible:false,minimizable:false, maximizable:false,icon:'icon-save'"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" data-options="fit:true">
			<!-- 中心区域 -->
			<div data-options="region:'center',border:false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table style="padding:3px">
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<!-- 中心区域结束 -->
			<div data-options="region:'south', border:false" style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" data-options="icon:'icon-ok'" href="javascript:void(0)"> 确定</a> 
				<a id="btnCancel" class="easyui-linkbutton" data-options="icon:'icon-cancel'" href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>
<!-- 修改密码窗口结束 -->
	<script>
	var _menus = {
			"menus":[{"menuid" : "1", "icon" : "icon-sys","menuname" : "人员管理",
				"menus":[ 
						{"menuid" : "11","menuname" : "学生管理","icon" : "icon-users","url": "<s:url value='/admin/userinfo.jsp' />"},
						{"menuid" : "12","menuname" : "学管理","icon" : "icon-users","url": "${pageContext.request.contextPath}/admin/uso.jsp"}, 
						{"menuid" : "13","menuname" : "学生管理","icon" : "icon-users","url": "${pageContext.request.contextPath}/admin/userinfo.jsp"}
						]},
			{"menuid" : "2", "icon" : "icon-sys","menuname" : "人员管理",
					"menus":[ 
						{"menuid" : "21","menuname" : "学生管理","icon" : "icon-users","url": "${pageContext.request.contextPath}/admin/userinfo.jsp"},
						{"menuid" : "22","menuname" : "学生管理","icon" : "icon-users","url": "${pageContext.request.contextPath}/admin/userinfo.jsp"}, 
						{"menuid" : "23","menuname" : "学生管理","icon" : "icon-users","url": "${pageContext.request.contextPath}/admin/userinfo.jsp"}
						]}					
			]}
	//设置登录窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
	}
	//关闭修改密码窗口
	function closePwd() {
		$('#w').window('close');
	}
	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');
		if(($newpass.val().length<5)||($newpass.val().length>12)){
			msgShow('系统提示', '密码5-12位！', 'warning');
			return false;
		}
		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请在一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}
		/* AJAX修改密码 */
		$.post('${pageContext.request.contextPath}/Index?method=updatepasswd&newpassword='
				+ $newpass.val(), function(msg) {
			if(msg=="true"){
			msgShow('系统提示', '恭喜，密码修改成功！' ,
					'info');}
			else{
				alert("修改失败");
			}
			$newpass.val('');
			$rePass.val('');
			parent.$('#w').window('close');
		})

	}
	
	$(function() {
		openPwd();
		$('#editpass').click(function() {
			$('#w').window('open');
		});

		$('#btnEp').click(function() {
			serverLogin();
		})

		$('#btnCancel').click(function() {
			closePwd();
		})

		$('#loginOut').click(function() {
			$.messager.confirm('系统提示','您确定要退出本次登录吗?',
			function(r) {
				if (r) {location.href = '${pageContext.request.contextPath}/Index?method=logout';}
								});
						})
	});
	</script>
	<script>
     $("#clickMe").click(function(){  
     var url = "<s:url value='/listuser' />";  
     $.ajax({
           type:'get',
           url:url,
           dataType: 'json',
            success:function(data){  
                $.each(data,function(i,list){
                    var _tr = $("<tr><td>"+list.id+"</td><td>"+list.userName+"</td><td>"+list.password+"</td></tr>");  
                    $("#showTable").append(_tr);  
                   })  
               }  
         })  
       })  
    </script>
</body>
</html>