package com.main.ly;
import java.sql.*; 

import com.main.ly.User.Grant;


public class DoMain {
	 
	public static Connection conn = null;
	public static Login login;
	public static DataBaseConsole console = new DataBaseConsole();
	public static void main(String[] args) {
		try {
			conn = new SQLConnection().TheSqlConnection();
		} catch (InterruptedException e1) {
			System.out.println("线程出现错误");
			return;
		}
		login  = new Login();
		Checking();
		
		   //数据库打开后就要关闭  
	    if(conn!=null)  
	    {  
	        try {  
	            conn.close();  
	        } catch (SQLException e) {   
	            e.printStackTrace();  
	            conn=null;  
	        }  
	    }  
	}

	public static boolean isConnected()
		{
			if(conn != null) return true;
			else return false;
		}
	public static void Checking()
	{
		try {
			Statement stmt = conn.createStatement();
			Grant grant = null;
			do{//检验密码是否正确
				ResultSet rs = stmt.executeQuery("Select user_name,user_pass,user_grant From users Where user_name ='"+login.user+
						"'And user_pass = '"+login.pass+"';");//execctueQuery方法一般用于执行一个select语句
				grant = console.PassCheck(rs,login.user,login.pass);
				if(grant == null){
					System.out.println("密码错误，请重新输入");
					login = new Login();
				}
			}while(grant==null);
			System.out.println("密码正确");
			User user = new User(login.user,login.pass,grant);
			Menu menu = new Menu(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


