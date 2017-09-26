<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
</head>
<body>
	<script>
			function addTabFn(info,ym)
			{
				// 先判断有米有该选项卡
				var flg=$("#tabss").tabs("exists",info);
				if(flg)
				{
					
					$("#tabss").tabs("select",info);
					
				}else
				{
					// 没有--创建
					$("#tabss").tabs("add",
					{
						title:info,
						closable:true,
						href:ym
					})
				}
				
				
				
			}
	</script>




	<div id="cc" class="easyui-layout" data-options="fit:true">   
	    <div data-options="region:'north',title:'北',split:true" style="height:200px;padding:25px;text-align: center">
	    		<font style="font-size: 45px">网 上 商城 后 台 管 理 系 统</font>
	    </div>    
	    
	    <div data-options="region:'west',title:'西',split:true" style="width:350px;">
	    	
	    	<!-- 折叠窗 -->
		    	<div id="aa" class="easyui-accordion" data-options="fit:true,border:0">   
				    <div title="JAVA">   
				           <!-- 树 -->
				           <ul id="tt" class="easyui-tree">   
							    <li>   
							        <span>商品分类管理</span>
							        <ul>
							        	<li>
							        		<span><a href="#" onclick="addTabFn('商品类别展示','category_list.jsp')">商品分类列表</a></span>
							        	</li>
							        </ul>   
							    </li>   
							   
								<li>   
							        <span>商品信息管理</span>  
							        <ul>
							        	<li>
							        		<span><a href="#" onclick="addTabFn('商品信息展示','product_list.jsp')">商品信息列表</a></span>
							        	</li>
							        </ul> 
							    </li>   
							</ul>        
				    </div>   
				    <div title="C++">   
				        2222222   
				    </div>   
				    <div title="UI">   
				       3333333    
				    </div>   
				</div> 	
	   </div>   
	    
	    
	    <div data-options="region:'center'" >
	    	<!-- 选项卡 -->
	    	<div id="tabss" class="easyui-tabs" data-options="fit:true,border:0">   
				    <div title="欢迎页面">   
				           	欢迎您的登录~~~
				    </div>   
			</div> 	
	    
	    </div>   
	</div>  	
</body>
</html>