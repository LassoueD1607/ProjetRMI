import java.rmi.*;
import java.rmi.server.*;

public class FabMulti extends UnicastRemoteObject implements FabMultiInterface {
  public FabMulti() throws RemoteException {}
  public MultiplicationInterface newMultiplication() throws RemoteException {
    return new Multiplication();
  }
}
