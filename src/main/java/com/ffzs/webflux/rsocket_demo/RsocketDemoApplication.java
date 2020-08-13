package com.ffzs.webflux.rsocket_demo;

import io.netty.util.internal.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class RsocketDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsocketDemoApplication.class, args);
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Temperature {
    Long id;
    Double temperature;
    LocalDateTime time;

    public Temperature (long id) {
        this.id = id;
        this.temperature = ThreadLocalRandom.current().nextDouble(20.0,35.0);
        this.time = LocalDateTime.now();
    }
}

@Controller
class TemperatureController {

    @MessageMapping("server")
    public Flux<Temperature> temperature () {
        return Flux.interval(Duration.ofSeconds(1))
                .map(Temperature::new);
    }
}
