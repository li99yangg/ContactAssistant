package com.main.ly;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.main.ly.User.Grant;

public class DataBaseConsole {
	public  void dispResultSet(ResultSet rs) { 
		try{
		String tmpstr = null;
		ResultSetMetaData rsmd = rs.getMetaData();
		int numCols = rsmd.getColumnCount();
		if(!rs.next())System.out.println("未搜索到任何信息。");
		System.out.print("order\t\t");
		for(int i = 1;i<=numCols;i++)//获取每个字段名
		{
			if(i>1)System.out.print("\t");
			System.out.print(rsmd.getColumnLabel(i));
		}
		System.out.println();
		int j = 1;
		do
		{System.out.print((j++)+"\t\t");
			for(int i = 1;i<=numCols;i++)
			{
				
				if(i>1)System.out.print("\t\t");
				tmpstr = rs.getString(i);
				if(rs.wasNull())
					System.out.print("NULL");
				else System.out.print(tmpstr);
				
			}
			System.out.println();
		}while(rs.next());
		}catch(SQLException e){
			System.out.println("搜索失败，请重试");
			return;
		}
	}
	public Grant PassCheck(ResultSet rs, String user, String pass) { 
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			if(rs.next())
			{
					if((user.equals(rs.getString(1)) )&&(pass.equals(rs.getString(2)) )){
						switch(rs.getString(3))
						{
							case "root":return Grant.root;
							case "admin":return Grant.admin;
							case "ordinary":return Grant.ordinary;
						}
					}
					else return null;
			}
			 return null;
		} catch (SQLException e) {
				System.out.println("搜索失败，请重试");
		}
		return null;
		
	}
}
	

