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
<h3>以下の部を削除しますか？</h3>
<div class="col-sm-12">
<table class="table">
	<tr>
		<th><h4>部の名前</h4></th>
		<td><h5>${f:h(ClubName)}</h5></td>
	</tr>
	<tr>
		<th><h4>部の概要</h4></th>
		<td><h5><pre>${f:h(ClubMemo)}</pre></h5></td>
	</tr>
</table>
<s:form method="POST" >
	<input type="submit" value="削除" id="complete" name="complete" property="complete" class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12  btn btn-danger">
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>