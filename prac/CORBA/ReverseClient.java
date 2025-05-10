import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.*;

public class ReverseClient {

	public static void main (String [] arg){
	
		try{
			
			ORB orb = ORB.init(arg, null);
			Object objRef = orb.resolve_initial_references("NameService");
			
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			String name = "Reverse";
			
			ReverseModule.Reverse reverseImpl = ReverseModule.ReverseHelper.narrow(ncRef.resolve_str(name));
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter String : ");
			String str = sc.nextLine();
			
			String tempStr = reverseImpl.reverse(str);
			
			System.out.println(tempStr);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	
	}

}
