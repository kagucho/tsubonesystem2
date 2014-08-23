<div class="container">
	<div class="col-sm-12">
		<s:form method="POST" >
			<div class="table-responsive">
				<h4>変更したいメンバーを選択し、変更したいボタンを押してください</h4>
				<table class="table">
					<tr>
						<th></th><th>ハンドルネーム</th>
					</tr>
					<c:forEach var="e" items="${tMemberOn}">
						<tr>
							<td>
				 				<html:checkbox property="offCheck" value="${e.id}" /> 
							</td>
							<td>
								<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.id}">${f:h(e.hname) }</a>
							</td>
						</tr>
					</c:forEach>
					<c:forEach var="b" items="${tMemberOff}">
						<tr>
							<td>
				 				<html:checkbox property="offCheck" value="${b.id}" />
							</td>
							<td>
								<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${b.id}">${f:h(b.hname) }</a>
							</td>
						</tr>
					</c:forEach>
					<c:forEach var="d" items="${tMemberKuzu}">
						<tr>
							<td>
				 				<html:checkbox property="offCheck" value="${d.id}" />
							</td>
							<td>
								<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${d.id}">${f:h(d.hname) }</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<input type="submit" value="${f:h(btn1) }" id="confirm" name="confirm" property="confirm" formaction="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/confirm/${btn1Key }"/>" class="col-md-3 col-md-offset-3 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-primary btnYOKO btnMRC ">
			<input type="submit" value="${f:h(btn2) }" id="confirm" name="confirm" property="confirm" formaction="<c:url value="/${loginMemberDto.actorKind}/partyAttendUpdate/confirm/${btn2Key }"/>" class="col-md-3 col-sm-5 col-xs-12 btn btn-primary">
		</s:form>
	</div>
</div>