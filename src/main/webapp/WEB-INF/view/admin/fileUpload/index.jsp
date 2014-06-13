<html>
<html lang="jp">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TsuboneSystem</title>
    <link href="${f:url('/css/bootstrap.min.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/layout.css')}" type="text/css" rel="stylesheet">
    <link href="${f:url('/css/signin.css')}" type="text/css" rel="stylesheet">
    <script>
		window.addEventListener("load", function(){
		
		//fileApiが使えるかどうかの確認
        if (!window.File){
            result.innerHTML = "File API 使用不可";
            return;
        }
        
        //imageFileに変化があった時以下の処理が実行される
        document.getElementById("file").addEventListener("change", function(){
            var reader = new FileReader();
            
            //画像を読み込む
            reader.onload = function(event){
                document.getElementById("file").src = reader.result;
            }
            //var file = document.getElementById("file").files[0];
            var form = $('#ajaxform').get(0);
            var formData = new FormData(form);
            $.ajax({  
                url: '${f:url('/api/imageUpload')}',  
                type: 'post',
                processData: false,
                contentType: false,
                data: formData,
                dataType: "json",
                error: function(){  
                    alert("jsonファイルの読み込みに失敗しました");  
                },  
                success: function(json){  
                    
                    }
                }  
              );  
            
            reader.readAsDataURL(file);
        }, true);
    }, true);
    </script>
  </head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>
<div class="container">
	<form class="form-horizontal" id="ajaxform">
		<div class="form-group">
			<label class="control-label col-sm-4" for=file>画像</label>
			<div class="col-sm-8 memberF" >
				<input type="file" id="file" name="file">
				<html:errors property="file"/>
			</div>
		</div>
	</form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>