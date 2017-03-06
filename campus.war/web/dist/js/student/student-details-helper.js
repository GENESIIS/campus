/**
 * 20161125 PN this file contains all the functions to handle the student
 */
// details. implemented getAjaxData(response), displayDetails() methods.
// implemented addEducationDetails(), clearSchoolEducationForm() methods.
//20161126 PN c26-add-student-details: implemented validateForm() and modified code in addEducationDetails() method.
//20161128 PN c26-add-student-details: implemented addProfessionalExpForm() method, validateProfessionalExpForm() method and clearProfessionalExpForm() method.
// 			PN c26-add-student-details: professional details form dropdown details populate using db values.
//			PN c26-add-student-details: addProfessionalExpForm() implementation completed.
//20161129 PN c26-add-student-details: addProfessionalExpForm() method modified.
//20161204 PN c26-add-student-details: set countryCode according to the selected country, as prefixed into the phone number.
//20161205 PN c26-add-student-details: validateStudentPersonalDetails() implementation completed.
//          PN c26-add-student-details: validateStudentPersonalDetails(), addStudentPersonalDetails() and clearPersonalDetailsForm() methods modified.
//20161206 PN CAM-26 add-student-details: This JavaScript is to populate tagitUI with DB values.
//		   PN CAM-26 add-student-details: implemented addSkillDetails(),addInterestsDetails() and createObject() methods.
//20161214 PN CAM-28: getStudentData(response) method modified to load student personal details on load.
//20161214 PN CAM-28: completed front UI population with db values, Student Higher education details form.
//20161215 PN CAM-28: implemented addHigherEducationDetails() method and validateHigherEducationForm() done.
//20161215 PN CAM-28: implemented clearSchoolEducationForm() method.
//20161216 PN CAM-28: implementing datatables using DB values. -WIP
//20161220 PN CAM-28: modified table.row.add() method to set code into checkobox.
//20161220 PN CAM-28: implemented Ajax method call to pass selected checkbox values to be deleted, into servlet.
//20170103 PN CAM-28: added JavaScript method to get datatable row data back as an alert.
// 		   PN CAM-28: modified the JavaScript code to set edit data into textboxes.
//20170104 PN CAM-28: implement JavaScript methods to populate student Higher education details data table.
//20170104 PN CAM-28: implement JavaScript code to edit data taken from the data table.
//20170104 PN CAM-28: implemented radionButtonSelectedValueSet(name, SelectdValue) method to set radio button value from the DB.
//20170109 PN CAM-28: modified JavaScript code to display the updated details after saving them to the DB. Added character replacement for replace ','.
//20170110 PN CAM-28: modified JavaScript to display School Education details after save them to DB.
//20170110 PN CAM-28: modified JavaScript to display Higher Education details after save them to DB.
//20170110 PN CAM-28: modified JavaScript to display Professional Experience details after save them to DB.
//20170213 PN CAM-28: integrated student personal details model code with UI, expect Clear button coding.
//					  variable value names are changed to fix incorrect details loading on data tables. 
//20170213 PN CAM-28: modified addHigherEducationDetails() and addProfessionalExpForm() method to setting a variable to hold value for record ID.
//20170214 PN CAM-28: moved all the data manipulation functions into student-details-manipulation.js file	
//					  openPersonalDataModel();openEduDataModel();openProfExpDataModel(); implemented.
//20170306 PN CAM-150: email validation method added to sEmail text field onKeyup event.

var extStudentSkills = [];
var extStudentInterests = [];
var dataSet = null;

$(document).ready(function() {
	$('#loading-gif-id').css('display', 'block'); //loading gif appears.
	$('.editformdatabtn').prop("disabled", true); // edit buttons are now disabled.
	displayDetails();	
});

/**
 * This method will be handle the data displaying functionality.
 * @returns
 */
function displayDetails() {
	// Array holding selected row IDs
	$.ajax({
		url : '../../../StudentController',
		data : {
			CCO : 'GSD'
		},
		dataType : "json",
		success : function(response) {
			dataSet = response;
			getStudentData(response);
		},
		error : function(response) {
			alert("Error: " + response);
		}
	});
}

/**
 * This method populate data into UI elements on page loading.
 * @param response
 * @returns
 */
function getStudentData(response) {
	
	
	// Get existing skill values.
	var inputValues = createJsonObj(response.skillCollection);
	$('#studentSkills').tagsinput({
		itemValue : 'value',
		itemText : 'text',
		typeahead : {
			source : function(query) {
				return $.parseJSON(inputValues);
			}
		}
	});

	// Get skills assigned with Student
	$.each(response.stskillCollection, function(index, value) {
		extStudentSkills.push(parseInt(value[0]));
		$('#studentSkills').tagsinput('add', {
			"value" : parseInt(value[0]),
			"text" : value[1],
			"continent" : "A"
		});
	});

	// Get existing interest values.
	var inputValues2 = createJsonObj(response.interestCollection);
	$('#studentInterests').tagsinput({
		itemValue : 'value',
		itemText : 'text',
		typeahead : {
			source : function(query) {
				return $.parseJSON(inputValues2);
			}
		}
	});

	// Get interest assigned with Student
	$.each(response.stinterestCollection, function(index, value) {
		extStudentInterests.push(parseInt(value[0]));
		$('#studentInterests').tagsinput('add', {
			"value" : parseInt(value[0]),
			"text" : value[1],
			"continent" : "A"
		});
	});
	
	var rows_selected = [];
	
	var table = $('#example').DataTable({
		'columnDefs' : [ {
			'targets' : 0,
			'searchable' : false,
			'orderable' : false,
			'width' : '1%',
			'className' : 'dt-body-center',
			'render' : function(data, type, full, meta) {
				return '<input type="checkbox">';
			}
		} ],
		'order' : [ 1, 'asc' ],
		'rowCallback' : function(row, data, dataIndex) {
			// Get row ID
			var rowId = data[0];
			// If row ID is in the list of selected row IDs
			if ($.inArray(rowId, rows_selected) !== -1) {
				$(row).find('input[type="checkbox"]').prop('checked', true);
				$(row).addClass('selected');
			}
		}
	});

	table.clear().draw();
	var experienceducount = 0;
	var experience = $('#li-std-experience');
	experience.find('li').remove();
	$.each(response.stdExpCollection,function(index, value) {
		if (experienceducount < 4) {
			experience.append('<li>'+value[2].toString()+' <span class="drop-at">at</span> '+value[1].toString()+' <br><span class="drop-time"> Duration: '+value[5].toString()+'-'+value[6].toString()+'</span></li>');
		}

		experienceducount++;
						
		table.row.add([value[0].toString(),value[1].toString(),value[2].toString(),value[3].toString(),value[4].toString(),
					value[5].toString() + "<br/>" + value[6].toString(),value[7].toString(),
					'<button type="button" class="btn btn-info editstpe"><span class="glyphicon glyphicon-edit"></span></button>' ]).draw(false);
	 });

	// Handle click on checkbox
	$('#example').on('click', 'input[type="checkbox"]', function(e) {
		var $row = $(this).closest('tr');

		// Get row data
		var data = table.row($row).data();

		// Get row ID
		var rowId = data[0];

		// Determine whether row ID is in the list of selected row IDs
		var index = $.inArray(rowId, rows_selected);

		// If checkbox is checked and row ID is not in list of selected row IDs
		if (this.checked && index === -1) {
			rows_selected.push(rowId);

			// Otherwise, if checkbox is not checked and row ID is in list of
			// selected row IDs
		} else if (!this.checked && index !== -1) {
			rows_selected.splice(index, 1);
		}

		if (this.checked) {
			$row.addClass('selected');
		} else {
			$row.removeClass('selected');
		}

		// Update state of "Select all" control
		updateDataTableSelectAllCtrl(table);

		// Prevent click event from propagating to parent
		e.stopPropagation();
	});

	// This handles the edit button click.
	$('#example tbody').on('click','button',function() {
				var data = table.row($(this).parents('tr')).data();
				if (data) {
					populateProfExpDetailsForm(data);
				} else {					
					var idx = $(this).index(this);
					if (idx > 0) {
						var data = $(this).eq(idx - 1).closest('tr');
					} else {
						var data = table.row($(this).closest('tr').prev('tr'))
								.data();
					}
					populateProfExpDetailsForm(data);
				}
	 });

	// Handle click on "Select all" control
	$('thead input[name="select_all"]', table.table().container()).on('click',function(e) {
				if (this.checked) {
					$('#example tbody input[type="checkbox"]:not(:checked)')
							.trigger('click');
				} else {
					$('#example tbody input[type="checkbox"]:checked').trigger(
							'click');
				}

				// Prevent click event from propagating to parent
				e.stopPropagation();
	});

	// Handle table draw event
	table.on('draw', function() {
		// Update state of "Select all" control
		updateDataTableSelectAllCtrl(table);
	});

	// Handle form submission event
	$('#frm-example').on('submit',function(e) {
				var form = this;

				// Iterate over all selected checkboxes
				$.each(rows_selected, function(index, rowId) {
					// Create a hidden element
					$(form).append(
							$('<input>').attr('type', 'hidden').attr('name',
									'id[]').val(rowId));
					table.row('.selected').remove().draw(false);
				});

				// Output form data to a console
				$('#example-console').text(rows_selected);

				$.ajax({
					url : '../../../StudentController',
					data : {
						rows : rows_selected,
						CCO : 'DPE'
					},
					dataType : "json",
					success : function(response) {
						
						var table = $('#example').DataTable();
						table.clear().draw();

						var experienceducount = 0;
						var experience = $('#li-std-experience');
						experience.find('li').remove();

						$.each(response.result,function(index, value) {
							if (experienceducount < 4) {
								experience.append('<li>'+value[2].toString()+' <span class="drop-at">at</span> '+value[1].toString()+' <br><span class="drop-time"> Duration: '+value[5].toString()+'-'+value[6].toString()+'</span></li>');
							}

							experienceducount++;
																
							table.row.add([value[0].toString(),value[1].toString(),value[2].toString(),value[3].toString(),
										value[4].toString(),value[5].toString() + "<br/>" + value[6].toString(),value[7].toString(),
										'<button type="button" class="btn btn-info editstpe"><span class="glyphicon glyphicon-edit"></span></button>' ]).draw(false);
						});
					},
					error : function(response) {
						alert("Error: " + response);
					}
				});

				// Remove added elements
				$('input[name="id\[\]"]', form).remove();

				// Prevent actual form submission
				e.preventDefault();
	});

	
	// Disable typing '0' at the beginning of the phone number text box. Already
	// prefixed with country code.
	$("input[class='phoneNum']").on('input propertychange paste', function(e) {
		var reg = /^0+/gi;
		if (this.value.match(reg)) {
			this.value = this.value.replace(reg, '');
		}
	});

	// //Higher Education DataTable Scripts starting from here.
	var hEdurowsSelected = [];

	var higherEdutbl = $('#higherEdutbl').DataTable({
		'columnDefs' : [ {
			'targets' : 0,
			'searchable' : false,
			'orderable' : false,
			'width' : '1%',
			'className' : 'dt-body-center',
			'render' : function(data, type, full, meta) {
				return '<input type="checkbox">';
			}
		} ],
		'order' : [ 1, 'asc' ],
		'rowCallback' : function(row, data, dataIndex) {
			// Get row ID
			var rowId = data[0];
			// If row ID is in the list of selected row IDs
			if ($.inArray(rowId, hEdurowsSelected) !== -1) {
				$(row).find('input[type="checkbox"]').prop('checked', true);
				$(row).addClass('selected');
			}
		}
	});

	higherEdutbl.clear().draw();
	var highereducount = 0;
	var higheredu = $('#li-std-higheredu');
	higheredu.find('li').remove();
	$.each(response.stdHighEduCollection,function(index, value) {
		if (highereducount < 2) {
				higheredu.append('<li>'	+ value[3].toString() + '-'	+ value[4].toString() + ' <span class="drop-at"> at </span> '
								+ value[1].toString() + ' <br><span class="drop-time"> Duration: ' + value[6].toString() + ' to '
								+ value[7].toString() + '</span></li>');
		}

		highereducount++;

		higherEdutbl.row.add([value[0].toString(),value[1].toString() + "<br/>"	+ value[2].toString(),
							value[3].toString() + "<br/>" + value[4].toString(), value[9].toString() + "<br/>" + "IndexNo: " + value[8].toString(),
							value[11].toString(), value[5].toString(), value[6].toString() + "<br/>" + value[7].toString(),
							value[10].toString(),
							'<button type="button" class="btn btn-info editstpe"><span class="glyphicon glyphicon-edit"></span></button>' ]).draw(false);
	});

	// Handle click on checkbox
	$('#higherEdutbl').on('click', 'input[type="checkbox"]', function(e) {
		var $row = $(this).closest('tr');

		// Get row data
		var data = higherEdutbl.row($row).data();

		// Get row ID
		var rowId = data[0];

		// Determine whether row ID is in the list of selected row IDs
		var index = $.inArray(rowId, hEdurowsSelected);

		// If checkbox is checked and row ID is not in list of selected row IDs
		if (this.checked && index === -1) {
			hEdurowsSelected.push(rowId);

			// Otherwise, if checkbox is not checked and row ID is in list of
			// selected row IDs
		} else if (!this.checked && index !== -1) {
			hEdurowsSelected.splice(index, 1);
		}

		if (this.checked) {
			$row.addClass('selected');
		} else {
			$row.removeClass('selected');
		}

		// Update state of "Select all" control
		updatehigherEduDataTableSelectAllCtrl(higherEdutbl);

		// Prevent click event from propagating to parent
		e.stopPropagation();
	});

	// This handles the edit button click.
	$('#higherEdutbl tbody').on('click','button',function() {
				var data = higherEdutbl.row($(this).parents('tr')).data();
				if (data) {
					populateHigherEduDetailsForm(data);
				} else {
					var idx = $(this).index(this);
					if (idx > 0) {
						var data = $(this).eq(idx - 1).closest('tr');
					} else {
						var data = higherEdutbl.row(
								$(this).closest('tr').prev('tr')).data();
					}
					populateHigherEduDetailsForm(data);
				}
	});

	// Handle click on "Select all" control
	$('thead input[name="select_all"]', higherEdutbl.table().container()).on('click',function(e) {
			if (this.checked) {
				$('#higherEdutbl tbody input[type="checkbox"]:not(:checked)').trigger('click');
			} else {
				$('#higherEdutbl tbody input[type="checkbox"]:checked').trigger('click');
			}

			// Prevent click event from propagating to parent
			e.stopPropagation();
	});

	// Handle table draw event
	higherEdutbl.on('draw', function() {
		// Update state of "Select all" control
		updatehigherEduDataTableSelectAllCtrl(higherEdutbl);
	});

	// Handle form submission event
	$('#frm-hedu').on('submit',	function(e) {
						var form = this;

						// Iterate over all selected checkboxes
						$.each(hEdurowsSelected, function(index, rowId) {
							// Create a hidden element
							$(form).append(
									$('<input>').attr('type', 'hidden').attr(
											'name', 'id[]').val(rowId));
							higherEdutbl.row('.selected').remove().draw(false);
						});

						// FOR DEMONSTRATION ONLY
						// Output form data to a console
						$('#higherEdu-console').text(hEdurowsSelected);

						$.ajax({
									url : '../../../StudentController',
									data : {
										rows : hEdurowsSelected,
										CCO : 'DHE'
									},
									dataType : "json",
									success : function(response) {
										var higherEdutbl = $('#higherEdutbl')
												.DataTable();
										higherEdutbl.clear().draw();
										var highereducount = 0;
										var higheredu = $('#li-std-higheredu');
										higheredu.find('li').remove();
										$.each(response.result,function(index, value) {
											if (highereducount < 2) {
												higheredu.append('<li>'	+ value[3].toString() + '-' + value[4].toString() + ' <span class="drop-at"> at </span> '
																+ value[1].toString()+ ' <br><span class="drop-time"> Duration: ' + value[6].toString()	+ ' to '
																+ value[7].toString() + '</span></li>');
											}

											highereducount++;
											higherEdutbl.row.add([value[0].toString(),value[1].toString()+ "<br/>"+ value[2].toString(),
															value[3].toString()+ "<br/>"+ value[4].toString(), value[9].toString() + "<br/>" + "IndexNo: " + value[8].toString(),
															value[11].toString(),value[5].toString(),value[6].toString()+ "<br/>"+ value[7].toString(), value[10].toString(),
															'<button type="button" class="btn btn-info editstpe"><span class="glyphicon glyphicon-edit"></span></button>' ]).draw(false);
										});
									},
									error : function(response) {
										// alert("Error: " + response);
									}
								});

						// Remove added elements
						$('input[name="id\[\]"]', form).remove();

						// Prevent actual form submission
						e.preventDefault();
	});
	
	//Methods to set details into models' UI elements on page loading.
	populatePersonalDataformElements(response);
	populateSchoolEduformElements(response);
	populateHigherEduformElements(response);
	populateProfExpformElements(response);
	
	$('#loading-gif-id').css('display', 'none');
	$('.editformdatabtn').prop("disabled", false); // edit buttons are now enabled.
	
	$("#sEmail").first().keyup(function () {
		var email = this.value;
		var isValid = isValidEmailFormat(email);
		if (!isValid){
			document.getElementById('sEmailError').innerHTML = "Invalid email address.";
		}else{
			document.getElementById('sEmailError').innerHTML = "";
		}
	});
}

/**
 * Method to open Personal details model.
 * @returns
 */
function openPersonalDataModel(){
	populatePersonalDataformElements(dataSet);
	$("#studentPersonalDetailsModal").modal('show');
}

/**
 * Method to open education details model.
 * @returns
 */
function openEduDataModel(){
	populateSchoolEduformElements(dataSet);
	populateHigherEduformElements(dataSet);
	$("#myModal").modal('show');
}

/**
 * Method to open Professional experiences details model.
 * @returns
 */
function openProfExpDataModel(){
	populateProfExpformElements(dataSet);
	$("#studentProfessionalDetailsModal").modal('show');
}