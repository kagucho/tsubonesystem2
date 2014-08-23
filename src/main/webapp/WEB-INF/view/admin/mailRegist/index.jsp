<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3 class="col-md-4 col-sm-5 col-xs-12">メール内容作成</h3>
<a class="col-md-2 col-md-offset-6 col-sm-2 col-sm-offset-5 col-xs-12  btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/mailList"/>" role="button">Mail History</a>
<s:form method="POST">
<div class="table-responsive">
	<%@ include file="/WEB-INF/view/common/partyMailFormInput.jsp"%>
</div>
<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3 col-xs-12  btn btn-primary">
</s:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>