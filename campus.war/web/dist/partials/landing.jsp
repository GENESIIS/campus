<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 20161025 TR c1 start style header bottom part -->
<!-- 20161025 TR c1 landing page layout - done -->
<!-- 20161025 TR c1 main menu bar - done -->
<!-- 20161025 TR c1 main search bar - done -->
<!-- 20161025 TR c1 start styling category search area -->
<!-- 20170117 DJ c124-general-filter-search-programme-MP-dj  searchFunction() Implementation. -->



<div class="main-screen clearfix">
    <div class="content-panel clearfix">
        <div class="main-slider clearfix">
            <img src="dist/i/slide-1.jpg" alt="">
        </div>
        <!-- End Main slider -->
        <div class="search-area clearfix">
			<div class="main-search-bar center-block clearfix">
				<div class="bar-holder center-block clearfix">
					<input class="center-block" type="text" id="keyWord" name="keyWord"
						placeholder="Search : Program, Course, or Career   e.g: accounting, accountant ">
					<div align="center">
						<div align="center">
							<input type="radio" name="cpRadio" value="CPROVIDER"><span	align="left">Course Provider</span> 
							<input type="radio"	name="cpRadio" value="PROGRAMME"><span align="left">Programme</span>
						</div>
						<button type="button" id="addSearchData" name="addSearchData"
							class="btn btn-primary" onclick="searchFunction()">Apply Search</button>
					</div>
				</div>
			</div>
			<!-- End Main search bar -->

            <div class="filter-boxes clearfix">
                <div class="box-holder center-block">
                    <ul class="list-inline">
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

<script type="text/javascript">
function searchFunction(){
	alert("test");
	var keyWordString=$("#keyWord").val();	
	var selectedType= $('input[name=cpRadio]:checked').val();
	
	 //Retrieve course provider code list according to search parameters.
	$.ajax({
		url : '../../PublicController',
		data : {
			CCO : 'GENERAL_FILTER_SEARCH_COURSE_PROGRAMME',
			keyWordString : keyWordString,
			selectedType : selectedType
		},
		dataType : "json",
		success : function(response) {
			cpCodeList = response.codeList;
			generalSearchFlag = "TRUE";
			getProviderCodeList(cpCodeList, generalSearchFlag);
		},
		error : function(jqXHR, exception) {
			var msg = '';
			alert("general");
			if (jqXHR.status === 0) {
				msg = 'Not connect.\n Verify Network.';
			} else if (jqXHR.status == 404) {
				msg = 'Requested page not found. [404]';
			} else if (jqXHR.status == 500) {
				msg = 'Internal Server Error [500].';
			} else if (exception === 'timeout') {
				msg = 'Time out error.';
			} else {
				msg = 'Internal error is occurred. Please try again.';
			}
			alert(msg);
		}
	});
	
   // window.location.replace("/dist/partials/courses.jsp.jsp?keyWord="+keyWordString+"&selectedType="+ selectedType);
}
</script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>

