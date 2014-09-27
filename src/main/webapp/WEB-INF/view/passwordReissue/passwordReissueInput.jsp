<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3 class="CENTER">登録したメールアドレスを入力してください</h3>
	<s:form method="POST" >
		<form class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-4 CENTER" for="mailAddress">メールアドレス&nbsp;</label>
				<div class="col-sm-8 memberF" >
					<input type="text" id="mailAddress" name="mailAddress" class="form-control btnMRC" placeholder="MailAddress">
					<html:errors property="mailAddress"/>
				</div>
			</div>
			<div class="form-group">
				<input type="submit" value="再発行する" id="complete" name="complete" class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
			</div>
		</form>
	</s:form>
</div>
<div class="container">
	<div class="bs-callout bs-callout-info">
			<h4>注意事項</h4>
			<ul>
				<li>登録されたメールアドレスとログインIDに一致していたときのみ、メールが配信されます。</li>
				<li>メールに記載されている仮パスワードを使ってログインし、可及的速やかにパスワードの再設定を行ってください。</li>
				<li>メールが来ないときはもう一度確認してください。</li>
				<li>メールアドレスも忘れたあふぉは知らん。web管理者か局長にでも連絡してください。</li>
				<li><span class="hissu">仮パスワードのまま使用してると取得した全単位が没収されます。</span></li>
			</ul>
		</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>