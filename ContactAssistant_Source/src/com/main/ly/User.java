package com.main.ly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class User implements User_Dos {
	public Scanner sc = new Scanner(System.in);
	public enum Grant{root,admin,ordinary};
	private String name = null;
	private String password = null;
	private Grant grant = Grant.ordinary;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Grant getGrant() {
		return grant;
	}
	public void setGrant(Grant grant) {
		this.grant = grant;
	}
	public User(){
		
	}
	public User (String name,String password,Grant grant)
	{
		this.name = name;
		this.password = password;
		this.grant = grant;
		System.out.println(grant);
	}
	public void UpdateInfo()
	{
		try { 
			DoMain.conn.createStatement().executeUpdate("Insert Into Users (user_name,user_pass)	Values('"+this.name+"','"+this.password+"'); ");
		} catch (SQLException e) {
			System.out.println("用户名已注册，请重新输入");
			e.printStackTrace();
		}
	}
	@Override
	public void AddContacter() {
		
	}
	@Override
	public void DeleteContacter() {
		
	}
	@Override
	public void UpdateContacter()  {
		
	}
	@Override
	public void ShowContacter()   {
		
		try {
			DoMain.console.dispResultSet(DoMain.conn.createStatement().executeQuery("Select * From contact Where contact_from ='"+name+"';"));
		} catch (SQLException e) {
			System.out.println("查找失败，请重试");
			return;
		}
	}
	@Override
	public void SearchContacter()  {
		
	}
	@Override
	public void UpdatePassword()  {
		
	}
	@Override
	public void ExitSystem() {
		if(DoMain.conn!=null)  
	    {  
	        try {  
	        	DoMain.conn.close();  
	        } catch (SQLException e) {   
	            e.printStackTrace();  
	            DoMain.conn=null;  
	        }  
	    }  
		System.out.println("感谢您的使用，再见");
		System.exit(0);
	}
	
	
}
