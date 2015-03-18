<h3 class="CENTER">設定項目一覧</h3>
	<c:if test="${loginMemberDto.actorKindCode == 1 }">
		<div class="col-md-4">
			<a href="<c:url value="/admin/settingsEdit/tempMemberInput"/>"><button type="button" class="btn btn-default btn-lg btn-block">一時メンバーの変更</button></a>
		</div>
		<div class="col-md-8">
			<p>仮登録する際に使用するIDを設定できます。ここで作成したIDとパスワードを周知することによって、メンバー自身が登録することが出来ます。メンバー自身が作成したアカウントは仮登録状態となり、管理者が認証するまでログイン出来ません。</p>
		</div>
		<div class="clearfix"></div>
		<div class="col-md-4">
			<a href="<c:url value="/admin/settingsEdit/ruleUpdateInput"/>"><button type="button" class="btn btn-default btn-lg btn-block">規約の更新</button></a>
		</div>
		<div class="col-md-8">
			<p>規約の更新があった場合に、ここからPDFでアップロードすることで規約の更新ができます。</p>
		</div>
		<div class="clearfix btnMRC"></div>
		<div class="col-md-4">
			<a href="<c:url value="/admin/settingsEdit/topBuckImage"/>"><button type="button" class="btn btn-default btn-lg btn-block">背景画像の編集</button></a>
		</div>
		<div class="col-md-8">
			<p>外部向けページの背景画像を編集出来ます。</p>
		</div>
		<div class="clearfix btnMRC"></div>
	</c:if>
	<div class="col-md-4 ">
		<a href="<c:url value="/${loginMemberDto.actorKind}/topAnnounceList"/>"><button type="button" class="btn btn-default btn-lg btn-block">おしらせ</button></a>
	</div>
	<div class="col-md-8">
		<p>外部向けページにお知らせを表示させる事ができます。</p>
	</div>