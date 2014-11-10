<div class="container">
	<h3 class="col-md-6">アンケート詳細</h3>
	<a class="col-md-2 col-sm-3 col-xs-12 btn btn-primary btnYOKO" href="<c:url value="/admin/enqueteReply/${f:h(id)}"/>" role="button">回答する</a>
	<table class="table">
		<tr>
		    <th class="col-md-2 col-sm-2"><h4>タイトル</h4></th>
			<td><h5>${f:h(title)}</h5></td>
		</tr>
		<tr>
			<th class="col-md-2 col-sm-2"><h4>氏名</h4></th>
			<td>${f:h(userName)}</td>
		</tr>
		<tr>
			<th class="col-md-2 col-sm-2"><h4>メモ</h4></th>
			<td class="col-md-10 col-sm-10">${f:h(memo)}</td>
		</tr>
		<c:forEach var="rec" items="${selectedContents}" varStatus="status">
		<tr>
            <th class="col-md-2 col-sm-2"><h4>選択肢${f:h(status.count)}</h4></th>
            <td class="col-md-10 col-sm-10">${f:h(rec)}</td>
        </tr>
        </c:forEach>
        <tr>
        </tr>
	</table>
	<a href="<c:url value="/admin/enqueteList"/>">一覧</a>
</div>