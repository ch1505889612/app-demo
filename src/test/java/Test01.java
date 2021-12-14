import com.ch.jdbc.utils.JdbcUtils;
import org.junit.Test;

import java.sql.*;

/**
 * 预编译处理:PreparedStatement
 *   好处:
 *     1.预编译
 *     2.防止sql注入
 */
public class Test01 {


    public static void main(String[] args) {
        //System.out.println(insert(1,"李四",1234)?"成功":"失败");
       // System.out.println(insert(2,"王五",4321)?"成功":"失败");

        System.out.println(login("李四","1234")?"登录成功":"登录失败");
    }


    /**
     * 添加一个用户
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Test
    public static boolean insert(Integer id,String username,Integer password){
        boolean flag=false;
        Connection connection=null;

        PreparedStatement statement=null;
        try {
            connection = JdbcUtils.getConnection();
            //传递的数据
            String sql="insert into t_user values (?,?,?)";
            //预处理快
            statement= connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,username);
            statement.setInt(3,password);
            int i = statement.executeUpdate();
            if (i>0){
                flag=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JdbcUtils.close(connection,statement);
        }
        return flag;
    }

    /**
     *模拟登录
     */
    public static boolean login(String username, String password) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from t_user where username=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getInt(3));
            }
            if (null != resultSet) {
                flag = true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }

        return flag;
    }

}
