package com.example.demo.form;

import java.io.Serializable;

import com.example.demo.model.Article;
import com.example.demo.model.Bookmark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;

	private Integer articleId;

	public Bookmark toEntity() {

		Bookmark bookmark = new Bookmark();
		Article article = new Article();
		article.setId(articleId);
		bookmark.setUserId(userId);
		bookmark.setArticleId(article);

		return bookmark;
	}

}
