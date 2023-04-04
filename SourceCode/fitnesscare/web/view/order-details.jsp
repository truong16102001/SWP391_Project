<%-- 
    Document   : order-details
    Created on : Mar 12, 2023, 3:13:45 AM
    Author     : ThinkPro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="./images/logo.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>  
        <%@include file="../component/javascript.jsp" %>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" /> 
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            body {
                min-height: 100vh;
                background-size: cover;
                font-family: 'Lato', sans-serif;
                color: black;
                background: white;
            }
            .container-fluid-detail {
                margin-top: 200px ;
            }

            p {
                font-size: 14px;
                margin-bottom: 7px;

            }

            .small {
                letter-spacing: 0.5px !important;
            }

            .card-1 {
                box-shadow: 2px 2px 10px 0px rgb(190, 108, 170);
            }

            hr {
                background-color: rgba(248, 248, 248, 0.667);
            }


            .bold {
                font-weight: 500;
            }

            .change-color {
                color: #AB47BC !important;
            }

            .card-2 {
                box-shadow: 1px 1px 3px 0px rgb(112, 115, 139);

            }

            .fa-circle.active {
                font-size: 8px;
                color: #AB47BC;
            }

            .fa-circle {
                font-size: 8px;
                color: #aaa;
            }

            .rounded {
                border-radius: 2.25rem !important;
            }


            .progress-bar {
                background-color: #AB47BC !important;
            }


            .progress {
                height: 5px !important;
                margin-bottom: 0;
            }

            .invoice {
                position: relative;
                top: -70px;
            }

            .Glasses {
                position: relative;
                top: -12px !important;
            }

            .card-footer {
                background-color: #E0E0E0;
                color: #D00000;
            }

            .cart-footer h2 {
                color: rgb(78, 0, 92);
                letter-spacing: 2px !important;
            }

            .display-3 {
                font-weight: 500 !important;
            }

            @media (max-width: 709px) {
                .invoice {
                    position: relative;
                    top: 7px;
                }

                .border-line {
                    border-right: 0px solid rgb(226, 206, 226) !important;
                }

            }

            @media (max-width: 1500px) {

                .cart-footer h2 {
                    color: rgb(78, 0, 92);
                    font-size: 17px;
                }

                .display-3 {
                    font-size: 28px;
                    font-weight: 500 !important;
                }
            }

            .card-footer small {
                letter-spacing: 7px !important;
                font-size: 12px;
            }

            .border-line {
                border-right: 1px solid rgb(226, 206, 226)
            }

            .cus-fontsize {
                font-size: 20px;
            }


            .mtop {
                margin-top: 2%;
            }

            .groundy{
                font-family: sans-serif;
                background: #F5FFFA;
            }

            .circle {
                height: 10px;
                width: 10px;
                border: 50%;
            }

            .scrollBar{
                height: 400px; /* Đặt chiều cao cố định cho thẻ div */
                overflow-y: scroll; /* Thêm thanh cuộn dọc */
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="../component/account.jsp" %>
        <%@include file="header-manage.jsp" %>
        <div id="layoutSidenav">
            <%@include file="leftboard.jsp" %>
            <div class="container-fluid" style="margin-top: 100px; margin-left: 220px;" >

                <div class="row" style="display: flex;">
                    <div class="row" > 
                        <h2 style="text-align: center; padding-bottom:  50px;" class="color-1 mb-0 change-color">Thông tin đơn hàng</h2> 
                    </div>
                    <c:set var="k" value="${order}" scope="request" />
                    <form id="my-form" action="update-order" class="col-md-12" style="display: flex;">
                        <div class="col-md-6" style="display: flex;">
                            <div class="row" style="display: flex;">
                                <c:if test="${order.status_order_id == 4 or order.status_order_id == 3}">
                                    <div style="display: flex;">
                                        <span style="margin-right:20px;">
                                            Trạng thái: 
                                        </span>

                                        <span>
                                            <select style="width:150px;" class="form-control form-select" aria-label=".form-select-lg example"  name="status" aria-label="Default select example" >
                                                <option style="color: green;" value="1" ${1 == k.status_order_id ? "Selected" : ""}>
                                                    Thành công
                                                </option>
                                                <option style="color: red;" value="2" ${2 == k.status_order_id ? "Selected" : ""}>
                                                    Đã hủy
                                                </option>

                                                <c:if test="${ order.status_order_id == 3}">
                                                    <option style="color: blue;" value="3" ${3 == k.status_order_id ? "Selected" : ""}>
                                                        Đang giao
                                                    </option>
                                                </c:if>
                                                <c:if test="${ order.status_order_id == 4}">
                                                    <option style="color: blue;" value="3" ${3 == k.status_order_id ? "Selected" : ""}>
                                                        Đang giao
                                                    </option>
                                                    <option value="4" ${4 == k.status_order_id ? "Selected" : ""}>
                                                        Chờ xử lý
                                                    </option>
                                                </c:if>
                                            </select>
                                        </span>
                                    </div>

                                    <c:if test="${sessionScope.us.role_id == 1 or sessionScope.us.role_id == 2}">
                                        <c:if test="${ order.status_order_id eq 4}">
                                            <div style="display: flex;">
                                                <span style="margin-right:  20px;">
                                                    Người bán: 
                                                </span>
                                                <span>
                                                    <select style="width: 200px;" class="form-control form-select  " aria-label=".form-select-lg example"  name="seller_id" aria-label="Default select example" >
                                                        <c:forEach items="${sellers}" var="a">
                                                            <option value="${a.user_id}" ${a.user_id == k.seller_id ? "Selected" : ""}>
                                                                ${a.fullName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </span>
                                            </div>
                                        </c:if>
                                        <c:if test="${ order.status_order_id ne 4}">
                                            <div>
                                                <span style="margin-right: 10px;">
                                                    Người bán:
                                                </span>
                                                <c:forEach items="${sellers}" var="a">
                                                    <c:if test="${a.user_id == k.seller_id}">
                                                        <span>
                                                            <strong style="color:orangered;"> ${a.fullName}</strong> 
                                                        </span>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </c:if>
                                </c:if> 


                                <c:if test="${order.status_order_id == 2 or order.status_order_id == 1}">
                                    <div>
                                        <span style="margin-right: 10px;">
                                            Trạng thái: 
                                        </span>

                                        <c:if test="${order.status_order_id == 2 }" >
                                            <span>
                                                <strong style="color:red;">Đã hủy</strong>  
                                            </span>
                                        </c:if>
                                        <c:if test="${order.status_order_id == 1 }" >
                                            <span>
                                                <strong style="color: green;">Thành công</strong>    
                                            </span>
                                        </c:if> 
                                    </div>

                                    <c:if test="${sessionScope.us.role_id == 1 or sessionScope.us.role_id == 2}">
                                        <div>
                                            <span style="margin-right: 10px;">
                                                Người bán:      
                                            </span>

                                            <c:forEach items="${sellers}" var="a">
                                                <c:if test="${a.user_id == k.seller_id}">
                                                    <span>
                                                        <strong style="color: orange;"> ${a.fullName}</strong> 
                                                    </span>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                </c:if>

                                <div> 
                                    <span style="margin-right: 10px;"> Khách hàng: </span>
                                    <span> <b>${k.customer_name}</b></span> 
                                </div>
                                <div>
                                    <span style="margin-right: 10px;">Số điện thoại:</span>                                                       
                                    <span> <b>${k.phone}</b></span> 
                                </div>
                                <div ><span style="margin-right: 10px;">Địa chỉ: </span> 
                                    <span> <b>${k.address}</b></span></div>

                                <div >
                                    <span style="margin-right: 10px;">Order ID: </span><!-- comment -->
                                    <span> <b>${k.order_id}</b></span>
                                </div><!-- comment -->
                                <div>
                                    <span style="margin-right: 10px;"> Ngày mua hàng : </span>
                                    <span><b>${k.order_date}</b></span>
                                </div>

                            </div>
                        </div>



                        <div class="col-md-6" >
                            <div class="scrollBar">
                                <table class="table" style="text-align: center;">
                                    <thead style="    position: sticky;
                                           top: 0;
                                           background-color: #B0C4DE;">
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Ảnh</th>
                                            <th scope="col">Tên sản phẩm</th>
                                            <th scope="col">Giá</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Tổng giá</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items = "${order_details}" var="s" varStatus="loop">
                                            <c:forEach items="${products}" var="pd" >
                                                <c:if test="${pd.product_id eq s.product_id}">
                                                    <tr>
                                                        <th scope="row">${loop.index+1}</th>
                                                        <td>
                                                            <span style="margin-right:10px;">
                                                                <img  src="${pd.images.get(0)}" width="135" height="135" />
                                                            </span>
                                                        </td>
                                                        <td><small>${s.product_name}</small> </td>
                                                        <td><h6 > ${s.product_price}</h6></td>
                                                        <td><h6 > ${s.quantity}</h6></td>
                                                        <td><h6  style="color: orange;">${s.product_price*s.quantity}</h6></td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>


                            <div class="card-footer" style="display: flex;">
                                <span style="margin-right: 40px;">
                                    <img class="img-fluid my-auto align-self-center " src="images/logo.png" width="115" height="115"></span>

                                <span style="margin-right: 30px;"><h2>Tổng: </h2></span>
                                <span><h1>${k.total_cost}VNĐ</h1></span>
                            </div>
                            <input type="hidden" name="order_id" value="${k.order_id}"/>
                        </div>
                    </form>
                    <c:if test="${order.status_order_id == 4 or order.status_order_id == 3}">
                        <div  style="text-align: center; margin-top: 30px;" >
                            <input id="submit-btn" onclick="submitForm()" class="btn btn-dark btn-lg" value="Lưu">  
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <script>
            function submitForm() {
                document.getElementById("my-form").submit();
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./js/scripts.js"></script>                         
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="./js/datatables-simple-demo.js"></script>
    </body>
</html>
