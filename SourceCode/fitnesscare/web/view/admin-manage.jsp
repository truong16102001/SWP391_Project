

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="icon" type="image/x-icon" href="./images/logo.png">
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Admin Manage</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css/styles.css">
        <link rel="stylesheet" href="../assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            .dateFromTo {
                font-size: 20px;
                padding: 1%;
                margin: 0;
                box-sizing: border-box;
            }
            input[type="date"] {
                font-size: 20px;
                border-radius: 5px;
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="../component/account.jsp" %>
        <%@include file="header-manage.jsp" %>
        <div id="layoutSidenav">
            <%@include file="leftboard.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h2 class="mt-4 text-center mb-4">Thống kê</h2>
                        <div class="row numberStatistic">
                            <div class="col-xl-3 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="fa fa-user"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${noCustomer}</h5>
                                            <p class="text-muted mb-0">Customer</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->

                            <div class="col-xl-3 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="ti ti-dropbox-alt"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${noProduct}</h5>
                                            <p class="text-muted mb-0">Product</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->

                            <div class="col-xl-3 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="fa-solid fa-hand-holding-heart"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${noService}</h5>
                                            <p class="text-muted mb-0">Services</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->

                            <div class="col-xl-3 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="fa fa-users"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${noStaff}</h5>
                                            <p class="text-muted mb-0">Staffs</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->                     
                        </div>
                        <br>

                        <div class="dateFromTo">
                            <form action="admin-manage" onsubmit="return checkDate()">
                                Từ: 
                                <input class="" type="date" id="start" name="start" value="${start}">
                                Đến: 
                                <input type="date" id="end" name="end" value="${end}">
                                <input class="ml-4 btn btn-primary" type="submit" value="Thống kê"/>
                            </form>
                        </div>
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-pie me-1"></i>
                                        Thống kê đơn hàng
                                    </div>                               
                                    <div class="card-body">
                                        <canvas id="myPieChartAdminCustomers" width="100%" height="40"></canvas>
                                    </div>
                                </div>
                            </div>

                           
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        Thống kê doanh thu
                                    </div>
                                    <div class="card-body"><canvas id="myAreaChart-1" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script>
            function checkDate(){
            const start = document.getElementById()('start');
            const startDateString = start.value;
            const end = document.getElementsById('end');
            const endDateString = end.value;
            const startDate = new Date(startDateString);
            const endDate = new Date(endDateString);
            if (startDate.getTime() > endDate.getTime()){
            alert('start date phải trước end date');
            return false;
            } else{
            return true;
            }
            }
        </script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.2.1/chart.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./js/scripts.js"></script>


        <script>

            // Set new default font family and font color to mimic Bootstrap's default styling
            Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
            Chart.defaults.global.defaultFontColor = '#292b2c';
// Pie Chart Example
            var ctx = document.getElementById("myPieChartAdminCustomers");
            var myPieChart = new Chart(ctx, {
            type: 'pie',
                    data: {
                    labels: [<c:forEach  items="${listStatusOrder}" var="status" > "${status.status_order_name}",</c:forEach>],
                            datasets: [{
                            data: [${totalOrder1}, ${totalOrder2}, ${totalOrder3},${totalOrder4}],
                                    backgroundColor: ['green', 'red', 'blue', 'gray'],
                            }],
                    },
            });
        </script>

        <script>
            // Set new default font family and font color to mimic Bootstrap's default styling
            Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
            Chart.defaults.global.defaultFontColor = '#292b2c';
            // Area Chart Example
            var ctx1 = document.getElementById("myAreaChart-1");
            var myLineChart1 = new Chart(ctx1, {
            type: 'bar',
                    data: {
                    labels: [<c:forEach  items="${listChartRevenueArea}" var="revenue" > "${revenue.date}",</c:forEach>],
                            datasets: [{
                            label: "Doanh Thu",
                                    lineTension: 0.3,
                                    backgroundColor: "rgba(2,117,216,0.2)",
                                    borderColor: "rgba(2,117,216,1)",
                                    pointRadius: 5,
                                    pointBackgroundColor: "rgba(2,117,216,1)",
                                    pointBorderColor: "rgba(255,255,255,0.8)",
                                    pointHoverRadius: 5,
                                    pointHoverBackgroundColor: "rgba(2,117,216,1)",
                                    pointHitRadius: 50,
                                    pointBorderWidth: 2,
                                    data: [<c:forEach  items="${listChartRevenueArea}" var="revenue" > "${revenue.value}",</c:forEach>],
                            }],
                    },
                    options: {
                    scales: {
                    xAxes: [{
                    time: {
                    unit: 'date'
                    },
                            gridLines: {
                            display: false
                            },
                            ticks: {
                            maxTicksLimit: 7
                            }
                    }],
                            yAxes: [{
                            ticks: {
                            min: 0,
                                    max: ${maxListChartRevenueArea},
                                    maxTicksLimit: 5
                            },
                                    gridLines: {
                                    color: "rgba(0, 0, 0, .125)",
                                    }
                            }],
                    },
                            legend: {
                            display: false
                            }
                    }
            });
//            var ctx2 = document.getElementById("myAreaChart-2");
//            var myLineChart2 = new Chart(ctx2, {
//            type: 'bar',
//                    data: {
//                    labels: [<c:forEach  items="${listChartCustomer}" var="customer" > "${customer.date}",</c:forEach>],
//                            datasets: [{
//                            label: "Khách hàng",
//                                    lineTension: 0.3,
//                                    backgroundColor: "#DCDCDC",
//                                    borderColor: "rgba(2,117,216,1)",
//                                    pointRadius: 5,
//                                    pointBackgroundColor: "rgba(2,117,216,1)",
//                                    pointBorderColor: "rgba(255,255,255,0.8)",
//                                    pointHoverRadius: 5,
//                                    pointHoverBackgroundColor: "rgba(2,117,216,1)",
//                                    pointHitRadius: 50,
//                                    pointBorderWidth: 2,
//                                    data: [<c:forEach  items="${listChartCustomer}" var="customer" > "${customer.value}",</c:forEach>],
//                            }],
//                    },
//                    options: {
//                    scales: {
//                    xAxes: [{
//                    time: {
//                    unit: 'date'
//                    },
//                            gridLines: {
//                            display: false
//                            },
//                            ticks: {
//                            maxTicksLimit: 7
//                            }
//                    }],
//                            yAxes: [{
//                            ticks: {
//                            min: 0,
//                                    max: ${maxListChartCustomerArea},
//                                    maxTicksLimit: 5
//                            },
//                                    gridLines: {
//                                    color: "rgba(0, 0, 0, .125)",
//                                    }
//                            }],
//                    },
//                            legend: {
//                            display: false
//                            }
//                    }
//            });
        </script>


    </body>
</html>


