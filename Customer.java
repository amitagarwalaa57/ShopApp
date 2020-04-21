package shop;
import java.sql.*;
public class Customer {
	private String custID; 
	private String custFName;
	private String custLName; 
	private String sex; 
	private long contactNo; 
	private String city; 
	private String country;
	private static Connection con;
	private static PreparedStatement pstate;
	private static ResultSet rs;
	public Customer() {
		this.custID="";
		this.custFName="";
		this.custLName="";
		this.sex="";
		this.contactNo=0;
		this.city="";
		this.country="";
	}
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop","root","abhi7099");
			pstate=null;
			rs=null;
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void accept(String custID,String custFName,String custLName,String sex,long contactNo,String city,String country) {
		this.custID=custID;
		this.custFName=custFName;
		this.custLName=custLName;
		this.sex=sex;
		this.contactNo=contactNo;
		this.city=city;
		this.country=country;
		try {
		pstate=con.prepareStatement("insert into Customer values(?,?,?,?,?,?,?)");
		pstate.setString(1, this.custID);
		pstate.setString(2,this.custFName);
		pstate.setString(3,this.custLName);
		pstate.setString(4,this.sex);
		pstate.setLong(5,this.contactNo);
		pstate.setString(6, this.city);
		pstate.setString(7,this.country);
		int row=pstate.executeUpdate();
		if(row!=0) {
			System.out.println("Database Inserted:Sucess");
		}
		else {
			System.out.println("Database Inserted:failure");
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void display(String custID) {
		try {
			pstate=con.prepareStatement("Select * from Customer where custID=?");
			pstate.setString(1, custID);
			rs=pstate.executeQuery();
			if(rs.next()) {
				do {
					System.out.println("-------------------------------------");
					System.out.println("CustID:"+rs.getString(1));
					System.out.println("Customer First Name:"+rs.getString(2));
					System.out.println("Customer Last Name:"+rs.getString(3));
					System.out.println("Sex:"+rs.getString(4));
					System.out.println("Contact No:"+rs.getLong(5));
					System.out.println("City:"+rs.getString(6));
					System.out.println("Country:"+rs.getString(7));
					System.out.println("-------------------------------------");
				}while(rs.next());
			}
			else {
				System.out.println("No Record found");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static boolean search(String custID) {
		
		try {
			pstate=con.prepareStatement("Select concat(custFName,' ',custLName) from Customer where custID=?");
			pstate.setString(1, custID);
			rs=pstate.executeQuery();
			if(rs.next()) {
				System.out.println("Customer Name:"+rs.getString(1));
				return  true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
	
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getCustFName() {
		return custFName;
	}
	public void setCustFName(String custFName) {
		this.custFName = custFName;
	}
	public String getCustLName() {
		return custLName;
	}
	public void setCustLName(String custLName) {
		this.custLName = custLName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String toString(){
		String s="";
		s+=("Customer Id:"+this.custID+"\n");
		s+=("Customer First Name:"+this.custFName+"\n");
		s+=("Customer Last Name:"+this.custLName+"\n");
		s+=("Sex:"+this.sex+"\n");
		s+=("Contact No:"+this.contactNo+"\n");
		s+=("City:"+this.city+"\n");
		s+=("Country:"+this.country+"\n");
		return s;
	}
	

}
