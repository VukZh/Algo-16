package demoucronapp;

public class DemoucronApp {

    public static void main(String[] args) {
        Demoucron G = new Demoucron(12);

        G.setArr(2, new int[]{0, 7, 10});
        G.setArr(3, new int[]{6});
        G.setArr(4, new int[]{3});
        G.setArr(5, new int[]{1});
        G.setArr(6, new int[]{11});
        G.setArr(7, new int[]{6});
        G.setArr(8, new int[]{2, 9});
        G.setArr(10, new int[]{11});

        G.displayGraph(); // вывод графа      

        G.demoucron(); // вычисляем
        G.displayLevel(); // вывод вершин по уровням

    }
}
