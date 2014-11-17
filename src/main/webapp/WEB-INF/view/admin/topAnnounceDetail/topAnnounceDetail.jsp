<html>
<html lang="jp">
	<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
	<body>
		<%@ include file="/WEB-INF/view/common/header.jsp"%>
		<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
		<div class="container">
			<h3>${f:h(announceTitle)}の詳細</h3>
			<a class="col-xs-12 col-sm-2 col-md-2 col-sm-offset-7 col-md-offset-7 btn btn-danger btnYOKO btnMRC" href="<c:url value="/${loginMemberDto.actorKind}/topAnnounceDelete/${id}"/>" role="button">削除</a>
			<a class="col-xs-12 col-sm-2 col-md-2   btn btn-primary btnYOKO" href="<c:url value="/${loginMemberDto.actorKind}/topAnnounceUpdate/${id}"/>" role="button">更新</a>
			<%@ include file="/WEB-INF/view/common/topAnnounceFormConfirm.jsp"%>
			<c:if test="${tImageUpload != null}">
				<div class="col-md-4">
					<h3>添付画像</h3>
				</div>
				<div class="col-md-8">
					<img src="${f:url('/images/top/announce')}/${tImageUpload.fileName}" width="240" height="180" style="display:block;width:60%;height:auto;"/>
				</div>
			</c:if>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${f:url('/js/bootstrap.min.js')}"></script>
	</body>
</html>	