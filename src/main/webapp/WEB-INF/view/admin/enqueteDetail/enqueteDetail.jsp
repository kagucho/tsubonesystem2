<!doctype html>
<html>

<head>
	<meta charset="utf-8" content="">
	<title>TsuboneSystem</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TsuboneSystem</title>
    <link href="${f:url('/css/bootstrap.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/layout.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/signin.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/docs.min.css')}" type="text/css" rel="stylesheet">
	<script src="${f:url('/js/Chart.js')}"></script>
	<meta name = "viewport" content = "initial-scale = 1, user-scalable = no">
	<style>
	</style>
</head>
<body bgcolor="#e2e2e2">
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<div class="col-md-6">
	<h3 class="col-md-6">アンケート詳細</h3>
	<a class="col-md-2 col-sm-3 col-xs-12 btn btn-primary btnYOKO" href="<c:url value="/admin/enqueteReply/${f:h(id)}"/>" role="button">解答</a>
	<table class="table">
		<tr>
		    <th class="col-md-2 col-sm-2"><h4>タイトル</h4></th>
			<td><h5>${f:h(title)}</h5></td>
		</tr>
		<tr>
			<th class="col-md-2 col-sm-2"><h4>氏名</h4></th>
			<td>${f:h(userName)}</td>
		</tr>
		<tr>
			<th class="col-md-2 col-sm-2"><h4>メモ</h4></th>
			<td class="col-md-10 col-sm-10">${f:h(memo)}</td>
		</tr>
		<c:forEach var="rec" items="${selectedContents}" varStatus="status">
		<tr>
            <th class="col-md-2 col-sm-2"><h4>選択肢${f:h(status.count)}</h4></th>
            <td class="col-md-10 col-sm-10">${f:h(rec)}</td>
        </tr>
        </c:forEach>
        <tr>
        </tr>
	</table>
	<a href="<c:url value="/admin/enqueteList"/>">一覧</a>
	</div>
	<div class="col-md-6">
	<div class="CENTER">
	<canvas id="canvas" height="450" width="450"></canvas>
	<script>
		var pieData = [
				<c:forEach var="s" items="${tEnqueteSelectList}" varStatus="status">
				{
					value: ${s.resultNum},
					color:"rgb("+(120/(${status.count}+${status.count}%2))+","+(160/(${status.count}+${status.count}%2))+","+(240/(${status.count}+${status.count}%2))+")",
				},
				</c:forEach>
			];
		var myPie = new Chart(document.getElementById("canvas").getContext("2d")).Pie(pieData);
	</script>
	 </div>
	 </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>