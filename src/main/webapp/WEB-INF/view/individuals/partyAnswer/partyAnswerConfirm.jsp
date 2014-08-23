<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>以下の内容で登録します。よろしいですか？</h3>
<div class="col-sm-12">
<s:form method="POST" >
<table class="table">
	<tr>
		<th class="col-md-3"><h4>回答内容</h4></th>
		<td><h5>${f:h(answer)}</h5></td>
	</tr>
	<tr>
		<th><h4>メールを送る</h4></th>
		<td>
			<c:forEach var="b" items="${answerSendKindMap}">
				<html:radio property="answerSendKind" value="${b.key}"/>&nbsp;${f:h(b.value)}&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<html:errors property="sex"/>
		</td>
	</tr>
</table>
<input type="submit" value="戻る" id="index" name="index" property="index" class="col-md-3 col-md-offset-3 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-primary btnYOKO btnMRC ">
<input type="submit" value="登録" id="answerComplete" name="answerComplete" property="answerComplete" class="col-md-3 col-sm-5 col-xs-12 btn btn-primary">
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>