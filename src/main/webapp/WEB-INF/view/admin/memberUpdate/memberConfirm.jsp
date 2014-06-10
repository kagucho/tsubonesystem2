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
<h3>以下の内容で登録します。よろしいですか？</h3>
<div class="col-sm-12">
<table class="table">
	<tr>
		<th><h4>名前</h4></th>
		<td><h5>${f:h(name)}</h5></td>
	</tr>
	<tr>
		<th><h4>性別</h4></th>
		<td><h5>${f:h(sexMap[sex])}</h5></td>
	</tr>
	<tr>
		<th><h4>ハンドネーム</h4></th>
		<td><h5>${f:h(hname)}</h5></td>
	</tr>
	<tr>
		<th><h4>メールアドレス</h4></th>
		<td><h5>${f:h(mail)}</h5></td>
	</tr>
	<tr>
		<th><h4>所属部</h4></th>
		<td>
			<h5>
				<c:forEach var="e" items="${clubListChecked}">
					${f:h(clubMapSS[e])}
				</c:forEach>
			</h5>
		</td>
	</tr>
	<tr>
		<th><h4>学科</h4></th>
		<td><h5>${f:h(curriculum)}</h5></td>
	</tr>
	<tr>
		<th><h4>入学年度</h4></th>
		<td><h5>${f:h(entrance)}</h5></td>
	</tr>
	<tr>
		<th><h4>電話番号</h4></th>
		<td><h5>${f:h(tel1)}-${f:h(tel2)}-${f:h(tel3)}</h5></td>
	</tr>
	<tr>
		<th><h4>UserName</h4></th>
		<td><h5>${f:h(userName)}</h5></td>
	</tr>
	<tr>
		<th><h4>Password</h4></th>
		<td><h5>${f:h(password)}</h5></td>
	</tr>
</table>
<s:form method="POST" >
	<input type="submit" value="登録" id="complete" name="complete" property="complete" class="btn btn-primary">
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>