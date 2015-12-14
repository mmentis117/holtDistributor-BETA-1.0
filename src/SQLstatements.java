
public class SQLstatements {

	public static String territoryList = "SELECT territories.territory_number, territories.Territory_name, salespersonbasicinfo.Sales_rep_number, rep_name, rep_address, "
			+ "customerbasicinfo.Customer_Number, customerbasicinfo.Customer_sold_to_name, customerbasicinfo.Customer_sold_to_address_line_1, "
			+ "customerbasicinfo.Customer_sold_to_address_line_2 FROM holtdistributors.territories, holtdistributors.salespersonbasicinfo, "
			+ "holtdistributors.customerbasicinfo where territories.territory_number = salespersonbasicinfo.territory_number and "
			+ "salespersonbasicinfo.Sales_rep_number = customerbasicinfo.Customer_sold_to_rep_number "
			+ "order by territories.territory_number;";

	public static String customerMasterList = "SELECT customerbasicinfo.Customer_Number, customerbasicinfo.Customer_sold_to_address_line_1, "
			+"customerbasicinfo.Customer_sold_to_address_line_2, orderinfo.Customer_ship_to_address_line_1, orderinfo.Customer_ship_to_address_line_2, "
			+"salespersonbasicinfo.Sales_rep_number, salespersonbasicinfo.rep_name, salespersonbasicinfo.rep_address, salespersonbasicinfo.rep_city, "
			+"salespersonbasicinfo.rep_state, salespersonbasicinfo.rep_zip, territories.territory_number, territories.Territory_name "
			+"FROM holtdistributors.territories, holtdistributors.salespersonbasicinfo, "
			+"holtdistributors.customerbasicinfo, holtdistributors.orderinfo "
			+"where territories.territory_number = salespersonbasicinfo.territory_number and "
			+"salespersonbasicinfo.Sales_rep_number = customerbasicinfo.Customer_sold_to_rep_number and "
			+"customerbasicinfo.Customer_sold_to_name = orderinfo.Customer_sold_to_name "
			+"order by customerbasicinfo.Customer_Number;";

	public static String customerOpenOrderReport = "SELECT customerbasicinfo.Customer_Number, orderinfo.Order_Number , itembasicinfo.Item_Number, itembasicinfo.Item_Description," 
			+"orderinfo.order_date, orderanditeminfo.item_quantity_order, orderanditeminfo.item_sales_price "
			+"FROM holtdistributors.orderinfo, holtdistributors.orderanditeminfo, "
			+"holtdistributors.customerbasicinfo, holtdistributors.itembasicinfo "
			+"where orderinfo.Order_Number = orderanditeminfo.Order_Number and "
			+"orderinfo.Customer_sold_to_name = customerbasicinfo.Customer_sold_to_name and "
			+"orderanditeminfo.Item_Number = itembasicinfo.Item_Number "
			+"order by customerbasicinfo.Customer_Number;";

	public static String itemOpenOrderReport = "SELECT itembasicinfo.Item_Number, orderinfo.Order_Number , customerbasicinfo.Customer_Number, itembasicinfo.Item_Description,"
			+"orderinfo.order_date, orderanditeminfo.item_quantity_order, orderanditeminfo.item_sales_price "
			+"FROM holtdistributors.orderinfo, holtdistributors.orderanditeminfo, "
			+"holtdistributors.customerbasicinfo, holtdistributors.itembasicinfo "
			+"where orderinfo.Order_Number = orderanditeminfo.Order_Number and "
			+"orderinfo.Customer_sold_to_name = customerbasicinfo.Customer_sold_to_name and "
			+"orderanditeminfo.Item_Number = itembasicinfo.Item_Number "
			+"order by itembasicinfo.Item_Number;";

	public static String stockStatusReport = "SELECT itembasicinfo.Item_Number, itembasicinfo.Item_Description, itembasicinfo.Item_Price, itemsalesinfo.MTD_sales,"
			+ " itemsalesinfo.YTD_sales, itemsalesinfo.units_on_hand, itemsalesinfo.units_allocated, itemsalesinfo.reorder_point from holtdistributors.itembasicinfo,"
			+ " holtdistributors.itemsalesinfo where itembasicinfo.Item_Number = itemsalesinfo.Item_Number;";

	public static String reorderPointList = "SELECT itembasicinfo.Item_Number, itembasicinfo.Item_Description, itembasicinfo.Item_Price, itemsalesinfo.MTD_sales,"
			+ " itemsalesinfo.YTD_sales, itemsalesinfo.units_on_hand, itemsalesinfo.units_allocated, itemsalesinfo.reorder_point from holtdistributors.itembasicinfo,"
			+ " holtdistributors.itemsalesinfo where itembasicinfo.Item_Number = itemsalesinfo.Item_Number and itemsalesinfo.units_on_hand < itemsalesinfo.reorder_point;";

	public static String venderReport = "SELECT venderbasicinfo.Vender_Number, venderbasicinfo.vender_name, venderbasicinfo.vender_city, venderbasicinfo.vender_state, "
			+ "venderbasicinfo.vender_zip, venderanditeminfo.Item_Number, itembasicinfo.Item_Description, venderanditeminfo.vender_item_price, "
			+ "venderanditeminfo.minimum_order_quantity, venderanditeminfo.lead_time from holtdistributors.venderbasicinfo, holtdistributors.venderanditeminfo, "
			+ "holtdistributors.itembasicinfo where venderbasicinfo.Vender_Number = venderanditeminfo.Vender_Number and "
			+ "venderanditeminfo.Item_Number = itembasicinfo.Item_Number order by venderbasicinfo.Vender_Number;";

	public static String customerMailingLabelInfo = "SELECT customerbasicinfo.Customer_Number, customerbasicinfo.Customer_sold_to_name, "
			+ "customerbasicinfo.Customer_sold_to_address_line_1, customerbasicinfo.Customer_sold_to_address_line_2, customerbasicinfo.Customer_sold_to_city, "
			+ "customerbasicinfo.Customer_sold_to_state, customerbasicinfo.Customer_sold_to_zip from holtdistributors.customerbasicinfo "
			+ "order by customerbasicinfo.Customer_Number;";

	public static String monthlySalesRepCommissionReport = "SELECT salespersonbasicinfo.Sales_rep_number, salespersonbasicinfo.rep_name, salespersonbasicinfo.rep_address, "
			+ "salespersonsalesinfo.MTD_sales, salespersonsalesinfo.YTD_sales, salespersonsalesinfo.MTD_commission, salespersonsalesinfo.YTD_commission, "
			+ "salespersonsalesinfo.Commission_rate from holtdistributors.salespersonbasicinfo, holtdistributors.salespersonsalesinfo where "
			+ "salespersonbasicinfo.Sales_rep_number = salespersonsalesinfo.Sales_rep_number order by salespersonbasicinfo.Sales_rep_number;";

	public static String insertTerritory(int territory_number, String territory_name)
	{
		return "insert into territories (territory_number, Territory_name) values ("+territory_number+", '"+territory_name+"');";
	}

	public static String updateTerritoryByNumber(int territory_number, String territory_name)
	{
		return "update territories set territories.territory_name = '"+territory_name+"' where territories.Territory_Number = '"+territory_number+"';";
	}

	public static String deleteTerritory(int territory_number, String territory_name)
	{
		return "delete from territories where territories.Territory_Number = "+territory_number+" or territories.Territory_name = '"+territory_name+"';";
	}

	public static String insertSalesRepBasicInfo(int Sales_rep_number, String rep_name, String rep_address, String rep_city, String rep_state, int rep_zip, int territory_number)
	{
		return "insert into salespersonbasicinfo (Sales_rep_number, rep_name, rep_address, rep_city, rep_state, rep_zip, territory_number) "
				+ "values ("+Sales_rep_number+", '"+rep_name+"', '"+rep_address+"', '"+rep_city+"', '"+rep_state+"', "+rep_zip+", "+territory_number+");";
	}

	public static String updateSalesRepBasicInfoByNumber(int Sales_rep_number, String rep_name, String rep_address, String rep_city, String rep_state, int rep_zip, int territory_number)
	{
		return "update salespersonbasicinfo set rep_name = '"+rep_name+"', rep_address = '"+rep_address+"', rep_city = '"+rep_city+"', rep_state = '"+rep_state+"',"
				+ " rep_zip = "+rep_zip+", territory_number = "+territory_number+" where Sales_rep_number = "+Sales_rep_number+";";
	}
	public static String deleteSalesRepBasicInfo(int Sales_rep_number, String rep_name)
	{
		return "delete from salespersonbasicinfo where salespersonbasicinfo.Sales_rep_number = "+Sales_rep_number+" or salespersonbasicinfo.rep_name = '"+rep_name+"';";
	}

	public static String insertSalesRepSalesInfo(int Sales_rep_number, float MTD_sales, float YTD_sales, float MTD_commission, float YTD_commission, float Commission_rate)
	{
		return "insert into salespersonsalesinfo (Sales_rep_number, MTD_sales, YTD_sales, MTD_commission, YTD_commission, Commission_rate) "
				+ "values ("+Sales_rep_number+", "+MTD_sales+", "+YTD_sales+", "+MTD_commission+", "+YTD_commission+", "+Commission_rate+");";
	}

	public static String updateSalesRepSalesInfoByNumber(int Sales_rep_number, float MTD_sales, float YTD_sales, float MTD_commission, float YTD_commission, float Commission_rate)
	{
		return "update salespersonsalesinfo set MTD_sales = "+MTD_sales+", YTD_sales = "+YTD_sales+", MTD_commission = "+MTD_commission+","
				+ " YTD_commission = "+YTD_commission+", Commission_rate = "+Commission_rate+" where Sales_rep_number = "+Sales_rep_number+";";
	}
	
	public static String updateSalesRepSalesInfoByNumber(int Sales_rep_number, float MTD_sales, float YTD_sales)
	{
		return "update salespersonsalesinfo set MTD_sales = "+MTD_sales+", YTD_sales = "+YTD_sales+" where Sales_rep_number = "+Sales_rep_number+";";
	}

	public static String insertCustomerBasicInfo(int Customer_Number, String Customer_sold_to_name , String Customer_sold_to_address_line_1, 
			String Customer_sold_to_address_line_2, String Customer_sold_to_city, String Customer_sold_to_state, int Customer_sold_to_zip, int Customer_sold_to_rep_number)
	{
		return "insert into customerbasicinfo (Customer_Number, Customer_sold_to_name, Customer_sold_to_address_line_1, Customer_sold_to_address_line_2,"
				+ " Customer_sold_to_city, Customer_sold_to_state, Customer_sold_to_zip, Customer_sold_to_rep_number) "
				+ "values ("+Customer_Number+", '"+Customer_sold_to_name+"', '"+Customer_sold_to_address_line_1+"', '"+Customer_sold_to_address_line_2+"', '"
				+Customer_sold_to_city+"', '"+Customer_sold_to_state+"', "+Customer_sold_to_zip+", "+Customer_sold_to_rep_number+");";
	}

	public static String updateCustomerBasicInfoByNumber(int Customer_Number, String Customer_sold_to_name , String Customer_sold_to_address_line_1, 
			String Customer_sold_to_address_line_2, String Customer_sold_to_city, String Customer_sold_to_state, int Customer_sold_to_zip, int Customer_sold_to_rep_number)
	{
		return "update customerbasicinfo set Customer_sold_to_name = '"+Customer_sold_to_name+"', "
				+ "Customer_sold_to_address_line_1 = '"+Customer_sold_to_address_line_1+"', "
				+ "Customer_sold_to_address_line_2 = '"+Customer_sold_to_address_line_2+"', Customer_sold_to_city = '"+Customer_sold_to_city+"',"
				+ " Customer_sold_to_state = '"+Customer_sold_to_state+"', Customer_sold_to_zip = "+Customer_sold_to_zip+", "
				+ "Customer_sold_to_rep_number = "+Customer_sold_to_rep_number+" where Customer_Number = "+Customer_Number+";";
	}
	public static String deleteCustomerBasicInfo(int Customer_Number, String Customer_sold_to_name)
	{
		return "delete from customerbasicinfo where customerbasicinfo.Customer_Number = "+Customer_Number+" or customerbasicinfo.Customer_sold_to_name = '"+Customer_sold_to_name+"';";
	}

	public static String insertCustomerSalesInfo(int Customer_Number, float Customer_MTD_sales , float Customer_YTD_sales, 
			float Customer_balance, float Customer_credit_limits, float Customer_total_invoice, float Customer_payment)
	{
		return "insert into customersalesinfo (Customer_Number, Customer_MTD_sales, Customer_YTD_sales, Customer_balance,"
				+ " Customer_credit_limits, Customer_total_invoice, Customer_payment) "
				+ "values ("+Customer_Number+", "+Customer_MTD_sales+", "+Customer_YTD_sales+", "+Customer_balance+", "
				+Customer_credit_limits+", "+Customer_total_invoice+", "+Customer_payment+");";
	}

	public static String updateCustomerSalesInfoByNumber(int Customer_Number, float Customer_MTD_sales , float Customer_YTD_sales, 
			float Customer_balance, float Customer_credit_limits, float Customer_total_invoice, float Customer_payment)
	{
		return "update customersalesinfo set Customer_MTD_sales = '"+Customer_MTD_sales+"', "
				+ "Customer_YTD_sales = '"+Customer_YTD_sales+"', "
				+ "Customer_balance = '"+Customer_balance+"', Customer_credit_limits = '"+Customer_credit_limits+"',"
				+ " Customer_total_invoice = '"+Customer_total_invoice+"', Customer_payment = "+Customer_payment+" where Customer_Number = "+Customer_Number+";";
	}
	
	public static String updateCustomerSalesInfoByNumber(int Customer_Number, float Customer_MTD_sales , float Customer_YTD_sales, 
			float Customer_balance, float Customer_total_invoice)
	{
		return "update customersalesinfo set Customer_MTD_sales = '"+Customer_MTD_sales+"', "
				+ "Customer_YTD_sales = '"+Customer_YTD_sales+"', "
				+ "Customer_balance = '"+Customer_balance+"',"
				+ " Customer_total_invoice = '"+Customer_total_invoice+"' where Customer_Number = "+Customer_Number+";";
	}

	public static String insertItemBasicInfo(int Item_Number, String Item_Description , float Item_Price)
	{
		return "insert into itembasicinfo (Item_Number, Item_Description, Item_Price) "
				+ "values ("+Item_Number+", '"+Item_Description+"', "+Item_Price+");";
	}

	public static String updateItemBasicInfoByNumber(int Item_Number, String Item_Description , float Item_Price)
	{
		return "update itembasicinfo set Item_Description = '"+Item_Description+"' , "
				+ "Item_Price = "+Item_Price+" where Item_Number = "+Item_Number+";";
	}
	public static String deleteItemBasicInfo(int Item_Number)
	{
		return "delete from itembasicinfo where itembasicinfo.Item_Number = "+Item_Number+";";
	}

	public static String insertItemSalesInfo(int Item_Number, float MTD_sales , float YTD_sales, 
			int units_on_hand, int units_allocated, int reorder_point)
	{
		return "insert into itemsalesinfo (Item_Number, MTD_sales, YTD_sales, units_on_hand,"
				+ " units_allocated, reorder_point) "
				+ "values ("+Item_Number+", "+MTD_sales+", "+YTD_sales+", "+units_on_hand+", "+units_allocated+", "+reorder_point+");";
	}

	public static String updateItemSalesInfoByNumber(int Item_Number, float MTD_sales , float YTD_sales, 
			int units_on_hand, int units_allocated, int reorder_point)
	{
		return "update itemsalesinfo set MTD_sales = "+MTD_sales+", "
				+ "YTD_sales = "+YTD_sales+", "
				+ "units_on_hand = "+units_on_hand+", units_allocated = "+units_allocated+","
				+ " reorder_point = "+reorder_point+" where Item_Number = "+Item_Number+";";
	}



	public static String insertVenderBasicInfo(int Vender_Number, String vender_name, String vender_address, String vender_city, String vender_state, int vender_zip)
	{
		return "insert into venderbasicinfo (Vender_Number, vender_name, vender_address, vender_city, vender_state, vender_zip) "
				+ "values ("+Vender_Number+", '"+vender_name+"', '"+vender_address+"', '"+vender_city+"', '"+vender_state+"', "+vender_zip+");";
	}

	public static String updateVenderBasicInfoByNumber(int Vender_Number, String vender_name, String vender_address, String vender_city, String vender_state, int vender_zip)
	{
		return "update venderbasicinfo set vender_name = '"+vender_name+"', vender_address = '"+vender_address+"', vender_city = '"+vender_city+"', vender_state = '"+vender_state+"',"
				+ " vender_zip = "+vender_zip+" where Vender_Number = "+Vender_Number+";";
	}
	public static String deleteVenderBasicInfo(int Vender_Number, String vender_name)
	{
		return "delete from venderbasicinfo where venderbasicinfo.Vender_Number = "+Vender_Number+" or venderbasicinfo.vender_name = '"+vender_name+"';";
	}


	public static String insertVenderAndItemInfo(int Vender_Number, int Item_Number, float vender_item_price, float minimum_order_quantity, String lead_time)
	{
		return "insert into venderanditeminfo (Vender_Number, Item_Number, vender_item_price, minimum_order_quantity, lead_time) "
				+ "values ("+Vender_Number+", "+Item_Number+", "+vender_item_price+", "+minimum_order_quantity+", '"+lead_time+"');";
	}

	public static String updateVenderAndItemInfoByNumber(int Vender_Number, int Item_Number, float vender_item_price, float minimum_order_quantity, String lead_time)
	{
		return "update venderanditeminfo set Item_Number = '"+Item_Number+"', vender_item_price = '"+vender_item_price+"', minimum_order_quantity = '"+minimum_order_quantity+"', lead_time = '"+lead_time
				+"' where Vender_Number = "+Vender_Number+" and Item_Number = "+Item_Number+";";
	}
	public static String deleteVenderAndItemInfo(int Vender_Number, int Item_Number)
	{
		return "delete from venderanditeminfo where venderanditeminfo.Vender_Number = "+Vender_Number+" and venderanditeminfo.Item_Number = "+Item_Number+";";
	}


	public static String insertOrderInfo(int Order_Number, String order_date, String Customer_sold_to_name, int Customer_PO_number, String Customer_ship_to_name, 
			String Customer_ship_to_address_line_1, String Customer_ship_to_address_line_2, String Customer_ship_to_city, String Customer_ship_to_state, int Customer_ship_to_zip)
	{
		return "insert into orderinfo (Order_Number, order_date, Customer_sold_to_name, Customer_PO_number, Customer_ship_to_name, Customer_ship_to_address_line_1, "
				+ "Customer_ship_to_address_line_2, Customer_ship_to_city, Customer_ship_to_state, Customer_ship_to_zip) "
				+ "values ("+Order_Number+", '"+order_date+"', '"+Customer_sold_to_name+"', '"+Customer_PO_number+"', '"+Customer_ship_to_name+"', '"+Customer_ship_to_address_line_1
				+"', '"+Customer_ship_to_address_line_2+"', '"+Customer_ship_to_city+"', '"+Customer_ship_to_state+"', "+Customer_ship_to_zip+");";
	}

	public static String updateOrderInfoByNumber(int Order_Number, String order_date, String Customer_sold_to_name, int Customer_PO_number, String Customer_ship_to_name, 
			String Customer_ship_to_address_line_1, String Customer_ship_to_address_line_2, String Customer_ship_to_city, String Customer_ship_to_state, int Customer_ship_to_zip)
	{
		return "update orderinfo set order_date = '"+order_date+"', Customer_sold_to_name = '"+Customer_sold_to_name+"', Customer_PO_number = '"+Customer_PO_number
				+"', Customer_ship_to_name = '"+Customer_ship_to_name+"',"
				+ " Customer_ship_to_address_line_1 = '"+Customer_ship_to_address_line_1+"', Customer_ship_to_address_line_2 = '"+Customer_ship_to_address_line_2
				+"', Customer_ship_to_city = '"+Customer_ship_to_city+"', Customer_ship_to_state = '"+Customer_ship_to_state+"', Customer_ship_to_zip = "
				+Customer_ship_to_zip+" where Order_Number = "+Order_Number+";";
	}
	public static String deleteOrderInfo(int Order_Number)
	{
		return "delete from orderinfo where orderinfo.Order_Number = "+Order_Number+";";
	}


	public static String insertOrderAndItemInfo(int Order_Number, int Item_Number, int item_quantity_order, int item_quantity_shipped, float item_sales_price)
	{
		return "insert into orderanditeminfo (Order_Number, Item_Number, item_quantity_order, item_quantity_shipped, item_sales_price) "
				+ "values ("+Order_Number+", "+Item_Number+", "+item_quantity_order+", "+item_quantity_shipped+", "+item_sales_price+");";
	}

	public static String updateOrderAndItemInfoByNumber(int Order_Number, int Item_Number, int item_quantity_order, int item_quantity_shipped, float item_sales_price)
	{
		return "update orderanditeminfo set Item_Number = "+Item_Number+", item_quantity_order = "+item_quantity_order+", item_quantity_shipped = "
				+item_quantity_shipped+", item_sales_price = "+item_sales_price+" where Order_Number = "+Order_Number+" and Item_Number = "+Item_Number+";";
	}
	public static String deleteOrderAndItemInfo(int Order_Number, int Item_Number)
	{
		return "delete from orderanditeminfo where orderanditeminfo.Order_Number = "+Order_Number+" and orderanditeminfo.Item_Number = "+Item_Number+";";
	}


	public static String insertInvoiceInfo(int Invoice_Number, String invoice_date, int order_number, float ship_charge, float tax)
	{
		return "insert into invoice (Invoice_Number, invoice_date, order_number, ship_charge, tax) "
				+ "values ("+Invoice_Number+", '"+invoice_date+"', "+order_number+", "+ship_charge+", "+tax+");";
	}

	public static String updateInvoiceInfoByNumber(int Invoice_Number, String invoice_date, int order_number, float ship_charge, float tax)
	{
		return "update invoice set tax = "+tax+", invoice_date = '"+invoice_date+"', order_number = "
				+order_number+", ship_charge = "+ship_charge+" where Invoice_Number = "+Invoice_Number+";";
	}
	
	public static String updateInvoiceInfoByNumber(int Invoice_Number, float total)
	{
		return "update invoice set total = "+total+" where Invoice_Number = "+Invoice_Number+";";
	}
	
	public static String deleteInvoiceInfo(int Invoice_Number)
	{
		return "delete from invoice where invoice.Invoice_Number = "+Invoice_Number+";";
	}



	public static String insertPaymentInfo(int Payment_Number, int customer_number, String payment_date, float payment_amount)
	{
		return "insert into payment (Payment_Number, customer_number, payment_date, payment_amount) "
				+ "values ("+Payment_Number+", "+customer_number+", '"+payment_date+"', "+payment_amount+");";
	}

	public static String updatePaymentInfoByNumber(int Payment_Number, int customer_number, String payment_date, float payment_amount)
	{
		return "update payment set customer_number = "+customer_number+", payment_date = '"+payment_date+"', payment_amount = "
				+payment_amount+" where Payment_Number = "+Payment_Number+";";
	}
	public static String deletePaymentInfo(int Payment_Number)
	{
		return "delete from payment where payment.Payment_Number = "+Payment_Number+";";
	}
	
	public static String insertCustomerLoginBasicInfo(String Customer_sold_to_name , String Customer_sold_to_address_line_1, 
			String Customer_sold_to_address_line_2, String Customer_sold_to_city, String Customer_sold_to_state, int Customer_sold_to_zip, int Customer_sold_to_rep_number)
	{
		return "insert into customerbasicinfo (Customer_sold_to_name, Customer_sold_to_address_line_1, Customer_sold_to_address_line_2,"
				+ " Customer_sold_to_city, Customer_sold_to_state, Customer_sold_to_zip, Customer_sold_to_rep_number) "
				+ "values ('"+Customer_sold_to_name+"', '"+Customer_sold_to_address_line_1+"', '"+Customer_sold_to_address_line_2+"', '"
				+Customer_sold_to_city+"', '"+Customer_sold_to_state+"', "+Customer_sold_to_zip+", "+Customer_sold_to_rep_number+");";
	}
	
	public static String insertCustomerLoginSalesInfo(float Customer_MTD_sales , float Customer_YTD_sales, 
			float Customer_balance, float Customer_credit_limits, float Customer_total_invoice, float Customer_payment)
	{
		return "insert into customersalesinfo (Customer_MTD_sales, Customer_YTD_sales, Customer_balance,"
				+ " Customer_credit_limits, Customer_total_invoice, Customer_payment) "
				+ "values ("+Customer_MTD_sales+", "+Customer_YTD_sales+", "+Customer_balance+", "
				+Customer_credit_limits+", "+Customer_total_invoice+", "+Customer_payment+");";
	}
	
	public static String insertCustomerOrderInfo(String order_date, String Customer_sold_to_name, int Customer_PO_number, String Customer_ship_to_name, 
			String Customer_ship_to_address_line_1, String Customer_ship_to_address_line_2, String Customer_ship_to_city, String Customer_ship_to_state, int Customer_ship_to_zip)
	{
		return "insert into orderinfo (order_date, Customer_sold_to_name, Customer_PO_number, Customer_ship_to_name, Customer_ship_to_address_line_1, "
				+ "Customer_ship_to_address_line_2, Customer_ship_to_city, Customer_ship_to_state, Customer_ship_to_zip) "
				+ "values ('"+order_date+"', '"+Customer_sold_to_name+"', '"+Customer_PO_number+"', '"+Customer_ship_to_name+"', '"+Customer_ship_to_address_line_1
				+"', '"+Customer_ship_to_address_line_2+"', '"+Customer_ship_to_city+"', '"+Customer_ship_to_state+"', "+Customer_ship_to_zip+");";
	}
	
	public static String insertUserLoginInfo(int Customer_Number , String username, String password)
	{
		return "insert into userinfo (Customer_Number, username, password) "
				+ "values ('"+Customer_Number+"', '"+username+"', '"+password+"');";
	}
	
	public static String getCustomerNumber(String customerName)
	{
		return "SELECT Customer_Number FROM holtdistributors.customerbasicinfo Where Customer_sold_to_name = '"+customerName+"';";
	}

	public static String insertCustomerPaymentTable(int customer_number, String payment_date, float payment_amount)
	{
		return "insert into payment (customer_number, payment_date, payment_amount) "
				+ "values ("+customer_number+", '"+payment_date+"', "+payment_amount+");";
	}


	public static String getDailyInvoiceRegister(int year, int month, int day)
	{
		String searchDate = year + "-" + month + "-" + day;
		return "Select invoice.Invoice_Number, invoice.invoice_date, customerbasicinfo.Customer_Number, customerbasicinfo.Customer_sold_to_name,"
		+" invoice.total, customersalesinfo.Customer_total_invoice from holtdistributors.customerbasicinfo, holtdistributors.customersalesinfo,"
		+" holtdistributors.invoice, holtdistributors.orderinfo where invoice.order_number = orderinfo.order_number and "
		+" orderinfo.Customer_sold_to_name = customerbasicinfo.Customer_sold_to_name and customerbasicinfo.Customer_Number = "
		+" customersalesinfo.Customer_Number and invoice.invoice_date between '"+searchDate+"' and '"+searchDate+" 23:59:59';";
	}

	public static String getMonthlyInvoiceRegister(int year, int month)
	{
		String searchDate = year + "-" + month;
		return "Select invoice.Invoice_Number, invoice.invoice_date, customerbasicinfo.Customer_Number, customerbasicinfo.Customer_sold_to_name,"
		+" invoice.total, customersalesinfo.Customer_total_invoice from holtdistributors.customerbasicinfo, holtdistributors.customersalesinfo,"
		+" holtdistributors.invoice, holtdistributors.orderinfo where invoice.order_number = orderinfo.order_number and "
		+" orderinfo.Customer_sold_to_name = customerbasicinfo.Customer_sold_to_name and customerbasicinfo.Customer_Number = "
		+" customersalesinfo.Customer_Number and invoice.invoice_date between '"+searchDate+"-1' and '"+searchDate+"-31 23:59:59';";
	}

	public static String getDailyCashReceiptJournal(int year, int month, int day)
	{
		String searchDate = year + "-" + month + "-" + day;
		return "SELECT payment.Payment_Number, customerbasicinfo.Customer_Number, customerbasicinfo.Customer_sold_to_name, payment.payment_amount, payment.payment_date "
		+"from holtdistributors.payment, holtdistributors.customerbasicinfo where payment.customer_number = customerbasicinfo.Customer_Number and payment.payment_date between '"+searchDate+"' and '"+searchDate+" 23:59:59'"
		+"order by payment.Payment_Number;";

	}

	public static String getMonthlyCashReceiptJournal(int year, int month)
	{
		String searchDate = year + "-" + month;
		return "SELECT payment.Payment_Number, customerbasicinfo.Customer_Number, customerbasicinfo.Customer_sold_to_name, payment.payment_amount, payment.payment_date "
		+"from holtdistributors.payment, holtdistributors.customerbasicinfo where payment.customer_number = customerbasicinfo.Customer_Number and payment.payment_date between '"+searchDate+"-1' and '"+searchDate+"-31 23:59:59'"
		+"order by payment.Payment_Number;";

	}
	
	public static String getTerritoryTable = "SELECT * FROM holtdistributors.territories;";
	
	public static String getItemBasicTable = "SELECT * FROM holtdistributors.itembasicinfo;";
	
	public static String getSalesRepBasicTable = "SELECT * FROM holtdistributors.salespersonbasicinfo;";
	
	public static String getOrderTable = "SELECT * FROM holtdistributors.orderinfo;";
	
	public static String getCustomerSalesTable(int Customer_Number)
	{
		return "SELECT * FROM holtdistributors.customersalesinfo where Customer_Number = "+Customer_Number+";";
	}
	
	public static String getCustomerBalance(int Customer_Number)
	{
		return "SELECT Customer_balance FROM holtdistributors.customersalesinfo where Customer_Number = "+Customer_Number+";";
	}
	
	public static String getCustomerBasicTable(int Customer_Number)
	{
		return "SELECT * FROM holtdistributors.customerbasicinfo where Customer_Number = "+Customer_Number+";";
	}
	
	public static String getOrderNumber(String Customer_sold_to_name)
	{
		return "SELECT Order_Number FROM holtdistributors.orderinfo where Customer_sold_to_name = '"+Customer_sold_to_name+"';";
	}
	
	public static String getOrderNumbers(String Customer_sold_to_name)
	{
		return "SELECT Order_Number FROM holtdistributors.orderinfo where Customer_sold_to_name = '"+Customer_sold_to_name+"';";
	}
	
	public static String getCustomerNameByCustomerNumber(int Customer_Number)
	{
		return "SELECT Customer_sold_to_name FROM holtdistributors.customerbasicinfo where Customer_Number = "+Customer_Number+";";
	}
	
	public static String getCustomerNameByOrderNumber(int Order_Number)
	{
		return "SELECT Customer_sold_to_name FROM holtdistributors.orderinfo where Order_Number = "+Order_Number+";";
	}
	
	public static String getCustomerSalesRepNumber(int Customer_Number)
	{
		return "SELECT Customer_sold_to_rep_number FROM holtdistributors.customerbasicinfo where Customer_Number = "+Customer_Number+";";
	}
	
	public static String getSalesRepSalesTable(int Sales_rep_number)
	{
		return "SELECT * FROM holtdistributors.salespersonsalesinfo where Sales_rep_number = "+Sales_rep_number+";";
	}
	
	public static String getInvoiceNumber(String invoice_date, int order_number, float ship_charge, float tax)
	{
		return "SELECT Invoice_Number FROM holtdistributors.invoice where invoice_date = '"+invoice_date+"' and order_number = "+order_number+" "
				+ "and ship_charge = "+ship_charge+" and tax = "+tax+";";
	}
	
	public static String getInvoiceTotal(int Invoice_Number)
	{
		return "SELECT total FROM holtdistributors.invoice where Invoice_Number = "+Invoice_Number+";";
	}
	
	public static String getCustomerInvoiceTable(String orderSelection)
	{
		return "SELECT * FROM holtdistributors.invoice where "+orderSelection+";";
	}
	
	public static String getOrderItemInfo(int Order_Number)
	{
		return "SELECT * FROM holtdistributors.orderanditeminfo where Order_Number = "+Order_Number+";";
	}
	
	public static String getItemDescriptionByNumber(int Item_Number)
	{
		return "SELECT Item_Description FROM holtdistributors.itembasicinfo where Item_Number = "+Item_Number+";";
	}
	
	public static String getItemSalesInfo(int Item_Number)
	{
		return "SELECT * FROM holtdistributors.itemsalesinfo where Item_Number = "+Item_Number+";";
	}
	

	public static String updateItemSalesInfoByNumber(int Item_Number, float MTD_sales , float YTD_sales, 
			int units_on_hand, int units_allocated)
	{
		return "update itemsalesinfo set MTD_sales = "+MTD_sales+", "
				+ "YTD_sales = "+YTD_sales+", "
				+ "units_on_hand = "+units_on_hand+", units_allocated = "+units_allocated
				+ " where Item_Number = "+Item_Number+";";
	}
	
	public static String updateCustomerBalanceAndPayment(int Customer_Number, float Customer_balance, float Customer_payment)
	{
		return "update customersalesinfo set Customer_balance = "+Customer_balance+", Customer_payment = "+Customer_payment+" where Customer_Number = "+Customer_Number+";";
	}
	
	public static String updateQuantityShipped(int item_quantity_shipped, int Order_Number, int Item_Number)
	{
		return "update orderanditeminfo set item_quantity_shipped = "+item_quantity_shipped+" where Order_Number = "+Order_Number+" and Item_Number = "+Item_Number+";";
	}
	
	public static String sendThroughCustomerInvoiceInfo(String invoice_date, int order_number, float ship_charge, float tax, float total)
	{
		return "insert into invoice (invoice_date, order_number, ship_charge, tax, total) "
				+ "values ('"+invoice_date+"', "+order_number+", "+ship_charge+", "+tax+", "+total+");";
	}
	
	public static String getCurrentUser = "SELECT USER();";
}
