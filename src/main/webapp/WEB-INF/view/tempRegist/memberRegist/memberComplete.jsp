<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container CENTER">
	<c:if test="${rePageError == null}"><h3>登録が完了しました。</h3></c:if>
	<c:if test="${rePageError != null}"><h3>${f:h(rePageError)}</h3></c:if>
	<c:if test="${mailMsg != null}"><h3>${f:h(mailMsg)}</h3></c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>