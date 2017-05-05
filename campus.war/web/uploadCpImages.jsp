<%--
20170221 PN CAM-48: INIT test JSP page to implement CP image upload function.
20170223 PN CAM-48: modifingy UI elements to add front end validations. no script tag added to the JSP page.
20170226 PN CAM-48: modified UI elements to display images on change event of cp_img_type dropdown. delete button created.
20170417 DK CAM-48: Orinal Code was commented to develop the BS modal structure.
20170417 DK CAM-48: Develop upload course provider logo modal.
20170419 PN CAM-48: modifying new UI element IDs and NAMEs to match with the initial UI.
20170502 PN CAM-163: cp image details content table modified to display more details in it.
 --%>
 
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
							Upload Course Provider Logos
						</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
				  </div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row upload-form-content">
							<div class="col-sm-3">
								<strong>Image Type</strong>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<select id="cp_img_type" name="cp_img_type" required>
											<option value="">--Select Type--</option>
									</select>
								</div>
							</div>
							<div class="col-sm-5">
								<div class="form-group">
									<label class="img-info" id="img-info-lbl" name="img-info-lbl">
										<i class="fa fa-info-circle" aria-hidden="true"></i>
										<span id="cp_img_desc" name="cp_img_desc"></span>										
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
										<input type="file" id="cp_img_upload" name="cp_img_upload" accept="image/*" onchange="validateFile(this, 'cp_img_upload', 'cp_img_upload_btn')" hidden>
									</label>
								</div>
							</div>
						</div>
						<div class="row upload-form-content">
							<div class="col-sm-3">

							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<button type="button" class="btn btn-upload btn-default btn-sm" id="cp_img_upload_btn" name="cp_img_upload_btn">
										<i class="fa fa-cloud-upload" aria-hidden="true"></i> Upload Image
									</button>
								</div>
							</div>
							<div class="col-sm-5">
								<div class="form-group">
									<label class="img-info" id="img-err-lbl" name="img-err-lbl">
										<i class="fa fa-info-circle" aria-hidden="true"></i>
										<span id="cp_img_err" name="cp_img_err"></span>										
									</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<table class="tbl-logo-list table" id="cpImageData">
									<thead>
										<tr>
											<th></th>
											<th>Image Name</th>
											<th>Dimensions</th>
											<th>Size</th>
											<th>Type</th>
											<th>Added</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
							  </table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-close btn-default" data-dismiss="modal">Close</button>
				</div>
				</div>
			</div>
		</div>
	</form>

	<!-- jQuery Library -->
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
		
	<!-- Bootstrap JS -->
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<!-- FancyBox JS -->	
	<script src="/dist/bower-components/fancybox/dist/jquery.fancybox.js"></script>
	<script src="/dist/js/imageUpload/cp_img_upload_helper.js" type="text/javascript"></script>
	
	<!-- JS & jQuery Scripts -->
	<script src="/dist/js/imageUpload/sitescripts.js" type="text/javascript"></script>
	
</body>
</html>