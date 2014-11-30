$(function() {

    // 作品
    var submitFileChangeTrigger = function(){
      var submitFileSize = ($(".submitFile")[0].files[0].size / 1048576);
      if (submitFileSize > 10) {
        $('#submitFile').remove();
        $('<input type="file" id="submitFile" name="submitFile" class="submitFile">').appendTo('#submitDiv');
        $('#submitFile').bind('change',function(){
          fileChangeTrigger();
        });
        alert("ファイルサイズが大きすぎます。最大10MB以内にしてください。");
      }
    };
    $('#submitFile').bind('change',function(){
      submitFileChangeTrigger();
    });
    
    // キャプション
    var submitCaptionImageFileChangeTrigger = function(){
      var submitFileSize = ($(".submitCaptionImageFile")[0].files[0].size / 1048576);
      if (submitFileSize > 5) {
        $('#submitCaptionImageFile').remove();
        $('<input type="file" id="submitCaptionImageFile" name="submitCaptionImageFile" class="submitCaptionImageFile">').appendTo('#submitCaptionImageFileDiv');
        $('#submitCaptionImageFile').bind('change',function(){
          fileChangeTrigger();
        });
        alert("ファイルサイズが大きすぎます。最大5MB以内にしてください。");
      }
    };
    $('#submitCaptionImageFile').bind('change',function(){
      submitCaptionImageFileChangeTrigger();
    });    
});