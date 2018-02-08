package com.main.ly;
import java.sql.*;
public class Contacter {
	enum Sex {F,M};
	private String name = null;
	private String sex = null;
	private int age = 0;
	private String phone = null;
	private String address = null;
	private String email= null;
	private String position = null;
	private String remark = null;
	private Timestamp upload_time= null;
	private String from= null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String string) {
		this.sex = string;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Timestamp upload_time) {
		this.upload_time = upload_time;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Contacter (){}
	public Contacter ( String name, String sex,int age,String phone,String address, String email,String position,
			String remark,Timestamp upload_time, String from){
		this.name  = name;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.position = position;
		this.remark = remark;
		this.upload_time = upload_time;
		this.from = from;
	}




}
		

