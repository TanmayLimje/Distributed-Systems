import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

public class ReverseServer {

	public static void main(String [] arg){
	
		try{
		
			ORB orb = ORB.init(arg, null);
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));	
			rootPOA.the_POAManager().activate();
			
			ReverseImpl reverseService = new ReverseImpl();
			Object ref = rootPOA.servant_to_reference(reverseService);
			
			System.out.println("Step 1..");
			ReverseModule.Reverse href = ReverseModule.ReverseHelper.narrow(ref);
			System.out.println("Created a reference to the reverse object");
			
			System.out.println("Step 2..");
			Object objRef = orb.resolve_initial_references("NameService");
			System.out.println("Obtained a reference to the NameService");
			
			System.out.println("Step 3..");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			System.out.println("Narrowed the reference to NamingContext");
			
			System.out.println("Step 4..");
			String name = "Reverse";
			NameComponent[] path = ncRef.to_name(name);
			ncRef.rebind(path, href);
			
			System.out.println("Reverse Object is bound in the NameService with name: " + name);
			
			System.out.println("\nServer is ready and waiting client to connect...");
			
		
		}catch (Exception e){
			e.printStackTrace();
		}
	
	}

}












