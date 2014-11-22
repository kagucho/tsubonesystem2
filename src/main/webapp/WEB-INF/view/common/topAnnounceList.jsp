<div class="container">
	<a class="btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/topAnnounceRegist"/>">お知らせ作成</a>
	<h3>TOPお知らせ一覧</h3>
	<div class="table-responsive">
		<table class="table">
		<tr>
			<th>お知らせのタイトル</th><th>お知らせ開始日</th><th>お知らせ終了日</th><th>お知らせの登録者</th>
			<c:forEach var="e" items="${list}">
				<tr>
					<td>
						<a href="<c:url value="/${loginMemberDto.actorKind}/topAnnounceDetail/"/>${e.id}">${f:h(e.announceTitle) }</a>
					</td>
					<td>
						<c:if test="${e.announceFromDay != null}">
							<fmt:formatDate value="${e.announceFromDay}" pattern="yyyy/MM/dd" />
						</c:if>
						<c:if test="${e.announceFromDay == null}">
							<p>未設定</p>
						</c:if>
					</td>
					<td>
						<c:if test="${e.announceToDay != null}">
							<fmt:formatDate value="${e.announceToDay}" pattern="yyyy/MM/dd" />
						</c:if>
						<c:if test="${e.announceToDay == null}">
							<p>未設定</p>
						</c:if>
					</td>
					<td>
						${f:h(e.tMember.hname)}
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

