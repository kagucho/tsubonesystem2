<html>
<html lang="jp">
	<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TsuboneSystem</title>
    <link href="${f:url('/css/bootstrap.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/layout.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/signin.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/docs.min.css')}" type="text/css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="${f:url('/js/change.js')}"></script>
    <script src="${f:url('/js/imageFileApi.js')}"></script>
    <script src="${f:url('/js/uploadFileCheck.js')}"></script>
	</head>
	<body>
		<%@ include file="/WEB-INF/view/common/header.jsp"%>
		<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
		<div class="container">
			<h3>TOPに表示したいお知らせを入力してください。</h3>
			<h5 class="hissu">＊がついている項目は必須です</h5>
			<s:form method="POST" enctype="multipart/form-data"><%-- 画像などをアップロードするときは"enctype="multipart/form-data" "をつける --%>
				<c:if test="${submitProductFileType == 1}"><%@ include file="/WEB-INF/view/common/submitFormInputImage.jsp"%></c:if><%-- 画像系 --%>
				<c:if test="${submitProductFileType == 2}"><%@ include file="/WEB-INF/view/common/submitFormInputProgram.jsp"%></c:if><%-- プログラム系 --%>
				<c:if test="${submitProductFileType == 3}"><%@ include file="/WEB-INF/view/common/submitFormInputDtm.jsp"%></c:if><%-- DTM系 --%>
			</s:form>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${f:url('/js/bootstrap.min.js')}"></script>
	</body>
</html>