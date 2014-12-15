<div class="container">
	<div class="col-sm-12">
		<c:if test="${loginMemberDto.actorKindCode == '1'}">
			<a class="btn btn-primary" href="<c:url value="/admin/enqueteRegist/index"/>">新規登録</a>
		</c:if>
		アンケート一覧
		<div class="table-responsive">
			<table class="table">
			<tr>
			　　<th>氏名</th>
			   <th>タイトル</th>
			   <th></th>
			   <th>回答済み</th>
			</tr>
			  <c:forEach var="rec" items="${list}">
			     <tr>
	               <td>
	               	${rec.tMember.userName}
	               </td>
	               <td>
	               	${rec.title}
	               </td>
	               <td>
	                   <a class="btn btn-primary col-md-4 col-md-offset-4"  href="<c:url value="/admin/enqueteDetail/"/>${rec.id}">回答</a>
	               </td>
	               <td>
	               		<c:if test="${rec.answered}">○</c:if>
	               		<c:if test="${!rec.answered}">☓</c:if>
	               </td>
			     </tr>
			  </c:forEach>
			</table>
		</div>
	</div>
</div>