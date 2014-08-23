<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3 class="col-md-4">${f:h(hname)}ちゃんの詳細情報</h3>

<!-- 自分の情報だけ更新ができる-->
<c:if test="${isMyInfo != null && isMyInfo}">
	<a class="btn btn-primary col-md-1 col-md-offset-7" href="<c:url value="/individuals/memberUpdate/input"/>/">更新</a>
</c:if>

<div class="col-sm-12">
	<jsp:include page="/WEB-INF/view/common/memberFormConfirm.jsp" flush="true" />
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>