<div class="container">
	<div class="col-sm-12">
		<c:if test="${loginMemberDto.actorKindCode == '1'}">
			<a class="btn btn-primary" href="<c:url value="/admin/enqueteRegist/index"/>">新規登録</a>
		</c:if>
		<th class="col-md-1 col-sm-1">アンケート一覧</th>
		<div class="table-responsive">
			<table class="table">
			<tr>
			　　<th>氏名</th>
			   <th>タイトル</th>
			   <th></th>
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
	                   <a href="<c:url value="/admin/enqueteDetail/"/>/${rec.id}">詳細</a>
	               </td>
			     </tr>
			  </c:forEach>
			  </table>
		</div>
	</div>
</div>