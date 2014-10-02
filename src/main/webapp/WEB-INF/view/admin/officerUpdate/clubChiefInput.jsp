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
	    // 表示/非表示
	    function toggle() {
	    	$("#secretInformation").attr("checked", true);
	    }
	</script> 
  </head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<s:form method="POST">
		<form class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-4" for="memberUpdate">メンバー情報の更新権限</label>
				<div class="col-sm-8 memberF">
					<html:checkbox property="memberUpdate" onclick="toggle();"/>&nbsp;&nbsp;メンバー情報を更新出来るようにする&nbsp;(※ONにすると自動的に秘匿情報も開示されます)
				</div>	
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="secretInformation">秘匿情報の表示</label>
				<div class="col-sm-8 memberF">
					<html:checkbox property="secretInformation"/>&nbsp;&nbsp;メンバーの電話番号が閲覧出来るようにする
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="attendUpdate">出欠変更権限</label>
				<div class="col-sm-8 memberF">
					<html:checkbox property="attendUpdate"/>&nbsp;&nbsp;他者の出欠を変更出来るようにする
				</div>
			</div>
			<input type="submit" value="確認" name="clubChiefConfirm" class="btn btn-primary">
		</form>
	</s:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>