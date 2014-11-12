	<form class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-4" for="name">名前&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF" >
			<input type="text" id="name" name="name" property="name" class="form-control" placeholder="Name" value="${name}">
			<html:errors property="name"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="sex">性別&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<c:forEach var="b" items="${sexMap}">
				<html:radio property="sex" value="${b.key}"/>&nbsp;${f:h(b.value)}&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<html:errors property="sex"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="hname">ハンドルネーム&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<input type="text" id="hname" name="hname" property="hname" class="form-control" placeholder="H.N" value="${hname}">
			<html:errors property="hname"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="mail">メールアドレス&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<input type="text" id="mail" name="mail" property="mail" class="form-control" placeholder="EmailAddress" value="${mail}">
			<html:errors property="mail"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="department">所属部&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
		<c:forEach var="e" items="${clubMap}">
			<html:multibox property="clubListChecked" value="${e.key}"/>&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
		</c:forEach>
		<html:errors property="department"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="curriculum">学科</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="curriculum" name="curriculum" property="curriculum" class="form-control" placeholder="Curriculum" value="${curriculum}">
			<html:errors property="curriculum"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="entrance">入学年度&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<input type="text" id="entrance" name="entrance" property="entrance" class="form-control" placeholder="Entrance" value="${entrance}">
			<html:errors property="entrance"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="tel">電話番号</label>
		<div class="col-sm-8 memberF">
			<input class="col-sm-3" type="text" id="tel1" name="tel1" property="tel1" class="form-control" placeholder="(090)" value="${tel1}">
			<input class="col-sm-3" type="text" id="tel2" name="tel2" property="tel2" class="form-control" placeholder="(123)" value="${tel2}">
			<input class="col-sm-3" type="text" id="tel3" name="tel3" property="tel3" class="form-control" placeholder="(456)" value="${tel3}" >
			<html:errors property="tel1"/>
			<html:errors property="tel2"/>
			<html:errors property="tel3"/>
		</div>
	</div>
	<c:if test="${loginMemberDto.actorKindCode == '1'}"><%-- adminの時はOBを解除できる --%>
		<div class="form-group">
			<label class="control-label col-sm-4" for="obFlag">OB宣言</label>
			<div class="col-sm-8 memberF">
				<input type="checkbox" id="obFlag" name="obFlag" value="true" <c:if test="${obFlag}"> checked="checked"</c:if> />&nbsp;&nbsp;OB宣言をする&nbsp;<span class="hissu">(注：一度宣言すると取り消せません！！)</span>
				<html:errors property="obFlag"/>
			</div>
		</div>
		<div id = "sendStopFlagBox">
			<div class="form-group">
				<label class="control-label col-sm-4" for="sendStopFlag">メール受信可否</label>
				<div class="col-sm-8 memberF">
					<input type="checkbox" id="sendStopFlag" name="sendStopFlag" value="true" <c:if test="${sendStopFlag}">checked</c:if>  />&nbsp;&nbsp;メールを受信しない
					<html:errors property="obFlag"/>
				</div>
			</div>	
		</div>
	</c:if>
	<c:if test="${loginMemberDto.actorKindCode != '1'}"><%-- admin以外はOBを解除できない --%>
		<c:if test="${!obFlag}">
			<div class="form-group">
				<label class="control-label col-sm-4" for="obFlag">OB宣言</label>
				<div class="col-sm-8 memberF">
					<input type="checkbox" id="obFlag" name="obFlag" value="true" <c:if test="${obFlagDisply}"> checked="checked"</c:if> />&nbsp;&nbsp;OB宣言をする&nbsp;<span class="hissu">(注：一度宣言すると取り消せません！！)</span>
					<html:errors property="obFlag"/>
				</div>
			</div>
			<div id = "sendStopFlagBox">
				<div class="form-group">
					<label class="control-label col-sm-4" for="sendStopFlag">メール受信可否</label>
					<div class="col-sm-8 memberF">
						<input type="checkbox" id="sendStopFlag" name="sendStopFlag" value="true" <c:if test="${sendStopFlag}">checked</c:if>  />&nbsp;&nbsp;メールを受信しない
						<html:errors property="obFlag"/>
					</div>
				</div>	
			</div>
		</c:if>
		<c:if test="${obFlag}">
			<div class="form-group">
				<label class="control-label col-sm-4" for="sendStopFlag">メール受信可否</label>
				<div class="col-sm-8 memberF">
					<input type="checkbox" id="sendStopFlag" name="sendStopFlag" value="true" <c:if test="${sendStopFlag}">checked</c:if>  />&nbsp;&nbsp;メールを受信しない
					<html:errors property="obFlag"/>
				</div>
			</div>	
		</c:if>
	</c:if>
	<div class="form-group">
		<label class="control-label col-sm-4" for="userName">UserName&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<input type="text" id="userName" name="userName" property="userName" class="form-control" placeholder="UserName" value="${userName}">
			<html:errors property="userName"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="password">Password&nbsp;※更新したい時のみ入力</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="password" name="password" property="password" class="form-control" placeholder="Password" value="${password}">
			<html:errors property="password"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-8">
			<input type="submit" value="確認" id="confirmUp" name="confirmUp" property="confirmUp" class="col-md-6 col-md-offset-6 col-sm-6 col-sm-offset-6 col-xs-12  btn btn-primary">
		</div>
	</div>
</form>