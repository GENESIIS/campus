<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 20161025 TR c1 start style header bottom part -->
<!-- 20161025 TR c1 landing page layout - done -->
<!-- 20161025 TR c1 main menu bar - done -->
<!-- 20161025 TR c1 main search bar - done -->
<!-- 20161025 TR c1 start styling category search area -->
<!-- 20161111 PN c1-campus-landing-page modified the UI to load categories from the DB values dynamically. -->

<div class="main-screen clearfix">
	<div class="content-panel clearfix">
		<div class="main-slider clearfix">
			<img src="dist/i/slide-1.jpg" alt="">
		</div>
		<!-- End Main slider -->
		<div class="search-area clearfix">
			<div class="main-search-bar center-block clearfix">
				<div class="bar-holder center-block clearfix">
					<input class="center-block" type="text"
						placeholder="Search : Program, Course, or Career   e.g: accounting, accountant ">
				</div>
				
				<input type="radio" name="courseProvider" value="CP"> Course Provider<br>
                <input type="radio" name="courseProvider" value="P"> Programme<br>
                <input type="button" name="Search" value="Search">
			</div>
			<!-- End Main search bar -->

			<div class="filter-boxes clearfix">
				<div class="box-holder center-block">
					<ul class="list-inline" name="mainCategoryList"
						id="mainCategoryList">




					</ul>
				</div>

			</div>
			<!-- End category boxes area -->
		</div>
	</div>

	<div class="banner-panel pull-right clearfix">Advertise Here</div>
</div>