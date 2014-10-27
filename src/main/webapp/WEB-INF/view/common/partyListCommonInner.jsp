<tr>
	<td>
		<a href="<c:url value="/${loginMemberDto.actorKind}/partyDetail/detail/"/>${e.id}">${f:h(e.meetingName) }</a><%-- 会議名 --%>
</td>
<td>
	<c:if test="${e.meetingEndDay != null}">
		<fmt:formatDate value="${e.meetingEndDay}" pattern="yyyy/MM/dd" /><br><%-- 終了日 --%>
	</c:if>
	<c:if test="${e.meetingEndDay == null}">
		<c:if test="${e.meetingDay == nul}">(開催日なし)</c:if>
		<c:if test="${e.meetingDay != nul}">
			<fmt:formatDate value="${e.meetingDay}" pattern="yyyy/MM/dd" /><br><p>(当日終了)</p><%-- 終了日(当日終了) --%>
		</c:if>
	</c:if>
</td>
<td>
	${f:h(e.meetingRoom) }<%-- 会議場所 --%>
</td>
<td class="hidden-xs">
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
	<a class="btn btn-info" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendList/${e.id}"/>">確認</a>
	</td>
</tr>