
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="sb-topnav navbar navbar-expand navbar-light bg-light ">
    <!-- Navbar Brand-->    
        <a class="navbar-brand me-5 " href="home" style="padding-left: 2%">
            <img src="images/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
            FitnessCare
        </a>
  
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <div class="collapse navbar-collapse ms-5" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <a class="nav-item nav-link  active"  data-toggle="link" href="home" role="tab" 
               aria-controls="nav-home" aria-selected="true" ><b>Trang chủ</b></a>
        </ul>
        <!-- Navbar-->
<!--        <form class="d-flex me-5" action="list">
            <input class="form-control me-2" type="text" name ="key" placeholder="Tìm kiếm..." aria-label="Search" value="${key}" id="" required class="form-control">
            <button class="btn btn-outline-success" type="submit">Tìm</button>
        </form>-->

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
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" type="button" data-toggle="modal"  data-target="#userProfileModal">Thông tin cá nhân</a></li>                      
                        <li><a class="dropdown-item" href="logout">Đăng xuất</a></li>
                    </ul>
                </div>
            </c:if>
            <c:if test="${sessionScope.us == null}">
                <li class="nav-item">
                    <a><i type="button" class="ti-user btn btn-icon py-2 px-4" data-toggle="modal"  data-target="#loginModal"></i></a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>


