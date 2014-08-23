<div class="container">
	<div class="col-sm-12">
	<a class="btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/partyRegist/index"/>">新規登録</a>
	<h3>出席受付中の会議</h3>
		<div class="table-responsive">
			<table class="table">
			<tr>
				<th>会議名</th><th class="hidden-xs">出席必須</th><th>会議日</th><th class="hidden-xs">会議場所</th><th>出欠席</th><th class="hidden-xs">締切日</th><th>出欠席状況</th>
				<c:forEach var="e" items="${partyItemOn}">
					<tr>
						<td>
							<a href="<c:url value="/${loginMemberDto.actorKind}/partyDetail/"/>${e.id}">${f:h(e.meetingName) }</a>
						</td>
						<td class="hidden-xs">
							<c:if test="${e.meetingNecessaryFlag}">
								<span class="glyphicon glyphicon-ok"></span>
							</c:if>
						</td>
						<td>
							<fmt:formatDate value="${e.meetingDay}" pattern="yyyy/MM/dd" /><br>
						</td>
						<td class="hidden-xs">
							${f:h(e.meetingRoom) }
						</td>
						<td>
							<c:if test="${e.tPartyAttendList.size() > 0}">
								<c:if test="${e.tPartyAttendList.get(0).attend == 1}">
									<span class="glyphicon glyphicon-ok"></span>
								</c:if>
								<c:if test="${e.tPartyAttendList.get(0).attend == 2}">
									<span class="glyphicon glyphicon-remove"></span>
								</c:if>
							</c:if>
						</td>
						<td class="hidden-xs">
							<fmt:formatDate value="${e.meetingDeadlineDay}" pattern="yyyy/MM/dd" /><br>
						</td>
						<td>
							<a class="btn btn-info" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendList/${e.id}"/>">確認</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<h3>過去の会議</h3>
			<table class="table">
			<tr>
				<th>会議名</th><th class="hidden-xs">出席必須</th><th>会議日</th><th class="hidden-xs">会議場所</th><th>出欠席</th><th class="hidden-xs">締切日</th><th>出欠席状況</th>
				<c:forEach var="e" items="${partyItemOff}">
					<tr>
						<td>
							<a href="<c:url value="/${loginMemberDto.actorKind}/partyDetail/"/>${e.id}">${f:h(e.meetingName) }</a>
						</td>
						<td class="hidden-xs">
							<c:if test="${e.meetingNecessaryFlag}">
								<span class="glyphicon glyphicon-ok"></span>
							</c:if>
						</td>
						<td>
							<fmt:formatDate value="${e.meetingDay}" pattern="yyyy/MM/dd" /><br>
						</td>
						<td class="hidden-xs">
							${f:h(e.meetingRoom) }
						</td>
						<td>
							<c:if test="${e.tPartyAttendList.size() > 0}">
								<c:if test="${e.tPartyAttendList.get(0).attend == 1}">
									<span class="glyphicon glyphicon-ok"></span>
								</c:if>
								<c:if test="${e.tPartyAttendList.get(0).attend == 2}">
									<span class="glyphicon glyphicon-remove"></span>
								</c:if>
							</c:if>
						</td>
						<td class="hidden-xs">
							<fmt:formatDate value="${e.meetingDeadlineDay}" pattern="yyyy/MM/dd" /><br>
						</td>
						<td>
							<a class="btn btn-info" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendList/${e.id}"/>">確認</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>