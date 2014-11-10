<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript">
<%--  --%>
$(function() {
	var val = $("#obFlag:checked").val();
	if(val == "true") {
		$("#sendStopFlag").attr('disabled', false);
	} else {
		$("#sendStopFlag").attr('disabled', true);
	}
	$("#obFlag").click(function() {
		var val = $("#obFlag:checked").val();
		if(val == "true") {
			$("#sendStopFlag").attr('disabled', false);
		} else {
			$("#sendStopFlag").attr('disabled', true);
		}
	});
});
</script>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>メンバー情報を入力してください。</h3>
<h5 class="hissu">＊がついている項目は必須です</h5>
<div class="col-sm-12">
<html:errors property="OfficerCheck"/>
<s:form method="POST" >
<%@ include file="/WEB-INF/view/common/memberUpdateFormInput.jsp"%>
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>