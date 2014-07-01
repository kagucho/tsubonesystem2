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
	<div class="row CENTER">
		<h3 class="col-md-6 col-xs-12">${f:h(tPartyQuestion.tParty.meetingName)}たんへの質問回答</h3>
	</div>
	<div class="table-responsive">
		<table class="table table-bordered">
			<tr class="info">
			<th>質問者</th><th>質問内容</th>
			<tr>
				<td class="col-md-2 col-sm-2">
					${f:h(tPartyQuestion.tMember.hname) }
				</td>
				<td>
					${f:h(tPartyQuestion.question) }
				</td>
			</tr>
		</table>
	</div>
	<div class="table-responsive">
		<table class="table table-bordered">
			<tr class="info">
			<th>回答者</th><th>回答内容</th>
			<c:forEach var="e" items="${tPartyQuestion.tPartyAnswerList}">
				<tr>
					<td class="col-md-2 col-sm-2">
						${f:h(e.tMember.hname) }
					</td>
					<td>
						${f:h(e.answer) }
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>		
	<s:form method="POST" >
		<form name="party" class="form-horizontal">
			<div class="col-md-12">
				<textarea class="form-control" name="answer" rows="5" property="answer" placeholder="質問に対しての解答">${f:h(answer)}</textarea>
				<html:errors property="answer"/>
			</div>
			<input type="submit" value="解答する" id="answerConfirm" name="answerConfirm" property="answerConfirm" class="marginUP col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12  btn btn-primary">
		</form>
</s:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>