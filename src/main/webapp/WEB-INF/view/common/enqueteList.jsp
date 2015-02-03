<div class="container">
	<div class="col-sm-12">
			<a class="btn btn-primary" href="<c:url value="/admin/enqueteRegist/index"/>">新規登録</a>
		<p>アンケート一覧</p>
		<div class="table-responsive">
			<table class="table">
			<tr>
				<th>アンケート名</th>
				<th>作成者</th>
			   <th></th>
			   <th>回答済み</th>
			</tr>
			  <c:forEach var="rec" items="${list}">
			     <tr>
	               <td>
	               	${rec.title}
	               </td>
	               <td>
	               	${rec.tMember.userName}
	               </td>
	               <td>
	                   	<a class="btn btn-primary col-md-4 col-md-offset-4"  href="<c:url value="/${loginMemberDto.actorKind}/enqueteDetail/"/>${rec.id}">詳細</a>
	               </td>
	               <td>
	               		<c:if test="${rec.answered}"><span class="glyphicon glyphicon glyphicon-ok" aria-hidden="true"></span></c:if>
	               		<c:if test="${!rec.answered}"><span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span></c:if>
	               </td>
			     </tr>
			  </c:forEach>
			</table>
		</div>
	</div>
</div>