<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<div class="col-sm-12">
<div class="table-responsive">
<table class="table">
<tr>
	<th class="col-md-1 col-sm-1">部名</th><th class="col-md-2 col-sm-2">部の代表者</th><th>部の概要</th>
	<c:forEach var="e" items="${clubItems}">
		<tr>
			<td>
				<a href="<c:url value="/individuals/clubDetail/"/>${e.id}">${f:h(e.ClubName) }</a>
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