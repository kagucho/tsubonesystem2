<div class="container">
	<h3>以下の内容でよろしいですか？</h3>
	<div class="col-sm-12">
		<table class="table">
			<tr>
				<th><h4>会議の名前</h4></th>
				<td><h5>${f:h(meetingName)}</h5></td>
			</tr>
			<tr>
				<th><h4>出欠席</h4></th>
				<td><h5>${f:h(attendMessege)}</h5></td>
			</tr>
		</table>
		<s:form method="POST" >
			<input type="submit" value="登録" id="complete" name="complete" property="complete" class="col-md-4 col-md-offset-4 col-sm-4　col-sm-offset-4 col-xs-12 btn btn-default btn-lg">
		</s:form>
	</div>
</div>