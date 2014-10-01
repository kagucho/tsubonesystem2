<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<div class="row CENTER">
	<h3 class="col-md-6">${f:h(hname)}ちゃんの詳細情報</h3>
	<c:if test="${loginLeadersDto.memberUpdate || isMyInfo}">
		<a class="col-md-2 col-sm-5 col-xs-12 btn btn-primary" href="<c:url value="/leaders/memberUpdate/${id}"/>">更新</a>
	</c:if>
</div>
<div class="col-sm-12">
	<jsp:include page="/WEB-INF/view/common/memberFormConfirm.jsp" flush="true" />
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>