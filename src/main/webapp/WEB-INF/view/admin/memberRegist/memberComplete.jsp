<html>
<html lang="jp">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TsuboneSystem</title>
    <link href="${f:url('/css/bootstrap.min.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/layout.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/signin.css')}" type="text/css" rel="stylesheet">
  </head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<c:if test="${rePageError == null}"><h3>登録が完了しました。</h3></c:if>
	<c:if test="${rePageError != null}"><h3>${f:h(rePageError)}</h3></c:if>
<div class="col-sm-5 col-sm-offset-3">
	<a class="btnMRC" href="<c:url value="/admin/memberList"/>"><button type="button" class="btn btn-default btn-lg btn-block">Back.MemberList </button></a>
	<c:if test="${rePageError == null}"><a class="btnMRC" href="<c:url value="/admin/memberDetail"/>"><button type="button" class="btn btn-default btn-lg btn-block">Show.MemberDetail</button></a></c:if>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>