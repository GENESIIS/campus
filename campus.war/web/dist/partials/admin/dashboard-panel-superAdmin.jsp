<!-- 20170418 AS c154 -dashboard-panel-SuperAdmin.jsp sample page created -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="divTable" style="border: 1px solid #000;">
	<div class="divTableBody">
		<div class="divTableRow">
			<div class="divTableCell">&nbsp;${col_adminName}</div>
		</div>
		<div class="divTableRow">
			<div class="divTableCell">&nbsp;${col_adminEmail}</div>
		</div>
		<div class="divTableRow">
			<div class="divTableCell">&nbsp;${col_adminUserTypeString}</div>
		</div>
	</div>
<!-- 	sample buttons  -->
	<button type="button" class="btn btn-primary">Primary</button>
	<button type="button" class="btn btn-success">Success</button>
</div>