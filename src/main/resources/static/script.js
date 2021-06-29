  
  $(function() {

    getArticle();

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
          createUser: $(this).attr('data-submitter')
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
      $('#edit-cover, #edit-modal').fadeTo(200, 1);
      $('#edit-title').attr('value', $(this).closest('.title').find('h4').text());
      $('#edit-content').empty();
      $('#edit-content').val($(this).closest('.articles').find('.content').text());
      $('#edit-btn').attr('data-article-id', $(this).attr('data-article-id'));
    });
    
    $('#edit-cover').click(function() {
      $('#edit-cover, #edit-modal').fadeTo(200, 0).fadeOut(1000);
    });
    
    $('body').on('click', '#edit-btn', function() {

      let date = new Date();
      
      let data = {
        title: $('#edit-title').val(),
        content: $('#edit-content').val(),
        updatedDate: date
      }
      
      $.ajax({
        url: '/article/' + $(this).attr('data-article-id'),
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
        $.ajax({
          url: '/article/' + $(this).attr('data-article-id'),
          type: 'DELETE'
        })
        .done(function(data) {
          $('#edit-cover, #edit-modal').fadeOut(200);
          $('#articles').empty();
          getArticle();
        });
      };
    });

    $('body').on('click', '.like-on', function() {

      let data = {
        userId: $('body').attr('data-user-id'),
        articleId: $(this).attr('data-article-id')
      };
      
      $.ajax({
        url: '/like/on',
        type: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json'
      })
      .done(function(data) {
      });
    });

    $('body').on('click', '.fa-star', function() {
    });

    function getArticle(){
      $.getJSON("get/articles", function(data) {
        let size = $(data).length;
        for(let i = size-1; i >= 0; i--) {
          $('#articles').append(
            $('<div class="mb-5 border rounded-top articles"></div>').append(
              $('<div class="p-2 d-flex align-items-center border-bottom rounded-top bg-dark text-white title"></div>').append(
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
              $('<div class="p-3 content"></div>').append(data[i].content),
              $('<div class="p-2 d-flex align-items-center"></div>').append(
                $('<i class="fas fa-heart fa-lg like-on ml-2 my-1 text-danger" data-article-id='+ data[i].id +'></i>'),
                $('<span class="like-count ml-1 my-1"></span>'),
                // $('<i class="far fa-heart like-off m-1"></i>'),
                $('<i class="fas fa-star fa-lg ml-4 my-1"></i>'),
                $('<span class="bookmark-count ml-1 my-1"></span>'),
                $('<i class="fab fa-twitter fa-lg ml-4 my-1 text-info"></i>')
              )
            )
          )
          $.getJSON("like/count/"+ data[i].id, function(data) {
            $('.like-count').text(data);
          });
        };
      });
    };
  });