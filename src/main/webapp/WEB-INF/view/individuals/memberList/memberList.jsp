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
<div class="col-sm-12">
<s:form method="POST">
<div class="table-responsive">
<table class="table">
<tr>
<th></th><th>名前</th><th>ハンドルネーム</th><th>メールアドレス</th><th>学部</th><th>入学年度</th>
		<tr>
			<td>
				<a href="<c:url value="/individuals/memberDetail"/>/"><span class="glyphicon glyphicon-user"></span></a>
			</td>
			<td>
				${f:h(tMember.name) }
			</td>
			<td>
				${f:h(tMember.hname) }
			</td>
			<td>
				${f:h(tMember.mail) }
			</td>
			<td>
				${f:h(tMember.curriculum) }
			</td>
			<td>
				${f:h(tMember.entrance) }
			</td>
		</tr>
</table>
<table class="table">
<tr>
<th>名前</th><th>ハンドルネーム</th><th>メールアドレス</th><th>学部</th><th>入学年度</th>
	<c:forEach var="e" items="${memberItems}">
		<tr>
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
				${f:h(e.curriculum) }
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