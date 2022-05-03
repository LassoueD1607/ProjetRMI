import java.rmi.registry.*;
import java.util.Scanner;

public class MultiplicationClient {

  //Affichage d'une matrice :
  public void afficher(int[][] M, int ligA, int colB) {
    for (int i = 0; i < ligA; i++) {
      for (int j = 0; j < colB; j++) {
        System.out.print(M[i][j] + "  ");
      }
      System.out.print("\n");
    }
  }

  //Remplissage d'une matrice à partir du clavier:
  public int[][] lire_matrice(int ligne, int colonne) {
    Scanner S = new Scanner(System.in);
		int i, j;
    int[][] M;
    

    M = new int[ligne][colonne];

    for (i = 0; i < M.length; i = i + 1) {
      for (j = 0; j < M[0].length; j = j + 1) {
        System.out.print(" [" + (i + 1) + "," + (j + 1) + "]:");
        M[i][j] = S.nextInt();
      }
    }
    return M;
  }
	//Création de la résultat à partir d'un produit par blocs
  public int[][] Rassamblage(int[][] a,int[][] b,int[][] c,int[][] d,int lig,int col) {
				int[][] result = new int[lig][col];

				for (int i = 0; i < lig; i++) {
					for (int j = 0; j < col; j++) {
						if (i < lig / 2) {
							if (j < col / 2) {
								result[i][j] = a[i][j];
							} else {
								result[i][j] = b[i][j];
							}
						} else {
							if (j < col / 2) {
								result[i][j] = c[i][j];
							} else {
								result[i][j] = d[i][j];
							}
						}
					}
				}
				return result;
			}
	//programme principale
  public MultiplicationClient(String[] args) {
    try {
      //Déclaration des matrices vides A et B et de leurs tailles (lignes, colonnes)
      int[][] A;
      int[][] B;
      int ligA = 0, colA = 0, ligB = 0, colB = 0;
      //Saisie des tailles des matrices A et B
      Scanner S = new Scanner(System.in);
      //La boucle while nous assure que les matrices ne sont pas nulles et que le nombre de colonne de A sont égaux aux nombres de lignes B pour faire la multiplication
      while (ligA == 0 || colA == 0 || ligB == 0 || colB == 0 || colA != ligB) {
        System.out.print("Donner nombre de lignes de la matrice A: ");
        ligA = S.nextInt();
        System.out.print("Donner nombre de colonnes de la matrice A: ");
        colA = S.nextInt();
        System.out.print("Donner nombre de lignes de la matrice B: ");
        ligB = S.nextInt();
        System.out.print("Donner nombre de colonnes de la matrice A: ");
        colB = S.nextInt();
      }
      System.out.println("Remplir les elements de la matrice A");
      A = new int[ligA][colA];
      A = lire_matrice(ligA, colA);

      System.out.println("Remplir les elements de la matrice B");
      B = new int[ligB][colB];
      B = lire_matrice(ligB, colB);

      //if(System.getSecurityManager() == null)
      //System.setSecurityManager(new RMISecurityManager());
      Registry reg = LocateRegistry.getRegistry("localhost", 1099);
      FabMultiInterface fabrique = (FabMultiInterface) reg.lookup("Fabrique");

      MultiplicationInterface Obj1;
      Obj1 = (MultiplicationInterface) fabrique.newMultiplication();
      MultiplicationInterface Obj2;
      Obj2 = (MultiplicationInterface) fabrique.newMultiplication();
      MultiplicationInterface Obj3;
      Obj3 = (MultiplicationInterface) fabrique.newMultiplication();
      MultiplicationInterface Obj4;
      Obj4 = (MultiplicationInterface) fabrique.newMultiplication();

      int[][] result1 = Obj1.MultiplicationMatrice(A,B,0,ligA/2,0,colB/2,colA);
      int[][] result2 = Obj2.MultiplicationMatrice(A,B,0,ligA/2,colB/2,colB,colA);
      int[][] result3 = Obj3.MultiplicationMatrice(A,B,ligA/2,ligA,0,colB/2,colA);
      int[][] result4 = Obj4.MultiplicationMatrice(A,B,ligA/2,ligA,colB/2,colB,colA);

      System.out.println(
        "Le resultat de multiplication des deux matrices est :"
      );
			//affichage de résultat
      afficher(Rassamblage(result1, result2, result3, result4, ligA, colB),ligA,colB);
			//fermeture de Scanner
      S.close();
    } catch (Exception e) {
      System.out.println("Erreur d'acces a l'objet distant!!!");
      System.out.println(e.toString());
    }
  }
}
