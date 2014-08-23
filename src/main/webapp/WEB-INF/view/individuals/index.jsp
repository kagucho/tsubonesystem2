<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%-- <%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%> --%>
<jsp:include page="/WEB-INF/view/common/jumbotronMenu.jsp" >
<jsp:param name="actionName" value="actionName"/>
</jsp:include >
<div class="container">
<div class="col-sm-12">
	<c:if test="${tPartyNoAttendList.size() != 0}">
		<div class="alert alert-danger">
			<h4>以下の会議はまだ出欠を出していません！！</h4>
		</div>
		<div class="table-responsive">
			<table class="table">
				<tr>
					<th>会議名</th><th>会議内容</th><th>開催日</th><th>締切日</th>
				</tr>
				<c:forEach var="e" items="${tPartyNoAttendList}">
					<tr>
						<td>
							<a href="<c:url value="/individuals/partyDetail/"/>${e.id}">${f:h(e.meetingName) }</a>
						</td>
						<td>
							${f:h(e.meetingMemo) }
						</td>
						<td>
							<fmt:formatDate value="${e.meetingDay}" pattern="yyyy/MM/dd" /><br>
						</td>
						<td>
							<fmt:formatDate value="${e.meetingDeadlineDay}" pattern="yyyy/MM/dd" /><br>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	<c:if test="${tPartyToDayList.size() != 0}">
		<div class="alert alert-info">
			<h4>本日開催予定の会議一覧</h4>
		</div>
		<table class="table-responsive">
			<tr>
				<th>会議名</th><th>会議内容</th><th>開催場所</th><th>締切日</th>
			</tr>
			<c:forEach var="e" items="${tPartyToDayList}">
				<tr>
					<td>
						<a href="<c:url value="/individuals/partyDetail/"/>${e.id}">${f:h(e.meetingName) }</a>
					</td>
					<td>
						${f:h(e.meetingMemo) }
					</td>
					<td>
						${f:h(e.meetingRoom) }
					</td>
					<td>
						<fmt:formatDate value="${e.meetingDeadlineDay}" pattern="yyyy/MM/dd" /><br>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
    <div class="row">
	    <div class="col-lg-4">
		    <h2>PartyInfo</h2>
		    <c:if test="${tPartyNoAttendList.size() != 0}">
		    	<p class="text-danger">出欠を出してない会議が存在します！</p>
		    </c:if>
		    <p>会議の出欠や、会議の内容を確認するのはこちらから</p>
		    <p><a class="btn btn-primary" href="<c:url value="/individuals/partyList"/>" role="button">PartyList &raquo;</a></p>
	    </div>
	    <div class="col-lg-4">
		    <h2>MemberInfo</h2>
		    <p>連絡先や、諸情報を更新するにはこちらから。また他のメンバーの連絡先を検索するのもこちら。</p>
		    <p><a class="btn btn-primary" href="<c:url value="/individuals/memberList"/>" role="button">MemberList &raquo;</a></p>
	    </div>
	    <div class="col-lg-4">
		    <h2>OfficerInfo</h2>
		    <p>代表や、部長に連絡を取るにはこちらから。</p>
		    <p><a class="btn btn-primary" href="<c:url value="/individuals/officerList"/>" role="button">OfficerList &raquo;</a></p>
	    </div>
    </div>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>