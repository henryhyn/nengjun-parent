<nav class='pagination'>
<#if (data.page>1)>
  <a class='extend prev' href='/?page=${data.page-1}'>
    <span class='glyphicon glyphicon-chevron-left'></span>
  </a>
</#if>
<#if (data.page>2)>
  <a class='page-number' href='/?page=${data.page-2}'>${data.page-2}</a>
</#if>
<#if (data.page>1)>
  <a class='page-number' href='/?page=${data.page-1}'>${data.page-1}</a>
</#if>
  <span class='page-number current'>${data.page}</span>
<#if (data.page*data.pageSize<data.total)>
  <a class='page-number' href='/?page=${data.page+1}'>${data.page+1}</a>
</#if>
<#if ((data.page+1)*data.pageSize<data.total)>
  <a class='page-number' href='/?page=${data.page+2}'>${data.page+2}</a>
</#if>
<#if (data.page*data.pageSize<data.total)>
  <a class='extend next' href='/?page=${data.page+1}'>
    <span class='glyphicon glyphicon-chevron-right'></span>
  </a>
</#if>
</nav>