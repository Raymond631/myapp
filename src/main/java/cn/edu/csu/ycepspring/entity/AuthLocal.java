package cn.edu.csu.ycepspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLocal {
    private int id;
    private int account;
    private String username;
    private String password;

    public AuthLocal(int account, String username, String password) {
        this.account = account;
        this.username = username;
        this.password = password;
    }
}
