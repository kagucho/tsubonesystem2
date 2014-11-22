<html>
<html lang="jp">
	<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
	<body>
		<%@ include file="/WEB-INF/view/common/header.jsp"%>
		<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
		<div class="container">
			<h3>以下の内容でよろしいですか？</h3>
			<%@ include file="/WEB-INF/view/common/submitFormConfirm.jsp"%>
			<s:form method="POST" >
				<%@ include file="/WEB-INF/view/common/confirmButton.jsp"%>
			</s:form>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${f:url('/js/bootstrap.min.js')}"></script>
	</body>
</html>