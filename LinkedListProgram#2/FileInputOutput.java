/*Name: Taha Rangwala
 * Date: October 24, 2016
 * Purpose: The purpose of this class is to all the user to create new files to store information, add information to existing files, or
 * delete files all together.
 */

import java.io.*;//allows for more java elements to be used

import javax.swing.JFrame;//allows for more java elements to be used

import javax.swing.JOptionPane;//allows for more java elements to be used

public class FileInputOutput {

	/*Purpose: The purpose of this method is to write a file or create a new file
	 * @param obj This is the object that is passed in the will be put into the file
	 */
	public static void write(Object obj){
		
		try{
			
			FileOutputStream File = new FileOutputStream("Data.dat");
			ObjectOutputStream Output = new ObjectOutputStream(File);
			
			//Output.
			Output.writeObject(obj);
			File.close();
			Output.close();
			
		}
		catch(IOException E){
			System.out.println(E);
		}
	}
	
	/*Purpose: This method returns a file to the user in object form
	 * @return This method returns an object that is the file the user wants to obtain since it has information from
	 * a previous start up of the program
	 */
	public static Object getFile(){
		
		Object Output = new Object();
		try{
			
			FileInputStream File2 = new FileInputStream("Data.dat");
			ObjectInputStream Input = new ObjectInputStream(File2);
			Output = Input.readObject();
			File2.close();
			Input.close();
		}
		catch(ClassNotFoundException E){
			System.out.println(E);
		}
		catch(IOException E){
			System.out.println(E);
		}
		
		return Output;
		
	}
	
	/*Purpose: The purpose of this method is to delete files
	 * @param fileName This is the name of the file that is going to be deleted
	 */
	public static void Delete(String fileName){
		try{
			(new File(fileName)).delete();
		}catch(Exception E){
			
		}
	}
	
}
