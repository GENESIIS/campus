<%-- 20161114 TR c25 start styling user profile page container  --%>
<%-- 20161114 TR c25 page header - done  --%>
<%-- 20161114 TR c25 profile-image-box - done  --%>
<%-- 20161227 MM c25-student-create-dashboard-MP Converted to JSP and converted relative paths to absolute paths --%>
<%-- 20161227 MM c25-student-create-dashboard-MP Moved JS files to the bottom of the page for better performance included the call to studentDashboard.js file --%>
<%-- 20161228 MM c25-student-create-dashboard-MP Removed static carousel template code that is replaced at runtime with dynamically produced element list --%>
<%-- 20161228 MM c25-student-create-dashboard-MP Removed static template code for institute list which, when running, 
				is replaced with dynamically produced institute list --%>
<%-- 20161229 MM c25-student-create-dashboard-MP Made minor modification to make it possible to get details from the session  --%>
<%-- 20161228 MM c25-student-create-dashboard-MP Removed static template code for activity list --%>
<%-- 20170104 MM c25-student-dashboard-MP Assigned dynamic content to items that display student biographical info --%>
<%-- 20170104 MM c25-student-dashboard-MP Fixed bugs in JSTL code that accesses the data payload sent from the command class; removed header/footer code from 
				this page and included header and footer files instead --%>
<%-- 20170104 MM c25-student-dashboard-MP Added code to dynamically assign the path to the profile image of the student;
				disabled the displaying of the student-activity section --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/css/image-slides.css" rel="stylesheet">
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<script src="/dist/js/header/ui-populate-helper.js"></script>
<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/header/ui-populate-helper.js"></script>

</head>

<body>

<!-- Header-->
<jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
<!--< End header -->

<div class="dashboard">
    <div class="stud-dashboard clearfix">
       <div class="prf-page-header col-md-12 col-lg-12 col-sm-12">
           <h1>| User Profile </h1>
       </div>
        <!-- End page header -->

        <div class="prf-page-container col-md-12 col-lg-12 col-sm-12 col-xs-12 clearfix">
            <div class="left-side col-md-3 col-lg-3 col-sm-12 clearfix">
                <div class="prf-pic-holder">
                    <div class="prf-image">
                        <c:set var="studentCode" value="${result.collection[0][10]}"/>
                        <c:set var="slashChar" value="/"/>
                        <c:set var="imageExtension" value=".jpg"/>
                        <c:set var="fullImagePath" value="${studentProfileImagePath}${slashChar}${studentCode}${slashChar}${studentCode}${imageExtension}"/>
                        <img src="${fullImagePath}" alt="Image of ${result.collection[0][0]} ${result.collection[0][1]}">
                    </div>
                    <!-- End profile image -->

                    <div class="prf-name">
                    	<h2>${result.collection[0][0]} ${result.collection[0][1]} <%-- ${sessionScope.userFirstName} ${sessionScope.userLastName} --%></h2>
                        <h3>${result.collection[0][5]} (via ${result.collection[0][4]})</h3>
                        <h4>${result.collection[0][3]}</h4>
                    </div>
                    <!-- End profile name -->
                    <div class="follow-me">
                        <button class="btn-follow">View Profile</button>
                    </div>
                    <!-- End follow-me button -->
                </div>
                <!-- end profile picture box -->

                <div class="short-info-holder">
                    <table class="tbl-left-info">
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>${result.collection[0][9]} at <span>${result.collection[0][8]}</span></p></td>
                        </tr>
                        <!-- End works at -->

                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Studied at <span>${result.collection[0][6]}</span></p></td>
                        </tr>
                        <!-- End studied at -->

                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Lives in <span>${result.collection[0][3]}</span></p></td>
                        </tr>
                        <!-- End lives in -->
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>About Me:<br> <span class="sp-note">${result.collection[0][2]}</span></p></td>
                        </tr>
                        <!-- End Notes -->
                    </table>
                </div>
            </div>
            <!-- End Left side panel -->

            <div class="right-side col-md-9 col-lg-9 col-sm-12 clearfix">
                <div class="widget w-rec-courses clearfix">
                    <div class="widget-header">
                        <label>Recommended Courses</label>
                    </div>
                    <!-- End widget header -->

                    <div class="widget-content clearfix">

                        <div class="col-md-12" data-wow-delay="0.2s">
                            <div class="carousel slide" data-ride="carousel" id="quote-carousel">
                                <!-- Bottom Carousel Indicators -->
                                <!--<ol class="carousel-indicators">-->
                                    <!--<li data-target="#quote-carousel" data-slide-to="0" class="active"><img class="img-responsive " src="../../i/icbt.png" alt="">-->
                                    <!--</li>-->
                                    <!--<li data-target="#quote-carousel" data-slide-to="1"><img class="img-responsive" src="../../i/icbt.png" alt="">-->
                                    <!--</li>-->
                                    <!--<li data-target="#quote-carousel" data-slide-to="2"><img class="img-responsive" src="../../i/icbt.png" alt="">-->
                                    <!--</li>-->
                                <!--</ol>-->

                                <!-- Carousel Slides / Quotes -->
                                <div class="carousel-inner text-center">                                    
                                </div>

                                <!-- Carousel Buttons Next/Prev -->
                                <a data-slide="prev" href="#quote-carousel" class="left carousel-control"><i class="fa fa-chevron-left"></i></a>
                                <a data-slide="next" href="#quote-carousel" class="right carousel-control"><i class="fa fa-chevron-right"></i></a>
                            </div>
                        </div>


                        <!-- End about-me : right side -->
                    </div>
                    <!-- End widget content -->
                </div>
                <!-- End Widget : ABOUT -->

                <div class="widget w-rec-institute clearfix">
                    <div class="widget-header">
                        <label>Recommended Institutes</label>
                    </div>
                    <!-- End widget header -->

                    <div class="widget-content clearfix">
                        <div class="rec-institute clearfix">
                            <ul>
                            </ul>
                        </div>
                        <!-- End about-me : left side -->


                        <!-- End about-me : right side -->
                    </div>
                    <!-- End widget content -->
                </div>
                <!-- End Widget : ABOUT -->

<%--            <div class="widget w-recent-activity clearfix">
                    <div class="widget-header">
                        <label>Recent Activity</label>
                    </div>
                    <!-- End widget header -->

                    <div class="widget-content">
                        <ul class="ul-activity">
						 </ul>
                    </div>
                    <!-- End widget content -->
                </div>
                <!-- End Widget : ABOUT -->
--%>


            </div>
            <!-- End Right side -->


        </div>
        <!-- End page container -->
    </div>
</div>
<!-- End Dashboard  -->

<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/js/student/studentDashboard.js"></script>

<!-- Footer -->
<!--<footer w3-include-html="../layout/footer.html"></footer>-->
<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>

</body>
</html>