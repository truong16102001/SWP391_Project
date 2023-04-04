<%-- 
    Document   : blogdetails-manage
    Created on : Mar 17, 2023, 11:42:26 PM
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
        <title>Blog details</title>
        <style>
            .groundy{
                font-family: sans-serif;
                background: white;
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
                    <div class="container rounded bg-white mt-5 mb-5">
                        <form action="update-blog" method="post" enctype="multipart/form-data">
                            <div class="row"  style="margin-top: 8%;">
                                <div class="p-4">

                                    <h4 class="text-center">Chỉnh sửa bài đăng</h4>
                                </div>
                                <div class="col-md-8">
                                    <div class="p-3 py-5">
                                        <div class="col-md-12">Tiêu đề<input type="text" class="form-control"  name="title" value="${blog.title}"></div>
                                        <div class="col-md-12">Thông tin rút gọn<textarea class="form-control" name="brief_info"  rows="2" >${blog.brief_info}</textarea></div>
                                        <div class="col-md-12">Nội dung

                                            <textarea cols="20" rows="40" id="editor" name="content">${blog.content}</textarea>


                                            <script>
                                                var editor = CKEDITOR.replace('editor');
                                                CKFinder.setupCKEditor(editor, '<%=request.getContextPath()%>/ckfinder/');
                                            </script>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="p-3 py-5">
                                        <div class="row mt-16">
                                            <div class="col-md-12">Blog ID<input type="text" name="blog_id" class="form-control"  value="${blog.blog_id}" readonly ></div>
                                            <div class="col-md-12">Tác giả<input readonly type="text" class="form-control" value="${author.fullName}"/></div>                                        
                                            <div class="col-md-12">Ngày cập nhật<input type="date" class="form-control" value="${blog.updated_date}"  readonly=""></div>
                                            <div class="col-md-12">Danh mục 
                                                <select class="form-control"  name="cb_id" aria-label="Default select example" >
                                                    <c:forEach items="${sessionScope.listCategoryBlog}" var="c">
                                                        <option value="${c.cb_id}" ${blog.cb_id == c.cb_id ? "Selected" : ""}>
                                                            ${c.cb_name}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-12">Trạng thái
                                                <br/>
                                                <input name="status" type="radio" value="true" ${blog.status == true ? 'checked' : ''} />&nbsp;Hiện
                                                <input name="status" type="radio" value="false" ${blog.status == false ? 'checked' : ''} />&nbsp;Ẩn
                                            </div>
                                            <div class="col-md-12">Hình thu nhỏ<input type="file" name="thumbnail" class="form-control" placeholder="Thumbnail" value=""></div>
                                            <img style="width: 220px; height: 150px;" class="mt-5"  src="${blog.thumbnail}"/>
                                        </div>

                                    </div>

                                </div>

                                <div class="mt-5 p-4 text-center">
                                    <a href="${historyUrl}"><button class="btn btn-outline-dark" type="button">Quay lại</button></a>
                                    <input class="btn btn-dark" type="submit" value="Cập nhật">
                                </div>
                            </div>
                        </form> 
                    </div>
                </main>
            </div>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./js/scripts.js"></script>
    </body>
</html>
