<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>TsuboneSystem</title>
	<link href="${f:url('/css/bootstrap.min.css')}" type="text/css" rel="stylesheet">
	<link href="${f:url('/css/layout.css')}" type="text/css" rel="stylesheet">
	<link href="${f:url('/css/signin.css')}" type="text/css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<s:form method="POST" enctype="multipart/form-data">
		<font color="red">${f:br(f:nbsp(f:h(errorMsg)))}</font>
		<font color="blue">${f:br(f:nbsp(f:h(infoMsg)))}</font>
		<input type="file" id="file" value="ファイルを選択" name="file" property="file">
		<br />
		<input type="submit" value="アップロードを実行"  name="upload" id="upload"  property="upload" class="btn btn-primary">
		<br />
		<br />
		<hr style="border-top: 2px dashed #ff9d9d;width: 100%;">
		<input type="submit" value="アップロードファイルのひな形をダウンロード"  name="download" id="download"  property="download" class="btn btn-primary">
	</s:form>
	
	<br />
	一件でもエラーがあるとデータはひとつも登録されません。エラーの場合ははじめからやり直してください。<br />
	全てのデータが正常に登録されると、登録されたメンバーにメールが送られます。
<div  class="container">
	<table class="table">
	<thead>
		<tr>
			<th>列番号</th>
			<th>見出し名</th>
			<th>説明</th>
		</tr>
	</thead>
		<tr>
			<td>1列目</td>
			<td>hname</td>
			<td>ハンドルネーム(10文字まで)</td>
		</tr>
		<tr>
			<td>2列目</td>
			<td>mail</td>
			<td>メールアドレス</td>
		</tr>
		<tr>
			<td>3列目</td>
			<td>id</td>
			<td>ログインID(英数字)</td>
		</tr>
		<tr>
			<td>4列目</td>
			<td>pw</td>
			<td>ログインパスワード(英数字)</td>
		</tr>
	</table>
</div>
</div>
</body>
</html>