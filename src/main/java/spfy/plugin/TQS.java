package spfy.plugin;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spfy.tool.HttpUntil;
import spfy.tool.RegexParse;
import java.net.URI;
import java.net.URISyntaxException;

public class TQS {



    private Logger logger = LoggerFactory.getLogger(TQS.class);


    private void excute() throws  Exception{
        HttpUntil httpUntil= new HttpUntil();
        addCart(httpUntil);
       // checkChart(httpUntil);
        cartCheckOut(httpUntil);
        addrCheckOut(httpUntil);
        logger.info(">>>>>>>>>>>>>>111>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        shippingMethod(httpUntil);
        logger.info(">>>>>>>>>>>>>>222>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        contactInformation(httpUntil);
        logger.info(">>>>>>>>>>>>>>333>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        paymentMethod(httpUntil);
        logger.info(">>>>>>>>>>>>>>444>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        paymentMethodGet(httpUntil);
        logger.info(">>>>>>>>>>>>>>444>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        pay(httpUntil);
        logger.info(">>>>>>>>>>>>>>666>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private void addCart(HttpUntil httpUntil) throws URISyntaxException {
        HttpUriRequest httpUriRequest = RequestBuilder.post()
               // .setUri(new URI("https://undefeated.com/cart/add"))
                .setUri(new URI("https://tianqingse.myshopify.com/cart/add"))
                .addParameter("form_type","product")
                .addParameter("utf8","✓")
                //.addParameter("id","18343416627273")
                .addParameter("id","14367348654146")
                .addParameter("add","")
                .setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                .build();
        httpUntil = httpUntil.post(httpUriRequest);
        httpUntil.printResult();
    }

    private void  checkChart (HttpUntil httpUntil){
        httpUntil= httpUntil.get("https://tianqingse.myshopify.com/cart");
        httpUntil.printResult();
    }


    private  void cartCheckOut(HttpUntil httpUntil) throws URISyntaxException {
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(new URI("https://tianqingse.myshopify.com/cart"))
                .addParameter("updates[]","1")
                .addParameter("checkout","Check out")
                .setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                .build();
        httpUntil = httpUntil.post(httpUriRequest);
        httpUntil.printResult();
    }

    private void contactInformation(HttpUntil httpUntil){
        httpUntil= httpUntil.get(httpUntil.getTemp().get("checkOutURL"));
        httpUntil.getTemp().put("authenticity_token", RegexParse.baseParse(httpUntil.getResult(),"authenticity_token\"\\s*?value=\"([\\S]*?)\"",1));
        httpUntil.printResult();
    }

    private  void  shippingMethod(HttpUntil httpUntil) throws URISyntaxException {
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(new URI(httpUntil.getTemp().get("checkOutURL")))
                .addParameter("utf8","✓")
                .addParameter("_method","patch")
                .addParameter("authenticity_token",httpUntil.getTemp().get("authenticity_token"))
                .addParameter("previous_step","contact_information")
                .addParameter("step","shipping_method")
                .addParameter("checkout[email_or_phone]","+8618410942514")
                .addParameter("checkout[buyer_accepts_marketing]","0")
                .addParameter("checkout[shipping_address][first_name]","")
                .addParameter("checkout[shipping_address][last_name]","")
                .addParameter("checkout[shipping_address][address1]","")
                .addParameter("checkout[shipping_address][address2]","")
                .addParameter("checkout[shipping_address][city]","")
                .addParameter("checkout[shipping_address][country]","")
                .addParameter("checkout[shipping_address][province]","")
                .addParameter("checkout[shipping_address][zip]","")
                .addParameter("checkout[shipping_address][first_name]","ye")
                .addParameter("checkout[shipping_address][last_name]","tes")
                .addParameter("checkout[shipping_address][address1]","北京市海淀区东大街")
                .addParameter("checkout[shipping_address][address2]","28号")
                .addParameter("checkout[shipping_address][city]","北京")
                .addParameter("checkout[shipping_address][country]","China")
                .addParameter("checkout[shipping_address][province]","BJ")
                .addParameter("checkout[shipping_address][zip]","100000")
                .addParameter("checkout[remember_me]","true")
                .addParameter("checkout[remember_me]","0")
                .addParameter("button","")
                .addParameter("checkout[client_details][browser_width]","414")
                .addParameter("checkout[client_details][browser_height]","736")
                .addParameter("checkout[client_details][javascript_enabled]","1")
                .setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                .build();
        httpUntil = httpUntil.post(httpUriRequest);
        String url="";
        for(Header header:httpUntil.getHeaders())
        {
            if("Location".equals(header.getName())){
                url=header.getValue();
                httpUntil.getTemp().put("checkOutURL",url);//
                break ;
            }
        }
        httpUntil.printResult();
    }


    private void addrCheckOut(HttpUntil httpUntil){
        String url="";
        for(Header header:httpUntil.getHeaders())
        {
            if("Location".equals(header.getName())){
                url=header.getValue();
                httpUntil.getTemp().put("checkOutURL",url);//
                httpUntil.getTemp().put("authenticity_token", RegexParse.baseParse(httpUntil.getResult(),"authenticity_token\"\\s*?value=\"([\\S]*?)\"",1));
                break ;
            }
        }
        httpUntil.get(url);
        httpUntil.printResult();
    }


    private void paymentMethod(HttpUntil httpUntil) throws URISyntaxException {
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(new URI(httpUntil.getTemp().get("checkOutURL").substring(0,httpUntil.getTemp().get("checkOutURL").indexOf("?"))))
                .addParameter("utf8","✓")
                .addParameter("_method","patch")
                .addParameter("authenticity_token",httpUntil.getTemp().get("authenticity_token"))
                .addParameter("previous_step","shipping_method")
                .addParameter("step","payment_method")
                .addParameter("checkout[shipping_rate][id]","shopify-Standard%20Shipping-0.00")
                .addParameter("button","")
                .addParameter("checkout[client_details][browser_width]","414")
                .addParameter("checkout[client_details][browser_height]","736")
                .addParameter("checkout[client_details][javascript_enabled]","1")
                .setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                .build();
        httpUntil = httpUntil.post(httpUriRequest);
        String url="";
        for(Header header:httpUntil.getHeaders())
        {
            if("Location".equals(header.getName())){
                url=header.getValue();
                httpUntil.getTemp().put("payment_methodURL",url);//
                break ;
            }
        }
        httpUntil.printResult();
    }

    private void  pay(HttpUntil httpUntil) throws URISyntaxException {
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(new URI("https://elb.deposit.shopifycs.com/sessions"))
                 .setEntity(new StringEntity("{\"credit_card\":{\"number\":\"1\",\"name\":\"Bogus Gateway\",\"month\":8,\"year\":2023,\"verification_value\":\"068\"}}","UTF-8"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                .build();
        httpUntil = httpUntil.post(httpUriRequest);
        httpUntil.printResult();



        httpUriRequest = RequestBuilder.post()
                .setUri(new URI(httpUntil.getTemp().get("checkOutURL").substring(0,httpUntil.getTemp().get("checkOutURL").indexOf("?"))))
                 .addParameter("utf8","✓")
                .addParameter("_method","patch")
                .addParameter("authenticity_token",httpUntil.getTemp().get("authenticity_token"))
                .addParameter("previous_step","payment_method")
                .addParameter("step","")
                .addParameter("s",RegexParse.baseParse(httpUntil.getResult(),"id\":\"([\\S]*?)\"",1))
                .addParameter("checkout[payment_gateway]","19499352130")
                .addParameter("checkout[credit_card][vault]","false")
                .addParameter("checkout[different_billing_address]","false")
                .addParameter("checkout[total_price]","1")
                .addParameter("complete","1")
                .addParameter("checkout[client_details][browser_width]","414")
                .addParameter("checkout[client_details][browser_height]","736")
                .addParameter("checkout[client_details][javascript_enabled]","1")
                .setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                .build();
        httpUntil = httpUntil.post(httpUriRequest);
        httpUntil.printResult();


    }
    private void paymentMethodGet(HttpUntil httpUntil){
        httpUntil.get(httpUntil.getTemp().get("payment_methodURL"));
        httpUntil.getTemp().put("authenticity_token", RegexParse.baseParse(httpUntil.getResult(),"authenticity_token\"\\s*?value=\"([\\S]*?)\"",1));
        httpUntil.printResult();
    }

    public static void main(String[] args)throws  Exception {
        TQS tqs=new TQS();
        tqs.excute();
    }
}
