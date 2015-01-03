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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="${f:url('/js/mailSendSelectArea.js')}"></script>
	<script src="${f:url('/js/mailSendSelect.js')}"></script>
  </head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<div class="row CENTER">
	<h3 class="col-md-12 col-xs-12">会議での審議結果を入力してください</h3>
</div>
<div class="col-sm-12">
	<table class="table">
		<tr>
			<th><h4>会議の名前</h4></th>
			<td><h5>${f:h(meetingName)}</h5></td>
		</tr>
	</table>
		<s:form method="POST" >
			<form class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-4" for="meetingResult">審議結果&nbsp;<span class="hissu">＊</span></label>
					<div class="col-sm-8 memberF">
						<textarea class="form-control" name="meetingResult" rows="10" property="meetingResult" placeholder="meetingResult">${f:h(meetingResult)}</textarea>
						<html:errors property="meetingResult"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4" for="resultEditEndFlag">以後の編集を禁止する</label>
					<div class="col-sm-8 memberF">
						<input type="checkbox" id="resultEditEndFlag" name="resultEditEndFlag" <c:if test="${resultEditEndFlag}"> checked="checked"</c:if> value="true" />&nbsp;以後自分以外の編集を禁止にする。
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">メールを送る</label>
					<div class="col-sm-8 memberF mailSendFlag">
						<input type="checkbox" id="mailSendFlag" name="mailSendFlag" value="true" property="mailSendFlag"  onclick="hideToggle($('#mailInput'));"  <c:if test="${mailSendFlag}"> checked="checked"</c:if>/>&nbsp;上記の結果をメールで送る
					</div>	
				</div>
				<div id="mailInput">
					<h4>メールを配信する場合は、送信する相手を選択してください。</h4>
					<div class="form-group">
						<label class="control-label col-sm-4" for="mailSendAllFlag">現役 or OB</label>
						<div class="col-sm-8 memberF activeOrOb">
							<input type="radio" name="activeOrOb" value="1" <c:if test="${activeOrOb == 1}">checked</c:if> >&nbsp;現役生に送信する
							<input type="radio" name="activeOrOb" value="2" <c:if test="${activeOrOb == 2}">checked</c:if> >&nbsp;OBに配信する
							<html:errors property="activeOrOb"/>
						</div>
					</div>
					<div class="form-group clubListCheck">
						<label class="control-label col-sm-4" for="clubListCheck">全員 or 部ごと</label>
						<div class="col-sm-8 memberF allOrClub">
							<input type="radio" name="allOrClub" value="1" <c:if test="${allOrClub == 1}">checked</c:if> >&nbsp;全員に送信する
							<input type="radio" name="allOrClub" value="2" <c:if test="${allOrClub == 2}">checked</c:if> >&nbsp;部で選択する
							<html:errors property="allOrClub"/>
							<div id = "selectClubDiv">
							(
							<c:forEach var="e" items="${clubMapSS}">
								<html:multibox  property="clubListCheck" value="${e.key}" />&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
							</c:forEach>
							)
							</div>
							<html:errors property="clubListCheck"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12  btn btn-primary">
				</div>
			</form>
		</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>