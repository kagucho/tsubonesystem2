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
  </head>
<body>
<%@ include file="/WEB-INF/view/common/header.jsp"%>
<%@ include file="/WEB-INF/view/common/jumbotronMenu.jsp"%>

<div class="container">
    <form class="form-signin" role="form">
      	<s:form method="POST" >
        	<h2 class="form-signin-heading">Please sign in</h2>
        		<html:errors property="signError"/>
        		<input type="text" id="id" name="id" property="id" class="form-control btnMRC" placeholder="ID">
        		<html:errors property="id"/>
        		<input type="password" id="password" name="password" property="password" class="form-control btnMRC" placeholder="Password">
        		<html:errors property="password"/>
        		<input type="submit" value="Sign in" id="complete" name="complete" property="complete" class="btn btn-lg btn-primary btn-block">
        </s:form>
    </form>
</div> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${f:url('/js/bootstrap.min.js')}"></script>
</body>
</html>