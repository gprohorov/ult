<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet"
          type="text/css" href="<@spring.url '/css/style.css'/>"/>
</head>
<body>

<h1>Welcome</h1>

<h2>${message}</h2>


<a href="<@spring.url '/student/list'/>">Student List</a>

</body>
</html>