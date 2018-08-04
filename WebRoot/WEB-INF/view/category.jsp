<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>欢迎使用氢博客</title>
    <!-- Bootstrap -->
    <link href="../../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="../../vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="../../vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet" />
    <!-- bootstrap-daterangepicker -->  
    <link href="../../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="../../build/css/custom.min.css" rel="stylesheet">
    <style type="text/css">
        .toolbar {
            border: 1px solid #ccc;
        }
        .text {
            border: 1px solid #ccc;
            height: 370px;
        }
    </style>
</head>

<body class="nav-md footer_fixed">
    <div class="container body">
        <div class="main_container">
            <div class="col-md-3 left_col menu_fixed ">
                <div class="left_col scroll-view">
                    <div class="navbar nav_title" style="border: 0;">
                        <a href="../../index/${user.userName}" id="userIndex" class="site_title" >
                            <i class="fa fa-h-square"></i><span>H<span style="font-size: 10px">2</span> Blog~!</span>
                        </a>
                    </div>
                    <div class="clearfix"></div>
                    <!-- menu profile quick info -->
                    <div class="profile clearfix">
                        <div class="profile_pic">
                            <img src="../../${user.userAvatar }" alt="..." class="img-circle profile_img">
                        </div>
                        <div class="profile_info">
                            <span>欢迎来到<br><h2>${user.userNickName }</h2>的氢博客</span>
                        </div>
                    </div>
                    <!-- /menu profile quick info -->
                    <br />
                    <!-- sidebar menu -->
                    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                        <div class="menu_section">
                            <h3>${user.userNickName }的博客</h3>
                            
                            <ul class="nav side-menu">
                                <li>
                                	<a href="../../index/${user.userName}" class="allBlogMenu"><i class="fa fa-home"></i> 首　　页 </a>
                                </li>
                                <li><a><i class="fa fa-align-left"></i> 博文分类 <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu" id="articleManagement" >
                                    	<c:forEach items="${categoryList}" var="pCategory">
	                                        <li>
	                                        	<a class="blogManage" id="${pCategory.categoryId}">${ pCategory.categoryName}
	                                        		<c:if test="${fn:length(pCategory.childCategoryList)>0}">
	                                        			<span class="fa fa-chevron-down"></span>
	                                        		</c:if>
	                                        	</a>
	                                        	<c:if test="${fn:length(pCategory.childCategoryList)>0}">
							                        <ul class="nav child_menu" >
							                        	<c:forEach items="${pCategory.childCategoryList}" var="cCategory" >
									                        <li >
									                        	<a href="${cCategory.categoryId}" class="blogManage" id="${cCategory.categoryId}">${cCategory.categoryName }</a>
									                        </li>
								                        </c:forEach>
							                        </ul>
						                        </c:if>
	                                        </li>
	                                     </c:forEach>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- /sidebar menu -->
                </div>
            </div>
            <!-- top navigation -->
            <div class="top_nav">
                <div class="nav_menu">
                    <nav>
                        <div class="nav toggle">
                            <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                        </div>
                    </nav>
                </div>
            </div>
            <!-- /top navigation -->
            <!-- page content -->
            <!-- /index -->
            <div class="right_col" role="main" id="indexContent" >
                
                    <!-- 最新发布的文章 -->
                    <div style="width: 73%;float: left;background-color: #FFF;border:1px solid rgb(220,220,220);border-radius:5px;padding: 20px">
                        <div>
                            <h2>分类名：${category.categoryName}</h2>
                            <br>
                        </div>
                        <c:set var="index" value="0"></c:set>
                        <c:if test="${empty articles }">
                        	<h3>该分类没有文章</h3>
                        </c:if>
                        <c:forEach items="${articles }" var="article">
	                        <div style="padding: 10px;border:1px solid rgb(220,220,220);margin-top: 10px;border-radius:5px;color: black;background-color: ">
	                        	<c:set var="key" value="${article.articleId}"></c:set>
	                        	<c:if test="${not empty imgMap[key]}">

                                    <div style="float: right;">
                                	   <img src="${imgMap[key]}" style="width: 200px;height: 200px ;padding-left: 20px;" >
                                    </div>

                                </c:if>

                                <div style="width: 75%;word-break: break-all; word-wrap:break-word;">
    	                            <h2><a href="../../article/${user.userName }/${article.articleId }" style="color: black;">${article.articleTitle }</a></h2>
    	                            <div>
    	                          		<c:forEach items="${TagsList[index]}" var="tag">
    	                                	<a href="../../tag/${user.userName }/${tag }" style="color: black;margin-right: 15px;"><i class="fa fa-tag"></i>${tag }</a>
    	                                </c:forEach>
    	                                <c:set var="index" value="${index+1 }"></c:set>
    	                            </div>
    	                            <br>
    	                            <div>${article.articleContent }</div><br>
    	                            <span style="margin-right: 30px;"><i class="fa fa-user"></i>${article.user.userName }</span>
    	                            <span style="margin-right: 30px;"><i class="fa fa-eye"></i>阅读数量：${article.articleViewCount }</span>
                                    <span style="margin-right: 30px;"><i class="fa fa-eye"></i>点赞数：${article.articleLikeCount }</span>
    	                            <span style="margin-right: 30px;"><i class="fa fa-comment"></i>评论：${article.articleCommentCount }</span>
    	                            <span style="margin-right: 30px;">
                                        <i class="fa fa-clock-o"></i>
                                        <fmt:formatDate value="${ article.articlePostTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                    </span>
                                </div>
                                <div style="clear:both"></div>
	                        </div>
	                	</c:forEach>
                    </div>
                    <!-- /最新发布的文章 -->
                    <div style="width: 23%;float: right;">
                    <!-- 热门阅读 -->
                    <div style="background-color: #FFF;border:1px solid rgb(220,220,220);border-radius:5px;padding: 10px">
                        <div>
                            <h5>热门阅读：</h5>
                            <br>
                        </div>
                        <c:forEach items="${hotArticles }" var="hotArticle">
	                        <div style="word-break: break-all;">
	                            <a href="../../article/${user.userName }/${hotArticle.articleId}" style="line-height: 25px;font-size: 15px">${hotArticle.articleTitle }</a>
	                            <hr/>
	                        </div>
                        </c:forEach>
                    </div>
                    <!-- /热门阅读 -->
                    <!-- 热门阅读 -->
                    <div style="background-color: #FFF;border:1px solid rgb(220,220,220);border-radius:5px;padding: 10px;margin-top: 40px;">
                        <div>
                            <h5>热门标签：</h5>
                            <br>
                        </div>
                        <div>
                            <c:forEach items="${hotTags }" var="hotTag">
                            <span style="margin-right: 10px;"><a href="../../tag/${user.userName }/${hotTag.tagName}" style="line-height: 25px;font-size: 15px"><i class="fa fa-tag"></i>${hotTag.tagName }</a></span>
                            </c:forEach>
                        </div>
                    </div>
                    </div>
                    <div style="padding:50px;width: 500px;clear: both;"></div>
                    <!-- /热门阅读 -->
            <!-- /page content -->
            <!-- footer content -->
            <footer>
                <div class="pull-right ">
                    Copyright © 2018 氢博客 All rights reserved. <a href="javascript:;">站点地图</a>
                </div>
                <div class="clearfix"></div>
            </footer>
            <!-- /footer content -->
        </div>
    </div>
    <!-- jQuery -->
    <script src="../../vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="../../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="../../vendors/nprogress/nprogress.js"></script>
    <!-- Chart.js -->
    <script src="../../vendors/Chart.js/dist/Chart.min.js"></script>
    <!-- gauge.js -->
    <script src="../../vendors/gauge.js/dist/gauge.min.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="../../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
    <script src="../../vendors/iCheck/icheck.min.js"></script>
    <!-- Skycons -->
    <script src="../../vendors/skycons/skycons.js"></script>
    <!-- Flot -->
    <script src="../../vendors/Flot/jquery.flot.js"></script>
    <script src="../../vendors/Flot/jquery.flot.pie.js"></script>
    <script src="../../vendors/Flot/jquery.flot.time.js"></script>
    <script src="../../vendors/Flot/jquery.flot.stack.js"></script>
    <script src="../../vendors/Flot/jquery.flot.resize.js"></script>
    <!-- Flot plugins -->
    <script src="../../vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
    <script src="../../vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
    <script src="../../vendors/flot.curvedlines/curvedLines.js"></script>
    <!-- DateJS -->
    <script src="../../vendors/DateJS/build/date.js"></script>
    <!-- JQVMap -->
    <script src="../../vendors/jqvmap/dist/jquery.vmap.js"></script>
    <script src="../../vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
    <script src="../../vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="../../vendors/moment/min/moment.min.js"></script>
    <script src="../../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="../../build/js/custom.min.js"></script>
    <script src="../../wangEditor/release/wangEditor.js"></script>
    <!-- <script src="../statics/js/user.js"></script> -->
</body>
</html>