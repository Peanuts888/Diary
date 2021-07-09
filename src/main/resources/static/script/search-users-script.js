$(function() {
  let searchParam = $('body').data('search-param');
  let loginUserId = $('body').data('login-user-id');

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
    $.getJSON("/search/users/pagination?page=" + i + "&param=" + searchParam, function(data) {
      let count = 75;
      let size = $(data).length;
      for(let i = 0; i <= size-1; i++) {
        let userId = data[i].id;
        let username = data[i].username;
        let displayName = data[i].displayName;
        let profile = data[i].profile;
        let profileLength = profile.length;
        let icon = data[i].icon;
        if(userId != loginUserId) {
          $('#search-users-list').append(
            $('<div class="border-bottom border-secondary"></div>').append(
              $('<div class="position-relative"></div>').append(
                $('<a href="/' + userId + '" class="d-block position-absolute w-100 h-100"></a>'),
                $('<div class="d-flex p-2"></div>').append(
                  $('<div class="mr-2" style="flex-basis: 10%;"></div>').append(
                    $('<div class="wrapper-1-1"></div>').append(
                      $('<div class="icon-parent icon' + i + ' d-flex justify-content-center"></div>')
                    )
                  ),
                  $('<div style="flex-basis: 90%;"></div>').append(
                    $('<div style="font-size: 150%;">' + displayName + '</div>'),
                    $('<div>' + username + '</div>'),
                    $('<div class="profile' + i + '"></div>')
                  )
                )
              )
            )
          )
        }
        if(icon != null) {
          $('.icon' + i).append(
            $('<img alt="プロフィール画像" src="/show/icon/' + userId + '" class="w-100 h-100 rounded-circle object-fit">')
          );
        } else {
          $('.icon' + i).append(
            $('<span class="w-100 text-center"></span>').append(
              $('')
            )
          );
        }
        if(profileLength > count) {
          let showProfile = profile.substring(0, count);
          let insertProfile = showProfile += '…';
          $('.profile' + i).text(insertProfile);
        } else {
          $('.profile' + i).text(profile);
        }
      };
    });
  };
});