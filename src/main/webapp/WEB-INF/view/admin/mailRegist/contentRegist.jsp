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
<h3>メールの内容を入力してください。</h3>
<div class="col-sm-12">
<table class="table">
<s:form method="POST" >
<form class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-4" for="Title">メールのタイトル</label>
		<div class="col-sm-8 memberF" >
			<input type="text" id="title" name="title" property="title" class="form-control" placeholder="Title">
			<html:errors property="title"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="Content">メールの内容</label>
		<div class="col-sm-8 memberF">
			<textarea class="form-control" name="content" rows="10" property="content" placeholder="Content"></textarea>
			<html:errors property="content"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-8">
			<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="btn btn-primary">
		</div>
	</div>
</form>
</s:form>
</table>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>