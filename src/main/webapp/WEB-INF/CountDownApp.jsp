<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="imane.WebServlet.CountDown" %>
<%@ page import="javax.servlet.http.Cookie" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Les Comptes à rebours</title>
	<link rel="stylesheet" href="css/foundation.css">
	<link rel="stylesheet" href="css/app.css">

</head>

<body>
	<div class="row">
		<div class="large-12 columns">
			<h1>Les Comptes à rebours</h1>
		</div>
	</div>
	<div class="row">
	</div>
	<div class="row">
	</div>
	<div class="row">
		<div class="large-12 columns">
			<a class="small expanded button" href="/Application/Add">Ajouter</a>
		</div>
	</div>

	<div class="row">
		<div id="rcorners3" class="large columns">
			<table>
				<thead>
					<tr>
						<th></th>
						
						
						
					</tr>
				</thead>
				<%
				if(request.getAttribute("nouveau_compteur") != "false" )
				{
					Cookie[] diff = (Cookie[]) request.getAttribute("nouveau_compteur");
					int i=0;
				%>
				<tbody>
					<% for ( Cookie c : diff)
							{
								if(i != 0)
								{
									// une condition pour ne pas afficher les cookies vides (supprimés)
									if(!c.getValue().equals(""))
									{
										//une fonction pour decouper la valeur dans la cookie qui contient les details d'un compteur
									  String[] chaine = c.getValue().split(" ");
						%>
					<tr>
						<td>
							<%
											out.println(chaine[0]);
				 						%>Non
						</td>
						
						<td>
							<form action="/App/modifier" method="post">
								
							</form>
						</td>
						
					</tr>
					<%
									}
								}
								i++;
							}
							%>
					<tr>
						<td>Timer</td>
						<td><td id="< out.print(c.getName());>"></td></td>
						
						
					</tr>
					<tr>
						<td>Modifier</td>
						<td><button name="modifier" type="submit" class="button" value="< out.print(c.getName()); >">Modifier</button></td>
						
						
					</tr>
					<tr>
						<td>Supprimer</td>
						<td><button name="supprimer" type="submit" class="alert button" value="< out.print(c.getName()); >">Supprimer</button></td>
						
						
					</tr>
				</tbody>
				<%
				}
				%>
			</table>
		</div>
	</div>

    <script type="text/javascript">
        var webSocket = new WebSocket(
                'ws://localhost:8080/App/websocket');

        webSocket.onerror = function(event) {
            onError(event)
        };

        webSocket.onopen = function(event) {
            onOpen(event)
        };

        webSocket.onmessage = function(event) {
            onMessage(event)
        };

        function onMessage(event) {
						var data = event.data;
						var tableau = JSON.parse(data);

				<%	if(request.getAttribute("nouveau_compteur") != "false" )
						{
							Cookie[] diff = (Cookie[]) request.getAttribute("nouveau_compteur");
							for(Cookie c : diff)
							{%>
								for (var i = 0; i<tableau.length; i++)
								{
									var t = tableau[i].Id;
										if(t === '<% out.print(c.getName()); %>')
									{
										document.getElementById('<% out.print(c.getName()); %>').innerHTML = tableau[i].Difference;
									}
								}
					<%	}
					}  %>
        }

        function onOpen(event) {
						start();
        }

        function onError(event) {
            alert(event.data);
        }

        function start() {
            <%
						String text = "";
						if(request.getAttribute("nouveau_compteur") != "false" )
						{
							Cookie[] diff = (Cookie[]) request.getAttribute("nouveau_compteur");
							int longueur = diff.length;
							int i=0;
				 				for(Cookie c : diff)
								{
									if( (i != 0) && (i != longueur-1) )
									{
										// une condition pour ne pas afficher les cookies vides (supprimés)
										if(!c.getValue().equals(""))
										{
											text = text + c.getName()+"!"+c.getValue()+"@";
										}
									}else if(i == longueur-1)
									{
										if(!c.getValue().equals(""))
										{
											text = text + c.getName()+"!"+c.getValue();
										}
									}
									i++;
								}
						}
						%>
						var text = "<%out.print(text);%>";
            webSocket.send(text);
            return false;
        }
    </script>
</body>
</html>
