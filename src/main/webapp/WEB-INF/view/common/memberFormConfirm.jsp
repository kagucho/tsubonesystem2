<table class="table">
	<tr>
		<th><h4>名前</h4></th>
		<td><h5>${f:h(name)}</h5></td>
	</tr>
	<tr>
		<th><h4>性別</h4></th>
		<td>
			<h5>
				${f:h(sexMap[sex])}
			</h5>
		</td>
	</tr>
	<tr>
		<th><h4>ハンドネーム</h4></th>
		<td><h5>${f:h(hname)}</h5></td>
	</tr>
	<tr>
		<th><h4>メールアドレス</h4></th>
		<td><h5>${f:h(mail)}</h5></td>
	</tr>
	<tr>
		<th><h4>所属部</h4></th>
		<td>
			<h5>
				<c:forEach var="e" items="${tMemberClubList}">
					${f:h(e.tClub.ClubName)}
				</c:forEach>
			</h5>
		</td>
	</tr>
	<tr>
		<th><h4>学科</h4></th>
		<td><h5>${f:h(curriculum)}</h5></td>
	</tr>
	<tr>
		<th><h4>入学年度</h4></th>
		<td><h5>${f:h(entrance)}</h5></td>
	</tr>
	<tr>
		<th><h4>電話番号</h4></th>
		<td><h5>${f:h(tel1)}-${f:h(tel2)}-${f:h(tel3)}</h5></td>
	</tr>
	<tr>
		<th><h4>OB宣言</h4></th>
	　	<td>
			<c:if test="${obFlag}">
				<h5>OB宣言済み</h5>
			</c:if>
			<c:if test="${!obFlag}">
				<h5>(現役部員)</h5>
			</c:if>
		</td>
	</tr>
	<c:if test="${obFlag}">
		<tr>
			<th><h4>メール受信可否</h4></th>
		　	<td>
				<c:if test="${sendStopFlag}">
					<h5>メールを受信しない</h5>
				</c:if>
				<c:if test="${!sendStopFlag}">
					<h5>メールを受信する</h5>
				</c:if>
			</td>
		</tr>
	</c:if>
	<tr>
		<th><h4>ログインID</h4></th>
		<td><h5>${f:h(userName)}</h5></td>
	</tr>
	<tr>
		<th><h4>Password</h4></th>
		<td><h5>${f:h(password)}</h5></td>
	</tr>
</table>