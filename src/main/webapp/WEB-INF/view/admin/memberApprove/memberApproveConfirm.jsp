<html>
<html lang="jp">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TsuboneSystem</title>
    <link href="${f:url('/css/bootstrap.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/layout.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/signin.css')}" type="text/css" rel="stylesheet">
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