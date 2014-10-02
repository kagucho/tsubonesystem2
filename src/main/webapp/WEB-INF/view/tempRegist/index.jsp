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
<jsp:include page="/WEB-INF/view/common/jumbotronMenu.jsp" >
<jsp:param name="actionName" value="actionName"/>
</jsp:include >
<div class="container">
	<h3 class="CENTER">規約を読み、登録ボタンを押してください。</h3>
	<div class="col-md-12 col-sm-12 col-xs-12">
	 	<object data="${f:url('/pdf/kaisoku.pdf')}" width="1150" height="600"></object>
	 </div>
	 <a class="marginUP col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12 btn btn-primary" href="<c:url value="/tempRegist/memberRegist"/>">規約を読み、了解して登録する</a>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>