<body data-spy="scroll" data-target="#sidebar">
	<div id="wrap">
		<div id="header" class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-top">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<c:url value="/admin/"/>">TsuboneSystem</a>
				</div>
				<div class="collapse navbar-collapse navbar-top">
					<ul class="nav navbar-nav">
						<li><a href="<c:url value="/admin/memberList"/>">Member</a></li>
					</ul>
					<ul class="nav navbar-nav">
						<li><a href="<c:url value="/admin/partyList"/>">Party</a></li>
					</ul>
					<ul class="nav navbar-nav">
						<li><a href="<c:url value="/admin/clubList"/>">Club</a></li>
					</ul>
					<ul class="nav navbar-nav">
						<li><a href="<c:url value="/admin/mailList"/>">Mail</a></li>
					</ul>
					<ul class="nav navbar-nav">
						<li><a href="<c:url value="/admin/officerList"/>">Officer</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="<c:url value="/admin/logout"/>">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>