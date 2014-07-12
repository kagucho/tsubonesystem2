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
<%@ include file="/WEB-INF/view/common/indeividualsHeader.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<h2 class="col-md-12 col-xs-12">掲示板</h2>
</div>
<div class="container">
	<div class="table-responsive">
		<table class="table table-bordered">
			<tr class="info">
			<th>スレ名</th><th class="col-md-2 col-sm-2">制作者</th>
			<c:forEach var="e" items="${tBbsSubjectList}">
				<tr>
					<td>
						<a href="<c:url value="/individuals/bbsDetailList"/>/${e.id}">${f:h(e.title) }</a>
					</td>
					<td>
						${f:h(e.tMember.hname) }
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>	
	<s:form method="POST" >
		<form name="party" class="form-horizontal">
			<div class="col-md-12">
				<input type="text" id="title" name="title" property="title" class="form-control" placeholder="スレ名" value="${title}">
				<html:errors property="title"/>
			</div>
			<input type="submit" value="スレを作る" id="bbsRegist" name="bbsRegist" property="bbsRegist" class="marginUP col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12  btn btn-primary">
		</form>
	</s:form>
</div>
<div class="container">
	<div class="bs-callout bs-callout-danger">
			<h4>掲示板のおやくそく</h4>
			<ul>
				<li>一つ、誹謗中傷禁止。</li>
				<li>一つ、法に抵触することを促すような書き込み禁止。(未成年飲酒・喫煙とかドラッグとかそういうのの勧誘・計画)</li>
				<li>一つ、大事な連絡は直接本人に。</li>
				<li>一つ、個人情報にはきをつける。</li>
				<li>一つ、あらし行為は今年度全単位没収or一号館から自由落下。(重複スレの乱造もあらしとみなす)</li>
				<li>一つ、”奇跡も、魔法もあるんだよ”の精神で。</li>
				<li>一つ、リア充禁止。</li>
				<li>あ、ホモも禁止(重要！！)</li>
			</ul>
			<p>以上を守ってみんななかよくプレイしましょう。</p>
			</ br>
			<p>※削除機能がまだないので、早急に削除したい場合はweb管理者まで</p>
		</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>