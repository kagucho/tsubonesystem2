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
<div class="col-sm-12">
<div class="table-responsive">
<div class="col-sm-4">
<h4 class="col-md-4">出席</h4>
<c:if test="${!deadFlag}">
	<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/admin/partyAttendUpdate/1"/>">編集</a>
</c:if>
<table class="table">
<tr>
<th>ハンドルネーム</th>
	<c:forEach var="e" items="${tAttendOn}">
		<tr>
			<td>
				<a href="<c:url value="/admin/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
			</td>
		</tr>
	</c:forEach>
</table>
<c:if test="${!empty(tMemberOn)}">
	<s:form method="POST" >
		<input type="submit" value="DownList" id="createExcel" name="createExcel" property="createExcel" class="btn btn-success ">
		<input type="submit" value="Mail" id="contentRegist" name="contentRegist" property="contentRegist" class="btn btn-success">
	</s:form>
</c:if>
</div>
<div class="col-sm-4">
<h4 class="col-md-4">欠席</h4>
<c:if test="${!deadFlag}">
	<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/admin/partyAttendUpdate/2"/>">編集</a>
</c:if>
<table class="table">
<tr>
<th>ハンドルネーム</th>
	<c:forEach var="e" items="${tAttendOff}">
		<tr>
			<td>
				<a href="<c:url value="/admin/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<div class="col-sm-4">
<h4 class="col-md-4">未回答</h4>
<c:if test="${!deadFlag}">
	<a class="btn btn-primary col-md-4 col-md-offset-4" href="<c:url value="/admin/partyAttendUpdate/3"/>">編集</a>
</c:if>
<table class="table">
<tr>
<th>ハンドルネーム</th>
	<c:forEach var="e" items="${tAttendKuzu}">
		<tr>
			<td>
				<a href="<c:url value="/admin/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>