package spfy.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import spfy.tool.HttpUntil;
import spfy.tool.RegexParse;
import spfy.tool.Similarity;

/***
 * @author jhy
 *
 * 目前观测基本上所有shopify的类别都是连接后面跟随collections/，但是这些是什么就不固定了；
 * 所以暂时想用这个collections为起点，至于更前面是如何逻辑暂时不管;
 * 基本逻辑
 *       根据传入的连接，http，去请求collections页面，然后根据传入的名字，模糊的匹配一个产品；然后把连接返回
 */
@Component
public class Goods {

    private Logger logger = LoggerFactory.getLogger(Goods.class);


    private HttpUntil httpUntil;


    public void setHttpUntil(HttpUntil httpUntil) {
        this.httpUntil = httpUntil;
    }


    /****
     * 选择商品
     * @param url
     */
    private String choose(String url,String name,String regex) {
        httpUntil = httpUntil.get(url);
        return httpUntil.urlResolve(url,match(httpUntil.getResult(),name,regex));
    }

    /***
     * 根据名字匹配对应的商品
     */
    private String match(String content,String name,String regex) {
        //这个规则是先匹配大块，就是包含了连接&名字
        int index=Similarity.index(RegexParse.baseParseList(content,regex,2),name);
        return RegexParse.baseParseList(content,regex,1).get(index);
    }

    public static void main(String[] args) {
        Goods goods=new Goods();
        goods.setHttpUntil(new HttpUntil());
        System.out.println("=================href:"+goods.choose("https://undefeated.com/collections/footwear"," AIR MAX 270 ISPA  -  BLUEVOID / BLACK / TERRAORANGE / OATMEAL  -   -  / 8","itemprop=\"name\">[\\s]*?<a href=\"([\\S]*?)\">([\\S\\s]*?)</a>"));

    }

}
