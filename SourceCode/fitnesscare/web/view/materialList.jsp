<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css1/styles.css">
        <link rel="stylesheet" href="./assets/css1/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@include file="../component/account.jsp" %>
        <%@include file="header-manage.jsp" %>
        <div id="layoutSidenav">
            <%@include file="leftboard.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h2 class="mt-4 text-center mb-4">Thống kê vật tư</h2>
                        <form action="SearchMaterial" method="post" class="form-inline my-2 my-lg-0">
                            <input value="${txtSearch}" type="text" name="search"/>
                            <button type="submit"> 
                                <i class="fa fa-search"> </i>
                            </button>
                        </form>
                           
                            <button type="button" onclick="openAdd()" class="btn btn-primary" data-toggle="modal" data-target="#AddModal">
                                Add
                            </button>
                        
                        <table class="table w-100 h-100">
                            <thead>
                                <tr>
                                    <th>Tên vật tư</th>
                                    <th>Số lượng</th>
                                    <th>Số lượng hỏng</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.materials}" var="materials">
                                    <tr>
                                        <td class="m2">${materials.material_name}</td>
                                        <td class="m3">${materials.quantity}</td>
                                        <td class="m4">${materials.broken_quantity}</td>
                                        <td> 
                                            <button type="button" onclick="openUpdate(${materials.material_id})" class="btn btn-primary" data-toggle="modal" data-target="#UpdateModal">
                                                Update
                                            </button>
                                            <button type="button" onclick="openDelete(${materials.material_id})" class="btn btn-primary" data-toggle="modal" data-target="#DeleteModal">
                                                Delete
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    </body>
                    <div id="modal-add">

                    </div>
                    <script>
                        function openAdd() {
                            $.ajax({
                                type: "get",
                                url: "MaterialAdd",
                                success: function (data) {
                                    var formAdd = document.getElementById("modal-add");
                                    formAdd.innerHTML += data;
                                }
                            });
                            document.getElementById("modal-add").style.display = "flex";
                        }
                        function openUpdate(id) {

                            $.ajax({
                                type: "get",
                                url: "MaterialUpdate",
                                data: {
                                    id: id
                                },
                                success: function (data) {
                                    var formAdd = document.getElementById("modal-add");
                                    formAdd.innerHTML += data;
                                }
                            });
                        }
                        function openDelete(id) {

                            $.ajax({
                                type: "get",
                                url: "MaterialDelete",
                                data: {
                                    id: id
                                },
                                success: function (data) {
                                    var formAdd = document.getElementById("modal-add");
                                    formAdd.innerHTML += data;
                                }
                            });
                        }
                    </script>
                    </html>
