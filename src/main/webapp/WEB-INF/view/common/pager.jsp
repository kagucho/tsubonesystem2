	<!-- total:総件数、hasPrev:前のページがあればTRUE、hasNext:次のページがあればTRUE、page:現在のページ番号、maxPageCount:最大ページ数 -->
	全部で<strong>${total}件</strong>のデータが有ります。<br />
	<ul class="pagination">
		<li>
			<c:if test="${hasPrev}">
				<a href="?page=${page - 1}">&laquo;</a>
			</c:if>
		</li>
		<c:forEach var="i" begin="0" end="${maxPageCount}" step="1">
			<li class="<c:if test="${i == page}">active</c:if>"><c:if test="${i == page}"><a href='#'>${i+1}</a></c:if>
			<c:if test="${i != page}"><a href='?page=${i}'>${i+1}</a></c:if></li>
		</c:forEach>
		<li>
			<c:if test="${hasNext}">
				<a href="?page=${page + 1}">&raquo;</a>
			</c:if>
		</li>
	</ul>