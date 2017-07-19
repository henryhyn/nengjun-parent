<html>
<title>Hello world!</title>

<body>
<#list data.list as article>
<h2>${article.title}</h2>

<div>${article.htmlcontent}</div>
<a href="/articles/${article.id}">继续阅读</a>
</#list>
</body>
</html>
