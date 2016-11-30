<!-- 20161027 TR c11 start styling courses filter result page -->
<!-- 20161027 TR c11 styling all category selections -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
</head>
<body>
	<!-- Header-->
	<jsp:include page="/dist/partials/layout/header.jsp" />
	<!--End Header-->

	<!-- Main Container - All-Courses -->
	<div id="current_div"></div>
	<div>
		<input type="hidden" id="catCode" value="${param.categoryCode}" />
	</div>
	<div class="courses-screen clearfix">
		<!-- Page Inner Header -->
		<div class="inner-header center-block">
			<div class="main-topic">
				<h1>Choose your path</h1>
			</div>
			<div class="right-corner">
				<div class="sort-box">
					<!--&lt;!&ndash;<p>Sort by</p> &ndash;&gt;-->
					<!--<input type="text" name="product" list="sortBy" placeholder="Latest"/>-->
					<!--<datalist id="sortBy">-->
					<!--<option value="Latest"></option>-->
					<!--<option value="Latest"></option>-->
					<!--<option value="Latest"></option>-->
					<!--</datalist>-->
				</div>
			</div>
		</div>

		<!-- Filtering Area Container -->
		<div class="filter-area clearfix">
			<div class="category-panel">
				<h1 class="page-topic-t1">| All Providers</h1>

				<!-- All Category -->
				<div class="all-category">

					<!-- Category - All -->
					<!-- <div class="filter-item">
						Drop item header
						<div class="item-header">
							<label>All <span id="totalCount" name="totalCount"></span></label>
							<a href="javascript:"><input type="checkbox" name="allSelect" ></a>
							<a href="javascript:"><input id="selectAll" id="selectAll" type="checkbox" ></a>
						</div>
					</div> -->

					<!-- 1st Category - Categories -->
					<div class="filter-item clearfix">
						<!-- Drop item header -->
						<div class="item-header flip">
							<label>Categories <span id="catCount" name="catCount"></span></label>
							<!-- <a href="javascript:"><input id="categoryAll"  type="checkbox"></a> -->
						</div>
						<!-- Item Drop list -->
						<!-- id="categoryName" name="categoryName"  -->
						<div id="dropItem1" class="item-container slideable">
							<ul id="select-category" class="select-item row-fluid">
							</ul>
						</div>
					</div>

					<!-- 2nd Category - Course provider type  -->
					<div id="cpTypeDiv" class="filter-item clearfix">
						<!-- Drop item header -->
						<div class="item-header flip">
							<label>Provider Type<span id="cpTypeCount"
								name="cpTypeCount"></span></label> <a href="javascript:"><input
								id="cpTypeAll" type="checkbox"></a>
						</div>
						<!-- Item Drop list -->
						<div id="dropItem1" class="item-container slideable">
							<ul id="select-cpType" class="select-item row-fluid">
							</ul>
						</div>
					</div>

					<!-- 2nd Category - Major -->
					<div id="majorDiv" class="filter-item clearfix">
						<!-- Drop item header -->
						<div class="item-header flip">
							<label>Major <span id="majorCount" name="majorCount"></span></label>
							<a href="javascript:"><input id="majorAll" type="checkbox"></a>
						</div>
						<!-- Item Drop list -->
						<div id="dropItem1" class="item-container slideable">
							<ul id="select-major" class="select-item row-fluid">
							</ul>
						</div>
					</div>

					<!-- 2nd Category - Levels -->
					<div id="levelDiv" class="filter-item clearfix">
						<!-- Drop item header -->
						<div class="item-header flip">
							<label class="flip">Levels <span id="levelCount"
								name="levelCount"></span></label> <a href="javascript:"><input
								id="levelAll" type="checkbox"></a>
						</div>
						<!-- Item Drop list -->
						<div id="dropItem1" class="item-container slideable">
							<ul id="select-level" class="select-item row-fluid">
							</ul>
						</div>
					</div>
					<div class="filter-item clearfix">
						<!-- Drop item header -->
						<div class=""></div>
						<!-- Item Drop list -->
						<div id="" class="">
							<button type="button" id="addSearchData" name="addSearchData"
								class="btn btn-primary" onclick="">Apply Search</button>
							<!-- <input type="search" name="search" id="search" placeholder="name or info" /> -->
						</div>
					</div>
					<!-- End  search -->

				</div>
				<!-- End All category -->
			</div>
			<div class="result-panel">
				<!-- Panel Header -->
				<div class="panel-header clearfix">
					<div class="filter-path">
						<!--<ol class="list-inline">-->
						<!--<li>Courses</li>-->
						<!--<li>/ All</li>-->
						<!--</ol>-->
					</div>

					<!-- Search drop down boxes bar -->
					<div class="search-boxes-bar clearfix">

						<!--  <div class="district-col col-md-4 col-lg-4  pull-right">
                        <div class="drop-holder">
                            <input type="text" name="product" list="District" placeholder="District"/>
                            <datalist id="District">
                                <option value="Kurunegala"></option>
                                <option value="Colombo"></option>
                                <option value="Gampaha"></option>
                            </datalist>
                        </div>
                    </div> -->

						<div class="district-col col-md-4 col-lg-4 pull-right">
							<div class="drop-holder">
								<input type="text" name="districtlist" id="districtlist"
									list="districtName" placeholder="-- Select District --" />
								<datalist id="districtName">
								</datalist>
							</div>
							<!-- 	<div class="drop-holder">
								<select id="example-select"></select>
							</div> -->

							<!-- 	<div class="drop-holder">
								<select id="example-select"></select>
							</div> -->
						</div>
						<!-- End find district drop down -->
					</div>
				</div>
				<!-- End Panel Header -->
				<hr>

				<!-- Search Result List view -->
				<div class="search-result-view clearfix">
					<div class="result-box clearfix">
						<ul id="providerList" class="list-inline clearfix">

						</ul>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- End Main Container - All-Courses -->
	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"
		charset="utf-8"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"
		charset="utf-8"></script>
	<script src="/dist/js/main.js"></script>

	<script src="/dist/js/filterSearch/ui-provider-populate.js"></script>

	<!-- W3-Include -->
	<script src="../bower-components/w3/w3data.js"></script>
	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!--End  Footer -->
</body>
</html>