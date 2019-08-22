<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>CMS系统</title>

<!-- Bootstrap -->
<!-- <link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.css" /> -->
<link rel="stylesheet"
	href="/resource/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/resource/css/cms.css?v=1.1" />
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<div>
		<br />
	</div>
	<div class="container">
		<div class="row">
			<!--所有栏目 -->
			<div class="col-md-2 ">
				<ul class="list-group">
					<!-- 固定信息 -->
					<li class="list-group-item  text-center"><a class="channel"
						href="/">热门</a></li>
					<!-- 所有的栏目，点击每个栏目进入到对应的种类中去 -->
					<c:forEach var="link" items="${links}">
						<li class="list-group-item text-center"><a
							href="${pageContext.request.contextPath}/index?channelId=${link.id}"
							class="channel">${link.name}</a></li>
					</c:forEach>
				</ul>
			</div>
			<!-- 主体区 -->
			<div class="col-md-7 split min_h_500">
				<c:choose>
					<c:when test="${channelId==-1}">
						<!-- 默认显示图片轮播+热点内容 -->
						<div id="carousel" class="carousel slide" data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carousel" data-slide-to="0" class="active"></li>
								<li data-target="#carousel" data-slide-to="1"></li>
								<li data-target="#carousel" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner">
								<div class="carousel-item active">
									<img class="d-block w-100"
										src="${pageContext.request.contextPath}/upload/1.jpg"
										alt="First slide">
								</div>
								<div class="carousel-item">
									<img class="d-block w-100"
										src="${pageContext.request.contextPath}/upload/2.jpg"
										alt="Second slide">
								</div>
								<div class="carousel-item">
									<img class="d-block w-100"
										src="${pageContext.request.contextPath}/upload/3.jpg"
										alt="Third slide">
								</div>
							</div>
							<a class="carousel-control-prev" href="#carousel" role="button"
								data-slide="prev"> <span class="carousel-control-prev-icon"
								aria-hidden="true"></span> <span class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carousel" role="button"
								data-slide="next"> <span class="carousel-control-next-icon"
								aria-hidden="true"></span> <span class="sr-only">Next</span>
							</a>
						</div>
						<div id="hot">
							<!-- 热门文章 -->
							<c:forEach var="entity" items="${pageInfo.content}">
								<div class="media">
									<img class="align-self-start mr-3" width="100px" height="80px"
										src="${entity.picture!=null?entity.picture:'/upload/01.jpg' }"
										alt="no pic">
									<div class="media-body">
										<h5 class="mt-0">
											<a href="javascript:toDetail(${entity.id })">${entity.title }</a>
										</h5>
										<c:if test="${fn:length(entity.content)>30}">
								       ${fn:substring(entity.content,0,30)}...
								  </c:if>
										<c:if test="${fn:length(entity.content)<=30}">
								       ${entity.content}
								  </c:if>
										<div class="blog_item_footer">
											<span class="glyphicon glyphicon-user" title="作者"></span>作者：${entity.userName }&nbsp;&nbsp;
											&nbsp; <span class="glyphicon glyphicon-time" title="发布时间">
											</span>发布：
											<fmt:formatDate value="${entity.created}"
												pattern="yyyy年MM月dd日" />
											&nbsp;前&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
									</div>
								</div>
							</c:forEach>
							<br />
							<!-- 分页 -->
							<nav>
								<ul class="pagination">
							      <li class="page-item"><a class="page-link"
											href="${pageContext.request.contextPath}/index?pageNum=1&channelId=${channelId}">首页</a>
										</li>
									<c:if test="${pageInfo.number>0}">
										<li class="page-item"><a class="page-link"
											href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.number-1}&channelId=${channelId}">上一页</a>
										</li>
									</c:if>
									<c:if test="${pageInfo.number<pageInfo.totalPages}">
										<li class="page-item"><a class="page-link"
											href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.number+1}&channelId=${channelId}">下一页</a>
										</li>
									</c:if>
									  <li class="page-item"><a class="page-link"
											href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.totalPages}&channelId=${channelId}">尾页</a>
									</li>
								</ul>
							</nav>
						</div>
					</c:when>
					<c:otherwise>
						<div id="category">
							<!-- 显示上部的导航效果 -->
							<div>
								<ul class="nav nav-pills">
									<c:forEach items="${categories}" var="category">
									    
										<li class="nav-item">
										 <a class="nav-link"
											href="${pageContext.request.contextPath}/index?channelId=${channelId}&categoryId=${category.id}">${category.name }</a>
										</li>
									</c:forEach>
								</ul>
							</div>
							<!-- 缺省文章 -->
							<c:forEach var="entity" items="${pageInfo.content}">
								<div class="media">
									<img class="align-self-start mr-3" width="100px" height="80px"
										src="${entity.picture!=null?entity.picture:'/upload/01.jpg' }"
										alt="no pic">
									<div class="media-body">
										<h5 class="mt-0">
											<a href="javascript:toDetail(${entity.id })">${entity.title }</a>
										</h5>
										<c:if test="${fn:length(entity.content)>30}">
								       ${fn:substring(entity.content,0,30)}...
								  </c:if>
										<c:if test="${fn:length(entity.content)<=30}">
								       ${entity.content}
								  </c:if>
										<div class="blog_item_footer">
											<span class="glyphicon glyphicon-user" title="作者"></span>作者：${entity.userName }&nbsp;&nbsp;
											&nbsp; <span class="glyphicon glyphicon-time" title="发布时间">
											</span>发布：
											<fmt:formatDate value="${entity.created}"
												pattern="yyyy年MM月dd日" />
											&nbsp;前&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
									</div>
								</div>
							</c:forEach>
							<br />
							<!-- 分页 -->
							<nav>
								<ul class="pagination">
								      <li class="page-item"><a class="page-link"
											href="${pageContext.request.contextPath}/index?pageNum=1&channelId=${channelId}">首页</a>
										</li>
									<c:if test="${pageInfo.number>0}">
										<li class="page-item"><a class="page-link"
											href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.number-1}&channelId=${channelId}">上一页</a>
										</li>
									</c:if>
									<c:if test="${pageInfo.number<pageInfo.totalPages}">
										<li class="page-item"><a class="page-link"
											href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.number+1}&channelId=${channelId}">下一页</a>
										</li>
									</c:if>
									  <li class="page-item"><a class="page-link"
											href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.totalPages}&channelId=${channelId}">尾页</a>
									</li>
								</ul>
							</nav>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-md-3">
				<div class="card">
					<div class="card-header">最新文章</div>
					<div class="card-body">
						<ol>
							<li class="text-truncate"><a href="#">最新文章</a></li>
						</ol>
					</div>
				</div>
				<div class="card">
					<div class="card-header">友情链接</div>
					<ol>
						<li class="text-truncate text-center"><a href="#"
							target="_blank">友情连接</a></li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<br />
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
</body>
</html>