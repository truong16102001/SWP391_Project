<%-- 
    Document   : registerService
    Created on : Feb 20, 2023, 10:39:47 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css1/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
        <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <%@include file="../component/javascript.jsp" %>

    </head>
    <body>

        <%@include file="../component/header.jsp" %>
        <%@include file="../component/account.jsp" %>
        <form action="register_service" method="post">
            <div class="container-fluid" style="margin-top: 100px ">
                <div class="row">

                    <div class="col-md-6" style="display: flex; justify-content: center">
                        <div>
                            <img src="${service.s_image}" alt="alt" style="width: 100%; height: 100%"/>
                        </div>
                    </div>
                        <div class="col-md-6">
                        <h3 style="text-align: center">Thông tin đăng ký</h3>
                        <h5>Khách hàng</h5>
                        Họ tên: ${sessionScope.us.fullName} <br>
                        Email: ${sessionScope.us.email} <br>
                        SÐT: ${sessionScope.us.phone} <br>
                        Địa chỉ: ${sessionScope.us.address}<br><br>
                        <h5>Dịch vụ</h5>
                        <div>
                            Tên dịch vụ: ${service.service_name}
                        </div>
                        <div>
                            Thời gian tập luyện: ${service.practiceTime} tháng
                        </div>
                        <div>
                            Giá: <span style="color: #ff3102">${service.price} đ </span>
                        </div>
                        <div>
                            Timeslot: 
                            <select onchange="loadTechS()" name="ts_id" id="ts_id">
                                <c:forEach items="${listTimeslot}" var="ts">
                                    <option 
                                        value="${ts.ts_id}">${ts.ts_description}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            Ngày bắt đầu: <input onchange="loadTechS()" id="start_date" type="date" name="start_date"/>
                        </div>
                        <c:if test = "${fn:contains(service.service_name, 'PT')}">
                            <div>
                                Huấn luyện viên: <select id="contentTechS" name="techS_id">

                                </select>
                            </div>
                        </c:if>
                        Note: <br>
                        <textarea style="width: 75%" name="note"></textarea><br>
                        <input type="submit" value="Ðăng ký"/>
                    </div>
                </div>
            </div>
        </form>
    </body>
    <script>
        function loadTechS() {
            var ts_id = document.getElementById("ts_id").value;
            var start_date = document.getElementById("start_date").value;
            $.ajax({
                url: "/SWP391_Fitnesscare/loadAvailableTechS",
                type: "get", //send it through get method
                data: {
                    timeslot: ts_id,
                    startdate: start_date
                },
                success: function (data) {
                    var content = document.getElementById("contentTechS");
                    content.innerHTML = data;
                },
                error: function (xhr) {
                    //Do Something to handle error
                }
            });
        }
    </script>
</html>
