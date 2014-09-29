<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3 class="CENTER">メールが配信されました</h3>
<h4 class="CENTER">※配信されない場合はメールアドレスをよく確認してください。</h4>
<a href="<c:url value="/login"/>/"><span class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">ログイン画面に戻る</span></a>
</div>
<div class="container">
	<div class="bs-callout bs-callout-info">
			<h4>注意事項</h4>
			<ul>
				<li>入力されたメールアドレスが、登録されているメールアドレスに<span class="hissu">一致していたときのみ、</span>メールが配信されます。</li>
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