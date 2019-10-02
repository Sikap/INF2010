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
        Integer[] Values = values.toArray(new Integer[values.size()]);
        LinkedHashMap Map = new LinkedHashMap(values.size());

        Set<MatchingPair> MatchingPairs= new HashSet<MatchingPair>() ;

        for (int i=0;i<Values.length;i++)
        {
            Map.put(i,Values[i]);

        }
        for (int i=0;i<Map.size();i++)
        {

        }

        return MatchingPairs;

    }


}

