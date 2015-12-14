import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
public class Driver extends Application implements EventHandler<ActionEvent>{

	public static void main(String[] args) {
		launch(args);

	}

	String inputSelection = "";
	Stage window;
	Scene startScene, HomeScene, TableScene, inputDataScene, LoginScene, invoiceItemScene;
	Button startButton, territoryListButton, backToHomeButton, customerMasterListButton, customerOpenOrderReportButton, ItemOpenOrderReportButton,
	DailyInvoiceReportButton, MonthlyInvoiceReportButton, StockStatusReportButton, ReorderPointListButton, VenderListButton, DailyCashReceiptJournalButton, 
	MonthlyCashReceiptJournalButton, CustomerMailingLabelInfoButton, MonthlySalesRepCommissionReportButton, inputAcceptButton, insertTerritory, updateTerritory, deleteTerritory,
	insertSalesRepInfo, updateSalesRepInfo, deleteSalesRepInfo, insertCustomerInfo, updateCustomerInfo, deleteCustomerInfo, insertItemInfo, updateItemInfo, deleteItemInfo,
	insertVenderInfo, updateVenderInfo, deleteVenderInfo, insertVenderItemInfo, updateVenderItemInfo, deleteVenderItemInfo, insertOrderInfo, updateOrderInfo, deleteOrderInfo,
	insertOrderItemInfo, updateOrderItemInfo, deleteOrderItemInfo, insertInvoiceInfo, updateInvoiceInfo, deleteInvoiceInfo, insertPaymentInfo, updatePaymentInfo, 
	deletePaymentInfo, createLoginButton, acceptLoginButton, sendThroughInvoice, backToStartButton;
	ArrayList<TextField> textFields = new ArrayList<TextField>();
	ArrayList<ComboBox<String>> comboBoxList = new ArrayList<ComboBox<String>>();
	Connection myConn;
	Statement myStmt;
	float shipCharge, tax;
	int orderNumber, invoiceNumber;

	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

		//Start Scene Layout
		startSceneLayout();

		//Home Scene Layout
		homeSceneLayout();

		//Display Scene 1 at start
		window.setScene(startScene);
		window.setTitle("Holt Distributor Home");
		window.show();

	}

	//When button is clicked, handle() gets called
	//Button click is an ActionEvent (also MouseEvents, TouchEvents, etc...)
	public void handle(ActionEvent event) {
		if (event.getSource() == startButton) {
			try{
				if(textFields.get(0).getText().equalsIgnoreCase("root"))
				{
					// 1. Get a connection to database
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/holtdistributors", textFields.get(0).getText(), textFields.get(1).getText());

					// 2. Create a statement
					myStmt = myConn.createStatement();
					inputSelection = "goToHomeScene";
					reset();
				}
				else
				{
					// 1. Get a connection to database
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/holtdistributors", "abc", "123");

					// 2. Create a statement
					myStmt = myConn.createStatement();
					// 3. Execute SQL Query
					ResultSet myRs = myStmt.executeQuery("SELECT Customer_Number FROM holtdistributors.userinfo where username = '"+ textFields.get(0).getText()+"' and password = '"+ textFields.get(1).getText()+"';");
					//load customer scene
					if(myRs.next())
					{
						customerBranch customerView = new customerBranch();
						customerView.customerNumber =  myRs.getInt("Customer_Number");
						customerView.setDriver(Driver.this);
						customerView.customerSceneLayout();
						window.setScene(customerView.customerScene);
					}
					else
					{
						AlertBox alertbox = new AlertBox();
						alertbox.display("Invalid Entry", "You have entered an invalid"
								+ "\n Username or Password.");
					}
					//need to disconnect from database on back
				}
				
			}
			catch (Exception exc){
				AlertBox alertbox = new AlertBox();
				alertbox.display("Invalid Entry", "You have entered an invalid"
						+ "\n Username or Password.");
				exc.printStackTrace();
			}

		}

		else if (event.getSource() == backToStartButton){
			inputSelection = "backToStart";
			reset();
		}
		
		else if (event.getSource() == createLoginButton){
			//Table Scene Layout
			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Username");
			dataInputs1.add("Password");
			dataInputs1.add("Customer Name");
			dataInputs1.add("Address Line 1");
			dataInputs1.add("Address Line 2");
			dataInputs1.add("City");
			dataInputs1.add("State");
			dataInputs1.add("Zip");
			dataInputs1.add("Sales Rep Number");
			
			try{
			// 1. Get a connection to database
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/holtdistributors", "abc", "123");

			// 2. Create a statement
				myStmt = myConn.createStatement();
			}
			catch (Exception exc){
				AlertBox alertbox = new AlertBox();
				alertbox.display("System Error", "System Error: default SQL username deleted");
				exc.printStackTrace();
			}
			textFields.clear();
			inputSelection = "insertLoginInfo";
			inputDataLayout("Holt Distributor Create Login", dataInputs1, 500, 550);
			
			window.setScene(inputDataScene);
		}

		else if (event.getSource() == backToHomeButton){
				comboBoxList.clear();
				textFields.clear();
				if(!(inputSelection.equals("insertLoginInfo")))
				{
					window.setScene(HomeScene);
				}
				else
				{
					try{
						myConn.close();
					}
					catch(Exception e)
					{
						AlertBox alertbox = new AlertBox();
						alertbox.display("Couldnt Close Connection.", e.toString());
						e.printStackTrace();
					}
					reset();
				}
		}
		else if (event.getSource() == territoryListButton){
			//Table Scene Layout
			createTableLayout(holtDistributorFunctions.getTerritoryList(myStmt));
			window.setScene(TableScene);
		}
		else if (event.getSource() == customerMasterListButton){
			//Table Scene Layout
			createTableLayout(holtDistributorFunctions.getCustomerMasterList(myStmt));
			window.setScene(TableScene);
		}
		else if (event.getSource() == customerOpenOrderReportButton){
			//Table Scene Layout
			createTableLayout(holtDistributorFunctions.getCustomerOpenOrderReport(myStmt));
			window.setScene(TableScene);
		}
		else if (event.getSource() == ItemOpenOrderReportButton){
			//Table Scene Layout
			createTableLayout(holtDistributorFunctions.getItemOpenOrderReport(myStmt));
			window.setScene(TableScene);
		}  
		else if (event.getSource() == StockStatusReportButton){
			//Table Scene Layout
			createTableLayout(holtDistributorFunctions.getStockStatusReport(myStmt));
			window.setScene(TableScene);
		}  
		else if (event.getSource() == ReorderPointListButton){
			//Table Scene Layout
			createTableLayout(holtDistributorFunctions.getReorderPointList(myStmt));
			window.setScene(TableScene);
		}  
		else if (event.getSource() == VenderListButton){
			//Table Scene Layout
			createTableLayout(holtDistributorFunctions.getVenderList(myStmt));
			window.setScene(TableScene);
		}  
		else if (event.getSource() == CustomerMailingLabelInfoButton){
			//Table Scene Layout
			createTableLayout(holtDistributorFunctions.getCustomerMailingLabelInfo(myStmt));
			window.setScene(TableScene);
		}  
		else if (event.getSource() == MonthlySalesRepCommissionReportButton){
			//Table Scene Layout
			createTableLayout(holtDistributorFunctions.getMonthlySalesRepCommissionReport(myStmt));
			window.setScene(TableScene);
		}  
		
		else if (event.getSource() == sendThroughInvoice){    	

			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Order Number");
			dataInputs1.add("Ship Charge");
			dataInputs1.add("Tax");

			inputSelection = "sendThroughInvoice";
			inputDataLayout("Send Through Invoice", dataInputs1, 500, 550);
			//Enter input Scene
			window.setScene(inputDataScene);
		}
		
		
		else if (event.getSource() == DailyInvoiceReportButton || event.getSource() == DailyCashReceiptJournalButton){    	

			ArrayList<String> dataInputs = new ArrayList<String>();
			dataInputs.add("Day");
			dataInputs.add("Month");
			dataInputs.add("Year");

			//Input Scene Layout
			if (event.getSource() == DailyInvoiceReportButton) inputSelection = "DailyInvoiceReport";
			else inputSelection = "DailyCashReceiptJournal";
			inputDataLayout("For What Day/Month/Year?", dataInputs, 350, 200);


			//Enter input Scene
			window.setScene(inputDataScene);

		} 
		else if (event.getSource() == MonthlyInvoiceReportButton || event.getSource() == MonthlyCashReceiptJournalButton){    	

			ArrayList<String> dataInputs = new ArrayList<String>();
			dataInputs.add("Month");
			dataInputs.add("Year");

			//Input Scene Layout
			if (event.getSource() == MonthlyInvoiceReportButton) inputSelection = "MonthlyInvoiceReport";
			else inputSelection = "MonthlyCashReceiptJournal";

			inputDataLayout("For What Month/Year?", dataInputs, 350, 200);
			//Enter input Scene
			window.setScene(inputDataScene);

		} 
		else if (event.getSource() == insertTerritory || event.getSource() == updateTerritory || event.getSource() == deleteTerritory){    	

			ArrayList<String> dataInputs = new ArrayList<String>();
			dataInputs.add("Territory Number");
			dataInputs.add("Territory Name");

			//Input Scene Layout
			if(event.getSource() == insertTerritory)
			{
				inputSelection = "insertTerritory";
				inputDataLayout("Insert Territory", dataInputs, 450, 150);
			}
			else if(event.getSource() == updateTerritory)
			{
				inputSelection = "updateTerritory";
				inputDataLayout("Update Territory By Number", dataInputs, 450, 150);
			}
			else
			{
				inputSelection = "deleteTerritory";
				inputDataLayout("Delete Territory", dataInputs, 450, 150);

			}

			//Enter input Scene
			window.setScene(inputDataScene);

		}

		else if (event.getSource() == insertSalesRepInfo || event.getSource() == updateSalesRepInfo || event.getSource() == deleteSalesRepInfo){    	

			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Sales Rep Number");
			dataInputs1.add("Rep Name");
			dataInputs1.add("Rep Address");
			dataInputs1.add("Rep City");
			dataInputs1.add("Rep State");
			dataInputs1.add("Rep Zip");
			dataInputs1.add("Territory Number");
			dataInputs1.add("MTD Sales");
			dataInputs1.add("YTD Sales");
			dataInputs1.add("MTD Commission");
			dataInputs1.add("YTD Commission");
			dataInputs1.add("Commission Rate");

			ArrayList<String> dataInputs2 = new ArrayList<String>();
			dataInputs2.add("Sales Rep Number");
			dataInputs2.add("Rep Name");

			//Input Scene Layout
			if(event.getSource() == insertSalesRepInfo)
			{
				inputSelection = "insertSalesRep";
				inputDataLayout("Insert Sales Rep Information", dataInputs1, 500, 550);
			}
			else if(event.getSource() == updateSalesRepInfo)
			{
				inputSelection = "updateSalesRep";
				inputDataLayout("Update Sales Rep By Number", dataInputs1, 500, 550);
			}
			else
			{
				inputSelection = "deleteSalesRep";
				inputDataLayout("Delete Sales Rep By Name or Number", dataInputs2, 500, 550);
			}

			//Enter input Scene
			window.setScene(inputDataScene);

		} 

		else if (event.getSource() == insertCustomerInfo || event.getSource() == updateCustomerInfo || event.getSource() == deleteCustomerInfo){    	

			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Customer Number");
			dataInputs1.add("Customer Sold to Name");
			dataInputs1.add("Customer Sold to Address Line 1");
			dataInputs1.add("Customer Sold to Address Line 2");
			dataInputs1.add("Customer Sold to City");
			dataInputs1.add("Customer Sold to State");
			dataInputs1.add("Customer Sold to Zip");
			dataInputs1.add("Customer Sold to Rep Number");
			dataInputs1.add("Customer MTD Sales");
			dataInputs1.add("Customer YTD Sales");
			dataInputs1.add("Customer Balance");
			dataInputs1.add("Customer Credit Limits");
			dataInputs1.add("Customer Total Invoice");
			dataInputs1.add("Customer Payment");

			ArrayList<String> dataInputs2 = new ArrayList<String>();
			dataInputs2.add("Customer Number");
			dataInputs2.add("Customer Sold to Name");

			//Input Scene Layout
			if(event.getSource() == insertCustomerInfo)
			{
				inputSelection = "insertCustomer";
				inputDataLayout("Insert Customer Information", dataInputs1, 500, 550);
			}
			else if(event.getSource() == updateCustomerInfo)
			{
				inputSelection = "updateCustomer";
				inputDataLayout("Update Customer By Number", dataInputs1, 500, 550);
			}
			else
			{
				inputSelection = "deleteCustomer";
				inputDataLayout("Delete Customer By Name or Number", dataInputs2, 500, 550);
			}

			//Enter input Scene
			window.setScene(inputDataScene);

		} 

		else if (event.getSource() == insertItemInfo || event.getSource() == updateItemInfo || event.getSource() == deleteItemInfo){    	

			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Item Number");
			dataInputs1.add("Item Description");
			dataInputs1.add("Item Price");
			dataInputs1.add("MTD Sales");
			dataInputs1.add("YTD Sales");
			dataInputs1.add("Units on Hand");
			dataInputs1.add("Units Allocated");
			dataInputs1.add("Reorder Point");

			ArrayList<String> dataInputs2 = new ArrayList<String>();
			dataInputs2.add("Item Number");

			//Input Scene Layout
			if(event.getSource() == insertItemInfo)
			{
				inputSelection = "insertItem";
				inputDataLayout("Insert Item Information", dataInputs1, 500, 550);
			}
			else if(event.getSource() == updateItemInfo)
			{
				inputSelection = "updateItem";
				inputDataLayout("Update Item By Number", dataInputs1, 500, 550);
			}
			else
			{
				inputSelection = "deleteItem";
				inputDataLayout("Delete Item By Number", dataInputs2, 500, 550);
			}

			//Enter input Scene
			window.setScene(inputDataScene);

		} 


		else if (event.getSource() == insertVenderInfo || event.getSource() == updateVenderInfo || event.getSource() == deleteVenderInfo){    	

			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Vender Number");
			dataInputs1.add("Vender Name");
			dataInputs1.add("Vender Address");
			dataInputs1.add("Vender City");
			dataInputs1.add("Vender State");
			dataInputs1.add("Vender Zip");

			ArrayList<String> dataInputs2 = new ArrayList<String>();
			dataInputs2.add("Vender Number");
			dataInputs2.add("Vender Name");

			//Input Scene Layout
			if(event.getSource() == insertVenderInfo)
			{
				inputSelection = "insertVender";
				inputDataLayout("Insert Vender Information", dataInputs1, 500, 550);
			}
			else if(event.getSource() == updateVenderInfo)
			{
				inputSelection = "updateVender";
				inputDataLayout("Update Vender By Number", dataInputs1, 500, 550);
			}
			else
			{
				inputSelection = "deleteVender";
				inputDataLayout("Delete Vender By Number or Name", dataInputs2, 500, 550);
			}

			//Enter input Scene
			window.setScene(inputDataScene);

		}

		else if (event.getSource() == insertVenderItemInfo || event.getSource() == updateVenderItemInfo || event.getSource() == deleteVenderItemInfo){    	

			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Vender Number");
			dataInputs1.add("Item Number");
			dataInputs1.add("Vender Item Price");
			dataInputs1.add("Minimum Order Quantity");
			dataInputs1.add("Lead Time");

			ArrayList<String> dataInputs2 = new ArrayList<String>();
			dataInputs2.add("Vender Number");
			dataInputs2.add("Item Number");

			//Input Scene Layout
			if(event.getSource() == insertVenderItemInfo)
			{
				inputSelection = "insertVenderItem";
				inputDataLayout("Insert Vender/Item Information", dataInputs1, 500, 550);
			}
			else if(event.getSource() == updateVenderItemInfo)
			{
				inputSelection = "updateVenderItem";
				inputDataLayout("Update Vender/Item By Vender Number", dataInputs1, 500, 550);
			}
			else
			{
				inputSelection = "deleteVenderItem";
				inputDataLayout("Delete Vender/Item By Vender and Item Number", dataInputs2, 500, 550);
			}

			//Enter input Scene
			window.setScene(inputDataScene);

		}

		else if (event.getSource() == insertOrderInfo || event.getSource() == updateOrderInfo || event.getSource() == deleteOrderInfo){    	

			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Order Number");
			dataInputs1.add("Order Date");
			dataInputs1.add("Customer Sold to Name");
			dataInputs1.add("Customer PO Number");
			dataInputs1.add("Customer Ship to Name");
			dataInputs1.add("Customer Ship to Address Line 1");
			dataInputs1.add("Customer Ship to Address Line 2");
			dataInputs1.add("Customer Ship to City");
			dataInputs1.add("Customer Ship to State");
			dataInputs1.add("Customer Ship to Zip");

			ArrayList<String> dataInputs2 = new ArrayList<String>();
			dataInputs2.add("Order Number");

			//Input Scene Layout
			if(event.getSource() == insertOrderInfo)
			{
				inputSelection = "insertOrder";
				inputDataLayout("Insert Order Information", dataInputs1, 500, 550);
			}
			else if(event.getSource() == updateOrderInfo)
			{
				inputSelection = "updateOrder";
				inputDataLayout("Update Order By Number", dataInputs1, 500, 550);
			}
			else
			{
				inputSelection = "deleteOrder";
				inputDataLayout("Delete Order By Number", dataInputs2, 500, 550);
			}

			//Enter input Scene
			window.setScene(inputDataScene);

		}

		else if (event.getSource() == insertOrderItemInfo || event.getSource() == updateOrderItemInfo || event.getSource() == deleteOrderItemInfo){    	

			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Order Number");
			dataInputs1.add("Item Number");
			dataInputs1.add("Item Quantity Order");
			dataInputs1.add("Item Quantity Shipped");
			dataInputs1.add("Item Sales Price");

			ArrayList<String> dataInputs2 = new ArrayList<String>();
			dataInputs2.add("Order Number");
			dataInputs2.add("Item Number");

			//Input Scene Layout
			if(event.getSource() == insertOrderItemInfo)
			{
				inputSelection = "insertOrderItem";
				inputDataLayout("Insert Order/Item Information", dataInputs1, 500, 550);
			}
			else if(event.getSource() == updateOrderItemInfo)
			{
				inputSelection = "updateOrderItem";
				inputDataLayout("Update Order/Item By Order Number", dataInputs1, 500, 550);
			}
			else
			{
				inputSelection = "deleteOrderItem";
				inputDataLayout("Delete Order By Order and Item Number", dataInputs2, 500, 550);
			}

			//Enter input Scene
			window.setScene(inputDataScene);

		}

		else if (event.getSource() == insertInvoiceInfo || event.getSource() == updateInvoiceInfo || event.getSource() == deleteInvoiceInfo){    	

			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Invoice Number");
			dataInputs1.add("Invoice Date");
			dataInputs1.add("Order Number");
			dataInputs1.add("Ship Charge");
			dataInputs1.add("Tax");

			ArrayList<String> dataInputs2 = new ArrayList<String>();
			dataInputs2.add("Invoice Number");

			//Input Scene Layout
			if(event.getSource() == insertInvoiceInfo)
			{
				inputSelection = "insertInvoice";
				inputDataLayout("Insert Invoice Information", dataInputs1, 500, 550);
			}
			else if(event.getSource() == updateInvoiceInfo)
			{
				inputSelection = "updateInvoice";
				inputDataLayout("Update Invoice By Invoice Number", dataInputs1, 500, 550);
			}
			else
			{
				inputSelection = "deleteInvoice";
				inputDataLayout("Delete Invoice By Invoice Number", dataInputs2, 500, 550);
			}

			//Enter input Scene
			window.setScene(inputDataScene);

		}

		else if (event.getSource() == insertPaymentInfo || event.getSource() == updatePaymentInfo || event.getSource() == deletePaymentInfo){    	

			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Payment Number");
			dataInputs1.add("Customer Number");
			dataInputs1.add("Payment Date");
			dataInputs1.add("Payment Amount");

			ArrayList<String> dataInputs2 = new ArrayList<String>();
			dataInputs2.add("Payment Number");

			//Input Scene Layout
			if(event.getSource() == insertPaymentInfo)
			{
				inputSelection = "insertPayment";
				inputDataLayout("Insert Payment Information", dataInputs1, 500, 550);
			}
			else if(event.getSource() == updatePaymentInfo)
			{
				inputSelection = "updatePayment";
				inputDataLayout("Update Payment By Payment Number", dataInputs1, 500, 550);
			}
			else
			{
				inputSelection = "deletePayment";
				inputDataLayout("Delete Payment By Payment Number", dataInputs2, 500, 550);
			}

			//Enter input Scene
			window.setScene(inputDataScene);

		}

		else if (event.getSource() == inputAcceptButton){    	

			if(inputSelection.equals("DailyInvoiceReport"))
			{
				try{
					//Table Scene Layout
					createTableLayout(holtDistributorFunctions.getDailyInvoiceReport(myStmt, Integer.parseInt(textFields.get(2).getText()), 
							Integer.parseInt(textFields.get(1).getText()), Integer.parseInt(textFields.get(0).getText())));
					//Enter Table Scene
					window.setScene(TableScene);
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("MonthlyInvoiceReport"))
			{
				try{
					//Table Scene Layout
					createTableLayout(holtDistributorFunctions.getMonthlyInvoiceReport(myStmt, Integer.parseInt(textFields.get(1).getText()), 
							Integer.parseInt(textFields.get(0).getText())));
					//Enter Table Scene
					window.setScene(TableScene);
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("DailyCashReceiptJournal"))
			{
				try{
					//Table Scene Layout
					createTableLayout(holtDistributorFunctions.getDailyCashReceiptJournal(myStmt, Integer.parseInt(textFields.get(2).getText()), 
							Integer.parseInt(textFields.get(1).getText()), Integer.parseInt(textFields.get(0).getText())));
					//Enter Table Scene
					window.setScene(TableScene);
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("MonthlyCashReceiptJournal"))
			{
				try{

					//Table Scene Layout
					createTableLayout(holtDistributorFunctions.getMonthlyCashReceiptJournal(myStmt, Integer.parseInt(textFields.get(1).getText()), 
							Integer.parseInt(textFields.get(0).getText())));
					//Enter Table Scene
					window.setScene(TableScene);
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("insertTerritory"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.insertTerritory(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText());
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("updateTerritory"))
			{
				try{
					//Table Scene Layout				
					String territoryNum = comboBoxList.get(0).getValue();
					territoryNum = territoryNum.substring(0 , territoryNum.indexOf(' '));
					holtDistributorFunctions.updateTerritoryByNumber(myStmt, Integer.parseInt(territoryNum) , textFields.get(0).getText());
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("deleteTerritory"))
			{
				try{
					//Table Scene Layout
					String territoryNum = comboBoxList.get(0).getValue();
					territoryNum = territoryNum.substring(0 , territoryNum.indexOf(' '));
					
					//Check if territory is linked to a sales representative
					int salesRepCheck = holtDistributorFunctions.checkSalesRepTableSalesNumber(myStmt, Integer.parseInt(territoryNum));
					if(salesRepCheck == -1)
					{
						holtDistributorFunctions.deleteTerritory(myStmt,  Integer.parseInt(territoryNum), textFields.get(0).getText());
						reset();
					}
					else
					{
						AlertBox alertbox = new AlertBox();
						alertbox.display("Invalid Entry", "You cannot delete that territory is linked to sales rep number : "+ salesRepCheck);
					}
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("insertSalesRep"))
			{
				try{
					String territoryNum = comboBoxList.get(0).getValue();
					territoryNum = territoryNum.substring(0 , territoryNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.insertSalesRepBasicInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(), textFields.get(2).getText()
							, textFields.get(3).getText(), textFields.get(4).getText(), Integer.parseInt(textFields.get(5).getText()),   Integer.parseInt(territoryNum));
					holtDistributorFunctions.insertSalesRepSalesInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), Float.parseFloat(textFields.get(6).getText()), 
							Float.parseFloat(textFields.get(7).getText()), Float.parseFloat(textFields.get(8).getText()), Float.parseFloat(textFields.get(9).getText()),
							Float.parseFloat(textFields.get(10).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("updateSalesRep"))
			{
				try{
					String salesRepNum = comboBoxList.get(0).getValue();
					salesRepNum = salesRepNum.substring(0 , salesRepNum.indexOf(' '));
					
					String territoryNum = comboBoxList.get(1).getValue();
					territoryNum = territoryNum.substring(0 , territoryNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.updateSalesRepBasicInfoByNumber(myStmt, Integer.parseInt(salesRepNum), textFields.get(1).getText(), textFields.get(2).getText()
					, textFields.get(3).getText(), textFields.get(4).getText(), Integer.parseInt(textFields.get(5).getText()), Integer.parseInt(territoryNum));
					holtDistributorFunctions.updateSalesRepSalesInfoByNumber(myStmt, Integer.parseInt(textFields.get(0).getText()), Float.parseFloat(textFields.get(6).getText()), 
							Float.parseFloat(textFields.get(7).getText()), Float.parseFloat(textFields.get(8).getText()), Float.parseFloat(textFields.get(9).getText()),
							Float.parseFloat(textFields.get(10).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("deleteSalesRep"))
			{
				try{
					String salesRepNum = comboBoxList.get(0).getValue();
					salesRepNum = salesRepNum.substring(0 , salesRepNum.indexOf(' '));
					
					//Check if sales representative is linked to customer
					int customerNumberCheck = holtDistributorFunctions.checkCustomerBasicTableSalesNumber(myStmt, Integer.parseInt(salesRepNum));
					if(customerNumberCheck == -1)
					{
						//Table Scene Layout
						holtDistributorFunctions.deleteSalesRepBasicInfo(myStmt, Integer.parseInt(salesRepNum), textFields.get(0).getText());
						reset();
					}
					else
					{
						AlertBox alertbox = new AlertBox();
						alertbox.display("Invalid Entry", "You cannot delete that sales rep is linked to customer number : "+ customerNumberCheck);
					}
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("insertCustomer"))
			{
				try{
					String salesRepNum = comboBoxList.get(0).getValue();
					salesRepNum = salesRepNum.substring(0 , salesRepNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.insertCustomerBasicInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(), textFields.get(2).getText()
							, textFields.get(3).getText(), textFields.get(4).getText(), textFields.get(5).getText(), Integer.parseInt(textFields.get(6).getText()), Integer.parseInt(salesRepNum));
					holtDistributorFunctions.insertCustomerSalesInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), Float.parseFloat(textFields.get(8).getText()), 
							Float.parseFloat(textFields.get(9).getText()), Float.parseFloat(textFields.get(10).getText()), Float.parseFloat(textFields.get(11).getText()),
							Float.parseFloat(textFields.get(12).getText()), Float.parseFloat(textFields.get(13).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("updateCustomer"))
			{
				try{
					String salesRepNum = comboBoxList.get(0).getValue();
					salesRepNum = salesRepNum.substring(0 , salesRepNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.updateCustomerBasicInfoByNumber(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(), textFields.get(2).getText()
							, textFields.get(3).getText(), textFields.get(4).getText(), textFields.get(5).getText(), Integer.parseInt(textFields.get(6).getText()), Integer.parseInt(salesRepNum));
					holtDistributorFunctions.updateCustomerSalesInfoByNumber(myStmt, Integer.parseInt(textFields.get(0).getText()), Float.parseFloat(textFields.get(8).getText()), 
							Float.parseFloat(textFields.get(9).getText()), Float.parseFloat(textFields.get(10).getText()), Float.parseFloat(textFields.get(11).getText()),
							Float.parseFloat(textFields.get(12).getText()), Float.parseFloat(textFields.get(13).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("deleteCustomer"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.deleteCustomerBasicInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText());
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("insertItem"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.insertItemBasicInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),  Float.parseFloat(textFields.get(2).getText()));
					holtDistributorFunctions.insertItemSalesInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), Float.parseFloat(textFields.get(3).getText()), 
							Float.parseFloat(textFields.get(4).getText()), Integer.parseInt(textFields.get(5).getText()), Integer.parseInt(textFields.get(6).getText()),
							Integer.parseInt(textFields.get(7).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("updateItem"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.updateItemBasicInfoByNumber(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),  Float.parseFloat(textFields.get(2).getText()));
					holtDistributorFunctions.updateItemSalesInfoByNumber(myStmt, Integer.parseInt(textFields.get(0).getText()), Float.parseFloat(textFields.get(3).getText()), 
							Float.parseFloat(textFields.get(4).getText()), Integer.parseInt(textFields.get(5).getText()), Integer.parseInt(textFields.get(6).getText()),
							Integer.parseInt(textFields.get(7).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("deleteItem"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.deleteItemBasicInfo(myStmt, Integer.parseInt(textFields.get(0).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("insertVender"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.insertVenderBasicInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),  textFields.get(2).getText()
							, textFields.get(3).getText(), textFields.get(4).getText(), Integer.parseInt(textFields.get(5).getText()));
					reset();

				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("updateVender"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.updateVenderBasicInfoByNumber(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),  textFields.get(2).getText()
							, textFields.get(3).getText(), textFields.get(4).getText(), Integer.parseInt(textFields.get(5).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("deleteVender"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.deleteVenderBasicInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText());
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("insertVenderItem"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.insertVenderAndItemInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), Integer.parseInt(textFields.get(1).getText()),   Float.parseFloat(textFields.get(2).getText())
							,  Float.parseFloat(textFields.get(3).getText()), textFields.get(4).getText());
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("updateVenderItem"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.updateVenderAndItemInfoByNumber(myStmt, Integer.parseInt(textFields.get(0).getText()), Integer.parseInt(textFields.get(1).getText()),   Float.parseFloat(textFields.get(2).getText())
							,  Float.parseFloat(textFields.get(3).getText()), textFields.get(4).getText());
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("deleteVenderItem"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.deleteVenderAndItemInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), Integer.parseInt(textFields.get(1).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("insertOrder"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.insertOrderInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),   textFields.get(2).getText()
							, Integer.parseInt(textFields.get(3).getText()), textFields.get(4).getText(), textFields.get(5).getText(), textFields.get(6).getText(), textFields.get(7).getText(), textFields.get(8).getText(),
							Integer.parseInt(textFields.get(9).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("updateOrder"))
			{
				try{
					String orderNum = comboBoxList.get(0).getValue();
					orderNum = orderNum.substring(0 , orderNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.updateOrderInfoByNumber(myStmt, Integer.parseInt(orderNum), textFields.get(1).getText(),   textFields.get(2).getText()
							, Integer.parseInt(textFields.get(3).getText()), textFields.get(4).getText(), textFields.get(5).getText(), textFields.get(6).getText(), textFields.get(7).getText(), textFields.get(8).getText(),
							Integer.parseInt(textFields.get(9).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("deleteOrder"))
			{
				try{
					String orderNum = comboBoxList.get(0).getValue();
					orderNum = orderNum.substring(0 , orderNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.deleteOrderInfo(myStmt, Integer.parseInt(orderNum));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("insertOrderItem"))
			{
				try{
					String orderNum = comboBoxList.get(0).getValue();
					orderNum = orderNum.substring(0 , orderNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.insertOrderAndItemInfo(myStmt, Integer.parseInt(orderNum), Integer.parseInt(textFields.get(1).getText()),   Integer.parseInt(textFields.get(2).getText())
							, Integer.parseInt(textFields.get(3).getText()), Float.parseFloat(textFields.get(4).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("updateOrderItem"))
			{
				try{
					String orderNum = comboBoxList.get(0).getValue();
					orderNum = orderNum.substring(0 , orderNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.updateOrderAndItemInfoByNumber(myStmt, Integer.parseInt(orderNum), Integer.parseInt(textFields.get(1).getText()),   Integer.parseInt(textFields.get(2).getText())
							, Integer.parseInt(textFields.get(3).getText()), Float.parseFloat(textFields.get(4).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("deleteOrderItem"))
			{
				try{
					String orderNum = comboBoxList.get(0).getValue();
					orderNum = orderNum.substring(0 , orderNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.deleteOrderAndItemInfo(myStmt, Integer.parseInt(orderNum), Integer.parseInt(textFields.get(1).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("insertInvoice"))
			{
				try{
					String orderNum = comboBoxList.get(0).getValue();
					orderNum = orderNum.substring(0 , orderNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.insertInvoiceInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),   Integer.parseInt(orderNum)
							, Float.parseFloat(textFields.get(3).getText()), Float.parseFloat(textFields.get(4).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("updateInvoice"))
			{
				try{
					String orderNum = comboBoxList.get(0).getValue();
					orderNum = orderNum.substring(0 , orderNum.indexOf(' '));
					//Table Scene Layout
					holtDistributorFunctions.updateInvoiceInfoByNumber(myStmt, Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),  Integer.parseInt(orderNum)
							, Float.parseFloat(textFields.get(3).getText()), Float.parseFloat(textFields.get(4).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("deleteInvoice"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.deleteInvoiceInfo(myStmt, Integer.parseInt(textFields.get(0).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}

			else if(inputSelection.equals("insertPayment"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.insertPaymentInfo(myStmt, Integer.parseInt(textFields.get(0).getText()), Integer.parseInt(textFields.get(1).getText()),   textFields.get(2).getText()
							, Float.parseFloat(textFields.get(3).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("updatePayment"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.updatePaymentInfoByNumber(myStmt, Integer.parseInt(textFields.get(0).getText()), Integer.parseInt(textFields.get(1).getText()),   textFields.get(2).getText()
							, Float.parseFloat(textFields.get(3).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("deletePayment"))
			{
				try{
					//Table Scene Layout
					holtDistributorFunctions.deletePaymentInfo(myStmt, Integer.parseInt(textFields.get(0).getText()));
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("insertLoginInfo"))
			{
				try{
					//Table Scene Layout
					//holtDistributorFunctions.deletePaymentInfo(myStmt, Integer.parseInt(textFields.get(0).getText()));
					
					//insert customerbasicinfo
					holtDistributorFunctions.insertCustomerLoginBasicInfo(myStmt, textFields.get(2).getText(), textFields.get(3).getText(),textFields.get(4).getText(),
							textFields.get(5).getText(), textFields.get(6).getText(), Integer.parseInt(textFields.get(7).getText()), Character.getNumericValue(comboBoxList.get(0).getValue().charAt(0)));
					//get customer number
					int customerNumber = holtDistributorFunctions.getCustomerNumber(myStmt, textFields.get(2).getText());
					//insert customersalesinfo as 0's
					holtDistributorFunctions.insertCustomerSalesInfo(myStmt, customerNumber, 0, 0, 0, 0, 0, 0);
					//insert userNameInfo
					holtDistributorFunctions.insertUserLoginInfo(myStmt, customerNumber, textFields.get(0).getText(), textFields.get(1).getText());
					reset();
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
							+ "\n entry type. Please try again.");
				}
			}
			else if(inputSelection.equals("sendThroughInvoice"))
			{
				try{
					String orderNum = comboBoxList.get(0).getValue();
					orderNum = orderNum.substring(0, orderNum.indexOf(' '));
					orderNumber =  Integer.parseInt(orderNum);
					shipCharge =  Float.parseFloat(textFields.get(0).getText());
					tax =  Float.parseFloat(textFields.get(1).getText());
					
					
					
					//get item numbers and quantity ordered from "order and item info" table				
					String dataArray[][] = holtDistributorFunctions.getOrderItemInfo(myStmt, orderNumber);
					
					//Display Ordered Items
					comboBoxList.clear();
					invoiceItemsLayout(dataArray);
					window.setScene(invoiceItemScene);
					inputSelection = "confirmSendInvoice";
				
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
								+ "\n entry type. Please try again.");
					exc.printStackTrace();
				}
			}
			
			else if(inputSelection.equals("confirmSendInvoice"))
			{
				try{

					//get item numbers and quantity ordered from "order and item info" table				
					String orderAndItemTable[][] = holtDistributorFunctions.getOrderItemInfo(myStmt, orderNumber);		



					//if units on hand < units shipped send error
					Boolean unitsOnHandToLow = false;
					for(int i = 1; !(orderAndItemTable[i][0]  == null); i++)
					{
						String itemSalesInfo[][] = holtDistributorFunctions.getItemSalesInfo(myStmt, Integer.parseInt(orderAndItemTable[i][1]));
						if(Integer.parseInt(comboBoxList.get(i-1).getValue()) > Integer.parseInt(itemSalesInfo[1][3]))
						{
							unitsOnHandToLow = true;
						}
					}

					if(!unitsOnHandToLow)
					{
						//get Customer Name
						String customerName = holtDistributorFunctions.getCustomerNameByOrderNumber(myStmt, orderNumber);

						//get Customer Number
						int customerNumber = holtDistributorFunctions.getCustomerNumber(myStmt, customerName);

						//get Customer Sales Rep Number
						int repNumber = holtDistributorFunctions.getCustomerSalesRepNumber(myStmt, customerNumber);

						//get Customer Sales Info
						String customerSalesInfo[][] = holtDistributorFunctions.getCustomerSalesTable(myStmt, customerNumber);
						float customerMTDsales = Float.parseFloat(customerSalesInfo[1][1]);//Customer MTD Sales
						float customerYTDSales= Float.parseFloat(customerSalesInfo[1][2]);//Customer YTD Sales
						float customerBalance = Float.parseFloat(customerSalesInfo[1][3]);//Customer Balance
						float customerTotalInvoice = Float.parseFloat(customerSalesInfo[1][5]);//Customer total invoice

						//get Sales Rep Sales Info
						String salesRepSalesInfo[][] = holtDistributorFunctions.getSalesRepSalesTable(myStmt, repNumber);
						float salesRepMTDsales = Float.parseFloat(salesRepSalesInfo[1][1]);// Sales Rep MTD Sales
						float salesRepYTDSales= Float.parseFloat(salesRepSalesInfo[1][2]);// Sales Rep YTD Sales
						//float salesRepMTDCommission = Float.parseFloat(salesRepSalesInfo[1][3]);// Sales Rep MTD Commission
						//float salesRepYTDCommission = Float.parseFloat(salesRepSalesInfo[1][4]);// Sales Rep YTD Commission
						float salesRepCommissionRate = Float.parseFloat(salesRepSalesInfo[1][5]);// Sales Rep Commission Rate

						//Get Invoice Table invoice total
						float invoiceTableTotal = 0;		

						//Dont print invoice if all combo boxes are 0
						boolean showInvoice = false;
						for(int i = 1; !(orderAndItemTable[i][0]  == null); i++)
						{
							if(Integer.parseInt(comboBoxList.get(i-1).getValue()) != 0)
							{
								showInvoice = true;
							}
						}

						if(showInvoice)
						{
							//Set invoice table
							String dateTime = getDateTime();
							holtDistributorFunctions.sendThroughCustomerInvoiceInfo(myStmt, dateTime , orderNumber, shipCharge, tax, 0);

							invoiceNumber = holtDistributorFunctions.getInvoiceNumber(myStmt, dateTime , orderNumber, shipCharge, tax);
						}

						//Update Ship Quantity in order and item table
						for(int i = 1; !(orderAndItemTable[i][0]  == null); i++)
						{
							holtDistributorFunctions.updateQuantityShipped(myStmt, Integer.parseInt(comboBoxList.get(i-1).getValue()) + Integer.parseInt(orderAndItemTable[i][3]) , Integer.parseInt(orderAndItemTable[i][0]) , Integer.parseInt(orderAndItemTable[i][1]));
						}					

						//Updates sales and item info for each item
						for(int i = 1; !(orderAndItemTable[i][0]  == null); i++)
						{
							Integer.parseInt(orderAndItemTable[i][1]);//itemNumber
							String itemSalesInfo[][] = holtDistributorFunctions.getItemSalesInfo(myStmt, Integer.parseInt(orderAndItemTable[i][1]));
							//						 Float.parseFloat(itemSalesInfo[1][1]);//Item MTD Sales
							//						 Float.parseFloat(itemSalesInfo[1][2]);//Item YTD Sales
							//						Integer.parseInt(itemSalesInfo[1][3];//units on hand
							//						Integer.parseInt(itemSalesInfo[1][4];//units allocated
							//						 Float.parseFloat(orderAndItemTable[i][4]);//item sales price
							//						comboBoxList.get(i-1).getValue();//quantity shipped
							//						itemSalesInfo[i][3] - comboBoxList.get(i-1).getValue();
							//						itemSalesInfo[i][4] + comboBoxList.get(i-1).getValue();
							float priceOfAllItem = Float.parseFloat(orderAndItemTable[i][4]) *  Integer.parseInt(comboBoxList.get(i-1).getValue());

							//Update Item MTD Sales, YTD Sales, units on hand and units allocated
							holtDistributorFunctions.updateItemSalesInfoByNumber(myStmt, Integer.parseInt(orderAndItemTable[i][1]),  Float.parseFloat(itemSalesInfo[1][1])
									+ (priceOfAllItem), Float.parseFloat(itemSalesInfo[1][2])
									+ ( priceOfAllItem), Integer.parseInt(itemSalesInfo[1][3]) - Integer.parseInt(comboBoxList.get(i-1).getValue()), 
									Integer.parseInt(itemSalesInfo[1][4]) + Integer.parseInt(comboBoxList.get(i-1).getValue()));

							//Update Customer MTD Sales, YTD Sales, Balance, Total Invoice
							holtDistributorFunctions.updateCustomerSalesInfoByNumber(myStmt, customerNumber, customerMTDsales + priceOfAllItem , 
									customerYTDSales + priceOfAllItem , customerBalance + priceOfAllItem, customerTotalInvoice + priceOfAllItem);


							//Get new Sales Info
							customerSalesInfo = holtDistributorFunctions.getCustomerSalesTable(myStmt, customerNumber);
							customerMTDsales = Float.parseFloat(customerSalesInfo[1][1]);//Customer MTD Sales
							customerYTDSales= Float.parseFloat(customerSalesInfo[1][2]);//Customer YTD Sales
							customerBalance = Float.parseFloat(customerSalesInfo[1][3]);//Customer Balance
							customerTotalInvoice = Float.parseFloat(customerSalesInfo[1][5]);//Customer total invoice

							//Update Sales Rep MTD Sales, YTD Sales
							holtDistributorFunctions.updateSalesRepSalesInfoByNumber(myStmt, repNumber, salesRepMTDsales + priceOfAllItem, salesRepYTDSales + priceOfAllItem);

							//Get new Sales Rep MTD Sales, YTD Sales
							salesRepSalesInfo = holtDistributorFunctions.getSalesRepSalesTable(myStmt, repNumber);
							salesRepMTDsales = Float.parseFloat(salesRepSalesInfo[1][1]);//Customer MTD Sales
							salesRepYTDSales= Float.parseFloat(salesRepSalesInfo[1][2]);//Customer YTD Sales

							//Update Invoice Table Invoice total
							holtDistributorFunctions.updateInvoiceInfoByNumber(myStmt, invoiceNumber, invoiceTableTotal + priceOfAllItem);

							//Get new Invoice Table invoice total
							invoiceTableTotal = holtDistributorFunctions.getInvoiceTotal(myStmt, invoiceNumber);
						}

						//Update Customer MTD Sales, YTD Sales, Balance, Total Invoice (shipping and tax values)
						holtDistributorFunctions.updateCustomerSalesInfoByNumber(myStmt, customerNumber, customerMTDsales + shipCharge + tax , 
								customerYTDSales + shipCharge + tax  , customerBalance + shipCharge + tax,  customerTotalInvoice + shipCharge + tax );

						//Update Sales Rep MTD Sales, YTD Sales (shipping and tax values)
						holtDistributorFunctions.updateSalesRepSalesInfoByNumber(myStmt, repNumber, salesRepMTDsales + shipCharge + tax, salesRepYTDSales + shipCharge + tax,
								(salesRepMTDsales + shipCharge + tax)*salesRepCommissionRate, (salesRepYTDSales + shipCharge + tax)*salesRepCommissionRate);

						//Update Invoice Table invoice total (shipping and tax values)
						holtDistributorFunctions.updateInvoiceInfoByNumber(myStmt, invoiceNumber, invoiceTableTotal + shipCharge + tax);

						//Go back to home screen
						reset();
					}
					else{
						AlertBox alertbox = new AlertBox();
						alertbox.display("Invalid Entry", "Units on hand is to low for one item");
					}
				
				}
				catch(Exception exc){
					AlertBox alertbox = new AlertBox();
					alertbox.display("Invalid Entry", "You have entered an invalid"
								+ "\n entry type. Please try again.");
					exc.printStackTrace();
				}
			}
		}
		
	}



	public void startSceneLayout(){
		//GridPane with 10px padding around edge
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		//Start Label
		Label startLabel = new Label("Holt Distributors");
		startLabel.setFont(new Font("Arial", 35));
		GridPane.setConstraints(startLabel, 0, 0);

		//User Name and Password Entry Field
		TextField textField1 = new TextField();
		textField1.setPromptText("Username");
		GridPane.setConstraints(textField1, 0, 1);
		Label inputLabel1 = new Label("Username");
		GridPane.setConstraints(inputLabel1, 1, 1);
		textFields.add(textField1);

		PasswordField textField2 = new PasswordField();
		textField2.setPromptText("Password");
		GridPane.setConstraints(textField2, 0, 2);
		Label inputLabel2 = new Label("Password");
		GridPane.setConstraints(inputLabel2, 1, 2);
		textFields.add(textField2);

		startButton = new Button("Start");
		GridPane.setConstraints(startButton, 0, 3);
		
		createLoginButton = new Button("Create Login Account");
		GridPane.setConstraints(createLoginButton, 0, 4);


		grid.getChildren().addAll(startLabel, textField1, inputLabel1, textField2, inputLabel2, startButton, createLoginButton);
		startScene = new Scene(grid, 350, 250);

		//This class will handle the button events
		startButton.setOnAction(this);
		createLoginButton.setOnAction(this);
	}
	

	public void homeSceneLayout(){
		StackPane titleLayerLayout = new StackPane();
		Label homeLabel = new Label("Holt Distributors Home");
		homeLabel.setFont(new Font("Arial", 40));
		titleLayerLayout.setAlignment(homeLabel, Pos.TOP_CENTER);
		titleLayerLayout.getChildren().addAll(homeLabel);


		//GridPane with 10px padding around edge
		GridPane buttonLayerLayout = new GridPane();
		buttonLayerLayout.setPadding(new Insets(10, 10, 10, 10));
		buttonLayerLayout.setVgap(8);
		buttonLayerLayout.setHgap(10);

		Label specialReportsLabel = new Label("Special Reports/Lists");
		specialReportsLabel.setFont(new Font("Arial", 20));
		GridPane.setConstraints(specialReportsLabel, 0, 0);

		territoryListButton = new Button("Territory List");
		GridPane.setConstraints(territoryListButton, 0, 1);

		customerMasterListButton = new Button("Customer Master List");
		GridPane.setConstraints(customerMasterListButton,0, 2);

		customerOpenOrderReportButton = new Button("Customer Open Order Report");
		GridPane.setConstraints(customerOpenOrderReportButton, 1, 1);

		ItemOpenOrderReportButton = new Button("Open Orders By Item");
		GridPane.setConstraints(ItemOpenOrderReportButton, 1, 2);

		DailyInvoiceReportButton = new Button("Daily Invoice Report");
		GridPane.setConstraints(DailyInvoiceReportButton, 2, 1);

		MonthlyInvoiceReportButton = new Button("Monthly Invoice Report");
		GridPane.setConstraints(MonthlyInvoiceReportButton, 2, 2);

		StockStatusReportButton = new Button("Stock Status Report");
		GridPane.setConstraints(StockStatusReportButton, 1, 4);

		ReorderPointListButton = new Button("Reorder Point List");
		GridPane.setConstraints(ReorderPointListButton, 1, 3);

		VenderListButton = new Button("Vender List");
		GridPane.setConstraints(VenderListButton, 0, 3);

		DailyCashReceiptJournalButton = new Button("Daily Cash Receipt Journal");
		GridPane.setConstraints(DailyCashReceiptJournalButton, 2, 3);

		MonthlyCashReceiptJournalButton = new Button("Monthly Cash Receipt Journal");
		GridPane.setConstraints(MonthlyCashReceiptJournalButton, 2, 4);
		
		sendThroughInvoice = new Button("Release Order");
		GridPane.setConstraints(sendThroughInvoice, 3, 1);

		CustomerMailingLabelInfoButton = new Button("Customer Mailing Label Info");
		GridPane.setConstraints(CustomerMailingLabelInfoButton, 3, 2);

		MonthlySalesRepCommissionReportButton = new Button("Monthly Sales Rep Commission Report");
		GridPane.setConstraints(MonthlySalesRepCommissionReportButton, 3, 3);

		Label dataTransactionsLabel = new Label("Data Transactions");
		dataTransactionsLabel.setFont(new Font("Arial", 20));
		GridPane.setConstraints(dataTransactionsLabel, 0, 5);

		Label territoryLabel = new Label("Territories");
		territoryLabel.setFont(new Font("Arial", 16));
		GridPane.setConstraints(territoryLabel, 0, 6);

		insertTerritory = new Button("Insert Territory");
		GridPane.setConstraints(insertTerritory, 0, 7);

		updateTerritory = new Button("Update Territory");
		GridPane.setConstraints(updateTerritory, 0, 8);

		deleteTerritory = new Button("Delete Territory");
		GridPane.setConstraints(deleteTerritory, 0, 9);

		Label salesRepsLabel = new Label("Sales Reps");
		salesRepsLabel.setFont(new Font("Arial", 16));
		GridPane.setConstraints(salesRepsLabel, 1, 6);

		insertSalesRepInfo = new Button("Insert Sales Rep Info");
		GridPane.setConstraints(insertSalesRepInfo, 1, 7);

		updateSalesRepInfo = new Button("Update Sales Rep Info");
		GridPane.setConstraints(updateSalesRepInfo, 1, 8);

		deleteSalesRepInfo = new Button("Delete Sales Rep Info");
		GridPane.setConstraints(deleteSalesRepInfo, 1, 9);

		Label customersLabel = new Label("Customers");
		customersLabel.setFont(new Font("Arial", 16));
		GridPane.setConstraints(customersLabel, 2, 6);

		insertCustomerInfo = new Button("Insert Customer Info");
		GridPane.setConstraints(insertCustomerInfo, 2, 7);

		updateCustomerInfo = new Button("Update Customer Info");
		GridPane.setConstraints(updateCustomerInfo, 2, 8);

		deleteCustomerInfo = new Button("Delete Customer Info");
		GridPane.setConstraints(deleteCustomerInfo, 2, 9);

		Label invoicesLabel = new Label("Invoices");
		invoicesLabel.setFont(new Font("Arial", 16));
		GridPane.setConstraints(invoicesLabel, 3, 6);

		insertInvoiceInfo = new Button("Create Invoice");
		GridPane.setConstraints(insertInvoiceInfo, 3, 7);

		updateInvoiceInfo = new Button("Update Invoice");
		GridPane.setConstraints(updateInvoiceInfo, 3, 8);

		deleteInvoiceInfo = new Button("Delete Invoice");
		GridPane.setConstraints(deleteInvoiceInfo, 3, 9);

		Label itemsLabel = new Label("Items");
		itemsLabel.setFont(new Font("Arial", 16));
		GridPane.setConstraints(itemsLabel, 0, 13);

		insertItemInfo = new Button("Insert Item Info");
		GridPane.setConstraints(insertItemInfo, 0, 14);

		updateItemInfo = new Button("Update Item Info");
		GridPane.setConstraints(updateItemInfo, 0, 15);

		deleteItemInfo = new Button("Delete Item Info");
		GridPane.setConstraints(deleteItemInfo, 0, 16);

		Label vendersLabel = new Label("Venders");
		vendersLabel.setFont(new Font("Arial", 16));
		GridPane.setConstraints(vendersLabel, 1, 13);

		insertVenderInfo = new Button("Insert Vender Info");
		GridPane.setConstraints(insertVenderInfo, 1, 14);

		updateVenderInfo = new Button("Update Vender Info");
		GridPane.setConstraints(updateVenderInfo, 1, 15);

		deleteVenderInfo = new Button("Delete Vender Info");
		GridPane.setConstraints(deleteVenderInfo, 1, 16);

		Label vendersAndItemsLabel = new Label("Vender/Item Info");
		vendersAndItemsLabel.setFont(new Font("Arial", 16));
		GridPane.setConstraints(vendersAndItemsLabel, 2, 13);

		insertVenderItemInfo = new Button("Insert Vender/Item Info");
		GridPane.setConstraints(insertVenderItemInfo, 2, 14);

		updateVenderItemInfo = new Button("Update Vender/Item Info");
		GridPane.setConstraints(updateVenderItemInfo, 2, 15);

		deleteVenderItemInfo = new Button("Delete Vender/Item Info");
		GridPane.setConstraints(deleteVenderItemInfo, 2, 16);

		Label ordersLabel = new Label("Orders");
		ordersLabel.setFont(new Font("Arial", 16));
		GridPane.setConstraints(ordersLabel, 2, 13);

		insertOrderInfo = new Button("Insert Order Info");
		GridPane.setConstraints(insertOrderInfo, 2, 14);

		updateOrderInfo = new Button("Update Order Info");
		GridPane.setConstraints(updateOrderInfo, 2, 15);

		deleteOrderInfo = new Button("Delete Order Info");
		GridPane.setConstraints(deleteOrderInfo, 2, 16);

		Label paymentsLabel = new Label("Payments");
		paymentsLabel.setFont(new Font("Arial", 16));
		GridPane.setConstraints(paymentsLabel, 3, 13);

		insertPaymentInfo = new Button("Create Payment");
		GridPane.setConstraints(insertPaymentInfo, 3, 14);

		updatePaymentInfo = new Button("Update Payment");
		GridPane.setConstraints(updatePaymentInfo, 3, 15);

		deletePaymentInfo = new Button("Delete Payment");
		GridPane.setConstraints(deletePaymentInfo, 3, 16);

		Label ordersAndItemsLabel = new Label("Order/Item Info");
		ordersAndItemsLabel.setFont(new Font("Arial", 16));
		GridPane.setConstraints(ordersAndItemsLabel, 1, 20);

		insertOrderItemInfo = new Button("Insert Order/Item Info");
		GridPane.setConstraints(insertOrderItemInfo, 1, 21);

		updateOrderItemInfo = new Button("Update Order/Item Info");
		GridPane.setConstraints(updateOrderItemInfo, 1, 22);

		deleteOrderItemInfo = new Button("Delete Order/Item Info");
		GridPane.setConstraints(deleteOrderItemInfo, 1, 23);
		
		backToStartButton = new Button("Back");
		GridPane.setConstraints(backToStartButton, 2, 20);

		buttonLayerLayout.getChildren().addAll(specialReportsLabel, territoryListButton, customerMasterListButton, customerOpenOrderReportButton, ItemOpenOrderReportButton,
				DailyInvoiceReportButton, MonthlyInvoiceReportButton, StockStatusReportButton, ReorderPointListButton, VenderListButton, DailyCashReceiptJournalButton, 
				MonthlyCashReceiptJournalButton, sendThroughInvoice, CustomerMailingLabelInfoButton, MonthlySalesRepCommissionReportButton, dataTransactionsLabel,territoryLabel, insertTerritory, updateTerritory, 
				deleteTerritory,salesRepsLabel, insertSalesRepInfo, updateSalesRepInfo, deleteSalesRepInfo,customersLabel, insertCustomerInfo, updateCustomerInfo, deleteCustomerInfo,itemsLabel, insertItemInfo, 
				updateItemInfo, deleteItemInfo,vendersLabel, insertVenderInfo, updateVenderInfo, deleteVenderInfo,vendersAndItemsLabel, insertVenderItemInfo, updateVenderItemInfo,  deleteVenderItemInfo, backToStartButton);
		
		//Buttons Taken out insertOrderInfo, updateOrderInfo, deleteOrderInfo, insertOrderItemInfo, updateOrderItemInfo, deleteOrderItemInfo, insertInvoiceInfo, updateInvoiceInfo, deleteInvoiceInfo,
		//insertPaymentInfo, updatePaymentInfo, deletePaymentInfo,
		//paymentsLabel, 
		//ordersAndItemsLabel, 
		//invoicesLabel, 
		//ordersLabel
		

		BorderPane layout4 = new BorderPane();
		layout4.setTop(titleLayerLayout);
		layout4.setCenter(buttonLayerLayout);
		HomeScene = new Scene(layout4, 850, 600);

		//This class will handle the button events
		territoryListButton.setOnAction(this);
		customerMasterListButton.setOnAction(this);
		customerOpenOrderReportButton.setOnAction(this);
		ItemOpenOrderReportButton.setOnAction(this);
		DailyInvoiceReportButton.setOnAction(this);
		MonthlyInvoiceReportButton.setOnAction(this);
		StockStatusReportButton.setOnAction(this);
		ReorderPointListButton.setOnAction(this);
		VenderListButton.setOnAction(this);
		DailyCashReceiptJournalButton.setOnAction(this);
		MonthlyCashReceiptJournalButton.setOnAction(this);
		sendThroughInvoice.setOnAction(this);
		CustomerMailingLabelInfoButton.setOnAction(this);
		MonthlySalesRepCommissionReportButton.setOnAction(this);
		insertTerritory.setOnAction(this);
		updateTerritory.setOnAction(this);
		deleteTerritory.setOnAction(this);
		insertSalesRepInfo.setOnAction(this);
		updateSalesRepInfo.setOnAction(this);
		deleteSalesRepInfo.setOnAction(this);
		insertCustomerInfo.setOnAction(this);
		updateCustomerInfo.setOnAction(this);
		deleteCustomerInfo.setOnAction(this);
		insertItemInfo.setOnAction(this);
		updateItemInfo.setOnAction(this);
		deleteItemInfo.setOnAction(this);
		insertVenderInfo.setOnAction(this);
		updateVenderInfo.setOnAction(this);
		deleteVenderInfo.setOnAction(this);
		insertOrderInfo.setOnAction(this);
		updateOrderInfo.setOnAction(this);
		deleteOrderInfo.setOnAction(this);
		insertVenderItemInfo.setOnAction(this);
		updateVenderItemInfo.setOnAction(this);
		deleteVenderItemInfo.setOnAction(this);
		insertOrderItemInfo.setOnAction(this);
		updateOrderItemInfo.setOnAction(this);
		deleteOrderItemInfo.setOnAction(this);
		insertInvoiceInfo.setOnAction(this);
		updateInvoiceInfo.setOnAction(this);
		deleteInvoiceInfo.setOnAction(this);
		insertPaymentInfo.setOnAction(this);
		updatePaymentInfo.setOnAction(this);
		deletePaymentInfo.setOnAction(this);
		backToStartButton.setOnAction(this);
	}

	public void createTableLayout(String[][] tableData){
		StackPane tablePane = new StackPane();
		//       String[][] tableData = {{"Territory_Number", "Territory_name", "Sales_rep_number", "rep_name", "rep_address", "Customer_Number", "Customer_sold_to_name", "Customer_sold_to_address_line_1", "Customer_sold_to_address_line_2"},
		//                                {"a", "b", "c","a", "b", "c","a", "b", "c"},
		//                                {"d", "e", "f","d", "e", "f","d", "e", "f"}};
		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(tableData));
		data.remove(0);//remove titles from data
		TableView<String[]> table = new TableView<String[]>();
		for (int i = 0; i < tableData[0].length; i++) {
			TableColumn tc = new TableColumn(tableData[0][i]);
			final int colNo = i;
			tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
					return new SimpleStringProperty((p.getValue()[colNo]));
				}
			});
			tc.setPrefWidth(90);
			table.getColumns().add(tc);
		}
		table.setItems(data);
		backToHomeButton = new Button("Back");
		tablePane.setAlignment(backToHomeButton, Pos.BOTTOM_CENTER);
		tablePane.setMargin(backToHomeButton, new Insets(15,15,15,15));
		tablePane.getChildren().addAll(table, backToHomeButton);
		TableScene = new Scene(tablePane, 800, 600);

		//This class will handle the button events
		backToHomeButton.setOnAction(this);
	}

	public void inputDataLayout(String title, ArrayList<String> inputFields, int XgridSize, int YgridSize)
	{
		//GridPane with 10px padding around edge
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		//Labels
		Label inputDailyLabel = new Label(title);
		inputDailyLabel.setFont(new Font("Arial", 20));
		GridPane.setConstraints(inputDailyLabel, 0, 0);

		//Buttons
		inputAcceptButton = new Button("Accept");
		GridPane.setConstraints(inputAcceptButton, 0, inputFields.size() +1);
		backToHomeButton = new Button("Back");
		GridPane.setConstraints(backToHomeButton, 1, inputFields.size() +1);

		inputAcceptButton.setOnAction(this);
		backToHomeButton.setOnAction(this);
		grid.getChildren().addAll(inputDailyLabel, inputAcceptButton, backToHomeButton);

		int i =1;
		for(String input : inputFields)
		{
			if(input.equalsIgnoreCase("Territory Number") && !(inputSelection.equalsIgnoreCase("insertTerritory")))
			{
				ComboBox<String> inputComboBox = new ComboBox<String>();
				String dataArray[][] = holtDistributorFunctions.getTerritoryTable(myStmt);
				
				for(int j = 1; !(dataArray[j][0] == null); j++)
				{
					inputComboBox.getItems().addAll(
							dataArray[j][0] + " - " + dataArray[j][1]
									);
				}

				GridPane.setConstraints(inputComboBox, 0, i);
				Label inputLabel = new Label(input);
				GridPane.setConstraints(inputLabel, 1, i);
				grid.getChildren().addAll(inputComboBox, inputLabel);
				comboBoxList.add(inputComboBox);
				i++;
			}
			else if((input.equalsIgnoreCase("Sales Rep Number") || input.equalsIgnoreCase("Customer Sold to Rep Number")) && !(inputSelection.equalsIgnoreCase("insertSalesRep")))
			{
				ComboBox<String> inputComboBox = new ComboBox<String>();
				String dataArray[][] = holtDistributorFunctions.getSalesRepBasicTable(myStmt);
				
				for(int j = 1; !(dataArray[j][0] == null); j++)
				{
					inputComboBox.getItems().addAll(
							dataArray[j][0] + " - " + dataArray[j][1]
									);
				}

				GridPane.setConstraints(inputComboBox, 0, i);
				Label inputLabel = new Label(input);
				GridPane.setConstraints(inputLabel, 1, i);
				grid.getChildren().addAll(inputComboBox, inputLabel);
				comboBoxList.add(inputComboBox);
				i++;
			}
			else if(input.equalsIgnoreCase("Order Number") && !(inputSelection.equalsIgnoreCase("insertOrder")))
			{
				ComboBox<String> inputComboBox = new ComboBox<String>();
				String dataArray[][] = holtDistributorFunctions.getOrderTable(myStmt);
				
				for(int j = 1; !(dataArray[j][0] == null); j++)
				{
					inputComboBox.getItems().addAll(
							dataArray[j][0] + " - " + dataArray[j][2]
									);
				}

				GridPane.setConstraints(inputComboBox, 0, i);
				Label inputLabel = new Label(input);
				GridPane.setConstraints(inputLabel, 1, i);
				grid.getChildren().addAll(inputComboBox, inputLabel);
				comboBoxList.add(inputComboBox);
				i++;
			}
			else{
				TextField textField = new TextField();
				textField.setPromptText(input);
				GridPane.setConstraints(textField, 0, i);
				Label inputLabel = new Label(input);
				GridPane.setConstraints(inputLabel, 1, i);
				grid.getChildren().addAll(textField, inputLabel);
				textFields.add(textField);
				i++;
			}
		}
		inputDataScene = new Scene(grid, XgridSize, YgridSize);
	}
	
	public void invoiceItemsLayout(String dataArray[][])
	{
				//GridPane with 10px padding around edge
				GridPane grid = new GridPane();
				grid.setPadding(new Insets(10, 10, 10, 10));
				grid.setVgap(8);
				grid.setHgap(10);

				//Labels
				Label titleLabel = new Label("Ordered Items");
				titleLabel.setFont(new Font("Arial", 40));
				GridPane.setConstraints(titleLabel, 0, 0);
				grid.getChildren().addAll(titleLabel);
				int i;
				for(i = 1; !(dataArray[i][0]==(null)); i++)
				{
					Label itemLabel = new Label("Item Number : " + dataArray[i][1]);
					itemLabel.setFont(new Font("Arial", 20));
					GridPane.setConstraints(itemLabel, 0, i);
					
					ComboBox<String> inputComboBox = new ComboBox<String>();
					
					Label shipQuantityLabel = new Label("Ship Quantity :");
					GridPane.setConstraints(shipQuantityLabel, 1, i);

					for(int j = 0; Integer.parseInt(dataArray[i][2]) - Integer.parseInt(dataArray[i][3]) >=  j; j++)
					{
						String w = j + "";
						inputComboBox.getItems().addAll(
								w
								);
					}					
					GridPane.setConstraints(inputComboBox, 2, i);
					
					
					grid.getChildren().addAll(inputComboBox, itemLabel, shipQuantityLabel);
					comboBoxList.add(inputComboBox);
				}
				inputAcceptButton = new Button("Accept");
				inputAcceptButton.setOnAction(this);
				GridPane.setConstraints(inputAcceptButton, 0, i +1);
				grid.getChildren().addAll(inputAcceptButton);

				invoiceItemScene = new Scene(grid, 500, 500);
	}
	
	
	public String getDateTime()
	{
		   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   //get current date time with Date()
		   Date date = new Date();
		   return dateFormat.format(date);
	}
	
	public void reset()
	{
			comboBoxList.clear();
			textFields.clear();
			if(!(inputSelection.equalsIgnoreCase("insertLoginInfo")) && !(inputSelection.equalsIgnoreCase("backToStart"))){
				homeSceneLayout();
				window.setScene(HomeScene);
			}
			else{
				try{
						myConn.close();
				}
				catch(Exception e)
				{
					AlertBox alertbox = new AlertBox();
					alertbox.display("Couldnt Close Connection.", e.toString());
					e.printStackTrace();
				}
				startSceneLayout();
				window.setScene(startScene);
			}
	}
	


}
