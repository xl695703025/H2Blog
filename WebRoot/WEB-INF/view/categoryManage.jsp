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
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="../vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="../vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet" />
    <!-- bootstrap-daterangepicker -->
    <link href="../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">
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
                        <a href="../admin/index?pageNow=1&pageSize=10" class="site_title" >
                            <i class="fa fa-h-square"></i><span>H<span style="font-size: 10px">2</span> Blog~!</span>
                        </a>
                    </div>
                    <div class="clearfix"></div>
                    <!-- menu profile quick info -->
                    <div class="profile clearfix">
                        <div class="profile_pic">
                            <img src="../${user.userAvatar }" alt="..." class="img-circle profile_img">
                        </div>
                        <div class="profile_info">
                            <span>欢迎登录氢博客后台,</span>
                            <h2>${user.userNickName }</h2>
                        </div>
                    </div>
                    <!-- /menu profile quick info -->
                    <br />
                    <!-- sidebar menu -->
                    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                        <div class="menu_section">
                            <h3>我的博客</h3>
                            
                            <ul class="nav side-menu">
                                <li>
                                	<a href="../article/add" class="writeBlogMenu"><i class="fa fa-edit"></i> 写博客 </a>
                                </li>
                                <li><a><i class="fa fa-book"></i> 博文管理 <span class="fa fa-chevron-down"></span></a>
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
									                        	<a href="../article/manage?categoryId=${cCategory.categoryId}&pageNow=1&pageSize=10" class="blogManage" id="${cCategory.categoryId}">${cCategory.categoryName }</a>
									                        </li>
								                        </c:forEach>
							                        </ul>
						                        </c:if>
	                                        </li>
	                                     </c:forEach>
                                    </ul>
                                </li>
                                <li><a><i class="fa fa-align-left"></i> 分类管理 <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li><a class="categoryAddMenu" href="../category/add">添加分类</a></li>
                                        <li><a class="categoryManage" href="../category/manage">删除/修改分类</a></li>
                                    </ul>
                                </li>
                                <li><a><i class="fa fa-tag"></i>标签管理 <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li><a class="tagAddMenu" href="../tag/add">添加标签</a></li>
                                        <li><a  href="../tag/manage" class="tagManage">删除/修改标签</a></li>
                                    </ul>
                                </li>
                                <li><a><i class="fa  fa-comment"></i> 评论管理<span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li><a href="../comment/manage" class="commentManage">删除评论</a></li>
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
                        <ul class="nav navbar-nav navbar-right">
                            <li class="">
                                <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
	                    <img src="../${user.userAvatar }" alt="">${user.userNickName }
	                    <span class=" fa fa-angle-down"></span>
	                  </a>
                                <ul class="dropdown-menu dropdown-usermenu pull-right">
                                    <li><a href="../user/logout"><i class="fa fa-sign-out pull-right"></i> 退出登录</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <!-- /top navigation -->
            <!-- page content -->
            <!-- /index -->
            <div class="right_col" role="main" id="indexContent" >
                <!-- top tiles -->
                <div class="row tile_count">
                </div>
                <!-- /top tiles -->
                <!-- 分类管理 -->
                <div class="table-responsive">
                    <p class="h1"><i class="fa fa-align-left"></i>分类管理：</p>
                <table id="datatable" class="table table-striped jambo_table bulk_action">
                      <thead>
                            <tr class="headings">
                                <th class="column-title">分类Id </th>
                                <th class="column-title">一级分类</th>
                                <th class="column-title">二级分类</th>
                                <th class="column-title">分类描述</th>
                                <th class="column-title no-link last"><span class="nobr">操作</span></th>
                            </tr>
                        </thead>
                        <tbody id="tbody3">
                       		<c:forEach items="${categoryList }" var="pCategory">
	                            <tr class="even pointer" id="hideRow2">
	                                <td class=" ">${pCategory.categoryId }</td>
	                                <td class=" ">${pCategory.categoryName }</td>
	                                <td class=" "></td>
	                                <td class=" ">${pCategory.categoryDescription }</td>
	                                <td class=" last"><a href="javascript:;" class="del">删除</a>|<a href="javascript:;" class="update">修改</a></td>
	                            </tr>
	                            <c:forEach items="${pCategory.childCategoryList }" var="cCategory">
	                            <tr class="even pointer" id="hideRow2">
	                                <td class=" ">${cCategory.categoryId }</td>
	                                <td class=" "></td>
	                                <td class=" ">${cCategory.categoryName }</td>
	                                <td class=" ">${cCategory.categoryDescription }</td>
	                                <td class=" last"><a href="javascript:;" class="del">删除</a>|<a href="javascript:;" class="update">修改</a></td>
	                            </tr>
                            </c:forEach>
                            </c:forEach>
                        </tbody>
                </table>
                </div>   
            </div>

            <div class="right_col" role="main" id="updateCategory" >
                <p class="h1"><i class="fa fa-tag"></i>修改分类：</p>
                <div style="width:400px;margin: 50px" >
                    <input type="hidden" id="categoryId">
                    <input id="newCategoryName" type="text" class="form-control" placeholder="分类名" />
                    <textarea id="newCategoryDescription" style="margin-top: 20px;height: 100px" class="form-control" placeholder="分类描述"></textarea>
                    <br>
                    <a class="btn btn-default submit" href="javascript:;" id="updateCategoryBtn">提交</a>
                </div>
            </div>
            <!-- /index -->


    </div>
    <!-- jQuery -->
    <script src="../vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="../vendors/nprogress/nprogress.js"></script>
    <!-- Chart.js -->s
    <script src="../vendors/Chart.js/dist/Chart.min.js"></script>
    <!-- gauge.js -->
    <script src="../vendors/gauge.js/dist/gauge.min.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
    <script src="../vendors/iCheck/icheck.min.js"></script>
    <!-- Skycons -->
    <script src="../vendors/skycons/skycons.js"></script>
    <!-- Flot -->
    <script src="../vendors/Flot/jquery.flot.js"></script>
    <script src="../vendors/Flot/jquery.flot.pie.js"></script>
    <script src="../vendors/Flot/jquery.flot.time.js"></script>
    <script src="../vendors/Flot/jquery.flot.stack.js"></script>
    <script src="../vendors/Flot/jquery.flot.resize.js"></script>
    <!-- Flot plugins -->
    <script src="../vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
    <script src="../vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
    <script src="../vendors/flot.curvedlines/curvedLines.js"></script>
    <!-- DateJS -->
    <script src="../vendors/DateJS/build/date.js"></script>
    <!-- JQVMap -->
    <script src="../vendors/jqvmap/dist/jquery.vmap.js"></script>
    <script src="../vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
    <script src="../vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="../vendors/moment/min/moment.min.js"></script>
    <script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="../build/js/custom.min.js"></script>
    <script src="../wangEditor/release/wangEditor.js"></script>
    <script src="../statics/js/categoryManage.js"></script>
</body>

</html>