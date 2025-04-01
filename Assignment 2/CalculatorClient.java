import CalculatorApp.*;
import CalculatorApp.CalculatorHelper;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class CalculatorClient{
	public static void main(String [] args){
		try{
			ORB orb = ORB.init(args,null);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			String name = "Calculator";
			
			Calculator clac = CalculatorHelper.narrow(ncRef.resolve_str(name));
			
			System.out.println("ADD: " + clac.add(10.5f,5.5f));
			System.out.println("SUB: " + clac.subtract(10.5f,5.5f));
			System.out.println("MUL: " + clac.multiply(10.5f,5.5f));
			System.out.println("DIV: " + clac.divide(10.5f,5.5f));
		}
		
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
