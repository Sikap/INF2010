package tp2;

import java.util.ArrayList;
import java.util.Collection;

public class Interview {
    /**
     * Finds all pairs within values which sum up to targetSum
     * @param values All possible values that can form a pair (can contain duplicates)
     * @param targetSum Pairs should add up to this
     * @return A collection containing all valid pairs with no permutations, but all combinations (empty collection if none found)
     */

    public Collection<MatchingPair> matchingPairs(Collection<Integer> values, Integer targetSum){
        Collection<MatchingPair> MatchingPairs= new ArrayList<>() ;
        if(values.size()==0) {
            return MatchingPairs;
        }
        int i,y;
        int sizeFile=values.size();
        Integer[] file1 =new Integer[sizeFile+1] ;
        Integer value1,value2;
        i=0;
        for (Integer value:values) {
            file1[i]=value;
            i++;
        }
        i=0;
        y=0;
        while(i<sizeFile){
            value1=file1[i];
            value2=file1[y];
            if(y==sizeFile-1){y=0;i++;}else y++;
            if(value1>value2){
                if((value1+value2)==targetSum){
                    if (value1!=value2){
                        MatchingPairs.add(new MatchingPair(value2,value1));
                    }
                }
            }
        }
        return MatchingPairs;
    }


}

