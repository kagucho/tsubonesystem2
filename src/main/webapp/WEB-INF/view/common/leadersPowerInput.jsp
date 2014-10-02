<form class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-4" for="secretInformation">秘匿情報の表示</label>
		<div class="col-sm-8 memberF">
			<html:checkbox property="secretInformation"/>&nbsp;&nbsp;メンバーの電話番号が閲覧出来るようにする
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="memberUpdate">メンバー情報の更新権限</label>
		<div class="col-sm-8 memberF">
			<html:checkbox property="memberUpdate"/>&nbsp;&nbsp;メンバー情報を更新出来るようにする
		</div>	
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="attendUpdate">出欠変更権限</label>
		<div class="col-sm-8 memberF">
			<html:checkbox property="attendUpdate"/>&nbsp;&nbsp;他者の出欠を変更出来るようにする
		</div>
	</div>
</form>
