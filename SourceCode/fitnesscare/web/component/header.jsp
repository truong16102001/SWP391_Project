<%-- 
Document   : header
Created on : Feb 3, 2022
Author     : Thinkpro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .nav-link.active {
        color: red;
        font-weight: bold;
    }

    .nav-item a .item-numb {
        width: 15px;
        height: 18px;
        text-align: center;
        line-height: 20px;
        position: absolute;
        background: #0089ff;
        color: #fff;
        font-size: 12px;
        border-radius: 50%;
    }


    .ti-shopping-cart {
        font-size: 16px;
        display: inline-block;
        font-size: inherit;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }



</style>
<div id="header">
    <nav class="navbar navbar-expand-lg navbar-header bg-body">
        <div class="container-fluid " >
            <nav class="navbar navbar-light bg-light">
                <a class="navbar-brand " href="home">
                    <img src="images/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
                    FitnessCare
                </a>
            </nav>

            <button class="navbar-toggler " type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>



            <div class="collapse navbar-collapse ms-5" id="navbarSupportedContent">
                <nav>
                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                        <a class="nav-item nav-link  ${active == 1 ?  "active":""}"  data-toggle="link" href="home" role="tab" 
                           aria-controls="nav-home" aria-selected="true" ><b>Trang chủ</b></a> &nbsp; &nbsp;
                        <a class="nav-item nav-link  ${active2 == 2 ? "active":""}"  data-toggle="link" href="list-product" role="tab" 
                           aria-controls="nav-contact" aria-selected="false"><b>Sản phẩm</b></a> &nbsp; &nbsp;
                        <a class="nav-item nav-link "  data-toggle="link" href="list-service" role="tab" 
                           aria-controls="nav-contact" aria-selected="false"><b>Dịch vụ</b></a> &nbsp;&nbsp;
                        <a class="nav-item nav-link ${active4 == 4 ? "active":""}"  data-toggle="link" href="list-blog?cb_id=0" role="tab" 
                           aria-controls="nav-contact" aria-selected="false"><b>Blog</b></a>&nbsp;&nbsp;
                        <button data-toggle="modal"  data-target="#contactModal" class="nav-item nav-link "   
                                aria-controls="nav-contact" aria-selected="false"><b>Liên hệ</b></button>
                    </div>
                </nav>

                <nav class="navbar navbar-light bg-body">
                    <form class="form-inline">
                        <input style="font-size: 15px; pl" class="form-control mr-sm-2" name ="key" type="search" placeholder=" product or service" aria-label="Search" value="${key}" id="" required class="form-control">
                        &nbsp;&nbsp; <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>&nbsp;&nbsp;&nbsp;
                    </form>
                </nav>


                <ul class="navbar-nav mb-2 mb-lg-0">
                    <c:if test="${sessionScope.us != null}">
                        <div class="btn-group">
                            <button type="button" style="border-radius: 4px" class="btn btn-outline-dark dropdown-toggle py-2 px-4" data-toggle="dropdown" aria-expanded="false">
                                <c:if test="${sessionScope.us.avatar != null && sessionScope.us.avatar ne ''}">
                                    <img class="rounded-circle" width="20px" src="${sessionScope.us.avatar}">
                                    <span class="font-weight-bold" style="font-size: 16px">${sessionScope.us.fullName ne "" ? sessionScope.us.fullName : sessionScope.us.email}</span>
                                </c:if>
                                <c:if test="${sessionScope.us.avatar == null || sessionScope.us.avatar eq ''}">
                                    <img class="rounded-circle" width="20px" src="images/avatar/avtdefault.png">
                                    <span class="font-weight-bold" style="font-size: 16px">${sessionScope.us.fullName ne "" ? sessionScope.us.fullName : sessionScope.us.email}</span>
                                </c:if>
                            </button>


                            <ul class="dropdown-menu menuScroll">
                                <a type="button" data-toggle="modal" data-dismiss="modal" data-target="#userProfileModal" 
                                   > &nbsp;&nbsp; Thông tin cá nhân  </a>


                                <c:if test="${sessionScope.us.role_id == 1}">
                                    <li><a class="dropdown-item"  href="admin-manage">Quản lý  </a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.us.role_id == 2 }">
                                    <li><a class="dropdown-item"  href="onlinestaff-manage">Quản lý </a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.us.role_id == 3 }">
                                    <li><a class="dropdown-item" href="tecnicalstaff-manage">Quản lý</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.us.role_id == 4}">
                                    <li><a class="dropdown-item" href="customer-manage">Quản lý</a></li>
                                    </c:if>
                                <li><a class="dropdown-item" href="logout">Đăng xuất</a></li>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.us == null}">
                        <li class="nav-item">
                            <a><i style="border: 1px solid black;border-radius: 10px;" type="button" class="ti-user btn btn-icon py-2 px-4" data-toggle="modal"  data-target="#loginModal"></i></a>
                        </li>
                    </c:if>
                    <!-- begin icon header -->
                    <c:if test="${sessionScope.us.role_id == 4 || sessionScope.us == null}">
                        <c:if test="${sessionScope.us == null}" >
                            <li class="nav-item" >
                                <a class="nav-link btn btn-icon py-2 px-4" data-target="#loginModal" data-toggle="modal"  tabindex="-1" aria-disabled="true">
                                    <i class="ti-shopping-cart"></i></a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.us ne null }" >  
                            <li class="nav-item" >
                                <a class="nav-link btn btn-icon py-2 px-4" href="cartinfo"  tabindex="-1" aria-disabled="true">
                                    <i class="ti-shopping-cart"></i> 
                                    <c:if test="${sessionScope.totalItem > 0}">
                                        <span style="background: blue;" class="item-numb">${sessionScope.totalItem}</span>    
                                    </c:if>
                                </a>
                            </li>
                        </c:if>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</div>



<!-- contact modal -->
<div class="modal fade col-md-12 text-center" role="dialog" id="contactModal">
    <div class="modal-dialog">
        <div class="modal-content" style="border-radius: 10px; margin-top: 50px;">
            <div class="modal-header text-center">
                <h2 id="contactModal" ><b>Thông tin liên hệ</b></h2><br>
            </div>
            <div class="modal-body">
                <div style="padding-top:50px;padding-bottom:40px; text-align: center;">
                    <div>
                        <p>• Hotline: 0123456789</p>
                        <p>• IG: @fitnesscare</p>
                        <p>• FB: fb.com/fitnesscare</p>
                        <p>• Address: Hà Nội</p>
                        <p>-------------------------------</p>
                    </div>
                </div>
                <div class="map">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.483649132689!2d105.52468021467764!3d21.01332549368153!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31345b465a4e65fb%3A0xaae6040cfabe8fe!2sFPT%20University!5e0!3m2!1sen!2s!4v1676534300383!5m2!1sen!2s" 
                            width="100%" height="400" style="border:0;" allowfullscreen="" loading="lazy" 
                            referrerpolicy="no-referrer-when-downgrade">
                    </iframe>
                </div>
            </div>
        </div>
    </div>
</div>