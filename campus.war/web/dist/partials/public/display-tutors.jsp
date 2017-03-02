<!-- 20170227 TR c95 INIT display-tutors.html -->
<!-- 20170227 TR c95 Added header and import all bower-components -->
<!-- 20170227 TR c95 Added page container -->
<!-- 20170302 JH c96 INIT display-tutors.jsp with display-tutors.html page-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="../../bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/style.css" rel="stylesheet">

    <!-- Data Table CSS -->
    <link href="../../bower-components/datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../bower-components/datatable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- jQuery & Other js -->
    <script type="text/javascript" src="../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    <script src="../../bower-components/datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../bower-components/datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../bower-components/datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../bower-components/datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../js/main.js"></script>

</head>
<body>

<!-- Header-->
<header class="header col-lg-12 col-md-12 col-sm-12 clearfix">
    <div class="top">
        <div class="logo-brand">
            <h1 class="logo-txt">Campus.lk</h1>
        </div>
    </div>
    <div class="bottom">
        <div class="menu-bar">
            <div class="home pull-left">
                <a href="../../../index.jsp" class="btn-home center-block"></a>
            </div>
            <!-- End home button -->
            <div class="menu-tabs clearfix">
                <!-- Main menu tabs -->
                <div class="top-menus">
                    <ul class="list-inline">
                        <li><a href="javascript:">All Courses</a></li>
                        <li><a href="about-us.jsp">About Us</a></li>
                        <li><a href="contact-us.jsp">Contact Us</a></li>
                        <li><a href="news.jsp">News</a></li>
                        <li><a href="f-and-q.jsp">F & Q</a></li>
                        <li><a href="rss.jsp">Rss</a></li>
                    </ul>
                </div>
                <!-- End Main menu tabs -->

                <!-- Course Category tabs -->
                <div class="bottom-menus">
                    <ul class="list-inline">
                        <li><a href="javascript:">Pre Education</a></li>
                        <li><a href="javascript:">School Education</a></li>
                        <li><a href="category/higher-education.jsp">Higher
                            Education</a></li>
                        <li><a href="javascript:">Corporate Training</a></li>
                        <li><a href="javascript:">Vocational Training</a></li>
                        <li><a href="javascript:">Talent & Skill</a></li>
                    </ul>
                </div>
                <!-- End Course Category tabs -->
            </div>
            <div class="keyword-search pull-right">
                <div class="search-bar">
                    <!--<input type="text" placeholder="Keyword Search"> <a href="javascript:" class="colr-white">Enter</a>-->
                </div>
                <!-- End Keyword Search -->
                <div class="login-link">
                    <a href="javascript:" class="colr-white">Login</a>
                </div>
            </div>
            <!-- End keyword search -->
        </div>
    </div>
</header>
<!-- End Header -->

<div class="tutors-screen clearfix">
    <!-- Page Inner Header -->
    <div class="inner-header center-block">
        <div class="main-topic">
            <h1>Choose your path</h1>
        </div>
    </div>
    <!-- End theme bar -->

    <div class="screen-container clearfix">
        <div class="col-md-3 col-lg-3">
            <h1 class="page-topic-t1">| All Tutors</h1>
        </div>
        <div class="col-md-9 col-lg-9">
            <div class="filter-path">
                <ol class="list-inline">
                    <li>Tutors</li>
                    <li>/ All</li>
                </ol>
            </div>
        </div>
        <!-- End inner header -->

        <div class="col-md-12 col-lg-12 col-sm-12">
            <div class="result-panel">
                <!-- Panel Header -->
                <div class="panel-header clearfix">
                    <!-- Search drop down boxes bar -->
                    <div class="search-boxes-bar clearfix">

                        <div class="category-col col-md-4 col-lg-4">
                            <div class="drop-holder">
                                <input type="text" placeholder="Educational Area"/>
                            </div>
                        </div>

                        <div class="institute-col col-md-4 col-lg-4">
                            <div class="drop-holder">
                                <input type="text" placeholder="Subject Area "/>
                            </div>
                        </div>

                        <div class="district-col col-md-4 col-lg-4">
                            <div class="drop-holder">
                                <input type="text" placeholder="Location" />
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Panel Header -->
                <hr>
                <div class="search-result-view clearfix">
                    <table  class="table table-striped table-bordered dt-responsive" cellspacing="0" width="">
                        <thead>
                            <tr>
                                <th>Profile</th>
                                <th>Contact Details</th>
                                <th>Subjects</th>
                                <th>Qualifications</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td>
                                    <div class="tutor-prof-image">
                                        <a href="javascript:">
                                            <img src="../../i/public/tutors/pfr-image.jpg" alt="">
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <div class="t-contact-details">
                                        <a href="javascript:">Anil K Senadheera</a>
                                        <h3>Mobile : 	<span>0777 123 456</span></h3>
                                        <h3>Office  :	<span>011 7 123 456</span></h3>
                                        <h3>Email :	<span>test@gmail.com</span></h3>
                                        <h3>District :	<span>Colombo</span></h3>
                                    </div>
                                </td>
                                <td>
                                    <div class="t-subjects">
                                        <ul>
                                            <li>- Mathematics </li>
                                            <li>- Computer Science  </li>
                                            <li>- ICT  </li>
                                            <li>- Software Engineering </li>
                                            <li>- Computer Science  </li>
                                        </ul>
                                    </div>
                                </td>
                                <td>
                                    <div class="t-qualifications clearfix">
                                        <label for="">Educational Area  :</label>
                                        <ul>
                                            <li>- School Education </li>
                                            <li>- Higher Education </li>
                                        </ul>

                                        <label for="">Educational Area  :</label>
                                        <ul>
                                            <li> - BSc. Engineering</li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <!-- 1st row -->

                            <tr>
                                <td>
                                    <div class="tutor-prof-image">
                                        <a href="javascript:">
                                            <img src="../../i/public/tutors/pfr-image.jpg" alt="">
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <div class="t-contact-details">
                                        <a href="javascript:">Anil K Senadheera</a>
                                        <h3>Mobile : 	<span>0777 123 456</span></h3>
                                        <h3>Office  :	<span>011 7 123 456</span></h3>
                                        <h3>Email :	<span>test@gmail.com</span></h3>
                                        <h3>District :	<span>Colombo</span></h3>
                                    </div>
                                </td>
                                <td>
                                    <div class="t-subjects">
                                        <ul>
                                            <li>- Mathematics </li>
                                            <li>- Computer Science  </li>
                                            <li>- ICT  </li>
                                            <li>- Software Engineering </li>
                                            <li>- Computer Science  </li>
                                        </ul>
                                    </div>
                                </td>
                                <td>
                                    <div class="t-qualifications clearfix">
                                        <label for="">Educational Area  :</label>
                                        <ul>
                                            <li>- School Education </li>
                                            <li>- Higher Education </li>
                                        </ul>

                                        <label for="">Educational Area  :</label>
                                        <ul>
                                            <li> - BSc. Engineering</li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <!-- 1st row --> 

                            <tr>
                                <th>Profile</th>
                                <th>Contact Details</th>
                                <th>Subjects</th>
                                <th>Qualifications</th>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- End result-panel -->
    </div>

</div>
<!-- End All-tutors screen -->

<!-- Footer -->
<footer>
    <div class="ft-top"></div>
    <div class="ft-bottom text-center">
        <label for="Copyright">Copyright � Campus.lk</label>
    </div>
</footer>
<!-- End Footer -->
</body>
</html>