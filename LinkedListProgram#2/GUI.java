/*Name: Taha Rangwala
 * Date: October 24, 2016
 * Purpose: The purpose of this class is to allow the user to use the program. This allows the user to use
 * buttons, input and output fields, radio buttons, and more. The information from the database is relayed back
 * to this class so the user can see it.
 */

import javax.swing.*;//allows for more window objects

import BreezySwing.*;//allow for more window objects

import java.awt.Color;//allows for more colors

import java.util.*;//allows for iterator

//GUI class header
public class GUI extends GBFrame{

	//Declaring private instance variables
	private LinkedList List;//Linked List class object
	//Declaring window objects
	private JLabel firstNameLabel, lastNameLabel, yearLabel, GPALabel;
	private JTextField firstNameField, lastNameField;
	private JLabel editFirstNameLabel, editLastNameLabel;
	private JTextField editFirstNameField, editLastNameField;
	private IntegerField yearField;
	private DoubleField GPAField;
	private JTextArea outputArea;
	private JButton Add, Print, Specific, Delete, deleteAll, Edit, SaveAndExit;
	private JRadioButton Iterator, noIterator;
	
	/*Constructor method of the GUI class
	 */
	public GUI(){
		//initiailizing window objects
		firstNameLabel = addLabel("First Name",1,1,1,1);
		firstNameField = addTextField("",1,2,1,1);
		lastNameLabel = addLabel("Last Name",1,3,1,1);
		lastNameField = addTextField("",1,4,1,1);
		yearLabel = addLabel("Graduation Year",2,1,1,1);
		yearField = addIntegerField(0,2,2,1,1);
		GPALabel = addLabel("GPA",2,3,1,1);
		GPAField = addDoubleField(0,2,4,1,1);
		editFirstNameLabel = addLabel("Edit First Name",3,1,1,1);
		editFirstNameField = addTextField("",3,2,1,1);
		editLastNameLabel = addLabel("Edit last Name",3,3,1,1);
		editLastNameField = addTextField("",3,4,1,1);
		Iterator = addRadioButton("Iterator",4,1,1,1);
		noIterator = addRadioButton("No Iterator",4,3,1,1);
		Add = addButton("Add",5,1,1,1);
		Print = addButton("Print",5,3,1,1);
		deleteAll = addButton("Delete All",5,4,1,1);
		outputArea = addTextArea("",6,1,4,1);
		Specific = addButton("Display Specific",7,1,1,1);
		Delete = addButton("Delete Specific",7,2,1,1);
		Edit = addButton("Edit",7,3,1,1);
		SaveAndExit = addButton("Save and Exit",7,4,1,1);
		ButtonGroup bG = new ButtonGroup();
		bG.add(Iterator);
		bG.add(noIterator);
		Iterator.setSelected(true);
		//Checking for files that may exist
		try{
			List = (LinkedList)FileInputOutput.getFile();
			outputArea.setText(List.toString());
		}
		catch(Exception E){
			List = new LinkedList();
			outputArea.setText("No Files Exist In The Database!");
		}
	}
	
	/*Purpose: The purpose of this method is to determine which button the user is pressing and then performing
	 * an action according to that
	 * @param buttonObj This is the button object which determine what button the user is pressing
	 */
	public void buttonClicked(JButton buttonObj){
		if(buttonObj == Add){//adding values
			if(checkNumberValues()){
				try{
					Student.checkStudentInputs(firstNameField.getText(), lastNameField.getText(),
					yearField.getNumber(), GPAField.getNumber());
					List.Add(new Student(firstNameField.getText(), lastNameField.getText(),
					yearField.getNumber(), GPAField.getNumber()));
					firstNameField.setText("");
					lastNameField.setText("");
					yearField.setText("");
					GPAField.setText("");
					firstNameField.grabFocus();
				}
				catch(Exception E){
					JOptionPane.showMessageDialog(new JFrame(),E.getLocalizedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
				JOptionPane.showMessageDialog(new JFrame(),"Please Input Valid Numbers", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		else if(buttonObj == Print){//printing all values
			if(Iterator.isSelected()){
				try{
					outputArea.setText(printAll());
				}
				catch(Exception E){
					JOptionPane.showMessageDialog(new JFrame(),E.getLocalizedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				try{
					outputArea.setText(List.printAllNoIterator());
				}
				catch(Exception E){
					JOptionPane.showMessageDialog(new JFrame(),E.getLocalizedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(buttonObj == deleteAll){//deleting all values
			FileInputOutput.Delete("Data.dat");
			outputArea.setText("All Data Has Been Cleared!");
			List = new LinkedList();
		}
		else if(buttonObj == Specific){//printing specific values
			try{
				Student.checkStudentInputs(firstNameField.getText(), lastNameField.getText(), 0,0);
				outputArea.setText(List.printSpecific(new Student(firstNameField.getText(), lastNameField.getText(), 0,0)));
				firstNameField.setText("");
				lastNameField.setText("");
				yearField.setText("");
				GPAField.setText("");
				firstNameField.grabFocus();
			}
			catch(Exception E){
				JOptionPane.showMessageDialog(new JFrame(),E.getLocalizedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(buttonObj == Delete){//deleting specific values
			if(Iterator.isSelected()){
				try{
					Student.checkStudentInputs(firstNameField.getText(), lastNameField.getText(), 0,0);
					deleteWithIterator(new Student(firstNameField.getText(), lastNameField.getText(),0,0));
					firstNameField.setText("");
					lastNameField.setText("");
					yearField.setText("");
					GPAField.setText("");
					firstNameField.grabFocus();
					outputArea.setText("");
				}
				catch(Exception E){
					JOptionPane.showMessageDialog(new JFrame(),E.getLocalizedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				try{
					Student.checkStudentInputs(firstNameField.getText(), lastNameField.getText(), 0,0);
					List.deleteSpecific(new Student(firstNameField.getText(), lastNameField.getText(),0,0));
					firstNameField.setText("");
					lastNameField.setText("");
					yearField.setText("");
					GPAField.setText("");
					firstNameField.grabFocus();
					outputArea.setText("");
				}
				catch(Exception E){
					JOptionPane.showMessageDialog(new JFrame(),E.getLocalizedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if (buttonObj == Edit){//editing values
			if(checkNumberValues()){
				try{
					Student.checkStudentInputs(firstNameField.getText(), lastNameField.getText(), 0,0);
					Student.checkStudentInputs(editFirstNameField.getText(), editLastNameField.getText(),
					yearField.getNumber(), GPAField.getNumber());
					List.Edit(new Student(firstNameField.getText(), lastNameField.getText(), 0,0), 
					new Student(editFirstNameField.getText(), editLastNameField.getText(),
					yearField.getNumber(), GPAField.getNumber()));
					firstNameField.setText("");
					lastNameField.setText("");
					editFirstNameField.setText("");
					editLastNameField.setText("");
					yearField.setText("");
					GPAField.setText("");
					firstNameField.grabFocus();
				}
				catch(Exception E){
					JOptionPane.showMessageDialog(new JFrame(),E.getLocalizedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
				JOptionPane.showMessageDialog(new JFrame(),"Please Input Valid Numbers", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		else{//saving data and closing the program
			FileInputOutput.write(List);
			this.dispose();
		}
	}
	
	/*Purpose: The purpose of this method is to print all of the data in the program using an iterator
	 * @return This returns a string value of all of the data in the program
	 */
	private String printAll(){
		List.checkHead();
		String Output = "";
		Iterator <Student> Iter = List.Iterator();
		while(Iter.hasNext()){
			Output += Iter.next();
		}
		return Output;
	}
	
	/*Purpose: The purpose of this method is to remove a specific value that the user wants to be deleted
	 * @param Value This is the value the user wants to be deleted
	 */
	private <T> void deleteWithIterator(T Value){
		List.checkHead();
		Iterator <Student> Iter = List.Iterator();
		while(Iter.hasNext()){
			if(Iter.next().getLastName().equals(((Student) Value).getLastName())){
				Iter.remove();
				return;
			}
		}
		throw new IllegalArgumentException("No Student Was Found");
	}
	
	/*Purpose: The purpose of this method is to check if the year and gpa fields have valid number values
	 * @return This method returns a boolean value of whether or not both number fields hold valid numbers
	 */
	private boolean checkNumberValues(){
		return yearField.isValidNumber() && GPAField.isValidNumber();
	}
	
	//Main method to set up the GUI
	public static void main (String [] args){
		GUI theMainGUI = new GUI();//GUI object
		theMainGUI.setSize(500,500);//size of the GUI interface
		theMainGUI.setTitle("Taha's Linked List Program");//title of GUI interface
		theMainGUI.setVisible(true);//visibility of interface
		theMainGUI.setLocationRelativeTo(null);//Location is in center of screen
		theMainGUI.setLookAndFeel("MOTIF");//This changes the look of the GUI interface
		theMainGUI.getContentPane().setBackground(new Color(169,229,255));//background of GUI is light blue
	}
	
}
