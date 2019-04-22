package demoucronapp;

public class Demoucron {

    private final int[][] G; // граф

    private final int[][] M; // матрица смежности

    private final boolean[] F; // массив пройденных вершин

    private final DArray<DArray<Integer>> level; // массив пройденных вершин по уровням

    private int checkVertex; // число пройденных вершин при сортировке

    private int Iteration; // уровни сортировки

    Demoucron(int arr[][]) {

        G = arr;
        M = new int[G.length][G.length];
        F = new boolean[G.length];
        level = new DArray<>();
        Iteration = 0;
        checkVertex = 0;
    }

    private void setL(int lvl, int v, int r) {
        DArray<Integer> tmp;
        if (v == 0) {
            tmp = new DArray<>();
        } else {
            tmp = level.get(lvl);
        }
        tmp.add(v, r);
        level.add(lvl, tmp);
    }

    public void createMatrix() { // инициализация узла матрицы смежности
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G[i].length; j++) {
                M[i][G[i][j]] = 1;
            }
        }
    }

    private int getL(int lvl, int v) {
        DArray<Integer> tmp = level.get(lvl);
        return tmp.get(v);
    }

    private int sizeL(int l) { // число вершин, на которые есть связь с вершины v
        DArray<Integer> tmp = level.get(l);
        return tmp.size();
    }

    public void displayLevel() { // вывод уровней
        System.out.println("Level");
        for (int i = 0; i < Iteration; i++) {
            System.out.print("Level " + i);
            for (int j = 0; j < sizeL(i); j++) {
                System.out.print(" > " + getL(i, j));
            }
            System.out.println("");
        }
    }

    private int[][] returnLevel() { // вывод уровней
        int[][] result = new int[Iteration][];
        for (int i = 0; i < Iteration; i++) {
            int[] res1 = new int[sizeL(i)];
            for (int j = 0; j < sizeL(i); j++) {

                res1[j] = getL(i, j);
            }
            result[i] = res1;
        }
        return result;
    }

    public void displayMatrix() { // вывод матрицы смежности
        createMatrix();
        System.out.println("Matrix");
        for (int i = 0; i < G.length; i++) {
            System.out.printf("%2d%s", i, ":");
            for (int j = 0; j < G.length; j++) {
                System.out.printf("%2d", M[i][j]);
            }
            System.out.println("");
        }
    }

    private void delRow(int y) { // пометка строки матрицы смежности
        for (int i = 0; i < G.length; i++) {
            M[y][i] = -1;
        }
    }

    private boolean findCol0(int x) { // поиск 0 в столбце в матрице смежности
        boolean res = true;
        for (int i = 0; i < G.length; i++) {
            if (M[i][x] > 0) {
                res = false;
                break;
            }
        }
        if (res) {
            F[x] = true;
        }
        return res;
    }

    public int[][] demoucron() {
        createMatrix();
        while (checkVertex < G.length) {
            int step = 0;
            for (int i = 0; i < G.length; i++) {
                if (!F[i] && findCol0(i)) {
                    setL(Iteration, step, i);
//                    System.out.println(" Iteration " + Iteration + " step " + step + " i " + i);
                    step ++;
                }
            }
//            FindV();     
            if (step == 0) { // не найдены вершины с 0 входов -> мы в цикле
                System.out.println("FIND LOOP !!!");
                break;
            }
            checkVertex += step;
            for (int i = 0; i < sizeL(Iteration); i++) {
//                System.out.println("del " + getL(Iteration, i));
                delRow(getL(Iteration, i));
            }

            Iteration++;
        }
        return returnLevel();
    }

    public void FindV() { // вывод пройденных вершин
        System.out.println("Find");
        System.out.print("   ");
        for (int i = 0; i < G.length; i++) {
            if (F[i]) {
                System.out.print(" 1");
            } else {
                System.out.print(" 0");
            }
        }
        System.out.println("");
    }

}
