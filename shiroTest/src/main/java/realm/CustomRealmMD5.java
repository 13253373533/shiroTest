package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class CustomRealmMD5 extends AuthenticatingRealm {

    @Override
    public void setName(String name){
        name="customRealm";
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       String username= (String) token.getPrincipal();
        String credentials = new String ((char[])token.getCredentials());
        String salt="zxcvbn";
        SimpleHash md5 = new SimpleHash("MD5", credentials, salt);
        String password=md5.toString();

       SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(salt),this.getName());
       return authenticationInfo;
    }
}
