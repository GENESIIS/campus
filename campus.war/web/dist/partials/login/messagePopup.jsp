<!-- 20170120 AS CAM-20 Session timeout popup message created. -->
<!-- 20170123 AS CAM-20 Set the Static login message -->
<!-- 20170125 AS CAM-21 Logout message for 'logout-popup' window created.C  -->
<!-- 20170228 AS CAM-21 User Already Logged In popup message window created  -->
<!-- 20170324 TR CAM-21 added display-msg class to massage label and styling -->
<!-- 20170327 TR CAM-21 added display-msg class to massage label and styling -->

<!-- popup massage window -->

<div class="modal fade modal-session" id="msg-popup" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="login-dialog modal-dialog" role="document">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">Close</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-12 col-md-12 col-lg-12">

							<label class="display-msg" id="popupMessage">Session has been expired! Please login again. </label>

							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!--  logout-popup window message -->
<div class="modal fade modal-logout" id="logout-popup" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="login-dialog modal-dialog" role="document">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">Close</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-12 col-md-12 col-lg-12">

							<label class="display-msg" id="popupMessage">You are Successfully logout! </label>

							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- User Already Logged In popup message -->
<div class="modal fade modal-login" id="alreadyLogged-popup" tabindex="-1" role="dialog" aria-hidden="true" >
	<div class="login-dialog modal-dialog" role="document">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">Close</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-6">

							<label class="" id="popupMessage">User Already Logged In ! </label>

							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>