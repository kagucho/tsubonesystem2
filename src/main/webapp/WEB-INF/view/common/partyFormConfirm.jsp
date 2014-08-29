<table class="table">
	<tr>
		<th class="col-md-3 col-xs-4"><h4>会議の名前</h4></th>
		<td><h5>${f:h(meetingName)}</h5></td>
	</tr>
	<tr>
		<th><h4>必須判定</h4></th>
	　	<td>
			<c:if test="${meetingNecessaryFlag}">
				<h5>出席を必須とする。</h5>
			</c:if>
			<c:if test="${!meetingNecessaryFlag}">
				<h5>(必須ではない)</h5>
			</c:if>
		</td>
	</tr>
	<tr>
		<th><h4>開催日</h4></th>
		<td><h5>${f:h(meetingDay)}<c:if test="${meetingEndDay != null}">から${f:h(meetingEndDay)}まで</c:if></h5></td>
	</tr>
	<tr>
		<th><h4>開催時間</h4></th>
		<td><h5>${f:h(meetingTime)}</h5></td>
	</tr>
	<tr>
		<th><h4>開催場所</h4></th>
		<td><h5>${f:h(meetingRoom)}</h5></td>
	</tr>
	<tr>
		<th><h4>会議の内容</h4></th>
		<td><h5><pre>${f:h(meetingMemo)}</pre></h5></td>
	</tr>
	<tr>
		<th><h4>出席対象者を部に絞る</h4></th>
		<td>
			<h5>
				<c:forEach var="e" items="${attendClub}">
					${f:h(clubMapSS[e])}
				</c:forEach>
			</h5>
		</td>
	</tr>
	<tr>
		<th><h4>OB出席</h4></th>
		<td>
			<c:if test="${ObAttendFlag}">
				<h5>OBを出席対象とする</h5>
			</c:if>
			<c:if test="${!ObAttendFlag}">
				<h5>(現役生のみ)</h5>
			</c:if>
		</td>
	</tr>
	<tr>
		<th><h4>締切日</h4></th>
		<td><h5>${f:h(meetingDeadlineDay)}</h5></td>
	</tr>
	<c:if test="${deadFlag}">
	<tr>
		<th><h4>会議の結果</h4></th>
		<td><h5><pre>${f:h(meetingResult)}</pre></h5></td>
	</tr>
	</c:if>
</table>