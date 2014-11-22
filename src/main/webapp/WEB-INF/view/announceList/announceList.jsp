
<html lang="jp">
	<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
	<body>
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
							<a class="navbar-brand" href="<c:url value="/"/>">TsuboneSystem.TOP</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		</body>
		<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
		<div class="container">
			<h2 class="CENTER">${tTopAnnounce.announceTitle}</h2>
			<h4>${f:br(f:h(tTopAnnounce.announceContent))}</h4>
			<c:forEach var="e" items="${tSubmitList}">
				<div class="col-md-12 col-xs-12 announce  announceListOne">
					<div class="announceListOne">
						<table>
							<tbody>
							<tr>
							<c:if test="${e.tImageUpload != null}">
								<td align="center" rowspan="3"><img src="${f:url('/images/top/announce')}/${e.tImageUpload.fileName}" width="240" height="180" style="display:block;width:40%;height:auto;"/></td>
							</c:if>
							<th class="CENTER"><h3>${f:h(e.submitName) }</h3></th>
							</tr>
							<tr>
								<td>作成者：${f:h(e.tMember.hname) }</td>
							</tr>
							<tr>
								<td align="left">
									<h4>${f:br(f:h(e.submitDetail))}</h4>
									<c:if test="${e.submitProductFileType == 2 }">
										<a href="<c:url value="/announceList/download/${e.id }"/>"><button type="button" class="col-md-8 col-xs-12 col-md-offset-2 btn btn-default">Download</button></a>
									</c:if>
								</td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</c:forEach>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${f:url('/js/bootstrap.min.js')}"></script>
	</body>
