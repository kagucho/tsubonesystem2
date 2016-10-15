
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>神楽坂一丁目通信局</title>
    <link href="${f:url('/css/memberTopCss/bootstrap.css')}" rel="stylesheet">
    <link href="${f:url('/css/memberTopCss/slidefolio.css')}" rel="stylesheet">
    <link href="${f:url('/fonts/topfonts/css/font-awesome.min.css')}" rel="stylesheet">
    <link href="${f:url('/css/layout.css')}" rel="stylesheet">
    <link href="${f:url('/css/fullcalendar.css')}" type="text/css" rel="stylesheet">
    
  </head>
  <body>
  <%@ include file="/WEB-INF/view/common/header.jsp"%>
    <!-- Header Area -->
    <div id="top" class="header">
      <div class="vert-text">
	      <div class="col-md-6">
	       <h2>イベントを登録したい</h2>
	        <a href="<c:url value="/${loginMemberDto.actorKind}/partyRegist/index/"/>">
	         <button  type="button" class="btn btn-default btn-lg btn-block">EventRegist</button>
	        </a>
	      </div>
	      <div class="col-md-6">
	      	<h2>メールを送りたい</h2>
	      	<a href="<c:url value="/${loginMemberDto.actorKind}/mailRegist/"/>">
	      	<button type="button" class="btn btn-default btn-lg btn-block">SendMail</button>
	      	</a>
	      </div>
      </div>
    </div>
    <!-- /Header Area -->
    <!-- Services -->
    <div id="services" class="services">
      <div class="container">
        <div class="row">
          <div class="col-md-4 col-md-offset-4 text-center">
            <h2>現在出欠中のイベント</h2>
            <hr>
          </div>
        </div>
        <div class="row">
            <c:if test="${fn:length( tPartyNoAttendList ) >= 2}">
		        <c:forEach var="e" items="${tPartyNoAttendList}">
			        <div class="col-md-6 text-center">
			            <div class="">
			              <a href="<c:url value="/${loginMemberDto.actorKind}/partyDetail/"/>${e.id}"><h3>${f:h(e.meetingName)}</h3></a>
			              <div class=" col-md-6">
			                <a href="<c:url value="/${loginMemberDto.actorKind}/attend/yesFromList/${e.id}"/>">
			                  <button type="button" class="btn btn-success btn-lg btn-block marginUP">出席する</button>
			                </a>
			              </div>
			              <div class="col-md-6">
			                <a href="<c:url value="/${loginMemberDto.actorKind}/attend/noFromList/${e.id}"/>">
			                  <button type="button" class="btn btn-danger btn-lg btn-block marginUP">欠席する</button>
			                </a>
			              </div>
			            </div>
		          	</div>
		        </c:forEach>
	        </c:if>
	        <c:if test="${fn:length( tPartyNoAttendList ) == 1}">
		        <c:forEach var="e" items="${tPartyNoAttendList}">
			        <div class="col-md-12 text-center">
			            <div class="">
			              <a href="<c:url value="/${loginMemberDto.actorKind}/partyDetail/"/>${e.id}"><h3>${f:h(e.meetingName)}</h3></a>
			              <div class=" col-md-6">
			                <a href="<c:url value="/${loginMemberDto.actorKind}/attend/yesFromList/${e.id}"/>">
			                  <button type="button" class="btn btn-success btn-lg btn-block marginUP">出席する</button>
			                </a>
			              </div>
			              <div class="col-md-6">
			                <a href="<c:url value="/${loginMemberDto.actorKind}/attend/noFromList/${e.id}"/>">
			                  <button type="button" class="btn btn-danger btn-lg btn-block marginUP">欠席する</button>
			                </a>
			              </div>
			            </div>
		          	</div>
		        </c:forEach>
	        </c:if>
	        <c:if test="${fn:length( tPartyNoAttendList ) == 0}">
		        <div class="col-md-12 text-center">
				  <div class="">
				    <h4>※現在、開催日と締め切り日が設定されたイベントの出欠は募集していません。</h4>
				  </div>
			    </div>
	        </c:if>
        </div>
      </div>
    </div>
    <!-- /Services -->
    
   <!-- Contact -->
    <div id="contact">
      <div class="container">
        <div class="row">
		<div class="col-md-4 col-md-offset-4 text-center">
            <h2>Contact List</h2>
            <p>このシステムでできること・・・</p>
			<hr>
        </div>
          <div class="col-md-10 col-md-offset-1">
            <div class="service-item text-center linkArea">
		       <h3>Member List</h3>
		       <p>メンバーの連絡先の参照や、登録している自分の情報の編集ができます。</p>
		       <a href="<c:url value="/${loginMemberDto.actorKind}/memberList/"/>"></a>
		    </div>
		    <div class="service-item text-center linkArea">
		       <h3>Event List</h3>
		       <p>イベントの登録や、照会、過去のイベントの結果を見ることができます。</p>
		       <a href="<c:url value="/${loginMemberDto.actorKind}/partyList/"/>"></a>
		    </div>
		    <div class="service-item text-center linkArea">
		       <h3>Submit List</h3>
		       <p>作品の提出や閲覧することができます。</p>
		       <a href="<c:url value="/${loginMemberDto.actorKind}/submitList/"/>"></a>
		    </div>
		    <div class="service-item text-center linkArea">
		       <h3>Enquete List</h3>
		       <p>アンケートと作ったり、回答することができます。</p>
		       <a href="<c:url value="/${loginMemberDto.actorKind}/enqueteList/"/>"></a>
		    </div>
		    <div class="service-item text-center linkArea">
		        <h3>Kagucho BBS</h3>
		        <p>かぐちょの掲示板はこちら。</p>
		        <a href="<c:url value="/${loginMemberDto.actorKind}/bbsList/"/>"></a>
		    </div>
          </div>
        </div>
      </div>
    </div>
    <!-- /Contact -->
    
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
    
    
    
    <!-- Footer -->
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-md-6 col-md-offset-3 text-center">
           <h2>緊急連絡先</h2>
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
			timeFormat: "H:mm",
			// クリックされたイベントの編集画面に遷移する
			eventClick: function(calEvent, jsEvent, view) {
				location.href = '${f:url('partyDetail/detail/')}' + calEvent.id;
			},
        });
    });
	</script>
	
	
<!-- Slideshow Background  -->
<script>
	$.vegas('slideshow', {
	  delay:5000,
	  backgrounds:[
		 { src:'${f:url('/images/mypage/VwvWsCATBPimage.jpg')}', fade:5000 }  
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