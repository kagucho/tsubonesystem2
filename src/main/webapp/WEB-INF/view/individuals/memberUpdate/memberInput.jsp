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
<%@ include file="/WEB-INF/view/common/indeividualsHeader.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>メンバー情報を入力してください。</h3>
<h5 class="hissu">＊がついている項目は必須です</h5>
<c:if test="${sendErrorFlag}">
	<div class="alert alert-danger"><h4>メールが正常に届いていません。メールアドレスを確認して下さい。</h4></div>
</c:if>
<div class="col-sm-12">
<s:form method="POST" >
	<%@ include file="/WEB-INF/view/common/memberFormInput.jsp"%>
	<div class="form-group">
		<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
	</div>
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>