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
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<h3 class="CENTER">設定項目一覧</h3>
	<div class="col-md-4">
		<a href="<c:url value="/admin/settingsEdit/tempMemberInput"/>"><button type="button" class="btn btn-default btn-lg btn-block">一時メンバーの変更</button></a>
	</div>
	<div class="col-md-8">
		<p>仮登録する際に使用するIDを設定できます。ここで作成したIDとパスワードを周知することによって、メンバー自身が登録することが出来ます。メンバー自身が作成したアカウントは仮登録状態となり、管理者が認証するまでログイン出来ません。</p>
	</div>
	<div class="col-md-4">
		<a href="<c:url value="/admin/settingsEdit/ruleUpdateInput"/>"><button type="button" class="btn btn-default btn-lg btn-block">規約の更新</button></a>
	</div>
	<div class="col-md-8">
		<p>規約の更新があった場合に、ここからPDFでアップロードすることで規約の更新ができます。</p>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>