<!-- 20170221 PN CAM-48: INIT test JSP page to implement CP image upload function. -->
<!-- 20170223 PN CAM-48: modifingy UI elements to add front end validations. no script tag added to the JSP page.. -->
<!-- 20170226 PN CAM-48: modified UI elements to display images on change event of cp_img_type dropdown. delete button created. -->
<!-- 20170417 DK CAM-48: Orinal Code was commented to develop the BS modal structure. -->
<!-- 20170417 DK CAM-48: Develop upload course provider logo modal. -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Upload Course Provider images</title>
	<!-- Bootstrap CSS Styles -->
	<link href="/dist/bower-components/bootstrap/bootstrap.min.css"	rel="stylesheet">
	<!-- FancyBox CSS Styles -->
	<link href="/dist/bower-components/fancybox/dist/jquery.fancybox.css" rel="stylesheet">
	<!-- FontAwsome CSS Styles -->
	<link href="/dist/bower-components/fonts/faw/css/faw.css" rel="stylesheet">	
	<link href="/dist/css/style.css" rel="stylesheet">	
</head>
<body>

	<noscript><b>Sorry, your browser does not support JavaScript!</b></noscript>
	
	<form class="form-course-provider" name="form_CourseProviderDetails" method="POST" action="">
		<button type="submit" class="btn btn-default upload-btn">
			<i class="fa fa-file-image-o" aria-hidden="true"></i> Upload Logos
		</button>
	</form>

	<form class="form-course-provider-logo" name="form_CourseProviderLogo" method="POST" action="">
		<div class="modal fade upload-content" id="course-provider-logo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							<i class="fa fa-picture-o" aria-hidden="true"></i>
							Upload Course Provider Logo
						</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
				  </div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row upload-form-content">
							<div class="col-sm-3">
								<strong>Display Type</strong>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label for="type-large" class="radio-inline"><input type="radio" id="type-large" name="logoSizeType" value="Large">Large</label>
									<label for="type-medium" class="radio-inline"><input type="radio" id="type-medium" name="logoSizeType" value="Medium">Medium</label>
									<label  for="type-small" class="radio-inline"><input type="radio" id="type-small" name="logoSizeType" value="Small">Small</label> 
								</div>
							</div>
							<div class="col-sm-5">
								<div class="form-group">
									<label class="img-info">
										<i class="fa fa-info-circle" aria-hidden="true"></i>
										Larger logo is to display on the homepage. The default size is 1600 x 675px.
									</label>
								</div>
							</div>
						</div>					
						<div class="row upload-form-content">
							<div class="col-sm-3">
								<strong>Upload Image</strong>
							</div>
							<div class="col-sm-9">
								<div class="form-group file-upload">
									<label class="btn btn-default btn-file">
										<input type="file" id="upload-img" hidden>
									</label>
								</div>
							</div>
						</div>
						<div class="row upload-form-content">
							<div class="col-sm-3">

							</div>
							<div class="col-sm-9">
								<div class="form-group">
									<button type="button" class="btn btn-upload btn-default btn-sm">
										<i class="fa fa-cloud-upload" aria-hidden="true"></i> Upload Image
									</button>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<table class="tbl-logo-list table">
									<thead>
										<tr>
											<th></th>
											<th>Image Name</th>
											<th>Image Type</th>
											<th>Image Size</th>
											<th>Display Type</th>
											<th>Modified</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<input type="checkbox" name="optLogo1" value="" />
											</td>
											<td>
												<a class="thumb-img" href="/dist/i/uploadCpImages/uploads/linkedin_large.png" title="">
													<img src="/dist/i/uploadCpImages/uploads/linkedin_large.png" alt="" />
												</a>CourseProviderLogo_Large
											</td>
											<td>image/jpeg</td>
											<td>1200 x 650 px</td>
											<td>Large</td>
											<td>3/23/2017 12:31:40 PM</td>
											<td class="action-list">
												<a class="remove-item action-item fa fa-trash-o" aria-hidden="true">&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" name="optLogo2" value="" />
											</td>
											<td>
												<a class="thumb-img" href="/dist/i/uploadCpImages/uploads/linkedin_medium.png" title="">
													<img src="/dist/i/uploadCpImages/uploads/linkedin_medium.png" alt="" />
												</a>CourseProviderLogo_Med
											</td>
											<td>image/jpeg</td>
											<td>1200 x 650 px</td>
											<td>Medium</td>
											<td>3/23/2017 12:31:40 PM</td>
											<td class="action-list">
												<a class="remove-item action-item fa fa-trash-o" aria-hidden="true">&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" name="optLogo3" value="" />
											</td>
											<td>
												<a class="thumb-img" href="/dist/i/uploadCpImages/uploads/linkedin_small.png" title="">
													<img src="/dist/i/uploadCpImages/uploads/linkedin_small.png" alt="" />
												</a>CourseProviderLogo_Small
											</td>
											<td>image/jpeg</td>
											<td>1200 x 650 px</td>
											<td>Small</td>
											<td>3/23/2017 12:31:40 PM</td>
											<td class="action-list">
												<a class="remove-item action-item fa fa-trash-o" aria-hidden="true">&nbsp;</a>
											</td>
										</tr>
									</tbody>
							  </table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-close btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-save btn-default">Save changes</button>
				</div>
				</div>
			</div>
		</div>
	</form>

<!--	Orinal Code was commented to develop the BS modal structure - 20170417 DK CAM-48

		<select id="cp_img_type" name="cp_img_type" required>
			<option value="">--Select Type--</option>
		</select>	
		<span id="cp_img_desc" name="cp_img_desc"></span>
		<input type="file" id="cp_img_upload" name="cp_img_upload" accept="image/*" onchange="validateFile(this, 'cp_img_upload', 'cp_img_upload_btn')">
		<input type="submit" id="cp_img_upload_btn" name="cp_img_upload_btn" value="Upload Image">
		<input type="submit" id="cp_img_delete_btn" name="cp_img_delete_btn" value="Delete Image">
		<br>
		<span id="cp_img_err" name="cp_img_err"></span>
		<img id="cp_img_display" name="cp_img_display" src="" alt="" width="" height="">
		
-->	

	<!-- jQuery Library -->
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<!-- Bootstrap JS -->
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<!-- FancyBox JS -->	
	<script src="/dist/bower-components/fancybox/dist/jquery.fancybox.js"></script>
	<!-- JS & jQuery Scripts -->
	<script src="/dist/js/imageUpload/sitescripts.js" type="text/javascript"></script>
	<script src="/dist/js/imageUpload/cp_img_upload_helper.js" type="text/javascript"></script>

</body>
</html>