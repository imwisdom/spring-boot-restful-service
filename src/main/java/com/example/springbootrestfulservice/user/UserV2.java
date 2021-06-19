package com.example.springbootrestfulservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password"})//ignore하고 싶은 필드 이름을 적음
@NoArgsConstructor
@JsonFilter("UserInfoV2")
public class UserV2 extends User{
    private String grade;
}
