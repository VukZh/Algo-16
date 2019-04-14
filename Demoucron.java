package demoucronapp;

public class Demoucron {

    private final DArray<DArray<Integer>> G; // граф

    private final DArray<DArray<Integer>> M; // матрица смежности

    private final DArray<Boolean> F; // массив пройденных вершин

    private final DArray<DArray<Integer>> level; // массив пройденных вершин по уровням

    private int checkVertex; // число пройденных вершин при сортировке

    private int Iteration; // уровни сортировки

    Demoucron() {
        G = new DArray<>();
        M = new DArray<>();
        F = new DArray<>();
        level = new DArray<>();

        Iteration = 0;
        checkVertex = 0;
    }

    Demoucron(int size) {

        G = new DArray<>();
        DArray<Integer> tmp = new DArray<>();
        tmp.add(0, -1);
        for (int i = 0; i < size; i++) {
            G.add(i, tmp);
        }
        M = new DArray<>();
        F = new DArray<>();
        level = new DArray<>();
        Iteration = 0;
        checkVertex = 0;
    }

    public void set(int g_i, int el_i, int r) { // установка для матрицы вектора смежности (g_i - вершина, el_i индекс массива вершин куда уходят ребра, r - вершины на которые можно уйти с g_i)
        DArray<Integer> tmp;
        if (el_i == 0) {
            tmp = new DArray<>();
        } else {
            tmp = G.get(g_i);
        }
        tmp.add(el_i, r);
        G.add(g_i, tmp);
    }

    public void setArr(int v, int[] arr) { // 
        int g_i = v;
        int sizeArr = arr.length;
        for (int el_i = 0; el_i < sizeArr; el_i++) {
            set(g_i, el_i, arr[el_i]);
        }
    }

    private void setL(int lvl, int v, int r) { // установка для матрицы вектора смежности (g_i - вершина, el_i индекс массива вершин куда уходят ребра, r - вершины на которые можно уйти с g_i)
        DArray<Integer> tmp;
        if (v == 0) {
            tmp = new DArray<>();
        } else {
            tmp = level.get(lvl);
        }
        tmp.add(v, r);
        level.add(lvl, tmp);
    }

    private void initM(int x, int y, int f) { // инициализация узла матрицы смежности
        DArray<Integer> tmp;
        if (x == 0) {
            tmp = new DArray<>();
        } else {
            tmp = M.get(y);
        }
        tmp.add(x, f);
        M.add(y, tmp);
    }

    private void setM(int x, int y, int f) { // установка узла матрицы смежности
        DArray<Integer> tmp;
        tmp = M.get(y);
        tmp.add(x, f);
        M.set(y, tmp);
    }

    private int getM(int x, int y) { // вывод узла матрицы смежности
        DArray<Integer> tmp = M.get(y);
        return tmp.get(x);
    }

    public int get(int g_i, int el_i) { // получение вершины из матрицы вектора смежности (g_i - вершина с которой идет связь на нашу вершину, el_i индекс массива вершин для вершины g_i)
        DArray<Integer> tmp = G.get(g_i);
        return tmp.get(el_i);
    }

    private int getL(int lvl, int v) { // получение вершины из матрицы вектора смежности (g_i - вершина с которой идет связь на нашу вершину, el_i индекс массива вершин для вершины g_i)
        DArray<Integer> tmp = level.get(lvl);
        return tmp.get(v);
    }

    private int sizeV() { // число вершин графа
        return G.size();
    }

    private int sizeS(int v) { // число вершин, на которые есть связь с вершины v
        DArray<Integer> tmp = G.get(v);
        return tmp.size();
    }

    private int sizeL(int v) { // число вершин, на которые есть связь с вершины v
        DArray<Integer> tmp = level.get(v);
        return tmp.size();
    }

    public void displayGraph() { // вывод графа
        System.out.println("Graph");
        for (int i = 0; i < sizeV(); i++) {
            System.out.print("i-" + i);
            for (int j = 0; j < sizeS(i); j++) {
                if (get(i, j) != -1) {
                    System.out.print(" > " + get(i, j));
                }
            }
            System.out.println("");
        }
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

    private void createMatrix() { // создание матрицы смежности для алгоритма Демукрона
        DArray<Integer> tmp;
        for (int i = 0; i < sizeV(); i++) { // подготовка
            F.add(i, Boolean.FALSE);
            for (int j = 0; j < sizeV(); j++) {
                initM(i, j, 0);
            }
        }

        for (int i = 0; i < sizeV(); i++) { // заполнение из матрицы вектора смежности
            tmp = G.get(i);
            for (int j = 0; j < sizeS(i); j++) {
                if (tmp.get(j) == -1) {
                    break;
                } else {
                    int t = tmp.get(j);
                    setM(t, i, 1);
                }
            }
        }
    }

    public void displayMatrix() { // вывод матрицы смежности
        DArray<Integer> tmp;
        System.out.println("Matrix");
        for (int i = 0; i < sizeV(); i++) {
            System.out.printf("%2d%s", i, ":");
            for (int j = 0; j < sizeV(); j++) {
                tmp = M.get(i);
                System.out.printf("%2d", tmp.get(j));
            }
            System.out.println("");
        }
    }

    private void delRow(int y) { // пометка строки матрицы смежности
        for (int i = 0; i < sizeV(); i++) {
            setM(i, y, -1);
        }
    }

    private boolean findCol0(int x) { // поиск 0 в столбце в матрице смежности
        boolean res = true;
        for (int i = 0; i < sizeV(); i++) {
            if (getM(x, i) > 0) {
                res = false;
                break;
            }
        }
        if (res) {
            F.set(x, Boolean.TRUE);
        }
        return res;
    }

    public void FindV() { // вывод пройденных вершин
        System.out.println("Find");
        System.out.print("   ");
        for (int i = 0; i < sizeV(); i++) {
            if (F.get(i)) {
                System.out.print(" 1");
            } else {
                System.out.print(" 0");
            }
        }
        System.out.println("");
    }

    public void demoucron() {
        createMatrix();
        while (checkVertex < sizeV()) { // пока не проверены все вершины - проверяем вершины с 0 входов

//            System.out.println("...........................");   ////////////////////  вывод матрицы в каждом уровне        
//            System.out.println("Iteration " + Iteration);    ////////////////////////            
//            displayMatrix(); ////////////////// 
            int step = 0;
            for (int i = 0; i < sizeV(); i++) {
                if (!F.get(i) && findCol0(i)) {
                    setL(Iteration, step, i);
                    step++;
                }
            }

//            FindV();   ////////////////// вывод найденных вершин после каждой итерации
            checkVertex += step;
            for (int i = 0; i < sizeL(Iteration); i++) {
                delRow(getL(Iteration, i));
            }
            if (step == 0) { // не найдены вершины с 0 входов -> мы в цикле
                System.out.println("FIND LOOP !!!");
                break;
            }
            Iteration++;
        }
    }
}
