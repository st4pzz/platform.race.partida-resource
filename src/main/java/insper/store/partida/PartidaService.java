package insper.store.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.NonNull;
@Service
public class PartidaService {

    @Autowired
    private PartidaRepository PartidaRepository;

    
    

    public Iterable<PartidaModel> readAll() {
        return PartidaRepository.findAll();
    }

    public Partida create(Partida in) {
        return PartidaRepository.save(new PartidaModel(in)).to();
    }

    public Partida read(@NonNull String id) {
        return PartidaRepository.findById(id).map(PartidaModel::to).orElse(null);
    }
    
    

    
    
}
