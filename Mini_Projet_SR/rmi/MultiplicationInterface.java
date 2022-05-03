import java.rmi.Remote;
import java.rmi.RemoteException;
public interface MultiplicationInterface extends Remote 
{
	int[][] MultiplicationMatrice( int[][] m1, int[][] m2, int debI, int finI, int debJ, int finJ, int comm) throws RemoteException;
}

