package tp2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Interview {
    /**
     * Finds all pairs within values which sum up to targetSum
     * @param values All possible values that can form a pair (can contain duplicates)
     * @param targetSum Pairs should add up to this
     * @return A collection containing all valid pairs with no permutations, but all combinations (empty collection if none found)
     */

    public Collection<MatchingPair> matchingPairs(Collection<Integer> values, Integer targetSum){
        Integer pivot =values.iterator().next();
        Set<MatchingPair> MatchingPairs= new HashSet<MatchingPair>() ;
        for (Integer value : values) {
                if(value<pivot){

                }
        }


        return MatchingPairs;

    }


}

