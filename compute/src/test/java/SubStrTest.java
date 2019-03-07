import org.junit.Test;

/**
 * @description: ${description}
 * @author: He Kun
 * @create: 2019-03-06 15:11
 **/
public class SubStrTest {

    @Test
    public void subStringtest(){

int[] ars = {1,-2,2,5,-6,2,9};

System.out.println(getSubString(ars));
    }

    public static int getSubString(int[] ars){
            int maxsub=0;
            int thissum = 0;
            for(int i =0; i< ars.length; i++){
                thissum += ars[i];
                if(thissum> maxsub){
                    maxsub = thissum;
                }else if(thissum <0){
                    thissum =0;
                }
            }
            return  maxsub;
    }

}
