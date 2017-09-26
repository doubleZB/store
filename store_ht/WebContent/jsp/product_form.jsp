<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form id="productform" action="${pageContext.request.contextPath}/addProduct" method="post" enctype="multipart/form-data">
	    	<table cellpadding="5" style="padding:10px">
	    		<tr>
	    			<td>商品名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="pname" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>市场价格:</td>
	    			<td><input class="easyui-textbox" type="text" name="market_price" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>商城价格:</td>
	    			<td><input class="easyui-textbox" type="text" name="shop_price" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>上传:</td>
	    			<td><input class="easyui-filebox" name="upload" data-options="buttonIcon:'icon-add',buttonText:''" style="width:200px"></input></td>
	    		</tr>
	    		<tr>
	    			<td>是否热门:</td>
	    			<td>								
	    				<select class="easyui-combobox" name="is_hot" data-options="panelHeight:'auto'">
	    						<option value="0">是</option>
	    						<option value="1">否</option>
	    				</select>
		    		</td>
	    		</tr>
	    		<tr>
	    			<td>所属分类:</td>
	    			<td>								
		    			<input id="product_cid" name="cid" value="--请选择--">	
		    			 <script>
							   $('#product_cid').combobox
								({    
								    url:'${pageContext.request.contextPath}/product?method=findCategory',      //servlet的路径  返回的是json数据
								    valueField:'cid',  //<option value='1'><option>   		// 对应的是返回json数据的key 根据key会自动选择value  放在<option value=?> 
								    textField:'cname'  //<option value='1'>手机数码<option>   			// 对应的是返回json数据的key 根据key会自动选择value  放在<option>?</option>
								});  
						</script>  
		    		</td>
	    		</tr>
	    		<tr>
	    			<td>商品描述:</td>
	    			<td><input class="easyui-textbox" name="pdesc" data-options="multiline:true" style="height:60px"></input></td>
	    		</tr>
	    		</table>
	   </form>
	  <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
	    </div>
	    </div>
	</div>
	<script>
	function submitForm(){
		$('#productform').form('submit',
		{
			success: function(d)
			{
				// 关闭对话框
				$("#product_dialog").dialog("close","close");
				// 右下角做提示
				$.messager.show({
							title:'添加商品消息',
							msg:'恭喜你,添加成功',
							timeout:3000,
							showType:'slide'
						});

				// 刷新表格数据
				$("#product_list").datagrid("reload",true);
				
			}
		});
	}
	function clearForm(){
		$('#productform').form('clear');
	}
	</script>
	  
</body>
</html>