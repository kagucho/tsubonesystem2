<div class="container">
<div class="col-sm-12">
<div class="table-responsive">
<h3>局長</h3>
<table class="table">
<tr>
	<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
	<c:forEach var="e" items="${tLeadersChief}">
		<tr>
			<td>
				${f:h(e.tMember.name) }
			</td>
			<td>
				${f:h(e.tMember.hname) }
			</td>
			<td>
				<a href="mailto:${e.tMember.mail}">${f:h(e.tMember.mail)}</a>
			</td>
			<td>
				${f:h(e.tMember.tel1) }-${f:h(e.tMember.tel2) }-${f:h(e.tMember.tel3) }
			</td>
			<c:if test="${loginMemberDto.actorKindCode == '1'}">
				<td>
					<a class="btn btn-primary" href="<c:url value="/admin/officerUpdate/indexAdmin/${e.id}"/>">編集</a>
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
<h3>副局長</h3>
<table class="table">
<tr>
	<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
	<c:forEach var="e" items="${tLeadersSubChief}">
		<tr>
			<td>
				${f:h(e.tMember.name) }
			</td>
			<td>
				${f:h(e.tMember.hname) }
			</td>
			<td>
				<a href="mailto:${e.tMember.mail}">${f:h(e.tMember.mail)}</a>
			</td>
			<td>
				${f:h(e.tMember.tel1) }-${f:h(e.tMember.tel2) }-${f:h(e.tMember.tel3) }
			</td>
			<c:if test="${loginMemberDto.actorKindCode == '1'}">
				<td>
					<a class="btn btn-primary" href="<c:url value="/admin/officerUpdate/indexAdmin/${e.id}"/>">編集</a>
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
<h3>会計</h3>
<table class="table">
<tr>
	<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
	<c:forEach var="e" items="${tLeadersAccount}">
		<tr>
			<td>
				${f:h(e.tMember.name) }
			</td>
			<td>
				${f:h(e.tMember.hname) }
			</td>
			<td>
				<a href="mailto:${e.tMember.mail}">${f:h(e.tMember.mail)}</a>
			</td>
			<td>
				${f:h(e.tMember.tel1) }-${f:h(e.tMember.tel2) }-${f:h(e.tMember.tel3) }
			</td>
			<c:if test="${loginMemberDto.actorKindCode == '1'}">
				<td>
					<a class="btn btn-primary" href="<c:url value="/admin/officerUpdate/${e.id}"/>">編集</a>
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
<h3>各部長</h3>
<c:if test="${loginMemberDto.actorKindCode == '1'}">
	<a class="btn btn-primary" href="<c:url value="/admin/officerUpdate/clubChiefInput"/>">権限管理</a>
</c:if>
<table class="table">
<tr>
	<th class="col-md-2 col-ms-2 col-xs-2">部名</th><th class="col-md-2 col-ms-2 col-xs-2">部の代表者(H.N)</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th>
	<c:forEach var="e" items="${officerListItem}">
		<tr>
			<td>
				${f:h(e.ClubName) }
			</td>
			<td>
				${f:h(e.tLeaders.tMember.hname) }
			</td>
			<td>
				<a href="mailto:${e.tLeaders.tMember.mail}">${f:h(e.tLeaders.tMember.mail)}</a>
			</td>
			<td>
				${f:h(e.tLeaders.tMember.tel1) }-${f:h(e.tLeaders.tMember.tel2) }-${f:h(e.tLeaders.tMember.tel3) }
			</td>
		</tr>
	</c:forEach>
</table>
<h3>合宿委員</h3>
<table class="table">
<tr>
	<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
	<c:forEach var="e" items="${tLeadersGassyuku}">
		<tr>
			<td>
				${f:h(e.tMember.name) }
			</td>
			<td>
				${f:h(e.tMember.hname) }
			</td>
			<td>
				<a href="mailto:${e.tMember.mail}">${f:h(e.tMember.mail)}</a>
			</td>
			<td>
				${f:h(e.tMember.tel1) }-${f:h(e.tMember.tel2) }-${f:h(e.tMember.tel3) }
			</td>
			<c:if test="${loginMemberDto.actorKindCode == '1'}">
				<td>
					<a class="btn btn-primary" href="<c:url value="/admin/officerUpdate/${e.id}"/>">編集</a>
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
<h3>WEB管理者</h3>
<table class="table">
<tr>
	<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
	<c:forEach var="e" items="${tLeadersWebAdmin}">
		<tr>
			<td>
				${f:h(e.tMember.name) }
			</td>
			<td>
				${f:h(e.tMember.hname) }
			</td>
			<td>
				<a href="mailto:${e.tMember.mail}">${f:h(e.tMember.mail)}</a>
			</td>
			<td>
				${f:h(e.tMember.tel1) }-${f:h(e.tMember.tel2) }-${f:h(e.tMember.tel3) }
			</td>
			<c:if test="${loginMemberDto.actorKindCode == '1'}">
				<td>
					<a class="btn btn-primary" href="<c:url value="/admin/officerUpdate/${e.id}"/>">編集</a>
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>