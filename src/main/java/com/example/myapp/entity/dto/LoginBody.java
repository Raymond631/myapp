package com.example.myapp.entity.dto;

import com.example.myapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginBody {
    private User user;
    private String id;
    private int code;
}
