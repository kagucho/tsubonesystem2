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
<div class="col-sm-12">
<h3>新しい局長、もしくは副局長を選択してください。</h3>
<html:errors property="leadersOld"/>
<html:errors property="leadersConnection"/>
<s:form method="POST">
<div class="table-responsive">
<table class="table">
<tr>
<th></th><th>名前</th><th>ハンドルネーム</th><th>メールアドレス</th><th>入学年度</th>
	<c:forEach var="e" items="${memberItems}">
		<tr>
			<td>
				<a class="btn btn-primary" href="<c:url value="/admin/officerUpdate/confirm/${e.id }"/>">選択</a>
			</td>
			<td>
				${f:h(e.name) }
			</td>
			<td>
				${f:h(e.hname) }
			</td>
			<td>
				${f:h(e.mail) }
			</td>
			<td>
				${f:h(e.entrance) }
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>