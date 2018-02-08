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
		System.out.println("\t\t��ӭʹ��ͨѶ¼��������");
		System.out.println("\t\t**************");
		System.out.println("\t\t1.��¼");
		System.out.println("\t\t2.ע��");
		System.out.println("\t\t**************");
		int choice = sc.nextInt();
		if(choice == 1){
		System.out.println();
		System.out.println("\t\t��ӭʹ��ͨѶ¼��������");
		System.out.println("\t\t**************");
		System.out.println("\t\t�������˺ţ�");
		try {
			user = br.readLine();
		} catch (IOException e) {
			System.out.println("�����������������");
			continue;
		}
//		System.out.println(user);
		System.out.println();
		System.out.println("\t\t��ӭʹ��ͨѶ¼��������");
		System.out.println("\t\t**************");
		System.out.println("\t\t���������룺");
		try {
			pass = br.readLine();
		} catch (IOException e) {
			System.out.println("�����������������");
		continue;
		}
		System.out.println("\t\t**************");
		if(user.length()>15||user.length()<4||pass.length()>16||pass.length()<6){

			System.out.println("�����û�������Ӧ��4~15���ַ��ڣ����볤��Ӧ��6~16λ֮�䣬����������");
			continue;
		}
//		System.out.println(pass);
		break;
		}
		else if(choice == 2){
			Register();
		}
		else
			System.out.println("\t\t�������������");
		}
	}
	private void Register()  {
		User user  = new User();
		String name = null,password = null;
		boolean flag = false;
		System.out.println();
		do{
			System.out.println("\t\t�����������û�����");
			try {
				name = br.readLine();
			} catch (IOException e) {
				System.out.println("�����������������");
				continue;
			}
			flag = false;
			if(name.length()>15||name.length()<4) 
			{
			System.out.println("�����û�������Ӧ��4~15���ַ��ڣ�����������");
			flag = true;
			}
		}while(flag);
		user.setName(name);
		
		do{
			System.out.println("\t\t**************");
			System.out.print("\t\t�������������룺");
			try {
				password = br.readLine();
			} catch (IOException e) {
				System.out.println("�����������������");
				continue;
			}
			flag = false;
			if(password.length()>16||password.length()<6) 
				{
				System.out.println("�������볤��Ӧ��6~16λ֮�䣬����������");
				flag = true;
				}
		}while(flag);
		user.setPassword(password);
		user.setGrant(Grant.ordinary);
		user.UpdateInfo();
		System.out.println("ע��ɹ������¼ϵͳ");
		return;
	}
}
