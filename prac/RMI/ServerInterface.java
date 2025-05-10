import java.rmi.*;

interface ServerInterface extends Remote{
	
	public double addition (double num1, double num2) throws RemoteException;
	public double subtraction (double num1, double num2) throws RemoteException;
	public double multiplication (double num1, double num2) throws RemoteException;
	public double division (double num1, double num2) throws RemoteException;
	
}
