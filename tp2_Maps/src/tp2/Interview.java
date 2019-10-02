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
       // Collection<Integer> cfile1=new HashSet<>();        Iterator itFile1,itFile2;
        //Collection<Integer> cfile2=new HashSet<>();
        Integer[] file1 =new Integer[sizeFile+1] ;
        Integer[] file2 =new Integer[sizeFile+1] ;
        MatchingPair actuel;
        Integer value1,value2;
        i=y=0;

        for (Integer value:values) {
            if(value>=0)
            {
                file1[i]=value;
                i++;
            }
            else{
                file2[y]=value;y++;
            }


        }

       /* for(i=sizeFile-1;i>=0;i--){
            if(file2[i]!=null)
            cfile2.add(file2[i]);
        }*/

       /* if(cfile1.size()!=cfile2.size())
            return MatchingPairs;*/

        //itFile1=cfile1.iterator();
        //itFile2=cfile2.iterator();
        i=0;
        y=0;
        while(i<sizeFile){
            if(file1[i]==null)break;
            value1=file1[i];
            if (file2[y]!=null) {
                value2=file2[y];
            }else if(file1[y]!=null) value2=file1[y];else{value2=0;}

            if(value2+value1<=targetSum)
                if(y==sizeFile-1){y=0;i++;}else y++;
            if(value2+value1>targetSum)
            {
                i++;
                y=0;
            }
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

