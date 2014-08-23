<div class="container">
<h3>以下の内容で登録します。よろしいですか？</h3>
<div class="col-sm-12">
<table class="table">
	<tr>
		<th><h4>会議の名前</h4></th>
		<td><h5>${f:h(ClubName)}</h5></td>
	</tr>
	<tr>
		<th><h4>会議の必須判定</h4></th>
		<td><h5>${f:h(memberMap[OfficerId])}</h5></td>
	</tr>
	<tr>
		<th><h4>会議の開催日</h4></th>
		<td><h5><pre>${f:h(ClubMemo)}</pre></h5></td>
	</tr>
</table>
<s:form method="POST" >
	<%@ include file="/WEB-INF/view/common/confirmButton.jsp"%>
</s:form>
</div>
</div>