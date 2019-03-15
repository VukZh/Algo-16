package demoucronapp;

public class DemoucronApp {

    public static void main(String[] args) {
        Demoucron G = new Demoucron();

        G.set(0, 0, -1); // ввод графа (небезопасный метод - вершины с 0, связи с 0, без проверки связей на несуществующие вершины; -1 - нет исходящих ребер)
        G.set(1, 0, -1);
        G.set(2, 0, 0);
        G.set(2, 1, 7);
        G.set(2, 2, 10);
        G.set(3, 0, 6);
        G.set(4, 0, 3);
        G.set(5, 0, 1);
        G.set(6, 0, 11);
        G.set(7, 0, 6);
        G.set(8, 0, 2);
        G.set(8, 1, 9);
        G.set(9, 0, -1);
        G.set(10, 0, 11);
        G.set(11, 0, -1);

        G.displayGraph(); // вывод графа      

        G.demoucron(); // вычисляем
        G.displayLevel(); // вывод вершин по уровням

    }
}
