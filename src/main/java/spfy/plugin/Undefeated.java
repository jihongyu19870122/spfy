package spfy.plugin;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spfy.tool.HttpUntil;
import spfy.tool.RegexParse;

import java.net.URI;

public class Undefeated {


//    private Logger logger = LoggerFactory.getLogger(Goods.class);
//
//
//    /*****
//     *  step 1 login
//     *  setp 2 choose goods
//     *  setp 3 add
//     *  setp 4 check out;
//     * @throws Exception
//     */
//    private void excute() throws  Exception{
//            HttpUntil httpUntil= new HttpUntil();
//            Login login=new Login();
//            login.setHttpUntil(httpUntil);
//            HttpUriRequest loginParms = RequestBuilder.post()
//                .setUri(new URI("https://undefeated.com/account/login"))
//                .addParameter("form_type", "customer_login")
//                .addParameter("utf8", "✓")
//                .addParameter("customer[email]", "fbw692@163.com")
//                .addParameter("customer[password]", "123qwe@#$")
//                .setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
//                .build();
//            login.login(loginParms,"https://undefeated.com");
//            Goods goods=new Goods();
//            goods.setHttpUntil(httpUntil);
//            String goodsUrl=goods.choose("https://undefeated.com/collections/footwear","SUPERCOURT PREMIERE  -  RAWWHT / CWHITE / OWHITE","itemprop=\"name\">[\\s]*?<a href=\"([\\S]*?)\">([\\S\\s]*?)</a>");
//            Id id=new Id();
//            id.setHttpUntil(httpUntil);
//            String myId=id.getId(goodsUrl,"variants\":\\[\\{\"id\":([\\d]*?)\\,");
//            Add add=new Add();
//            add.setHttpUntil(httpUntil);
//            HttpUriRequest addUrl = RequestBuilder.post()
//                .setUri(new URI("https://undefeated.com/cart/add.js"))
//                .addParameter("id", myId)
//                .addParameter("quantity", "1")
//                .setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
//                .build();
//            String addResult=add.add(addUrl);
//            httpUntil.get("https://undefeated.com/checkout");
//            httpUntil.printResult();
//            String pageUrl=RegexParse.baseParse(httpUntil.getResult(),"pageurl\":\"([\\S]*?)/stock_problems",1);
//            pageUrl= httpUntil.urlResolve("https://undefeated.com/",pageUrl.replace("\\",""));
//            System.out.println(pageUrl);
//            logger.info(pageUrl+">>>>>>>>>>>>>>>>>>>>>>>");
//    }
//
//    public static void main(String[] args)throws  Exception {
//        Undefeated undefeated=new Undefeated();
//        undefeated.excute();
//    }
}
