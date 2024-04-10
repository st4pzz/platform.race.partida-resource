package insper.store.partida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = { "insper.store.account", "insper.store.jogador" })
@EnableDiscoveryClient
@SpringBootApplication
public class PartidaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartidaApplication.class, args);
    }

}
