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
			System.out.println("�̳߳��ִ���");
			return;
		}
		login  = new Login();
		Checking();
		
		   //���ݿ�򿪺��Ҫ�ر�  
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
			do{//���������Ƿ���ȷ
				ResultSet rs = stmt.executeQuery("Select user_name,user_pass,user_grant From users Where user_name ='"+login.user+
						"'And user_pass = '"+login.pass+"';");//execctueQuery����һ������ִ��һ��select���
				grant = console.PassCheck(rs,login.user,login.pass);
				if(grant == null){
					System.out.println("�����������������");
					login = new Login();
				}
			}while(grant==null);
			System.out.println("������ȷ");
			User user = new User(login.user,login.pass,grant);
			Menu menu = new Menu(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


