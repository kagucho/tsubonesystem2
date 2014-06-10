<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>KaguTyo</title> 
    <link href="${f:url('/css/bootstrap.css')}" rel="stylesheet">
    <link href="${f:url('/css/main.css')}" rel="stylesheet">
	<link rel="stylesheet" href="${f:url('/css/font-awesome.min.css')}">
	<link rel="stylesheet" href="${f:url('/css/full-screen-slider.css')}">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="${f:url('/js/Chart.js')}"></script>
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,300,700' rel='stylesheet' type='text/css'>

  </head>

  <body>
   
   <%-- 以下の画像が背景としてスライドになる --%>
  	<div class="full-screen-slider">
  	  <img src="images/top/w.jpg"class="active">
      <img src="images/top/1.jpg">
      <img src="images/top/w.jpg">
      <img src="images/top/2.jpg">
      <img src="images/top/w.jpg">
      <img src="images/top/3.jpg">
      <img src="images/top/w.jpg">
      <img src="images/top/4.jpg">
      <img src="images/top/w.jpg">
      <img src="images/top/5.jpg">
      <img src="images/top/w.jpg">
      <img src="images/top/6.jpg">
      <img src="images/top/w.jpg">
      <img src="images/top/7.jpg">
      <img src="images/top/w.jpg">
      <img src="images/top/8.jpg">
    </div>
		
	<%-- Menu  --%>
	<nav class="menu" id="theMenu">
		<div class="menu-wrap">
			<h1 class="logo"><a href="">Kagutyo</a></h1>
			<i class="menu-close"><span class="glyphicon glyphicon-remove"></span></i>
			<a href="<c:url value="/introduction"/>">what's? KAGUTYO</a>
			<a href="<c:url value="/clubInfo"/>">Club information</a>
			<a href="<c:url value="/contact"/>">Contact</a>
		</div>
		<!-- Menu button -->
		<div id="menuToggle"><span class="glyphicon glyphicon-align-justify"></span></div>
	</nav>
	
	<div id="headerwrap">
		<div class="container">
			<br>
			<h1>KaguTyo</h1>
			<h2>神楽坂一丁目通信局</h2>
			<div class="row">
				<br>
				<br>
				<br>
				<div class="col-lg-6 col-lg-offset-3">
				<h3></h3>
				</div>
			</div>
		</div><!-- /container -->
	</div><!-- /headerwrap -->

	
	<script src="${f:url('/js/classie.js')}"></script>
    <script src="${f:url('/js/bootstrap.min.js')}"></script>  
    <script src="${f:url('/js/smoothscroll.js')}"></script>
    <script src="${f:url('/js/full-screen-slider.js')}"></script>
	<script src="${f:url('/js/main.js')}"></script>
</body>
</html>