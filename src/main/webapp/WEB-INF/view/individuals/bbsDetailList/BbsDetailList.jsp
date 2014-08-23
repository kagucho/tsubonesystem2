<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<a class="btn btn-primary" href="<c:url value="/indeividuals/bbsList"/>">一覧に戻る</a>
	<h2 class="col-md-12 col-xs-12">${f:h(tBbsSubject.title) }</h2>
	<div class="row CENTER">
		
	</div>
	<div class="table-responsive">
		<table class="table table-bordered">
			<tr class="info">
			<th>書き込み</th><th class="col-md-2 col-sm-2">制作者</th>
			<c:forEach var="e" items="${tBbsDetailList}">
				<tr>
					<td>
						${f:h(e.detail) }
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
				<input type="text" id="detail" name="detail" property="detail" class="form-control" placeholder="内容" value="${detail}">
				<html:errors property="detail"/>
			</div>
			<input type="submit" value="書き込む" id="bbsDetailRegist" name="bbsDetailRegist" property="bbsDetailRegist" class="marginUP col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12  btn btn-primary">
		</form>
</s:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>