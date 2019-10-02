package tp2;

import sun.awt.SunHints;

import java.lang.reflect.Array;
import java.util.*;

public class Interview {
    /**
     * Finds all pairs within values which sum up to targetSum
     * @param values All possible values that can form a pair (can contain duplicates)
     * @param targetSum Pairs should add up to this
     * @return A collection containing all valid pairs with no permutations, but all combinations (empty collection if none found)
     */

    public Collection<MatchingPair> matchingPairs(Collection<Integer> values, Integer targetSum){
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        Collection<MatchingPair> Pairs= new ArrayList<>();
        Integer[] Values = values.toArray(new Integer[values.size()]);

        for(int i=0;i<Values.length;i++)//Mettre les Values dans la map
        {

            if(map.get(Values[i])==null)
            {
                map.put(Values[i], i);
            }

        }
        for(int i=0;i<Values.length;i++)
        {
           if(map.get(targetSum-Values[i])!=null && map.get(targetSum-Values[i])!=i)
            {
                if(!Pairs.contains(new MatchingPair(targetSum - Values[i],Values[i])))
                {
                    Pairs.add(new MatchingPair(Values[i], targetSum - Values[i]));
                }
            }

        }


        return Pairs;
    }


}

