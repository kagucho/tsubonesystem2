<s:form method="POST">
	<c:if test="${loginMemberDto.actorKindCode == '1'}">
		<a class="btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/memberRegist/index"/>">新規登録</a>
		<a class="btn btn-primary" href="<c:url value="/admin/memberUpload/index"/>">CSV一括登録</a>
	</c:if>
	<div class="table-responsive">
		<table class="table table-bordered">
			<tr class="info">
			<th></th><th>名前</th><th>ハンドルネーム</th><th>入学年度</th>
					<tr>
						<td align="center">
							<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${loginMember.id}"><span class="glyphicon glyphicon-user"></span></a>
						</td>
						<td>
							${f:h(loginMember.name) }
						</td>
						<td>
							<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${loginMember.id}">${f:h(loginMember.hname) }</a>
						</td>
						<td>
							${f:h(loginMember.entrance) }
						</td>
					</tr>
		</table>
	</div>
	<%@ include file="/WEB-INF/view/common/pager.jsp"%>
	<div class="table-responsive">
		<table class="table">
			<tr class="info">
				<th></th><th>名前</th><th>ハンドルネーム</th><th>入学年度</th>
				<c:forEach var="e" items="${memberItems}">
					<tr>
						<td>
							<span class="glyphicon glyphicon-user" style="visibility:hidden"></span>
						<td>
							${f:h(e.name) }
						</td>
						<td>
							<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.id}">${f:h(e.hname) }</a>
						</td>
						<td>
							${f:h(e.entrance) }
						</td>
					</tr>
				</c:forEach>
		</table>
	</div>
	<%@ include file="/WEB-INF/view/common/pager.jsp"%>
</s:form>