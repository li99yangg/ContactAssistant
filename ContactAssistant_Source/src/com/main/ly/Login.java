package com.main.ly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import com.main.ly.User.Grant;

public class Login {
	protected String user="null";
	protected String pass="null";
	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public Scanner sc = new Scanner(System.in);
	public Login() {
		while(true){
		System.out.println();
		System.out.println("\t\t欢迎使用通讯录管理助手");
		System.out.println("\t\t**************");
		System.out.println("\t\t1.登录");
		System.out.println("\t\t2.注册");
		System.out.println("\t\t**************");
		int choice = sc.nextInt();
		if(choice == 1){
		System.out.println();
		System.out.println("\t\t欢迎使用通讯录管理助手");
		System.out.println("\t\t**************");
		System.out.println("\t\t请输入账号：");
		try {
			user = br.readLine();
		} catch (IOException e) {
			System.out.println("输入错误，请重新输入");
			continue;
		}
//		System.out.println(user);
		System.out.println();
		System.out.println("\t\t欢迎使用通讯录管理助手");
		System.out.println("\t\t**************");
		System.out.println("\t\t请输入密码：");
		try {
			pass = br.readLine();
		} catch (IOException e) {
			System.out.println("输入错误，请重新输入");
		continue;
		}
		System.out.println("\t\t**************");
		if(user.length()>15||user.length()<4||pass.length()>16||pass.length()<6){

			System.out.println("您的用户名长度应在4~15个字符内，密码长度应在6~16位之间，请重新输入");
			continue;
		}
//		System.out.println(pass);
		break;
		}
		else if(choice == 2){
			Register();
		}
		else
			System.out.println("\t\t输入错误，请重试");
		}
	}
	private void Register()  {
		User user  = new User();
		String name = null,password = null;
		boolean flag = false;
		System.out.println();
		do{
			System.out.println("\t\t请输入您的用户名：");
			try {
				name = br.readLine();
			} catch (IOException e) {
				System.out.println("输入错误，请重新输入");
				continue;
			}
			flag = false;
			if(name.length()>15||name.length()<4) 
			{
			System.out.println("您的用户名长度应在4~15个字符内，请重新输入");
			flag = true;
			}
		}while(flag);
		user.setName(name);
		
		do{
			System.out.println("\t\t**************");
			System.out.print("\t\t请输入您的密码：");
			try {
				password = br.readLine();
			} catch (IOException e) {
				System.out.println("输入错误，请重新输入");
				continue;
			}
			flag = false;
			if(password.length()>16||password.length()<6) 
				{
				System.out.println("您的密码长度应在6~16位之间，请重新输入");
				flag = true;
				}
		}while(flag);
		user.setPassword(password);
		user.setGrant(Grant.ordinary);
		user.UpdateInfo();
		System.out.println("注册成功，请登录系统");
		return;
	}
}
