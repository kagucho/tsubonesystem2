<div id="searchWindow" class="col-sm-4">
	<h4>メンバー検索</h4>
	<s:form method="POST">
	<form class="form-horizontal">
		<div class="form-group">
			<label class="control-label col-sm-3" for="name">名前</label>
			<div class="col-sm-9 memberF" >
				<input type="text" class="form-control" id="name" name="name" property="name" placeholder="Name"  value="${name}">
<%-- 			<html:errors property="name"/> --%>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-3" for="hname">H.ネーム</label>
			<div class="col-sm-9 memberF" >
				<input type="text" class="form-control" id="hname" name="hname" property="hname" placeholder=H.Name  value="${hname}">
<%-- 			<html:errors property="name"/> --%>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-3" for="entrance">入学年度</label>
			<div class="col-sm-9 memberF" >
				<input type="text" class="form-control" id="entrance" name="entrance" property="entrance" placeholder="Entrance"  value="${entrance}">
<%-- 			<html:errors property="name"/> --%>
			</div>
		</div>
		<div class="form-group">
		<label class="control-label col-sm-7" for="obFlag">OB宣言している人も含める</label>
		<div class="col-sm-5 memberF">
			<input type="checkbox" id="obFlag" name="obFlag" value="true" />
		</div>
	</div>
		<div class="form-group col-sm-offset-10">
			<input type="submit" value="検索" id="onSearch" name="onSearch" property="onSearch" class="btn btn-primary">
		</div>
	</form>
	</s:form>
</div>