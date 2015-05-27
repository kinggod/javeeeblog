<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>用户管理</title>
	<link rel="stylesheet" type="text/css" href="<s:url value='/common/themes/default/easyui.css'/>">
	<link rel="stylesheet" type="text/css" href="<s:url value='/common/themes/icon.css'/>">
	<link rel="stylesheet" type="text/css" href="<s:url value='/common/css/demo.css'/>">
	<script type="text/javascript" src="<s:url value='/common/js/jquery-1.11.2.min.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/common/js/jquery.easyui.min.js'/>"></script>
</head>
<script type="text/javascript">
function destroyUser(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
			if (r){
				var uri="<s:url value='/deleteuser'/>";
				$.post(uri,{'id':row.id},function(result){
					if (result=="success"){
						$('#dg').datagrid('reload');	// reload the user da
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: "删除失败"
						});
					}
				});
			}
		});
	}
}
function newUser(){
	$('#dlg').dialog('open').dialog('setTitle','添加用户');
	$('#fm').form('clear');
	url = "<s:url value='/adduser'/>";
}

function editUser(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$('#dlg').dialog('open').dialog('setTitle','编辑用户');
		$('#fm').form('load',row);
		url = "<s:url value='/adduser'/>";
	}
}
	
	function saveUser(){
		$('#fm').form('submit',{
			url: url,
			success: function(result){
				if (result!="success"){
					$.messager.show({
						title: 'Error',
						msg: "保存失败"
					});
				} else {
					$('#dlg').dialog('close');		// close the dialog
					$('#dg').datagrid('reload');	// reload the user data
				}
			}
		});
	}
</script>
</head>
<body>
<!-- 	<table id="userTable"></table>   -->
  	<table id="dg" title="学生管理" class="easyui-datagrid" style="width:550px;height:250px"
		url="<s:url value='listuser'/>" 
		toolbar="#toolbar" rownumbers="true" fitColumns="true" singleSelect="true"  pagination="true"
		    pageList="[5,10,30]" fit="true">
	<thead>
		<tr>
			<th field='ck' checkbox="true"></th>
			<th field='id' title='用户ID' width="100">用户ID</th>
			<th field='userName' title='用户名' width="100">用户名</th>
		    <th field='password' title='密码' >密码</th>
		</tr>
	</thead>
</table>
<div id="toolbar">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
</div>
<!--表单下面  -->
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
	<div class="ftitle">用户信息</div>
	<form id="fm" method="post">
		<div class="fitem">
			<input type="hidden" name="id"  />
		</div>
		<div class="fitem">	
				<label>用户名</label>
				<input type="text" name="userName" />
		</div>
		<div class="fitem">	
				<label>密码</label>
				<input type="password" name="password1" />
		</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>
</body>
</html>