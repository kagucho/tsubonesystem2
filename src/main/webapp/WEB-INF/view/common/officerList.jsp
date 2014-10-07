<div class="container">
	<div class="table-responsive">
		<h3 class="CENTER">局長</h3>
		<table class="table">
		<tr>
			<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
			<c:forEach var="e" items="${tLeadersChief}">
				<tr>
					<td>
						${f:h(e.tMember.name) }
					</td>
					<td>
						<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
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
		<h3 class="CENTER">副局長</h3>
		<table class="table">
		<tr>
			<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
			<c:forEach var="e" items="${tLeadersSubChief}">
				<tr>
					<td>
						${f:h(e.tMember.name) }
					</td>
					<td>
						<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
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
					<c:if test="${loginMemberDto.actorKindCode == '1'}">
						<td>
							<a class="btn btn-danger" href="<c:url value="/admin/officerDelete/indexAdmin/${e.id}"/>">削除</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<h3 class="CENTER">会計</h3>
		<table class="table">
		<tr>
			<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
			<c:forEach var="e" items="${tLeadersAccounts}">
				<tr>
					<td>
						${f:h(e.tMember.name) }
					</td>
					<td>
						<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
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
					<c:if test="${loginMemberDto.actorKindCode == '1'}">
						<td>
							<a class="btn btn-danger" href="<c:url value="/admin/officerDelete/${e.id}"/>">削除</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<h3 class="CENTER">各部長</h3>
		<c:if test="${loginMemberDto.actorKindCode == '1'}">
			<a class="btn btn-primary" href="<c:url value="/admin/officerUpdate/clubChiefInput"/>">権限管理</a>
		</c:if>
		<table class="table">
		<tr>
			<th class="col-md-2 col-ms-2 col-xs-2">部名</th><th class="col-md-2 col-ms-2 col-xs-2">部の代表者(H.N)</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th>
			<c:forEach var="e" items="${tClubLeaderList}">
				<tr>
					<td>
						${f:h(e.ClubName) }
					</td>
					<td>
						<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tLeaders.tMember.id}">${f:h(e.tLeaders.tMember.hname) }</a>
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
		<h3 class="CENTER">合宿委員</h3>
		<table class="table">
		<tr>
			<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
			<c:forEach var="e" items="${tLeadersGassyuku}">
				<tr>
					<td>
						${f:h(e.tMember.name) }
					</td>
					<td>
						<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
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
					<c:if test="${loginMemberDto.actorKindCode == '1'}">
						<td>
							<a class="btn btn-danger" href="<c:url value="/admin/officerDelete/${e.id}"/>">削除</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<h3 class="CENTER">理大祭実行委員</h3>
		<table class="table">
		<tr>
			<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
			<c:forEach var="e" items="${tLeadersRidaisai}">
				<tr>
					<td>
						${f:h(e.tMember.name) }
					</td>
					<td>
						<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
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
					<c:if test="${loginMemberDto.actorKindCode == '1'}">
						<td>
							<a class="btn btn-danger" href="<c:url value="/admin/officerDelete/${e.id}"/>">削除</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<h3 class="CENTER">その他</h3>
		<table class="table">
		<tr>
			<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
			<c:forEach var="e" items="${tLeadersEtc}">
				<tr>
					<td>
						${f:h(e.tMember.name) }
					</td>
					<td>
						<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
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
					<c:if test="${loginMemberDto.actorKindCode == '1'}">
						<td>
							<a class="btn btn-danger" href="<c:url value="/admin/officerDelete/${e.id}"/>">削除</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<h3 class="CENTER">WEB管理者</h3>
		<table class="table">
		<tr>
			<th class="col-md-2 col-ms-2 col-xs-2">本名</th><th class="col-md-2 col-ms-2 col-xs-2">ハンドルネーム</th><th class="col-md-5 col-ms-5 col-xs-5">連絡先（mail）</th><th class="col-md-2 col-ms-2 col-xs-2">連絡先（電話番号）</th><c:if test="${loginMemberDto.actorKindCode == '1'}"><th class="col-md-1 col-ms-1 col-xs-1"></th></c:if>
			<c:forEach var="e" items="${tLeadersWebAdmin}">
				<tr>
					<td>
						${f:h(e.tMember.name) }
					</td>
					<td>
						<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.tMember.id}">${f:h(e.tMember.hname) }</a>
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
					<c:if test="${loginMemberDto.actorKindCode == '1'}">
						<td>
							<a class="btn btn-danger" href="<c:url value="/admin/officerDelete/indexAdmin/${e.id}"/>">削除</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
	<c:if test="${loginMemberDto.actorKindCode == '1'}">
		<div class="col-md-4 marginUp">
			<a href="<c:url value="/admin/officerRegist/subChiefIndex"/>"><button type="button" class="btn btn-default btn-lg btn-block">副局長を登録する</button></a>
		</div>
		<div class="col-md-4 marginUp">
			<a href="<c:url value="/admin/officerRegist/accountIndex"/>"><button type="button" class="btn btn-default btn-lg btn-block">会計を登録する</button></a>
		</div>
		<div class="col-md-4 marginUp">
			<a href="<c:url value="/admin/officerRegist/gassyukuIndex"/>"><button type="button" class="btn btn-default btn-lg btn-block">合宿委員を登録する</button></a>
		</div>
		<div class="col-md-4 marginUp">
			<a href="<c:url value="/admin/officerRegist/ridaisaiIndex"/>"><button type="button" class="btn btn-default btn-lg btn-block">理大祭実行委員を登録する</button></a>
		</div>
		<div class="col-md-4 marginUp">
			<a href="<c:url value="/admin/officerRegist/webAdminIndex"/>"><button type="button" class="btn btn-default btn-lg btn-block">WEB管理者を登録する</button></a>
		</div>
		<div class="col-md-4 marginUp">
			<a href="<c:url value="/admin/officerRegist/etcIndex"/>"><button type="button" class="btn btn-default btn-lg btn-block">その他の役員を登録する</button></a>
		</div>
	</c:if>
</div>