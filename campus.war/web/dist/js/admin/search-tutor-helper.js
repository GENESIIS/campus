/**
 * This java script file is to support admin list tutor function
 * 20170117 JH C133-admin-list-tutor
 */

window.tutorList = null;

window.onload = function() {
	listAllTutors();
};

function listAllTutors(){
	$.ajax({
		url : '/AdminController',
		method : 'POST',
		data : {
			'CCO' : 'LIST_TUTORS'
		},
		dataType : "json",
		async : false,
		success : function(response) {

			if (response !== undefined && response !== null) {
				window.tutorList = response.result;
				if(tutorList){
					alert(tutorList);
				}
			}
		},
	});
}