<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript">
<%-- 送信対象が全員を選択したときは部の選択ボックスをdisableにする --%>
$(function() {
	$("#mailSendAllFlag").click(function() {
		var val = $("#mailSendAllFlag:checked").val();
		if(val == "true") {
			$("#clubListCheck").attr('disabled', true);
		} else {
			$("#clubListCheck").attr('disabled', false);
		}
	});
});
</script>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<h3 class="col-md-4 col-sm-5 col-xs-12">メール内容作成</h3>
	<a class="col-md-2 col-md-offset-6 col-sm-2 col-sm-offset-5 col-xs-12  btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/mailList"/>" role="button">Mail History</a>
	<s:form method="POST">
		<%@ include file="/WEB-INF/view/common/partyMailFormInput.jsp"%>
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
		<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3 col-xs-12  btn btn-primary">
	</s:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>