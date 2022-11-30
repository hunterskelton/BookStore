package hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BookStoreApp 
{
	// GUI Setup
	private static JFrame frame;
	private static JTabbedPane tabPane;
	private static JPanel mainPanel;
	private static JLabel mainHeader;
	private static JButton mainButton;
	private static JPanel invPanel;
	private static JLabel invHeader;
	private static JTextArea invInfo;
	private static JTextField invSearch;
	private static JButton invButton;
	private static JTextArea invDisplay;
	private static JScrollPane invScroll;
	private static JPanel rentPanel;
	private static JLabel rentHeader;
	private static JTextArea rentInfo;
	private static JTextField rentSearch;
	private static JButton rentButton;
	private static JTextArea rentDisplay;
	private static JScrollPane rentScroll;
	private static JPanel cusPanel;
	private static JLabel cusHeader;
	private static JTextArea cusInfo;
	private static JTextField cusSearch;
	private static JButton cusButton;
	private static JTextArea cusDisplay;
	private static JScrollPane cusScroll;
	private static JPanel CRPanel;
	private static JLabel CRHeader;
	private static JTextArea CRDisplay;
	private static JScrollPane CRScroll;
	public static Font myFont;
	private final static String newline = "\n";
	
	private static void GUI() 
	{
		// Creates ArrayLists, then fills them with the appropriate info according to the text files
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		ArrayList<Book> bookList = new ArrayList<Book>();
		ArrayList<Rental> rentalList = new ArrayList<Rental>();
		ArrayList<Integer> stockCount = new ArrayList<Integer>();
		ArrayList<Integer> cusRentalCount = new ArrayList<Integer>();
		
		try 
		{
			File customerInput = new File("customer_info.txt");
			Scanner scCustomer = new Scanner(customerInput);
			while (scCustomer.hasNextLine()) 
			{
				String customerData = scCustomer.nextLine();
				String info[] = customerData.split(",");
				customerList.add(new Customer(info[0], info[1], info[2], info[3], Integer.parseInt(info[4])));
				cusRentalCount.add(Integer.parseInt(info[4]));
			}
			File bookInput = new File("book_info.txt");
			Scanner scBook = new Scanner(bookInput);
			while (scBook.hasNextLine()) 
			{
				String bookData = scBook.nextLine();
				String info[] = bookData.split(",");
				bookList.add(new Book(info[0], info[1], info[2], Integer.parseInt(info[3]), info[4], Integer.parseInt(info[5])));
				stockCount.add(Integer.parseInt(info[5]));
			}
			File rentalInput = new File("bookRental_info.txt");
			Scanner scRental = new Scanner(rentalInput);
			while (scRental.hasNextLine()) 
			{
				String rentalData = scRental.nextLine();
				String info[] = rentalData.split(",");
				rentalList.add(new Rental(info[0], info[1]));
			}
		}
		catch (FileNotFoundException errorIn) 
		{
			System.out.println("File Not Found.");
		}
		
		frame = new JFrame("Bookstore");
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.getContentPane();	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFont = new Font("Helvetica", Font.PLAIN, 18);
		
	// Main Panel
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(20, 20, 760, 560);
		mainPanel.setBackground(Color.GRAY);
		mainHeader = new JLabel("Welcome to The Bookstore!");
		mainHeader.setFont(myFont);
		mainHeader.setBounds(30, 30, 500, 30);
		mainButton = new JButton("Update the System");
		mainButton.setBounds(214, 422, 315, 30);
		mainButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					File bookOutput = new File("book_info.txt");
					PrintWriter pBook = new PrintWriter(bookOutput);
					for (int i = 0; i < bookList.size(); i++)
					{
						pBook.println(bookList.get(i).getTitle()+","+bookList.get(i).getAuthorFN()+","
									  +bookList.get(i).getAuthorLN()+","+bookList.get(i).getPageCount()+","
						              +bookList.get(i).getPublisher()+","+stockCount.get(i));
					}
					pBook.close();
					File customerOutput = new File("customer_info.txt");
					PrintWriter pCustomer = new PrintWriter(customerOutput);
					for (int i = 0; i < customerList.size(); i++)
					{
						pCustomer.println(customerList.get(i).getCustomerFN()+","+customerList.get(i).getCustomerLN()+","
					                  +customerList.get(i).getEmailAddress()+","+customerList.get(i).getPhoneNumber()+","
					                  +cusRentalCount.get(i));
					}
					pCustomer.close();
					File bookRentalOutput = new File("bookRental_info.txt");
					PrintWriter pBookRental = new PrintWriter(bookRentalOutput);
					for (int i = 0; i < rentalList.size(); i++)
					{
						pBookRental.println(rentalList.get(i).getRentee()+","+rentalList.get(i).getRentedBook());
					}
					pBookRental.close();
					System.exit(0);
				}
				catch (FileNotFoundException errorOut) 
				{
					System.out.println("File Not Found.");
				}
			}
		});
		mainPanel.add(mainButton);
		mainPanel.add(mainHeader);
		
	// Inventory Panel
		invPanel = new JPanel();
		invPanel.setLayout(null);
		invPanel.setBounds(20, 20, 760, 560);
		invPanel.setBackground(Color.gray);
		invHeader = new JLabel("Our Inventory");
		invHeader.setFont(myFont);
		invHeader.setBounds(30, 30, 500, 30);
		invInfo = new JTextArea(5, 20);
		invInfo.setFont(myFont);
		invInfo.setBounds(30, 455, 315, 30);
		invInfo.setEditable(false);
		invInfo.append("Warning: Inputs are case-sensitive!");
		invDisplay = new JTextArea(5, 20);
		invDisplay.setFont(myFont);
		invDisplay.setBounds(375, 20, 355, 465);
		invDisplay.setEditable(false);
		for(int i = 0; i < bookList.size(); i++) 
		{
			invDisplay.append("     -   "+ bookList.get(i).getTitle()+ newline);
			invDisplay.append("         Author: "+ bookList.get(i).getAuthorFN()+" "+bookList.get(i).getAuthorLN()+ newline);
			invDisplay.append("         Page Count: "+ bookList.get(i).getPageCount()+ newline);
			invDisplay.append("         Publisher: "+ bookList.get(i).getPublisher()+ newline);
			invDisplay.append("         In Stock: "+ stockCount.get(i)+ newline + newline);
		}
		invSearch = new JTextField(25);
		invSearch.setFont(myFont);
		invSearch.setText("Search for a title");
		invSearch.setBounds(30, 100, 315, 30);
		invSearch.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				invSearch.setText("");
			}
		});
		invButton = new JButton("Search");
		invButton.setBounds(30, 140, 315, 30);
		invButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				int count = 0;
				for(int i = 0; i < bookList.size(); i++) 
				{
					if(invSearch.getText().equals(bookList.get(i).getTitle())) 
					{
						invDisplay.setText("");
						invDisplay.append("     -   "+ bookList.get(i).getTitle()+ newline);
						invDisplay.append("         Author: "+ bookList.get(i).getAuthorFN()+" "+bookList.get(i).getAuthorLN()+ newline);
						invDisplay.append("         Page Count: "+ bookList.get(i).getPageCount()+ newline);
						invDisplay.append("         Publisher: "+ bookList.get(i).getPublisher()+ newline);
						invDisplay.append("         In Stock: "+ stockCount.get(i)+ newline + newline);
						invInfo.setText("");
						invInfo.append("Success!");
						count++;
					}
				}
				if (count == 0) 
				{
					invInfo.setText("");
					invInfo.append("Sorry, we couldn't find that title.");
				}
			}
		});
		invScroll = new JScrollPane (invDisplay);
		invScroll.setBounds(375, 20, 355, 465);
		invScroll.setSize(355, 465);
		invPanel.add(invInfo);
		invPanel.add(invScroll);
		invPanel.add(invHeader);
		invPanel.add(invSearch);
		invPanel.add(invButton);
		
	// Rent Panel
		rentPanel = new JPanel();
		rentPanel.setLayout(null);
		rentPanel.setBounds(20, 20, 760, 560);
		rentPanel.setBackground(Color.gray);
		rentHeader = new JLabel("Book Rental");
		rentHeader.setFont(myFont);
		rentHeader.setBounds(30, 30, 500, 30);
		rentInfo = new JTextArea(5, 20);
		rentInfo.setFont(myFont);
		rentInfo.setBounds(30, 455, 315, 30);
		rentInfo.setEditable(false);
		rentInfo.append("Warning: Inputs are case-sensitive!");
		rentDisplay = new JTextArea(5, 20);
		rentDisplay.setFont(myFont);
		rentDisplay.setBounds(375, 20, 355, 465);
		rentDisplay.setEditable(false);
		for(int i = 0; i < bookList.size(); i++) 
		{
			rentDisplay.append("     -   "+ customerList.get(i).getCustomerFN()+" "+customerList.get(i).getCustomerLN()+ newline);
			rentDisplay.append("         Current Rentals: "+ cusRentalCount.get(i)+ newline + newline);
		}
		rentSearch = new JTextField(25);
		rentSearch.setFont(myFont);
		rentSearch.setText("Enter a rentee");
		rentSearch.setBounds(30, 100, 315, 30);
		rentSearch.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				rentSearch.setText("");
			}
		});
		rentButton = new JButton("Go");
		rentButton.setBounds(30, 140, 315, 30);
		rentButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String renteeInput = rentSearch.getText();
				int count = 0;
				for(int i = 0; i < bookList.size(); i++) 
				{
					if(renteeInput.equals(customerList.get(i).getCustomerFN()+" "+customerList.get(i).getCustomerLN())) 
					{
						rentDisplay.setText("");
						for(int j = 0; j < bookList.size(); j++) 
						{
							rentDisplay.append("     -   "+ bookList.get(j).getTitle()+ newline);
							rentDisplay.append("         In Stock: "+ stockCount.get(j)+ newline + newline);
						}
						rentInfo.setText("");
						rentInfo.append("Success!");
						rentSearch.setText("Enter the book to be rented");
						Customer rentee = customerList.get(i);
						rentButton.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e) 
							{
								String bookRentedInput = rentSearch.getText();
								int count = 0;
								for(int i = 0; i < bookList.size(); i++) 
								{
									if(bookRentedInput.equals(bookList.get(i).getTitle())) 
									{
										if(stockCount.get(i) == 0) 
										{
											rentDisplay.setText("Sorry, but there are no books of this type currently in stock.");
										}
										else if (stockCount.get(i) > 0) 
										{
											rentalList.add(new Rental(renteeInput, bookRentedInput));
											stockCount.set(i, stockCount.get(i) - 1);
											cusRentalCount.set(i, cusRentalCount.get(i) + 1);
											rentDisplay.setText("Success!");
											rentInfo.append("Success!");
										}
										count++;
									}
								}
								if (count == 0) 
								{
									rentInfo.setText("");
									rentInfo.append("Sorry, we couldn't find that title.");
								}
							}
						});
						count++;
					}
				}
				if (count == 0) 
				{
					rentInfo.setText("");
				}
			}
		});
		rentScroll = new JScrollPane (rentDisplay);
		rentScroll.setBounds(375, 20, 355, 465);
		rentScroll.setSize(355, 465);
		rentPanel.add(rentInfo);
		rentPanel.add(rentScroll);
		rentPanel.add(rentHeader);
		rentPanel.add(rentSearch);
		rentPanel.add(rentButton);
		
	// Customer Panel
		cusPanel = new JPanel();
		cusPanel.setLayout(null);
		cusPanel.setBounds(20, 20, 760, 560);
		cusPanel.setBackground(Color.gray);
		cusHeader = new JLabel("Customers");
		cusHeader.setFont(myFont);
		cusHeader.setBounds(30, 30, 500, 30);
		cusInfo = new JTextArea(5, 20);
		cusInfo.setFont(myFont);
		cusInfo.setBounds(30, 455, 315, 30);
		cusInfo.setEditable(false);
		cusInfo.append("Warning: Inputs are case-sensitive!");
		cusDisplay = new JTextArea(5, 20);
		cusDisplay.setFont(myFont);
		cusDisplay.setBounds(375, 20, 355, 465);
		cusDisplay.setEditable(false);
		for(int i = 0; i < bookList.size(); i++) 
		{
			cusDisplay.append("     -   "+ customerList.get(i).getCustomerFN()+" "+customerList.get(i).getCustomerLN()+ newline);;
			cusDisplay.append("         Email Address: "+ customerList.get(i).getEmailAddress()+ newline);
			cusDisplay.append("         Phone Number: "+ customerList.get(i).getPhoneNumber()+ newline);
			cusDisplay.append("         Current Rentals: "+ cusRentalCount.get(i)+ newline + newline);
		}
		
		cusSearch = new JTextField(25);
		cusSearch.setFont(myFont);
		cusSearch.setText("Search a name");
		cusSearch.setBounds(30, 100, 315, 30);
		cusSearch.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				cusSearch.setText("");
			}
		});
		cusButton = new JButton("Search");
		cusButton.setBounds(30, 140, 315, 30);
		cusButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				int count = 0;
				for(int i = 0; i < bookList.size(); i++) 
				{
					if(cusSearch.getText().equals(customerList.get(i).getCustomerFN()+" "+customerList.get(i).getCustomerLN())) 
					{
						cusDisplay.setText("");
						cusDisplay.append("     -   "+ customerList.get(i).getCustomerFN()+" "+customerList.get(i).getCustomerLN()+ newline);;
						cusDisplay.append("         Email Address: "+ customerList.get(i).getEmailAddress()+ newline);
						cusDisplay.append("         Phone Number: "+ customerList.get(i).getPhoneNumber()+ newline);
						cusDisplay.append("         Current Rentals: "+ customerList.get(i).getRentals()+ newline + newline);
						cusInfo.setText("");
						cusInfo.append("Success!");
						count++;
					}
				}
				if (count == 0) 
				{
					cusInfo.setText("");
					cusInfo.append("Sorry, we couldn't find that person.");
				}
			}
		});
		cusScroll = new JScrollPane (cusDisplay);
		cusScroll.setBounds(375, 20, 355, 465);
		cusScroll.setSize(355, 465);
		cusPanel.add(cusInfo);
		cusPanel.add(cusScroll);
		cusPanel.add(cusHeader);
		cusPanel.add(cusSearch);
		cusPanel.add(cusButton);
		
	// Current Rentals Panel
		CRPanel = new JPanel();
		CRPanel.setLayout(null);
		CRPanel.setBounds(20, 20, 760, 560);
		CRPanel.setBackground(Color.gray);
		CRHeader = new JLabel("Current Rentals");
		CRHeader.setFont(myFont);
		CRHeader.setBounds(30, 30, 500, 30);
		CRDisplay = new JTextArea(5, 20);
		CRDisplay.setFont(myFont);
		CRDisplay.setBounds(375, 20, 355, 465);
		CRDisplay.setEditable(false);
		for(int i = 0; i < rentalList.size(); i++) 
		{
			CRDisplay.append("     -   "+ rentalList.get(i).getRentedBook()+ newline);
			CRDisplay.append("         Rentee: "+ (rentalList.get(i).getRentee().split(" ", 2)[1])+ newline + newline);
		}
		CRScroll = new JScrollPane (CRDisplay);
		CRScroll.setBounds(375, 20, 355, 465);
		CRScroll.setSize(355, 465);
		
		CRPanel.add(CRScroll);
		CRPanel.add(CRHeader);
		
		
		tabPane = new JTabbedPane();
		tabPane.setBounds(20, 20, 750, 530);
		tabPane.add("Home",mainPanel);
		tabPane.add("Our Inventory",invPanel);
		tabPane.add("Rent a Book",rentPanel);
		tabPane.add("Customer Information",cusPanel);
		tabPane.add("Current Rentals",CRPanel);
		
		frame.add(tabPane);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				GUI();
			}
		});
	}
}
