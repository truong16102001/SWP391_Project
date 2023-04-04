
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="images/logo.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Home page</title>
        <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="assets/fonts/themify-icons/themify-icons.css">
        <%@include file="component/javascript.jsp" %>

    </head>

    <body>

        <%@include file="component/header.jsp" %>
        <%@include file="component/account.jsp" %>

        <!-- body content -->
        <div id="content">
            <div class="section mcb-section mcb-section-80cba8ba8 dark" 
                 style="padding-top:90px;padding-bottom:40px; height: 800px; width: 100%;
                 background-color:#1e1d23;
                 background-image:url('images/homebackground.jpg')
                 ; background-repeat:no-repeat;background-position:0px 50px; background-size: 100% 100%;
                 ">
                <div class="section_wrapper mcb-section-inner">
                    <div class="slogan" style="margin: 70px 10% 0 0;">
                        <h2 style="font-size: 55px; line-height: 55px; margin-bottom: 35px; margin-left: 700px; color: greenyellow">
                            Keep your body<br>fit &amp;  strong</h2>  

                        <h3 style="font-size: 30px; line-height: 35px; margin-bottom: 50px; margin-left: 700px; color: cornsilk ">
                            Chúng tôi có nhiều sản phẩm, dịch vụ và tâm huyết giúp sức khỏe và vóc dáng của bạn tốt hơn! 
                        </h3>
                        <h3 style="font-size: 30px; line-height: 35px; margin-bottom: 50px; margin-left: 700px; color: lightcyan ">
                            Thời gian hoạt động: 8:00AM - 22:00PM  
                        </h3>
                    </div>
                </div>
            </div>

            <div class="why_us_section">
                <section class="why_us_section layout_padding">
                    <div class="container">
                        <div class="heading_container heading_center">
                            <h2>
                                Why Choose Us
                            </h2>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="box ">
                                    <div class="img-box">
                                        <img src="images/w1.png" alt="">
                                    </div>
                                    <div class="detail-box">
                                        <h5>
                                            Fast Delivery
                                        </h5>                                      
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="box ">
                                    <div class="img-box">
                                        <img src="images/w2.png" alt="">
                                    </div>
                                    <div class="detail-box">
                                        <h5>
                                            Free Shiping
                                        </h5>                                    
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="box ">
                                    <div class="img-box">
                                        <img src="images/w3.png" alt="">
                                    </div>
                                    <div class="detail-box">
                                        <h5>
                                            Best Quality
                                        </h5>                                   
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <%@include file="component/footer.jsp" %>
        <script>
            if (document.querySelector('.alert')) {
                document.querySelectorAll('.alert').forEach(function ($el) {
                    setTimeout(() => {
                        $el.classList.remove('show');
                    }, 3000);
                });
            }
        </script>
    </body>
</html>
