<!-- 20161027 TR c11 start styling courses filter result page -->
<!-- 20161027 TR c11 styling all category selections -->
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

<!-- jQuery & Other js -->
<script src="../bower-components/bootstrap/bootstrap-3.3.7.min.js" charset="utf-8"></script>
<script src="../bower-components/jquery/jquery-3.1.1.min.js" charset="utf-8"></script>
<script src="../js/main.js"></script>

<script src="../js/filterSearch/ui-provider-populate.js"></script>

<!-- W3-Include -->
<script src="../bower-components/w3/w3data.js"></script>

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
                <a href="../../../index.html" class="btn-home center-block"></a>
            </div>
            <!-- End home button -->
            <div class="menu-tabs clearfix">
                <!-- Main menu tabs -->
                <div class="top-menus">
                    <ul class="list-inline">
                        <li><a href="javascript:">All Courses</a></li>
                        <li><a href="about-us.html">About Us</a></li>
                        <li><a href="contact-us.html">Contact Us</a></li>
                        <li><a href="news.html">News</a></li>
                        <li><a href="f-and-q.html">F & Q</a></li>
                        <li><a href="rss.html">Rss</a></li>
                    </ul>
                </div>
                <!-- End Main menu tabs -->

                <!-- Course Category tabs -->
                <div class="bottom-menus">
                    <ul class="list-inline">
                        <li><a href="javascript:">Pre Education</a></li>
                        <li><a href="javascript:">School Education</a></li>
                        <li><a href="category/higher-education.html">Higher Education</a></li>
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
            <h1 class="page-topic-t1">| All Providers </h1>

            <!-- All Category -->
            <div class="all-category">

                <!-- Category - All -->
                <div class="filter-item">
                    <!-- Drop item header -->
                    <div  class="item-header">
                        <label>All <span>- 100</span></label>
                        <a href="javascript:"><input type="checkbox"></a>
                    </div>
                </div>

                <!-- 1st Category - Categories -->
                <div class="filter-item clearfix">
                    <!-- Drop item header -->
                    <div  class="item-header flip">
                        <label>Categories <span>- 06</span></label>
                        <a href="javascript:"><input type="checkbox"></a>
                    </div>
                    <!-- Item Drop list -->  
                   <!-- id="categoryName" name="categoryName"  -->                  
                    <div id="dropItem1" class="item-container slideable">
							<ul id="select-item1" class="select-item row-fluid">
								
							</ul>
					</div>
                    
                   <!-- <div class="item-container slideable">
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <span id="categoryName" name="categoryName"></span>
                        </div>
                         <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>School Education</label>
                        </div>
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>Higher Education</label>
                        </div>
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>Corporate Education</label>
                        </div>
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>Vocational Training</label>
                        </div>
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>Talent & Skill</label>
                        </div>
                    </div> -->
                </div>

                <!-- 2nd Category - Course provider type  -->
                <div class="filter-item clearfix">
                    <!-- Drop item header -->
                    <div  class="item-header flip">
                        <label>Provider Type<span >- 03</span></label>
                        <a href="javascript:"><input type="checkbox"></a>
                    </div>
                    <!-- Item Drop list -->
                    <div class="item-container slideable">
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label><span  id="courseProviderType"	name="courseProviderType"></span></label>
                        </div>
                       <!--  <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>Institute</label>
                        </div>
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>Tutor</label>
                        </div> -->
                    </div>
                </div>

                <!-- 2nd Category - Major -->
                <div class="filter-item clearfix">
                    <!-- Drop item header -->
                    <div  class="item-header flip">
                        <label>Major <span>- 02</span></label>
                        <a href="javascript:"><input type="checkbox"></a>
                    </div>                    
                <!--     <div class="item-container slideable">
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>Engineering</label>
                        </div>
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>Arts</label>
                        </div>
                    </div> -->
                </div>

                <!-- 2nd Category - Levels -->
                <div class="filter-item clearfix">
                    <!-- Drop item header -->
                    <div  class="item-header flip">
                        <label class="flip">Levels <span id="levelList"	name="levelList">- 08</span></label>
                        <a href="javascript:"><input type="checkbox"></a>
                    </div>
                    <!-- Item Drop list -->
                    <div class="item-container slideable">
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>Degree</label>
                        </div>
                        <div class="select-item row-fluid">
                            <a href="javascript:"><input type="checkbox"></a>
                            <label>PHD</label>
                        </div>
                    </div>
                </div>

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
								<input type="text" name="districtName" id="districtName" list="districtList" placeholder="-- Select District --" />
								<datalist id="districtList">
								</datalist>
							</div>
						</div>
                    <!-- End find district drop down -->
                </div>
            </div>
            <!-- End Panel Header -->
            <hr>

            <!-- Search Result List view -->
            <div class="search-result-view clearfix">
                <div class="result-box clearfix">
                    <div class="provider-name">
                        <!-- <a href="javascript:">Bsc (Hons) in Information Technology <span class="provider-loc"> @Colombo</span></a> -->
                    </div>
                    <div class="p-info">
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text.</p>
                    </div>
                    <div class="provider-logo">
                        <a href="javascript:"><img src="../../i/icbt.png" alt="ICBT"></a>
                    </div>
                </div>
                <div class="result-box clearfix">
                    <div class="provider-name">
                        <a href="javascript:">Bsc (Hons) in Information Technology <span class="provider-loc"> @Kandy</span></a>
                    </div>
                    <div class="p-info">
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text.</p>
                    </div>
                    <div class="provider-logo">
                        <a href="javascript:"><img src="../../i/icbt.png" alt="ICBT"></a>
                    </div>
                </div>
                <div class="result-box clearfix">
                    <div class="provider-name">
                        <a href="javascript:">Bsc (Hons) in Information Technology <span class="provider-loc"> @Kurunegala</span></a>
                    </div>
                    <div class="p-info">
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text.</p>
                    </div>
                    <div class="provider-logo">
                        <a href="javascript:"><img src="../../i/icbt.png" alt="ICBT"></a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- End Main Container - All-Courses -->

<!-- Footer -->
<footer w3-include-html="../layout/footer.html"></footer>

<!-- jQuery & Other js -->


</body>
</html>