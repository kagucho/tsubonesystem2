<div class="container">
<h3>部の情報を入力してください。</h3>
<h5 class="hissu">＊がついている項目は必須です</h5>
<div class="col-sm-12">
	<s:form method="POST" >
		<form class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-4" for=ClubName>部の名前&nbsp;<span class="hissu">＊</span></label>
				<div class="col-sm-8 memberF" >
					<input type="text" id="ClubName" name="ClubName" property="ClubName" class="form-control" placeholder="ClubName" value="${ClubName}">
					<html:errors property="ClubName"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="OfficerId">部の代表者&nbsp;<span class="hissu">＊</span></label>
				<div class="col-sm-8 memberF">
					<html:select property="OfficerId" value="${tLeaders.tMember.id}">
						<c:forEach var="e" items="${memberMap}">
							<html:option value="${e.key}">${f:h(e.value)}</html:option>
						</c:forEach>
					</html:select>
					<html:errors property="OfficerId"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="ClubMemo">部の概要&nbsp;<span class="hissu">＊</span></label>
				<div class="col-sm-8 memberF">
					<textarea class="form-control" name="ClubMemo" rows="10" property="ClubMemo" placeholder="ClubMemo">${f:h(ClubMemo)}</textarea>	
					<html:errors property="ClubMemo"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8">
					<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-6 col-md-offset-6 col-sm-6 col-sm-offset-6 col-xs-12  btn btn-primary">
				</div>
			</div>
		</form>
	</s:form>
</div>
</div>