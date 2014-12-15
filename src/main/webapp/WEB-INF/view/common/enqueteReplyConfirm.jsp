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
		<input type="submit" value="回答" id="complete" name="complete"  class="col-md-3 col-sm-5 col-xs-12 btn btn-primary">
	</s:form>
</div>