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
<h3>以下のメンバーでよろしいですか？</h3>
<s:form method="POST">
<div class="table-responsive">
<table class="table">
<tr>
<th>名前</th><th>ハンドルネーム</th><th>メールアドレス</th><th>電話番号</th><th>入学年度</th>
		<tr>
			<td>
				${f:h(tMemberNew.name) }
			</td>
			<td>
				${f:h(tMemberNew.hname) }
			</td>
			<td>
				${f:h(tMemberNew.mail) }
			</td>
			<td>
				${f:h(tMemberNew.tel1) }-${f:h(tMemberNew.tel2) }-${f:h(tMemberNew.tel3) }
			</td>
			<td>
				${f:h(tMemberNew.entrance) }
			</td>
		</tr>
</table>
</div>
<input type="submit" value="登録" id="complete" name="complete" property="complete" class="btn btn-primary">
</s:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>