
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>神楽坂一丁目通信局</title>
    <!-- Bootstrap core CSS -->
    <link href="${f:url('/css/topcss/bootstrap.css')}" rel="stylesheet">
    <!-- Add custom CSS here -->
    <link href="${f:url('/css/topcss/slidefolio.css')}" rel="stylesheet">
    <link href="${f:url('/css/layout.css')}" rel="stylesheet">
	<!-- Font Awesome -->
    <link href="${f:url('/fonts/topfonts/css/font-awesome.min.css')}" rel="stylesheet">
        <link href="${f:url('/css/topcss/fullcalendar.css')}" type="text/css" rel="stylesheet">

    </script>
  </head>
  <body>
    <!-- Header Area -->
    <div id="top" class="header">
      <div class="vert-text">
	  <img class="img-rounded" alt="" src="./images/top/logo250c.png"/>
        <h2>神楽坂一丁目通信局</h2>
			<br>
			<a href="#about" class="btn btn-top">What's KAGUCHO?</a>
      </div>
    </div>
    <!-- /Header Area -->
    <div id="second" class="second">



    <!-- About -->
    <div id="about" class="about_us">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <h2>What's KAGUCHO?</h2>
            <div class="col-md-4">
            	<h3>Independent</h3>
            	<p class="lead">
            	神楽坂一丁目通信局の特徴として、内部に4つの部が存在していることです。prog部、CG部、DTM部、自作部が存在し、別々の活動を行う一方で、prog部が作成したゲームにCG部がデザインし、DTM
            	部が音楽をつけたりと、独立の活動を行いながら時には一つの作品を作成しています。
            	</p>
            </div>
            <div class="col-md-4">
            	<h3>Creativity</h3>
            	<p class="lead">
            		prog部はゲームを作成したり、時には外部との開発イベントをこなしたりしながら技術を磨いています。DTM部では、作曲した曲をM3に展示したりと積極的に創作活動をしています。CG部においては、3D・2D問わず作品を創作し、他の部の作品に提供しています。
            	</p>
            </div>
            <div class="col-md-4">
            	<h3>Activity</h3>
            	<p class="lead">
            		理科大の学園祭“理大祭”はもちろんのこと、DTM部はM3などに出展、prog部は外部の部と共同で開発のためのイベントに参加したりしています。また局のイベントとして夏には合宿を行っています。
            	</p>
            </div>
          </div>
        </div>
	  </div>
    </div>
    <!-- /About -->
    <!-- おしらせ -->
	<c:if test="${fn:length( topAnnounceList ) > 0}">
		<div id="announce" class="services">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4 text-center serviceMessage">
						<h2>おしらせ</h2>
						<hr>
					</div>
				</div>
				<div class="row">
					<c:forEach var="e" items="${topAnnounceList}" varStatus="status">
						<div class="col-md-12 text-center announce">
							<c:if test="${e.submitFlag}">
								<div class="service-item text-center linkArea">
									<a href="<c:url value="/announceList/${e.id}"/>"></a>
									<table>
										<tbody>
											<tr>
												<c:if test="${e.tImageUpload != null}">
													<td align="center" rowspan="2">
                            <img src="${f:url('/api/displyImage')}${e.tImageUpload.id}" alt="${f:h(e.announceTitle) }" width="240" height="180" style="display:block;width:60%;height:auto;"/>
                          </td>
												</c:if>
												<th class="CENTER"><h3>${f:h(e.announceTitle) }</h3></th>
											</tr>
											<tr>
												<td align="left"><h4>${f:br(f:h(e.announceContent))}</h4></td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
							<c:if test="${!e.submitFlag}">
								<div class="service-item text-center">
									<table>
										<tbody>
											<tr>
												<c:if test="${e.tImageUpload != null}">
													<td align="center" rowspan="2"><img src="${f:url('/images/top/announce')}/${e.tImageUpload.fileName}" alt="${f:h(e.announceTitle) }" width="240" height="180" style="display:block;width:60%;height:auto;"/></td>
												</c:if>
												<th class="CENTER"><h3>${f:h(e.announceTitle) }</h3></th>
											</tr>
											<tr>
												<td align="left"><h4>${f:br(f:h(e.announceContent))}</h4></td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:if>
    <!-- おしらせ -->
    <!-- 部活別照会 -->
    <div class="services">
      <div class="container">
        <div class="row">
          <div class="col-md-4 col-md-offset-4 text-center">
            <h2>活動別紹介</h2>
            <hr>
          </div>
        </div>
        <div class="row">
	        <c:forEach var="e" items="${clubList}">
		        <div class="col-md-12 text-center">
		            <div class="service-item">
		              <c:if test="${e.clubUrl != null}">
		            	  <a href="${e.clubUrl}"><h3>${f:h(e.ClubName) }</h3></a>
		              </c:if>
		              <c:if test="${e.clubUrl == null}">
		            	  <h3>${f:h(e.ClubName) }</h3>
		              </c:if>
		              <p>${f:h(e.ClubMemo) }</p>
		            </div>
	          	</div>
	        </c:forEach>
        </div>
      </div>
    </div>
    <!-- /Services -->
    <!-- カレンダー -->
    <div id="contact">
      <div class="container">
        <div class="row">
          <div class="col-md-4 col-md-offset-4 text-center">
            <h2>schedule</h2>
            <hr>
          </div>
          <div class="col-md-8 col-md-offset-2">
            <div id='calendar'></div>
          </div>
        </div>
      </div>
    </div>
    <!-- /カレンダー -->
    

    <!-- Contact -->
    <div id="contact">
      <div class="container">
        <div class="row">
		<div class="col-md-4 col-md-offset-4 text-center">
            <h2>Contact Us</h2>
			<hr>
          </div>

          <div class="col-md-9 col-md-offset-1">
          <h5 class="CENTER">お問い合わせはこちらまで</h5>
          <a href="mailto:kagucho.net@gmail.com"><h5 class="CENTER">kagucho.net@gmail.com</h5></a>
		 
          </div>
        </div>
      </div>
    </div>
    
    </div>

    <script src="${f:url('/js/topjs/jquery.js')}"></script>
	<script src="${f:url('/js/topjs/jquery-scrolltofixed-min.js')}"></script>
	<script src="${f:url('/js/topjs/jquery.vegas.js')}"></script>
	<script src="${f:url('/js/topjs/jquery.mixitup.min.js')}"></script>
	<script src="${f:url('/js/topjs/jquery.validate.min.js')}"></script>
	<script src="${f:url('/js/topjs/script.js')}"></script>
	<script src="${f:url('/js/topjs/bootstrap.js')}"></script>
	<script src="${f:url('/js/moment.min.js')}"></script>
	<script src="${f:url('/js/fullcalendar.js')}"></script>
	<script type='text/javascript'>
	$(function() {
		$('#calendar').fullCalendar({
        	events: {
        		url: '${f:url('/api/fulCalenderApi/getJson')}',
				error: function() {
					$('#script-warning').show();
				}
			},
			timeFormat: "",
        });
    });
	</script>


<!-- Slideshow Background  -->
	<script>
$.vegas('slideshow', {
  delay:5000,
  backgrounds:[
	 <c:forEach var="e" items="${imageList}">
	   { src:'${f:url('/api/displyImage')}${e.id}', fade:5000 },
	 </c:forEach>
	 { src:'./images/top/2.png', fade:5000 }
  ]
})('overlay', {

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