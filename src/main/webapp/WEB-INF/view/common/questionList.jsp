<div class="col-md-12 col-xs-12">
<h3 class="col-md-6 col-xs-12">過去の質問一覧</h3>
	<table class="table">
		<tr class="info">
		<th>質問者</th><th>質問内容</th><th></th>
		<c:forEach var="e" items="${tPartyQuestionList}">
			<tr>
				<td class="col-md-2 col-sm-2">
					${f:h(e.tMember.hname) }
				</td>
				<td class="col-md-9 col-sm-9">
					${f:h(e.question) }
				</td>
				<td class="col-md-1 col-sm-1">
					<a class="btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/partyAnswer/${id}/${e.id}"/>" role="button">回答</a>
				</td>
			</tr>
		</c:forEach>
		</tr>
	</table>
</div>