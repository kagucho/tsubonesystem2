<div class="container">
	<h3 class="col-md-6">アンケート詳細</h3>
	<s:form method="POST" >
		<table class="table">
			<c:forEach var="rec" items="${enqueteSelectMap}">
				<tr>
					<th>
						<input type="radio" name="answer" value="${rec.key}">${rec.value}
					</th>
        		</tr>
        	</c:forEach>
		</table>
		<input type="submit" value="確認画面" id="confirm" name="confirm"  class="col-md-3 col-sm-5 col-xs-12 btn btn-primary">
	</s:form>
	<a href="<c:url value="/admin/enqueteDetail"/>/${f:h(id)}">詳細に戻る</a>
</div>