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
<div class="table-responsive">
<div class="col-sm-4">
<h4>出席</h4>
<table class="table">
<tr>
<th>ハンドルネーム</th>
	<c:forEach var="e" items="${tMemberOn}">
		<tr>
			<td>
				${f:h(e.hname) }
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="col-sm-4">
<h4>欠席</h4>
<table class="table">
<tr>
<th>ハンドルネーム</th>
	<c:forEach var="e" items="${tMemberOff}">
		<tr>
			<td>
				${f:h(e.hname) }
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="col-sm-4">
<h4>未回答</h4>
<table class="table">
<tr>
<th>ハンドルネーム</th>
	<c:forEach var="e" items="${mapKuzuSS}">
		<tr>
			<td>
				${f:h(e.value) }
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>