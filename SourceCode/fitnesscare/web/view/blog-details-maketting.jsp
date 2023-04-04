<%-- 
    Document   : blog-details-maketting
    Created on : Mar 20, 2023, 10:35:02 AM
    Author     : ThinkPro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="./images/logo.png">
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Blog Details</title>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-cYXgDzaU6iNjjCXZ2U6wBm6L/72V7sFNz/4cC4mYmrYS2rdm7bUZl+FkO7+CNT1SX3qVFGOuUmFJGvYS8XnW3A==" crossorigin="anonymous" />

        <%@include file="../component/javascript.jsp" %>
        <style>


            .author-wrap {
                margin: 12px 0 0;
                display: flex;
                align-items: center;
            }

            .author-avatar__picture img {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%,-50%);
                max-height: 100%;
            }




            p{
                font-weight: normal;
            }
        </style>
    </head>
    <body>
        <!-- Responsive navbar-->
        <%@include file="../component/header.jsp" %>
        <%@include file="../component/account.jsp" %>
        <!-- Page content-->
        <div class="container mt-5" style="padding-top: 5%;">
            <div class="row">
                <!-- Side widgets-->

                <div class="col-lg-8">
                    <!-- Post content-->
                    <article>
                        <!-- Post header-->
                        <header class="mb-4">
                            <!-- Post title-->
                            <h1 class="fw-bolder mb-2">${blog.title}</h1>
                            <!-- Post meta content-->


                            <div class="text-muted mb-2" style="font-size:15px;">
                                <span>
                                    <img alt="${author.fullName}" src="${author.avatar}" style="border-radius: 100px; height: 40px; width: 40px;">      
                                </span>
                                <span ><b>${author.fullName} </b></span>
                                <span style=" color: #a0a4a8;"> posted on <i>${blog.updated_date} </i></span>
                            </div>

                            <hr>
                        </header>
                        <!-- Preview image figure-->
                        <figure class="mb-4"><img class="img-fluid rounded" src="${blog.thumbnail}" style="min-width: 90%;" alt="thumbnail" /></figure>
                        <!-- Post content-->
                        <section class="mb-5">
                            <p class="fs-5 mb-4">${blog.content}</p>
                        </section>
                    </article>
                </div>

                <div class="col-lg-4">
                    <div style="margin-top: 100px; margin-bottom: 50px;">
                        Đọc nhiều trong 
                        <c:forEach items="${sessionScope.listCategoryBlog}" var="c">
                            <a class="badge bg-secondary text-decoration-none link-light" href="list-blog?cb_id=${blog.cb_id}">${blog.cb_id == c.cb_id ? c.cb_name:""}</a>
                        </c:forEach>
                    </div>
                    <div>
                        <c:forEach items="${relatedBlog}" var="c">
                            <c:if test="${c.blog_id != blog.blog_id}">
                                <a href="blog-details-maketting?blog_id=${c.blog_id}" >
                                    <div style="display: flex;">
                                        <div style="width: 70%">
                                            <h6 class="blog-title" style="margin-bottom: 5px;">${c.title}</h6><!-- comment -->    
                                        </div>
                                        <div style="width: 30%">
                                            <img data-toggle="modal" data-target="#" style="width: 70px; height: 70px;"  src="${c.thumbnail}"alt="">    
                                        </div>
                                    </div>
                                </a>
                                <hr>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer-->
        <%@include file="../component/footer.jsp" %>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="./js/scripts.js"></script>
    </body>
</html>
