<div class="container">
<h3>以下の内容で登録します。よろしいですか？</h3>
<div class="col-sm-12">
<table class="table">
	<tr>
		<th><h4>部の名前</h4></th>
		<td><h5>${f:h(ClubName)}</h5></td>
	</tr>
	<tr>
		<th><h4>部の代表者</h4></th>
		<td><h5>${f:h(memberMap[OfficerId])}</h5></td>
	</tr>
	<tr>
		<th><h4>部の説明</h4></th>
		<td><h5>${f:br(f:h(ClubMemo))}</h5></td>
	</tr>
	<tr>
		<th><h4>部の公式ホームページ</h4></th>
		<td><h5>${f:h(clubUrl)}</h5></td>
	</tr>
</table>
<s:form method="POST" >
	<%@ include file="/WEB-INF/view/common/confirmButton.jsp"%>
</s:form>
</div>
</div>