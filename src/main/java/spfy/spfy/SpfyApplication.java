package spfy.spfy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/****
 * @author jhy
 *
 * 不知道为什么不自定义扫描包，总是说找不到类；暂时先这样吧；
 */

@SpringBootApplication
@ComponentScans(value ={@ComponentScan(value = "spfy.basic"), @ComponentScan(value = "spfy.spfy")})
public class SpfyApplication implements CommandLineRunner {

    @Autowired
    private Undefeated undefeated;

    @Autowired
    private Kith kith;



    public static void main(String[] args) {
        SpringApplication.run(SpfyApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //undefeated.execute();
        kith.excute();
    }
}

