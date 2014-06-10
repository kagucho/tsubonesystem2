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
<div class="col-sm-12">
<h3>出席受付中の会議</h3>
<div class="table-responsive">
<table class="table">
<tr>
	<th>会議名</th><th class="hidden-xs">必須</th><th>会議日</th><th class="hidden-xs">会議場所</th><th>会議内容</th><th class="hidden-xs">締切日</th><th>出欠席状況</th>
	<c:forEach var="e" items="${partyItemOn}">
		<tr>
			<td>
				<a href="<c:url value="/individuals/partyDetail/"/>${e.id}">${f:h(e.meetingName) }</a>
			</td>
			<td class="hidden-xs">
				<c:if test="${e.meetingNecessaryFlag}">
					<span class="glyphicon glyphicon-ok"></span>
				</c:if>
			</td>
			<td>
				<fmt:formatDate value="${e.meetingDay}" pattern="yyyy/MM/dd HH:mm" /><br>
			</td>
			<td class="hidden-xs">
				${f:h(e.meetingRoom) }
			</td>
			<td>
				${f:h(e.meetingMemo) }
			</td>
			<td class="hidden-xs">
				<fmt:formatDate value="${e.meetingDeadlineDay}" pattern="yyyy/MM/dd HH:mm" /><br>
			</td>
			<td class="hidden-xs">
				<a class="btn btn-info" href="<c:url value="/individuals/partyAttendList/${e.id}"/>">確認</a>
			</td>
		</tr>
	</c:forEach>
</table>
<h3>過去の会議</h3>
<table class="table">
<tr>
	<th>会議名</th><th class="hidden-xs">必須</th><th>会議日</th><th class="hidden-xs">会議場所</th><th>会議内容</th><th class="hidden-xs">締切日</th><th>出欠席状況</th>
	<c:forEach var="e" items="${partyItemOff}">
		<tr>
			<td>
				<a href="<c:url value="/individuals/partyDetail/"/>${e.id}">${f:h(e.meetingName) }</a>
			</td>
			<td class="hidden-xs">
				<c:if test="${e.meetingNecessaryFlag}">
					<span class="glyphicon glyphicon-ok"></span>
				</c:if>
			</td>
			<td>
				<fmt:formatDate value="${e.meetingDay}" pattern="yyyy/MM/dd HH:mm" /><br>
			</td>
			<td class="hidden-xs">
				${f:h(e.meetingRoom) }
			</td>
			<td>
				${f:h(e.meetingMemo) }
			</td>
			<td class="hidden-xs">
				<fmt:formatDate value="${e.meetingDeadlineDay}" pattern="yyyy/MM/dd HH:mm" /><br>
			</td>
			<td class="hidden-xs">
				<a class="btn btn-info" href="<c:url value="/individuals/partyAttendList/${e.id}"/>">確認</a>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>