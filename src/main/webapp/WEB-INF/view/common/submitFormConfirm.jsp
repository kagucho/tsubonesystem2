<table class="table">
	<c:if test="${loginMemberDto.actorKindCode != 3}">
		<tr>
			<th class="col-md-3 col-xs-4"><h4>作品の提出者</h4></th>
			<td><h5>${f:h(submitMemberMap[registId])}</h5></td>
		</tr>
	</c:if>
	<tr>
		<th class="col-md-3 col-xs-4"><h4>作品の分類タグ</h4></th>
		<td><h5>${f:h(submitTagNameMap[submitTagKindId])}</h5></td>
	</tr>
	<tr>
		<th class="col-md-3 col-xs-4"><h4>おしらせとのひもづけ</h4></th>
		<td><h5>${f:h(topAnnounceMap[topAnnounceId])}</h5></td>
	</tr>
	<tr>
		<th class="col-md-3 col-xs-4"><h4>作品名</h4></th>
		<td><h5>${f:h(submitName)}</h5></td>
	</tr>
	<tr>
		<th class="col-md-3 col-xs-4"><h4>作品の説明</h4></th>
		<td><h5>${f:br(f:h(submitDetail))}</h5></td>
	</tr>
	<tr>
		<th class="col-md-3 col-xs-4"><h4>種別</h4></th>
		<td><h5>${f:h(submitProductFileCodeMap[submitProductFileType])}</h5></td>
	</tr>
	<c:if test="${submitProductFileType != 3}">
		<tr>
			<th class="col-md-3 col-xs-4"><h4>作品の画像</h4></th>
			<td><h5>(画像は省略)</h5></td>
		</tr>
	</c:if>
	<tr>
		<c:if test="${submitProductFileType == 2}">
			<th class="col-md-3 col-xs-4"><h4>作品(ファイル名)</h4></th>
			<c:if test="${registFlag}">
				<td><h5>${f:h(submitProductFileName)}</h5></td>
			</c:if>
			<c:if test="${!registFlag}">
				<td><a href="<c:url value="/${loginMemberDto.actorKind}/submitDetail/download/"/>${e.id}"><h5>${f:h(submitProductFileName)}</h5></a></td>
			</c:if>
		</c:if>
		<c:if test="${submitProductFileType == 3}">
			<th class="col-md-3 col-xs-4"><h4>作品(ファイル名)</h4></th>
				<td>${soundCloudUrl}</td>
		</c:if>
	</tr>
</table>