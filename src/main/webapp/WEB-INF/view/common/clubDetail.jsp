<div class="container">
<h3 class="col-md-6">${f:h(ClubName)}たんの詳細情報</h3>
<c:if test="${loginMemberDto.actorKindCode == '1' }">
	<a class="col-md-2 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-danger btnYOKO btnMRC" href="<c:url value="/admin/clubDelete/${id}"/>">削除</a>
</c:if>
<c:if test="${loginMemberDto.actorKindCode == '1'}">
	<a class="col-md-2 col-sm-5 col-xs-12 btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/clubUpdate/${id}"/>">更新</a>
</c:if>
<c:if test="${loginMemberDto.actorKindCode == '2' && updateFlag}">
	<a class="col-md-2 col-sm-5 col-sm-offset-3 col-xs-12 btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/clubUpdate/${id}"/>">更新</a>
</c:if>
<table class="table">
	<tr>
		<th class="col-md-2 col-sm-2"><h4>部の名前</h4></th>
		<td><h5>${f:h(ClubName)}</h5></td>
	</tr>
	<tr>
		<th class="col-md-2 col-sm-2"><h4>部の代表者</h4></th>
		<td><a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail/${tMember.id}"/>"><h5>${f:h(tMember.hname)}(本名:${f:h(tMember.name)})</h5></a></td>
	</tr>
	<tr>
		<th class="col-md-2 col-sm-2"><h4>部の概要</h4></th>
		<td class="col-md-10 col-sm-10">${f:h(ClubMemo)}</td>
	</tr>
</table>
<div class="col-md-12 col-sm-12 col-sx-12">
<h3 class="col-md-5 col-sm-12 col-sx-12">${f:h(ClubName)}に所属しているメンバー</h3>
<s:form method="POST" >
		<input type="submit" value="Mail" id="contentRegist" name="contentRegist" property="contentRegist" class="col-md-4 col-sm-5 col-md-offset-2 col-sm-offset-4 col-xs-12  btn btn-success btnYOKO btnMRC">
</s:form>
<table class="table table-striped">
	<tr>
		<th><h4>ハンドルネーム</h4></th><th><h4>本名</h4></th>
	</tr>
	<c:forEach var="e" items="${tMemberList}">
		<tr>
			<td>${f:h(e.hname)}</td><td>${f:h(e.name)}</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>