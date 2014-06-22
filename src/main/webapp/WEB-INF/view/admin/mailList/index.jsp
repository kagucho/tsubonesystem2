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
<a class="btn btn-primary" href="<c:url value="/admin/mailRegist"/>">メール作成</a>
<h3>メールの送信履歴</h3>
<div class="table-responsive">
<table class="table">
<tr>
	<th>メールのタイトル</th><th class="hidden-xs">送信者名</th>
	<c:forEach var="e" items="${tMailItem}">
		<tr>
			<td>
				<a href="<c:url value="/admin/mailDetail/"/>${e.id}">${f:h(e.title) }</a>
			</td>
			<td class="hidden-xs">
				<c:if test="${e.registMemberId == null}"><p class="hidden-xs">(自動配信)</p></c:if>
				<p class="hidden-xs">${f:h(memberMapIS[e.registMemberId]) }</p>
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