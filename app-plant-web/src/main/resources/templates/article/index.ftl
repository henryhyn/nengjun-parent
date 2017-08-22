<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link href="/static/favicon.ico" rel="shortcut icon">
    <link href="/static/favicon.ico" rel="apple-touch-icon">
    <title>开源书屋</title>
    <link href="https://114.215.28.105/blog.css" rel="stylesheet">
</head>
<body>
<div class="hui-wrapper">
<#list data.list as article>
    <div class="hui-md-card">
        <h2 class="hui-md-header">${article.title}</h2>

        <div class="hui-md-body">${article.htmlContent}</div>
        <div class="hui-md-footer">
            <a href="/articles/${article.id}">继续阅读</a>
        </div>
    </div>
</#list>
</div>
</body>
</html>
