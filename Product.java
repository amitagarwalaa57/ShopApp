package shop;
import java.sql.*;
//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
public class Product {
	private String prodCode; 
	private String prodName; 
	private int prodWeight; 
	private String prodCategory;  
	private Date mfgDate;
	private Date expDate;
	private double cost;
	private static Connection con;
	private static PreparedStatement pstate;
	private static ResultSet rs;
	public Product() {
		this.prodCode=null;
		this.prodName=null;
		this.prodWeight=0;
		this.prodCategory=null;
		this.mfgDate=null;
		this.expDate=null;
		this.cost=0;
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
	public void accept(String prodCode,String prodName,int prodWeight,String prodCategory,Date mfgDate,Date expDate,double cost) 
	{
		this.prodCode=prodCode;
		this.prodName=prodName;
		this.prodWeight=prodWeight;
		this.prodCategory=prodCategory;
		this.mfgDate=mfgDate;
		this.expDate=expDate;
		this.cost=cost;
		try {
			pstate=con.prepareStatement("insert into Product values(?,?,?,?,?,?,?)");
			pstate.setString(1, this.prodCode);
			pstate.setString(2, this.prodName);
			pstate.setInt(3, this.prodWeight);
			pstate.setString(4, this.prodCategory);
			pstate.setDate(5, this.mfgDate);
			pstate.setDate(6, this.expDate);
			pstate.setDouble(7, this.cost);
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
	public static Map<String,Double> display(double lcost,double ucost) {
		Map<String,Double> a=null;
		try {
			pstate=con.prepareStatement("Select * from product where cost between ? and ?");
			pstate.setDouble(1, lcost);
			pstate.setDouble(2, ucost);
			rs=pstate.executeQuery();
			if(rs.next()) {
				a=new HashMap<String,Double>();
				do {
					
					System.out.println("-------------------------------------");
					System.out.println("Product No:"+(a.size()+1));
					String pcode=rs.getString(1);
					System.out.println("Product Code:"+pcode);
					System.out.println("Product Name:"+rs.getString(2));
					System.out.println("Product Weight:"+rs.getInt(3));
					System.out.println("Product Category:"+rs.getString(4));
					System.out.println("MFG Date:"+rs.getDate(5));
					System.out.println("Exp Date:"+rs.getDate(6));
					double co=rs.getDouble(7);
					System.out.println("Cost:"+co);
					System.out.println("-------------------------------------");
					a.put(pcode, co);
				}while(rs.next());
			}
			else {
				System.out.println("No Record found");
				a=null;
			}
			return a;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static void display(String prodcode) {
		
		try {
			pstate=con.prepareStatement("Select * from product where prodCode=?");
			pstate.setString(1, prodcode);
			rs=pstate.executeQuery();
			if(rs.next()) {
				
				do {
					
					System.out.println("-------------------------------------");
					System.out.println("Product Code:"+rs.getString(1));
					System.out.println("Product Name:"+rs.getString(2));
					System.out.println("Product Weight:"+rs.getInt(3));
					System.out.println("Product Category:"+rs.getString(4));
					System.out.println("MFG Date:"+rs.getDate(5));
					System.out.println("Exp Date:"+rs.getDate(6));
					
					System.out.println("Cost:"+rs.getDouble(7));
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
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdWeight() {
		return prodWeight;
	}
	public void setProdWeight(int prodWeight) {
		this.prodWeight = prodWeight;
	}
	public String getProdCategory() {
		return prodCategory;
	}
	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}
	public Date getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
}
