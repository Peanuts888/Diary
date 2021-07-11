$(function() {
  let id = $('body').data('article-id');
  let loginUserId = $('body').data('login-user-id');
  let SERVER_URL = $('body').data('server-url');
  
  $.getJSON("/like/count/" + id, function(count) {
    $('.like-count').text(count);
  });
  $.getJSON(SERVER_URL + "/bookmark/count/" + id, function(count) {
    $('.bookmark-count').text(count);
  });
  
  $('body').on('click', '.like-change', function() {

      let data = {
        userId: loginUserId,
        articleId: id
      };
      
      $.ajax({
        url: 'http://localhost:8080/like/change',
        type: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json'
      })
      .done(function(count) {
        $('.like-count').text(count);
      });
    });

    $('body').on('click', '.bookmark-change', function() {
      let data = {
        userId: loginUserId,
        articleId: id
      };
      
      $.ajax({
        url: 'http://localhost:8080/bookmark/change',
        type: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json'
      })
      .done(function(count) {
        $('.bookmark-count').text(count);
      });
    });
});