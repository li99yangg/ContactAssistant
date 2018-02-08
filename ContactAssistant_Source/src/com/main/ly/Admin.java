package com.main.ly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends User{
	Scanner sc = new Scanner(System.in);
	private String name;
	private String password;
	public Admin(String name,String password) {
		this.name = name;
		this.password = password;
		while(true)
		{
			System.out.println("\t\t欢迎您，管理员"+name+"使用本系统");
			System.out.println("\t\t=============");
			System.out.println("\t\t1.新建联系人");
			System.out.println("\t\t2.删除联系人");
			System.out.println("\t\t3。修改联系人信息"); 
			System.out.println("\t\t4.查看我的通讯录");
			System.out.println("\t\t5.搜索某一联系人");
			System.out.println("\t\t6.修改本用户密码");
			System.out.println("\t\t7.查看通讯录所有联系人");
			System.out.println("\t\t0.退出系统");
			System.out.print("\t\t请输入您想进行的操作:");
			
			int choice = sc.nextInt();
			switch(choice)
			{
				case 1:	AddContacter();
						break;
				case 2:	DeleteContacter();
						break;
				case 3:	UpdateContacter();
						break;
				case 4:	ShowContacter();
						break;
				case 5:	SearchContacter();
						break;
				case 6:	UpdatePassword();
						break;
				case 7:	ShowAllContacters();
					break;
				case 0:	super.ExitSystem();
						break;
				
			}
		}
	}
	public void ShowAllContacters(){
		try {
			DoMain.console.dispResultSet(DoMain.conn.createStatement().executeQuery("Select * From contact;"));
		} catch (SQLException e) {
			System.out.println("查找失败，请重新输入");
			return;
		}
		
	}
	@Override
	public void SearchContacter() {
		String key = null;
		int choice = 0;
		System.out.println("\t\t请选择您要进行的关键字搜索");
		System.out.println("\t\t1.根据姓名查找");
		System.out.println("\t\t2.根据性别查找"); 
		System.out.println("\t\t3.根据号码查找");
		System.out.println("\t\t4.根据地址查找");
		System.out.println("\t\t5.根据工作查找");
		choice = sc.nextInt();
		switch(choice){
		case 1:key = "name";break;
		case 2:key = "sex";break; 
		case 3:key = "phone";break;
		case 4:key = "address";break;
		case 5:key = "position";break;
		default:System.out.println("输入错误，请返回重试。");
		return;
		
		}
		System.out.println("请输入你要查找的"+key);
		String value = null;
		value = sc.next();
		try {
			DoMain.console.dispResultSet(DoMain.conn.createStatement().executeQuery("Select * From contact "
					+ "Where contact_"+key+" Like'%"+value+"%' And contact_from = '"+this.name+"';"));
		} catch (SQLException e) {
			System.out.println("查不到此人，请重试");
			return;
		}
		return;
	}
	@Override
	public void ShowContacter()  {
		try {
			DoMain.console.dispResultSet(DoMain.conn.createStatement().executeQuery("Select * From contact Where contact_from ='"+this.name+"';"));
		} catch (SQLException e) {
			System.out.println("查找失败，请重试");
			return;
		}
	}
	@Override
	public void UpdatePassword() {

		try{
			
			String newPass;
			boolean flag = false;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			do{
				System.out.println("\t\t**************");
				System.out.print("\t\t请输入您的新密码：");
				newPass = br.readLine();
				flag = false;
				if(newPass.length()>16||newPass.length()<6) 
					{
					System.out.println("您的密码长度应在6~16位之间，请重新输入");
					flag = true;
					}
				
				}while(flag);
			System.out.println(this.name);
			DoMain.conn.createStatement().executeUpdate("Update Users Set user_pass = '"+newPass+"' where user_name = '"+this.name+"';");
		} catch (SQLException | IOException e) {
			System.out.println("修改失败，请重试");
			return;
		}
		System.out.println("修改成功！");
	}
	@Override
	public void UpdateContacter() {
		try {
			Contacter c = new Contacter();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("\t\t请输入您要修改的联系人id:");
			int id = sc.nextInt();
			DoMain.console.dispResultSet(DoMain.conn.createStatement().executeQuery("Select * From contact "
					+ "Where contact_id = "+id+" And contact_from = '"+this.name+"';"));
			System.out.print("\t\t您是否要修改此联系人       Y/N");
			String choice = sc.next();
			if(choice.equals("Y")||choice.equals("y")){
				
			}else{
				return;
			}
			System.out.println("\t\t修改联系人（*为必填项！）");
			System.out.println("\t\t================");
			System.out.print("\t\t姓名*:");
			c.setName(br.readLine());
			do{
				int i = 1;
				if(i != 1)System.out.println("输入错误，请返回重试");
				System.out.print("\t\t性别*(M/F):");
				c.setSex(br.readLine().toUpperCase());
			}while(!(c.getSex().equals("F")||c.getSex().equals("M")));
			System.out.print("\t\t年龄：");
			c.setAge(sc.nextInt());
			System.out.print("\t\t电话号码*：");
			c.setPhone(br.readLine());
			System.out.print("\t\t地址：");
			c.setAddress(br.readLine());
			System.out.print("\t\t电子邮箱：");
			c.setEmail(br.readLine());
			System.out.print("\t\t工作岗位：");
			c.setPosition(br.readLine());
			System.out.print("\t\t备注：");
			c.setRemark(br.readLine());
			
			System.out.print("\t\t您是否要修改完成？       Y/N");
			String choice1 = br.readLine();
			if(choice1.equals("Y")||choice1.equals("y")){
				DoMain.conn.createStatement().executeUpdate("Update contact Set contact_name ='"+c.getName()+"',"
						+ "contact_sex='"+c.getSex()+"',"
								+ "contact_age ="+c.getAge()+","
										+ "contact_phone = '"+c.getPhone()+"',"
												+ "contact_address = '"+c.getAddress()+"',"
														+ "contact_email = '"+c.getEmail()+"',"
																+ "contact_position='"+c.getPosition()+"',"
																		+ "contact_remark = '"+c.getRemark()+"',"
																				+ "contact_from = '"+this.name+"' "
																						+ "where contact_id = "+id+";");								
				System.out.println("修改成功");
			}
			else if(choice1.equals("N")||choice1.equals("n")){
				System.out.println("请返回重新输入");
				return;
			}else{
				System.out.println("输入错误，请返回重试");
				return;
			}
		} catch (SQLException | IOException e) {
			System.out.println("修改联系人失败，请重试");
			return;
		}
	}
	@Override
	public void AddContacter() {
		try {
			Contacter c = new Contacter();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("\t\t新建联系人（*为必填项！）");
			System.out.println("\t\t================");
			System.out.print("\t\t姓名*:");
			c.setName(br.readLine());
			do{
				int i = 1;
				if(i != 1)System.out.println("输入错误，请返回重试");
				System.out.print("\t\t性别*(M/F):");
				c.setSex(br.readLine().toUpperCase());
			}while(!(c.getSex().equals("F")||c.getSex().equals("M")));
			System.out.print("\t\t年龄：");
			c.setAge(sc.nextInt());
			System.out.print("\t\t电话号码*：");
			c.setPhone(br.readLine());
			System.out.print("\t\t地址：");
			c.setAddress(br.readLine());
			System.out.print("\t\t电子邮箱：");
			c.setEmail(br.readLine());
			System.out.print("\t\t工作岗位：");
			c.setPosition(br.readLine());
			System.out.print("\t\t备注：");
			c.setRemark(br.readLine());
			
			System.out.print("\t\t您是否要创建此人       Y/N");
			String choice = br.readLine();
			if(choice.equals("Y")||choice.equals("y")){
				DoMain.conn.createStatement().executeUpdate("Insert into Contact"
						+ " (contact_name,contact_sex,contact_age,contact_phone,contact_address,contact_email,contact_position,contact_remark,contact_from) "
								+ "Value ('"+c.getName()+"','"+c.getSex()+"',"+c.getAge()+",'"+c.getPhone()+"','"+c.getAddress()+"','"+c.getEmail()+"','"+c.getPosition()+"','"+c.getRemark()+"','"+this.name+"');");
				System.out.println("创建成功");
			}
			else if(choice.equals("N")||choice.equals("n")){
				System.out.println("请返回重新输入");
				return;
			}else{
				System.out.println("输入错误，请返回重试");
				return;
			}
		} catch (SQLException | IOException e) {
			System.out.println("创建联系人失败，请重试");
		}
	}
	@Override
	public void DeleteContacter()
	{
		try {
			System.out.print("\t\t请输入您要删除的的人的id:");
			int id = sc.nextInt();
			DoMain.console.dispResultSet(DoMain.conn.createStatement().executeQuery("Select * From contact "
					+ "Where contact_id = "+id+" And contact_from = '"+this.name+"';"));
			System.out.print("\t\t您是否要删除此人       Y/N");
			String choice = sc.next();
			if(choice.equals("Y")||choice.equals("y")){
				DoMain.conn.createStatement().executeUpdate("Delete From Contact Where contact_id = "+id+";");
				System.out.println("删除成功");
			}
			else if(choice.equals("N")||choice.equals("n")){
				System.out.println("请返回重新输入");
				return;
			}else{
				System.out.println("输入错误，请返回重试");
				return;
			}
		} catch (SQLException e) {
			System.out.println("查不到此人，请重试");
			return;
		}
	}
}
/**
 * 打jar包，写配置文件，修改ip.，用bat文件运行程序。
 */
