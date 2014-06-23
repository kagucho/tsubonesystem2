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
<h3 class="col-md-8">以下の内容を削除しますか？</h3>
<div class="col-sm-12">
<table class="table">
	<tr>
		<th><h4>会議の名前</h4></th>
		<td><h5>${f:h(meetingName)}</h5></td>
	</tr>
	<tr>
		<th><h4>会議の必須判定</h4></th>
		<td><h5>${f:h(meetingNecessaryFlag)}</h5></td>
	</tr>
	<tr>
		<th><h4>会議の開催日時</h4></th>
		<td><h5>${f:h(meetingDay)}</h5></td>
	</tr>
	<tr>
		<th><h4>会議の開催場所</h4></th>
		<td><h5>${f:h(meetingRoom)}</h5></td>
	</tr>
	<tr>
		<th><h4>会議の内容</h4></th>
		<td><h5>${f:h(meetingMemo)}</h5></td>
	</tr>
	<tr>
		<th><h4>会議の締切時間</h4></th>
		<td><h5>${f:h(meetingDeadlineDay)}</h5></td>
	</tr>
</table>
<s:form method="POST" >
<input type="submit" value="削除" id="complete" name="complete" property="complete" class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>