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
		<th><h4>会議の名前</h4></th>
		<td><h5>${f:h(meetingName)}</h5></td>
	</tr>
	<tr>
		<th><h4>審議の結果</h4></th>
		<td><h5>${f:br(f:h(meetingResult))}</h5></td>
	</tr>
	<tr>
		<th><h4>編集禁止</h4></th>
	　	<td>
			<c:if test="${resultEditEndFlag}">
				<h5>以後の編集を禁止する</h5>
			</c:if>
			<c:if test="${!resultEditEndFlag}">
				<h5>以後の編集を禁止しない</h5>
			</c:if>
		</td>
	</tr>
</table>
<c:if test="${mailSendFlag}">
	<h3 class="col-md-4">メール</h3>
	<table class="table">
		<tr>
			<th><h4>メールのタイトル</h4></th>
			<td><h5>${f:h(title)}</h5></td>
		</tr>
		<tr>
			<th><h4>メールの内容</h4></th>
			<td>※審議の結果欄に記入した内容が送信されます。</td>
		</tr>
	</table>
	
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
</c:if>
<s:form method="POST" >
	<%@ include file="/WEB-INF/view/common/confirmButton.jsp"%>
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>