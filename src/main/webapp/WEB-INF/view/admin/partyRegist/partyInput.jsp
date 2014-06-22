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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script type="text/javascript">
	    $(function(){
	        // 初期表示でチェックボックスが空だったら非表示エリアを隠す
	        if ($('#mailSendFlag').val() != '1') {
	            $('#mailInput').hide();
	        }
	    });
	    // 表示/非表示
	    var speed = 500; //表示アニメのスピード（ミリ秒）
	    var stateDeliv = 1;
	    function hideToggle(hidearea) {
	        hidearea.toggle(speed);
	    }
	    function changehoge(value) {
	        $("#hoge").disabled(value); // チェックされたら無効化する
	        $("#hoge").val($("#hoge").disabled()); // 今の無効化状態をhogeに書く
	    }
	</script> 
  </head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<h3>会議の情報を入力してください。</h3>
	<h5 class="hissu">＊がついている項目は必須です</h5>
	<s:form method="POST" >
		<form name="party" class="form-horizontal">
			<div class="col-sm-12">
				<div class="form-group">
					<label class="control-label col-sm-4" for=meetingName>会議の題名&nbsp;<span class="hissu">＊</span></label>
					<div class="col-sm-8 memberF" >
						<input type="text" id="meetingName" name="meetingName" property="meetingName" class="form-control" placeholder="meetingName" value="${meetingName}">
						<html:errors property="meetingName"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4" for="meetingNecessaryFlag">出席必須</label>
					<div class="col-sm-8 memberF">
						<input type="checkbox" id="meetingNecessaryFlag" name="meetingNecessaryFlag" value="true" />&nbsp;出席を必須とする
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4" for="meetingDay">会議の開催日</label>
					<div class="col-sm-8 memberF">
						<input type="text" id="meetingDay" name="meetingDay" property="meetingDay" class="form-control" placeholder="meetingDay" value="${meetingDay}">
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
					<label class="control-label col-sm-4" for="password">会議の内容&nbsp;<span class="hissu">＊</span></label>
					<div class="col-sm-8 memberF">
						<textarea class="form-control" name="meetingMemo" rows="5" property="meetingMemo" placeholder="MeetingMemo" value="${MeetingMemo}"></textarea>
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
						<input type="checkbox" id="ObAttendFlag" name="ObAttendFlag" value="true" />&nbsp;OBも出席対象とする
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
					<label class="control-label col-sm-4">メール配信可否</label>
					<div class="col-sm-8 memberF">
						<input type="checkbox" name="mailSendFlag" value="mailSendFlag" property="mailSendFlag"  onclick="hideToggle($('#mailInput'));" />&nbsp;メールを配信する※チェクしないとメールは配信されません！！
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div id="mailInput">
					<h4>メールを配信する場合は、送信する相手と内容を入力してください。</h4>
					<div class="form-group">
						<label class="control-label col-sm-4" for="mailSendAllFlag">全体に送信する</label>
						<div class="col-sm-8 memberF">
							<input type="checkbox" id="mailSendAllFlag" name="mailSendAllFlag" value="true" onclick="this.blur();this.focus();" onchange="changehoge(this.checked)"/>&nbsp;全員に送信する
							<html:errors property="sendTo"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="clubListCheck">部で選択する</label>
						<div class="col-sm-8 memberF">
							<c:forEach var="e" items="${clubMapSS}">
								<html:multibox property="clubListCheck" value="${e.key}" />&nbsp;${f:h(e.value)}&nbsp;&nbsp;&nbsp;
							</c:forEach>
							<html:errors property="sendTo"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Title">メールのタイトル&nbsp;<span class="hissu">＊</span></label>
						<div class="col-sm-8 memberF" >
							<input type="text" id="title" name="title" property="title" class="form-control" placeholder="Title" >
							<html:errors property="title"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Content">メールの内容&nbsp;<span class="hissu">＊</span></label>
						<div class="col-sm-8 memberF">
							<textarea class="form-control" name="content" rows="10" property="content" placeholder="Content" ></textarea>
							<html:errors property="content"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="mailSendAllFlag">OBにも送信する&nbsp;<span class="hissu">＊</span></label>
						<div class="col-sm-8 memberF">
							<input type="checkbox" id="mailSendOBFlag" name="mailSendOBFlag" value="true" />&nbsp;OBを含める
						</div>
					</div>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>