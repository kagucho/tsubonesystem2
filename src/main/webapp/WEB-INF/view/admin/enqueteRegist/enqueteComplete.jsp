<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container CENTER">
    <h3>登録が完了しました。</h3>
<div class="col-sm-12">
	<div class="col-sm-4 col-sm-offset-4" id="compBtn">
		<a class="btnMRC" href="<c:url value="/admin/enqueteList"/>"><button type="button" class="btn btn-default btn-lg btn-block">Back.EnqueteList </button></a>
    </div>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>