
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="./images/logo.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Order List</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="./do/css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <%@include file="../component/javascript.jsp" %>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
        <style>

            .payment-method__item-name {
                font-size: 20px;
                padding-left: 20px;
            }

            .payment-method__item {
                display: flex;
                align-items: center;
                border: 1px solid #D9D9D9;
                border-radius: 16px;
                padding: 15px 20px;
                margin-bottom: 1rem;
            }



            .payment-method__item-icon-wrapper img {
                min-width: 100px;
                max-height: 100px;
                max-width: 100px;
                padding-left: 40px;
                image-rendering: -webkit-optimize-contrast;
            }
            .body_cartCompletion {
                font-family: sans-serif;
                background: linear-gradient(110deg, #fdfdbe 60%, #f9f86c 60%);
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
            .mtop {
                margin-top: 2%;
            }
            .title-order {
                display: flex;
                justify-content: center;
                color: red;
            }
            .tbborder {
                border: 2px solid black;
            }
            .sb-nav-fixed #layoutSidenav #layoutSidenav_content{
                justify-content: center;
            }

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
        <%@include file="../component/account.jsp" %>
        <%@include file="header-manage.jsp" %>

        <div id="layoutSidenav">
            <%@include file="leftboard.jsp" %>
            <div class="groundy" id="layoutSidenav_content" >
                <h2 class="mtop title-order" >Danh sách đơn hàng</h2>
                <div class="row justify-content-between" >
                    <div class="col-md-5">
                        <form class="search-bar" action="manageorder">
                            <div class="input-container">
                                <input style="width: 350px;" id="myInput1" class="form-control"  type="text" name="key" value="${key}" placeholder="order_id, order_date, customer_Name, phone" >
                                <span class="clear-icon" onclick="clearInput()">X</span>
                                <input type="hidden" name="status" value="${status}">                      
                            </div>
                            <button type="submit" class="btn btn-dark">Search</button>
                        </form>
                    </div>
                    <div  class="col-md-7 " style="display: flex; float: right; ">
                        <b style="font-size: 20px; margin-left: 350px;">Lọc theo trạng thái: </b> 
                        <div class="col-md-4 " >
                            <select class="form-select" aria-label="Default select example" onchange="location = this.value;">                                                                     
                                <option class=" text-center" value="manageorder?${historyKey}&status=0" ${status eq 0 ? "Selected" : ""}>
                                    Tất cả
                                </option> 
                                <c:forEach items="${statusList}" var="s">
                                    <option class=" text-center" value="manageorder?${historyKey}&status=${s.status_order_id}" ${status eq s.status_order_id ? "Selected" : ""}>
                                        ${s.status_order_name}
                                    </option>
                                </c:forEach>                      
                            </select>
                        </div>
                    </div>
                </div>
                <c:if test="${listOfPage.size() eq 0}">
                    <div style="text-align: center; ">  <h3 style="color:red;">Không tìm thấy kết quả </h3></div>
                </c:if>
                <br> <br> <br>
                <c:if test="${listOfPage.size() ne 0}">
                    <div class="container mtop" style="width:100%">
                        <table class="table table-striped table-bordered" id="sortTable">
                            <thead>
                                <tr>
                                    <th>OrderID</th>
                                    <th>Ngày mua hàng</th>
                                    <th>Tổng chi phí</th>
                                    <th> Người đặt hàng</th>
                                    <th> Số Điện Thoại</th>
                                    <th> Note</th>
                                    <th>ID Người bán</th>
                                    <th>Tình trạng</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listOfPage}" var="c" >
                                    <tr>
                                        <td>
                                            <a href="order-detail?order_id=${c.order_id}">
                                                ${c.order_id}</a>
                                        </td>
                                        <td>${c.order_date}</td>
                                        <td>${c.total_cost}</td>
                                        <td>${c.customer_name}</td>
                                        <td>${c.phone}</td>
                                        <td>${c.note}</td>
                                        <td>${c.seller_id == 0 ? "none" : c.seller_id}</td>
                                        <c:if test="${c.status_order_id == 1}">
                                            <td style="color: green;">
                                                Thành công
                                            </td>
                                        </c:if>
                                        <c:if test="${c.status_order_id == 2}">
                                            <td style="color: red;">
                                                Đã hủy
                                            </td>
                                        </c:if>
                                        <c:if test="${c.status_order_id == 3}">
                                            <td style="color: blue;">
                                                Đang giao
                                            </td>
                                        </c:if>
                                        <c:if test="${c.status_order_id == 4}">
                                            <td>
                                                Chờ xác nhận
                                            </td>
                                        </c:if>
                                        <td>
                                            <c:if test="${c.status_order_id == 4}">
                                                <div class="row">
                                                    <a onclick="return confirm('Xác nhận đơn hàng?')" href="approve-order?order_id=${c.order_id}&step=1" class="btn btn-success btn-lg active" role="button" aria-pressed="true" style="font-size: 12px">Xác nhận</a>
                                                </div>
                                                <div class="row">
                                                    <a onclick="return confirm('Từ chối đơn hàng?')" href="disapprove-order?order_id=${c.order_id}" class="btn btn-danger btn-lg active" role="button" aria-pressed="true" style="font-size: 12px">Từ chối</a>
                                                </div>
                                            </c:if>

                                            <c:if test="${c.status_order_id == 3}">
                                                <div class="row">
                                                    <a onclick="return confirm('Xác nhận giao hàng thành công?')" href="approve-order?order_id=${c.order_id}&step=2" class="btn btn-success btn-lg active" role="button" aria-pressed="true" style="font-size: 12px">Giao hàng thành công</a>
                                                </div>
                                                <div class="row">
                                                    <a onclick="return confirm('Xác nhận giao hàng thất bại?')" href="disapprove-order?order_id=${c.order_id}" class="btn btn-danger btn-lg active" role="button" aria-pressed="true" style="font-size: 12px">Giao hàng thất bại</a>
                                                </div>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- pagination -->
                        <c:if test="${listOfPage.size() != 0}">       
                            <nav aria-label="..." class="pagination">
                                <ul class="pagination">
                                    <li class="page-item">
                                        <a <c:if test="${pageIndex!=1}">                         
                                                href="manageorder?index=${pageIndex-1}${historyStatusOrder}"
                                            </c:if> class="page-link" aria-label="Previous">
                                            <span  aria-hidden="true">«</span>
                                        </a>
                                    </li>

                                    <c:forEach begin="1" end="${endPage}" var="i">
                                        <li class="page-item ${i==pageIndex ?"active" : ""}">
                                            <a class="page-link" href="manageorder?index=${i}${historyStatusOrder}">${i}</a>
                                        </li>
                                    </c:forEach>

                                    <li class="page-item">
                                        <a <c:if test="${pageIndex != endPage}">
                                                href="manageorder?index=${pageIndex+1}${historyStatusOrder}"
                                            </c:if> class="page-link" aria-label="Next">
                                            <span aria-hidden="true">»</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </c:if>
                    </div>
                </c:if>

            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./js/scripts.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script><!-- comment -->
        <script>
                                                        function clearInput() {
                                                            document.getElementById("myInput1").value = "";
                                                        }
        </script>
        <!--        <script>
                                                            $(document).ready(function () {
                                                                $('#sortTable').DataTable({
                                                                    "language": {
                                                                        "decimal": "",
                                                                        "emptyTable": "No data available in table",
                                                                        "info": " _START_ đến _END_ của _TOTAL_ bản ghi",
                                                                        "infoEmpty": "HIển thị 0 to 0 of 0 bản ghi",
                                                                        "infoFiltered": "(kết quả từ _MAX_ tổng số bản ghi)",
                                                                        "infoPostFix": "",
                                                                        "thousands": ",",
                                                                        "lengthMenu": "Hiển thị _MENU_ bản ghi",
                                                                        "loadingRecords": "Loading...",
                                                                        "processing": "",
                                                                        "search": "Tìm kiếm:",
                                                                        "zeroRecords": "Không tìm thấy kết quả nào",
                                                                        "paginate": {
                                                                            "first": "F",
                                                                            "last": "L",
                                                                            "next": "Sau",
                                                                            "previous": "Trước"
                                                                        },
                                                                        "aria": {
                                                                            "sortAscending": ": activate to sort column ascending",
                                                                            "sortDescending": ": activate to sort column descending"
                                                                        }
                                                                    },
                                                                    paging: true,
                                                                    columnDefs: [
                                                                        {targets: [0, 1, 2, 7], orderable: true},
                                                                        {targets: [3, 4, 5, 6, 8], orderable: false}
                                                                    ],
                                                                    searching: true,
                                                                    columns: [
                                                                        {searchable: true},
                                                                        {searchable: true},
                                                                        {searchable: false},
                                                                        {searchable: true},
                                                                        {searchable: true},
                                                                        {searchable: false},
                                                                        {searchable: true},
                                                                        {searchable: false}
                                                                    ]
                                                                });
                                                            });
        
                </script>-->
    </body>
</html>
