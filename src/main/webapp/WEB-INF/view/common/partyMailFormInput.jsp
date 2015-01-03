<div id="mailInput">
	<h4>メールを配信する場合は、送信する相手を選択してください。</h4>
	<div class="form-group">
		<label class="control-label col-sm-4" for="mailSendAllFlag">現役 or OB</label>
		<div class="col-sm-8 memberF activeOrOb">
			<input type="radio" name="activeOrOb" value="1" <c:if test="${activeOrOb == 1}">checked</c:if> >&nbsp;現役生に送信する
			<input type="radio" name="activeOrOb" value="2" <c:if test="${activeOrOb == 2}">checked</c:if> >&nbsp;OBに配信する
			<html:errors property="activeOrOb"/>
		</div>
	</div>
	<div class="form-group clubListCheck">
		<label class="control-label col-sm-4" for="clubListCheck">全員 or 部ごと</label>
		<div class="col-sm-8 memberF allOrClub">
			<input type="radio" name="allOrClub" value="1" <c:if test="${allOrClub == 1}">checked</c:if> >&nbsp;全員に送信する
			<input type="radio" name="allOrClub" value="2" <c:if test="${allOrClub == 2}">checked</c:if> >&nbsp;部で選択する
			<html:errors property="allOrClub"/>
			<div id = "selectClubDiv">
			(
			<c:forEach var="e" items="${clubMapSS}">
				<html:multibox  property="clubListCheck" value="${e.key}" />&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
			</c:forEach>
			)
			</div>
			<html:errors property="clubListCheck"/>
		</div>
	</div>
</div>