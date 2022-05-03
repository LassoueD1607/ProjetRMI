import java.rmi.*;

public interface FabMultiInterface extends Remote {
  public MultiplicationInterface newMultiplication() throws RemoteException;
}
