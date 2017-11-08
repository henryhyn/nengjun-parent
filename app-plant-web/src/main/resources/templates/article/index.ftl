<!doctype html>
<html>
<head>
  <meta charset='UTF-8'>
  <meta http-equiv='X-UA-Compatible' content='IE=edge'>
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
  <meta name='viewport' content='width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0'>
  <meta name='apple-mobile-web-app-status-bar-style' content='black'>
  <meta name='format-detection' content='telephone=no'>
  <meta name='format-detection' content='email=no'>
  <meta name='apple-mobile-web-app-capable' content='yes'>
  <link href='/static/favicon.ico' rel='shortcut icon'>
  <link href='/static/favicon.ico' rel='apple-touch-icon'>
  <title>开源书屋</title>
  <link href='/static/blog.css' rel='stylesheet'>
</head>
<body>
<div class='container one-collumn sidebar-position-left page-post-detail'>
  <div class='headband'></div>
<#include "./header.ftl">
  <main class='main'>
    <div class='main-inner'>
      <div class='content-wrap'>
        <div class='content'>
          <section class='posts-expand'>
          <#list data.list as article>
            <article class='post post-type-normal'>
              <header class='post-header'>
                <h1 class='post-title'>
                  <a href='/articles/${article.id}' class='post-title-link'>${article.title}</a>
                </h1>
              </header>
              <div class='post-meta'>
                <img src='${article.cover}'/>

                <p>${article.summary}</p>
              </div>
            </article>
          </#list>
          </section>
        <#include "./pagination.ftl">
        </div>
      </div>
    </div>
  </main>
<#include "./footer.ftl">
</div>
</body>
</html>
