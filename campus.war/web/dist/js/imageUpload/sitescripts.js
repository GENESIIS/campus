 $(document).ready(function(){
	
	$('.form-course-provider').on('submit', function(e) {
		
		//$('.upload-content').modal('show'); 
		$('.upload-content').modal({
			backdrop: 'static',
			keyboard: false
		});
		
		e.preventDefault();
		
	});
	
	
	
});