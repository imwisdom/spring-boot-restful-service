package com.example.springbootrestfulservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Integer id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")    //size가 최소 2여야 함
    private String name;
    @Past   //현재 날짜보다 과거만 가능
    private Date joinDate;
}
