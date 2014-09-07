<c:if test="${loginMemberDto.actorKindCode != '4'}">
	<body data-spy="scroll" data-target="#sidebar">
		<div id="wrap">
			<div id="header" class="navbar-fixed-top">
			<div class="navbar navbar-default ">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-top">
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="<c:url value="/${loginMemberDto.actorKind}/"/>">TsuboneSystem</a>
					</div>
					<div class="collapse navbar-collapse navbar-top">
						<ul class="nav navbar-nav">
							<li><a href="<c:url value="/${loginMemberDto.actorKind}/memberList"/>">Member</a></li>
						</ul>
						<ul class="nav navbar-nav">
							<li><a href="<c:url value="/${loginMemberDto.actorKind}/partyList"/>">Party</a></li>
						</ul>
						<ul class="nav navbar-nav">
							<li><a href="<c:url value="/${loginMemberDto.actorKind}/clubList"/>">Club</a></li>
						</ul>
						<ul class="nav navbar-nav">
							<li><a href="<c:url value="/${loginMemberDto.actorKind}/mailRegist"/>">Mail</a></li>
						</ul>
						<ul class="nav navbar-nav">
							<li><a href="<c:url value="/${loginMemberDto.actorKind}/officerList"/>">Officer</a></li>
						</ul>
						<ul class="nav navbar-nav">
							<li><a href="<c:url value="/${loginMemberDto.actorKind}/bbsList"/>">BBS</a></li>
						</ul>
<!-- 						<ul class="nav navbar-nav">
						    <li><a href="" />アンケート</a></li>
						</ul> -->
						<ul class="nav navbar-nav navbar-right">
							<li><a href="<c:url value="/${loginMemberDto.actorKind}/logout"/>">Logout</a></li>
						</ul>
						<c:if test="${loginMemberDto.actorKindCode == '1'}">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="<c:url value="/${loginMemberDto.actorKind}/settingsList"/>">Settings</a></li>
							</ul>
						</c:if>
					</div>
				</div>
			</div>
				<jsp:include page="/WEB-INF/view/common/tempMessage.jsp">
					<jsp:param name="authority" value="${loginMemberDto.actorKind}" />
				</jsp:include>
			</div>
		</div>
	</body>
</c:if>
<c:if test="${loginMemberDto.actorKindCode == '4'}">
	<body data-spy="scroll" data-target="#sidebar">
		<div id="wrap">
			<div id="header" class="navbar-fixed-top">
			<div class="navbar navbar-default ">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-top">
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="<c:url value="/${loginMemberDto.actorKind}/"/>">TsuboneSystem</a>
					</div>
					<div class="collapse navbar-collapse navbar-top">
					<ul class="nav navbar-nav navbar-right">
							<li><a href="<c:url value="/${loginMemberDto.actorKind}/logout"/>">Logout</a></li>
						</ul>
					</div>
				</div>
			</div>
				<jsp:include page="/WEB-INF/view/common/tempMessage.jsp">
					<jsp:param name="authority" value="${loginMemberDto.actorKind}" />
				</jsp:include>
			</div>
		</div>
	</body>
</c:if>