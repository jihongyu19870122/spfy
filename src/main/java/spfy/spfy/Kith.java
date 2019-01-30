package spfy.spfy;

import org.springframework.stereotype.Component;
import spfy.tool.HttpUntil;

/****
 * @author jhy
 */

@Component
public class Kith {

    private HttpUntil httpUntil=new HttpUntil();

    public void excute(){
        test();
    }

    private void test(){
        httpUntil=httpUntil.get("https://kith.com/cart.js");
        httpUntil.printResult();
    }
}


