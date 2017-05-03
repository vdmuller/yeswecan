<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Yes we can</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>


	<div class="container">

		<div class="starter-template">
            <h1>Consultar Natural Language Understanding</h1>
            
            <div class="well well-sm">
			<form action="nlu" method="post"
                class="form-horizontal">
				
				<div class="form-group">
					<label for="user" class="col-sm-2 control-label">user</label>
					<div class="col-sm-10"> 
						<input type="text" name="user"
						   id="user" class="form-control"
						   value="<c:out value="${param.user}" default="d232f093-85ce-48a8-8d16-889f5c24c8dc"/>">
					</div>
				</div>

				<div class="form-group">
				    <label for="pass" class="col-sm-2 control-label">pass</label> 
					<div class="col-sm-10"> 
						<input type="text" name="pass"
						   id="pass" class="form-control"
						   value="<c:out value="${param.pass}" default="3J8TCYLaIKlo"/>" />
				    </div>
				</div>

				<div class="form-group">
				    <label for="model_id" class="col-sm-2 control-label">model id</label>
				    <div class="col-sm-10">  
						<input type="text" name="modelId"
						   id="model_id" class="form-control"
						   value="<c:out value="${param.modelId}" default="20:bae850be-5ab6-4140-83bc-4e6533f968f7"/>" />
					</div>
				</div>

				<div class="form-group">
				    <label for="language" class="col-sm-2 control-label">language</label>
				    <div class="col-sm-10">  
						<input type="text" name="language"
						   id="language" class="form-control"
						   value="<c:out value="${param.language}" default="pt"/>" />
					</div> 
				</div>

				<div class="form-group">
					<label for="texto" class="col-sm-2 control-label">texto</label>
					<div class="col-sm-10">  
						<textarea name="texto" 
						   id="texto" class="form-control"
						   style="width: 100%; height: 100px" />${param.texto}</textarea>
					</div>
				</div>
                
                <div class="form-group">
                    <div class="col-sm-10 col-md-offset-2">  
				        <button type="submit" class="btn btn-primary">Enviar</button>
				    </div>
				</div>
			</form>
            </div>
            
            <c:if test="${requestScope.response != null}">
				<div class="panel panel-primary">
	                <div class="panel-heading">Entidades</div>
	                <div class="panel-body">
						<c:forEach var="entitiesResult"
							items="${requestScope.response.entities}"> 
					       Tipo: ${entitiesResult.type},
					       Texto: ${entitiesResult.text},
					       Contagem: ${entitiesResult.count}
					       <br>
						</c:forEach>
					</div>
				</div>
	
				<pre>${requestScope.response}</pre>
			</c:if>
			
		</div>

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="js/autogrow.min.js"></script>
	
	<script>
	   $(document).ready(function() {
		    $('textarea').autogrow({onInitialize: true});
	   });
	</script>
</body>
</html>
