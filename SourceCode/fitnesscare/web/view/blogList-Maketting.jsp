<%-- 
    Document   : blogList-Maketting
    Created on : Mar 18, 2023, 12:38:40 AM
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
        <title>Blog List Maketting</title>
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

            li.nav-item a.active{
                color: blue;
                font-weight: bold;
            }
            li.nav-item a{
                color: black;
            }
            .form-select option{
                background-color: transparent;
            }

            .input-container {
                position: relative;
                display: inline-block;
            }

            .input-container input[type="text"] {
                padding-right: 30px;
            }

            .clear-icon {
                position: absolute;
                top: 50%;
                color: red;
                right: 10px;
                transform: translateY(-50%);
                cursor: pointer;
                display: none;
            }

            .input-container:hover .clear-icon {
                display: block;
            }
            .card {
                width: 100%;
                border: 1px solid #ccc;
                box-shadow: 2px 2px 5px #ccc;
                padding: 10px;
            }

            .card-body {
                display: flex;
                align-items: center;
            }

            .image-container {
                flex: 0 0 250px;
                margin-right: 30px;
            }

            .blog-title {
                margin: 0;
            }

            .blog-content {
                margin: 0;
                font-weight: normal;
            }
        </style>
    </head>
    <body>
        <!-- Responsive navbar-->
        <%@include file="../component/header.jsp" %>
        <%@include file="../component/account.jsp" %>

        <div class="container-fluid" style="margin-top: 100px ">
            <div class="row">
                <h1 style="text-align: center;">Danh Sách Blog</h1>
                <div class="col-md-2" style="border-right: 1px solid #DCDCDC;">
                    <div class="title_module_arrow">
                        <h2 class="margin-top-0"><span>Danh mục</span></h2>
                    </div><!-- comment -->
                    <div class="aside-content aside-cate-link-cls">
                        <nav class="cate_padding nav-category navbar-toggleable-md">
                            <ul class="nav-ul nav navbar-pills flex-column">

                                <li class="nav-item">
                                    <a  class="nav-link ${cb_id == 0 ? "active":""}" href="list-blog?cb_id=0${historyKey}${historyValue}${historyType}"> Tất Cả </a>
                                </li>

                                <c:forEach items="${sessionScope.listCategoryBlog}" var="c">
                                    <li class="nav-item">
                                        <a class="nav-link ${cb_id == c.cb_id ?"active" : ""}"  href="list-blog?cb_id=${c.cb_id}${historyKey}${historyValue}${historyType}">${c.cb_name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>

                <div class="col-md-10">
                    <div class="row justify-content-between"">
                        <div class="col-md-4">
                            <form class="search-bar" action="list-blog">
                                <div class="input-container">

                                    <input id="myInput1" class="form-control"  type="text" name="key" value="${key}" placeholder="Nhập tên blog..." >
                                    <span class="clear-icon" onclick="clearInput()">X</span>
                                    <c:if test="${cp_id != 0}">
                                        <input type="hidden" name="cb_id" value="${cb_id}">
                                    </c:if>
                                    <input type="hidden" name="type" value="${type}">
                                    <input type="hidden" name="value" value="${value}">
                                </div>
                                <button type="submit" class="btn btn-dark">Search</button>
                            </form>
                        </div>
                        <div class="col-md-2 " style="margin-right: -530px; font-size: 20px;"> <b>Sắp xếp: </b> </div>
                        <div class="col-md-3">
                            <select class="form-select" aria-label="Default select example" onchange="location = this.value;">                                                                     
                                <option class=" text-center" value="list-blog?${historyCategoryId}${historyKey}&value=updated_date" ${value eq "updated_date" && type == null ? "Selected" : ""}>
                                    Cũ Nhất
                                </option> 
                                <option class=" text-center" value="list-blog?${historyCategoryId}${historyKey}&value=updated_date&type=desc" ${value eq "updated_date" && type eq "desc" ? "Selected" : ""}>
                                    Mới Nhất
                                </option>                                                         
                            </select>
                        </div>
                    </div>
                    <c:if test="${listBlog.size() == 0}">
                        <div style="text-align: center; color:red;">
                            Không tìm thấy kết quả nào!
                        </div>
                    </c:if>
                    <c:if test="${listBlog.size() > 0}">


                        <div class="row" style="margin:50px 100px;">
                            <c:forEach items="${listBlog}" var="b">
                                <div class="card" style="margin-bottom: 10px;">
                                    <div class="card-body " style="display: flex;">
                                        <div class="image-container">
                                            <img data-toggle="modal" data-target="#" style="width: 250px; height: 120px;"  src="${b.thumbnail}"alt="">
                                        </div>
                                        <div class="blog-content-container">
                                            <h4 class="blog-title" style="margin-bottom: 5px;">${b.title}</h4>
                                            <p class="blog-content" style="margin-bottom: 10px;">${b.brief_info}</p>    
                                            <a href="blog-details-maketting?blog_id=${b.blog_id}" class="btn btn-primary">Xem chi tiết</a>
                                        </div>
                                    </div>
                                    <div class="card-footer text-muted " style="text-align: center;">
                                        <div  style="color:rgba(0,0,0,.54);
                                              font-size: .99rem;">
                                            ${b.updated_date}
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <c:if test="${listBlog.size() != 0}">
                            <!-- pagination -->
                            <nav aria-label="..." class="pagination">
                                <ul class="pagination">
                                    <li class="page-item">
                                        <a <c:if test="${pageIndex!=1}">                         
                                                href="list-blog?index=${pageIndex-1}${historyCategoryId}${historyKey}${historyValue}${historyType}${historyStatus}${historyAuthor}"
                                            </c:if> class="page-link" aria-label="Previous">
                                            <span  aria-hidden="true">«</span>
                                        </a>
                                    </li>

                                    <c:forEach begin="1" end="${endPage}" var="i">
                                        <li class="page-item ${i==pageIndex ?"active" : ""}">
                                            <a class="page-link" href="list-blog?index=${i}${historyCategoryId}${historyKey}${historyValue}${historyType}${historyStatus}${historyAuthor}">${i}</a>
                                        </li>
                                    </c:forEach>

                                    <li class="page-item">
                                        <a <c:if test="${pageIndex != endPage}">
                                                href="list-blog?index=${pageIndex+1}${historyCategoryId}${historyKey}${historyValue}${historyType}${historyStatus}${historyAuthor}"
                                            </c:if> class="page-link" aria-label="Next">
                                            <span aria-hidden="true">»</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </c:if>
                    </c:if>
                </div>

            </div>
        </div>
        <%@include file="../component/footer.jsp" %>
        <script>
            function clearInput() {
                document.getElementById("myInput1").value = "";
            }
        </script>
    </body>
</html>
