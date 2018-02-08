package com.main.ly;

import java.io.IOException;
import java.security.DomainCombiner;
import java.sql.SQLException;

import com.main.ly.User.Grant;

public class Menu {
	public Menu(User user){
		switch (user.getGrant())
		{
		case root:Root root = new Root(user.getName(),user.getPassword());
			break;
		case admin:
				Admin admin = new Admin(user.getName(),user.getPassword());
			
			break;
		case ordinary:try {
				Ordinary ordinary = new Ordinary(user.getName(),user.getPassword());
			} catch (Exception e) {
				System.out.println("º”‘ÿ ß∞‹£¨«Î÷ÿ ‘");
			}
			break;
		}
		
	}

	public void Ordinary() { 
		
	}

	public void Admin(Admin user) { 
		
	}

	public void Root(Root user) { 
		
	}
}
