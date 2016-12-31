package demo.security.domain;

/**
 * Created by zhengjianhui on 16/10/7.
 * 处理 filter Request 中的参数
 */
public class LoginInfo {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
