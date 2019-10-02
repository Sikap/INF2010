package tp2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Interview {
    /**
     * Finds all pairs within values which sum up to targetSum
     * @param values All possible values that can form a pair (can contain duplicates)
     * @param targetSum Pairs should add up to this
     * @return A collection containing all valid pairs with no permutations, but all combinations (empty collection if none found)
     */

    public Collection<MatchingPair> matchingPairs(Collection<Integer> values, Integer targetSum){
        Collection<MatchingPair> MatchingPairs= new HashSet<MatchingPair>() ;
        if(values.size()==0) {
            return MatchingPairs;
        }
        int i=0;
        int sizeFile=values.size();
        Collection<Integer> cfile1=new HashSet<>();
        Collection<Integer> cfile2=new HashSet<>();
        Integer[] file2 =new Integer[sizeFile+1] ;
        MatchingPair temp,actuel;
        Integer pivot,value1,value2;
        Iterator itFile1,itFile2;
        temp=actuel=null;
        pivot=targetSum;
        Integer Dernier;

        for (Integer value:values) {
            if(value<=pivot)
            {
                Dernier=value;
                cfile1.add(value);
            }
            if(value>=pivot)
            {
                file2[i++] = value;
            }
        }
        sizeFile=cfile1.size();
       /* for(i=sizeFile-1;i>=0;i--){
            if(file2[i]!=null)
            cfile2.add(file2[i]);
        }*/

       /* if(cfile1.size()!=cfile2.size())
            return MatchingPairs;*/

        itFile1=cfile1.iterator();
        //itFile2=cfile2.iterator();

        while (itFile1.hasNext()){
            value1=(Integer) itFile1.next();
            sizeFile--;
            if(file2[sizeFile]!=null)
            value2=file2[sizeFile];
            else value2=0;
            if(value1+value2>=targetSum){
                if (value1!=value2){
                    actuel=new MatchingPair(value1,value2);
                    if(!actuel.equals(temp))
                        MatchingPairs.add(actuel);
                }
            }
            temp=actuel;
        }
        return MatchingPairs;
    }


}

