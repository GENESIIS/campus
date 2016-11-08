<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 20161027 TR c11 start styling courses filter result page -->
<!-- 20161027 TR c11 styling all category selections -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="../bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

    <!-- W3-Include -->
    <script src="http://www.w3schools.com/lib/w3data.js"></script>
	<script src="../js/filterSearch/ui-populate-helper.js"></script>
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
                <a href="../../index.jsp" class="btn-home center-block"></a>
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
                        <li><a href="category/higher-education.jsp">Higher Education</a></li>
                        <li><a href="javascript:">Corporate Training</a></li>
                        <li><a href="javascript:">Vocational Training</a></li>
                        <li><a href="javascript:">Talent & Skill</a></li>
                    </ul>
                </div>
                <!-- End Course Category tabs -->
            </div>
            <div class="keyword-search pull-right">
                <div class="search-bar">
                    <input type="text" placeholder="Keyword Search">
                    <a href="javascript:" class="colr-white">Enter</a>
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

<!-- Main Container - All-Courses -->

<div class="courses-screen clearfix">
    <!-- Page Inner Header -->
    <div class="inner-header center-block">
        <div class="main-topic">
            <h1>Choose your path</h1>
        </div>
        <div class="right-corner">
            <div class="sort-box">
                <!--<p>Sort by</p> -->
                <input type="text" name="product" list="sortBy" placeholder="Starting Soon"/>
				<datalist id="sortBy">
					<option value="1" selected>Starting Soon</option>
					<option value="2">Newest Courses</option>
				</datalist>
            </div>
        </div>
    </div>

    <!-- Filtering Area Container -->
    <div class="filter-area clearfix">
        <div class="category-panel">
            <h1 class="page-topic-t1">| All Courses </h1>

            <!-- All Category -->
            <div class="all-category">

                <!-- Category - All -->
                <div class="filter-item">
                    <!-- Drop item header -->
                    <div  class="item-header">
                        <label>All <span id="courseCount" name="courseCount"></span></label>
                        <a href="javascript:"><input type="checkbox"></a>
                    </div>
                </div>

                <!-- 1st Category - Categories -->
<!--                 <div class="filter-item clearfix"> -->
<!--                     Drop item header -->
<!--                     <div  class="item-header flip"> -->
<!--                         <label>Categories <span>- 06</span></label> -->
<!--                         <a href="javascript:"><input type="checkbox"></a> -->
<!--                     </div> -->
<!--                     Item Drop list -->
<!--                     <div class="item-container slideable"> -->
<!--                         <div class="select-item row-fluid"> -->
<!--                             <a href="javascript:"><input type="checkbox"></a> -->
<!--                             <label>Pre Education</label> -->
<!--                         </div> -->
<!--                         <div class="select-item row-fluid"> -->
<!--                             <a href="javascript:"><input type="checkbox"></a> -->
<!--                             <label>School Education</label> -->
<!--                         </div> -->
<!--                         <div class="select-item row-fluid"> -->
<!--                             <a href="javascript:"><input type="checkbox"></a> -->
<!--                             <label>Higher Education</label> -->
<!--                         </div> -->
<!--                         <div class="select-item row-fluid"> -->
<!--                             <a href="javascript:"><input type="checkbox"></a> -->
<!--                             <label>Corporate Education</label> -->
<!--                         </div> -->
<!--                         <div class="select-item row-fluid"> -->
<!--                             <a href="javascript:"><input type="checkbox"></a> -->
<!--                             <label>Vocational Training</label> -->
<!--                         </div> -->
<!--                         <div class="select-item row-fluid"> -->
<!--                             <a href="javascript:"><input type="checkbox"></a> -->
<!--                             <label>Talent & Skill</label> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->

                <!-- 2nd Category - Major -->
<!--                 <div class="filter-item clearfix"> -->
<!--                     Drop item header -->
<!--                     <div  class="item-header flip"> -->
<!--                         <label>Major <span>- 02</span></label> -->
<!--                         <a href="javascript:"><input type="checkbox"></a> -->
<!--                     </div> -->
<!--                     Item Drop list -->
<!--                     <div class="item-container slideable"> -->
<!--                         <div class="select-item row-fluid"> -->
<!--                             <a href="javascript:"><input type="checkbox"></a> -->
<!--                             <label>Engineering</label> -->
<!--                         </div> -->
<!--                         <div class="select-item row-fluid"> -->
<!--                             <a href="javascript:"><input type="checkbox"></a> -->
<!--                             <label>Arts</label> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->

                <!-- 2nd Category - Major -->
<!--                 <div class="filter-item clearfix"> -->
<!--                     Drop item header -->
<!--                     <div  class="item-header flip"> -->
<!--                         <label class="flip">Levels <span>- 08</span></label> -->
<!--                         <a href="javascript:"><input type="checkbox"></a> -->
<!--                     </div> -->
<!--                     Item Drop list -->
<!--                     <div class="item-container slideable"> -->
<!--                         <div class="select-item row-fluid"> -->
<!--                             <a href="javascript:"><input type="checkbox"></a> -->
<!--                             <label>Degree</label> -->
<!--                         </div> -->
<!--                         <div class="select-item row-fluid"> -->
<!--                             <a href="javascript:"><input type="checkbox"></a> -->
<!--                             <label>PHD</label> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->


			<!-- 2nd Category - Major -->
					<div class="filter-item clearfix">
						<!-- Drop item header -->
						<div class="item-header">
							<label slide-toggle="#dropItem1">Major <span
								id="majorCount" name="majorCount"></span>
							</label> <a href="javascript:"></a>
						</div>

						<!-- Item Drop list -->
						<div id="dropItem1" class="item-container slideable">
							<ul id="select-item1" class="select-item row-fluid">
								<li>Please choose Educational area</li>
							</ul>
						</div>
					</div>

					<div class="filter-item clearfix">
						<!-- Drop item header -->
						<div class=""></div>
						<!-- Item Drop list -->
						<div id="" class="">
							<button type="button" class="btn btn-primary"
								onclick="addsearchData()">Apply Search</button>
						</div>
					</div>

				</div>
            <!-- End All category -->
        </div>
<!--         <div class="result-panel"> -->
<!--             Panel Header -->
<!--             <div class="panel-header clearfix"> -->
<!--                 <div class="filter-path"> -->
<!--                     <ol class="list-inline"> -->
<!--                         <li>Courses</li> -->
<!--                         <li>/ All</li> -->
<!--                     </ol> -->
<!--                 </div> -->

<!--                 Search drop down boxes bar -->
<!--                 <div class="search-boxes-bar clearfix"> -->
<!--                     <div class="institute-col col-md-4 col-lg-4"> -->
<!--                         <div class="drop-holder"> -->
<!--                             <input type="text" name="product" list="institueName" placeholder="Institute"/> -->
<!--                             <datalist id="institueName"> -->
<!--                                 <option value="ICBT"></option> -->
<!--                                 <option value="IIT"></option> -->
<!--                                 <option value="NIBM"></option> -->
<!--                             </datalist> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="district-col col-md-4 col-lg-4"> -->
<!--                         <div class="drop-holder"> -->
<!--                             <input type="text" name="product" list="District" placeholder="District"/> -->
<!--                             <datalist id="District"> -->
<!--                                 <option value="Kurunegala"></option> -->
<!--                                 <option value="Colombo"></option> -->
<!--                                 <option value="Gampaha"></option> -->
<!--                             </datalist> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="town-col col-md-4 col-lg-4"> -->
<!--                         <div class="drop-holder"> -->
<!--                             <input type="text" name="product" list="Town" placeholder="Town"/> -->
<!--                             <datalist id="Town"> -->
<!--                                 <option value="Colombo"></option> -->
<!--                                 <option value="Kurunegala"></option> -->
<!--                                 <option value="Kandy"></option> -->
<!--                             </datalist> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--             End Panel Header -->
<!--             <hr> -->

<!--             Search Result List view -->
<!--             <div class="search-result-view clearfix"> -->
<!--                 <div class="result-box clearfix"> -->
<!--                     <div class="course-name"> -->
<!--                         <a href="javascript:">Bsc (Hons) in Information Technology <span class="provider-name"> @ICBT</span></a> -->
<!--                     </div> -->
<!--                     <div class="course-info"> -->
<!--                         <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text.</p> -->
<!--                     </div> -->
<!--                     <div class="provider-info"> -->
<!--                         <a href="javascript:"><img src="../i/icbt.png" alt="ICBT"></a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="result-box clearfix"> -->
<!--                     <div class="course-name"> -->
<!--                         <a href="javascript:">Bsc (Hons) in Information Technology <span class="provider-name"> @ICBT</span></a> -->
<!--                     </div> -->
<!--                     <div class="course-info"> -->
<!--                         <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text.</p> -->
<!--                     </div> -->
<!--                     <div class="provider-info"> -->
<!--                         <a href="javascript:"><img src="../i/icbt.png" alt="ICBT"></a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="result-box clearfix"> -->
<!--                     <div class="course-name"> -->
<!--                         <a href="javascript:">Bsc (Hons) in Information Technology <span class="provider-name"> @ICBT</span></a> -->
<!--                     </div> -->
<!--                     <div class="course-info"> -->
<!--                         <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text.</p> -->
<!--                     </div> -->
<!--                     <div class="provider-info"> -->
<!--                         <a href="javascript:"><img src="../i/icbt.png" alt="ICBT"></a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->

<!--         </div> -->

			<div class="result-panel">
				<!-- Panel Header -->
				<div class="panel-header clearfix">
					<div class="filter-path">
						<ol class="list-inline">
							<li>Courses</li>
							<li>/ All</li>
						</ol>
					</div>

					<!-- Search drop down boxes bar -->
					<div class="search-boxes-bar clearfix">
						<!-- Added By PN -->
						<div class="category-col col-md-4 col-lg-4">
							<div class="drop-holder">
								<input type="text" id="categorylist" name="categorylist"
									list="categoryName" placeholder="-- Select Educational Area --"
									oninput='displayDetailsOnChange()' />
								<datalist id="categoryName">
								</datalist>
							</div>
						</div>

						<div class="institute-col col-md-4 col-lg-4">
							<div class="drop-holder">
								<input type="text" id="instituelist" name="instituelist"
									list="institueName" placeholder="-- Select Institute --"
									oninput='displayDistricts()' />
								<datalist id="institueName">
								</datalist>
							</div>
						</div>
						<div class="district-col col-md-4 col-lg-4">
							<div class="drop-holder">
								<input type="text" name="districtlist" id="districtlist"
									list="districtName" placeholder="-- Select District --" />
								<datalist id="districtName">
								</datalist>
							</div>
						</div>
					</div>
				</div>
				<!-- End Panel Header -->
				<hr>
				<div class="">
					<table id="example"
						class="table table-striped table-bordered dt-responsive nowrap"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Institute</th>
								<th>Course Details</th>
								<th>Starting Date</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							</tr>
							<tr>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
</div>
<!-- End Main Container - All-Courses -->

<!-- Footer -->
<footer w3-include-html="layout/footer.jsp"></footer>

<!-- jQuery & Other js -->
<script src="../bower-components/jquery/jquery.min.js"></script>
<script src="../bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="../bower-components/bootstrap/bootstrap.min.js"></script>
<script src="../js/main.js"></script>

</body>
</html>