package insper.store.partida;

public class PartidaParser {

    public static Partida to(PartidaIn in) {
        return Partida.builder()
            .time1(in.time1())
            .time2(in.time2())
            .id_jogador(in.id_jogador())
            .build();
    }

    public static PartidaOut to(Partida Partida) {
        return PartidaOut.builder()
            .id(Partida.id())
            .time1(Partida.time1())
            .time2(Partida.time2())
            .id_jogador(Partida.id_jogador())
            .build();
    }
    
}
