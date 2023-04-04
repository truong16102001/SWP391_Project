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
        <%@include file="component/javascript.jsp" %>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" /> 
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            .toggle-btn {
                background-color: gray;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 20px;
                cursor: pointer;
            }

            .toggle-btn.active {
                background-color: green;
            }

            .toggle-btn.inactive {
                background-color: red;
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <button class="toggle-btn" id="my-btn">Active</button>
        <script>
            const btn = document.getElementById('my-btn');

            btn.addEventListener('click', function () {
                if (this.classList.contains('active')) {
                    this.innerHTML = 'Inactive';
                    this.classList.remove('active');
                    this.classList.add('inactive');
                } else {
                    this.innerHTML = 'Active';
                    this.classList.remove('inactive');
                    this.classList.add('active');
                }
            });
        </script>
    </body>

</html>
