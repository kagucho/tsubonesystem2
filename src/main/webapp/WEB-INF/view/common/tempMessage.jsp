<%--
このページをインクルードするときは必ずパラメータをつけてください
具体的にはadmin権限なら
<jsp:include page="/WEB-INF/view/common/tempMessage.jsp" >
  <jsp:param name="authority" value="admin" />
</jsp:include>

一般会員なら
<jsp:include page="/WEB-INF/view/common/tempMessage.jsp" >
  <jsp:param name="authority" value="individuals" />
</jsp:include>

ページを遷移する際、「/セットした値/hoge.jsp」と遷移します。

 --%>


<%@page import="java.util.List"%>
<%@page import="tsuboneSystem.entity.TTempMessage"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<c:if test="${tempMessage != null}">
			<script type="text/javascript">
				function alertAjax(messageId, auth) {
					 $.ajax({
						url : '<c:url value="/"/>' + auth + '/messageDeleteAjax?messageId=' + messageId,
						type : 'get'
					});
				}
			</script>
			<style type="text/css">
				.alertMargin {
					margin:0px 5px 2px 5px;
				}
			</style>
			<% for(TTempMessage msg : (List<TTempMessage>)request.getAttribute("tempMessage")) { %>
				<span>
					<div class="alert alert-warning fade in alertMargin">
						<button type="button" class="close" data-dismiss="alert" onclick="alertAjax('<%= msg.id %>', '<%=request.getParameter("authority")%>')">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<%= msg.message %>
					</div>
				</span>
			<% } %>
		</c:if>