<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
<h3>新規”部”情報を入力してください。</h3>
<h5 class="hissu">＊がついている項目は必須です</h5>
<div class="col-sm-12">
<s:form method="POST" >
<form class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-4" for="ClubName">部の名前&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF" >
			<input type="text" id="ClubName" name="ClubName" property="ClubName" class="form-control" placeholder="ClubName" value="${ClubName}">
			<html:errors property="ClubName"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="OfficerId">部長&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<html:select property="OfficerId" value="OfficerId">
				<c:forEach var="e" items="${memberMap}">
					<html:option value="${e.key}">${f:h(e.value)}</html:option>
				</c:forEach>
			</html:select>
			<html:errors property="OfficerId"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="ClubMemo">部の概要&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<textarea class="form-control" name="ClubMemo" rows="10" property="ClubMemo" placeholder="ClubMemo" value="${ClubName}">${ClubName}</textarea>
			<html:errors property="ClubMemo"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="clubUrl">部の公式ホームページ</label>
		<div class="col-sm-8 memberF" >
			<input type="text" id="clubUrl" name="clubUrl" class="form-control" placeholder="ClubUrl" value="${ClubName}">
			<html:errors property="clubUrl"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-8">
			<input type="submit" value="確認" id="confirm" name="confirm" property="confirm" class="col-md-6 col-md-offset-6 col-sm-10 col-sm-offset-4 col-xs-12  btn btn-primary">
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