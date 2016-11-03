<!-- 20161027 TR c11 start styling courses filter result page -->
<!-- 20161027 TR c11 styling all category selections -->
<!-- 20161101 PN c11-criteria-based-filter-search modified UI elements to display DB values on them. -->
<!-- 20161102 PN c11-criteria-based-filter-search implement data table to display courses. -->
<!-- 20161103 PN c11-criteria-based-filter-search modified the UI elements by removing unnecessary comments. -->

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
						<label>All <span id="courseCount" name="courseCount"></span></label> <a href="javascript:"></a>
					</div>
				</div>

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
					<div class="">
					</div>
					<!-- Item Drop list -->
					<div id="" class="">
						<button type="button" class="btn btn-primary" onclick="addsearchData()">Apply Search</button> 
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
								oninput='displayDetailsOnChange()' />
							<datalist id="categoryName">
							</datalist>
						</div>
					</div>

					<div class="institute-col col-md-4 col-lg-4">
						<div class="drop-holder">
							<input type="text" id="instituelist" name="instituelist"
								list="institueName" placeholder="-- Select Institute --" oninput='displayDistricts()' />
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
				<script type="text/javascript">
					$(document).ready(function() {
						$('#example').DataTable();
					})
				</script>
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


