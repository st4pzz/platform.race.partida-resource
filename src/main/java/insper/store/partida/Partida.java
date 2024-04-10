package insper.store.partida;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Builder
@Getter @Setter @Accessors(fluent = true, chain = true)
@AllArgsConstructor @NoArgsConstructor
public class Partida {

    private String id;
    private String time1;
    private String time2;
    private String data;
    private String local;
    private String campeonato;

}
