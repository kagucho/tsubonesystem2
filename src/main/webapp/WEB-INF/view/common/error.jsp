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
	<div class="container CENTER">
		<h3>不正なアクセスを検知しました。</h3>
	</div>
	<div class="container">
		<div class="bs-callout bs-callout-info">
			<h4>原因</h4>
			<ul>
				<li>登録などを2回以上押した</li>
				<li>完了画面で更新しようとした</li>
				<li>完了画面からブラウザバックで確認画面に遷移した</li>
				<li>邪な心で登録しようとした</li>
			</ul>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>