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
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>