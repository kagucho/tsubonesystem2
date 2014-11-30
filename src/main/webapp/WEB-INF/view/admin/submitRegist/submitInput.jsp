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
			<h3>提出したい作品を入力してください。</h3>
			<h5 class="hissu">＊がついている項目は必須です</h5>
			<s:form method="POST" enctype="multipart/form-data" ><%-- 画像などをアップロードするときは"enctype="multipart/form-data" "をつける --%>
				<div class="form-group">
				<label class="control-label col-sm-4" for="submitProductFileType">種別<span class="hissu">＊</span></label>
					<div class="col-sm-8 memberF">
						<c:forEach var="b" items="${submitProductFileCodeMap}">
							<html:radio styleClass="submitProductFileType" property="submitProductFileType" value="${b.key}"/>&nbsp;${f:h(b.value)}&nbsp;&nbsp;&nbsp;
						</c:forEach>
						<html:errors property="submitProductFileType"/>
					</div>
				</div>
				<div id="programForm"><%@ include file="/WEB-INF/view/common/submitFormInputProgram.jsp"%></div>
				<div id="imageForm"><%@ include file="/WEB-INF/view/common/submitFormInputImage.jsp"%></div>
				<div id="dtmForm"><%@ include file="/WEB-INF/view/common/submitFormInputDtm.jsp"%></div>
			</s:form>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${f:url('/js/bootstrap.min.js')}"></script>
	</body>
</html>