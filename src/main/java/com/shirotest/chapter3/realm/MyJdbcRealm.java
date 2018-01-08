package com.shirotest.chapter3.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyJdbcRealm extends AuthorizingRealm{
    protected DataSource dataSource;
    protected String authenticationQuery = "select password from users where username = ?";
    protected String userRolesQuery = "select role_name from user_roles where username = ?";
    protected String permissionsQuery = "select permission from roles_permissions where role_name = ?";
    protected boolean permissionsLookupEnabled = false;
    protected MyJdbcRealm.SaltStyle saltStyle;

    public MyJdbcRealm(){
        this.saltStyle = MyJdbcRealm.SaltStyle.NO_SALT;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setAuthenticationQuery(String authenticationQuery) {
        this.authenticationQuery = authenticationQuery;
    }

    public void setUserRolesQuery(String userRolesQuery) {
        this.userRolesQuery = userRolesQuery;
    }

    public void setPermissionsQuery(String permissionsQuery) {
        this.permissionsQuery = permissionsQuery;
    }

    public void setPermissionsLookupEnabled(boolean permissionsLookupEnabled) {
        this.permissionsLookupEnabled = permissionsLookupEnabled;
    }

    public void setSaltStyle(SaltStyle saltStyle) {
        this.saltStyle = saltStyle;
        if (saltStyle== SaltStyle.COLUMN && this.authenticationQuery.equals("select password from users where username = ?")){
             this.authenticationQuery = "select password,password_salt from users where username = ?";
        }
    }

    //    表示根据用户身份获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }
//    示获取身份验证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (username==null){
            throw new AccountException("null username are not allowed to this realm.");
        }else {
            SimpleAuthenticationInfo info = null;
            Connection conn = null;
            try{
                String salt;
                try {
                    conn = dataSource.getConnection();
                    String password = null;
                    salt = null;
                    switch(this.saltStyle){
                        case NO_SALT:
                            password = this.getPasswordByUser(conn,username)[0];
                        case CRYPT:
                            throw new ConfigurationException("Not implemented yet");
                        case COLUMN:
                            String[] queryResults = this.getPasswordByUser(conn, username);
                            password = queryResults[0];
                            salt = queryResults[1];
                            break;
                        case EXTERNAL:
                            password = this.getPasswordByUser(conn, username)[0];
                            salt = this.getSaltByUser(username);
                    }

                    if (password == null) {
                        throw new UnknownAccountException("No account found for user [" + username + "]");
                    }

                    info = new SimpleAuthenticationInfo(username,password,this.getName());
                    if (salt!=null){
                        info.setCredentialsSalt(ByteSource.Util.bytes(salt));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }finally {
                JdbcUtils.closeConnection(conn);
            }
            return info;
        }
    }

    private String getSaltByUser(String username) {
        return username;
    }

    private String[] getPasswordByUser(Connection conn, String username) {
        boolean slatEnable = false;
        String[] result;
        switch (this.saltStyle){
            case NO_SALT:
            case CRYPT:
            case EXTERNAL:
                result = new String[1];
                break;
            case COLUMN:
            default:
                result = new String[2];
                slatEnable = true;
        }
        PreparedStatement ps =null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(this.authenticationQuery);
            ps.setString(1,username);
            rs = ps.executeQuery();
            for(boolean foundResult = false; rs.next(); foundResult = true) {
                if (foundResult) {
                    throw new AuthenticationException("More than one user row found for user [" + username + "]. Usernames must be unique.");
                }

                result[0] = rs.getString(1);
                if (slatEnable) {
                    result[1] = rs.getString(2);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(ps);
        }
        return result;
    }

    public static enum SaltStyle{
        NO_SALT,
        CRYPT,
        COLUMN,
        EXTERNAL;

        private SaltStyle(){
        }
    }


}
