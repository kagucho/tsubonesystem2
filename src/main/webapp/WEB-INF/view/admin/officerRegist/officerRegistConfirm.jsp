<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
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
		<form class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-4" for="secretInformation">秘匿情報の表示</label>
				<div class="col-sm-8 memberF">
					<html:checkbox property="secretInformation"/>&nbsp;&nbsp;メンバーの電話番号が閲覧出来るようにする
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="memberUpdate">メンバー情報の更新権限</label>
				<div class="col-sm-8 memberF">
					<html:checkbox property="memberUpdate"/>&nbsp;&nbsp;メンバー情報を更新出来るようにする
				</div>	
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="attendUpdate">出欠変更権限</label>
				<div class="col-sm-8 memberF">
					<html:checkbox property="attendUpdate"/>&nbsp;&nbsp;他者の出欠を変更出来るようにする
				</div>
			</div>
			<input type="submit" value="確認" name="confirmSec" class="btn btn-primary">
		</form>
	</s:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>