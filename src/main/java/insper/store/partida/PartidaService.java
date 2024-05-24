package insper.store.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.NonNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository PartidaRepository;

    
    
    @CachePut(value = "partidas", key = "#result.id")
    public Iterable<PartidaModel> readAll() {
        return PartidaRepository.findAll();
    }

    @CachePut(value = "partidas", key = "#result.id")
    public Partida create(Partida in) {
        return PartidaRepository.save(new PartidaModel(in)).to();
    }

    @Cacheable(value = "partidas", key = "#id")
    public Partida read(@NonNull String id) {
        return PartidaRepository.findById(id).map(PartidaModel::to).orElse(null);
    }
    
    

    
    
}
