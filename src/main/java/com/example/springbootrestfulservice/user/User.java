package com.example.springbootrestfulservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password"})//ignore하고 싶은 필드 이름을 적음
@JsonFilter("UserInfo")
public class User {
    private Integer id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")    //size가 최소 2여야 함
    private String name;
    @Past   //현재 날짜보다 과거만 가능
    private Date joinDate;

    //@JsonIgnore //client에게 보여지는 json 값을 안보이도록 지정
    private String password;
    //@JsonIgnore
    private String ssn;     //주민번호
}
