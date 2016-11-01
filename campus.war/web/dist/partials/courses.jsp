<!-- 20161027 TR c11 start styling courses filter result page -->
<!-- 20161027 TR c11 styling all category selections -->
<!-- 20161101 PN c11-criteria-based-filter-search modified UI elements to display DB values on them. -->

<div class="courses-screen clearfix">
	<!-- Page Inner Header -->
	<div class="inner-header center-block">
		<div class="main-topic">
			<h1>Choose your path</h1>
		</div>
		<div class="right-corner">
			<div class="sort-box">
				<!--<p>Sort by</p> -->
				<input type="text" name="product" list="sortBy" placeholder="Latest" />
				<datalist id="sortBy">
					<option value="1">Starting Soon</option>
					<option value="2">Newest Courses</option>
				</datalist>
			</div>
		</div>
	</div>

	<!-- Filtering Area Container -->
	<div class="filter-area clearfix">
		<div class="category-panel">
			<h1 class="page-topic-t1">| All Courses</h1>

			<!-- All Category -->
			<div class="all-category">

				<!-- Category - All -->
				<div class="filter-item">
					<!-- Drop item header -->
					<div class="item-header">
						<label>All <span>- 100</span></label> <a href="javascript:"><input
							type="checkbox"></a>
					</div>
				</div>

				<!-- 2nd Category - Major -->
				<div class="filter-item clearfix">
					<!-- Drop item header -->
					<div class="item-header">
						<label slide-toggle="#dropItem1">Major <span id="majorCount" name="majorCount"></span>
						</label> <a href="javascript:"><input name="Category" type="checkbox"></a>
					</div>

					<!-- Item Drop list -->
					<div id="dropItem1" class="item-container slideable">
						<ul id="select-item1" class="select-item row-fluid">
							<li>Please choose Educational area</li> 
						</ul>
					</div>
				</div>

<!-- 				2nd Category - Levels -->
				<div class="filter-item clearfix">
<!-- 					Drop item header -->
					<div class="item-header">
						<label slide-toggle="#dropItem3">Levels <span id="levelCount" name="levelCount"></span></label>
						<a href="javascript:"><input type="checkbox"></a>
					</div>
					<!-- Item Drop list -->
					<div id="dropItem3" class="item-container slideable">
						<ul id="select-item2" class="select-item row-fluid">
							<li>Please choose Educational area</li> 
						</ul>
					</div>
				</div>
			</div>
			<!-- End All category -->
		</div>
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
								oninput='displayDetails()' />
							<datalist id="categoryName">
								<option value="ICBT"></option>
								<option value="IIT"></option>
								<option value="NIBM"></option>
							</datalist>
						</div>
					</div>

					<div class="institute-col col-md-4 col-lg-4">
						<div class="drop-holder">
							<input type="text" id="instituelist" name="instituelist" list="institueName"
								placeholder="-- Select Institute --" />
							<datalist id="institueName">
							</datalist>
						</div>
					</div>
					<div class="district-col col-md-4 col-lg-4">
						<div class="drop-holder">
							<input type="text" name="districtlist" id="districtlist" list="districtName"
								placeholder="-- Select District --" />
							<datalist id="districtName">
							</datalist>
						</div>
					</div>
				</div>
			</div>
			<!-- End Panel Header -->
			<hr>

			<!-- Search Result List view -->
			<div class="search-result-view clearfix">
				<div class="result-box clearfix">
					<div class="course-name">
						<a href="javascript:">Bsc (Hons) in Information Technology <span
							class="provider-name"> @ICBT</span></a>
					</div>
					<div class="course-info">
						<p>Lorem Ipsum is simply dummy text of the printing and
							typesetting industry. Lorem Ipsum has been the industry's
							standard dummy text.</p>
					</div>
					<div class="provider-info">
						<a href="javascript:"><img src="dist/i/icbt.png" alt="ICBT"></a>
					</div>
				</div>
				<div class="result-box clearfix">
					<div class="course-name">
						<a href="javascript:">Bsc (Hons) in Information Technology <span
							class="provider-name"> @ICBT</span></a>
					</div>
					<div class="course-info">
						<p>Lorem Ipsum is simply dummy text of the printing and
							typesetting industry. Lorem Ipsum has been the industry's
							standard dummy text.</p>
					</div>
					<div class="provider-info">
						<a href="javascript:"><img src="dist/i/icbt.png" alt="ICBT"></a>
					</div>
				</div>
				<div class="result-box clearfix">
					<div class="course-name">
						<a href="javascript:">Bsc (Hons) in Information Technology <span
							class="provider-name"> @ICBT</span></a>
					</div>
					<div class="course-info">
						<p>Lorem Ipsum is simply dummy text of the printing and
							typesetting industry. Lorem Ipsum has been the industry's
							standard dummy text.</p>
					</div>
					<div class="provider-info">
						<a href="javascript:"><img src="dist/i/icbt.png" alt="ICBT"></a>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>


