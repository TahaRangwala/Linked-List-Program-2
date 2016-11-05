/*Name: Taha Rangwala
 * Date: October 24, 2016
 * Purpose: The purpose of this class is to take all the information from all the other classes
 * and do some calculations and relay them back to the gui class for the user to see. This class essentially
 * holds all the information that the user wants to see and does necessary calculations as well.
 */

import java.io.Serializable;//allows for files to be saved

import java.util.*;

import BreezySwing.Format;

//LinkedList class header
public class LinkedList <T> implements Serializable{
	
	//Declaring private instance variables
	private ListNode <T> Head;//ListNode head containing data
	
	/*Purpose: The purpose of the constructor method is to initialize the instance variables
	 */
	public LinkedList(){
		Head = null;
	}
	
	/*Purpose: The purpose of this method is to add the value of the first node of the list node
	 * @param newValue This is the value added to the first node of the list node
	 */
	public void addFirst(T newValue){
		ListNode Temp = Head;
		Head = new ListNode(newValue, Temp);
	}
	
	/*Purpose: The purpose of this method is to add the value of the last node of the list node
	 * @param newValue This is the value added to the last node of the list node
	 */
	public void addLast(T newValue){
		if(Head != null){
			ListNode Temp = Head;
			while(Temp.getNext() != null){
				Temp = Temp.getNext();
			}
			Temp.setNext(new ListNode(newValue, null));
		}
		else addFirst(newValue);
	}
	
	/*Purpose: This method prints all of the information from the list node
	 * @return This returns a string value of all of the information in the list node
	 */
	public String printAll(){
		if(Head == null)
			throw new IllegalArgumentException("No Students Have Been Inputted");
		String Output = "";
		Output += Head.getValue().toString();
		ListNode Temp = Head;
		while(Temp.getNext() != null){
			Temp = Temp.getNext();
			Output += Temp.getValue().toString();
		}
		return Output;
	}
	
	/*Purpose: This method prints all of the information of the first node
	 * @return This returns a string value of all of the information of the first node
	 */
	public String printFirst(){
		if(Head == null)
			throw new IllegalArgumentException("No Students Have Been Inputted");
		String Output = "";
		return Output + Head.getValue();
	}
	
	/*Purpose: This method prints all of the information of the last node
	 * @return This returns a string value of all of the information of the last node
	 */
	public String printLast(){
		if(Head == null)
			throw new IllegalArgumentException("No Students Have Been Inputted");
		String Output = "";
		ListNode Temp = Head;
		while(Temp.getNext() != null){
			Temp = Temp.getNext();
		}
		return Output + Temp.getValue();
	}
	
	/*Purpose: This method removes the first node in the list node
	 */
	public void removeFirst(){
		if(Head == null)
			throw new IllegalArgumentException("No Students Have Been Inputted");
		if(Head.getNext() != null)
			Head = Head.getNext();
		else Head = null;
	}
	
	/*Purpose: This method removes the last node in the list node
	 */
	public void removeLast(){
		if(Head == null)
			throw new IllegalArgumentException("No Students Have Been Inputted");
		else if(Head.getNext() != null){
			ListNode Temp = Head;
			while(Temp.getNext() != null && Temp.getNext().getNext() != null){
				Temp = Temp.getNext();
			}
			Temp.setNext(null);
		}
		else 
			Head = null;
	}
	
	/*Purpose: The purpose of this method is to add values to the list node
	 * @param Value This is the value being added to the list node
	 */
	public void Add(T Value){
		ListNode Current = Head, Previous = null;
		if(Head == null)
			Head = new ListNode(Value,null);
		else{
			while(Current != null){
				if(((Student) Value).compareTo(((Student) Current.getValue())) < 0){
					if(Current == Head){
						Head = new ListNode(Value, Head);
					}
					else
						Previous.setNext(new ListNode(Value,Current));
					return;
				}
				else if(((Student) Value).compareTo(((Student) Current.getValue())) > 0){
					if((Current.getNext() != null && (((Student) Value).compareTo(((Student) Current.getNext().getValue())) < 0)||
					Current.getNext() == null)){
						Current.setNext(new ListNode(Value,Current.getNext()));
						return;
					}
				}
				Previous = Current;
				Current = Current.getNext();
			}
		}
	}
	
	/*Purpose: The purpose of this method is to output all of the information to the user
	 * @return This returns a string value of all of the information in the program
	 */
	public String toString(){
		return printAllNoIterator();
	}
	
	/*Purpose: The purpose of this method is to print all of the data in the program without using iterator
	 * @return This returns a string value of all of the data in the program
	 */
	public String printAllNoIterator(){
		checkHead();
		String Output = "";
		ListNode Temp = Head;
		while(Temp != null){
			Output += Temp.getValue().toString();
			Temp = Temp.getNext();
		}
		return Output;
	}
	
	/*Purpose: The purpose of this method is to delete a specific value without user iterator
	 * @param Value This is the value that the user wants to be deleted
	 */
	public void deleteSpecific(T Value){
		checkHead();
		ListNode Current = Head, Previous = null;
		while(Current != null){
			if(((Student) Current.getValue()).getFirstName().equals(((Student) Value).getFirstName()) &&
			 (((Student) Current.getValue()).getLastName().equals(((Student) Value).getLastName()))){
				if(Head.getNext() == null && Current == Head)
					Head = null;
				else if(Current == Head)
					Head = Head.getNext();
				else
					Previous.setNext(Current.getNext());
				return;
			}
			Previous = Current;
			Current = Current.getNext();
		}
		throw new IllegalArgumentException("No Student Was Found");
	}
	
	/*Purpose: The purpose of this method is to print a specific value
	 * @param Value This is the value that the user wants to be printed
	 */
	public String printSpecific(T Value){
		checkHead();
		ListNode Temp = Head;
		while(Temp != null){
			if(((Student) Value).getLastName().equals(((Student) Temp.getValue()).getLastName()) && 
			((Student) Value).getFirstName().equals(((Student) Temp.getValue()).getFirstName()))
				return Temp.getValue().toString();
			Temp = Temp.getNext();
		}
		throw new IllegalArgumentException("No Student Was Found!");
	}
	
	/*Purpose: The purpose of this method is to allow the user to edit any value
	 * @param Value This is the value the user wants to change
	 * @param newValue This is the value that will replace the value the user wants to change
	 */
	public void Edit(T Value, T newValue){
		checkHead();
		ListNode Current = Head, Previous = null;
		while(Current != null){
			if(((Student) Value).getLastName().equals(((Student)Current.getValue()).getLastName()) && 
			((Student) Value).getFirstName().equals(((Student)Current.getValue()).getFirstName())){
				if(Head == Current && Head.getNext() == null){
					Head = null;
					Add(newValue);
				}
				else if(Current == Head){
					Head = Head.getNext();
					Add(newValue);
				}
				else{
					Previous.setNext(Current.getNext());
					Add(newValue);
				}
				return;
			}
			Previous = Current;
			Current = Current.getNext();
		}
	}
	
	/*Purpose: This checks to make sure the head has a value or it throws an exception
	 */
	public void checkHead(){
		if(Head == null)
			throw new IllegalArgumentException("There Are No Students!");
	}
	
	/*Purpose: The purpose of this method is to return an iterator using the methods we have made
	 * @return This returns a linked list iterator
	 */
	public Iterator <T> Iterator(){
		return new LinkedListIterator<T>();
	}
	
	/*Name: Taha Rangwala
	 * Date: October 24, 2016
	 * Purpose: The purpose of this class is to ask as an iterator. It implements iterator allowing it to use the
	 * hasNext, next, and remove methods.
	 */
	//LinkedListIterator class header
	public class LinkedListIterator<T> implements Iterator{

		//Declaring private instance variables
		private ListNode Previous;//previous value of current
		private ListNode Current;//current value user is on
		private boolean Removable;//used to determine if you can remove or not
		
		/*Purpose: This is a constructor method to initialize all private instance variables
		 */
		public LinkedListIterator(){
			Current = new ListNode (null, Head);
			Previous = null;
			Removable = false;
		}
		
		/*Purpose: This lets the program know if the current value is null
		 * @return This returns a boolean value on whether or not the current value is null
		 */
		public boolean hasNext() {
			return Current.getNext() != null;
		}

		/*Purpose: The purpose of this method is to output the current value the user is on
		 * @return This returns the current value the user is on
		 */
		public Object next(){
			Removable = true;
			Previous = Current;
			Current = Current.getNext();
			return Current.getValue();
		}

		/*Purpose: The purpose of this method is to remove the current value the user is on
		 */
		public void remove(){
			if(Removable){
				if(Current == Head && Head.getNext() == null){
					Current = null;
					Previous = null;
					Removable = false;
				}
				else if(Current == Head){
					Head = Head.getNext();
					Current = null;
					Previous = null;
					Removable = false;
				}
				else{
					Previous.setNext(Current.getNext());
					Removable = false;
				}
			}
		
		}
	}
}