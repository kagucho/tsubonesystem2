<div class="container">
	<h3 class="col-md-6">アンケート回答</h3>
	<s:form method="POST" >
		<table class="table">
			<c:forEach var="rec" items="${enqueteSelectMap}">
				<tr>
					<th>
						<input type="radio" name="answer" value="${rec.key}" <c:if test="${rec.key == answer}">checked</c:if> >${rec.value}
						<html:errors property="answer"/>
					</th>
        		</tr>
        	</c:forEach>
		</table>
		<div class="form-group">
			<input type="submit" value="確認画面" id="confirm" name="confirm"  class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
		</div>
	</s:form>
</div>