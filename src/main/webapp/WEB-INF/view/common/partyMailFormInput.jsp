	<div class="col-sm-12">
		<div id="mailInput">
			<h4>メールを配信する場合は、送信する相手と内容を入力してください。</h4>
			<div class="form-group">
				<label class="control-label col-sm-4" for="mailSendAllFlag">全体に送信する</label>
				<div class="col-sm-8 memberF">
					<input type="checkbox" id="mailSendAllFlag"  name="mailSendAllFlag" value="true"  />&nbsp;全員に送信する(対象者関係なく全員に送信されます)
					<html:errors property="sendTo"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="clubListCheck">部で選択する</label>
				<div class="col-sm-8 memberF">
					<c:forEach var="e" items="${clubMapSS}">
					<html:multibox property="clubListCheck" value="${e.key}" />&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
					</c:forEach>
					<html:errors property="sendTo"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="Title">メールのタイトル&nbsp;<span class="hissu">＊</span></label>
				<div class="col-sm-8 memberF" >
					<input type="text" id="title" name="title" property="title" class="form-control" placeholder="Title" value="${title}" >
					<html:errors property="title"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="Content">メールの内容&nbsp;<span class="hissu">＊</span></label>
				<div class="col-sm-8 memberF">
					<textarea class="form-control" name="content" rows="10" property="content" placeholder="Content" >${f:h(content)}</textarea>
					<html:errors property="content"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="mailSendAllFlag">OBにも送信する&nbsp;</label>
				<div class="col-sm-8 memberF">
					<input type="checkbox" id="mailSendOBFlag" <c:if test="${mailSendOBFlag}"> checked="checked"</c:if>  name="mailSendOBFlag" value="true" />&nbsp;OBを含める
				</div>
			</div>
		</div>
	</div>