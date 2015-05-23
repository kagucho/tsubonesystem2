<div class="container">
	<div class="col-sm-12">
		<a class="btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/mailRegist"/>">メール作成</a>
		<h3>メールの送信履歴</h3>
		<div class="table-responsive">
			<table class="table">
			<tr>
				<th>メールのタイトル</th><th class="hidden-xs">送信者名</th><th>送信時間</th><th>送信ステータス</th><c:if test="${loginMemberDto.actorKindCode == 1}"><th>閲覧種別</th></c:if>
				<c:forEach var="e" items="${tMailItem}">
					<tr>
						<td>
							<a href="<c:url value="/${loginMemberDto.actorKind}/mailDetail/"/>${e.id}">${f:h(e.title) }</a>
						</td>
						<td class="hidden-xs">
							<c:if test="${e.registMemberId == null}"><p class="hidden-xs">(自動配信)</p></c:if>
							<p class="hidden-xs">${f:h(memberMapIS[e.registMemberId]) }</p>
						</td>
						<td>
							<fmt:formatDate value="${e.registTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
						<td>
							<c:if test="${e.errorFlag}">
								<span class="glyphicon glyphicon-remove"></span>
							</c:if>
							<c:if test="${!e.errorFlag}">
								<span class="glyphicon glyphicon-ok"></span>
							</c:if>
						</td>
						<c:if test="${loginMemberDto.actorKindCode == 1}">
							<c:choose>
								<c:when test="${e.browsingRights == 1 }" >
									<td>Admin</td>
								</c:when>
								<c:when test="${e.browsingRights == 2 }" >
									<td>Leaders</td>
								</c:when>
								<c:otherwise>
									<td>一般メンバー</td>
								</c:otherwise>
							</c:choose>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>