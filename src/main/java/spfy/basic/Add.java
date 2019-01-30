package spfy.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spfy.tool.HttpUntil;
import spfy.tool.RegexParse;

public class Add {


    private Logger logger = LoggerFactory.getLogger(Goods.class);


    private HttpUntil httpUntil;


    private static final String id="variants\":[{\"id\":([\\S]*?),\"";

    public void setHttpUntil(HttpUntil httpUntil) {
        this.httpUntil = httpUntil;
    }


    public  void add(String url,String regex){
        httpUntil = httpUntil.get(url);
        match(httpUntil.getResult(),regex);
    }

    /***
     * add
     */
    private String match(String content,String regex) {
        return RegexParse.baseParse(content,regex,1);
    }
}
