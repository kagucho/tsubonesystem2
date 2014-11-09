<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<%@ include file="/WEB-INF/view/common/mailList.jsp"%>
<div class="container">
	<div id="searchWindow" class="col-sm-12">
		<h4>メール検索</h4>
		<s:form method="POST" >
			<form class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-7" for="obFlag">自分に配信されたメールのみ</label>
					<div class="col-sm-5 memberF">
						<input type="checkbox" id="myMailCheck" name="myMailCheck" value="true" />
					</div>
				</div>
				<div class="form-group col-sm-offset-10">
					<input type="submit" value="検索" id="onSearch" name="onSearch" property="onSearch" class="btn btn-primary">
					<input type="submit" value="クリア" id="index" name="index" property="index" class="btn btn-primary">
				</div>
			</form>
		</s:form>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>