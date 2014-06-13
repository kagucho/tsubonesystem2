	window.addEventListener("load", function(){
		
		//fileApiが使えるかどうかの確認
        if (!window.File){
            result.innerHTML = "File API 使用不可";
            return;
        }
        
        //imageFileに変化があった時以下の処理が実行される
        document.getElementById("imageFile").addEventListener("change", function(){
            var reader = new FileReader();
            
            //画像を読み込む
            reader.onload = function(event){
                document.getElementById("image").src = reader.result;
            }
            var file = document.getElementById("imageFile").files[0];
            $.ajax({  
                url: '${f:url('/api/imageUpload')}',  
                type: 'post',
                processData: false,
                contentType: false,
                data: file,
                dataType: "json",
                error: function(){  
                    alert("jsonファイルの読み込みに失敗しました");  
                },  
                success: function(json){  
                    
                    }
                }  
              });  
            
            reader.readAsDataURL(file);
        }, true);
    }, true);
