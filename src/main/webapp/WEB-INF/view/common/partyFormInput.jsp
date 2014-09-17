
		<div class="form-group">
			<label class="control-label col-sm-4" for=meetingName>会議の題名</label>
			<div class="col-sm-8 memberF" >
				<input type="text" id="meetingName" name="meetingName" property="meetingName" class="form-control" placeholder="meetingName" value="${meetingName}">
				<html:errors property="meetingName"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="meetingNecessaryFlag">出席必須</label>
			<div class="col-sm-8 memberF">
				<input type="checkbox" id="meetingNecessaryFlag" name="meetingNecessaryFlag" <c:if test="${meetingNecessaryFlag}"> checked="checked"</c:if> value="true" />&nbsp;出席を必須とする
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="meetingDay">会議の開催日(yyyy/dd/mm:例2014/07/05)</label>
			<div class="col-sm-8 memberF">
				<input type="text" id="meetingDay" name="meetingDay" property="meetingDay" class="form-control" placeholder="meetingDay" value="${meetingDay}">
				<html:errors property="meetingDay"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="meetingEndDay">会議の終了日</label>
			<div class="col-sm-8 memberF">
				<input type="text" id="meetingEndDay" name="meetingEndDay" property="meetingEndDay" class="form-control" placeholder="※合宿など、日を跨ぐイベントの時のみ使用" value="${meetingEndDay}">
				<html:errors property="meetingEndDay"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="meetingTime">会議の開催時間</label>
			<div class="col-sm-8 memberF">
				<input type="text" id="meetingTime" name="meetingTime" property="meetingTime" class="form-control" placeholder="meetingTime" value="${meetingTime}">
				<html:errors property="meetingTime"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="meetingRoom">会議の開催場所</label>
			<div class="col-sm-8 memberF">
				<input type="text" id="meetingRoom" name="meetingRoom" property="meetingRoom" class="form-control" placeholder="meetingRoom" value="${meetingRoom}">
				<html:errors property="meetingRoom"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="password">会議の内容</label>
			<div class="col-sm-8 memberF">
				<textarea class="form-control" name="meetingMemo" rows="5" property="meetingMemo" placeholder="MeetingMemo">${f:h(meetingMemo)}</textarea>
				<html:errors property="meetingMemo"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="attendClub">出席対象者を部に絞る</label>
			<div class="col-sm-8 memberF">
				<c:if test="${disabledFlag}" >
					<c:forEach var="e" items="${clubMapSS}">
						 <html:multibox property="attendClub" value="${e.key}" disabled="true" />&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
					</c:forEach>
				</c:if>
				<c:if test="${!disabledFlag}" >
					<c:forEach var="e" items="${clubMapSS}">
						 <html:multibox property="attendClub" value="${e.key}" disabled="" />&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="ObAttendFlag">OB出席</label>
			<div class="col-sm-8 memberF">
				<input type="checkbox" <c:if test="${ObAttendFlag}"> checked="checked"</c:if> <c:if test="${disabledFlag}">disabled="disabled"</c:if>/>&nbsp;OBも出席対象とする
				<!-- チェックボックスがdisableのため情報が送信されないのでhiddenで情報を持っておく -->
				<input type="hidden" id="ObAttendFlag" name="ObAttendFlag" value="${ObAttendFlag}">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="meetingDeadlineDay">出欠席締め切り日</label>
			<div class="col-sm-8 memberF">
				<input type="text" id="meetingDeadlineDay" name="meetingDeadlineDay" property="meetingDeadlineDay" class="form-control" placeholder="meetingDeadlineDay" value="${meetingDeadlineDay}">
				<html:errors property="meetingDeadlineDay"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4">メール配信可否</label>
			<div class="col-sm-8 memberF">
				<input type="checkbox" id="mailSendFlag" name="mailSendFlag" value="mailSendFlag" property="mailSendFlag"  onclick="hideToggle($('#mailInput'));"  <c:if test="${mailSendFlag}"> checked="checked"</c:if>/>&nbsp;メールを配信する※チェクしないとメールは配信されません！！
			</div>
		</div>
