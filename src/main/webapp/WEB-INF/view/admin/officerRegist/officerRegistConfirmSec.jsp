<html>
<html lang="jp">
<%@ include file="/WEB-INF/view/common/headInclude.jsp"%>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<div class="col-sm-12">
		<h3>以下のメンバーでよろしいですか？</h3>
		<s:form method="POST">
			<div class="table-responsive">
				<table class="table">
					<tr>
						<th>名前</th><th>ハンドルネーム</th><th>メールアドレス</th><th>電話番号</th><th>入学年度</th>
					</tr>
						<tr>
							<td>
								${f:h(tMemberNew.name) }
							</td>
							<td>
								${f:h(tMemberNew.hname) }
							</td>
							<td>
								${f:h(tMemberNew.mail) }
							</td>
							<td>
								${f:h(tMemberNew.tel1) }-${f:h(tMemberNew.tel2) }-${f:h(tMemberNew.tel3) }
							</td>
							<td>
								${f:h(tMemberNew.entrance) }
							</td>
						</tr>
				</table>
				<c:if test="${!(officerKind == 2 || officerKind == 7)}">
					<%@ include file="/WEB-INF/view/common/leadersPowerConfirm.jsp"%>
				</c:if>
			</div>
			<input type="submit" value="登録" id="complete" name="complete" property="complete" class="btn btn-primary">
		</s:form>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>