import java.rmi.*;
import java.rmi.server.*;
public class Multiplication extends UnicastRemoteObject implements MultiplicationInterface 
{
       public Multiplication() throws RemoteException
       {
	    super();
       }
	public  int[][] MultiplicationMatrice( int[][] A, int[][] B, int debLig, int finLig, int debCol, int finCol, int n)throws RemoteException {
		int [][]C = new int[finLig][finCol];
		for(int i=debLig; i<finLig; i++){
			for(int j=debCol; j<finCol; j++) {
				C[i][j] = 0;
				for (int k=0; k<n; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}

		}
		return C;
	}

}
