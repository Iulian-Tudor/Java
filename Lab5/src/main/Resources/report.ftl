<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document Catalog Report</title>
</head>
<body>
    <h1>Document Catalog Report</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Location</th>
                <th>Author</th>
                <th>Year</th>
            </tr>
        </thead>
        <tbody>
            <#list documents as document>
                <tr>
                    <td>${document.id}</td>
                    <td>${document.name}</td>
                    <td>${document.location}</td>
                    <td>${document.tags.author!" "}</td>
                    <td>${document.tags.year!" "}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>
