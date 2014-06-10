<c:set var="page" value="${MemberListForm.pages}"/>
<logic:iterate id="itm" name="MemberListForm" property="allpages" indexId="i">
	<c:choose>
		<c:when test="${page == i}">
			<bean:write name="itm" />
		</c:when>
		<c:otherwise>
			<html:link action="/index.do" indexed="true" indexId="pages">
				<bean:write name="itm" />
			</html:link>
		</c:otherwise>
	</c:choose>
</logic:iterate>