<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helper" class="utils.DateTimeHelper"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="utils.DateTimeHelper" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./assets/css1/styles.css">
        <link rel="stylesheet" href="./assets/css1/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <script src="../js/manager.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
                $('table .delete').on('click', function () {
                    var id = $(this).parent().find('#id').val();
                    $('#deleteProductModal #id').val(id);
                });
                $('table .edit').on('click', function () {
                    var id = $(this).parent().find('#id').val();
                    $.ajax({
                        type: 'GET',
                        url: 'ProductUpdateController',
                        data: {id:id},
                        dataType: 'json',
                        contentType: 'application/json',
                                success: function(result) {
                                $('#editProductModal #product_id').val(result.product_id);
                                $('#editProductModal #product_name').val(result.product_name);
                                $('#editProductModal #original_price').val(result.original_price);
                                $('#editProductModal #sale_price').val(result.sale_price);
                                $('#editProductModal #pDescription').val(result.pDescription);
                                $('#editProductModal #brief_info').val(result.brief_info);
                                $('#editProductModal #quantity').val(result.quantity);
                                $('#editProductModal #status').val(result.status);
                                $('#editProductModal #cp_id').val(result.cp_id);
                                }
                    });
                });
            });
        </script>
    <body>
        <%@include file="../component/account.jsp" %>
        <%@include file="header-manage.jsp" %>
        <div id="layoutSidenav">
            <%@include file="leftboard.jsp" %>
            <div id="layoutSidenav_content">
               
                    <div class="container-fluid px-4">
                        <h2 class="mt-4 text-center mb-4">Thống kê sản phẩm</h2>
                        <form action="SearchProduct" method="post" class="form-inline my-2 my-lg-0">
                            <input value="${txtSearch}" type="text" name="search"/>
                            <button type="submit"> 
                                <i class="fa fa-search"> </i>
                            </button>
                        </form>
                        <a href="#addProductModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                        <table class="table w-100 h-100">
                            <thead>
                                <tr>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá gốc</th>
                                    <th>Giá bán</th>
                                    <th>Số lượng</th>
                                    <th>Trạng thái</th>
                                    <th>Category ID</th>
                                    <th>Ngày chỉnh sửa</th>
                                    <th>Hình ảnh</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.products}" var="products">
                                    <tr>
                                        <td>${products.product_name}</td>
                                        <td>${products.original_price}</td>
                                        <td>${products.sale_price}</td>
                                        <td>${products.quantity}</td>
                                        <td>${products.status}</td>
                                        <td>${products.cp_id}</td>
                                        <td>${products.update_date}</td>
                                        <td><img src="${products.images.get(0)}" alt="" width="50" height="50"/></td>
                                        <td> 
                                            <a href="#editProductModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="edit">&#xE254;</i></a>
                                            <a href="#deleteProductModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="delete">&#xE15C;</i></a>
                                            <input type="hidden" name="id" id="id" value="${products.product_id}">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
            </div>
        </div>
        <div id="addProductModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="ProductAddController" method="post" enctype="multipart/form-data">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group"> 
                                <label>Tên sản phẩm</label> 
                                <input name="pName" type="text" class="form-control" required> 
                            </div>

                            <div class="form-group"> 
                                <label>Giá gốc</label> 
                                <input name="pOPrice" type="text" class="form-control" required>  
                            </div>

                            <div class="form-group"> 
                                <label>Giá bán</label> 
                                <input name="pPrice" type="text" class="form-control" required>  
                            </div>

                            <div class="form-group"> 
                                <label>Description</label> 
                                <textarea name="pDescription" class="form-control" required=></textarea>
                            </div>

                            <div class="form-group"> 
                                <label>Brief infor</label> 
                                <textarea name="pBrief_infor" class="form-control" required=></textarea>
                            </div>

                            <div class="form-group"> 
                                <label>Số lượng</label> 
                                <input name="pQuantity" type="text" class="form-control" required>  
                            </div>

                            <div class="form-group"> 
                                <label>Trạng thái</label> 
                                <input type="radio" name="pStatus" value ="true" checked="checked"> True
                                <input type="radio" name="pStatus" value="false"> False  
                            </div>

                            <div class="form-group"> 
                                <label>Category</label>
                                <select name="pCategory" class="form-select">
                                    <c:forEach items="${requestScope.categoryProducts}" var="c">
                                        <option value="${c.cp_id}">${c.cp_name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group"> 
                                <label>Ngày cập nhật</label> 
                                <input type="text" name="pDate" class="form-control" readonly value="${requestScope.date}">
                            </div>
                            
                            <div class="form-group"> 
                                <label>Chọn ảnh</label> 
                                <input type="file" name="image" class="form-control">
                            </div>
                            
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div id="deleteProductModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="ProductDeleteController" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Delete Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <p>Bạn có chắc chắn muốn xóa không ?</p>
                            <input type="hidden" name="id" id="id">
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>                     

        <div id="editProductModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="ProductUpdateController" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group"> 
                                <label>Tên sản phẩm</label> 
                                <input name="product_name" type="text" class="form-control" required id="product_name"> 
                            </div>

                            <div class="form-group"> 
                                <label>Giá gốc</label> 
                                <input name="original_price" type="text" class="form-control" required id="original_price">  
                            </div>

                            <div class="form-group"> 
                                <label>Giá bán</label> 
                                <input name="sale_price" type="text" class="form-control" required id="sale_price">  
                            </div>

                            <div class="form-group"> 
                                <label>Description</label> 
                                <textarea name="pDescription" class="form-control" required id="pDescription"></textarea>
                            </div>

                            <div class="form-group"> 
                                <label>Brief infor</label> 
                                <textarea name="brief_info" class="form-control" required id="brief_info"></textarea>
                            </div>

                            <div class="form-group"> 
                                <label>Số lượng</label> 
                                <input name="quantity" type="text" class="form-control" required id="quantity">  
                            </div>

                           <div class="form-group"> 
                                <label>Trạng thái</label> 
                                <input type="radio" name="status" value ="true" id="status" checked="checked"> True
                                <input type="radio" name="status" value="false" id="status"> False  
                            </div>

                            <div class="form-group"> 
                                <label>Category</label>
                                <select name="pCategory" class="form-select" id="pCategory">
                                    <c:forEach items="${requestScope.categoryProducts}" var="c">
                                        <option value="${c.cp_id}">${c.cp_name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group"> 
                                <label>Ngày cập nhật</label> 
                                <input type="text" name="update_date" class="form-control" readonly value="${requestScope.date}">
                            </div>

                            
                            <input type="hidden" name="product_id" id="product_id">
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Edit">
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
