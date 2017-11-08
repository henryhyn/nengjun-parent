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
  <header class='header'>
    <div class='header-inner'>
      <div class='site-brand-wrapper'>
        <div class='site-meta'>
          <div class='custom-logo-site-title'>
            <a href='/' class='brand'>
              <span class='logo-line-before'><i></i></span>
              <span class='site-title'>开源书屋</span>
              <span class='logo-line-after'><i></i></span>
            </a>
          </div>
          <p class='site-subtitle'>问渠那得清如许？为有源头活水来。</p>
        </div>
      </div>
      <nav class='site-nav'>
        <ul class='menu'>
          <li class='menu-item menu-item-home'>
            <a href='/'>
              <span class='glyphicon glyphicon-home'></span> 首页
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </header>
  <main class='main'>
    <div class='main-inner'>
      <div class='content-wrap'>
        <div class='content'>
          <div class='posts-expand'>
          <#list data.list as article>
            <article class='post post-type-normal'>
              <header class='post-header'>
                <h1 class='post-title'>
                  <a href='/articles/${article.id}' class='post-title-link'>${article.title}</a>
                </h1>
              </header>
              <img src='${article.cover}'/>
              <blockquote>${article.summary}</blockquote>
            </article>
          </#list>
          </div>
          <nav class='pagination'>
            <span class='page-number current'>${data.page}</span>
            <a class='extend next' rel='next' href='/?page=${data.page+1}'>
              <span class='glyphicon glyphicon-chevron-right'></span>
            </a>
          </nav>
        </div>
      </div>
    </div>
  </main>
  <footer id='footer' class='footer'>
    <div class='footer-inner'>
      <div class='copyright'>
        &copy;
        <span>2017</span>
        <span class='with-love'>
          <span class='glyphicon glyphicon-heart'></span>
        </span>
        <span class='author'>开源书屋</span>
      </div>
    </div>
  </footer>
</div>
</body>
</html>
