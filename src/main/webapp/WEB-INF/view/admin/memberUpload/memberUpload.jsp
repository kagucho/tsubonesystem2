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
			<input type="file" id="file" name="file" property="file">
			<input type="submit" value="確認"  name="upload" id="upload"  property="upload" class="btn btn-primary">
	</s:form>
</div>
</body>
</html>