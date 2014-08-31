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
	    function changehoge(value) {
	        $("#mailInput").disabled(value); // チェックされたら無効化する
	        $("#mailInput").val($("#mailInput").disabled()); // 今の無効化状態をhogeに書く
	    }
	</script> 
  </head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>会議の情報を入力してください。</h3>
<div class="col-md-12">
<s:form method="POST" >
<form class="form-horizontal">
	<%@ include file="/WEB-INF/view/common/partyFormInput.jsp"%>
	<%@ include file="/WEB-INF/view/common/partyMailFormInput.jsp"%>
	<div class="form-group">
			<div class="col-sm-8">
				<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-5 col-md-offset-6 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
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