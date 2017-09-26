<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>


<body>
	    <form id="fffff" action="${pageContext.request.contextPath}/category" method="post" style="padding:25px">
	    	<input type="hidden" name="method" value="updateCategory">
	    	<input type="hidden" name="cid" value=<%=request.getParameter("cid") %>>
	    	<table cellpadding="5">
	    		<tr>
	    			<td>Name:</td>
	    			<td><input class="easyui-textbox" type="text" name="cname" value=<%=request.getParameter("cname") %>  data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
     
     <div style="text-align:center;padding:5px">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">修改</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
    </div>
	
	<script>
		function submitForm(){
			$('#fffff').form('submit',
			{
				success: function(d)
				{
					// 关闭对话框
					$("#category_dialog").dialog("close","close");
					// 右下角做提示
					$.messager.show({
								title:'修改分类消息',
								msg:'恭喜你,修改成功',
								timeout:3000,
								showType:'slide'
							});

					// 刷新表格数据
					$("#category_list").datagrid("reload",true);
				}

			})
		}
		function clearForm(){
			$('#fffff').form('clear');
		}
	</script>
</body>
</html>