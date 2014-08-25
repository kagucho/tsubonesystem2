<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>以下の内容で登録します。よろしいですか？</h3>
<div class="col-sm-12">
	<table class="table">
		<tr>
			<th><h4>ログインID</h4></th>
			<td><h5>${f:h(userName)}</h5></td>
		</tr>
		<tr>
			<th><h4>Password</h4></th>
			<td><h5>${f:h(password)}</h5></td>
		</tr>
	</table>
<s:form method="POST" >
	<input type="submit" value="戻る" id="viewinput" name="viewinput" property="viewinput" class="col-md-3 col-md-offset-3 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-primary btnYOKO btnMRC ">
	<input type="submit" value="登録" id="tempMemberComplete" name="tempMemberComplete" property="tempMemberComplete" class="col-md-3 col-sm-5 col-xs-12 btn btn-primary">
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>