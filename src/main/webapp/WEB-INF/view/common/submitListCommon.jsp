<div class="container">
	<a class="btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/submitRegist"/>">作品投稿</a>
	<h3>作品一覧</h3>
	<div class="table-responsive">
		<table class="table">
		<tr>
			<th>作品名</th><th>制作者</th><th>タグ</th><th>おしらせとのひもづけ</th>
			<c:forEach var="e" items="${list}">
				<tr>
					<td>
						<a href="<c:url value="/${loginMemberDto.actorKind}/submitDetail/"/>${e.id}">${f:h(e.submitName) }</a>
					</td>
					<td>
						${f:h(e.tMember.hname)}
					</td>
					<td>
						${f:h(submitTagNameMap[e.submitTagKindId])}
					</td>
					<c:if test="${e.topAnnounceId != null}">
						<td>
						${f:h(topAnnounceMap[e.topAnnounceId])}
						</td>
					</c:if>
					<c:if test="${e.topAnnounceId == null}">
						<td>
							(ひもづけなし)
						</td>
					</c:if>
					
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
