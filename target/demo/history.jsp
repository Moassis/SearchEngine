<%@page import = "java.util.*"%>
<%@page import = "com.example.*"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "styleSheet" type = "text/css" href="styles.css">
</head>
<body>
    <div class = "resultTable">
        <table border="2"> 
            <tr>
                <td>Name</td>
                <td>Link</td>
            </tr>
            
            <%
                ArrayList<historySearch> results = (ArrayList<historySearch>) request.getAttribute("results");
                for(historySearch result:results){
            %>
    
            <tr>
                <td><%out.println(result.getName());%></td>
                <td><a href = "<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>
            </tr>
    
            <%
                }
            %>
    
        </table>
    </div>
</body>
</html>