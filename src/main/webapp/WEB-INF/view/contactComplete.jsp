<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container CENTER">
	<h3>問い合わせを受け付けました</h3>
	<a href="<c:url value="/index"/>"><button type="button" class="col-md-4 col-md-offset-1 col-sm-5 col-xs-12 btn btn-default btn-lg btnYOKO30 btnMRC">Back.TOP </button></a>
</div>
<div class="container">
	<div class="bs-callout bs-callout-info">
			<h4>ご注意</h4>
			<ul>
				<li>システム上、ご返信に1週間以上お時間をいただく事がございます。</li>
				<li>当サークルは他校の大学に属していても入部する事が可能です。</li>
				<li>社会人の入部はお受け付けしておりません。</li>
			</ul>
		</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>