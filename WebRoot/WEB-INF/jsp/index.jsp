<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>腿哥商城</title>
<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="腿哥商城"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="./image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	
</div>
	<%@ include file="menu.jsp" %>
</div>	
<div class="container index">
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
					<div class="title">
						<strong>热门图书</strong>
						<!-- <a  target="_blank"></a> -->
					</div>
					<ul class="tab">
					</ul>
						<ul class="tabContent" style="display: block;">
							<s:iterator var="p" value="hList">
									<li>
										<a href="${ pageContext.request.contextPath }/product_findByPid.action?pid=<s:property value="#p.pid"/>" target="_blank"><img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" data-original="http://storage.shopxx.net/demo-image/3.0/201301/0ff130db-0a1b-4b8d-a918-ed9016317009-thumbnail.jpg" style="display: block;"></a>
									</li>
							</s:iterator>		
						</ul>
						<ul class="tabContent" style="display: none;">
						</ul>
						<ul class="tabContent" style="display: none;">
						</ul>
			</div>
		</div>
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
					<div class="title">
						<strong>最新上架</strong>
						<a  target="_blank"></a>
					</div>
					<ul class="tab">
							<li class="current">
							</li>
							<li>
								<a  target="_blank"></a>
							</li>
							<li>
								<a target="_blank"></a>
							</li>
					</ul>
<!-- 					<div class="newProductAd">
									<img src="${pageContext.request.contextPath}/image/q.jpg" width="260" height="343" alt="最新商品" title="最新商品">
						</div>
						 -->						
					<ul class="tabContent" style="display: block;">
						 	<s:iterator var="p" value="nList">
									<li>
										<a href="${ pageContext.request.contextPath }/product_findByPid.action?pid=<s:property value="#p.pid"/>" target="_blank"><img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" data-original="http://storage.shopxx.net/demo-image/3.0/201301/4a51167a-89d5-4710-aca2-7c76edc355b8-thumbnail.jpg" style="display: block;"></a>									</li>
									</li>
							</s:iterator>		
						</ul>
						<ul class="tabContent" style="display: none;">
						</ul>
						<ul class="tabContent" style="display: none;">
						</ul>
			</div>
		</div>
		<div class="span24">
			
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
		</div>	
	</div>
<div class="span24">
		<ul class="bottomNav">
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 1997-2019 腿哥书城 版权所有</div>
	</div>
</div>
</body>
</html>