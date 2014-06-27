			<div class="col-sm-12">
				<div class="form-group">
					<label class="control-label col-sm-4" for=meetingName>会議の題名&nbsp;<span class="hissu">＊</span></label>
					<div class="col-sm-8 memberF" >
						<input type="text" id="meetingName" name="meetingName" property="meetingName" class="form-control" placeholder="meetingName" value="${meetingName}">
						<html:errors property="meetingName"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4" for="meetingNecessaryFlag">出席必須</label>
					<div class="col-sm-8 memberF">
						<input type="checkbox" id="meetingNecessaryFlag" name="meetingNecessaryFlag" value="true" <c:if test="${meetingNecessaryFlag}"> checked="checked"</c:if>/>&nbsp;出席を必須とする
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
					<label class="control-label col-sm-4" for="password">会議の内容&nbsp;<span class="hissu">＊</span></label>
					<div class="col-sm-8 memberF">
						<textarea class="form-control" name="meetingMemo" rows="5" property="meetingMemo" placeholder="MeetingMemo">${f:h(meetingMemo)}</textarea>
						<html:errors property="meetingMemo"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4" for="attendClub">出席対象者を部に絞る</label>
					<div class="col-sm-8 memberF">
						<c:forEach var="e" items="${clubMapSS}">
							<html:multibox property="attendClub" value="${e.key}" />&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
						</c:forEach>
						<p>&nbsp;※無選択で全員が対象</p>
						<html:errors property="attendClub"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4" for="ObAttendFlag">OB出席</label>
					<div class="col-sm-8 memberF">
						<input type="checkbox" id="ObAttendFlag" name="ObAttendFlag" value="true" <c:if test="${ObAttendFlag}"> checked="checked"</c:if>/>&nbsp;OBも出席対象とする
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4" for="meetingDeadlineDay">出欠席締め切り日(yyyy/dd/mm:例2014/07/04)</label>
					<div class="col-sm-8 memberF">
						<input type="text" id="meetingDeadlineDay" name="meetingDeadlineDay" property="meetingDeadlineDay" class="form-control" placeholder="meetingDeadlineDay" value="${meetingDeadlineDay}">
						<html:errors property="meetingDeadlineDay"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">メール配信可否</label>
					<div class="col-sm-8 memberF">
						<input type="checkbox" name="mailSendFlag" value="mailSendFlag" property="mailSendFlag"  onclick="hideToggle($('#mailInput'));" <c:if test="${mailSendFlag}"> checked="checked"</c:if>/>&nbsp;メールを配信する※チェックしないとメールは配信されません！！
					</div>
				</div>
			</div>