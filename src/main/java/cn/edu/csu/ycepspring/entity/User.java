package cn.edu.csu.ycepspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int account;
    private int roleId;
    private String phone;
    private String email;
    private String sex;
    private String school;
    private String description;
}
