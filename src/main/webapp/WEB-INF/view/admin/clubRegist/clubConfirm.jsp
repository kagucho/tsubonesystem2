<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>以下の内容で登録します。よろしいですか？</h3>
<div class="col-sm-12">
<table class="table">
	<tr>
		<th><h4>部の名前</h4></th>
		<td><h5>${f:h(ClubName)}</h5></td>
	</tr>
	<tr>
		<th><h4>部長</h4></th>
		<td><h5>${f:h(memberMap[OfficerId])}</h5></td>
	</tr>
	<tr>
		<th><h4>部の概要</h4></th>
		<td><h5>${f:br(f:h(ClubMemo))}</h5></td>
	</tr>
</table>
<s:form method="POST" >
	<%@ include file="/WEB-INF/view/common/confirmButton.jsp"%>
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>