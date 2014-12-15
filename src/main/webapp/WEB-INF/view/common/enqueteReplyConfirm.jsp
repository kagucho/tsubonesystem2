<div class="container">
	<h3 class="col-md-6">確認画面</h3>
	<s:form method="POST" >
		<table class="table">
			<tr>
				<th>
					選択した回答：${f:h(enqueteSelectMap[answer])}
				</th>
       		</tr>
		</table>
		<div class="form-group">
			<input type="submit" value="回答" id="complete" name="complete"  class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
		</div>
	</s:form>
</div>