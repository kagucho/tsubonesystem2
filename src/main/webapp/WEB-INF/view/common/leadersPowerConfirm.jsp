<table class="table">
	<tr>
		<th>秘匿情報閲覧権限</th><th>メンバー情報更新権限</th><th>出欠変更権限</th><th>“部”情報更新権限</th>
	</tr>
	<tr>
		<td>
				<c:if test="${secretInformation}"><span class="glyphicon glyphicon-ok"></span></c:if><c:if test="${!secretInformation}"><span class="glyphicon glyphicon-remove"></span></c:if>
		</td>
		<td>
			<c:if test="${memberUpdate}"><span class="glyphicon glyphicon-ok"></span></c:if><c:if test="${!memberUpdate}"><span class="glyphicon glyphicon-remove"></span></c:if>
		</td>
		<td>
			<c:if test="${attendUpdate}"><span class="glyphicon glyphicon-ok"></span></c:if><c:if test="${!attendUpdate}"><span class="glyphicon glyphicon-remove"></span></c:if>
		</td>
	</tr>
</table>