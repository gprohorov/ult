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

    <h3>Student list</h3>
    <br>
    <div>

        <table border="3" bgcolor="#f0f8ff">
            <tr>
                <th>Name</th>
                <th>Group</th>
                <th>Mark</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <#list students as student>
                <tr>
                    <td>${student.name}</td>
                    <td>${student.group.name}</td>
                    <td>${student.mark}</td>
                    <td><a href="/web/student/delete/${student.id}">Delete</a></td>
                    <td><a href="/web/student/edit/${student.id}">Edit</a></td>
                </tr>
            </#list>
        </table>

    </div>

    <a href="/web/student/add">Add new student</a>


</body>
</html>
