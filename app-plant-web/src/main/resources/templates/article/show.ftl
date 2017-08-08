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
    <title>${article.title}</title>
    <link href="http://114.215.28.105/blog.css" rel="stylesheet">
</head>
<body>
<div class="hui-wrapper">
    <div class="hui-inner">
        <div class="hui-content hui-md-output">
            <h1 class="hui-md-title">${article.title}</h1>
            <img src="${article.cover}" class="hui-md-cover">
            <blockquote class="hui-md-summary">${article.summary}</blockquote>
        ${article.htmlContent}
            <a href="/">返回首页</a>
        </div>
    </div>
</div>
<script src="http://114.215.28.105/blog.js"></script>
</body>
</html>
