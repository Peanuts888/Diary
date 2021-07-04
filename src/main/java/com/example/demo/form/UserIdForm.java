package com.example.demo.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer userId;

}
