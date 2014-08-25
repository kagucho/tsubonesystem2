<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>以下の一時アカウントを削除しますか？</h3>
<div class="col-sm-12">
	<h4>現状の仮IDを仮パスワード</h4>
	<table class="table table-bordered">
		<tr class="info">
			<th>ID</th><th>Password</th>
		</tr>
		<c:forEach var="e" items="${tTempLoginNow}">
		<tr>
			<td>${e.userName}</td><td>*************</td>
		</tr>
		</c:forEach>
	</table>
	<s:form method="POST" >
		<input type="submit" value="戻る" id="viewinput" name="viewinput" property="viewinput" class="col-md-3 col-md-offset-3 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-primary btnYOKO btnMRC ">
		<input type="submit" value="削除" id="tempMemberDeleteComplete" name="tempMemberDeleteComplete" property="tempMemberDeleteComplete" class="col-md-3 col-sm-5 col-xs-12 btn btn-danger">
	</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>