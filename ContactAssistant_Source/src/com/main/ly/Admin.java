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
			System.out.println("\t\t��ӭ��������Ա"+name+"ʹ�ñ�ϵͳ");
			System.out.println("\t\t=============");
			System.out.println("\t\t1.�½���ϵ��");
			System.out.println("\t\t2.ɾ����ϵ��");
			System.out.println("\t\t3���޸���ϵ����Ϣ"); 
			System.out.println("\t\t4.�鿴�ҵ�ͨѶ¼");
			System.out.println("\t\t5.����ĳһ��ϵ��");
			System.out.println("\t\t6.�޸ı��û�����");
			System.out.println("\t\t7.�鿴ͨѶ¼������ϵ��");
			System.out.println("\t\t0.�˳�ϵͳ");
			System.out.print("\t\t������������еĲ���:");
			
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
			System.out.println("����ʧ�ܣ�����������");
			return;
		}
		
	}
	@Override
	public void SearchContacter() {
		String key = null;
		int choice = 0;
		System.out.println("\t\t��ѡ����Ҫ���еĹؼ�������");
		System.out.println("\t\t1.������������");
		System.out.println("\t\t2.�����Ա����"); 
		System.out.println("\t\t3.���ݺ������");
		System.out.println("\t\t4.���ݵ�ַ����");
		System.out.println("\t\t5.���ݹ�������");
		choice = sc.nextInt();
		switch(choice){
		case 1:key = "name";break;
		case 2:key = "sex";break; 
		case 3:key = "phone";break;
		case 4:key = "address";break;
		case 5:key = "position";break;
		default:System.out.println("��������뷵�����ԡ�");
		return;
		
		}
		System.out.println("��������Ҫ���ҵ�"+key);
		String value = null;
		value = sc.next();
		try {
			DoMain.console.dispResultSet(DoMain.conn.createStatement().executeQuery("Select * From contact "
					+ "Where contact_"+key+" Like'%"+value+"%' And contact_from = '"+this.name+"';"));
		} catch (SQLException e) {
			System.out.println("�鲻�����ˣ�������");
			return;
		}
		return;
	}
	@Override
	public void ShowContacter()  {
		try {
			DoMain.console.dispResultSet(DoMain.conn.createStatement().executeQuery("Select * From contact Where contact_from ='"+this.name+"';"));
		} catch (SQLException e) {
			System.out.println("����ʧ�ܣ�������");
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
				System.out.print("\t\t���������������룺");
				newPass = br.readLine();
				flag = false;
				if(newPass.length()>16||newPass.length()<6) 
					{
					System.out.println("�������볤��Ӧ��6~16λ֮�䣬����������");
					flag = true;
					}
				
				}while(flag);
			System.out.println(this.name);
			DoMain.conn.createStatement().executeUpdate("Update Users Set user_pass = '"+newPass+"' where user_name = '"+this.name+"';");
		} catch (SQLException | IOException e) {
			System.out.println("�޸�ʧ�ܣ�������");
			return;
		}
		System.out.println("�޸ĳɹ���");
	}
	@Override
	public void UpdateContacter() {
		try {
			Contacter c = new Contacter();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("\t\t��������Ҫ�޸ĵ���ϵ��id:");
			int id = sc.nextInt();
			DoMain.console.dispResultSet(DoMain.conn.createStatement().executeQuery("Select * From contact "
					+ "Where contact_id = "+id+" And contact_from = '"+this.name+"';"));
			System.out.print("\t\t���Ƿ�Ҫ�޸Ĵ���ϵ��       Y/N");
			String choice = sc.next();
			if(choice.equals("Y")||choice.equals("y")){
				
			}else{
				return;
			}
			System.out.println("\t\t�޸���ϵ�ˣ�*Ϊ�������");
			System.out.println("\t\t================");
			System.out.print("\t\t����*:");
			c.setName(br.readLine());
			do{
				int i = 1;
				if(i != 1)System.out.println("��������뷵������");
				System.out.print("\t\t�Ա�*(M/F):");
				c.setSex(br.readLine().toUpperCase());
			}while(!(c.getSex().equals("F")||c.getSex().equals("M")));
			System.out.print("\t\t���䣺");
			c.setAge(sc.nextInt());
			System.out.print("\t\t�绰����*��");
			c.setPhone(br.readLine());
			System.out.print("\t\t��ַ��");
			c.setAddress(br.readLine());
			System.out.print("\t\t�������䣺");
			c.setEmail(br.readLine());
			System.out.print("\t\t������λ��");
			c.setPosition(br.readLine());
			System.out.print("\t\t��ע��");
			c.setRemark(br.readLine());
			
			System.out.print("\t\t���Ƿ�Ҫ�޸���ɣ�       Y/N");
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
				System.out.println("�޸ĳɹ�");
			}
			else if(choice1.equals("N")||choice1.equals("n")){
				System.out.println("�뷵����������");
				return;
			}else{
				System.out.println("��������뷵������");
				return;
			}
		} catch (SQLException | IOException e) {
			System.out.println("�޸���ϵ��ʧ�ܣ�������");
			return;
		}
	}
	@Override
	public void AddContacter() {
		try {
			Contacter c = new Contacter();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("\t\t�½���ϵ�ˣ�*Ϊ�������");
			System.out.println("\t\t================");
			System.out.print("\t\t����*:");
			c.setName(br.readLine());
			do{
				int i = 1;
				if(i != 1)System.out.println("��������뷵������");
				System.out.print("\t\t�Ա�*(M/F):");
				c.setSex(br.readLine().toUpperCase());
			}while(!(c.getSex().equals("F")||c.getSex().equals("M")));
			System.out.print("\t\t���䣺");
			c.setAge(sc.nextInt());
			System.out.print("\t\t�绰����*��");
			c.setPhone(br.readLine());
			System.out.print("\t\t��ַ��");
			c.setAddress(br.readLine());
			System.out.print("\t\t�������䣺");
			c.setEmail(br.readLine());
			System.out.print("\t\t������λ��");
			c.setPosition(br.readLine());
			System.out.print("\t\t��ע��");
			c.setRemark(br.readLine());
			
			System.out.print("\t\t���Ƿ�Ҫ��������       Y/N");
			String choice = br.readLine();
			if(choice.equals("Y")||choice.equals("y")){
				DoMain.conn.createStatement().executeUpdate("Insert into Contact"
						+ " (contact_name,contact_sex,contact_age,contact_phone,contact_address,contact_email,contact_position,contact_remark,contact_from) "
								+ "Value ('"+c.getName()+"','"+c.getSex()+"',"+c.getAge()+",'"+c.getPhone()+"','"+c.getAddress()+"','"+c.getEmail()+"','"+c.getPosition()+"','"+c.getRemark()+"','"+this.name+"');");
				System.out.println("�����ɹ�");
			}
			else if(choice.equals("N")||choice.equals("n")){
				System.out.println("�뷵����������");
				return;
			}else{
				System.out.println("��������뷵������");
				return;
			}
		} catch (SQLException | IOException e) {
			System.out.println("������ϵ��ʧ�ܣ�������");
		}
	}
	@Override
	public void DeleteContacter()
	{
		try {
			System.out.print("\t\t��������Ҫɾ���ĵ��˵�id:");
			int id = sc.nextInt();
			DoMain.console.dispResultSet(DoMain.conn.createStatement().executeQuery("Select * From contact "
					+ "Where contact_id = "+id+" And contact_from = '"+this.name+"';"));
			System.out.print("\t\t���Ƿ�Ҫɾ������       Y/N");
			String choice = sc.next();
			if(choice.equals("Y")||choice.equals("y")){
				DoMain.conn.createStatement().executeUpdate("Delete From Contact Where contact_id = "+id+";");
				System.out.println("ɾ���ɹ�");
			}
			else if(choice.equals("N")||choice.equals("n")){
				System.out.println("�뷵����������");
				return;
			}else{
				System.out.println("��������뷵������");
				return;
			}
		} catch (SQLException e) {
			System.out.println("�鲻�����ˣ�������");
			return;
		}
	}
}
/**
 * ��jar����д�����ļ����޸�ip.����bat�ļ����г���
 */
