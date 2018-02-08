package com.main.ly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class SQLConnection {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static Scanner sc = new Scanner(System.in);
	public static  String IP = "localhost";
	public static  String PORT = "3306";
	  
	public static  String NAME="root";//登录名  
	public static  String PASSWORD="ly970909";//密码
	public Connection TheSqlConnection() throws InterruptedException  
	{  
		System.out.println("是否使用本地数据库？Y/N");
		if(sc.next().equals("Y")){
			
			try {
				System.out.print("请输入本地数据库的IP：");
				IP = br.readLine();
				System.out.print("请输入本地数据库的端口：");
				PORT = br.readLine();
				System.out.print("请输入数据库用户名：");
				NAME = br.readLine();
				System.out.print("请输入用户密码：：");
				PASSWORD = br.readLine();
			} catch (IOException e) {
				System.out.println("输入错误，请重试");
				new Thread().sleep(10000);
				System.exit(0);
			}
			
		}
		 String URL="jdbc:mysql://"+IP+":"+PORT+"/contactassistant?";//数据库连接字符串，这里的deom为数据库名
			System.out.println(URL); 
	    //1.加载驱动  
		Connection conn = null;
		try {  
	        Class.forName("com.mysql.jdbc.Driver");  
	    } catch (ClassNotFoundException e) {  
	        System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");  
	        new Thread().sleep(10000);
	                    //添加一个println，如果加载驱动异常，检查是否添加驱动，或者添加驱动字符串是否错误  
	        System.exit(0);  
	    }   
	    try {  
	        conn = DriverManager.getConnection(URL, NAME, PASSWORD);  
	            System.out.println("获取数据库连接成功！");  
	    } catch (SQLException e) {  
	        System.out.println("获取数据库连接失败！");  
	        new Thread().sleep(10000);
	                    //添加一个println，如果连接失败，检查连接字符串或者登录名以及密码是否错误  
	       	System.exit(0);
	    }
	return conn;    
	}

		
}
