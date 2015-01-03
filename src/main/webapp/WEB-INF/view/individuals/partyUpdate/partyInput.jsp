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
	<h5 class="hissu">＊がついている項目は必須です</h5>
	<s:form method="POST" >
		<form name="party" class="form-horizontal">
			<%@ include file="/WEB-INF/view/common/partyFormInput.jsp"%>
			<%@ include file="/WEB-INF/view/common/partyMailFormInput.jsp"%>
			<div class="form-group">
				<div class="col-sm-8">
					<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-6 col-md-offset-6 col-sm-10 col-sm-offset-4 col-xs-12  btn btn-primary">
				</div>
			</div>
		</form>
	</s:form>
</div>
</body>
</html>