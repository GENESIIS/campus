<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 20161025 TR c1 start style header bottom part -->
<!-- 20161025 TR c1 landing page layout - done -->
<!-- 20161025 TR c1 main menu bar - done -->
<!-- 20161025 TR c1 main search bar - done -->
<!-- 20161025 TR c1 start styling category search area -->
<!-- 20161111 PN c1-campus-landing-page modified the UI to load categories from the DB values dynamically. -->
<!-- 20170112 DJ c123-general-filter-search-course-provider-MP-dj  searchFunction() Implementation. -->
<!-- 20170123 DJ c123-general-filter-search-course-provider-MP-dj  UI integration. -->
<!-- 20170220 DJ c123-general-filter-search-course-provider-MP-dj  Merge latest UI same as cam-124-->
<!-- Bootstrap -->
   <link href="/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="main-screen clearfix">
    <div class="content-panel clearfix">
        <div class="main-slider clearfix">
            <img src="dist/i/slide-1.jpg" alt="">
        </div>
        <!-- End Main slider -->
        <div class="search-area clearfix">
        
        	<form autocomplete="on" onsubmit="return false;">			
				<div class="main-search-bar center-block clearfix">
						<div class="bar-holder center-block clearfix">
							<input id="course-search" class="center-block" type="text" name="course-search" value="Search : Program, Course, or Career" autocomplete="on">
							<input type="hidden" id="searchFcuntion" value="filterSearch" />
							<a class="panel-collapse-ico" href="#"><span class="glyphicon glyphicon-menu-down rotate"></span></a>
							<!-- End category boxes area -->
							<div class="filter-boxes clearfix">
					
								<div class="box-holder container-fluid center-block">
									<div class="row list-inline" name="mainCategoryList" id="mainCategoryList">	
										<div class="col-sm-10">		
											<label class="search-title">Search Options: </label>
											<label class="radio-inline">
												<input type="radio" id="courseOpt" name="courseOpt" value="CPROVIDER">Course Provider
											</label>
											<label class="radio-inline">
												<input type="radio" id="courseOpt" name="courseOpt" value="PROGRAMME">Program Title
											</label>
										</div>
										<div class="col-sm-2">
											<!-- <button type="submit" class="btn btn-search btn-small btn-block">Search</button> -->
											<button  type="button" id="addSearchData" name="addSearchData"
									class="btn btn-primary" >Apply Search</button>
										</div>			
									</div>
								</div>
		
							</div>
						</div>
					</div>
				</form>
				
				<!-- End Main search bar -->

            <div class="education-categories clearfix">
                <div class="category-list-wrapper center-block">
                    <ul class="category-list list-inline">
                        <!-- Pre Education -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">Pre Education</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <!-- School Education -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">School Education</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <!-- Higher Education -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">Higher Education</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <!-- Corporate Training -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">Corporate Training</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <!-- Vocational Training -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">Vocational Training</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <!-- Talent & Skill -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">Talent & Skill</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
            <!-- End category boxes area -->
        </div>
    </div>

    <div class="banner-panel pull-right clearfix">
        Advertise Here
    </div>
</div>
<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/filterSearch.js"></script>

