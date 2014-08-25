<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>一時メンバー情報を入力してください。</h3>
<h5 class="hissu">＊がついている項目は必須です</h5>
<div class="col-sm-12">
	<table class="table">
		<s:form method="POST" >
			<form class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-4" for="userName">ログインID&nbsp;<span class="hissu">＊</span></label>
					<div class="col-sm-8 memberF">
						<input type="text" id="userName" name="userName" property="userName" class="form-control" placeholder="UserName" >
						<html:errors property="userName"/>
					</div>
					<input type="submit" value="確認" id="tempMemberConfirm" name="tempMemberConfirm" property="tempMemberConfirm" class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12 btn btn-primary">
				</div>
			</form>
		</s:form>
	</table>
	<h4>現状の仮IDと仮パスワード</h4>
	<table class="table table-bordered">
		<tr class="info">
			<th>ID</th><th>Password</th>
		</tr>
		<c:forEach var="e" items="${tTempLoginNow}">
		<tr>
			<td>${e.userName}</td><td>(パスワードは復号化出来ないため表示出来ません。忘れた場合には登録し直してください。)</td>
		</tr>
		</c:forEach>
	</table>
	<s:form method="POST" >
		<input type="submit" value="削除" id="tempMemberDeleteConfirm" name="tempMemberDeleteConfirm" property="tempMemberDeleteConfirm" class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-danger">
	</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>