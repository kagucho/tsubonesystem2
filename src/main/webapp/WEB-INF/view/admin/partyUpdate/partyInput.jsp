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
<h3>会議の情報を入力してください。</h3>
<div class="col-sm-12">
<s:form method="POST" >
<form class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-4" for=meetingName>会議の題名</label>
		<div class="col-sm-8 memberF" >
			<input type="text" id="meetingName" name="meetingName" property="meetingName" class="form-control" placeholder="meetingName" value="${meetingName}">
			<html:errors property="meetingName"/>
		</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="meetingNecessaryFlag">出席必須</label>
		<div class="col-sm-8 memberF">
			<input type="checkbox" id="meetingNecessaryFlag" name="meetingNecessaryFlag" <c:if test="${meetingNecessaryFlag}"> checked="checked"</c:if> value="true" />&nbsp;出席を必須とする
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="meetingDay">会議の開催日</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="meetingDay" name="meetingDay" property="meetingDay" class="form-control" placeholder="meetingDay" value="${meetingDay}" >
			<html:errors property="meetingDay"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="meetingTime">会議の開催時間</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="meetingTime" name="meetingTime" property="meetingTime" class="form-control" placeholder="meetingTime" value="${meetingTime}">
			<html:errors property="meetingTime"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="meetingRoom">会議の開催場所</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="meetingRoom" name="meetingRoom" property="meetingRoom" class="form-control" placeholder="meetingRoom" value="${meetingRoom}">
			<html:errors property="meetingRoom"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="password">会議の内容</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="meetingMemo" name="meetingMemo" property="meetingMemo" class="form-control" placeholder="meetingMemo" value="${meetingMemo}">
			<html:errors property="meetingMemo"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="attendClub">出席対象者を部に絞る</label>
		<div class="col-sm-8 memberF">
			<c:forEach var="e" items="${clubMapSS}">
				<html:multibox property="attendClub" value="${e.key}" />&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<p>&nbsp;※無選択で全員が対象</p>
			<html:errors property="attendClub"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="ObAttendFlag">OB出席</label>
		<div class="col-sm-8 memberF">
			<input type="checkbox" id="ObAttendFlag" name="ObAttendFlag" <c:if test="${ObAttendFlag}"> checked="checked"</c:if> value="true" />&nbsp;OBも出席対象とする
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="meetingDeadlineDay">出欠席締め切り日</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="meetingDeadlineDay" name="meetingDeadlineDay" property="meetingDeadlineDay" class="form-control" placeholder="meetingDeadlineDay" value="${meetingDeadlineDay}">
			<html:errors property="meetingDeadlineDay"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="meetingDeadlineTime">締切時間</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="meetingDeadlineTime" name="meetingDeadlineTime" property="meetingDeadlineTime" class="form-control" placeholder="meetingDeadlineTime" value="${meetingDeadlineTime}">
			<html:errors property="meetingDeadlineTime"/>
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