<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<div class="col-sm-12">
		<h3>以下の設定でよろしいですか？</h3>
		<s:form method="POST">
			<div class="table-responsive">
				<%@ include file="/WEB-INF/view/common/leadersPowerConfirm.jsp"%>
			</div>
		<input type="submit" value="登録" id="clubChiefComplete" name="clubChiefComplete" property="clubChiefComplete" class="btn btn-primary">
		</s:form>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>