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
      <div class='site-nav-toggle'>
        <button id='site-nav-btn'>
          <span class='btn-bar'></span>
          <span class='btn-bar'></span>
          <span class='btn-bar'></span>
        </button>
      </div>
    </div>
    <nav id='site-nav-menu' class='site-nav'>
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

<script type='text/javascript'>
  document.getElementById('site-nav-btn').onclick = function () {
    var el = document.getElementById('site-nav-menu');
    if ('block' === el.style.display) {
      el.className = 'site-nav';
      el.style.display = 'none';
    } else {
      el.className = 'site-nav site-nav-on';
      el.style.display = 'block';
    }
  };
</script>