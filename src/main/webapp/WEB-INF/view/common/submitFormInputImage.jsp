<form class="form-horizontal">
	<input type="hidden" class="submitProductFileTypeHidden" name="submitProductFileType" value="">
	<div class="form-group">
		<label class="control-label col-sm-4" for="registMember">作品の提出者&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<c:if test="${loginMemberDto.actorKindCode != 3}">
				<select name="registId">
					<c:forEach var="e" items="${submitMemberMap}">
						<option value="${e.key}" <c:if test="${e.key == registId}">selected="selected"</c:if> >${f:h(e.value)}</option>
					</c:forEach>
				</select>
				<html:errors property="registId"/>
			</c:if>
			<c:if test="${loginMemberDto.actorKindCode == 3}">
				<select name="registId" disabled>
					<c:forEach var="e" items="${submitMemberMap}">
						<option value="${e.key}" <c:if test="${e.key == registId}">selected="selected"</c:if> >${f:h(e.value)}</option>
					</c:forEach>
				</select>
				<html:errors property="registId"/>
			</c:if>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="submitTagKind">作品の分類タグ</label>
		<div class="col-sm-8 memberF">
			<select name="submitTagKindId">
			<option value="" <c:if test="${submitTagKindId == null}">selected="selected"</c:if> >(未分類)</option>
				<c:forEach var="e" items="${submitTagNameMap}">
					<option value="${e.key}" <c:if test="${e.key == submitTagKindId}">selected="selected"</c:if> >${f:h(e.value)}</option>
				</c:forEach>
			</select>
			<html:errors property="submitTagKindId"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="TopAnnounce">おしらせとのひもづけ</label>
		<div class="col-sm-8 memberF">
			<select name="topAnnounceId">
			<option value="" <c:if test="${topAnnounceMap == null}">selected="selected"</c:if> >(おしらせとはひもづけない)</option>
				<c:forEach var="e" items="${topAnnounceMap}">
					<option value="${e.key}" <c:if test="${e.key == topAnnounceId}">selected="selected"</c:if> >${f:h(e.value)}</option>
				</c:forEach>
			</select>
			<html:errors property="topAnnounceId"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="submitName">作品名&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF" >
			<input type="text" id="submitName" name="submitName" class="form-control" placeholder="submitName" value="${submitName}">
			<html:errors property="submitName"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="submitDetail">作品の説明&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<textarea class="form-control" name="submitDetail" rows="5" placeholder="submitDetail">${f:h(submitDetail)}</textarea>
			<html:errors property="submitDetail"/>
		</div>
	</div>
	<div class="form-group submitFileArea">
		<label class="control-label col-sm-4" for="submitFile">作品</label>
		<div id="submitDiv" class="col-sm-8 memberF">
			<input type="file" id="submitFile" name="submitFile" class="submitFile">
			<html:errors property="submitFile"/>
		</div>
	</div>
	<div class="form-group">
		<input type="submit" value="確認" id="confirm" name="confirm" class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
	</div>
</form>