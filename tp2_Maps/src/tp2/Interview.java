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
        Integer[] file2 =new Integer[sizeFile] ;
        MatchingPair temp,actuel;
        Integer pivot,value1,value2;
        Iterator itFile1
        temp=actuel=null;
        pivot=targetSum/2;


        for (Integer value:values) {
            if(value<pivot)
            {
                cfile1.add(value);
            if(value>pivot)
            {
                file2[i++] = value;
            }
        }
        for(i=sizeFile-1;i>=0;i--){
            if(file2[i]!=null)
            cfile2.add(file2[i]);
        }

        while (i<sizeFile){
            value1=file1[i];
            i++;
            value2=file2[sizeFile-i];
            if(value1+value2==targetSum){
                actuel=new MatchingPair(value1,value2);
                if(temp!=null)
                    if(!temp.equals(actuel))
                        MatchingPairs.add(actuel);
            }
            temp=actuel;
        }
        return MatchingPairs;
    }


}

