<table class="table">
	<tr>
		<th class="col-md-3 col-xs-4"><h4>お知らせのタイトル</h4></th>
		<td><h5>${f:h(announceTitle)}</h5></td>
	</tr>
	<tr>
		<th class="col-md-3 col-xs-4"><h4>お知らせの内容</h4></th>
		<td><h5>${f:br(f:h(announceContent))}</h5></td>
	</tr>
	<tr>
		<th class="col-md-3 col-xs-4"><h4>お知らせ掲載日(開始)</h4></th>
		<td><h5>${f:h(announceFromDay)}</h5></td>
	</tr>
	<tr>
		<th class="col-md-3 col-xs-4"><h4>お知らせ掲載日(終了)</h4></th>
		<td><h5>${f:h(announceToDay)}</h5></td>
	</tr>
</table>