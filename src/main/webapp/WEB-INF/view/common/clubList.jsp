<div class="container">
	<div class="col-sm-12">
		<c:if test="${loginMemberDto.actorKindCode == '1'}">
			<a class="btn btn-primary" href="<c:url value="/admin/clubRegist/index"/>">新規登録</a>
		</c:if>
		<div class="table-responsive">
			<table class="table">
			<tr>
				<th class="col-md-1 col-sm-1">部名</th><th class="col-md-2 col-sm-2">部の代表者</th><th>部の概要</th>
				<c:forEach var="e" items="${clubItems}">
					<tr>
						<td>
							<a href="<c:url value="/${loginMemberDto.actorKind}/clubDetail/"/>${e.id}">${f:h(e.ClubName) }</a>
						</td>
						<td>
							${f:h(e.tLeaders.tMember.hname)}
						</td>
						<td>
							${f:h(e.ClubMemo) }
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>