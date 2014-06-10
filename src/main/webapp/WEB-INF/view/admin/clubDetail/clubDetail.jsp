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
<h3 class="col-md-8">${f:h(ClubName)}たんの詳細情報</h3>
<a class="btn btn-danger col-md-1 col-md-offset-1 btnDelete" href="<c:url value="/admin/clubDelete/${id}"/>">削除</a>
<a class="btn btn-primary col-md-1" href="<c:url value="/admin/clubUpdate/${id}"/>">更新</a>
<div class="col-sm-12">
<table class="table">
	<tr>
		<th><h4>部の名前</h4></th>
		<td><h5>${f:h(ClubName)}</h5></td>
	</tr>
	<tr>
		<th><h4>部の代表者</h4></th>
		<td><a href="<c:url value="/admin/memberDetail/detail/${tMember.id}"/>"><h5>${f:h(tMember.hname)}(本名:${f:h(tMember.name)})</h5></a></td>
	</tr>
	<tr>
		<th><h4>部の概要</h4></th>
		<td><h5><pre>${f:h(ClubMemo)}</pre></h5></td>
	</tr>
</table>
</div>
<div class="col-sm-12">
<h3 class="col-md-5">${f:h(ClubName)}に所属しているメンバー</h3>
<s:form method="POST" >
		<input type="submit" value="Mail" id="contentRegist" name="contentRegist" property="contentRegist" class="col-md-offset-6 btn btn-success">
</s:form>
<table class="table table-striped">
	<tr>
		<th><h4>ハンドルネーム</h4></th><th><h4>本名</h4></th>
	</tr>
	<c:forEach var="e" items="${tMemberList}">
		<tr>
			<td>${f:h(e.hname)}</td><td>${f:h(e.name)}</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>