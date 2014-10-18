<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
	<%@ include file="/WEB-INF/view/common/header.jsp"%>
	<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
	<div class="container">
		<c:if test="${sendErrorFlag}">
			<div class="alert alert-danger CENTER"><h4>このメンバーには正しくメールが届いていない可能性があります</h4></div>
		</c:if>
		<%-- ボタン群 --%>
		<div class="row CENTER">
			<h3 class="col-md-6">${f:h(hname)}ちゃんの詳細情報</h3>
			<a class="col-md-2 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-danger btnYOKO btnMRC" href="<c:url value="/admin/memberDelete/${id}"/>">削除</a>
			<a class="col-md-2 col-sm-5 col-xs-12 btn btn-primary" href="<c:url value="/admin/memberUpdate/${id}"/>">更新</a>
		</div>
		<%-- 入力項目 --%>
		<jsp:include page="/WEB-INF/view/common/memberFormConfirm.jsp" flush="true" />
		<%-- adminオリジナルボタン --%>
		<c:if test="${tempMemberFlag}">
			<a class="col-md-2 col-sm-5 col-md-offset-4 col-sm-offset-1 col-xs-12  btn btn-danger btnYOKO btnMRC" href="<c:url value="/admin/memberApprove/disApprove/${id}"/>">非承認</a>
			<a class="col-md-2 col-sm-5 col-xs-12 btn btn-primary" href="<c:url value="/admin/memberApprove/approve/${id}"/>">承認</a>
		</c:if>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>