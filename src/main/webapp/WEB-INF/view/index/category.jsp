<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.mlink{
    display: block;
     .5rem 1rem;
     padding-top: 0.5rem;
    padding-right: 1rem;
    padding-bottom: 0.5rem;
    padding-left: 1rem

}
</style>
<div id="channel_article">
<ul class="nav">

	<!--栏目下所有 分类 -->
	<li class="nav-item  list-group-item-success"><a class="mlink"
		href="javascript:void(0)" data="/getCategoryList?cid=${cid }">全部</a></li>
	<c:forEach items="${list}" var="c">

		<li class="nav-item "><a class="nav-link"
			href="javascript:void(0)"
			data="/article/selectListByCatId?catid=${c.id}">${c.name }</a></li>
	</c:forEach>

	<!-- 所有栏目下的文章 -->
	<div id="content-wrapper">
		<ul class="list-unstyled">
			<hr>
			<!-- 栏目下所有文章 -->
			<c:forEach items="${articleList }" var="a">
				<li class="media"><img class="mr-3" src="/pic/${a.picture }"
					alt="no pic">
					<div class="media-body">
						<h5 class="mt-0 mb-1">
							<small><a href="javascript:myopen(${a.id })">
									${a.title }</a></small>
						</h5>
						<br> <br>
						<h5 class="mt-0 mb-1">
							<small> ${a.username } &nbsp; ${a.created }</small>
						</h5>
					</div></li>
				<hr>
			</c:forEach>
		</ul>
</ul>
</div>
</div>
<script type="text/javascript">
 $(function(){
	   //当点击左侧菜单时  加载url
    $('.nav-link').click(function (e) {
      	  //获取点击的的url
          var url = $(this).attr('data');
      	 // console.log(url);
         //在中间区域显示地址的内容
         $('#content-wrapper').load(url);
      });
    //当点击左侧菜单时  加载url
    $('.mlink').click(function (e) {
      	  //获取点击的的url
          var url = $(this).attr('data');
      	 // console.log(url);
         //在中间区域显示地址的内容
         $('#channel_article').load(url);
      });
}) 
	function myopen(id) {
		//在新窗口打开文章的详情
		window.open("/article/index/getDetail?id=" + id, "_blank")
	}

</script>
<script type="text/javascript" src="/resource/js/cms.js"></script>
<script src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>