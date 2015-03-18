<div class="container">
	<div class="col-sm-12">
		<div class="table-responsive">
			<div class="col-sm-4">
				<h4 class="col-md-4">出席</h4>
				<c:if test="${!deadFlag}">
					<c:if test="${partyDto.meetingNecessaryFlag}">
						<c:if test="${loginMemberDto.actorKindCode == '1'}">
							<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/1"/>">編集</a>
						</c:if>
					</c:if>
					<c:if test="${!partyDto.meetingNecessaryFlag}">
						<c:if test="${loginMemberDto.actorKindCode == '1' || loginMemberDto.actorKindCode == '2'}">
							<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/1"/>">編集</a>
						</c:if>
						<c:if test="${loginMemberDto.actorKindCode == '3' && partyDto.myPartyFlag}">
							<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/1"/>">編集</a>
						</c:if>
					</c:if>
				</c:if>
				<table class="table">
				<tr>
					<th>ハンドルネーム</th>
				</tr>
					<c:forEach var="e" items="${tAttendOn}">
						<tr>
							<td>
								<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
								<c:if test="${e.tMember.sendErrorFlag}"><font color="red">(メール不達)</font></c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<c:if test="${!empty(tMemberOn)}">
					<s:form method="POST" >
						<input type="submit" value="DownList" id="createExcel" name="createExcel" property="createExcel" class="btn btn-success ">
						<input type="submit" value="Mail" id="contentRegist" name="contentRegist" property="contentRegist" class="btn btn-success">
					</s:form>
				</c:if>
			</div>
			<div class="col-sm-4">
				<h4 class="col-md-4">欠席</h4>
				<c:if test="${!deadFlag}">
					<c:if test="${partyDto.meetingNecessaryFlag}">
						<c:if test="${loginMemberDto.actorKindCode == '1'}">
							<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/2"/>">編集</a>
						</c:if>
					</c:if>
					<c:if test="${!partyDto.meetingNecessaryFlag}">
						<c:if test="${loginMemberDto.actorKindCode == '1' || loginMemberDto.actorKindCode == '2'}">
							<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/2"/>">編集</a>
						</c:if>
						<c:if test="${loginMemberDto.actorKindCode == '3' && partyDto.myPartyFlag}">
							<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/2"/>">編集</a>
						</c:if>
					</c:if>
				</c:if>
				<table class="table">
				<tr>
					<th>ハンドルネーム</th>
				</tr>
				<c:forEach var="e" items="${tAttendOff}">
					<tr>
						<td>
							<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
							<c:if test="${e.tMember.sendErrorFlag}"><font color="red">(メール不達)</font></c:if>
						</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<div class="col-sm-4">
				<h4 class="col-md-4">未回答</h4>
				<c:if test="${!deadFlag}">
					<c:if test="${partyDto.meetingNecessaryFlag}">
						<c:if test="${loginMemberDto.actorKindCode == '1'}">
							<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/3"/>">編集</a>
						</c:if>
					</c:if>
					<c:if test="${!partyDto.meetingNecessaryFlag}">
						<c:if test="${loginMemberDto.actorKindCode == '1' || loginMemberDto.actorKindCode == '2'}">
							<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/3"/>">編集</a>
						</c:if>
						<c:if test="${loginMemberDto.actorKindCode == '3' && partyDto.myPartyFlag}">
							<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/3"/>">編集</a>
						</c:if>
					</c:if>
				</c:if>
				<table class="table">
				<tr>
					<th>ハンドルネーム</th>
				</tr>
				<c:forEach var="e" items="${tAttendKuzu}">
					<tr>
						<td>
							<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
							<c:if test="${e.tMember.sendErrorFlag}"><font color="red">(メール不達)</font></c:if>
						</td>
					</tr>
				</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>