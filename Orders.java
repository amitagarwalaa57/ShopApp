package shop;
import java.sql.*;
public class Orders {
	private String  orderNo;
	private Date  orderDate;
	private String custID;
	private String prodCode;
	private int qtyPurchased;
	private double totBillAmt;
	private String transactionID; 
	private String paymentType;
	private static Connection con;
	private static PreparedStatement pstate;
	private static ResultSet rs;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public int getQtyPurchased() {
		return qtyPurchased;
	}
	public void setQtyPurchased(int qtyPurchased) {
		this.qtyPurchased = qtyPurchased;
	}
	public double getTotBillAmt() {
		return totBillAmt;
	}
	public void setTotBillAmt(double totBillAmt) {
		this.totBillAmt = totBillAmt;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public Orders() {
		this.orderNo=null;
		this.orderDate=null;
		this.custID=null;
		this.prodCode=null;
		this.qtyPurchased=0;
		this.totBillAmt=0;
		this.transactionID=null;
		this.paymentType=null;
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
	public void accept(String  orderNo,Date  orderDate,String custID,String prodCode,int qtyPurchased,double totBillAmt,String transactionID,String paymentType) {
		this.orderNo=orderNo;
		this.orderDate=orderDate;
		this.custID=custID;
		this.prodCode=prodCode;
		this.qtyPurchased=qtyPurchased;
		this.totBillAmt=totBillAmt;
		this.transactionID=transactionID;
		this.paymentType=paymentType;
		try {
			pstate=con.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?)");
			pstate.setString(1, this.orderNo);
			pstate.setDate(2, this.orderDate);
			pstate.setString(3, this.custID);
			pstate.setString(4, this.prodCode);
			pstate.setInt(5, this.qtyPurchased);
			pstate.setDouble(6, this.totBillAmt);
			pstate.setString(7, this.transactionID);
			pstate.setString(8, this.paymentType);
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
			pstate=con.prepareStatement("Select * from Orders where custID=?");
			pstate.setString(1, custID);
			rs=pstate.executeQuery();
			if(rs.next()) {
				do {
					System.out.println("-------------------------------------");
					System.out.println("Order No:"+rs.getString(1));
					System.out.println("Order Date:"+rs.getDate(2));
					Customer.display(rs.getString(3));
					Product.display(rs.getString(4));
					//System.out.println("Product Code:"+rs.getString(4));
					System.out.println("Order Quantity::"+rs.getInt(5));
					System.out.println("Total amount:"+rs.getDouble(6));
					System.out.println("Transaction ID:"+rs.getString(7));
					System.out.println("Payment Type:"+rs.getString(8));
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
	public String toString() {
		String s="";
		s+=("Order No:"+this.orderNo);
		s+=("Order Date:"+this.orderDate);
		s+=("Customer ID:"+this.custID);
		s+=("Product Code:"+this.prodCode);
		s+=("Order Quantity::"+this.qtyPurchased);
		s+=("Total amount:"+this.totBillAmt);
		s+=("Transaction ID:"+this.transactionID);
		s+=("Payment Type:"+this.paymentType);
		return s;
	}
}
