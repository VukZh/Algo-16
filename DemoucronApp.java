package demoucronapp;

public class DemoucronApp {

    public static void main(String[] args) {
        int[][] a1 = {
            {},
            {},
            {0, 7, 10},
            {6},
            {3},
            {1},
            {11},
            {6},
            {2, 9},
            {},
            {11},
            {},
        };
        
        Demoucron G = new Demoucron(a1);

//        G.displayMatrix();
        int[][] res = G.demoucron(); // вычисляем и выводим

        for (int i = 0; i < res.length; i++) {
            System.out.print("Level " + i + ":");
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(" " + res[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
