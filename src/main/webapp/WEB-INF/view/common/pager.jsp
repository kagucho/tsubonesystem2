	<!-- total:総件数、hasPrev:前のページがあればTRUE、hasNext:次のページがあればTRUE、page:現在のページ番号、maxPageCount:最大ページ数 -->
	全部で<strong>${total}件</strong>のデータが有ります。<br />
	<c:if test="${hasPrev}">
		<a href="?page=${page - 1}"><i class="glyphicon glyphicon-chevron-left"></i>前へ</a>　
	</c:if>
	<c:if test="${!hasPrev}">
		<i class="glyphicon glyphicon-chevron-left"></i>前へ　
	</c:if>
	<c:forEach var="i" begin="0" end="${maxPageCount}" step="1">
		<c:if test="${i == page}">${i+1}　</c:if>
		<c:if test="${i != page}"><a href='?page=${i}'>${i+1}</a>　</c:if>
	</c:forEach>
	<c:if test="${hasNext}">
		<a href="?page=${page + 1}">次へ<span class="glyphicon glyphicon-chevron-right"></span></a>
	</c:if>
	<c:if test="${!hasNext}">
		次へ<span class="glyphicon glyphicon-chevron-right"></span>
	</c:if>