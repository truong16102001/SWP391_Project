<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">          
            <div class="nav">
                <c:if test="${sessionScope.us.role_id == 1}">
                    <div class="sb-sidenav-menu-heading">Bảng điểu khiển</div>
                    <a class="nav-link" href="admin-manage">        
                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                        Thống kê
                    </a>
                </c:if>

                <div class="sb-sidenav-menu-heading">Quản lý</div>
                <c:if test="${sessionScope.us.role_id == 2 || sessionScope.us.role_id == 1}">


                    <a class="nav-link" href="manageproductlist">        
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        Quản lý sản phẩm
                    </a>



                    <a class="nav-link" href="blog-list">        
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        Quản lý blog
                    </a>

                    <c:if test="${sessionScope.us.role_id == 1}">
                        <a class="nav-link" href="managemateriallist">        
                            <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                            Quản lý vật tư
                        </a>
                    </c:if>

                    <a class="nav-link" href="manageorder?key=&status=0">        
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        Quản lý order
                    </a>

                    <a class="nav-link" href="registedService">        
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        Quản lý dịch vụ đã đăng ký
                    </a>
                </c:if>
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">Đăng nhập bởi:</div>
            <b>${sessionScope.us.fullName}</b>
        </div>
    </nav>
</div>