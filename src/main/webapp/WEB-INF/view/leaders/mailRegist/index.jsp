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
    <link href="${f:url('/css/docs.min.css')}" type="text/css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="${f:url('/js/change.js')}"></script>
    <script type="text/javascript">
    $(function($) {
    	var val = $("input[name='activeOrOb']:checked").val();
		if(val == '2') {
			$(".clubListCheck").hide("");
		} else {
			$(".clubListCheck").show();
		}
		var val = $("input[name='allOrClub']:checked").val();
		if(val == '1') {
			$("#selectClubDiv").hide();
		} else {
			$("#selectClubDiv").show();
		}
		
    	$(".activeOrOb").click(function() {
    		var val = $("input[name='activeOrOb']:checked").val();
    		if(val == '2') {
    			$(".clubListCheck").hide("normal");
    		} else {
    			$(".clubListCheck").show("normal");
    		}
    	});
    	$(".allOrClub").click(function() {
    		var val = $("input[name='allOrClub']:checked").val();
    		if(val == '1') {
    			$("#selectClubDiv").hide("normal");
    		} else {
    			$("#selectClubDiv").show("normal");
    		}
    	});
    });
    </script>
</head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<h3 class="col-md-4 col-sm-5 col-xs-12">メール内容作成</h3>
	<a class="col-md-2 col-md-offset-6 col-sm-2 col-sm-offset-5 col-xs-12  btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/mailList"/>" role="button">Mail History</a>
	<s:form method="POST">
		<div id="mailInput">
			<h4>メールを配信する場合は、送信する相手と内容を入力してください。</h4>
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
			<label class="control-label col-sm-4" for="Title">メールのタイトル&nbsp;<span class="hissu">＊</span></label>
			<div class="col-sm-8 memberF" >
				<input type="text" id="title" name="title" property="title" class="form-control" placeholder="Title" value="${title}" >
				<html:errors property="title"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="Content">メールの内容&nbsp;<span class="hissu">＊</span></label>
			<div class="col-sm-8 memberF">
				<textarea class="form-control" name="content" rows="10" property="content" placeholder="Content" >${f:h(content)}</textarea>
				<html:errors property="content"/>
			</div>
		</div>
		<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3 col-xs-12  btn btn-primary">
	</s:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>