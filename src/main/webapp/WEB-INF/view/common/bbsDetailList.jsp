<div class="container">
	<a class="btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/bbsList/index"/>">一覧に戻る</a>
	<h2 class="col-md-12 col-xs-12">${f:h(tBbsSubject.title) }</h2>
	<div class="row CENTER">
		
	</div>
	<div class="table-responsive">
		<table class="table table-bordered" >
			<tr class="info">
			<th>書き込み</th><th class="col-md-2 col-sm-2">制作者</th><th class="col-md-2 col-sm-2">日時</th>
			<c:forEach var="e" items="${tBbsDetailList}">
				<tr>
					<td>
						${f:br(f:h(e.detail))}
					</td>
					<td>
						${f:h(e.tMember.hname)}
					</td>
					<td>
						${f:h(e.updateTime) }
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>	
	<s:form method="POST" >
		<form name="party" class="form-horizontal">
			<div class="col-md-12">
				<textarea rows="5" cols="160" id="detail" name="detail" property="detail" placeholder="内容">${detail}</textarea>
				<html:errors property="detail"/>
			</div>
			<input type="submit" value="書き込む" id="bbsDetailRegist" name="bbsDetailRegist" property="bbsDetailRegist" class="marginUP col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12  btn btn-primary">
		</form>
</s:form>
</div>