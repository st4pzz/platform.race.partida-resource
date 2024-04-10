package insper.store.partida;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "partida")
@EqualsAndHashCode(of = "id")
@Builder @Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class PartidaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_partida")
    private String id;

    @Column(name = "time1")
    private String time1;

    @Column(name = "time2")
    private String time2;


    @Column(name = "id_jogador")
    private String id_jogador;
    

    public PartidaModel(Partida o) {
        this.id = o.id();
        this.time1 = o.time1();
        this.time2 = o.time2();
        this.id_jogador = o.id_jogador();
    }
    
    public Partida to() {
        return Partida.builder()
            .id(id)
            .time1(time1)
            .time2(time2)
            .id_jogador(id_jogador)
            .build();
    }
    
}
