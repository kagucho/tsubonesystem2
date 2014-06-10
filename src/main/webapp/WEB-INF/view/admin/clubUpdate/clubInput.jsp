<html>
<html lang="jp">
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
<h3>部の情報を入力してください。</h3>
<div class="col-sm-12">
	<s:form method="POST" >
		<form class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-4" for=ClubName>部の名前</label>
				<div class="col-sm-8 memberF" >
					<input type="text" id="ClubName" name="ClubName" property="ClubName" class="form-control" placeholder="ClubName" value="${ClubName}">
					<html:errors property="ClubName"/>
				</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="OfficerId">部の代表者</label>
				<div class="col-sm-8 memberF">
					<html:select property="OfficerId" value="OfficerId">
						<c:forEach var="e" items="${memberMap}">
							<html:option value="${e.key}">${f:h(e.value)}</html:option>
						</c:forEach>
					</html:select>
					<html:errors property="OfficerId"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="ClubMemo">部の概要</label>
				<div class="col-sm-8 memberF">
					<textarea class="form-control" name="ClubMemo" rows="10" property="ClubMemo" placeholder="ClubMemo">${f:h(ClubMemo)}</textarea>	
					<html:errors property="ClubMemo"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8">
					<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="btn btn-primary">
				</div>
			</div>
		</form>
	</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>