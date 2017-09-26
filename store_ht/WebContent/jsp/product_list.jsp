<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
			<table id="product_list"></table>  
		
			<div id="product_dialog"></div>


			<script>
			$('#product_list').datagrid({    
			    url:'${pageContext.request.contextPath}/product?method=findProduct',     // 返回的json数据
			    
			    columns:
			    [[    													//value :"products/1/c_0001.jpg"
			          													// row: {"cateGory":{"cid":"1","cname":"手机数码"},"is_hot":1,"market_price":1399,"pdate":"2015-11-02","pdesc":"小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待","pflag":0,"pid":"1","pimage":"products/1/c_0001.jpg","pname":"小米 4c 标准版","shop_price":1299},{"cateGory":{"cid":"1","cname":"手机数码"}
			        {field:'pimage',title:'商品图片',width:100,formatter: function(value,row,index)		
															 {
	
			        													return "<img width=60 src=${pageContext.request.contextPath}/"+value+">";
															 }		
			        
			        },    
			        {field:'pid',title:'商品编号',width:100},    
			        {field:'pname',title:'商品名称',width:200}, 
			        {field:'shop_price',title:'商品价格',width:100}, 
			        {field:'pdate',title:'商品日期',width:100}, 
			        {field:'is_hot',title:'是否热门',width:100,formatter: function(value,row,index)
			        										{
			        											return "0"==value?"热门":"不热门";
			        										}
			        },
			        
			       	/* 所属分类  */												
			        {field:'cateGory',title:'所属分类',width:100,formatter: function(value,row,index)
			        										   {
			        										 		// ps: row对象会有6个,第一个row里面的所有内容都为null
			        												if(value!=null)
			        												{
			        													return row.cateGory.cname;
			        												}
			        												
			        										   }
			        }
			    ]],
			    
			    /* 分页 */
			    pagination:true,
			    pageSize:5,
			    pageList:[5,10,15,20,25,30],
			    fit:true,
			    /* 工具栏 */
			    toolbar: [{
					iconCls: 'icon-add',
					handler: function()
							{
								// 弹出对话框
								$("#product_dialog").dialog(
								{
									title:"添加商品",
									width:400,
									height:400,
									// 对话框中引入表单页面
									href:"product_form.jsp"
								})
								
								
							}
				},'-',{
					iconCls: 'icon-help',
					handler: function(){alert('帮助按钮')}
				}]

			    
			});  


			</script>
</body>
</html>