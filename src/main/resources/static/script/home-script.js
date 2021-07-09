  
  $(function() {
    let otherUserId = $('body').data('other-user-id');
    let loginUserId = $('body').data('login-user-id');

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
          userId: loginUserId
      };
      
      $.ajax({
        url:'article',
        type: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json'
      })
      .done(function() {
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
        id: $(this).data('article-id'),
        title: $('#edit-title').val(),
        content: $('#edit-content').val(),
        updatedDate: date
      }
      
      $.ajax({
        url: 'article',
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
          url: 'article',
          type: 'DELETE',
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
        userId: loginUserId,
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
        userId: loginUserId,
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
      $.getJSON("article/" + otherUserId + "?page="+ i, function(data) {
        let size = $(data).length;
        for(let i = 0; i <= size-1; i++) {
          let id = data[i].id;
          let title = data[i].title;
          let createrId = data[i].userId;
          let content = data[i].content;
          let contentSplit = content.split(/\r\n|\r|\n/);
          let contentLength = contentSplit.length;
          let contentHtml = '<p class="mb-0">';
          $.each(contentSplit, function(index, val) {
            if(index !== contentLength -1) {
              contentHtml += val + '</br>';
            } else {
              contentHtml += val + '</p>';
            }
          });
          $('#articles').append(
            $('<div class="mb-5 rounded-top articles"></div>').append(
              $('<div class="py-2 px-2 d-flex align-items-center rounded-top bg-dark text-white title"></div>').append(
                $('<div class="flex-grow-1"></div>').append(
                  $('<h4 style="margin-bottom:0px;"></h4>').append(title)
                ),
                $('<div id="dropdown-' + i + '"></div>').append(
                  $('<div class="d-flex justify-content-end dropdown"></div>').append(
                    $('<button class="btn dropdown-toggle text-white py-0" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-expanded="false"></button>'),
                    $('<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton"></ul>').append(
                      $('<li class="dropdown-item article-edit" data-article-id="'+ id +'">編集</li>'),
                      $('<li class="dropdown-item article-delete" data-article-id="'+ id +'">削除</li>')
                    )
                  )
                )
              ),
              $('<div class="border border-secondary"</div>').append(
                $('<div class="p-3 text-break content"></div>').append(contentHtml),
                $('<div class="p-2 d-flex align-items-center"></div>').append(
                  $('<i class="fas fa-heart fa-lg like-change ml-2 my-1 text-danger" data-article-id='+ id +'></i>'),
                  $('<span class="like-count ml-1 my-1" data-article-id='+ id +'></span>'),
                  $('<i class="fas fa-star fa-lg ml-4 my-1 text-warning bookmark-change" data-article-id='+ id +'></i>'),
                  $('<span class="bookmark-count ml-1 mr-4 my-1" data-article-id='+ id +'></span>'),
                  $('<a href="https://twitter.com/share?ref_src=twsrc%5Etfw" class="twitter-share-button ml-1 my-1" data-url="http://localhost:8080/oneArticle' + id + '" data-text="' + title + '" data-size="large" data-show-count="false"></a>')
                )
              )
            )
          )
          $.getJSON("like/count/"+ id, function(count) {
            $('.like-count').filter(function(){
              return($(this).data('article-id') === id);
            }).text(count);
          });
          $.getJSON("bookmark/count/"+ id, function(count) {
            $('.bookmark-count').filter(function(){
              return($(this).data('article-id') === id);
            }).text(count);
          });
          if(loginUserId != createrId) {
            $('#dropdown-' + i).hide();
          };
        };
        $.getJSON("article/total-pages" + otherUserId, function(totalPages) {
          $('#more').data('total-pages', totalPages);
        })
        
      });
    };
  });