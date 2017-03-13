/**
 * 20170302 JH c96 public-list-tutor-helper.js created to help list tutors for the public
 * 20170306 JH c96 removed unwanted lines and display tutor image with static system config file path, added new method selectTutorRecord(code),
 * 					load tutor images using system config file path with loading default image on error source,show error messages on AJAX calls
 * 20170309 JH c96 changed the tutor table element id 'example' into 'tutorListTable' due to changes in jsp page
 * 20170310 JH c96 selectTutorRecord() method modified to display tutor private details with mobile, office, email and the town details wip
 * 20170313 JH c96 listPublicTutors() and DisplayTutorTable() methods modified to display tutor major, category and qualification details wip
 */

window.tutorList = null;
window.tutorProfileImagePath = null;
window.majorList = null;
window.categoryList = null;
window.qualificationList = null;

window.onload = function() {
	listPublicTutors();
};

/**
 * Creates a the database call to retrieve list of tutors 
 * @returns
 * @author JH
 */
function listPublicTutors(){
	$.ajax({
		url : '/PublicController',
		method : 'POST',
		data : {
			'CCO' : 'PUBLIC_LIST_ALL_TUTORS'
		},
		dataType : "json",
		async : false,
		success : function(response) {


			if (response !== undefined && response !== null) {
				window.tutorList = response.result;
				window.tutorProfileImagePath = response.tutorProfileImagePath;
				window.majorList =  response.majorMap;
				window.categoryList = response.categoryMap;
				window.qualificationList = response.qualificationMap;

				DisplayTutorTable();
//			    $('#tutorListTable tbody').on('click', 'tr', function () {
//			  	  var tutorCode = $(this).find("td:first").html();
//				   
//				   alert("will direct to tutor public profile. and the tutor is " + tutorCode);
//				   var form = document.createElement('form');
//				   form.method = 'post';
//				   form.action = '/dist/partials/public/display-tutors.jsp';
//				   var input = document.createElement('input');
//				   input.type = 'text';
//			      input.name = 'tutorCode';
//			      input.value = tutorCode;
//			      form.appendChild(input);
//			      form.submit();
//			    });

			}
		},
		error : function(x, status, error) {
			var err = displayErrorMessage(x, status, error);
			document.getElementById("userMessage").style.display = "block";
			$("#userMessage").html(err);
		}
	});
}



/**
 * used to select a specific record of a tutor and pass the value to the next page
 * @param code
 * @returns
 * @author JH
 */
function selectTutorRecord(){
	  var tutorCode = $(this).find("td:first").html();
	   
	   alert("will direct to tutor public profile. and the tutor is " + tutorCode);
	   var form = document.createElement('form');
	   form.method = 'post';
	   form.action = '/dist/partials/public/display-tutors.jsp';
	   var input = document.createElement('input');
	   input.type = 'text';
      input.name = 'tutorCode';
      input.value = tutorCode;
      form.appendChild(input);
      form.submit();
}


/**
 * Used to generate the tutor table using datatables
 * @returns
 * @author JH
 */
function DisplayTutorTable(){
	var t =  $('#tutorListTable').DataTable();
	var tutors = window.tutorList;
	var rowCount = 0;
	var tutorImagePath = window.tutorProfileImagePath;
	var fileSeparator = "/";
	var extension = ".jpg";
	t.clear().draw();
	var imageFile = null;
	var defaultImage  = "default";
	var majorMap = window.majorList;
	var categoryMap = window.categoryList;
	var qualificationMap = window.qualificationList;

	if (tutors !== undefined & tutors !== null) {
		$
				.each(
						tutors,
						function(index, value) {

							 rowCount++;
							var value1 = null;
							var hiddenCode = '<input type="hidden" name="tutorCode" id="tutorCode" value="'+ value[0] +'">';
							var value2 = null;
							var value3 =  '<div>';
							var value4 = '<div>';
							
							imageFile = fileSeparator + tutorImagePath + fileSeparator + value[0] + fileSeparator + value[0]+extension;
							
							onErroImage = fileSeparator + tutorImagePath + fileSeparator + defaultImage + extension;
							
							value1 = '  <img src="' + imageFile + '" alt="" onerror="this.src = \'' + onErroImage + '\'">';
							
							value2 = '<div> <div> <a href="javascript:" onclick="">' +value[1]+ ' ' +  value[2]+ ' ' + value[3] +'</a></div>'
							+ '<br/> <div> <label>Mobile : </label> ' + value[5]+ value[6]+ ' '+  value[7]+'</div>'
							+ '<div> <label>Office : </label> ' + value[5]+ value[8]+ ' '+  value[9]+'</div>'
							+ '<div> <label>Email : </label> ' + value[4] +'</div>'
							+ '<div> <label>Town : </label> ' + value[19]+ '</div><div>';	

							var majorArrayList = majorMap[value[0]];
							$.each(majorArrayList, function(index, currentMajor) {
								
								if(currentMajor[1] != null || currentMajor[1] != undefined){
									value3 +=  '<label class="glyphicon glyphicon-minus"></label> <a href="javascript:" onclick="">' + currentMajor[1] + '</a></br>';	
								}
								
							});
							value3 += '</div>';
							
							
							var categoryArrayList = categoryMap[value[0]];
							$.each(categoryArrayList, function(index, currentCategory) {
								
								if(currentCategory[1] != null || currentCategory[1] != undefined){
									
									value4 +=  '<a href="javascript:" onclick="">' + currentCategory[1] + '</a></br>';	
								}
								
							});
							value4 += '</div>';
	
t.row.add(
									[
									 
rowCount,
											value1,
											value2,
											value3,
											value4

									]).draw(false);

			
		});
	}
	
}