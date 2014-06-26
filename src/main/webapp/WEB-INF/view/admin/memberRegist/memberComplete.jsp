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
<div class="container CENTER">
	<c:if test="${rePageError == null}"><h3>登録が完了しました。</h3></c:if>
	<c:if test="${rePageError != null}"><h3>${f:h(rePageError)}</h3></c:if>

	<a href="<c:url value="/admin/memberList"/>"><button type="button" class="col-md-4 col-md-offset-1 col-sm-5 col-xs-12 btn btn-default btn-lg btnYOKO30 btnMRC">Back.MemberList </button></a>
	<c:if test="${rePageError == null}"><a href="<c:url value="/admin/memberDetail/detail/${id}"/>"><button type="button" class="col-md-4 col-md-offset-1 col-sm-5  col-xs-12 btn btn-default btn-lg">Show.MemberDetail</button></a></c:if>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>