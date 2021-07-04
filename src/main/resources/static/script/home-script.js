  
  $(function() {

    getArticle(0);

    $('body').on('click', '#create', function() {
      $('#submit-cover, #submit-modal').fadeIn(200);
    });
    
    $('#submit-cover').click(function() {
      $('#submit-cover, #submit-modal').fadeOut(200);
    });
  
    $('body').on('click', '#submit-btn', function() {

      let date = new Date();
      
      let data = {
          title: $('#submit-title').val(),
          content: $('#submit-content').val(),
          createdDate: date,
          updatedDate: date,
          userId: $(this).data('submitter')
      };
      
      $.ajax({
        url:'/article',
        type: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json'
      })
      .done(function(data) {
        $('#submit-cover, #submit-modal').fadeOut(200);
        $('#articles').empty();
        getArticle();
      })
      .fail(function() {
        window.alert('正しい結果を得られませんでした。');
      });
    });
  
    $('body').on('click', '.article-edit', function() {
      $('#edit-cover, #edit-modal').fadeIn(200);
      $('#edit-title').attr('value', $(this).closest('.title').find('h4').text());
      $('#edit-content').empty();
      $('#edit-content').val($(this).closest('.articles').find('.content').text());
      $('#edit-btn').attr('data-article-id', $(this).attr('data-article-id'));
    });
    
    $('#edit-cover').click(function() {
      $('#edit-cover, #edit-modal').fadeOut(200);
    });
    
    $('body').on('click', '#edit-btn', function() {

      let date = new Date();
      
      let data = {
        title: $('#edit-title').val(),
        content: $('#edit-content').val(),
        updatedDate: date
      }
      
      $.ajax({
        url: '/article/' + $(this).data('article-id'),
        type: 'PATCH',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json'
      })
      .done(function(data) {
        $('#edit-cover, #edit-modal').fadeOut(200);
        $('#articles').empty();
        getArticle();
      })
      .fail(function() {
        window.alert('正しい結果を得られませんでした。');
      });
    });
  
    $('body').on('click', '.article-delete', function() {
      if(confirm('削除しますか？')) {
        let id = $(this).data('article-id');

        $.ajax({
          url: 'article/delete',
          type: 'POST',
          data: JSON.stringify(id),
          dataType: 'json',
          contentType: 'application/json'
        })
        .done(function() {
          $('#articles').empty();
          getArticle();
        });
      };
    });

    $('body').on('click', '.like-change', function() {

      let data = {
        userId: $('body').data('user-id'),
        articleId: $(this).data('article-id')
      };
      
      $.ajax({
        url: '/like/change',
        type: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json'
      })
      .done(function(count) {
        $('.like-count').filter(function(){
          return($(this).data('article-id') === data.articleId);
        }).text(count);
      });
    });

    $('body').on('click', '.bookmark-change', function() {
      let data = {
        userId: $('body').data('user-id'),
        articleId: $(this).data('article-id')
      };
      
      $.ajax({
        url: '/bookmark/change',
        type: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json'
      })
      .done(function(count) {
        $('.bookmark-count').filter(function(){
          return($(this).data('article-id') === data.articleId);
        }).text(count);
      });
    });

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

    function getArticle(i){
      $.getJSON("article/get" + $('body').data('user-id') + "?page="+ i, function(data) {
        let size = $(data).length;
        for(let i = 0; i <= size-1; i++) {
          $('#articles').append(
            $('<div class="mb-5 rounded-top articles"></div>').append(
              $('<div class="py-1 px-2 d-flex align-items-center rounded-top bg-dark text-white title"></div>').append(
                $('<div class="flex-grow-1"></div>').append(
                  $('<h4 style="margin-bottom:0px;">' + data[i].title + '</h4>')
                ),
                $('<div class="d-flex justify-content-end dropdown"></div>').append(
                  $('<button class="btn dropdown-toggle text-white" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-expanded="false"></button>'),
                  $('<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton"></ul>').append(
                    $('<li class="dropdown-item article-edit" data-article-id="'+ data[i].id +'">編集</li>'),
                    $('<li class="dropdown-item article-delete" data-article-id="'+ data[i].id +'">削除</li>')
                  )
                )
              ),
              $('<div class="border border-secondary"</div>').append(
                $('<div class="p-3 content"></div>').append(data[i].content),
                $('<div class="p-2 d-flex align-items-center"></div>').append(
                  $('<i class="fas fa-heart fa-lg like-change ml-2 my-1 text-danger" data-article-id='+ data[i].id +'></i>'),
                  $('<span class="like-count ml-1 my-1" data-article-id='+ data[i].id +'></span>'),
                  $('<i class="fas fa-star fa-lg ml-4 my-1 text-warning bookmark-change" data-article-id='+ data[i].id +'></i>'),
                  $('<span class="bookmark-count ml-1 my-1" data-article-id='+ data[i].id +'></span>'),
                  $('<i class="fab fa-twitter fa-lg ml-4 my-1 text-info"></i>')
                )
              )
            )
          )
          $.getJSON("like/count/"+ data[i].id, function(count) {
            $('.like-count').filter(function(){
              return($(this).data('article-id') === data[i].id);
            }).text(count);
          });
          $.getJSON("bookmark/count/"+ data[i].id, function(count) {
            $('.bookmark-count').filter(function(){
              return($(this).data('article-id') === data[i].id);
            }).text(count);
          });
        }
        $.getJSON("total-pages" + $('body').data('user-id'), function(totalPages) {
          $('#more').data('total-pages', totalPages);
        })
      });
    };
  });