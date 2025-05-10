import java.rmi.*;

public class Server {

	public static void main(String [] arg)
	{
	
		try{
		
			ServerImpl serverImpl = new ServerImpl();
			Naming.rebind("Server", serverImpl);
			System.out.println("Server has Started !");
		
		}catch (Exception e){
			e.printStackTrace();
		}
	
	}

}
