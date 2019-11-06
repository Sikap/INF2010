import java.util.*; 


public class Main 
{
   /**
     * Fonction principale
     */
   public static void main(String[] args) 
   {
      // Creer un monceau avec 22 éléments et un tableau équivalent
      int numItems = 22;
      BinaryHeap<Integer> heap = new BinaryHeap<Integer>(true);
      
      Integer [ ] items = new Integer[ numItems ];

      int i;
      int j;

      // En insérant les éléments un a un
      for( i = 11, j = 0; j != numItems; i = ( i + 37 ), j++ )
      {
	  heap.offer( i );
	  items[ j ] = i;

	  i %=  numItems; 
      }

      // en construisant le monceau depuis le depart
      System.out.println("Monceau min contruit element par element:");
      System.out.println( heap.printFancyTree() );

      heap = new BinaryHeap<Integer>(false);
      // en inserant les elements un a un
      for( Integer item : items)
	  heap.offer( item );

      // en construisant le monceau depuis le depart
      System.out.println("Monceau max contruit element par element:");
      System.out.println( heap.printFancyTree() );

      heap = new BinaryHeap<Integer>(items,false);
      System.out.println("Monceau max contruit a partir d'un tableau:");
      System.out.println( heap.printFancyTree() );

      heap = new BinaryHeap<Integer>(items,true);
      System.out.println("Monceau min contruit a partir d'un tableau:");
      System.out.println( heap.printFancyTree() );

      System.out.println();
      System.out.println("Affichage recursif:");
      System.out.println( heap.printFancyTree() );

      System.out.println("Affichage non recursif:");
      System.out.println( heap.nonRecursivePrintFancyTree() );

      System.out.println();
      System.out.println("Tableau d'origine:");
      System.out.println( printArray( items ) );
      
      BinaryHeap.heapSort( items );
      System.out.println("Tableau ordonne:");
      System.out.println( printArray( items ) );

      BinaryHeap.heapSortReverse( items );
      System.out.println("Tableau inversement ordonne:");
      System.out.println( printArray( items ) );


      /*
       * Ajouter appels pour repondre a la question
       **/
       heap = new BinaryHeap<Integer>(items,false);
       System.out.println("Tableau des donne de itérateur :");
       String donner="";
       Iterator testIterator =heap.iterator();
       while (testIterator.hasNext()){
            donner+=testIterator.next();
            donner+=", ";
       }
       System.out.println(donner);
       heap = new BinaryHeap<Integer>(items,false);
       System.out.println("Test de modification:");
       donner="";
       testIterator =heap.iterator();
       heap.offer(5);
       try {
           while (testIterator.hasNext()){
               donner+=testIterator.next();
               donner+=", ";
           }
           System.out.println("reussis");
       }catch (ConcurrentModificationException a){
           System.out.println("a jout avec modification donc arraite ");
       }
       heap.offer(100);
       try {
           while (testIterator.hasNext()){
               donner+=testIterator.next();
               donner+=", ";
           }
           System.out.println();
       }catch (ConcurrentModificationException a){
           System.out.println("a jout avec modification donc arraite ");
       }
       System.out.println("test iterator remove");
       try {
           testIterator.remove();
       }catch (UnsupportedOperationException a){
           System.out.println("remove not a soupported operation ");
       }


       BinaryHeap test = new BinaryHeap<Integer>(items,false);
       System.out.println("Tableau poll test :");
       while (test.size()>0){
           Object a = test.poll();
           System.out.print(a);
           if(test.size()>0)
               System.out.print(", ");
       }
   }

   private static <AnyType> String printArray(AnyType[] a)
   {
      String outputString = "";

      for(AnyType item : a)
      {
         outputString += item;
         outputString += ", ";
      }

      return outputString.substring(0,outputString.length()-2);
   }
}
