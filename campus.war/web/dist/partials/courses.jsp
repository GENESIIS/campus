<!-- 20161027 TR c11 start styling courses filter result page -->
<!-- 20161027 TR c11 styling all category selections -->


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
					<option value="Latest"></option>
					<option value="Latest"></option>
					<option value="Latest"></option>
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

				<!-- 1st Category - Categories -->
				<div class="filter-item clearfix">
					<!-- Drop item header -->
					<div class="item-header">
						<label slide-toggle="#dropItem1">Categories <span>-
								06</span>
						</label> <a href="javascript:"><input name="Category" type="checkbox"></a>
					</div>


					<!-- Item Drop list -->
					<div id="dropItem1" class="item-container slideable">
						<div class="select-item row-fluid">
							<a href="javascript:"><input name="Category" type="checkbox"></a>
							<label>Pre Education</label>
						</div>
						<!--                         <div class="select-item row-fluid"> -->
						<!--                             <a href="javascript:"><input name="Category" type="checkbox"></a> -->
						<!--                             <label>School Education</label> -->
						<!--                         </div> -->
						<!--                         <div class="select-item row-fluid"> -->
						<!--                             <a href="javascript:"><input name="Category" type="checkbox"></a> -->
						<!--                             <label>Higher Education</label> -->
						<!--                         </div> -->
						<!--                         <div class="select-item row-fluid"> -->
						<!--                             <a href="javascript:"><input name="Category" type="checkbox"></a> -->
						<!--                             <label>Corporate Education</label> -->
						<!--                         </div> -->
						<!--                         <div class="select-item row-fluid"> -->
						<!--                             <a href="javascript:"><input name="Category" type="checkbox"></a> -->
						<!--                             <label>Vocational Training</label> -->
						<!--                         </div> -->
						<!--                         <div class="select-item row-fluid"> -->
						<!--                             <a href="javascript:"><input name="Category" type="checkbox"></a> -->
						<!--                             <label>Talent & Skill</label> -->
						<!--                         </div> -->
					</div>
				</div>

				<!-- 2nd Category - Major -->
				<div class="filter-item clearfix">
					<!-- Drop item header -->
					<div class="item-header">
						<label slide-toggle="#dropItem2">Major <span>- 02</span></label> <a
							href="javascript:"><input type="checkbox"></a>
					</div>
					<!-- Item Drop list -->
					<div id="dropItem2" class="item-container slideable">
						<div class="select-item row-fluid">
							<a href="javascript:"><input type="checkbox"></a> <label>Engineering</label>
						</div>
						<div class="select-item row-fluid">
							<a href="javascript:"><input type="checkbox"></a> <label>Arts</label>
						</div>
					</div>
				</div>

				<!-- 2nd Category - Major -->
				<div class="filter-item clearfix">
					<!-- Drop item header -->
					<div class="item-header">
						<label slide-toggle="#dropItem3">Levels <span>- 08</span></label>
						<a href="javascript:"><input type="checkbox"></a>
					</div>
					<!-- Item Drop list -->
					<div id="dropItem3" class="item-container slideable">
						<div class="select-item row-fluid">
							<a href="javascript:"><input type="checkbox"></a> <label>Degree</label>
						</div>
						<div class="select-item row-fluid">
							<a href="javascript:"><input type="checkbox"></a> <label>PHD</label>
						</div>
					</div>
				</div>

				<input name="tags" id="mySingleField" value="Apple, Orange"
					disabled="true">
				<!-- only disabled for demonstration purposes -->
				<ul id="singleFieldTags"></ul>



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
							<input type="text" id="categoryist" name="categoryist"
								list="categoryName" placeholder="Educational Area"
								oninput='displayMajor()' />
							<datalist id="categoryName">
								<option value="ICBT"></option>
								<option value="IIT"></option>
								<option value="NIBM"></option>
							</datalist>
						</div>
					</div>

					<div class="institute-col col-md-4 col-lg-4">
						<div class="drop-holder">
							<input type="text" name="product" list="institueName"
								placeholder="Institute" />
							<datalist id="institueName">
								<option value="ICBT"></option>
								<option value="IIT"></option>
								<option value="NIBM"></option>
							</datalist>
						</div>
					</div>
					<div class="district-col col-md-4 col-lg-4">
						<div class="drop-holder">
							<input type="text" name="product" list="District"
								placeholder="District" />
							<datalist id="District">
								<option value="Kurunegala"></option>
								<option value="Colombo"></option>
								<option value="Gampaha"></option>
							</datalist>
						</div>
					</div>
					<div class="town-col col-md-4 col-lg-4">
						<div class="drop-holder">
							<input type="text" name="product" list="Town" placeholder="Town" />
							<datalist id="Town">
								<option value="Colombo"></option>
								<option value="Kurunegala"></option>
								<option value="Kandy"></option>
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

<script>
        $(function(){
            var sampleTags = ['c++', 'java', 'php', 'coldfusion', 'javascript', 'asp', 'ruby', 'python', 'c', 'scala', 'groovy', 'haskell', 'perl', 'erlang', 'apl', 'cobol', 'go', 'lua'];
            //-------------------------------
            // Single field
            //-------------------------------
            $('#singleFieldTags').tagit({
                availableTags: sampleTags,
                // This will make Tag-it submit a single form value, as a comma-delimited field.
                singleField: true,
                singleFieldNode: $('#mySingleField')
            });
        });
    </script>