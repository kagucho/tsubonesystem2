<div class="container">
	<div class="col-md-6 col-sx-12">
		<h3 class="col-md-6 CENTER">アンケート詳細</h3>
		<a class="col-md-2 col-sm-12 col-xs-12 btn btn-primary btnYOKO" href="<c:url value="/${loginMemberDto.actorKind}/enqueteReply/${f:h(id)}"/>" role="button">回答する</a>
		<table class="table">
			<tr>
			    <th class="col-md-2 col-sm-2"><h4>タイトル</h4></th>
				<td><h5>${f:h(title)}</h5></td>
			</tr>
			<tr>
				<th class="col-md-2 col-sm-2"><h4>作成者</h4></th>
				<td>${f:h(userName)}</td>
			</tr>
			<tr>
				<th class="col-md-2 col-sm-2"><h4>メモ</h4></th>
				<td class="col-md-10 col-sm-10">${f:br(f:h(memo))}</td>
			</tr>
			<c:forEach var="rec" items="${tEnqueteSelectList}" varStatus="status">
				<tr>
					<th class="col-md-2 col-sm-2"><h4>選択肢${f:h(status.count)}</h4></th>
					<td class="col-md-10 col-sm-10">${f:h(rec.selectedContents)}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="col-md-6 col-sx-12">
			<canvas id="canvas"></canvas>
	</div>
	<script>
		var pieData = [
				<c:forEach var="s" items="${tEnqueteSelectList}" varStatus="status">
				{
					value: ${s.resultNum},
					color:"rgb(" + ${s.r} + "," + ${s.g} + "," + ${s.b} + ")",
				},
				</c:forEach>
			];
		var myPie = new Chart(document.getElementById("canvas").getContext("2d")).Pie(pieData);
	</script>
</div>
<div class="container">
	<h3 class="col-md-12">回答状況</h3>
	<table class="table table-bordered">
		<tr>
			<th>回答対象総数</th><th>回答人数</th><th>回答率</th>
		</tr>
		<tr>
			<td>${f:h(respondMemberCount)}</td>
			<td>${f:h(totalCountNum)}</td>
			<td><fmt:formatNumber value="${f:h(resAllRate)}" pattern="##.#%" /></td>
		</tr>
	</table>
	<span>※回答数の降順</span>
	<table class="table table-bordered">
		<tr>
			<th>順序</th><th>選択肢</th><th>回答数</th><th>割合(/回答)</th><th>割合(/全体)</th>
		</tr>
		<c:forEach var="entity" items="${tEnqueteSelectDetailList}" varStatus="status">
			<tr>
				<td>${f:h(status.count)}</td>
				<td>${f:h(entity.selectedContents)}</td>
				<td>${f:h(entity.resultNum)}</td>
				<td><fmt:formatNumber value="${f:h(entity.resultRate)}" pattern="##.#%" /></td>
				<td><fmt:formatNumber value="${f:h(entity.allRate)}" pattern="##.#%" /></td>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="container">
<div class="bs-callout bs-callout-info">
	<h4>項目説明</h4>
		<dl class="dl-horizontal">
			<dt>回答対象総数</dt>
			<dd>このアンケートに回答できる総人数</dd>
			<dt>回答人数</dt>
			<dd>現時点での回答人数</dd>
			<dt>回答率</dt>
			<dd>回答人数/回答対象総数</dd>
			<dt>割合(/回答)</dt>
			<dd>”回答人数”に対して、一つの回答がどれくらいの割合か</dd>
			<dt>割合(/全体)</dt>
			<dd>”回答対象総数”に対して、一つの回答がどれくらいの割合か</dd>
		</dl>
	</div>
</div>