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
<s:form method="POST" >
<div class="table-responsive">
<h4>変更したいメンバーを選択し、変更したいボタンを押してください</h4>
<table class="table">
<tr>
<th></th><th>ハンドルネーム</th>
	<c:forEach var="e" items="${tMemberOn}">
		<tr>
			<td>
 				<html:checkbox property="offCheck" value="${e.id}" /> 
			</td>
			<td>
				<a href="<c:url value="/admin/memberDetail/detail"/>/${e.id}">${f:h(e.hname) }</a>
			</td>
		</tr>
	</c:forEach>
	<c:forEach var="b" items="${tMemberOff}">
		<tr>
			<td>
 				<html:checkbox property="offCheck" value="${b.id}" />
			</td>
			<td>
				<a href="<c:url value="/admin/memberDetail/detail"/>/${b.id}">${f:h(b.hname) }</a>
			</td>
		</tr>
	</c:forEach>
	<c:forEach var="d" items="${tMemberKuzu}">
		<tr>
			<td>
 				<html:checkbox property="offCheck" value="${d.id}" />
			</td>
			<td>
				<a href="<c:url value="/admin/memberDetail/detail"/>/${d.id}">${f:h(d.hname) }</a>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<input type="submit" value="${f:h(btn1) }" id="confirm" name="confirm" property="confirm" formaction="<c:url value="/admin/partyAttendUpdate/confirm/${btn1Key }"/>" class="btn btn-primary">
<input type="submit" value="${f:h(btn2) }" id="confirm" name="confirm" property="confirm" formaction="<c:url value="/admin/partyAttendUpdate/confirm/${btn2Key }"/>" class="btn btn-primary">
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>