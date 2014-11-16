<form class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-4" for="announceTitle">お知らせタイトル&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF" >
			<input type="text" id="announceTitle" name="announceTitle" class="form-control" placeholder="announceTitle" value="${announceTitle}">
			<html:errors property="announceTitle"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="announceContent">おしらせの内容&nbsp;<span class="hissu">＊</span></label>
		<div class="col-sm-8 memberF">
			<textarea class="form-control" name="announceContent" rows="5" placeholder="announceContent">${f:h(announceContent)}</textarea>
			<html:errors property="announceContent"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="announceContent">添付画像</label>
		<div class="col-sm-8 memberF">
			<input type="file" id="file" name="file" class="file" accept="image/*">
			<img src="" alt="" width="480" height="360" style="display:block;width:60%;height:auto;"/>
			<html:errors property="file"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="announceFromDay">お知らせ掲載日(開始)(yyyy/dd/mm:例2014/07/05)</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="announceFromDay" name="announceFromDay" class="form-control" placeholder="announceFromDay" value="${announceFromDay}">
			<html:errors property="announceFromDay"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="announceToDay">お知らせ掲載日(終了)(yyyy/dd/mm:例2014/07/05)</label>
		<div class="col-sm-8 memberF">
			<input type="text" id="announceToDay" name="announceToDay" class="form-control" placeholder="announceToDay" value="${announceToDay}">
			<html:errors property="announceToDay"/>
		</div>
	</div>
	<div class="form-group">
		<input type="submit" value="確認" id="confirm" name="confirm" class="col-md-4 col-md-offset-4 col-sm-5 col-sm-offset-3 col-xs-12  btn btn-primary">
	</div>
</form>