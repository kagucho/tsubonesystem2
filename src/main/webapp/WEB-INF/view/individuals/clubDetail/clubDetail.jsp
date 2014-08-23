<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>${f:h(ClubName)}たんの詳細情報</h3>
<div class="col-sm-12">
<table class="table">
	<tr>
		<th class="col-md-2 col-sm-2"><h4>部の名前</h4></th>
		<td><h5>${f:h(ClubName)}</h5></td>
	</tr>
	<tr>
		<th class="col-md-2 col-sm-2"><h4>部の代表者</h4></th>
		<td><a href="<c:url value="/individuals/memberDetail/detail/${tMember.id}"/>"><h5>${f:h(tMember.hname)}(本名:${f:h(tMember.name)})</h5></a></td>
	</tr>
	<tr>
		<th class="col-md-2 col-sm-2"><h4>部の概要</h4></th>
		<td class="col-md-10 col-sm-10"><h5>${f:h(ClubMemo)}</h5></td>
	</tr>
</table>
</div>
<h3 class="col-md-4">${f:h(ClubName)}に所属しているメンバー</h3>
<div class="col-sm-12">
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