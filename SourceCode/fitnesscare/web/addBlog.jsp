<%-- 
    Document   : addBlog
    Created on : Mar 13, 2023, 1:04:28 PM
    Author     : ThinkPro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <link rel="icon" type="image/x-icon" href="./images/logo.png">
        < <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Add Blog</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <%@include file="component/javascript.jsp" %>
        <style>


            .groundy{
                font-family: sans-serif;

            }

            .circle {
                height: 10px;
                width: 10px;
                border: 50%;
            }
            .ic{
                color: red;
            }
        </style>
        <script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/ckfinder/ckfinder.js"></script>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="component/account.jsp" %>
        <%@include file="view/header-manage.jsp" %>
        <div id="layoutSidenav">
            <%@include file="view/leftboard.jsp" %>
            <div class="groundy" id="layoutSidenav_content">
                <main>
                    <div class="container rounded bg-white mt-5 mb-5">
                        <form action="add-blog" method="post" enctype="multipart/form-data" name="formAdd" onsubmit="return submitForm()">
                            <div class="row"  style="margin-top: 8%;">
                                <div class="p-4">

                                    <h4 class="text-center">Thêm Bài Viết</h4>
                                </div>
                                <div class="col-md-8">
                                    <div class="p-3 py-5">
                                        <div class="col-md-12">Tiêu đề <b class="ic">*</b><input type="text" name="title" class="form-control" placeholder="Nhập tiêu đề"  required=""></div>
                                        <div class="col-md-12">Thông tin rút gọn <b class="ic">*</b><textarea class="form-control" name="brief_info"  rows="2" required=""></textarea></div>
                                        <div class="col-md-12">Nội dung<b class="ic">*</b>

                                            <textarea cols="20" rows="40" id="editor" name="content" required=""></textarea>

                                            <%--                                            <script>
                                                                                            var editor = CKEDITOR.replace('editor');
                                                                                            CKFinder.setupCKEditor(editor, '<%=request.getContextPath()%>/ckfinder/');

                                            </script>--%>

                                            <script>
                                                CKEDITOR.replace('editor', {
                                                    filebrowserBrowseUrl: '<%=request.getContextPath()%>/ckfinder/ckfinder.html',
                                                    filebrowserImageBrowseUrl: '<%=request.getContextPath()%>/ckfinder/ckfinder.html?type=Images',
                                                    filebrowserUploadUrl: '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
                                                    filebrowserImageUploadUrl: '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images'
                                                });
                                            </script>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="p-3 py-5">
                                        <div class="row mt-16">
                                            <div class="col-md-12">Danh mục<b class="ic">*</b>
                                                <select class="dropdown-font-new" name="cb_id" style="width: 100%" aria-label="Default select example" >
                                                    <c:forEach items="${sessionScope.listCategoryBlog}" var="c">
                                                        <option value="${c.cb_id}">
                                                            ${c.cb_name}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-12">Trạng thái<b class="ic">*</b>
                                                <br/>
                                                <input name="status" type="radio" value="true" checked/>&nbsp;Hiện
                                                <input name="status" type="radio" value="false" />&nbsp;Ẩn
                                            </div>
                                            <div class="col-md-12">Hình thu nhỏ<b class="ic">*</b>
                                                <input type="file" name="thumbnail" class="form-control" placeholder="Thumbnail" required="">${img}</div>
                                        </div>

                                    </div>

                                </div>
                                <div class="mt-5 p-4 text-center">
                                    <a href="${historyUrl}"><button class="btn btn-outline-dark" type="button">Quay lại</button></a>
                                    <input class="btn btn-danger ml-5" type="submit" value="Thêm mới">
                                </div>
                            </div>
                        </form> 
                    </div>
                </main>
                <!-- Footer-->
            </div>
        </div>

        <script>
            function submitForm() {
                const title = document.forms["formAdd"]["title"].value.trim();
                const brief_info = document.forms["formAdd"]["brief_info"].value.trim();
                var content = document.getElementById('editor').value.trim();
                if (title.length < 1) {
                    alert("Title không được trống");
                    return false;
                }
                if (brief_info.length < 1) {
                    alert("Thông tin rút gọn không được trống ");
                    return false;
                }
                if (content.length < 1) {
                    alert("Content không được trống");
                    return false;
                }
                return true;
            }


            function showAlert(content) {
                var alertBox = document.createElement("div");
                alertBox.style.position = "fixed";
                alertBox.style.top = "20px";
                alertBox.style.right = "20px";
                alertBox.style.width = "300px";
                alertBox.style.height = "100px";
                alertBox.style.background = "#dc3545";
                alertBox.style.color = "white";
                alertBox.style.display = "flex";
                alertBox.style.justifyContent = "center";
                alertBox.style.alignItems = "center";
                alertBox.style.zIndex = "9999";

// Tạo nội dung cảnh báo
                var alertText = document.createElement("p");
                alertText.style.textAlign = "center";
                alertText.style.fontWeight = "bold";
                alertText.style.fontSize = "2em";
                alertText.innerHTML = content;

// Thêm phần tử cảnh báo và nội dung vào trang web
                alertBox.appendChild(alertText);
                document.body.appendChild(alertBox);

// Đóng cửa sổ cảnh báo sau 3 giây
                setTimeout(function () {
                    alertBox.style.display = "none";
                }, 3000);
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>

    </body>
</html>
