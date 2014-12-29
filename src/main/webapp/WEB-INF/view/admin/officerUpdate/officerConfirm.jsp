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
    <link href="${f:url('/css/docs.min.css')}" type="text/css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="${f:url('/js/bootstrap.min.js')}"></script>
  </head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<h3>選択されたメンバーを確認し、与える権限を設定してください。</h3>
	<div class="table-responsive">
		<table class="table">
			<tr>
				<th>名前</th><th>ハンドルネーム</th>
			</tr>
			<tr>
				<td>
					${f:h(tMemberNew.name) }
				</td>
				<td>
					${f:h(tMemberNew.hname) }
				</td>
			</tr>
		</table>
	</div>
</div>
<div class="container">
	<s:form method="POST">
		<%@ include file="/WEB-INF/view/common/leadersPowerInput.jsp"%>
	</s:form>
</div>
</body>
</html>