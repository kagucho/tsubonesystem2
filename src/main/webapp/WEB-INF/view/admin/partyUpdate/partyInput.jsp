<html>
<html lang="jp">
  <head>
    <%@ include file="/WEB-INF/view/common/headInnerCommon.jsp"%>
  </head>
<body>
	<%@ include file="/WEB-INF/view/common/header.jsp"%>
	<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
	<div class="container">
		<h3>会議の情報を入力してください。</h3>
		<s:form method="POST" >
			<form class="form-horizontal">
				<%@ include file="/WEB-INF/view/common/partyFormInput.jsp"%>
				<%@ include file="/WEB-INF/view/common/partyMailFormInput.jsp"%>
				<div class="form-group">
						<div class="col-sm-8">
							<input type="submit" value="確認" id="confirm" name="confirm" class="col-md-5 col-md-offset-6 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
						</div>
					</div>
			</form>
		</s:form>
	</div>
</body>
</html>