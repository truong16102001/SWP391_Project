<%-- 
    Document   : serviceDetail
    Created on : Feb 17, 2023, 12:19:56 AM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service Detail</title>
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
    </head>
    <body>
        <%@include file="../component/header.jsp" %>
        <%@include file="../component/account.jsp" %>
        <div class="container-fluid" style="margin-top: 100px ">
            <div class="row">
                <h1 style="text-align: center;">Thông tin Dịch Vụ</h1>
                <div class="row" style="margin-top:40px; display: flex">
                    <div class="col-5">
                        <img src="${service.s_image}" alt="alt" style="width: 100%; height: 100%"/>
                    </div>
                    <div class="col-7" style="font-size: large">
                        <h4>
                            Tên Dịch vụ: ${service.service_name} 
                        </h4>
                        <div>
                            Mô tả: ${service.service_Description} 
                        </div>
                        <div>
                            Giá: 
                            <span style="color: #ff3102">${service.price} đ </span>
                        </div>
                        <a class="btn btn-primary" href="#" role="button">Đăng ký</a>
                    </div>
                </div>

            </div>

            <!--comment -->


        </div>
    </div>
</body>
</html>
