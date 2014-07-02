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
<%@ include file="/WEB-INF/view/common/indeividualsHeader.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3 class="col-md-6 col-xs-12">${f:h(meetingName)}たんの詳細情報</h3>
<c:if test="${!deadFlag && myPartyFlag}">
	<a class="col-md-2 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-danger btnYOKO btnMRC" href="<c:url value="/individuals/partyDelete/${id}"/>" role="button">削除</a>
	<a class="col-md-2 col-sm-5 col-xs-12 btn btn-primary" href="<c:url value="/individuals/partyUpdate/${id}"/>" role="button">更新</a>
</c:if>
<c:if test="${deadFlag && myPartyFlag}">
	<a class="col-md-2 col-sm-5 col-sm-offset-4 col-xs-12  btn btn-primary btnMRC" href="<c:url value="/individuals/partyResult/${id}"/>" role="button">会議の結果を入力する</a>
</c:if>
<div class="col-sm-12">
	<%@ include file="/WEB-INF/view/common/partyFormConfirm.jsp"%>
	<c:if test="${!deadFlag}">
		<div class="row CENTER">
			<a class="col-md-3 col-md-offset-3 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-primary btnYOKO btnMRC" href="<c:url value="/individuals/attend/yes"/>">出席する</a>
			<a class="col-md-3 col-sm-5 col-xs-12 btn btn-primary" href="<c:url value="/individuals/attend/no"/>">欠席する</a>
		</div>
		<s:form method="POST" >
			<form name="party" class="form-horizontal">
				<%@ include file="/WEB-INF/view/common/partyQuestionFormInput.jsp"%>
				<div class="form-group">
					<div class="col-sm-8">
						<input type="submit" value="質問をする" id="questionConfirm" name="questionConfirm" property="questionConfirm" class="col-md-6 col-md-offset-6 col-sm-10 col-sm-offset-4 col-xs-12  btn btn-primary">
					</div>
				</div>
			</form>
		</s:form>
	</c:if>
	<c:if test="${deadFlag}">
		<div class="alert alert-danger"><h4>この会議は締め切り時間を過ぎています</h4></div>
	</c:if>
	<div class="col-md-12 col-xs-12">
<h3 class="col-md-6 col-xs-12">過去の質問一覧</h3>
	<table class="table">
		<tr class="info">
		<th>質問者</th><th>質問内容</th><th></th>
		<c:forEach var="e" items="${tPartyQuestionList}">
			<tr>
				<td class="col-md-2 col-sm-2">
					${f:h(e.tMember.hname) }
				</td>
				<td class="col-md-9 col-sm-9">
					${f:h(e.question) }
				</td>
				<td class="col-md-1 col-sm-1">
					<a class="btn btn-primary" href="<c:url value="/individuals/partyAnswer/${id}/${e.id}"/>" role="button">回答</a>
				</td>
			</tr>
		</c:forEach>
		</tr>
	</table>
</div>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>