$(function() {
  let searchParam = $('body').data('search-param');

  $('body').on('click', '#more', function() {
    let i = $(this).data('i');
    let totalPages = $(this).data('total-pages');
    getArticle(i);
    i++;
    if(totalPages == i) {
      $(this).fadeOut();
    };
    $(this).data('i', i);
  });

  function getArticle(i) {
    $.getJSON("/search/articles/pagination?page=" + i + "&param=" + searchParam, function(data) {
      let count = 100;
      let size = $(data).length;
      for(let i = 0; i <= size-1; i++) {
        let content = data[i].content;
        let contentLength = content.length;
        
        $('#search-articles-list').append(
          $('<div class="border-bottom border-secondary"></div>').append(
            $('<div class="position-relative"></div>').append(
              $('<a href="/oneArticle/' + data[i].id + '" class="d-block position-absolute w-100 h-100"></a>'),
              $('<div class="p-3"></div>').append(
                $('<h3>' + data[i].title + '</h3>'),
                $('<div class="text-break content' + i + '"></div>')
              )
            )
          )
        )
        if(contentLength > count) {
          let showContent = content.substring(0, count);
          let insertContent = showContent += '…';
          $('.content' + i).text(insertContent);
        } else {
          $('.content' + i).text(content);
        };
      };
    });
  };
});