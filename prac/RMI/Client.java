import java.rmi.*;
import java.util.*;


public class Client {

	public static void main (String [] arg){
	
		Scanner sc = new Scanner(System.in);
		
		try{
		
			String serverUrl = "rmi://localhost/Server";
			ServerInterface serverintf = (ServerInterface) Naming.lookup(serverUrl);
			
			System.out.println("Enter num1: ");
			double num1 = sc.nextDouble();
			
			System.out.println("Enter num2: ");
			double num2 = sc.nextDouble();
			
			System.out.println("Num1: " + num1 + " Num2: " + num2);
			
			System.out.println("\n Results -->");
			
			System.out.println("Addition: " + serverintf.addition(num1, num2));
			System.out.println("Subtraction: " + serverintf.subtraction(num1, num2));
			System.out.println("Multiplication: " + serverintf.multiplication(num1, num2));
			System.out.println("Division: " + serverintf.division(num1, num2));
		
		}catch (Exception e){
			e.printStackTrace();
		}
	
	}

}
