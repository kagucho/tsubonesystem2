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
	        if ('${mailSendFlag}' == 'false') {
	            $('#mailInput').hide();
	        }
	    });
	    // 表示/非表示
	    var speed = 500; //表示アニメのスピード（ミリ秒）
	    var stateDeliv = 1;
	    function hideToggle(hidearea) {
	        hidearea.toggle(speed);
	    }
	    $(function changehoge(value) {
	        $("#mailInput").disabled(value); // チェックされたら無効化する
	        $("#mailInput").val($("#mailInput").disabled()); // 今の無効化状態をhogeに書く
	    });
	</script>
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
					<div class="col-sm-8 memberF">
						<input type="checkbox" id="mailSendFlag" name="mailSendFlag" value="mailSendFlag" property="mailSendFlag"  onclick="hideToggle($('#mailInput'));"  <c:if test="${mailSendFlag}"> checked="checked"</c:if>/>&nbsp;上記の結果をメールで送る
					</div>	
				</div>
				<div id="mailInput">
					<h4>送信する相手を選択してください</h4>
					<div class="form-group">
						<label class="control-label col-sm-4" for="mailSendAllFlag">全体に送信する</label>
						<div class="col-sm-8 memberF">
							<input type="checkbox" id="mailSendAllFlag"  name="mailSendAllFlag" value="true" <c:if test="${mailSendAllFlag}"> checked="checked"</c:if>/>&nbsp;全員に送信する(対象者関係なく全員に送信されます)
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
						<label class="control-label col-sm-4" for="mailSendAllFlag">OBにも送信する&nbsp;</label>
						<div class="col-sm-8 memberF">
							<input type="checkbox" id="mailSendOBFlag" <c:if test="${mailSendOBFlag}"> checked="checked"</c:if>  name="mailSendOBFlag" value="true" />&nbsp;OBを含める
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