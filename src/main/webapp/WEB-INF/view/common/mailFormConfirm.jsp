<div class="container">
	<h3 class="col-md-4">以下の内容でよろしいですか？</h3>
	<div class="col-sm-12">
		<h4 class="col-md-4">メールの内容</h4>
		<table class="table">
			<tr>
				<th><h4>メールのタイトル</h4></th>
				<td><h5>${f:h(title)}</h5></td>
			</tr>
			<tr>
				<th><h4>メールの内容</h4></th>
				<td><h5>${f:h(content)}</h5></td>
			</tr>
		</table>
	</div>
	<div class="col-sm-12">
		<h4 class="col-md-4">メールを送る相手一覧</h4>
		<table class="table table-striped">
			<tr>
				<th><h4>ハンドルネーム</h4></th><th><h4>本名</h4></th>
			</tr>
			<c:forEach var="e" items="${tMemberSendList}">
				<tr>
					<td>${f:h(e.hname)}<c:if test="${e.obFlag}"><font color="blue">(OB)</font></c:if></td>
					<td>${f:h(e.name)}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<s:form method="POST" >
		<%@ include file="/WEB-INF/view/common/confirmButton.jsp"%>
	</s:form>
</div>