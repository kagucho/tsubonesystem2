<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<div class="col-sm-12">
<h3>新しく任命するメンバーを選択してください。</h3>
<h4>※局長、副局長、会計は重複して任命できません。(部長との兼任は可)</h4>
<html:errors property="leadersOld"/>
<html:errors property="leadersConnection"/>
<s:form method="POST">
<div class="table-responsive">
<table class="table">
<tr>
<th></th><th>名前</th><th>ハンドルネーム</th><th>メールアドレス</th><th>入学年度</th>
	<c:forEach var="e" items="${memberItems}">
		<tr>
			<td>
				<a class="btn btn-primary" href="<c:url value="/admin/officerUpdate/confirm/${e.id }"/>">選択</a>
			</td>
			<td>
				${f:h(e.name) }
			</td>
			<td>
				${f:h(e.hname) }
			</td>
			<td>
				${f:h(e.mail) }
			</td>
			<td>
				${f:h(e.entrance) }
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>