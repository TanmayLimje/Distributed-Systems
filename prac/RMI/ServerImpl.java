import java.rmi.*;
import java.rmi.server.*;

class ServerImpl extends UnicastRemoteObject implements ServerInterface 
{

	public ServerImpl()  throws RemoteException{
	
	}	
	
	public double addition (double num1, double num2)throws RemoteException{
		return num1 + num2;
	}
	public double subtraction (double num1, double num2)throws RemoteException{
		return num1 - num2;
	}
	public double multiplication (double num1, double num2)throws RemoteException{
		return num1 * num2;
	}
	public double division (double num1, double num2)throws RemoteException{
		if(num2 == 0)
			System.out.println("Cant divide by zero");
		return num1/num2;
	}
}
