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
<div class="row CENTER">
	<h3 class="col-md-6 col-xs-12">${f:h(meetingName)}たんの詳細情報</h3>
	<c:if test="${!deadFlag}">
		<a class="col-md-2 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-danger btnYOKO btnMRC" href="<c:url value="/admin/partyDelete/${id}"/>" role="button">削除</a>
		<a class="col-md-2 col-sm-5 col-xs-12 btn btn-primary" href="<c:url value="/admin/partyUpdate/${id}"/>" role="button">更新</a>
	</c:if>
	<c:if test="${deadFlag}">
		<a class="col-md-2 col-sm-5 col-sm-offset-4 col-xs-12  btn btn-primary btnMRC" href="<c:url value="/admin/partyResult/${id}"/>" role="button">会議の結果を入力する</a>
	</c:if>
</div>
<div class="col-sm-12">

<%@ include file="/WEB-INF/view/common/partyFormConfirm.jsp"%>

<c:if test="${!deadFlag}">
	<div class="row CENTER">
		<a class="col-md-3 col-md-offset-3 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-primary btnYOKO btnMRC " href="<c:url value="/admin/attend/yes"/>">出席する</a>
		<a class="col-md-3 col-sm-5 col-xs-12 btn btn-primary" href="<c:url value="/admin/attend/no"/>">欠席する</a>
	</div>
</c:if>
<c:if test="${deadFlag}">
	<div class="alert alert-danger">この会議は締め切り時間を過ぎています</div>
</c:if>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>