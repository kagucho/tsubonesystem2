$(function($) {
	$('#submitCaptionImageFile').change(function(){
		// ファイルがない場合は何もしない
		if (!this.files.length) {
			return;
		}
		var file = this.files[0],
		$_img = $(this).siblings('img'),
		fileReader = new FileReader();
		
		var fileSize = file.size / 1048576; // 取得したファイルの大きさをMBに直す
		var fileType = file.type; // ファイルの拡張子を取得する
		var limitSize = 5242880; // アップロードの最大は5MBとする
		var limitTypes = ['image/jpeg', 'image/png']; // 拡張子はjpegとpngのみ
		var judge = limitTypes.indexOf(fileType); // 拡張子が条件外であったら-1
		
		// 最大容量内か
		if (fileSize < limitSize) {
			// 許可された拡張子か
			if (judge !== -1) {
				// ファイルを読み込み、表示する
				fileReader.onload = function(event) {
					$_img.attr('src', event.target.result);
				};
				fileReader.readAsDataURL(file);
			} else {
				alert("拡張子はjpeg、jpg、pngのみです");
				this.files[0] = null;
			}
		} else {
			alert("ファイルサイズは最大5Mまでです");
			this.files[0] = null;
		}
	});
});