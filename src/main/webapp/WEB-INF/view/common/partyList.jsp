<div class="container">
<a href="<c:url value="/${loginMemberDto.actorKind}/partyRegist/index"/>">
<button type="button" class="btn btn-primary btn-lg btn-block">イベントを登録する</button>
</a>
	<div class="col-sm-12">
		<%-- 期限ありで出欠中の会議一覧 --%>
		<div class="col-md-6 CENTER">
			<h3>期限付き出欠</h3>
			<table class="table">
				<tr>
					<th class="col-md-2 col-xs-2 CENTER">今</th><th class="col-md-8 col-xs-8 CENTER">会議名</th><th class="col-md-2 col-xs-2 CENTER">出</th><th class="col-md-2 col-xs-2 CENTER">欠</th>
					<c:forEach var="e" items="${tPartyAttendNow}">
						<tr>
							<td class="CENTER">
								<c:if test="${e.tPartyAttendList.size() > 0}">
									<c:if test="${e.tPartyAttendList.get(0).attend == 1}">
										<span class="glyphicon glyphicon-ok"></span>
									</c:if>
									<c:if test="${e.tPartyAttendList.get(0).attend == 2}">
										<span class="glyphicon glyphicon-remove"></span>
									</c:if>
								</c:if>
							</td>
							<td>
								<a href="<c:url value="/${loginMemberDto.actorKind}/partyDetail/"/>${e.id}">${f:h(e.meetingName) }</a>
							</td>
							<td>
								<a class="btn btn-success" href="<c:url value="/${loginMemberDto.actorKind}/attend/yesFromList/${e.id}"/>">&nbsp;&nbsp;<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;</a>
							</td>
							<td>
								<a class="btn btn-danger" href="<c:url value="/${loginMemberDto.actorKind}/attend/noFromList/${e.id}"/>">&nbsp;&nbsp;<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</c:forEach>
				</tr>
			</table>
		</div>
		<%-- 期限なしで出欠中の会議一覧 --%>
		<div class="col-md-6 CENTER">
			<h3>期限なし出欠</h3>
			<table class="table">
				<tr>
					<th class="col-md-2 col-xs-2 CENTER">今</th><th class="col-md-8 col-xs-8 CENTER">会議名</th><th class="col-md-2 col-xs-2 CENTER">出</th><th class="col-md-2 col-xs-2 CENTER">欠</th>
					<c:forEach var="e" items="${tPartyNoAttendNow}">
						<tr>
							<td class="CENTER">
								<c:if test="${e.tPartyAttendList.size() > 0}">
									<c:if test="${e.tPartyAttendList.get(0).attend == 1}">
										<span class="glyphicon glyphicon-ok"></span>
									</c:if>
									<c:if test="${e.tPartyAttendList.get(0).attend == 2}">
										<span class="glyphicon glyphicon-remove"></span>
									</c:if>
								</c:if>
							</td>
							<td>
								<a href="<c:url value="/${loginMemberDto.actorKind}/partyDetail/"/>${e.id}">${f:h(e.meetingName) }</a>
							</td>
							<td>
								<a class="btn btn-success" href="<c:url value="/${loginMemberDto.actorKind}/attend/yesFromList/${e.id}"/>">&nbsp;&nbsp;<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;</a>
							</td>
							<td>
								<a class="btn btn-danger" href="<c:url value="/${loginMemberDto.actorKind}/attend/noFromList/${e.id}"/>">&nbsp;&nbsp;<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
	<%-- 開催中の会議一覧 --%>
	<div class="col-md-12">
		<h3 class="CENTER">開催中の会議</h3>
		<table class="table">
		<tr>
			<th class="col-md-4 col-xs-4">会議名</th><th class="col-md-2 col-xs-2">終了日</th><th class="col-md-2 col-xs-2">場所</th><th class="col-md-2 col-xs-2 hidden-xs">出欠</th><th class="col-md-2 col-xs-2 hidden-xs">出欠状況</th>
			<c:forEach var="e" items="${tPartySessionNow}">
				<%@ include file="/WEB-INF/view/common/partyListCommonInner.jsp"%>
			</c:forEach>
		</table>
	</div>
	<%-- 開催待ちの会議一覧 --%>
	<div class="col-md-12">
		<h3 class="CENTER">開催待ちの会議</h3>
		<table class="table">
		<tr>
			<th class="col-md-4 col-xs-4">会議名</th><th class="col-md-2 col-xs-2">終了日</th><th class="col-md-2 col-xs-2">場所</th><th class="col-md-2 col-xs-2 hidden-xs">出欠</th><th class="col-md-2 col-xs-2 hidden-xs">出欠状況</th>
			<c:forEach var="e" items="${tPartySessionWill}">
				<%@ include file="/WEB-INF/view/common/partyListCommonInner.jsp"%>
			</c:forEach>
		</table>
	</div>
	<%-- 構想段階の会議一覧 --%>
	<div class="col-md-12">
		<h3 class="CENTER">構想中・・・</h3>
		<table class="table">
		<tr>
			<th class="col-md-4 col-xs-4">会議名</th><th class="col-md-2 col-xs-2">終了日</th><th class="col-md-2 col-xs-2">場所</th><th class="col-md-2 col-xs-2 hidden-xs">出欠</th><th class="col-md-2 col-xs-2 hidden-xs">出欠状況</th>
			<c:forEach var="e" items="${tPartyIdea}">
				<%@ include file="/WEB-INF/view/common/partyListCommonInner.jsp"%>
			</c:forEach>
		</table>
	</div>
</div>
