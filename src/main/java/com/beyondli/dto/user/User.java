package com.beyondli.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by beyondLi
 * Date 2018/3/28 16:39
 * Desc .
 */
@ApiModel(value = "用户信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ApiModelProperty(value = "用户名", example = "beyondLi")
    @NotBlank(message = "TEST_0002")
    private String name;
    @ApiModelProperty(value = "密码", example = "123456")
    @NotBlank(message = "TEST_0003")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
