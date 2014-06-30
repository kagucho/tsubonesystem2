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
	<h3 class="col-md-12 col-xs-12">会議での審議結果を入力してください</h3>
</div>
<div class="col-sm-12">
	<table class="table">
		<tr>
			<th><h4>会議の名前</h4></th>
			<td><h5>${f:h(meetingName)}</h5></td>
		</tr>
	</table>
	<s:form method="POST" >
		<form class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-4" for="meetingResult">審議結果&nbsp;<span class="hissu">＊</span></label>
				<div class="col-sm-8 memberF">
					<textarea class="form-control" name="meetingResult" rows="10" property="meetingResult" placeholder="meetingResult">${f:h(meetingResult)}</textarea>
					<html:errors property="meetingResult"/>
				</div>
			</div>
			<div class="form-group">
				<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12  btn btn-primary">
			</div>
		</form>
	</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>