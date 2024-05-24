package insper.store.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.NonNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @CircuitBreaker(name = "partidaService", fallbackMethod = "fallbackReadAll")
    @CachePut(value = "partidas", key = "#result.id")
    public Iterable<PartidaModel> readAll() {
        return partidaRepository.findAll();
    }

    @CircuitBreaker(name = "partidaService", fallbackMethod = "fallbackCreate")
    @CachePut(value = "partidas", key = "#result.id")
    public Partida create(Partida in) {
        return partidaRepository.save(new PartidaModel(in)).to();
    }

    public Partida fallbackCreate(Partida in, Throwable t) {
        return new Partida();
    }

    @CircuitBreaker(name = "partidaService", fallbackMethod = "fallbackRead")
    @Cacheable(value = "partidas", key = "#id")
    public Partida read(@NonNull String id) {
        return partidaRepository.findById(id).map(PartidaModel::to).orElse(null);
    }

    public Partida fallbackRead(String id, Throwable t) {
        return new Partida();
    }
}
