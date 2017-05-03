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
				    <label for="model_id" class="col-sm-2 control-label">model id</label>
				    <div class="col-sm-10">  
						<input type="text" name="modelId"
						   id="model_id" class="form-control"
						   value="<c:out value="${param.modelId}" default="20:f678f91e-5fde-4a19-b6e7-98aff4414bf2"/>" />
					</div>
				</div>

				<div class="form-group">
					<label for="texto" class="col-sm-2 control-label">texto</label>
					<div class="col-sm-10">  
						<textarea name="texto" 
						   id="texto" class="form-control"
						   style="width: 100%; height: 100px" />${param.texto}</textarea>
						<p class="help-block">Ex: Mostrar Disciplinas Sala.</p>
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
	                   <table class="table table-striped table-condensed">
	                   <thead>
	                       <tr>
	                           <th>Tipo</th>
	                           <th>Texto</th>
                               <th>Ocorrências</th>
	                       </tr>
	                   </thead>
                       <tbody>
						   <c:forEach var="entitiesResult"
								items="${requestScope.response.entities}"> 
						      <tr>
						          <td>${entitiesResult.type}</td>
						          <td>${entitiesResult.text}</td>
						          <td>${entitiesResult.count}</td>
						       </tr>
							</c:forEach>
					   </tbody>
						</table>
					</div>
				</div>
				
				<div class="panel panel-primary">
                    <div class="panel-heading">Contagem de PF</div>
                    <div class="panel-body">
                        <table class="table table-bordered table-condensed">
	                       <thead>
	                           <tr>
	                               <th>CE <small class="text-muted">(3 PF / 4 PF)</small></th>
	                               <th>SE <small class="text-muted">(5 PF)</small></th>
	                               <th>EE <small class="text-muted">(3 PF / 4 PF)</small></th>
	                               <th>ALI <small class="text-muted">(10 PF)</small></th>
	                               <th>AIE <small class="text-muted">(7 PF)</small></th>
	                               <th>Total PF</th>
	                           </tr>
	                       </thead>
	                       <tbody>
	                            <tr>
	                                <td>${requestScope.contagemPF.contadorCE}</td>
		                            <td>${requestScope.contagemPF.contadorSE}</td>
		                            <td>${requestScope.contagemPF.contadorEE}</td>
		                            <td>${requestScope.contagemPF.contadorALI}</td>
		                            <td>${requestScope.contagemPF.contadorAIE}</td>
		                            <td><strong>${requestScope.contagemPF.totalPF}</strong></td>
		                        </tr>
		                   </tbody>
	                    </table>
	                </div>
                </div>
                
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		            <div class="panel panel-default">
		                <div class="panel-heading" role="tab" id="heading1">
		                  <h4 class="panel-title">
		                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse1" aria-expanded="false" aria-controls="collapse1">
		                      Resposta Processada
		                    </a>
		                  </h4>
		                </div>
		                <div id="collapse1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading1">
		                  <div class="panel-body">
		                    <pre>${requestScope.response}</pre>
		                  </div>
		                </div>
		              </div>
		              
		             <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading2">
                          <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse2" aria-expanded="false" aria-controls="collapse2">
                              Resposta Original
                            </a>
                          </h4>
                        </div>
                        <div id="collapse2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading2">
                          <div class="panel-body">
                            <pre>${requestScope.responseOriginal}</pre>
                          </div>
                        </div>
                      </div>
	            </div>
				
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
