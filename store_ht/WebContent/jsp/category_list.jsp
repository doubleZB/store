<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
			<!-- 分类表格 -->
			<table id="category_list"></table>

			<!-- 对话框 -->
			<div id="category_dialog"></div>  

			<script>
				$('#category_list').datagrid({    
				    url:"${pageContext.request.contextPath}/category?method=findCategory",     // servlet的地址   返回json数据
				    
				    columns:
				    [[    
				        {field:'cid',title:'分类编号',width:100},    
				        {field:'cname',title:'分类名称',width:100}, 
				         {field:'hahaha',title:'操作',width:100,formatter: function(value,row,index)
																		{
				        	 												// value:代表field对应的值   cid对应的值
				        	 												// row:一行数据   {cid:值  cname:值}
				        	 												// inex:代表每一行的下角标
				        	 												return "<a href=# onclick=del('"+row.cid+"')>删除</a>&nbsp;&nbsp;&nbsp;<a href=# onclick=update('"+row.cid+"','"+row.cname+"')>修改</a>";                          
																		}
				         		
				         }
				    ]],
				    
				    pagination:true,
				    pageSize:5,
				    pageList:[5,10,15,20,25,30],
				    fit:true,
				    /* 工具栏 */
				    toolbar: 
				    [{
							iconCls: 'icon-edit',
							handler: function()
									{
										// 弹出一个对话框
										$("#category_dialog").dialog(
										{
											width:300,
											height:200,
											title:"添加",
											// 对话框中引入form表单
											href:"category_form.jsp"
										})
										
									}
						},'-',{
							iconCls: 'icon-man',
							handler: function(){alert('帮助按钮')}
					}]

				}); 
			</script>
			
			<script>
				function del(cid)
				{
					//ajax
					var url="${pageContext.request.contextPath}/category";
					params="method=delBycid&cid="+cid;
					$.post(url,params,function(d)
					{
						if("ok"==d)
						{
							//删除成功--右下角做成功提示
							$.messager.show({
								title:'删除分类消息',
								msg:'恭喜你,删除成功',
								timeout:3000,
								showType:'slide'
							});

							// 刷新表格数据
						$("#category_list").datagrid("reload",true);
							
						}else
						{
							//删除失败
							$.messager.alert('警告','不能删除该分类,该分类下还有商品');
						}
						
					})
				}
				
				
				
				function  update(cid,cname)
				{
					
					
					// 弹出一个对话框
					$("#category_dialog").dialog(
					{
						width:300,
						height:200,
						title:"修改",
						// 对话框中引入form表单
						href:"category_update.jsp?cid="+cid+"&cname="+cname
					})
				}
			</script>
</body>
</html>