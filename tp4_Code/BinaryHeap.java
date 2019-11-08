

import java.util.*;


public class BinaryHeap<AnyType extends Comparable<? super AnyType>> extends AbstractQueue<AnyType> {
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;      // Nombre d'elements
    private AnyType[] array;    // Tableau contenant les donnees (premier element a l'indice 1)
    private boolean min;
    private int modifications;    // Nombre de modifications apportees a ce monceau

    @SuppressWarnings("unchecked")
    public BinaryHeap(boolean min) {
        this.min = min;
        currentSize = 0;
        array = (AnyType[]) new Comparable[DEFAULT_CAPACITY + 1];
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(AnyType[] items, boolean min) {
        this.min = min;
        // COMPLETEZ
        array = (AnyType[]) new Comparable[items.length + 1];
        for (int i = 1; i <= items.length; i++) {
            array[i] = items[i - 1];
            currentSize++;
        }
        if (min) {
            buildMinHeap();
        } else
            buildMaxHeap();
        // invoquez buildMinHeap() ou buildMaxHeap() en fonction du parametre min;
    }

    public boolean offer(AnyType x) {
        if (x == null)
            throw new NullPointerException();

        if (currentSize + 1 == array.length)
            doubleArray();
        // COMPLETEZ
        AnyType tmp;
        currentSize++;
        int nomberOfCaseToVisit[] = caseToViste();
        for (int i = 0; i < nomberOfCaseToVisit.length; i++) {
            if (array[nomberOfCaseToVisit[i]] == null) {
                array[nomberOfCaseToVisit[i]] = x;
            } else {
                int position = array[nomberOfCaseToVisit[i]].compareTo(x);
                if (position > 0) {
                    if (min) {
                        tmp = array[nomberOfCaseToVisit[i]];
                        array[nomberOfCaseToVisit[i]] = x;
                        x = tmp;
                        modifications++;
                    } else {

                    }
                } else {
                    if (min) {

                    } else {
                        tmp = array[nomberOfCaseToVisit[i]];
                        array[nomberOfCaseToVisit[i]] = x;
                        x = tmp;
                        modifications++;
                    }
                }
            }
        }


        return true;
    }

    private int[] caseToViste() {
        int position = size();
        int nbElement = (int) (Math.log10(position) / Math.log10(2) + 1);//+1 because element at 0 snd the first element at 1.
        int nomberOfCaseToVisit[] = new int[nbElement];

        for (; nbElement > 0; nbElement--) {
            nomberOfCaseToVisit[nbElement - 1] = position;
            position /= 2;
        }
        return nomberOfCaseToVisit;
    }

    public AnyType peek() {
        if (!isEmpty())
            return array[1];
        return null;
    }

    public AnyType poll() {
        //COMPLETEZ
        AnyType element = peek();
        if (peek() != null) {
            array[1] = array[currentSize--];
        }
        if (min) {
            buildMinHeap();
        } else
            buildMaxHeap();
        return element;
    }

    public Iterator<AnyType> iterator() {
        return new HeapIterator();
    }

    private void buildMinHeap() {
        for (int i = (currentSize / 2); i > 0; i--) {
            percolateDownMinHeap(i, currentSize);
        }
    }

    private void buildMaxHeap() {
        //COMPLETEZ
        for (int i = (currentSize / 2); i > 0; i--)
            percolateDownMaxHeap(i, currentSize);
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }

    public void clear() {
        currentSize = 0;
        modifications = 0;
        array = (AnyType[]) new Comparable[DEFAULT_CAPACITY + 1];
    }

    private static int leftChild(int i, boolean heapIndexing) {
        return (heapIndexing ? 2 * i : 2 * i + 1);
    }

    private void swapReferences(int index1, int index2) {
        swapReferences(array, index1, index2);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void swapReferences(AnyType[] array, int index1, int index2) {

        AnyType tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    @SuppressWarnings("unchecked")
    private void doubleArray() {
        AnyType[] newArray;

        newArray = (AnyType[]) new Comparable[array.length * 2];
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        array = newArray;
    }


    /**
     * @param hole Position a percoler
     * @param size Indice max du tableau
     */
    private void percolateDownMinHeap(int hole, int size) {
        percolateDownMinHeap(array, hole, size, true);
        modifications++;
    }

    /**
     * @param array        Tableau d'element
     * @param hole         Position a percoler
     * @param size         Indice max du tableau
     * @param heapIndexing True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percolateDownMinHeap(AnyType[] array, int hole, int size, boolean heapIndexing) {
        //COMPLETEZ
        int child;
        AnyType tmp;
        for (tmp = array[hole]; leftChild(hole, heapIndexing) < size; hole = child) {
            child = leftChild(hole, heapIndexing);
            if (child != size - 1 && array[child].compareTo(array[child + 1]) > 0)
                child++;
            if (tmp.compareTo(array[child]) > 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    /**
     * @param hole Position a percoler
     * @param size Indice max du tableau
     */
    private void percolateDownMaxHeap(int hole, int size) {
        percolateDownMaxHeap(array, hole, size, true);
        modifications++;
    }

    /**
     * @param array        Tableau d'element
     * @param hole         Position a percoler
     * @param size         Indice max du tableau
     * @param heapIndexing True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percolateDownMaxHeap(AnyType[] array, int hole, int size, boolean heapIndexing) {
        //COMPLETEZ

        int child;
        AnyType tmp;
        for (tmp = array[hole]; leftChild(hole, heapIndexing) < size; hole = child) {
            child = leftChild(hole, heapIndexing);
            if (child != size - 1 && array[child].compareTo(array[child + 1]) < 0)
                child++;
            if (tmp.compareTo(array[child]) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void heapSort(AnyType[] a) {
        //COMPLETEZ
        for (int i = a.length / 2; i >= 0; i--)
            percolateDownMaxHeap(a, i, a.length, false);
        for (int i = a.length - 1; i > 0; i--) {
            swapReferences(a, 0, i);
            percolateDownMaxHeap(a, 0, i, false);
        }

    }

    public static <AnyType extends Comparable<? super AnyType>>
    void heapSortReverse(AnyType[] a) {
        for (int i = a.length / 2; i >= 0; i--)
            percolateDownMinHeap(a, i, a.length, false);
        for (int i = a.length - 1; i > 0; i--) {
            swapReferences(a, 0, i);
            percolateDownMinHeap(a, 0, i, false);
        }

    }

    public String nonRecursivePrintFancyTree() {
        StringBuilder outputString = new StringBuilder();
        String prefix = "";
        ArrayDeque<Integer> array= new ArrayDeque<Integer> (0);
        array.push(1);
        while (array.size()!=0)
        {
            Integer left =2*array.getFirst();
            Integer right= 2*array.getFirst()+1;
            outputString.append(prefix).append("|__") ;
            outputString.append(this.array[array.getFirst()]).append("\n");
            if (array.getFirst() % 2 == 0)
                prefix+="|  " ; // un | et trois espace
            else
                prefix +=("   "); // quatre espaces

            if(2*array.getFirst()<currentSize) {
                array.pop();
                array.push(right);
                array.push(left);
            }else {
                if(array.getFirst()%2==0) {
                    prefix = prefix.substring(0, (array.getFirst() - 4));
                } else
                prefix=prefix.substring(0,(array.getFirst()/2)+1);

                array.pop();
            }
        }

        return outputString.toString();
    }

    public String printFancyTree() {
        return printFancyTree(1, "");
    }

    private String printFancyTree(int index, String prefix) {

        String outputString = "";

        outputString = prefix + "|__";

        if (index <= currentSize) {
            boolean isLeaf = index > currentSize / 2;

            outputString += array[index] + "\n";

            String _prefix = prefix;

            if (index % 2 == 0)
                _prefix += "|  "; // un | et trois espace
            else
                _prefix += "   "; // quatre espaces

            if (!isLeaf) {
                outputString += printFancyTree(2 * index, _prefix);
                outputString += printFancyTree(2 * index + 1, _prefix);
            }
        } else
            outputString += "null\n";

        return outputString;
    }

    private class HeapIterator implements Iterator {
        private int curentPosition = 1;
        private int curentModification = modifications;

        public boolean hasNext() {
            //COMPLETEZ
            if (curentPosition <= currentSize) {
                return true;
            } else return false;
        }

        public Object next() throws NoSuchElementException,
                ConcurrentModificationException,
                UnsupportedOperationException {
            //COMPLETEZ
            if (!hasNext())
                throw new NoSuchElementException();
            if (modifications == curentModification) {
                return array[curentPosition++];
            } else throw new ConcurrentModificationException();

        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
