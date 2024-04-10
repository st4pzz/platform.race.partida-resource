package insper.store.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import insper.store.account.AccountController;
import insper.store.account.AccountOut;
import insper.store.jogador.*;
import lombok.NonNull;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository PartidaRepository;

    
    @Autowired
    private JogadorController jogadorController;
    
    @Autowired
    private AccountController accountController;
    
    
    
    public Partida create(Partida in) {

        ResponseEntity<JogadorOut> response = jogadorController.read(in.id_jogador());

        if (response.getStatusCode().isError()) throw new IllegalArgumentException("Invalid jogador");

        ResponseEntity<AccountOut> response2 = accountController.read(in.id_jogador());

        if (response2.getStatusCode().isError()) throw new IllegalArgumentException("Invalid jogador");
        
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println(response2.getBody());
        System.out.println(response.getBody());
        System.out.println("------------------------------------------------------------------------------------");

        
        return PartidaRepository.save(new PartidaModel(in)).to();
        
    }

    public Partida read(@NonNull String id) {
        return PartidaRepository.findById(id).map(PartidaModel::to).orElse(null);
    }

    
    
}
