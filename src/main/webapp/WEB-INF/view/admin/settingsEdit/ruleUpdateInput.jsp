<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container CENTER">
	<h3>規約のPDFを選択してください</h3>
	<table class="table">
		<s:form action="ruleUpdateComplete" method="POST" enctype="multipart/form-data">
			<form class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-4" for="rulePdf">規約PDF</label>
					<div class="col-sm-8 memberF">
						<input type="file" id="rulePdf" name="rulePdf" placeholder="PDF" />
						<html:errors property="rulePdf"/>
					</div>
				</div>
				<div class="form-group">
					<input type="submit" value="確認" id="ruleUpdateComplete" name="ruleUpdateComplete" class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
				</div>
			</form>
		</s:form>
	</table>
</div>
<div class="container">
	<div class="bs-callout bs-callout-info">
		<h4>注意事項</h4>
		<ul>
			<li>アップロードできるファイルサイズは<span class="hissu">5MBまで</span>です。</li>
			<li>アップロードできる拡張子は<span class="hissu">PDFのみ</span>です。</li>
			<li>"A4ヨコ・二段組"が最適に表示されるようになっているので文の体裁を整えてからアップロードしてください。</li>
		</ul>
	</div>
</div>
<div class="container CENTER">
	<h3>現在の規約</h3>
	<div class="col-md-12 col-sm-12 col-xs-12">
	 	<object data="${f:url('/pdf/kaisoku.pdf')}" width="1150" height="600"></object>
	 </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>