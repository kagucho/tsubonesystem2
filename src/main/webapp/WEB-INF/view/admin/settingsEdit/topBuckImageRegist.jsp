<html>
<html lang="jp">
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>tsuboneSystem</title>
	<link href="${f:url('/css/bootstrap.css')}" type="text/css" rel="stylesheet">
	<link href="${f:url('/css/layout.css')}" type="text/css" rel="stylesheet">
	<link href="${f:url('/css/signin.css')}" type="text/css" rel="stylesheet">
	<link href="${f:url('/css/bootstrap-responsive.css')}" type="text/css" rel="stylesheet">
	<link href="${f:url('/css/dropzone-basic.css')}" type="text/css" rel="stylesheet">
	<link href="${f:url('/css/dropzone.css')}" type="text/css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="${f:url('/js/bootstrap.min.js')}"></script>
	<script src="${f:url('/js/dropzone.js')}"></script>
	<script src="${f:url('/js/jquery.blockUI.js')}"></script>
</head>
<body>
	<%@ include file="/WEB-INF/view/common/header.jsp"%>
	<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
	<div class="container">
		<input type="hidden" value="{setName}" class="setName">
		<div id="my-awesome-dropzone" class="dropzone">
			<p>ここにアップロードしたい写真をドロップしてください</p>
		</div>
		<button type="button" class="btn btn-default col-xs-12 btnMRC startBTN">新しい写真をアップロードする</button>
	</div>
	<script type="text/javascript">
	$(function(){
		Dropzone.autoDiscover = false;
		Dropzone.options.myAwesomeDropzone = {
			paramName : "file",           // input fileの名前
			parallelUploads:100,          // 1度に何ファイルずつアップロードするか
			acceptedFiles:'image/*',      // 画像だけアップロードしたい場合
			maxFiles:100,                 // 1度にアップロード出来るファイルの数
			maxFilesize:2,                // 1つのファイルの最大サイズ(1=1M)
			dictFileTooBig: "ファイルが大きすぎます。 ({{filesize}}MiB). 最大サイズ: {{maxFilesize}}MiB.",
			dictInvalidFileType: "画像ファイル以外です。",
			dictMaxFilesExceeded: "一度にアップロード出来るのは100ファイルまでです。",
			addRemoveLinks: true,
			autoProcessQueue: false,
			removedfile: function(file) { 
				var _ref;
				return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
			},
			success:function(file, responseText, e) {
				window.location.href = '${f:url('/admin/settingsEdit/topBuckImage')}';
			}
		};
		// urlは実際に画像をアップロードさせるURLパスを入れる
		var myDropzone = new Dropzone("#my-awesome-dropzone",{url:"${f:url('/api/imageUpload')}"});
		$('.startBTN').click(function() {
			$.blockUI({
				message: '<div>アップロードしています。<br/>写真が多い場合は時間がかなりかかる場合があります。</div>',
				css: {
					padding:'25px'
				}
			});
			myDropzone.processQueue();
		});
	});
	
	</script>
</body>
</html>