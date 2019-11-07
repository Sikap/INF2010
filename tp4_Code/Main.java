import java.util.*;


public class Main {
    /**
     * Fonction principale
     */
    public static void main(String[] args) {
        // Creer un monceau avec 22 éléments et un tableau équivalent
        int numItems = 22;
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>(true);

        Integer[] items = new Integer[numItems];

        int i;
        int j;

        // En insérant les éléments un a un
        for (i = 11, j = 0; j != numItems; i = (i + 37), j++) {
            testHeap.offer(i);
            items[j] = i;

            i %= numItems;
        }

        // en construisant le monceau depuis le depart
        System.out.println("Monceau min contruit element par element:");
        System.out.println(testHeap.printFancyTree());

        testHeap = new BinaryHeap<Integer>(false);
        // en inserant les elements un a un
        for (Integer item : items)
            testHeap.offer(item);

        // en construisant le monceau depuis le depart
        System.out.println("Monceau max contruit element par element:");
        System.out.println(testHeap.printFancyTree());

        testHeap = new BinaryHeap<Integer>(items, false);
        System.out.println("Monceau max contruit a partir d'un tableau:");
        System.out.println(testHeap.printFancyTree());

        testHeap = new BinaryHeap<Integer>(items, true);
        System.out.println("Monceau min contruit a partir d'un tableau:");
        System.out.println(testHeap.printFancyTree());

        System.out.println();
        System.out.println("Affichage recursif:");
        System.out.println(testHeap.printFancyTree());

        System.out.println("Affichage non recursif:");
        System.out.println(testHeap.nonRecursivePrintFancyTree());

        System.out.println();
        System.out.println("Tableau d'origine:");
        System.out.println(printArray(items));

        BinaryHeap.heapSort(items);
        System.out.println("Tableau ordonne:");
        System.out.println(printArray(items));

        BinaryHeap.heapSortReverse(items);
        System.out.println("Tableau inversement ordonne:");
        System.out.println(printArray(items));


        /*
         * Ajouter appels pour repondre a la question
         **/
        System.out.println();
        System.out.println("Test d'implantation :");
        System.out.println();
        System.out.println("Test de la methode offer:");
        testHeap = new BinaryHeap<>(true);
        PriorityQueue testQueue = new PriorityQueue(testHeap);
        for (i = 11, j = 0; j != numItems; i = (i + 37), j++) {
            testHeap.offer(i);
            testQueue.offer(i);
            items[j] = i;
            i %= numItems;
        }
        String heapData = "";
        String queueData = "";
        Iterator heapIterator = testHeap.iterator();
        Iterator queueIterator = testQueue.iterator();
        while (heapIterator.hasNext()) {
            heapData += heapIterator.next();
            if (heapIterator.hasNext())
                heapData += ", ";
        }
        while (queueIterator.hasNext()) {
            queueData += queueIterator.next();
            if (queueIterator.hasNext())
                queueData += ", ";
        }
        System.out.println("Affichage du BinaryHeap:");
        System.out.println(heapData);
        System.out.println("Affichage du PriorityQueue :");
        System.out.println(queueData);
        System.out.println("Test offer(null):");
        try {
            testHeap.offer(null);
        } catch (NullPointerException e) {
            System.out.println("On peut pas inserer null dans BinaryHeap");
        }
        try {
            testQueue.offer(null);
        } catch (NullPointerException e) {
            System.out.println("On peut pas inserer null dans PriorityQueue");
        }
        System.out.println();


        System.out.println("Test de la methode Next:");
        System.out.println("Test Next sans modification concconcurent:");
        try {
            while (heapIterator.hasNext()) {
                heapIterator.next();
            }
            System.out.println("BinaryHeap:Reussis");
        } catch (ConcurrentModificationException a) {
            System.out.println("BinaryHeap:Echec ");
        }
        try {
            while (queueIterator.hasNext()) {
                queueIterator.next();
            }
            System.out.println("PriorityQueue:Reussis");
        } catch (ConcurrentModificationException a) {
            System.out.println("PriorityQueue:Echec ");
        }
        System.out.println("Test Next avec plusieur modification concurent:");

        try {
            testHeap.offer(1);
            while (heapIterator.hasNext()) {
                heapIterator.next();
            }
            System.out.println("BinaryHeap:Reussis");
        } catch (ConcurrentModificationException a) {
            System.out.println("BinaryHeap:Echec");
        }

        try {
            testQueue.offer(1);
            while (queueIterator.hasNext()) {
                queueIterator.next();
            }
            System.out.println("PriorityQueue:Reussis");
        } catch (ConcurrentModificationException a) {
            System.out.println("PriorityQueue:Echec ");
        }


        System.out.println();
        System.out.println("Test d'operation remove:");

        try {
            heapIterator.remove();
        } catch (UnsupportedOperationException a) {
            System.out.println("BinaryHeap:Operation non suporter ");
        }

        System.out.println();
        System.out.println("Test d'operation poll:");
        System.out.println("poll min :");
        Integer heapPoll = testHeap.poll();
        Integer queuePoll = (Integer) testQueue.poll();
        System.out.println("BinaryHeap: " + heapPoll + " = min de BinaryHeap");
        System.out.println("PriorityQueue: " + queuePoll + " = min de PriorityQueue ");
        testHeap = new BinaryHeap<Integer>(items, false);
        testQueue = new PriorityQueue(testHeap.size(), Comparator.reverseOrder());
        testQueue.addAll(testHeap);
        System.out.println("poll max :");
        heapPoll = testHeap.poll();
        queuePoll = (Integer) testQueue.poll();
        System.out.println("BinaryHeap: " + heapPoll + " = max de BinaryHeap");
        System.out.println("PriorityQueue: " + queuePoll + " = max de PriorityQueue ");
    }

    private static <AnyType> String printArray(AnyType[] a) {
        String outputString = "";

        for (AnyType item : a) {
            outputString += item;
            outputString += ", ";
        }

        return outputString.substring(0, outputString.length() - 2);
    }
}
