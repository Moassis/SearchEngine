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
    <form action = "MyServlet">
        <input type = "text" name = "keyword"> </input>
        <button type="submit">Search</button>
    </form>
    
    <div class = "resultTable">
        <table border="2"> 
            <tr>
                <td>Name</td>
                <td>Link</td>
            </tr>
            
            <%
                ArrayList<searchResult> results = (ArrayList<searchResult>) request.getAttribute("results");
                for(searchResult result:results){
            %>
    
            <tr>
                <td><%out.println(result.getTitle());%></td>
                <td><a href = "<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>
            </tr>
    
            <%
                }
            %>
        </table>
    </div>
    
</body>
</html>