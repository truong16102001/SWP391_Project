<%-- 
    Document   : blogList-Manage
    Created on : Mar 13, 2023, 1:14:48 PM
    Author     : ThinkPro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="./images/logo.png">
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Blog Management</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/ckfinder/ckfinder.js"></script>
        <%@include file="../component/javascript.jsp" %>
        <style>

            .body_cartCompletion {
                font-family: sans-serif;
                background: linear-gradient(110deg, #fdfdbe 60%, #f9f86c 60%);
            }
            .groundy{
                font-family: sans-serif;
                background: #FFFAFA;
            }

            .circle {
                height: 10px;
                width: 10px;
                border: 50%;
            }


        </style>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="../component/account.jsp" %>
        <%@include file="header-manage.jsp" %>
        <div id="layoutSidenav">
            <%@include file="leftboard.jsp" %>
            <div class="groundy" id="layoutSidenav_content">
                <main>
                    <div class="container-fluid rounded row" style="margin-top: 1% !important; margin-bottom: 1% !important">
                        <div class="col-md-1">
                            <a href="add-blog"><button type="button" class="btn btn-danger " style="">Thêm</button></a>
                        </div>
                        <div class="col-md-2">
                            <select class="dropdown-font-new" style="width: 100%" aria-label="Default select example" onchange="location = this.value;">
                                <option value="blog-list?${historyKey}${historyValue}${historyType}${historyStatus}${historyAuthor}" ${cb_id == null ? "Selected" : ""}>
                                    Danh Mục
                                </option>
                                <c:forEach items="${sessionScope.listCategoryBlog}" var="c">
                                    <option value="blog-list?${historyKey}${historyValue}${historyType}${historyStatus}${historyAuthor}&cb_id=${c.cb_id}" ${cb_id == c.cb_id ? "Selected" : ""}>
                                        ${c.cb_name}
                                    </option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="col-md-2">
                            <select class="dropdown-font-new float-left" style="width: 100%" aria-label="Default select example" onchange="location = this.value;"> Sắp xếp
                                <option value="blog-list?${historyKey}${historyStatus}${historyAuthor}${historyCategoryId}&type=desc" ${type == "desc" ? "Selected" : ""}>
                                    Mới Nhất
                                </option>
                                <option value="blog-list?${historyKey}${historyStatus}${historyAuthor}${historyCategoryId}" ${type == null ? "Selected" : ""}>
                                    Cũ Nhất
                                </option>

                            </select>
                        </div>
                        <div class="col-md-2">
                            <select class="dropdown-font-new" style="width: 113%" aria-label="Default select example" onchange="location = this.value;">
                                <option value="blog-list?${historyKey}${historyValue}${historyStatus}${historyCategoryId}" ${author_id == null ? "Selected" : ""}>
                                    Tác giả
                                </option>
                                <c:forEach items="${sessionScope.listAuthor}" var="a">
                                    <option value="blog-list?${historyKey}${historyValue}${historyType}${historyStatus}${historyCategoryId}&author_id=${a.user_id}" ${author_id == a.user_id ? "Selected" : ""}>
                                        ${a.fullName}
                                    </option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="col-md-2">
                            <select class="dropdown-font-new float-right" aria-label="Default select example" onchange="location = this.value;"> Trạng thái
                                <option value="blog-list?${historyKey}${historyValue}${historyAuthor}${historyCategoryId}" ${status == null ? "Selected" : ""}>
                                    Trạng thái
                                </option>
                                <option value="blog-list?${historyKey}${historyValue}${historyAuthor}${historyCategoryId}&status=1" ${status == 1 ? "Selected" : ""}>
                                    Hiện
                                </option>
                                <option value="blog-list?${historyKey}${historyValue}${historyAuthor}${historyCategoryId}&status=0" ${status == 0 ? "Selected" : ""}>
                                    Ẩn
                                </option>

                            </select>
                        </div>
                        <div class="col-md-2 text-center">
                            <form action="blog-list">
<!--                                <input type="text" name="key" value="${key}" placeholder="Nhap title" class="filter-search__control" >-->
                                <div style="display: flex;">
                                    <input class="form-control me-2" type="text" name ="key" placeholder="Tìm kiếm..." 
                                           aria-label="Search" value="${key}" id="myInput1"  class="form-control">

                                    <button style="width: 40px; height: 35px;" type="submit" class="btn btn-outline-danger" href="#" role="button">
                                        <i style='font-size:15px' class='fas'>&#xf002;</i>
                                    </button>    
                                </div>

                                <input type="hidden" name="cb_id" value="${cb_id}">
                                <input type="hidden" name="type" value="${type}">
                                <input type="hidden" name="value" value="${value}">
                                <input type="hidden" name="author_id" value="${author_id}">
                                <input type="hidden" name="status" value="${status}">
                            </form>
                        </div>
                    </div>
                    <c:if test="${listBlog.size() eq 0}">
                        <div style="text-align: center; ">  <h3 style="color:red;">Không tìm thấy kết quả </h3></div>
                    </c:if>
                    <c:if test="${listBlog.size() ne 0}">


                        <div class="container rounded bg-white mt-5 mb-5" >
                            <table class="table"  style="margin-top: 4%">
                                <thead  class="text-center">
                                <th>ID</th>
                                <th>Hình thu nhỏ</th>
                                <th>Tiêu đề</th>
                                <th>Danh mục</th>
                                <th>Tác giả</th>
                                <th>Ngày cập nhật</th>
                                <th>Trạng thái</th>
                                <th style="width: 12%">Tùy chọn </th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listBlog}" var="b">
                                        <tr class="text-center">
                                            <th scope="row">${b.blog_id}</th>
                                            <td><img src="${b.thumbnail}" height="100px" width="100px"/></td>
                                            <td>${b.title}</td>
                                            <td>
                                                <c:forEach items="${sessionScope.listCategoryBlog}" var="c">
                                                    ${(b.cb_id == c.cb_id) ? c.cb_name:""}
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach items="${sessionScope.listAuthor}" var="a">
                                                    ${b.author_id == a.user_id ? a.fullName:""}
                                                </c:forEach>
                                            </td>
                                            <td >${b.updated_date}</td>
                                            <c:if test="${b.status == true}">
                                                <td> 
                                                    <a class="btn btn-success" href="change-status-blog?status=false&blog_id=${b.blog_id}" role="button" style='font-size:10px'>
                                                        <i style='font-size:10px' class='fas far fa-eye'>&#xf070;</i>
                                                    </a>
                                                </td>
                                            </c:if>
                                            <c:if test="${b.status != true}">
                                                <td> 
                                                    <a class="btn btn-dark" href="change-status-blog?status=true&blog_id=${b.blog_id}" role="button" style='font-size:10px'>
                                                        <i style='font-size:10px' class='fas far fa-eye-slash'>&#xf06e;</i>
                                                    </a>
                                                </td>
                                            </c:if>
                                            <td style="width: 125px">
                                                <a class="btn btn-primary" href="blog-details?blog_id=${b.blog_id}" role="button" style='font-size:10px'>
                                                    <i style='font-size:10px' class='fas'>&#xf044;</i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>



                            </table>
                            <c:if test="${listBlog.size() != 0}">
                                <!-- pagination -->
                                <nav aria-label="..." class="pagination">
                                    <ul class="pagination">
                                        <li class="page-item">
                                            <a <c:if test="${pageIndex!=1}">                         
                                                    href="blog-list?index=${pageIndex-1}${historyCategoryId}${historyKey}${historyValue}${historyType}${historyStatus}${historyAuthor}"
                                                </c:if> class="page-link" aria-label="Previous">
                                                <span  aria-hidden="true">«</span>
                                            </a>
                                        </li>

                                        <c:forEach begin="1" end="${endPage}" var="i">
                                            <li class="page-item ${i==pageIndex ?"active" : ""}">
                                                <a class="page-link" href="blog-list?index=${i}${historyCategoryId}${historyKey}${historyValue}${historyType}${historyStatus}${historyAuthor}">${i}</a>
                                            </li>
                                        </c:forEach>

                                        <li class="page-item">
                                            <a <c:if test="${pageIndex != endPage}">
                                                    href="blog-list?index=${pageIndex+1}${historyCategoryId}${historyKey}${historyValue}${historyType}${historyStatus}${historyAuthor}"
                                                </c:if> class="page-link" aria-label="Next">
                                                <span aria-hidden="true">»</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </c:if>
                        </div>
                    </c:if>
                </main>
                <!-- Footer-->
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./js/scripts.js"></script>        

    </body>
</html>
