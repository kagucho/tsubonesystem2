<html lang="jp">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>tsuboneSystem</title>
		<link href="${f:url('/css/bootstrap.css')}" type="text/css" rel="stylesheet">
		<link href="${f:url('/css/bootstrap-responsive.css')}" type="text/css" rel="stylesheet">
		<link href="${f:url('/css/signin.css')}" type="text/css" rel="stylesheet">
		<link href="${f:url('/css/layout.css')}" type="text/css" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${f:url('/js/bootstrap.min.js')}"></script>
	</head>
	<body>
		<%@ include file="/WEB-INF/view/common/header.jsp"%>
		<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
		<div class="container">
			<a href="<c:url value="/admin/settingsEdit/topBuckImageRegist"/>">
				<button type="button" class="btn btn-default col-xs-12 btnMRC" data-sort="myorder:desc">新しい写真をアップロードする</button>
			</a>
			<c:forEach var="e" items="${tImageUploadList}" varStatus="setSta">
				<div class="col-md-12 col-sm-12 marginUP">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<img src="${f:url('/api/displyImage')}${e.id}" width="240" height="180" style="display:block;width:60%;height:auto;"/>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<a class="btn btn-danger btnYOKO btnMRC" href="<c:url value="/admin/settingsEdit/topBuckImageDelete/${e.id}"/>" role="button">削除</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	</body>
</html>