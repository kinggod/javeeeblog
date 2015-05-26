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
				var uri='/gadi/UserAdmin?method=deleteuser&loginName='+row.loginName;
				$.post(uri,function(result){
					if (result=="true"){
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
	url = '/gadi/UserAdmin?method=adduser';
}

function editUser(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$('#dlg').dialog('open').dialog('setTitle','编辑用户');
		$('#fm').form('load',row);
		url = '/gadi/UserAdmin?method=edituser';
	}
}
	
	function saveUser(){
		$('#fm').form('submit',{
			url: url,
			success: function(result){
				if (result!="true"){
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
<!-- <script type="text/javascript">
	$(function(){
		$('#userTable').datagrid({  
		/* 		fit:true,*/fitColumns:true,	 
			title:"用户信息表",
			iconCls:"icon-search",
		    url:'${pageContext.request.contextPath}/Index?method=queryAllUser',    
		    pagination:true,
		    pageList:[5,10,30],
		    singleSelect:false,
		    rownumbers:true,
		    toolbar: [{
				iconCls: 'icon-add',
				text:"添加",
				handler: function(){alert('添加数据')}
			},'-',{
				iconCls: 'icon-edit',
				text:"修改",
				handler: function(){alert('修改')}
			},'-',{
				iconCls: 'icon-remove',
				text:"删除",
				handler: function(){alert('删除')}
			}],
		    columns:[[    
		        {field:'ck',checkbox:true},    
		        {field:'userName',title:'用户名'},    
		        {field:'loginName',title:'账号'},
		        {field:'sex',title:'性别'},
		        {field:'born',title:'出生日期 '},
		        {field:'age',title:'年龄'},
		        {field:'grade',title:'年级'},
		        {field:'tel',title:'手机'},
		        {field:'qq',title:'QQ'},
		        {field:'date',title:'注册时间'},
		        {field:'other',title:'描述'}
		    ]]
		}); 
	});

</script> -->
</head>
<body>
<!-- 	<table id="userTable"></table>   -->
  	<table id="dg" title="学生管理" class="easyui-datagrid" style="width:550px;height:250px"
		url='${pageContext.request.contextPath}/Index?method=queryAllUser'
		toolbar="#toolbar"
		rownumbers="true" fitColumns="true" singleSelect="true"  pagination="true"
		    pageList="[5,10,30]" fit="true">
	<thead>
		<tr>
			<th field='ck' checkbox="true"></th>
			<th field='userName' title='用户名'>用户名</th>
		    <th field='loginName' title='账号'>账号</th>
		    <th field='sex' title='性别'>性别</th>
		    <th field='born' title='出生日期 '>出生日期</th>
		    <th field='age' title='年龄'>年龄</th>
		    <th field='grade' title='年级'>年级</th>
		    <th field='tel' title='手机'>手机</th>
		    <th field='qq' title='QQ'>QQ</th>
		    <th field='date' title='注册时间'>注册时间</th>
		    <th field='other' title='描述'>描述</th>
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
			<label>账号</label>
			<input type="text" name="loginName" id="loginName"/>
		</div>
		<div class="fitem">	
				<label>用户名</label>>
				<input type="text" name="userName" id="userName" />
		</div>
		<div class="fitem">
		<label>性别</label>>
		<input type="radio" name="sex" value="男" checked="checked"/>男 <input type="radio" name="sex" value="女"/>女
		</div>
		<div class="fitem">	
				<label>出生日期:</label>>
				<input type="text" id="born" name="born"  />
		</div>
		<div class="fitem">	
				<label>年龄(周岁)</label>>
				<input type="text" name="age" id="age" />
		</div>
		<div class="fitem">	
				<label>你的学历</label>>
				<select name="grade" id="grade">
          <option value="">－－请选择你的学历－－</option>
          <option value="小学">小学</option>
          <option value="初中">初中</option>
          <option value="高中">高中</option>
          <option value="大学">大学</option>
          <option value="不明生物">不明生物</option>
        </select>
		</div>
		<div class="fitem">	
				<label>手机或电话:</label>>
				<input type="text" id="tel" name="tel" />
		</div>
		<div class="fitem">	
				<label>QQ号码:</label>>
			<input type="text" id="qq" name="qq"/>
		</div>
		<div class="fitem">	
				<label>个人描述</label>>
			 <textarea id="other" name="other" cols="12" rows="10"></textarea> 
		</div>
		<div class="fitem">
		<label>密码(编辑用户可以不输密码)</label>
		<input type="password" name="passwordSrc" id="passwordSrc"/>
		</div>
		
	</form>
</div>
<div id="dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>
</body>
</html>