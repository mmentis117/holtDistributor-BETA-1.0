import java.sql.*;
import java.util.ArrayList;

public class holtDistributorFunctions {

	public static void insertTerritory(Statement myStmt, int territory_number, String territory_name)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertTerritory(territory_number, territory_name));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updateTerritoryByNumber(Statement myStmt, int territory_number, String territory_name)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateTerritoryByNumber(territory_number, territory_name));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void deleteTerritory(Statement myStmt, int territory_number, String territory_name)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.deleteTerritory(territory_number, territory_name));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void insertSalesRepSalesInfo(Statement myStmt, int Sales_rep_number, float MTD_sales, float YTD_sales, float MTD_commission, float YTD_commission, float Commission_rate)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertSalesRepSalesInfo(Sales_rep_number, MTD_sales, YTD_sales, MTD_commission, YTD_commission, Commission_rate));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}


	public static void updateSalesRepSalesInfoByNumber(Statement myStmt, int Sales_rep_number, float MTD_sales, float YTD_sales, float MTD_commission, float YTD_commission, float Commission_rate)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateSalesRepSalesInfoByNumber(Sales_rep_number, MTD_sales, YTD_sales, MTD_commission, YTD_commission, Commission_rate));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}
	
	public static void updateSalesRepSalesInfoByNumber(Statement myStmt, int Sales_rep_number, float MTD_sales, float YTD_sales)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateSalesRepSalesInfoByNumber(Sales_rep_number, MTD_sales, YTD_sales));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void insertSalesRepBasicInfo(Statement myStmt, int Sales_rep_number, String rep_name, String rep_address, String rep_city, String rep_state, int rep_zip, int territory_number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertSalesRepBasicInfo(Sales_rep_number, rep_name, rep_address, rep_city, rep_state, rep_zip, territory_number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updateSalesRepBasicInfoByNumber(Statement myStmt, int Sales_rep_number, String rep_name, String rep_address, String rep_city, String rep_state, int rep_zip, int territory_number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateSalesRepBasicInfoByNumber(Sales_rep_number, rep_name, rep_address, rep_city, rep_state, rep_zip, territory_number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void deleteSalesRepBasicInfo(Statement myStmt, int Sales_rep_number, String rep_name)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.deleteSalesRepBasicInfo(Sales_rep_number, rep_name));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}	
	public static void insertCustomerBasicInfo(Statement myStmt,int Customer_Number, String Customer_sold_to_name , String Customer_sold_to_address_line_1, 
			String Customer_sold_to_address_line_2, String Customer_sold_to_city, String Customer_sold_to_state, int Customer_sold_to_zip, int Customer_sold_to_rep_number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertCustomerBasicInfo(Customer_Number, Customer_sold_to_name, Customer_sold_to_address_line_1, 
					Customer_sold_to_address_line_2, Customer_sold_to_city, Customer_sold_to_state, Customer_sold_to_zip, Customer_sold_to_rep_number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updateCustomerBasicInfoByNumber(Statement myStmt, int Customer_Number, String Customer_sold_to_name , String Customer_sold_to_address_line_1, 
			String Customer_sold_to_address_line_2, String Customer_sold_to_city, String Customer_sold_to_state, int Customer_sold_to_zip, int Customer_sold_to_rep_number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateCustomerBasicInfoByNumber(Customer_Number, Customer_sold_to_name, Customer_sold_to_address_line_1, 
					Customer_sold_to_address_line_2, Customer_sold_to_city, Customer_sold_to_state, Customer_sold_to_zip, Customer_sold_to_rep_number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void deleteCustomerBasicInfo(Statement myStmt, int Customer_Number, String Customer_sold_to_name)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.deleteCustomerBasicInfo(Customer_Number, Customer_sold_to_name));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void insertCustomerSalesInfo(Statement myStmt, int Customer_Number, float Customer_MTD_sales , float Customer_YTD_sales, 
			float Customer_balance, float Customer_credit_limits, float Customer_total_invoice, float Customer_payment)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertCustomerSalesInfo(Customer_Number, Customer_MTD_sales, Customer_YTD_sales, Customer_balance, Customer_credit_limits, Customer_total_invoice, Customer_payment));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}


	public static void updateCustomerSalesInfoByNumber(Statement myStmt, int Customer_Number, float Customer_MTD_sales , float Customer_YTD_sales, 
			float Customer_balance, float Customer_credit_limits, float Customer_total_invoice, float Customer_payment)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateCustomerSalesInfoByNumber(Customer_Number, Customer_MTD_sales, Customer_YTD_sales, Customer_balance, Customer_credit_limits, Customer_total_invoice, Customer_payment));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}
	
	public static void updateCustomerSalesInfoByNumber(Statement myStmt, int Customer_Number, float Customer_MTD_sales , float Customer_YTD_sales, 
			float Customer_balance, float Customer_total_invoice)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateCustomerSalesInfoByNumber(Customer_Number, Customer_MTD_sales, Customer_YTD_sales, Customer_balance, Customer_total_invoice));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void insertItemBasicInfo(Statement myStmt, int Item_Number, String Item_Description , float Item_Price)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertItemBasicInfo(Item_Number, Item_Description, Item_Price));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updateItemBasicInfoByNumber(Statement myStmt, int Item_Number, String Item_Description , float Item_Price)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateItemBasicInfoByNumber(Item_Number, Item_Description, Item_Price));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void deleteItemBasicInfo(Statement myStmt, int Item_Number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.deleteItemBasicInfo(Item_Number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void insertItemSalesInfo(Statement myStmt, int Item_Number, float MTD_sales , float YTD_sales, 
			int units_on_hand, int units_allocated, int reorder_point)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertItemSalesInfo(Item_Number, MTD_sales, YTD_sales, units_on_hand, units_allocated, reorder_point));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updateItemSalesInfoByNumber(Statement myStmt, int Item_Number, float MTD_sales , float YTD_sales, 
			int units_on_hand, int units_allocated, int reorder_point)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateItemSalesInfoByNumber(Item_Number, MTD_sales, YTD_sales, units_on_hand, units_allocated, reorder_point));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}
	public static void updateItemSalesInfoByNumber(Statement myStmt, int Item_Number, float MTD_sales , float YTD_sales, 
			int units_on_hand, int units_allocated)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateItemSalesInfoByNumber(Item_Number, MTD_sales, YTD_sales, units_on_hand, units_allocated));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void insertVenderBasicInfo(Statement myStmt, int Vender_Number, String vender_name, String vender_address, String vender_city, 
			String vender_state, int vender_zip)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertVenderBasicInfo(Vender_Number, vender_name, vender_address, vender_city, vender_state, vender_zip));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updateVenderBasicInfoByNumber(Statement myStmt, int Vender_Number, String vender_name, String vender_address, String vender_city, 
			String vender_state, int vender_zip)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateVenderBasicInfoByNumber(Vender_Number, vender_name, vender_address, vender_city, vender_state, vender_zip));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void deleteVenderBasicInfo(Statement myStmt, int Vender_Number, String vender_name)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.deleteVenderBasicInfo(Vender_Number, vender_name));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void insertVenderAndItemInfo(Statement myStmt, int Vender_Number, int Item_Number, float vender_item_price, float minimum_order_quantity, String lead_time)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertVenderAndItemInfo(Vender_Number, Item_Number, vender_item_price, minimum_order_quantity, lead_time));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updateVenderAndItemInfoByNumber(Statement myStmt, int Vender_Number, int Item_Number, float vender_item_price, float minimum_order_quantity, String lead_time)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateVenderAndItemInfoByNumber(Vender_Number, Item_Number, vender_item_price, minimum_order_quantity, lead_time));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void deleteVenderAndItemInfo(Statement myStmt, int Vender_Number, int Item_Number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.deleteVenderAndItemInfo(Vender_Number, Item_Number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}


	public static void insertOrderInfo(Statement myStmt, int Order_Number, String order_date, String Customer_sold_to_name, int Customer_PO_number, String Customer_ship_to_name, 
			String Customer_ship_to_address_line_1, String Customer_ship_to_address_line_2, String Customer_ship_to_city, String Customer_ship_to_state, int Customer_ship_to_zip)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertOrderInfo(Order_Number, order_date, Customer_sold_to_name, Customer_PO_number, Customer_ship_to_name, 
					Customer_ship_to_address_line_1, Customer_ship_to_address_line_2, Customer_ship_to_city, Customer_ship_to_state, Customer_ship_to_zip));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updateOrderInfoByNumber(Statement myStmt, int Order_Number, String order_date, String Customer_sold_to_name, int Customer_PO_number, String Customer_ship_to_name, 
			String Customer_ship_to_address_line_1, String Customer_ship_to_address_line_2, String Customer_ship_to_city, String Customer_ship_to_state, int Customer_ship_to_zip)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateOrderInfoByNumber(Order_Number, order_date, Customer_sold_to_name, Customer_PO_number, Customer_ship_to_name,
					Customer_ship_to_address_line_1, Customer_ship_to_address_line_2, Customer_ship_to_city, Customer_ship_to_state, Customer_ship_to_zip));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void deleteOrderInfo(Statement myStmt, int Order_Number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.deleteOrderInfo(Order_Number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void insertOrderAndItemInfo(Statement myStmt, int Order_Number, int Item_Number, int item_quantity_order, int item_quantity_shipped, float item_sales_price)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertOrderAndItemInfo(Order_Number, Item_Number, item_quantity_order, item_quantity_shipped, item_sales_price));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updateOrderAndItemInfoByNumber(Statement myStmt, int Order_Number, int Item_Number, int item_quantity_order, int item_quantity_shipped, float item_sales_price)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateOrderAndItemInfoByNumber(Order_Number, Item_Number, item_quantity_order, item_quantity_shipped, item_sales_price));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void deleteOrderAndItemInfo(Statement myStmt, int Order_Number, int Item_Number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.deleteOrderAndItemInfo(Order_Number, Item_Number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void insertInvoiceInfo(Statement myStmt, int Invoice_Number, String invoice_date, int order_number, float ship_charge, float tax)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertInvoiceInfo(Invoice_Number, invoice_date, order_number, ship_charge, tax));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updateInvoiceInfoByNumber(Statement myStmt, int Invoice_Number, String invoice_date, int order_number, float ship_charge, float tax)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateInvoiceInfoByNumber(Invoice_Number, invoice_date, order_number, ship_charge, tax));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}
	
	public static void updateInvoiceInfoByNumber(Statement myStmt, int Invoice_Number, float total)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateInvoiceInfoByNumber(Invoice_Number, total));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void deleteInvoiceInfo(Statement myStmt, int Invoice_Number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.deleteInvoiceInfo(Invoice_Number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void insertPaymentInfo(Statement myStmt, int Payment_Number, int customer_number, String payment_date, float payment_amount)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertPaymentInfo(Payment_Number, customer_number, payment_date, payment_amount));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void updatePaymentInfoByNumber(Statement myStmt, int Payment_Number, int customer_number, String payment_date, float payment_amount)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updatePaymentInfoByNumber(Payment_Number, customer_number, payment_date, payment_amount));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}

	public static void deletePaymentInfo(Statement myStmt, int Payment_Number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.deletePaymentInfo(Payment_Number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}
	
	public static void insertCustomerLoginBasicInfo(Statement myStmt, String Customer_sold_to_name , String Customer_sold_to_address_line_1, 
			String Customer_sold_to_address_line_2, String Customer_sold_to_city, String Customer_sold_to_state, int Customer_sold_to_zip, int Customer_sold_to_rep_number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertCustomerLoginBasicInfo(Customer_sold_to_name, Customer_sold_to_address_line_1, 
					Customer_sold_to_address_line_2, Customer_sold_to_city, Customer_sold_to_state, Customer_sold_to_zip, Customer_sold_to_rep_number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}
	
	public static void insertCustomerLoginSalesInfo(Statement myStmt,float Customer_MTD_sales , float Customer_YTD_sales, 
			float Customer_balance, float Customer_credit_limits, float Customer_total_invoice, float Customer_payment)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertCustomerLoginSalesInfo(Customer_MTD_sales, Customer_YTD_sales, Customer_balance, Customer_credit_limits, Customer_total_invoice, Customer_payment));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}
	
	public static void insertUserLoginInfo(Statement myStmt, int Customer_Number , String username, String password)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.insertUserLoginInfo(Customer_Number, username, password));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}
	
	public static int getCustomerNumber(Statement myStmt, String customerName)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getCustomerNumber(customerName));

			// 4. Process the result set
			int customerNumber = -1;
			while(myRs.next()){
				customerNumber = Integer.parseInt(myRs.getString("Customer_Number"));
			}
			return customerNumber;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static int getOrderNumber(Statement myStmt, String customerName)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getOrderNumber(customerName));

			// 4. Process the result set
			int orderNumber = -1;
			while(myRs.next()){
				orderNumber = Integer.parseInt(myRs.getString("Order_Number"));
			}
			return orderNumber;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static String getCustomerNameByOrderNumber(Statement myStmt, int Order_Number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getCustomerNameByOrderNumber(Order_Number));

			// 4. Process the result set
			String customerName = "";
			while(myRs.next()){
				customerName = (myRs.getString("Customer_sold_to_name"));
			}
			return customerName;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String getCustomerNameByCustomerNumber(Statement myStmt, int Customer_Number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getCustomerNameByCustomerNumber(Customer_Number));

			// 4. Process the result set
			String customerName = "";
			while(myRs.next()){
				customerName = (myRs.getString("Customer_sold_to_name"));
			}
			return customerName;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String getItemDescriptionByNumber(Statement myStmt, int Item_Number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getItemDescriptionByNumber(Item_Number));

			// 4. Process the result set
			String customerName = "";
			while(myRs.next()){
				customerName = (myRs.getString("Item_Description"));
			}
			return customerName;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static int getInvoiceNumber(Statement myStmt, String invoice_date, int order_number, float ship_charge, float tax)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getInvoiceNumber(invoice_date, order_number, ship_charge, tax));

			// 4. Process the result set
			int invoiceNumber = -1;
			while(myRs.next()){
				invoiceNumber = Integer.parseInt(myRs.getString("Invoice_Number"));
			}
			return invoiceNumber;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public static float getInvoiceTotal(Statement myStmt, int Invoice_Number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getInvoiceTotal(Invoice_Number));

			// 4. Process the result set
			float invoiceNumber = -1;
			while(myRs.next()){
				invoiceNumber = Float.parseFloat(myRs.getString("total"));
			}
			return invoiceNumber;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static int getCustomerSalesRepNumber(Statement myStmt, int Customer_Number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getCustomerSalesRepNumber(Customer_Number));

			// 4. Process the result set
			int repNumber = -1;
			while(myRs.next()){
				repNumber = Integer.parseInt(myRs.getString("Customer_sold_to_rep_number"));
			}
			return repNumber;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static float getCustomerBalance(Statement myStmt, int Customer_Number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getCustomerBalance(Customer_Number));

			// 4. Process the result set
			float balance = -1;
			while(myRs.next()){
				balance = Float.parseFloat(myRs.getString("Customer_balance"));
			}
			return balance;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static void sendThroughCustomerInvoiceInfo(Statement myStmt, String invoice_date, int order_number, float ship_charge, float tax, float total)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.sendThroughCustomerInvoiceInfo(invoice_date, order_number, ship_charge, tax, total));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}
	
	public static void updateQuantityShipped(Statement myStmt, int item_quantity_shipped, int Order_Number, int Item_Number)
	{
		try {
			// 3. Execute SQL Query
			myStmt.executeUpdate(SQLstatements.updateQuantityShipped(item_quantity_shipped, Order_Number, Item_Number));
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
	}
	
	

	public static String[][] getTerritoryList(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.territoryList);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Territory_Number");
			Columns.add("Territory_name");
			Columns.add("Sales_rep_number");
			Columns.add("rep_name");
			Columns.add("rep_address");
			Columns.add("Customer_Number");
			Columns.add("Customer_sold_to_name");
			Columns.add("Customer_sold_to_address_line_1");
			Columns.add("Customer_sold_to_address_line_2");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getCustomerMasterList(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.customerMasterList);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Customer_Number");
			Columns.add("Customer_sold_to_address_line_1");
			Columns.add("Customer_sold_to_address_line_2");
			Columns.add("Customer_ship_to_address_line_1");
			Columns.add("Customer_ship_to_address_line_2");
			Columns.add("Sales_rep_number");
			Columns.add("rep_name");
			Columns.add("rep_address");
			Columns.add("rep_city");
			Columns.add("rep_state");
			Columns.add("rep_zip");
			Columns.add("Territory_Number");
			Columns.add("Territory_name");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getCustomerOpenOrderReport(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.customerOpenOrderReport);


			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Customer_Number");
			Columns.add("Order_Number");
			Columns.add("Item_Number");
			Columns.add("Item_Description");
			Columns.add("order_date");
			Columns.add("item_quantity_order");
			Columns.add("item_sales_price");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getItemOpenOrderReport(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.itemOpenOrderReport);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Item_Number");
			Columns.add("Order_Number");
			Columns.add("Customer_Number");
			Columns.add("Item_Description");
			Columns.add("order_date");
			Columns.add("item_quantity_order");
			Columns.add("item_sales_price");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getDailyInvoiceReport(Statement myStmt, int year, int month, int day)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getDailyInvoiceRegister(year, month, day));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Invoice_Number");
			Columns.add("invoice_date");
			Columns.add("Customer_Number");
			Columns.add("Customer_sold_to_name");
			Columns.add("total");
			Columns.add("Customer_total_invoice");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getMonthlyInvoiceReport(Statement myStmt, int year, int month)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getMonthlyInvoiceRegister(year, month));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Invoice_Number");
			Columns.add("invoice_date");
			Columns.add("Customer_Number");
			Columns.add("Customer_sold_to_name");
			Columns.add("total");
			Columns.add("Customer_total_invoice");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getStockStatusReport(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.stockStatusReport);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Item_Number");
			Columns.add("Item_Description");
			Columns.add("Item_Price");
			Columns.add("MTD_sales");
			Columns.add("YTD_sales");
			Columns.add("units_on_hand");
			Columns.add("units_allocated");
			Columns.add("reorder_point");
			Columns.add("units_on_hand");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
				if(Integer.parseInt(dataArray[j][5]) < Integer.parseInt(dataArray[j][7]))
				{
					dataArray[j][8] =  dataArray[j][8] + " *";
				}
			}
			return dataArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getReorderPointList(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.reorderPointList);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Item_Number");
			Columns.add("Item_Description");
			Columns.add("Item_Price");
			Columns.add("MTD_sales");
			Columns.add("YTD_sales");
			Columns.add("units_on_hand");
			Columns.add("units_allocated");
			Columns.add("reorder_point");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getVenderList(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.venderReport);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Vender_Number");
			Columns.add("vender_name");
			Columns.add("vender_city");
			Columns.add("vender_state");
			Columns.add("vender_zip");
			Columns.add("Item_Number");
			Columns.add("Item_Description");
			Columns.add("vender_item_price");
			Columns.add("minimum_order_quantity");
			Columns.add("lead_time");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getDailyCashReceiptJournal(Statement myStmt, int year, int month, int day)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getDailyCashReceiptJournal(year, month, day));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Payment_Number");
			Columns.add("Customer_Number");
			Columns.add("Customer_sold_to_name");
			Columns.add("payment_amount");
			Columns.add("payment_date");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getMonthlyCashReceiptJournal(Statement myStmt, int year, int month)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getMonthlyCashReceiptJournal(year, month));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Payment_Number");
			Columns.add("Customer_Number");
			Columns.add("Customer_sold_to_name");
			Columns.add("payment_amount");
			Columns.add("payment_date");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getCustomerMailingLabelInfo(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.customerMailingLabelInfo);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Customer_Number");
			Columns.add("Customer_sold_to_name");
			Columns.add("Customer_sold_to_address_line_1");
			Columns.add("Customer_sold_to_address_line_2");
			Columns.add("Customer_sold_to_city");
			Columns.add("Customer_sold_to_state");
			Columns.add("Customer_sold_to_zip");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] getMonthlySalesRepCommissionReport(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.monthlySalesRepCommissionReport);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Sales_rep_number");
			Columns.add("rep_name");
			Columns.add("rep_address");
			Columns.add("MTD_sales");
			Columns.add("YTD_sales");
			Columns.add("MTD_commission");
			Columns.add("YTD_commission");
			Columns.add("Commission_rate");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getTerritoryTable(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getTerritoryTable);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Territory_Number");
			Columns.add("Territory_name");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getSalesRepBasicTable(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getSalesRepBasicTable);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Sales_rep_number");
			Columns.add("rep_name");
			Columns.add("rep_address");
			Columns.add("rep_city");
			Columns.add("rep_state");
			Columns.add("rep_zip");
			Columns.add("territory_number");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getCustomerBasicTable(Statement myStmt, int Customer_Number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getCustomerBasicTable(Customer_Number));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Customer_Number");
			Columns.add("Customer_sold_to_name");
			Columns.add("Customer_sold_to_address_line_1");
			Columns.add("Customer_sold_to_address_line_2");
			Columns.add("Customer_sold_to_city");
			Columns.add("Customer_sold_to_state");
			Columns.add("Customer_sold_to_zip");
			Columns.add("Customer_sold_to_rep_number");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getCustomerSalesTable(Statement myStmt, int Customer_Number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getCustomerSalesTable(Customer_Number));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Customer_Number");
			Columns.add("Customer_MTD_sales");
			Columns.add("Customer_YTD_sales");
			Columns.add("Customer_balance");
			Columns.add("Customer_credit_limits");
			Columns.add("Customer_total_invoice");
			Columns.add("Customer_payment");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getOrderTable(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getOrderTable);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Order_Number");
			Columns.add("order_date");
			Columns.add("Customer_sold_to_name");
			Columns.add("Customer_PO_number");
			Columns.add("Customer_ship_to_name");
			Columns.add("Customer_ship_to_address_line_1");
			Columns.add("Customer_ship_to_address_line_2");
			Columns.add("Customer_ship_to_city");
			Columns.add("Customer_ship_to_state");
			Columns.add("Customer_ship_to_zip");
			
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String[][] getOrderItemInfo(Statement myStmt, int Order_Number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getOrderItemInfo(Order_Number));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Order_Number");
			Columns.add("Item_Number");
			Columns.add("item_quantity_order");
			Columns.add("item_quantity_shipped");
			Columns.add("item_sales_price");
			
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getSalesRepSalesTable(Statement myStmt, int Sales_rep_number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getSalesRepSalesTable(Sales_rep_number));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Sales_rep_number");
			Columns.add("MTD_sales");
			Columns.add("YTD_sales");
			Columns.add("MTD_commission");
			Columns.add("YTD_commission");
			Columns.add("Commission_rate");
			
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getItemBasicTable(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getItemBasicTable);

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Item_Number");
			Columns.add("Item_Description");
			Columns.add("Item_Price");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getItemSalesInfo(Statement myStmt, int Item_Number)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getItemSalesInfo(Item_Number));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Item_Number");
			Columns.add("MTD_sales");
			Columns.add("YTD_sales");
			Columns.add("units_on_hand");
			Columns.add("units_allocated");
			Columns.add("reorder_point");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getCustomerInvoiceTable(Statement myStmt, String orderSelection)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getCustomerInvoiceTable(orderSelection));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Invoice_Number");
			Columns.add("invoice_date");
			Columns.add("order_number");
			Columns.add("ship_charge");
			Columns.add("tax");
			Columns.add("total");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getOrderNumbers(Statement myStmt, String Customer_sold_to_name)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getOrderNumbers(Customer_sold_to_name));

			// 4. Process the result set
			ArrayList<String> Columns = new ArrayList<String>();
			Columns.add("Order_Number");
			String dataArray[][] = new String[100][100];
			int i =0;
			for(String column : Columns)
			{			
				dataArray[0][i] = column;
				i++;
			}
			for(int j =1; myRs.next(); j++) {
				for(i =0; i < Columns.size(); i++){
					dataArray[j][i] = myRs.getString(Columns.get(i));
				}
			}
			return dataArray;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getUser(Statement myStmt)
	{
		try {
			// 3. Execute SQL Query
			ResultSet myRs = myStmt.executeQuery(SQLstatements.getCurrentUser);
			String User = myRs.getString(1);
			return User;
		} catch (SQLException e) {
			AlertBox alertbox = new AlertBox();
			alertbox.display("Invalid Entry", e.toString());
			e.printStackTrace();
		}
		return null;
	}

}
