package com.contact.mainapp;

import java.util.Scanner;

import com.contact.entity.contacts;

//(1)Importing Package
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//--Importing End-----------
public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		contacts cont = new contacts();
		//Creating Object Of Connection Class "conn"
		Connection conn = null;
		//Creating Object Of PreparedStament Class "pst"
		PreparedStatement pst = null;
		//Creating Object Of ResultSet Class "rst" --> Iterating Over Rows And Columns In Database Table
		ResultSet rst = null;
		System.out.println("Contact Management System");
		System.out.println("-----------------------------");
		try {
			//(2)Loading Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//(3)Registering Driver
			String DATABASE_URL ="jdbc:mysql://localhost/contact";
			String USERNAME ="root";
			String PASSWORD = "";
			conn = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
			
			//(4)Checking Whether Connection Established Or Not
			if(conn!=null) {
				System.out.println("App Connected To Database Successfuly.");
				int choice;
				do {
					System.out.println("-----------------------------");
					System.out.println("1. Add New Contact.");
					System.out.println("2. Display All Contacts.");
					System.out.println("3. Display Contact By Area.");
					System.out.println("4. Update Contact Name.");
					System.out.println("5. Update Contact Address.");
					System.out.println("6. Update Contact Sim.");
					System.out.println("7. Update Contact Number.");
					System.out.println("8. Delete Contact.");
					System.out.println("9. Exit Application.");
					System.out.print("Enter Choice : ");
					choice = sc.nextInt();
					switch(choice) {
					//Create Operations
					case 1:
						 //Declaring Variable To Store Data
						String name;
						String address;
						String sim;
						long number;
						//Takin User Input
						System.out.print("Enter Name : ");
						name = sc.next();
						System.out.print("Enter Address : ");
						address = sc.next();
						System.out.print("Enter Sim Operator : ");
						sim = sc.next();
						System.out.print("Enter Number : ");
						number = sc.nextLong();
					
						//Setting The User Values And Encapsulating
						cont.setName(name);
						cont.setAdress(address);
						cont.setSim(sim);
						cont.setNumber(number);
						
						String InsertQuery = "insert into information(name,address,simcard,number) values(?,?,?,?)";
						//Preparing Query To Be Excuted
						pst = conn.prepareStatement(InsertQuery);
						pst.setString(1, cont.getName());
						pst.setString(2, cont.getAdress());
						pst.setString(3, cont.getSim());
						pst.setLong(4, cont.getNumber());
						int i=pst.executeUpdate();
						if(i>0) {
							System.out.println("Message : New Contact Added Successully.");
						}
						break;
						//Display Operations
					case 2:
						String DisplayQuery = "select *from information";
						pst = conn.prepareStatement(DisplayQuery);
						rst = pst.executeQuery();
						int serialnumber=0;
						while(rst.next()) {  //next() is a type of pointer which points always in a next row
							System.out.println("-----------------------------");
							serialnumber++;
							System.out.println(serialnumber+".) Name : "+rst.getString("name"));
							System.out.println("Contact ID : "+rst.getString("id"));
							System.out.println("Adress : "+rst.getString("address"));
							System.out.println("Sim Card : "+rst.getString("simcard"));
							System.out.println("Phone Number : "+rst.getString("number"));
						}
						break;
					case 3:
						String AreaQuery ="select *from information where address = ?";
						String area;
						System.out.println("Enter Adress : ");
						area = sc.next();
						cont.setAdress(area);
						pst = conn.prepareStatement(AreaQuery);
						pst.setString(1, cont.getAdress());
						rst = pst.executeQuery();
						if(rst.next()) {
							System.out.println("Here Is Your Results: ");
						}else {
							System.out.println("Data Not Exists In Our Database.");
						}
						pst = conn.prepareStatement(AreaQuery);
						
						pst.setString(1, cont.getAdress());
						ResultSet rst1 = pst.executeQuery();
							int serialnumber1=0;
							while(rst1.next()) {
								serialnumber1++;
								System.out.println("-----------------------------");
								System.out.println(serialnumber1+".) Name : "+rst1.getString("name"));
								System.out.println("Contact ID : "+rst1.getString("id"));
								System.out.println("Adress : "+rst1.getString("address"));
								System.out.println("Sim Card : "+rst1.getString("simcard"));
								System.out.println("Phone Number : "+rst1.getString("number"));
							}
						break;
						//Update Operations
					case 4:
					
						int checkid;
						System.out.println("Enter Contact ID : ");
						checkid = sc.nextInt();
						String CheckQuery = "select *from information where id = ?";
						pst= conn.prepareStatement(CheckQuery);
						pst.setInt(1, checkid);
						rst = pst.executeQuery();
						if(rst.next()) {
							System.out.println("Contact ID Founded.");
							String newname;
							System.out.println("Old Name : "+rst.getString("name"));
							System.out.print("Enter New Name : ");
							newname = sc.next();
							cont.setName(newname);
							String UpdateQuery1 = "update information set name = ? where id = "+checkid;
							pst = conn.prepareStatement(UpdateQuery1);
							pst.setString(1, cont.getName());
							int i1= pst.executeUpdate();
							if(i1>0) {
								System.out.println("Name Changed Successfuly");
							}
						}else {
							System.out.println("This ID Do Not Exists.");
						}
						
						break;
					case 5:
						int Checkid;
						System.out.println("Enter the Conatct Id: ");
						Checkid = sc.nextInt();
						String checkquery = "Select * from information where id =?";
						pst = conn.prepareStatement(checkquery);
						pst.setInt(1, Checkid);
						rst = pst.executeQuery();
						if(rst.next()) {
							System.out.println("Contact id Found");
							String newaddress;
							System.out.println("Old Address : "+rst.getString("address"));
							System.out.print("Enter the New Address: ");
							newaddress = sc.next();
							cont.setAdress(newaddress);
							String Query2 = "update information set address =? where id="+ Checkid;
							pst = conn.prepareStatement(Query2);
							pst.setString(1, cont.getAdress());
							int i2 = pst.executeUpdate();
							if(i2>0) {
								System.out.println("Address Change Succesfully");
							}
						}else {
							System.out.println("Contact Id do not Exits");
						}
						break;
					case 6:
						int Checkid2;
						System.out.println("Enter Contact ID :");
						Checkid2 = sc.nextInt();
						String checkquery2 = "Select * from information where id =?";
						pst = conn.prepareStatement(checkquery2);
						pst.setInt(1, Checkid2);
						rst = pst.executeQuery();
						if(rst.next()) {
							System.out.println("Contact ID Founded.");
							String newsim;
							System.out.println("Old sim name :" +rst.getString("simcard"));
							System.out.print("Enter New Sim Name: ");
							newsim = sc.next();
							cont.setSim(newsim);
							String Query3 = "Update information set simcard =?  where id =" + Checkid2;
							pst = conn.prepareStatement(Query3);
							pst.setString(1,cont.getSim());
							int i3 = pst.executeUpdate();
							if(i3>0) {
								System.out.println("Sim Card Name Updated Successfully.");
							}
							
						}else {
							System.out.println("Not Found");
						}
						break;
					case 7:
						long Checkid3;
						System.out.println("Enter Contact ID : ");
						Checkid3 = sc.nextLong();
						String checkquery3 = "Select * from information where id=?";
						pst = conn.prepareStatement(checkquery3);
						pst.setLong(1,Checkid3);
						rst = pst.executeQuery();
						if(rst.next()) {
							System.out.println("Contact ID Founded");
							
							long newnumber;
							System.out.println("Old number:" +rst.getLong("number"));
							System.out.print("Enter new number :");
							newnumber = sc.nextLong();
							cont.setNumber(newnumber);
							String Query4 = "Update information set number =? where id = "+ Checkid3;
							pst = conn.prepareStatement(Query4);
							pst.setLong(1, cont.getNumber());
							int i4 = pst.executeUpdate();
							if(i4>0) {
								System.out.println("Number Updated Successfully");
							}
						}else {
							System.out.println("Not found");
						}
						break;
						//Delete Operation
					case 8:
						int checkid5;
						System.out.print("Enter The Contact Id : ");
						checkid5 = sc.nextInt();
						String checkquery6 = "Select * from information where id = ?";
						pst = conn.prepareStatement(checkquery6);
						pst.setInt(1, checkid5);
						rst = pst.executeQuery();
						if(rst.next()) {
							System.out.println("Contact Id Found");
							String deleteqyery = "Delete from information where id = "+ checkid5;
							pst = conn.prepareStatement(deleteqyery);
							int i5 = pst.executeUpdate();
							if(i5>0) {
								System.out.println("Contact Deleted");
							}
						}else {
							System.out.println("not found");
						}
						break;
					case 9:
						System.out.println("Bye! Application Exited.");
					}
					
				}while(choice!=9);
			}
		}catch(Exception e) {
			System.out.println("Something Went Wrong..");
			e.printStackTrace();
		}

	}

}
//Made By Rahul