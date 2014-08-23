<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<div class="col-sm-12">
		<h3>選択されたメンバーを確認し、与える権限を設定してください。</h3>
		<div class="table-responsive">
			<table class="table">
				<tr>
					<th>名前</th><th>ハンドルネーム</th>
				</tr>
				<tr>
					<td>
						${f:h(tMemberNew.name) }
					</td>
					<td>
						${f:h(tMemberNew.hname) }
					</td>
				</tr>
			</table>
		</div>
		<s:form method="POST">
			<%@ include file="/WEB-INF/view/common/leadersPowerInput.jsp"%>
			<input type="submit" value="確認" id="confirmSec" name="confirmSec" property="confirmSec" class="btn btn-primary">
		</s:form>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>