<div class="container">
	<div class="col-sm-12">
		<s:form method="POST" >
			<div class="table-responsive">
			<h4>変更したいメンバーは以下でよろしいですか？</h4>
			<table class="table">
				<tr>
					<th>ハンドルネーム</th>
				</tr>
				<c:forEach var="e" items="${tMemberNew}">
					<tr>
						<td>
							<a href="<c:url value="/${loginMemberDto.actorKind}/memberDetail/detail"/>/${e.id}">${f:h(e.hname) }</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		<input type="submit" value="登録" id="complete" name="complete" property="complete" class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
		</s:form>
	</div>
</div>