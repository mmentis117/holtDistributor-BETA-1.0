import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class customerBranch implements EventHandler<ActionEvent> {

	public Scene customerScene, inputDataScene, TableScene;
	Boolean firstOrder = true;
	ArrayList<TextField> textFields = new ArrayList<TextField>();
	ArrayList<ComboBox<String>> comboBoxList = new ArrayList<ComboBox<String>>();
	Button makePayment, placeOrder, backToStart, inputAcceptButton, backToCustomerHomeButton, viewInvoices;
	Driver driverUsed;
	int customerNumber;
	int orderNumber;

	public void customerSceneLayout(){
		StackPane titleLayerLayout = new StackPane();
		Label homeLabel = new Label("Holt Distributors Customers");
		homeLabel.setFont(new Font("Arial", 30));
		titleLayerLayout.setAlignment(homeLabel, Pos.TOP_CENTER);
		titleLayerLayout.getChildren().addAll(homeLabel);


		//GridPane with 10px padding around edge
		GridPane buttonLayerLayout = new GridPane();
		buttonLayerLayout.setPadding(new Insets(10, 10, 10, 10));
		buttonLayerLayout.setVgap(8);
		buttonLayerLayout.setHgap(10);

		Label paymentsLabel = new Label("Payments");
		paymentsLabel.setFont(new Font("Arial", 20));
		GridPane.setConstraints(paymentsLabel, 0, 0);

		makePayment = new Button("Make a Payment");
		GridPane.setConstraints(makePayment, 1, 0);

		Label orderLabel = new Label("Orders");
		orderLabel.setFont(new Font("Arial", 20));
		GridPane.setConstraints(orderLabel, 0, 1);

		placeOrder = new Button("Place an Order");
		GridPane.setConstraints(placeOrder, 1, 1);

		Label viewInvoicesLabel = new Label("View Invoices");
		viewInvoicesLabel.setFont(new Font("Arial", 20));
		GridPane.setConstraints(viewInvoicesLabel, 0, 2);

		viewInvoices = new Button("View Invoices");
		GridPane.setConstraints(viewInvoices, 1, 2);

		//Get current balance
		float currentBalancefloat = holtDistributorFunctions.getCustomerBalance(driverUsed.myStmt, customerNumber);

		//Show current balance
		Label currentBalanceLabel = new Label("Current Balance : ");
		currentBalanceLabel.setFont(new Font("Arial", 20));
		GridPane.setConstraints(currentBalanceLabel, 0, 3);

		Label currentBalance = new Label("$" + currentBalancefloat);
		currentBalance.setFont(new Font("Arial", 20));
		GridPane.setConstraints(currentBalance, 1, 3);

		backToStart = new Button("Back");
		GridPane.setConstraints(backToStart, 0, 4);

		buttonLayerLayout.getChildren().addAll(paymentsLabel, makePayment, orderLabel, placeOrder, backToStart, viewInvoices, viewInvoicesLabel, currentBalanceLabel, currentBalance);

		BorderPane mainLayout = new BorderPane();
		mainLayout.setTop(titleLayerLayout);
		mainLayout.setCenter(buttonLayerLayout);
		customerScene = new Scene(mainLayout, 500, 250);

		//This class will handle the button events
		makePayment.setOnAction(this);
		placeOrder.setOnAction(this);
		backToStart.setOnAction(this);
		viewInvoices.setOnAction(this);

		//need to disconnect from database on back
	}

	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == backToStart)
		{
			driverUsed.inputSelection = "insertLoginInfo";
			driverUsed.reset();
			//need to disconnect from database on back	
		}
		else if (event.getSource() == makePayment)
		{
			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Payment Amount");



			driverUsed.inputSelection = "customerPayment";
			inputDataLayout("Customer Payment", dataInputs1, 500, 550);
			//Enter input Scene
			driverUsed.window.setScene(inputDataScene);

		}
		else if (event.getSource() == placeOrder)
		{
			ArrayList<String> dataInputs1 = new ArrayList<String>();
			dataInputs1.add("Item Description");
			dataInputs1.add("Item Quantity");
			dataInputs1.add("PO Number");
			dataInputs1.add("Ship to Name");
			dataInputs1.add("Ship to Address 1");
			dataInputs1.add("Ship to Address 2");
			dataInputs1.add("Ship to City");
			dataInputs1.add("Ship to State");
			dataInputs1.add("Ship to Zip");

			driverUsed.inputSelection = "customerOrder";
			inputDataLayout("Customer Order", dataInputs1, 500, 550);
			//Enter input Scene
			driverUsed.window.setScene(inputDataScene);

		}
		else if (event.getSource() == viewInvoices)
		{
			//get Customer name from customer number
			String customerName = holtDistributorFunctions.getCustomerNameByCustomerNumber(driverUsed.myStmt, customerNumber);

			//get Order Numbers from customer name
			String[][] orderNumbers = holtDistributorFunctions.getOrderNumbers(driverUsed.myStmt, customerName);

			String SelectStatement = "";

			ArrayList<Integer> orderNumbers1 = new ArrayList<Integer>();
			for(int i = 1; !(orderNumbers[i][0]  == null); i++)
			{
				orderNumbers1.add(Integer.parseInt(orderNumbers[i][0]));
			}
			for(int orderNumbers2 : orderNumbers1)
			{
				SelectStatement = SelectStatement + " order_number = " + orderNumbers2 + " or";
			}
			if(!SelectStatement.equalsIgnoreCase(""))
			{
				SelectStatement = SelectStatement.substring(0, SelectStatement.length() - 2);

				//get invoices
				String[][] customerInvoiceTable = holtDistributorFunctions.getCustomerInvoiceTable(driverUsed.myStmt, SelectStatement);

				createTableLayout(customerInvoiceTable);
				driverUsed.window.setScene(TableScene);
			}
			else
			{
				AlertBox alertbox = new AlertBox();
				alertbox.display("No Invoice", "No Invoices Available.");
			}
			//			


			//			//get order and item info table
			//			for(int i = 1; !(orderNumbers[i][0]  == null); i++)
			//			{
			//				int currentOrderNumber = Integer.parseInt(orderNumbers[i][0]);
			//				String[][] orderedItems = holtDistributorFunctions.getOrderItemInfo(driverUsed.myStmt, currentOrderNumber);
			//				
			//				int itemNumber = Integer.parseInt(orderNumbers[i][1]);
			//				int quantityShipped = Integer.parseInt(orderNumbers[i][3]);
			//				float itemSalesPrice = Integer.parseInt(orderNumbers[i][4]);
			//				
			//				String[][] basicItemInfo = holtDistributorFunctions.getItemBasicTable(driverUsed.myStmt);
			//				String itemDescription = holtDistributorFunctions.getItemDescriptionByNumber(driverUsed.myStmt, itemNumber);
			//			}

			//			
			//			ArrayList<String> dataInputs1 = new ArrayList<String>();
			//			dataInputs1.add("Item Description");
			//			dataInputs1.add("Item Quantity");
			//			dataInputs1.add("PO Number");
			//			dataInputs1.add("Ship to Name");
			//			dataInputs1.add("Ship to Address 1");
			//			dataInputs1.add("Ship to Address 2");
			//			dataInputs1.add("Ship to City");
			//			dataInputs1.add("Ship to State");
			//			dataInputs1.add("Ship to Zip");
			//			
			//			driverUsed.inputSelection = "customerOrder";
			//			inputDataLayout("Customer Order", dataInputs1, 500, 550);
			//			//Enter input Scene
			//			driverUsed.window.setScene(inputDataScene);

		}
		else if (event.getSource() == inputAcceptButton)
		{
			acceptButton();
		}
		else if (event.getSource() == backToCustomerHomeButton)
		{
			backToCustomerHome();
		}
	}

	public void acceptButton()
	{
		if(driverUsed.inputSelection.equals("customerPayment"))
		{
			try{
				//set customer payment table

				driverUsed.myStmt.executeUpdate(SQLstatements.insertCustomerPaymentTable(customerNumber, getDateTime() , Float.parseFloat(textFields.get(0).getText())));

				//Decrease the customer balance and Increases customer payment
				String dataArray[][] = holtDistributorFunctions.getCustomerSalesTable(driverUsed.myStmt, customerNumber);
				float balance = Float.parseFloat(dataArray[1][3]);
				balance = balance - Float.parseFloat(textFields.get(0).getText());

				float payment = Float.parseFloat(dataArray[1][6]);
				payment = payment + Float.parseFloat(textFields.get(0).getText());

				driverUsed.myStmt.executeUpdate(SQLstatements.updateCustomerBalanceAndPayment(customerNumber, balance , payment));

				backToCustomerHome();
			}
			catch(Exception exc){
				AlertBox alertbox = new AlertBox();
				alertbox.display("Invalid Entry", "You have entered an invalid"
						+ "\n entry type. Please try again.");
			}
		}
		if(driverUsed.inputSelection.equals("customerOrder"))
		{
			try{

				//get Customer name
				String dataArray[][] = holtDistributorFunctions.getCustomerBasicTable(driverUsed.myStmt, customerNumber);
				String name = dataArray[1][1];
				//set Order Table
				if(firstOrder == true)
				{
					driverUsed.myStmt.executeUpdate(SQLstatements.insertCustomerOrderInfo(getDateTime(), name , Integer.parseInt(textFields.get(1).getText()), textFields.get(2).getText(), textFields.get(3).getText(),
							textFields.get(4).getText(), textFields.get(5).getText(), textFields.get(6).getText(),  Integer.parseInt(textFields.get(7).getText())));

					//get order number
					orderNumber = holtDistributorFunctions.getOrderNumber(driverUsed.myStmt, name);

					firstOrder = false;
				}
				//set order and item table
				String itemPrice = comboBoxList.get(0).getValue();
				itemPrice = itemPrice.substring(itemPrice.indexOf('$') + 1, itemPrice.length());
				holtDistributorFunctions.insertOrderAndItemInfo(driverUsed.myStmt, orderNumber, Character.getNumericValue(comboBoxList.get(0).getValue().charAt(0)),   Integer.parseInt(textFields.get(0).getText())
						, 0 , Float.parseFloat(itemPrice));

				//set Customer sales and balance
				float orderTotalCost =  Float.parseFloat(itemPrice) * Integer.parseInt(textFields.get(0).getText());

				backToCustomerHome();
			}
			catch(Exception exc){
				AlertBox alertbox = new AlertBox();
				alertbox.display("Invalid Entry", "You have entered an invalid"
						+ "\n entry type. Please try again.");
				exc.printStackTrace();
			}
		}
	}

	public void setDriver(Driver driver)
	{
		driverUsed = driver;
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
		backToCustomerHomeButton = new Button("Back");
		GridPane.setConstraints(backToCustomerHomeButton, 1, inputFields.size() +1);

		inputAcceptButton.setOnAction(this);
		backToCustomerHomeButton.setOnAction(this);
		grid.getChildren().addAll(inputDailyLabel, inputAcceptButton, backToCustomerHomeButton);

		int i =1;
		for(String input : inputFields)
		{
			if(input.equalsIgnoreCase("Item Description") && !(driverUsed.inputSelection.equalsIgnoreCase("insertOrder")))
			{
				ComboBox<String> inputComboBox = new ComboBox<String>();
				String dataArray[][] = holtDistributorFunctions.getItemBasicTable(driverUsed.myStmt);

				for(int j = 1; !(dataArray[j][0] == null); j++)
				{
					inputComboBox.getItems().addAll(
							dataArray[j][0] + " - " + dataArray[j][1] + " - $" + dataArray[j][2]
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
		backToCustomerHomeButton = new Button("Back");
		tablePane.setAlignment(backToCustomerHomeButton, Pos.BOTTOM_CENTER);
		tablePane.setMargin(backToCustomerHomeButton, new Insets(15,15,15,15));
		tablePane.getChildren().addAll(table, backToCustomerHomeButton);
		TableScene = new Scene(tablePane, 800, 600);

		//This class will handle the button events
		backToCustomerHomeButton.setOnAction(this);
	}

	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//get current date time with Date()
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void backToCustomerHome()
	{
		comboBoxList.clear();
		textFields.clear();
		customerSceneLayout();
		driverUsed.window.setScene(customerScene);
	}
}
