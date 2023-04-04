<%-- 
    Document   : serviceList
    Created on : Feb 15, 2023, 9:20:01 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css1/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Service List</title>
        <%@include file="../component/javascript.jsp" %>
        <link rel="icon" type="image/x-icon" href="../images/logo.png">
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
            <img src="./images/service/dichvu.png" alt=""/>
            <div class="container-fluid" style="margin-top: 100px ">
                <div class="row">
                    <h1 style="text-align: center;">Danh Sách Dịch Vụ</h1>

                    
                    <div class="col-md-12">
                        <div class="row justify-content-between" style="margin-left: 50px; margin-right: 50px">

                            <div class="col-md-4">
                                <form class="search-bar" action="list-service">
                                    <div class="input-container">
                                        <input id="myInput1" class="form-control"  type="text" name="key" value="${key}" placeholder="Tìm kiếm dịch vụ..." >
                                        <span class="clear-icon" onclick="clearInput()">X</span>
                                    </div>
                                    <button type="submit" class="fa fa-search"></button>
                                </form>
                            </div>


                            <div class="col-md-3">
                                <select class="form-select" aria-label="Default select example" onchange="location = this.value;">                                                                     
                                    
                                    <option class=" text-center" value="list-service?${historyKey}&value=service_name" ${value eq "service_name" ? "Selected" : ""}>
                                        A -> Z
                                    </option>
                                    <option class=" text-center" value="list-service?${historyKey}&value=service_name&type=desc" ${value eq "service_name" && type eq "desc" ? "Selected" : ""}>
                                        Z -> A
                                    </option>
                                    <option class="text-center " value="list-service?${historyKey}&value=price" ${value eq "price" ? "Selected" : ""}>
                                        Giá tăng dần
                                    </option>
                                    <option class="text-center " value="list-service?${historyKey}&value=price&type=desc" ${value eq "price" && type eq "desc" ? "Selected" : ""}>
                                        Giá giảm dần
                                    </option> 
                                </select>
                            </div>

                        </div>

                        <div class="row" style="margin-top:40px;">
                            <c:forEach items="${listOfPage}" var="s">
                                <div class="col-md-5 product-down">
                                    <div class="row">
                                        <div class="product-item" style="background-color: white;  border-radius: 10px;  box-shadow: 0 0 10px;   text-align: center;">                  
                                            <div class="product-top">
                                                <a  href="service-detail?service_id=${s.service_id}" class="product-thumb">
                                                    <img data-toggle="modal" data-target="#" style="height: 300px;
                                                         width: 100%;" src="${s.s_image}"alt="">
                                                </a>
                                                <c:if test="${sessionScope.us == null}" >
                                                    <a style="cursor: pointer;" class="buy-now" data-toggle="modal"  data-target="#loginModal" style="color: white">Đăng ký ngay</a>
                                                </c:if>
                                                <c:if test="${sessionScope.us != null}" >
                                                    <a style="cursor: pointer;" href="register-service?service_id=${s.service_id}" class="buy-now" >Đăng ký ngay</a>
                                                </c:if>
                                            </div>
                                            <div class="product-infor">
                                                <a href="" class="product-name">${s.service_name}</a>
                                                <div class="product-price">
                                                        ${s.price}đ
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


            <!-- pagination -->
            <nav aria-label="..." class="pagination">
                <ul class="pagination">
                    <li class="page-item">
                        <a <c:if test="${pageIndex!=1}">                         
                                href="list-product?index=${pageIndex-1}${historyKey}${historyValue}${historyType}"
                            </c:if> class="page-link" aria-label="Previous">
                            <span  aria-hidden="true">«</span>
                        </a>
                    </li>

                    <c:forEach begin="1" end="${endPage}" var="i">
                        <li class="page-item ${i==pageIndex ?"active" : ""}">
                            <a class="page-link" href="list-service?index=${i}${historyKey}${historyValue}${historyType}">${i}</a>
                        </li>
                    </c:forEach>

                    <li class="page-item">
                        <a <c:if test="${pageIndex!=endPage}">
                                href="list-service?index=${pageIndex+1}${historyKey}${historyValue}${historyType}"
                            </c:if> class="page-link" aria-label="Next">
                            <span aria-hidden="true">»</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>

    <script>
        function clearInput() {
            document.getElementById("myInput1").value = "";
        }
    </script>
    </body>
</html>
