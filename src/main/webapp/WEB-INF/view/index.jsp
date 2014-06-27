
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Slidefolio</title>
    <!-- Bootstrap core CSS -->
    <link href="${f:url('/css/topcss/bootstrap.css')}" rel="stylesheet">
    <!-- Add custom CSS here -->
    <link href="${f:url('/css/topcss/slidefolio.css')}" rel="stylesheet">
	<!-- Font Awesome -->
    <link href="${f:url('/fonts/topfonts/css/font-awesome.min.css')}" rel="stylesheet">
  </head>
  <body>
    <!-- Header Area -->
    <div id="top" class="header">
      <div class="vert-text">
	  <img class="img-rounded" alt="Company Logo" src="./images/top/logo.jpg"/>
        <h2><em>Kagutyo</em></h2>
		 <ul class="list-inline">
              <li><i class="fa fa-facebook fa-3x"></i></li>
              <li><i class="fa fa-twitter fa-3x"></i></li>
            </ul>	
			<br>
			<a href="#about" class="btn btn-top">Learn More</a>
      </div>
    </div>
    <!-- /Header Area -->
    
    <!-- About -->
    <div id="about" class="about_us">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <h2>神楽坂一丁目通信局について</h2>
            <p class="lead">こんにちは、神楽坂一丁目通信局（以下「局」）の代表です。
このサークルは内部にはProgram部、MIDI部、CG部、WebPage部、自作部の5つの部があり、各自興味を持った部に参加しています。 局自体は大きな活動をせず各部間の統制を取っているだけに過ぎません。 しかし各部が局に所属している以上各部の活動が局の活動とも言えます。 この様な少し複雑な形態をとることにより各部の活動を尊重しつつ各部間でのつながりが強くなると思います。
また局は1部文化会に所属しているのですが2部の学生も問題なく活動できます。 この活動の自由さは局の連絡方法がメールであることにあると思います。 局員用のMLを設置しそこで連絡や雑談をしているので時間的な制約がほとんどないのです。
各部の活動は各部毎のホームページなどを参考にして下さい。 これからも我が神楽坂通信局一丁目はパソコン技術を極めるために日々精進していきたいと思っています。 少しでも興味を持った人は気軽に私たちとコンタクトしてみてください。 きっと楽しいパソコンライフが待ち受けてます。</p>
          </div>
        </div>
	  </div>
    </div>
    <!-- /About -->
    <!-- Services -->
    <div id="services" class="services">
      <div class="container">
        <div class="row">
          <div class="col-md-4 col-md-offset-4 text-center">
            <h2>活動別紹介</h2>
            <hr>
          </div>
        </div>
        <div class="row">
	        <c:forEach var="e" items="${clubList}">
		        <div class="col-md-4 text-center">
		            <div class="service-item">
		              
		              <h3>${f:h(e.ClubName) }</h3>
		              <p>${f:h(e.ClubMemo) }</p>
		            </div>
	          	</div>
	        </c:forEach>
        </div>
      </div>
    </div>
    <!-- /Services -->

    <!-- Contact -->
    <div id="contact">
      <div class="container">
        <div class="row">
		<div class="col-md-4 col-md-offset-4 text-center">
            <h2>Contact Us</h2>
			<hr>
          </div>
          <div class="col-md-5 col-md-offset-3">
		  <!-- contact form starts -->
			<s:form method="POST" >
	            <form action="contact" id="contact-form" class="form-horizontal">
					<fieldset>
					    <div class="form-group">
					      <label class="col-sm-4 control-label" for="name">Your Name</label>
					      <div class="col-sm-8">
					        <input type="text"  placeholder="Your Name" class="form-control" name="name" id="name" property="name">
					      </div>
					    </div>
				    	<div class="form-group">
					      <label class="col-sm-4 control-label" for="email">Email Address</label>
					      <div class="col-sm-8">
					        <input type="text" placeholder="Enter Your Email Address" class="form-control" name="email" id="email" property="email">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="col-sm-4 control-label" for="subject">Subject</label>
					      <div class="col-sm-8">
					        <input type="text" placeholder="Subject" class="form-control" name="subject" id="subject" property="subject">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="col-sm-4 control-label" for="message">Your Message</label>
					      <div class="col-sm-8">
					      	<textarea placeholder="Please Type Your Message" class="form-control" name="message" id="message" rows="3" property="message"></textarea>
					      </div>
						</div>
			            <div class="col-sm-offset-4 col-sm-8">
		            		<button type="submit" class="btn btn-success">Submit</button>  
		      			</div>
					</fieldset>
				</form>	
			</s:form>
		  <!-- contact form ends -->		
          </div>
        </div>
      </div>
    </div>
    <!-- /Contact -->
    <!-- Footer -->
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-md-6 col-md-offset-3 text-center">
           <h2>Thank You</h2>
          </div>
        </div>
      </div>
    </footer>
    <!-- /Footer -->
    <!-- Bootstrap core JavaScript -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="${f:url('/js/topjs/jquery.js')}"></script>
	<script src="${f:url('/js/topjs/jquery-scrolltofixed-min.js')}"></script>
	<script src="${f:url('/js/topjs/jquery.vegas.js')}"></script>
	<script src="${f:url('/js/topjs/jquery.mixitup.min.js')}"></script>
	<script src="${f:url('/js/topjs/jquery.validate.min.js')}"></script>
	<script src="${f:url('/js/topjs/script.js')}"></script>
	<script src="${f:url('/js/topjs/bootstrap.js')}"></script>
	
<!-- Slideshow Background  -->
	<script>
$.vegas('slideshow', {
  delay:5000,
  backgrounds:[
     { src:'./images/top/nature1.jpg', fade:2000 },
	 { src:'./images/top/bw1.jpg', fade:2000 },
     { src:'./images/top/portrait1.jpg', fade:2000 },
	 { src:'./images/top/portrait5.jpg', fade:2000 },
     { src:'./images/top/portrait2.jpg', fade:2000 },
     { src:'./images/top/portrait3.jpg', fade:2000 },
	 { src:'./images/top/portrait4.jpg', fade:2000 },
	 { src:'./images/top/forest.jpg', fade:2000 }
	   
  ]
})('overlay', {
src:'./images/top/overlay.png'
});

	</script>
<!-- /Slideshow Background -->

<!-- Mixitup : Grid -->
    <script>
		$(function(){
    $('#Grid').mixitup();
      });
    </script>
<!-- /Mixitup : Grid -->	

    <!-- Custom JavaScript for Smooth Scrolling - Put in a custom JavaScript file to clean this up -->
    <script>
      $(function() {
        $('a[href*=#]:not([href=#])').click(function() {
          if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') 
            || location.hostname == this.hostname) {

            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
            if (target.length) {
              $('html,body').animate({
                scrollTop: target.offset().top
              }, 1000);
              return false;
            }
          }
        });
      });
    </script>
	
  </body>

</html>