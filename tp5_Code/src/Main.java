import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();
        System.out.println("TP05 : Graphes");

        // Partie 1: A completer : Création du graphe

        Node A = new Node(0, "A"),
                B = new Node(1, "B"),
                C = new Node(2, "C"),
                D = new Node(3, "D"),
                E = new Node(4, "E"),
                F = new Node(5, "F"),
                G = new Node(6, "G");
        List<Node> nodes = Arrays.asList(A, B, C, D, E, F, G);

        Edge AB = new Edge(A, B, 2),
                AC = new Edge(A, C, 1),
                BA = new Edge(B, A, 2),
                BD = new Edge(B, D, 1),
                BC = new Edge(B, C, 2),
                BE = new Edge(B, E, 3),
                CA = new Edge(C, A, 1),
                CB = new Edge(C, B, 2),
                CD = new Edge(C, D, 4),
                CE = new Edge(C, E, 3),
                CF = new Edge(C, F, 5),
                DB = new Edge(D, B, 1),
                DC = new Edge(D, C, 4),
                DF = new Edge(D, F, 6),
                DG = new Edge(D, G, 5),
                EB = new Edge(E, B, 3),
                EC = new Edge(E, C, 3),
                EF = new Edge(E, F, 1),
                FC = new Edge(F, C, 5),
                FD = new Edge(F, D, 6),
                FE = new Edge(F, E, 1),
                FG = new Edge(F, G, 2),
                GD = new Edge(G, D, 5),
                GF = new Edge(G, F, 2);
        List<Edge> edges = Arrays.asList(AB, AC, BA, BD, BC, BE, CA, CB, CD, CE, CF, DB, DC, DF, DG, EB, EC, EF, FC, FD, FE, FG, GD, GF);

        g.setNodes(nodes);
        g.setEdges(edges);

        // Partie 2: A completer : Implémentation de l’algorithme Dijkstra

        Dijkstra d = new Dijkstra(g);

        d.findPath(A,G);

        d.showTable();

        // Partie 3 : Afficher le chemin le plus court
        System.out.println(d.printShortPath(A, D));

    }
}
