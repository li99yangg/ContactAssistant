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
	  
	public static  String NAME="root";//��¼��  
	public static  String PASSWORD="ly970909";//����
	public Connection TheSqlConnection() throws InterruptedException  
	{  
		System.out.println("�Ƿ�ʹ�ñ������ݿ⣿Y/N");
		if(sc.next().equals("Y")){
			
			try {
				System.out.print("�����뱾�����ݿ��IP��");
				IP = br.readLine();
				System.out.print("�����뱾�����ݿ�Ķ˿ڣ�");
				PORT = br.readLine();
				System.out.print("���������ݿ��û�����");
				NAME = br.readLine();
				System.out.print("�������û����룺��");
				PASSWORD = br.readLine();
			} catch (IOException e) {
				System.out.println("�������������");
				new Thread().sleep(10000);
				System.exit(0);
			}
			
		}
		 String URL="jdbc:mysql://"+IP+":"+PORT+"/contactassistant?";//���ݿ������ַ����������deomΪ���ݿ���
			System.out.println(URL); 
	    //1.��������  
		Connection conn = null;
		try {  
	        Class.forName("com.mysql.jdbc.Driver");  
	    } catch (ClassNotFoundException e) {  
	        System.out.println("δ�ܳɹ������������������Ƿ�����������");  
	        new Thread().sleep(10000);
	                    //���һ��println��������������쳣������Ƿ����������������������ַ����Ƿ����  
	        System.exit(0);  
	    }   
	    try {  
	        conn = DriverManager.getConnection(URL, NAME, PASSWORD);  
	            System.out.println("��ȡ���ݿ����ӳɹ���");  
	    } catch (SQLException e) {  
	        System.out.println("��ȡ���ݿ�����ʧ�ܣ�");  
	        new Thread().sleep(10000);
	                    //���һ��println���������ʧ�ܣ���������ַ������ߵ�¼���Լ������Ƿ����  
	       	System.exit(0);
	    }
	return conn;    
	}

		
}
