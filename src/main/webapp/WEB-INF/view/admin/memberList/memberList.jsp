<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<div class="col-sm-12 col-md-12">
<s:form method="POST">
<a class="btn btn-primary" href="<c:url value="/admin/memberRegist/index"/>">新規登録</a>
<a class="btn btn-primary" href="<c:url value="/admin/memberUpload/index"/>">CSV一括登録</a>
<a class="btn btn-primary" href="<c:url value="/admin/memberList/onSearchTempMember"/>">仮登録メンバー一覧</a>
<div class="table-responsive">
	<table class="table table-bordered">
		<tr class="info">
		<th></th><th>名前</th><th>ハンドルネーム</th><th>入学年度</th>
				<tr>
					<td align="center">
						<a href="<c:url value="/admin/memberDetail/detail"/>/${loginMember.id}"><span class="glyphicon glyphicon-user"></span></a>
					</td>
					<td>
						${f:h(loginMember.name) }
					</td>
					<td>
						<a href="<c:url value="/admin/memberDetail/detail"/>/${loginMember.id}">${f:h(loginMember.hname) }</a>
					</td>
					<td>
						${f:h(loginMember.entrance) }
					</td>
				</tr>
	</table>
</div>
<%@ include file="/WEB-INF/view/common/pager.jsp"%>
<div class="table-responsive">
	<table class="table">
		<tr class="info">
			<th></th><th>名前</th><th>ハンドルネーム</th><th>入学年度</th>
			<c:forEach var="e" items="${memberItems}">
				<tr>
					<td>
						<span class="glyphicon glyphicon-user" style="visibility:hidden"></span>
					<td>
						${f:h(e.name) }
					</td>
					<td>
						<a href="<c:url value="/admin/memberDetail/detail"/>/${e.id}">${f:h(e.hname) }</a><c:if test="${e.obFlag}"><font color="blue">(OB)</font></c:if>
					</td>
					<td>
						${f:h(e.entrance) }
					</td>
				</tr>
			</c:forEach>
	</table>
</div>
<%@ include file="/WEB-INF/view/common/pager.jsp"%>

</s:form>
</div>
<%@ include file="/WEB-INF/view/common/search.jsp"%>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>