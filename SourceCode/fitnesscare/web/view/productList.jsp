<%-- 
    Document   : productList
    Created on : Feb 12, 2023, 10:15:22 PM
    Author     : ThinkPro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link rel="icon" type="image/x-icon" href="./images/logo.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Product List</title>
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

        </style>
    </head>
    <body>
        <div id="main">

            <%@include file="../component/header.jsp" %>
            <%@include file="../component/account.jsp" %>

            <div class="container-fluid" style="margin-top: 100px ">
                <div class="row">
                    <h1 style="text-align: center;">Danh Sách Sản Phẩm</h1>

                    <div class="col-md-2" style="border-right: 1px solid #DCDCDC;">

                        <div class="title_module_arrow">
                            <h2 class="margin-top-0"><span>Danh mục</span></h2>
                        </div>

                        <div class="aside-content aside-cate-link-cls">
                            <nav class="cate_padding nav-category navbar-toggleable-md">

                                <ul class="nav-ul nav navbar-pills flex-column">

                                    <li class="nav-item">
                                        <a  class="nav-link ${cp_id == 0 ? "active":""}" href="list-product?${historyKey}${historyValue}${historyType}"> Tất Cả </a>
                                    </li>

                                    <c:forEach items="${sessionScope.listcp}" var="c">
                                        <li class="nav-item">
                                            <a class="nav-link ${cp_id == c.cp_id ?"active" : ""}"  href="list-product?cp_id=${c.cp_id}${historyKey}${historyValue}${historyType}">${c.cp_name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </nav>
                        </div>
                    </div>

                    <div class="col-md-10">
                        <div class="row justify-content-between"">

                            <div class="col-md-4">
                                <form class="search-bar" action="list-product">
                                    <div class="input-container">

                                        <input id="myInput1" class="form-control"  type="text" name="key" value="${key}" placeholder="Tìm kiếm sản phẩm..." >
                                        <span class="clear-icon" onclick="clearInput()">X</span>
                                        <c:if test="${cp_id != 0}">
                                            <input type="hidden" name="cp_id" value="${cp_id}">
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
                                    <option class=" text-center" value="list-product?${historyCategoryId}${historyKey}&value=update_date" ${value eq "update_date" && type == null ? "Selected" : ""}>
                                        Cũ Nhất
                                    </option> 
                                    <option class=" text-center" value="list-product?${historyCategoryId}${historyKey}&value=update_date&type=desc" ${value eq "update_date" && type eq "desc" ? "Selected" : ""}>
                                        Mới Nhất
                                    </option>
                                    <option class=" text-center" value="list-product?${historyCategoryId}${historyKey}&value=product_name" ${value eq "product_name" ? "Selected" : ""}>
                                        A -> Z
                                    </option>
                                    <option class=" text-center" value="list-product?${historyCategoryId}${historyKey}&value=product_name&type=desc" ${value eq "product_name" && type eq "desc" ? "Selected" : ""}>
                                        Z -> A
                                    </option>
                                    <option class="text-center " value="list-product?${historyCategoryId}${historyKey}&value=original_price" ${value eq "original_price" ? "Selected" : ""}>
                                        Giá tăng dần
                                    </option>
                                    <option class="text-center " value="list-product?${historyCategoryId}${historyKey}&value=original_price&type=desc" ${value eq "original_price" && type eq "desc" ? "Selected" : ""}>
                                        Giá giảm dần
                                    </option> 
                                </select>
                            </div>

                        </div>

                        <div class="row" style="margin-top:40px;">
                            <c:forEach items="${listOfPage}" var="p">
                                <div class="col-lg-2 col-md-3 col-sm-6 product-down">
                                    <div class="row">
                                        <div class="product-item" style="background-color: white;  border-radius: 10px;  box-shadow: 0 0 10px rgb(0 0 0);   text-align: center;">                  
                                            <div class="product-top" >
                                                <a  href="product-detail?product_id=${p.product_id}&cp_id=${p.cp_id}" class="product-thumb">
                                                    <img data-toggle="modal" data-target="#" style="height: 300px;
                                                         width: 180px;" src="${p.images.get(0)}"alt="">
                                                </a>
                                                <c:if test="${sessionScope.us == null}" >
                                                    <a style="cursor: pointer;" class="buy-now" data-toggle="modal"  data-target="#loginModal" style="color: white">Mua ngay</a>
                                                </c:if>
                                                <c:if test="${sessionScope.us != null and sessionScope.us.role_id ne 1 and sessionScope.us.role_id ne 2}" >
                                                    <a style="cursor: pointer;" href="addcart?product_id=${p.product_id}" class="buy-now" >Mua ngay</a>
                                                </c:if>
                                            </div>
                                            <div class="product-infor">
                                                <a href="" class="product-name">${p.product_name}</a>
                                                <div class="product-price">
                                                    <c:if test="${p.sale_price != 0}">
                                                        ${p.sale_price}đ
                                                        <del>${p.original_price}đ</del>
                                                    </c:if>
                                                    <c:if test="${p.sale_price == 0}">
                                                        ${p.original_price}đ
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>

                    <!--comment -->


                </div>
            </div>


            <c:if test="${listOfPage.size() != 0}">
                <!-- pagination -->
                <nav aria-label="..." class="pagination">
                    <ul class="pagination">
                        <li class="page-item">
                            <a <c:if test="${pageIndex!=1}">                         
                                    href="list-product?index=${pageIndex-1}${historyCategoryId}${historyKey}${historyValue}${historyType}"
                                </c:if> class="page-link" aria-label="Previous">
                                <span  aria-hidden="true">«</span>
                            </a>
                        </li>

                        <c:forEach begin="1" end="${endPage}" var="i">
                            <li class="page-item ${i==pageIndex ?"active" : ""}">
                                <a class="page-link" href="list-product?index=${i}${historyCategoryId}${historyKey}${historyValue}${historyType}">${i}</a>
                            </li>
                        </c:forEach>

                        <li class="page-item">
                            <a <c:if test="${pageIndex != endPage}">
                                    href="list-product?index=${pageIndex+1}${historyCategoryId}${historyKey}${historyValue}${historyType}"
                                </c:if> class="page-link" aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </div>

    </div>
    <script>
        function clearInput() {
            document.getElementById("myInput1").value = "";
        }
    </script>
</body>
</html>


