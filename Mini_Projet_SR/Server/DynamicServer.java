import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;
public class DynamicServer {
	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.codebase", "file:../rmi/");

			if(System.getSecurityManager() == null){System.setSecurityManager(new SecurityManager());}
			

		      Registry registry = LocateRegistry.createRegistry(1099); 
		      System.out.println( "Le serveur : Construction de l'implementation");
		      System.out.println("L'objet Fabrique est  dans la RMIregistry");
		Properties p= System.getProperties();
		String url=p.getProperty("java.rmi.server.codebase");
		Class ClasseServeur = RMIClassLoader.loadClass(url,"FabMulti");

		registry.rebind("Fabrique",(Remote)ClasseServeur.getDeclaredConstructor().newInstance());
		      System.out.println ("Le serveur est pret.") ;
		      System.out.println("Attente des invocations des clients ...");
		    }
		catch (Exception e) {
			    System.out.println("Erreur de liaison de l'objet Fabrique");
		      System.out.println(e.toString());
		      }
		} 
}
