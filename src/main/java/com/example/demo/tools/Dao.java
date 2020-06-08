package com.example.demo.tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

    public static String getprivatekey(String email) {
        Connection con;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
        String user = "root";
        String password = "toor";
        String privatekey = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            Statement statement = con.createStatement();
            String email1 = "'"+email+"'";
            String sql = "select PRIVATEKEY from tb_user where tb_user.EMAIL="+email1 ;
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
            	privatekey = rs.getString("PRIVATEKEY");
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
            }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        	return privatekey;
        }
    }
    public static String getpublickey(String email) {
        Connection con;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
        String user = "root";
        String password = "toor";
        String privatekey = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            Statement statement = con.createStatement();
            String email1 = "'"+email+"'";
            String sql = "select PUBLICKEY from tb_user where tb_user.EMAIL="+email1 ;
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
            	privatekey = rs.getString("PUBLICKEY");
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
            }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        	return privatekey;
        }
    }
    
    // public static void main(String[] args) {
    // 	String privatekey = null;
    // 	privatekey = getprivatekey("xjx1123115247");
    // 	System.out.println("私钥："+privatekey);
        
    // }
    public static String getEmaile(Integer id) {
        Connection con;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
        String user = "root";
        String password = "toor";
        String email = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            Statement statement = con.createStatement();
            String id1 = "'"+id+"'";
            String sql = "select EMAIL from tb_video where tb_video.id="+id1 ;
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
            	email = rs.getString("EMAIL");
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
            }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        	return email;
        }
    }
    public static String getPath(Integer id) {
        Connection con;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
        String user = "root";
        String password = "toor";
        String path = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            Statement statement = con.createStatement();
            String id1 = "'"+id+"'";
            String sql = "select PATH from tb_video where tb_video.id="+id1 ;
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
            	path = rs.getString("PATH");
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
            }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        	return path;
        }
    }
}