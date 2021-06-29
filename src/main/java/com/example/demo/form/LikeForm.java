package com.example.demo.form;

import java.io.Serializable;

import com.example.demo.model.Article;
import com.example.demo.model.Likes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer userId;
	
    private String articleId;
    
    public Likes toEntity() {
    	
    	Likes like = new Likes();
    	Article article = new Article();
    	article.setId(Integer.parseInt(articleId));
    	like.setUserId(userId);
    	like.setArticleId(article);
    	
    	return like;
    }

}
