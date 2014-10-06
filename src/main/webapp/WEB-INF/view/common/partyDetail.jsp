<div class="container">
	<div class="row CENTER">
		<h3 class="col-md-4 col-xs-12">${f:h(meetingName)}たんの詳細情報</h3>
		<c:if test="${!deadFlag}">
			<c:if test="${loginMemberDto.actorKindCode == '1'}">
				<a class="col-md-2 col-sm-3 col-sm-offset-1 col-xs-12  btn btn-danger btnYOKO btnMRC" href="<c:url value="/${loginMemberDto.actorKind}/partyDelete/${id}"/>" role="button">削除</a>
				<a class="col-md-2 col-sm-3 col-xs-12 btn btn-primary btnYOKO" href="<c:url value="/${loginMemberDto.actorKind}/partyUpdate/${id}"/>" role="button">更新</a>
				<a class="col-md-2 col-sm-3 col-xs-12 btn btn-info" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendList/${id}"/>" role="button">出欠状況</a>
			</c:if>
			<c:if test="${loginMemberDto.actorKindCode == '2' && !meetingNecessaryFlag}">
				<a class="col-md-2 col-sm-3 col-sm-offset-1 col-xs-12  btn btn-danger btnYOKO btnMRC" href="<c:url value="/${loginMemberDto.actorKind}/partyDelete/${id}"/>" role="button">削除</a>
				<a class="col-md-2 col-sm-3 col-xs-12 btn btn-primary btnYOKO" href="<c:url value="/${loginMemberDto.actorKind}/partyUpdate/${id}"/>" role="button">更新</a>
				<a class="col-md-2 col-sm-3 col-xs-12 btn btn-info" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendList/${id}"/>" role="button">出欠状況</a>
			</c:if>
			<c:if test="${loginMemberDto.actorKindCode == '3' && myPartyFlag}">
				<a class="col-md-2 col-sm-3 col-sm-offset-1 col-xs-12  btn btn-danger btnYOKO btnMRC" href="<c:url value="/${loginMemberDto.actorKind}/partyDelete/${id}"/>" role="button">削除</a>
				<a class="col-md-2 col-sm-3 col-xs-12 btn btn-primary btnYOKO" href="<c:url value="/${loginMemberDto.actorKind}/partyUpdate/${id}"/>" role="button">更新</a>
				<a class="col-md-2 col-sm-3 col-xs-12 btn btn-info" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendList/${id}"/>" role="button">出欠状況</a>
			</c:if>
			<c:if test="${loginMemberDto.actorKindCode == '3' && !myPartyFlag}">
				<a class="col-md-2 col-md-offset-6 col-sm-12 col-xs-12 btn btn-info" href="<c:url value="/${loginMemberDto.actorKind}/partyAttendList/${id}"/>" role="button">出欠状況</a>
			</c:if>
		</c:if>
		<c:if test="${deadFlag}">
			<c:if test="${loginMemberDto.actorKindCode == '1'}">
				<a class="col-md-2 col-sm-5 col-sm-offset-4 col-xs-12  btn btn-primary btnMRC" href="<c:url value="/${loginMemberDto.actorKind}/partyResult/${id}"/>" role="button">会議の結果を入力する</a>
			</c:if>
			<c:if test="${resultPower && loginMemberDto.actorKindCode == '2'}">
				<a class="col-md-2 col-sm-5 col-sm-offset-4 col-xs-12  btn btn-primary btnMRC" href="<c:url value="/${loginMemberDto.actorKind}/partyResult/${id}"/>" role="button">会議の結果を入力する</a>
			</c:if>
			<c:if test="${loginMemberDto.actorKindCode == '3' && myPartyFlag}">
				<a class="col-md-2 col-sm-5 col-sm-offset-4 col-xs-12  btn btn-primary btnMRC" href="<c:url value="/${loginMemberDto.actorKind}/partyResult/${id}"/>" role="button">会議の結果を入力する</a>
			</c:if>
		</c:if>
	</div>
	<div class="col-md-12">
		<%@ include file="/WEB-INF/view/common/partyFormConfirm.jsp"%><%-- 詳細jsp --%>
		<c:if test="${!deadFlag}">
			<div class="row CENTER">
				<a class="col-md-3 col-md-offset-3 col-sm-5 col-sm-offset-1 col-xs-12  btn btn-primary btnYOKO btnMRC " href="<c:url value="/${loginMemberDto.actorKind}/attend/yes"/>">出席する</a>
				<a class="col-md-3 col-sm-5 col-xs-12 btn btn-primary" href="<c:url value="/${loginMemberDto.actorKind}/attend/no"/>">欠席する</a>
			</div>
			<s:form method="POST" >
				<form name="party" class="form-horizontal">
					<%@ include file="/WEB-INF/view/common/partyQuestionFormInput.jsp"%>
					<div class="form-group">
						<div class="col-sm-8">
							<input type="submit" value="質問する" id="questionConfirm" name="questionConfirm" property="questionConfirm" class="col-md-6 col-md-offset-6 col-sm-10 col-sm-offset-4 col-xs-12  btn btn-primary">
						</div>
					</div>
				</form>
			</s:form>
	</c:if>
	<c:if test="${deadFlag}">
		<div class="alert alert-danger">この会議は締め切り時間を過ぎています</div>
	</c:if>
	<%@ include file="/WEB-INF/view/common/questionList.jsp"%>
	</div>
</div>