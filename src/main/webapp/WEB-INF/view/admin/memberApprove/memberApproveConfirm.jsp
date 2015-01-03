<html>
<html lang="jp">
  <head>
    <%@ include file="/WEB-INF/view/common/headInnerCommon.jsp"%>
  </head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<div class="CENTER">
		<c:if test="${approveFlag}">
			<h3>以下のメンバーを認証しますか？</h3>
		</c:if>
		<c:if test="${!approveFlag}">
			<h3>以下のメンバーを<span class="hissu">非</span>認証しますか？</h3>
		</c:if>
	</div>
	<div class="col-sm-12">
		<jsp:include page="/WEB-INF/view/common/memberFormConfirm.jsp" flush="true" />
		<s:form method="POST" >
			<%@ include file="/WEB-INF/view/common/confirmButton.jsp"%>
		</s:form>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>