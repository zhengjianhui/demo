package demo.shiro.realm;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by zhengjianhui on 17/1/14.
 */
public class ShiroMd5 {

    // 盐 salt
    private static final String salt = "demo";

    //原始密码
    public static String md5Password(String source) {
//
//        //散列次数
//        int hashInterration=1;
//        //MD5（****）一次
//        Md5Hash md5Hash=new Md5Hash(source, salt, hashInterration);
//        return md5Hash.toString();
//        //散列加密2
//        // SimpleHash simpleHash=new SimpleHash("md5",source,salt,hashInterration);

        // 四个参数分别标识算法名称，散列对象，散列使用的salt值，散列次数。
        return new SimpleHash("md5",source, ByteSource.Util.bytes(salt),2).toHex();
    }
}
