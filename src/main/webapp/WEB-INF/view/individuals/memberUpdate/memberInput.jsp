<html>
<html lang="jp">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TsuboneSystem</title>
    <link href="${f:url('/css/bootstrap.min.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/layout.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/signin.css')}" type="text/css" rel="stylesheet">
  </head>
<body>
<%@ include file="/WEB-INF/view/common/indeividualsHeader.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>メンバー情報を入力してください。</h3>
<h5 class="hissu">＊がついている項目は必須です</h5>
<c:if test="${sendErrorFlag}">
	<div class="alert alert-danger"><h4>メールが正常に届いていません。メールアドレスを確認して下さい。</h4></div>
</c:if>
<div class="col-sm-12">
<s:form method="POST" >
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
				<html:multibox property="clubListChecked" value="${e.key}" />&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
			</c:forEach>
		<html:errors property="clubListChecked"/>
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
			<input class="col-sm-3" type="text" id="tel1" name="tel1" property="tel1" class="form-control" placeholder="Tel1" value="${tel1}">
			<input class="col-sm-3" type="text" id="tel2" name="tel2" property="tel2" class="form-control" placeholder="Tel2" value="${tel2}">
			<input class="col-sm-3" type="text" id="tel3" name="tel3" property="tel3" class="form-control" placeholder="Tel3" value="${tel3}" >
			<html:errors property="tel1"/>
			<html:errors property="tel2"/>
			<html:errors property="tel3"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="obFlag">OB宣言</label>
		<div class="col-sm-8 memberF">
			<input type="checkbox" id="obFlag" name="obFlag" value="true" />&nbsp;&nbsp;OB宣言をする
			<html:errors property="obFlag"/>
		</div>
	</div>
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
			<input type="submit" value="確認" id="confirmUp" name="confirmUp" property="confirmUp" class="btn btn-primary">
		</div>
	</div>
</form>
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>