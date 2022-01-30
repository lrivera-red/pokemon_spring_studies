package usr.lrivera.pokemonestudo.controller.services;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class WebClientService {
    private final static String url = "https://webhook.site/93954357-3564-4c4e-ad8a-6dda2282a08c";
    private  final HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected(conn ->
                    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
    public WebClient webClientBuilder(TraceService traceService) {
        return  WebClient.
                builder().
                baseUrl(url).
                defaultHeader("X-Trace-Id", traceService.getTraceId()).
                clientConnector(new ReactorClientHttpConnector(httpClient)).
                build();
    }
}