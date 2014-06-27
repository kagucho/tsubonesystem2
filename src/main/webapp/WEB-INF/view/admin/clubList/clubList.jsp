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
<a class="btn btn-primary" href="<c:url value="/admin/clubRegist/index"/>">新規登録</a>
<div class="table-responsive">
<table class="table">
<tr>
	<th>部名</th><th>部の代表者</th><th>部の概要</th>
	<c:forEach var="e" items="${clubItems}">
		<tr>
			<td>
				<a href="<c:url value="/admin/clubDetail/"/>${e.id}">${f:h(e.ClubName) }</a>
			</td>
			<td>
				${f:h(e.tLeaders.tMember.hname)}
			</td>
			<td>
				${f:h(e.ClubMemo) }
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>