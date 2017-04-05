<!-- 20170120 CAM-20 login popup window integrated -->
<!-- 20170308 CW c147-tutor-reset-password-cw tutor-login.js script src added. -->

<script src='/dist/js/institute/validation/validation.js'></script>
<script src='/dist/js/tutor-login.js'></script>

<div class="modal fade" id="loginPopup" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLongTitle" aria-hidden="true"
	data-backdrop="static">
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

							<form name="userLogin" class="form-vertical form-user"
								role="form" method="" action="">

								<div class="form-group has-error">
									<h5>Sign In</h5>
									<label for="error-msg" class="error-msg"><span
										class="error-txt" id="errorMesssage"></span></label>
								</div>

								<div class="form-group has-error">
									<label for="username">Username or Email <span
										class="error-txt" id="emailtbError"></span></label> <input
										type="email" class="form-control" id="email"
										placeholder="Email address" required
										onclick="clearField('emailtbError')">
								</div>

								<div class="form-group has-error">
									<label for="password">Password <span class="error-txt"
										id="passtbError"></span></label> <input type="password"
										class="form-control" id="password" placeholder="Password"
										required onclick="clearField('passtbError')">
								</div>

								<div class="form-group">
									<a class="forgot-pwd text-right" href="/dist/partials/login/emailVerification.jsp" 
										title="Forgot Password ?">Forgot Password ?</a>
								</div>

								<div class="form-group">
									<button type="button" class="btn btn-primary btn-block"
										name="CCO" id="CCO" value="SLOG"
										onclick="studentLogin(); resetLoginLabels();">Sign In</button>
								</div>

								<div class="form-group">
									<div class="checkbox pull-left">
										<label><input type="checkbox" name="remember"
											id="remember"> Keep me logged in</label>
									</div>
									<p class="new-user pull-right">
										New Here? <a href="#" title="Join Us">Join Us</a>
									</p>
								</div>

							</form>

						</div>
						<div class="col-sm-5 col-sm-push-1">

							<form name="socialLogin" class="form-vertical form-social"
								role="form" method="" action="">

								<button class="btn btn-block facebook">
									<span class="social-ico"><i class="fa fa-facebook fa-2x"></i></span>
									<span class="social-title">Login with Facebook</span>
								</button>

								<button class="btn btn-block google">
									<span class="social-ico"><i class="fa fa-google fa-2x"></i></span>
									<span class="social-title">Login with Google</span>
								</button>

								<button class="btn btn-block linkedin">
									<span class="social-ico"><i class="fa fa-linkedin fa-2x"></i></span>
									<span class="social-title">Login with LinkedIn</span>
								</button>

								<button class="btn btn-block twitter">
									<span class="social-ico"><i class="fa fa-twitter fa-2x"></i></span>
									<span class="social-title">Login with Twitter</span>
								</button>

							</form>

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>