<html>
<html lang="jp">
	<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
	<body>
		<%@ include file="/WEB-INF/view/common/header.jsp"%>
		<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
		<div class="container">
			<h3>以下のおしらせを削除してよろしいですか？</h3>
			<%@ include file="/WEB-INF/view/common/topAnnounceFormConfirm.jsp"%>
			<s:form method="POST" >
				<input type="submit" value="削除" id="complete" name="complete" class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12  btn btn-danger">
			</s:form>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${f:url('/js/bootstrap.min.js')}"></script>
	</body>
</html>