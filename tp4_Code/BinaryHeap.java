import javafx.util.Pair;
import sun.dc.pr.PRError;

import java.lang.reflect.Array;
import java.util.*;


public class BinaryHeap<AnyType extends Comparable<? super AnyType>> extends AbstractQueue<AnyType>
{
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;      // Nombre d'elements
    private AnyType [ ] array;    // Tableau contenant les donnees (premier element a l'indice 1)
    private boolean min;
    private int modifications;    // Nombre de modifications apportees a ce monceau
    
    @SuppressWarnings("unchecked")
    public BinaryHeap( boolean min ){
	    this.min = min;
	    currentSize = 0;
	    array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1];
    }
    
    @SuppressWarnings("unchecked")
    public BinaryHeap( AnyType[] items, boolean min ){
	    this.min = min;
		// COMPLETEZ
        array = (AnyType[]) new Comparable[ items.length +1];
        for(int i=1;i<=items.length;i++) {
            array[i]= items[i-1];
            currentSize++;
        } if(min){
            buildMinHeap();
        }else buildMaxHeap();
	    // invoquez buildMinHeap() ou buildMaxHeap() en fonction du parametre min;
    }
    
    public boolean offer( AnyType x ){
	    if (x == null)
	    throw new NullPointerException("Cannot insert null in a BinaryHeap");

	    if( currentSize + 1 == array.length )
	        doubleArray();
        // COMPLETEZ
        AnyType tmp;
        int nombreCasseAvisiter[]= caseAVisiter();
        for(int i =0;i<nombreCasseAvisiter.length;i++){
            if(array[nombreCasseAvisiter[i]]==null){
                array[nombreCasseAvisiter[i]]=x;
            }else{
                int position= array[nombreCasseAvisiter[i]].compareTo(x);
                if(position>0){
                    if(min){
                      tmp=  array[nombreCasseAvisiter[i]];
                        array[nombreCasseAvisiter[i]]=x;
                        x=tmp;
                    }else{

                    }
                }else{
                    if(min){

                    }else{
                        tmp=  array[nombreCasseAvisiter[i]];
                        array[nombreCasseAvisiter[i]]=x;
                        x=tmp;
                    }
                }
            }
        }

        currentSize++;
	    return true;
    }
    private int[] caseAVisiter(){
        int position=size()+1;
        int grandeur=(int)(Math.log10(position)/Math.log10(2))+1;
        int nombreCasseAvisiter[] =new int[grandeur];

        for (;grandeur>0;grandeur--){
            nombreCasseAvisiter[grandeur-1]=position;
                position/=2;
        }
        return nombreCasseAvisiter ;
    }
    public AnyType peek(){
	    if(!isEmpty())
	    return array[1];
	
	    return null;
    }
    
    public AnyType poll(){
	    //COMPLETEZ
        if(peek()!=null)
            array[1]=null;
    	return null/**/;
    }
    
    public Iterator<AnyType> iterator(){
	    return new HeapIterator();
    }
    
    private void buildMinHeap(){
        for( int i = (currentSize / 2); i > 0; i-- )
            percolateDownMinHeap(i,currentSize);
    }
    
    private void buildMaxHeap(){
	    //COMPLETEZ
        for( int i = (currentSize / 2); i > 0; i-- )
            percolateDownMaxHeap(i,currentSize);
    }
    
    public boolean isEmpty(){
	    return currentSize == 0;
    }
    
    public int size(){
	    return currentSize;
    }
    
    public void clear(){
	    currentSize = 0;
	    modifications = 0;
	    array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1];
    }
    
    private static int leftChild( int i, boolean heapIndexing ){
	            return ( heapIndexing ? 2*i : 2*i+1 );
    }
    
    private void swapReferences( int index1, int index2 ){
	    swapReferences(array, index1, index2);
    }
    
    private static <AnyType extends Comparable<? super AnyType>>
				    void swapReferences( AnyType[] array, int index1, int index2 ){
	
    	AnyType tmp = array[ index1 ];
	    array[ index1 ] = array[ index2 ];
	    array[ index2 ] = tmp;
    }
    
    @SuppressWarnings("unchecked")
	private void doubleArray(){
	    AnyType [ ] newArray;
	
	    newArray = (AnyType []) new Comparable[ array.length * 2 ];
	for( int i = 0; i < array.length; i++ )
	    newArray[ i ] = array[ i ];
	    array = newArray;
    }
    
    
    /**
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     */
    private void percolateDownMinHeap( int hole, int size ){
	     percolateDownMinHeap(array, hole, size, true);
    }
    
    /**
     * @param array   Tableau d'element
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     * @param heapIndexing  True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
				    void percolateDownMinHeap( AnyType[] array, int hole, int size, boolean heapIndexing )
    {
	//COMPLETEZ
        int child;
        AnyType tmp;
        for( tmp = array[ hole ]; leftChild( hole,heapIndexing ) < size; hole = child ) {
            child = leftChild( hole ,heapIndexing);
            if( child != size - 1 && array[ child ].compareTo( array[ child + 1 ] ) >0 )
                child++;
            if( tmp.compareTo( array[ child ] ) > 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
    
    /**
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     */
    private void percolateDownMaxHeap( int hole, int size ){
	    percolateDownMaxHeap(array, hole, size, true);
    }
    
    /**
     * @param array         Tableau d'element
     * @param hole          Position a percoler
     * @param size          Indice max du tableau
     * @param heapIndexing  True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>> 
				    void percolateDownMaxHeap( AnyType[] array, int hole, int size, boolean heapIndexing )
    {
	//COMPLETEZ

        int child;
        AnyType tmp;
        for( tmp = array[ hole ]; leftChild( hole,heapIndexing ) < size; hole = child ) {
            child = leftChild( hole ,heapIndexing);
            if( child != size - 1 && array[ child ].compareTo( array[ child + 1 ] ) < 0 )
                child++;
            if( tmp.compareTo( array[ child ] ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }

    public static <AnyType extends Comparable<? super AnyType>>
				   void heapSort( AnyType[] a )
    {
	//COMPLETEZ
        for( int i = a.length / 2; i >= 0; i-- )
            percolateDownMaxHeap(a,i,a.length,false);
        for( int i = a.length - 1; i > 0; i-- )
        {
            swapReferences( a, 0, i );
            percolateDownMaxHeap(a,0,i,false);
        }

    }

    public static <AnyType extends Comparable<? super AnyType>>
				   void heapSortReverse( AnyType[] a )
    {
        for( int i = a.length / 2; i >= 0; i-- )
            percolateDownMinHeap(a,i,a.length,false);
        for( int i = a.length - 1; i > 0; i-- )
        {
            swapReferences( a, 0, i );
            percolateDownMinHeap(a,0,i,false);
        }

    }


    public String nonRecursivePrintFancyTree()
    {

        String outputString = "";
        String prefix = "";
        Pair<String, Integer>[] prefixWithIndex = new Pair[currentSize];
        int index=1;
        while (2*index<currentSize)
        {
            outputString += prefix + "|__";
            prefixWithIndex[index]= new Pair<>(prefix,index);
            outputString += array[index] + "\n";
            if (index % 2 == 0)
                prefix += "|  " ; // un | et trois espace
            else
                prefix += "   "; // quatre espaces

            index=2*index;
        }
        int level= index/2;
        int indexKid=2 * level;
        int indexBrother =level + 1;
        for (int i = 0; i < 2; i++)
            outputString += prefixWithIndex[level].getKey() + "|  |__" + array[indexKid + i] + "\n";

        outputString += prefixWithIndex[level].getKey() + "|__" + array[indexBrother] + "\n";

        for (int i = 2; i < 4; i++)
            outputString += prefixWithIndex[level].getKey() + "   |__" + array[indexKid + i] + "\n";

        while ((level/2)!=1) {
            level = level / 2;
            indexBrother = level + 1;
            outputString += prefixWithIndex[level].getKey() + "|__" + array[indexBrother] + "\n";
            outputString += prefixWithIndex[level].getKey() + "   |__" + array[2 * (level + 1)] + "\n";
            outputString += prefixWithIndex[level].getKey() + "   |  |__" + array[4 * (level + 1)] + "\n";
            outputString += prefixWithIndex[level].getKey() + "   |  |__" + array[4 * (level + 1) + 1] + "\n";
            outputString += prefixWithIndex[level].getKey() + "   |__" + array[2 * (level + 1) + 1] + "\n";
            outputString += prefixWithIndex[level].getKey() + "      |__" + array[2 * (2 * (level + 1) + 1)] + "\n";
            if(2 * (2 * (level + 1) + 1) + 1> currentSize) {
                outputString += prefixWithIndex[level].getKey() + "      |__" + "null" + "\n";
            }else outputString += prefixWithIndex[level].getKey() + "      |__" + array[2 * (2 * (level + 1) + 1) + 1] + "\n";
        }
        return outputString;
    }
    
    public String printFancyTree()
    {
	return printFancyTree(1, "");
    }

    private String printFancyTree( int index, String prefix)
    {

	String outputString = "";
	
	outputString = prefix + "|__";

	if( index <= currentSize )
	    {
		boolean isLeaf = index > currentSize/2;
		
		outputString += array[ index ] + "\n";
		
		String _prefix = prefix;
		
		if( index%2 == 0 )
		    _prefix += "|  "; // un | et trois espace
		else
		    _prefix += "   " ; // quatre espaces
		
		if( !isLeaf ) {
		    outputString += printFancyTree( 2*index, _prefix);
		    outputString += printFancyTree( 2*index + 1, _prefix);
		}
	    }
	else
	    outputString += "null\n";
	
	return outputString;
    }
    
    private class HeapIterator implements Iterator {
        private int curentPosition=1;
        private int curentModification=modifications;

    public boolean hasNext() {
        //COMPLETEZ
            if(curentPosition!=currentSize && curentPosition<currentSize){
                return true;
            } else return false;
    }

	public Object next() throws NoSuchElementException, 
				    ConcurrentModificationException, 
				    UnsupportedOperationException {
	    //COMPLETEZ
        if (!hasNext())
            throw new NoSuchElementException();
        if(modifications==curentModification) {
            return array[curentPosition++];
        } else throw new ConcurrentModificationException();

	}
	
	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }
}
