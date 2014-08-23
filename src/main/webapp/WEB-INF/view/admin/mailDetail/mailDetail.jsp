<html>
<html lang="jp">
  <%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<div class="col-sm-12">
<h4 class="col-md-4">メールの内容</h4>
<table class="table">
	<tr>
		<th><h4>メールのタイトル</h4></th>
		<td><h5>${f:h(title)}</h5></td>
	</tr>
	<tr>
		<th><h4>メールの内容</h4></th>
		<td><h5><pre><bean:write name="mailForm" property="content" filter="false"/></pre> </h5></td>
	</tr>
</table>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>