package cn.edu.csu.ycepspring.mapper;

import cn.edu.csu.ycepspring.entity.AuthLocal;
import cn.edu.csu.ycepspring.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    AuthLocal selectPasswordByUsername(@Param("username") String username);

    int selectRoleIdByAccount(@Param("account") int account);

    void createUser(User user);

    List<String> selectAllUsername();

    void insertAuthLocal(AuthLocal auth);
}
