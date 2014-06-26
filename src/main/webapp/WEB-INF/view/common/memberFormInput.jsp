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
			<input type="text" id="mail" name="mail" property="mail" class="form-control" placeholder="EmailAddress"  value="${mail}">
			<html:errors property="mail"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="department">所属部&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
		<c:forEach var="e" items="${clubMapSS}">
			<html:multibox property="clubListCheck" value="${e.key}" />&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
		</c:forEach>
		<html:errors property="department"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="curriculum">学科</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="curriculum" name="curriculum" property="curriculum" class="form-control" placeholder="Curriculum"  value="${curriculum}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="entrance">入学年度&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<input type="text" id="entrance" name="entrance" property="entrance" class="form-control" placeholder="Entrance"  value="${entrance}">
			<html:errors property="entrance"/>
		</div>
		
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="tel">電話番号</label>
		<div class="col-sm-8 memberF">
			<input class="col-sm-3" type="text" id="tel1" name="tel1" property="tel1" class="form-control" placeholder="(03)" value="${tel1}">
			<input class="col-sm-3" type="text" id="tel2" name="tel2" property="tel2" class="form-control" placeholder="(1234)" value="${tel2}">
			<input class="col-sm-3" type="text" id="tel3" name="tel3" property="tel3" class="form-control" placeholder="(5678)" value="${tel3}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="obFlag">OB宣言</label>
		<div class="col-sm-8 memberF">
			<input type="checkbox" id="obFlag" name="obFlag" value="true" />&nbsp;&nbsp;OB宣言をする
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="userName">ログインID&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<input type="text" id="userName" name="userName" property="userName" class="form-control" placeholder="UserName" value="${userName}">
			<html:errors property="userName"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="password">Password&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<input type="password" id="password" name="password" property="password" class="form-control" placeholder="Password" value="${password}">
			<html:errors property="password"/>
		</div>
	</div>
	<div class="form-group">
		<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
	</div>
</form>