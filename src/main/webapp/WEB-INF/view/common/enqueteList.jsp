<div class="container">
		<a href="<c:url value="/${loginMemberDto.actorKind}/enqueteRegist/index"/>">
			<button type="button" class="btn btn-primary btn-lg btn-block">アンケートを登録する</button>
		</a>
		<h3>アンケート一覧</h3>
		<div class="table-responsive">
			<table class="table">
			<tr>
				<th>アンケート名</th>
				<th>作成者</th>
			   <th>回答済み</th>
			</tr>
				<c:forEach var="rec" items="${list}">
					<tr>
						<td>
							<a href="<c:url value="/${loginMemberDto.actorKind}/enqueteDetail/"/>${rec.id}">${rec.title}</a>
						</td>
						<td>
							${rec.tMember.userName}
						</td>
						<td>
							<c:if test="${rec.answered}"><span class="glyphicon glyphicon glyphicon-ok" aria-hidden="true"></span></c:if>
							<c:if test="${!rec.answered}"><span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span></c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
</div>