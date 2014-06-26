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
	<h3 class="col-md-4">以下の内容を削除しますか？</h3>
	<div class="col-sm-12">
		<html:errors property="OfficerCheck"/>
		<jsp:include page="/WEB-INF/view/common/memberFormConfirm.jsp" flush="true" />
	</div>
	
</div>
<div class="container">
	<s:form method="POST" >
		<input type="submit" value="削除" id="complete" name="complete" property="complete" class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12  btn btn-danger">
	</s:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>