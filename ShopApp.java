package shop;
import java.io.*;
import java.util.*;
import java.lang.Math;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class ShopApp {
	private List<Product> pro;
	private List<Customer> cus;
	public ShopApp() {
		pro=new ArrayList<Product>();
		cus=new ArrayList<Customer>();
	}
	public void customer_Accept() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			Customer c=new Customer();
			System.out.print("Customer ID:");
			String custID=br.readLine();
			System.out.print("Customer First Name:");
			String custFName=br.readLine();
			System.out.print("Customer Last Name:");
			String custLName=br.readLine();
			String sex;
			do {
				System.out.print("Sex:");
				sex=br.readLine();
				if(!sex.toLowerCase().equals("male") && !sex.toLowerCase().equals("female"))
					System.out.println("Wrong entry,Try Again");
			}while(!sex.toLowerCase().equals("male") && !sex.toLowerCase().equals("female"));
			System.out.print("Contact No.:");
			Long contactNo=Long.parseLong(br.readLine());
			String city;
			do {
				System.out.print("City:");
				city=br.readLine();
				if(!city.toLowerCase().equals("kolkata") && !city.toLowerCase().equals("pune")&& !city.toLowerCase().equals("hyderabad"))
					System.out.println("Wrong entry,Try Again");
			}while(!city.toLowerCase().equals("kolkata") && !city.toLowerCase().equals("pune")&& !city.toLowerCase().equals("hyderabad"));
			String country;
			do {
				System.out.print("Country:");
				country=br.readLine();
				if(!country.toLowerCase().equals("india")) {
					System.out.println("Wrong entry,Try Again");
				}
			}while(!country.toLowerCase().equals("india"));
			c.accept(custID, custFName, custLName, sex, contactNo, city, country);
			cus.add(c);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void product_Accept() {
		try {
			Product p=new Product();	
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Product Code :");
			String prodCode=br.readLine();
			System.out.print("Product Name :");
			String prodName=br.readLine();
			int prodWeight;
			do {
				System.out.print("Product Weight :");
				prodWeight=Integer.parseInt(br.readLine());
				if(prodWeight<0)	System.out.println("Wrong entry,Try Again");
			}while(prodWeight<0);
			System.out.print("Product Category :");
			String prodCategory=br.readLine();
			System.out.print("MFG date :");
			Date mfgDate=Date.valueOf(br.readLine());
			System.out.print("Expire date :");
			Date expDate=Date.valueOf(br.readLine());
			double cost;
			do {
				System.out.print("Product Cost :");
				cost=Double.parseDouble(br.readLine());
				if(cost<=0)		System.out.println("Wrong entry,Try Again");
			}while(cost<=0);
			p.accept(prodCode, prodName, prodWeight, prodCategory, mfgDate, expDate, cost);
			pro.add(p);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	public void purchase_Product() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Searching Product....");
			System.out.print("Lower Limit:");
			double lcost=Double.parseDouble(br.readLine());
			System.out.print("Upper Limit:");
			double ucost=Double.parseDouble(br.readLine());
			Map<String,Double> a=Product.display(lcost, ucost);
			if(a!=null) {
				System.out.print("Enter Product Code:");
				String pcode=br.readLine();
				if(a.keySet().contains(pcode)) {
					System.out.print("Enter Customer ID:");
					String cid=br.readLine();
					if(Customer.search(cid)) {
						Orders o=new Orders();
						
						//3-cus_co 3_ prod dd-mm ss
						Date orderDate=Date.valueOf(LocalDate.now());
						System.out.print("Quantity purchase:");
						int qtyPurchased=Integer.parseInt(br.readLine());
						String s=LocalDateTime.now().toString();
						String[] s1=s.split("T");
						String[] s2=s1[0].split("-");
						String[] s3=s1[1].split(":");
						
						//System.out.println(s2[2]+"|"+s2[1]+s3[2].substring(0, 2));
						String orderNo=cid.substring(0,3)+pcode.substring(0,3)+s2[2]+s2[1]+s3[2].substring(0, 2);
						//String s=LocalDate.now().toString();
						
						double totBillAmt=qtyPurchased*a.get(pcode);
						
						String transactionID="TNX"+((int)Math.random()*10000);
						
						String paymentType="CASH ON DELIVERY";
						o.accept(orderNo, orderDate, cid, pcode, qtyPurchased, totBillAmt, transactionID, paymentType);
					}
					else {
						System.out.println("No Customer Found");
					}
					
				}
				else {
					System.out.println("Product code Not found");
				}
				
			}
			else {
				System.out.println("No product found :(");
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void display_OrderDetails() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter Customer ID:");
			String custID=br.readLine();
			Orders.display(custID);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			int ch;
			do {
				ShopApp s=new ShopApp();
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				System.out.println("-------------------------------------");
				System.out.println("Accept Customer Details [1]");
				System.out.println("Accept Product  Details [2]");
				System.out.println("Purchase a Product      [3]");
				System.out.println("Display Order Details   [4]");
				System.out.println("Exit                    [5]");
				System.out.print("Enter Your choice :");
				ch=Integer.parseInt(br.readLine());
				switch(ch) {
					case 1:
						s.customer_Accept();
						break;
					case 2:
						s.product_Accept();
						break;
					case 3:
						s.purchase_Product();
						break;
					case 4:
						s.display_OrderDetails();
						break;
					case 5:
						break;
					default:
						System.out.println("Wrong entry,Try Again");
						break;
					
				}
			}while(ch!=5);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
