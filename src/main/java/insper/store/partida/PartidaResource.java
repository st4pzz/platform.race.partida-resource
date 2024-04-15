package insper.store.partida;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Partida", description = "Partida API")
public class PartidaResource implements PartidaController {

    

    @Autowired
    private PartidaService PartidaService;

    @GetMapping("/partidas/info")
    @Tag(name = "Info", description = "Partida API Info")
    public ResponseEntity<Map<String, String>> info() {
        return new ResponseEntity<Map<String, String>>(
                Map.ofEntries(
                        Map.entry("microservice.name", PartidaApplication.class.getSimpleName()),
                        Map.entry("os.arch", System.getProperty("os.arch")),
                        Map.entry("os.name", System.getProperty("os.name")),
                        Map.entry("os.version", System.getProperty("os.version")),
                        Map.entry("file.separator", System.getProperty("file.separator")),
                        Map.entry("java.class.path", System.getProperty("java.class.path")),
                        Map.entry("java.home", System.getProperty("java.home")),
                        Map.entry("java.vendor", System.getProperty("java.vendor")),
                        Map.entry("java.vendor.url", System.getProperty("java.vendor.url")),
                        Map.entry("java.version", System.getProperty("java.version")),
                        Map.entry("line.separator", System.getProperty("line.separator")),
                        Map.entry("path.separator", System.getProperty("path.separator")),
                        Map.entry("user.dir", System.getProperty("user.dir")),
                        Map.entry("user.home", System.getProperty("user.home")),
                        Map.entry("user.name", System.getProperty("user.name")),
                        Map.entry("jar", new java.io.File(
                                PartidaApplication.class.getProtectionDomain()
                                        .getCodeSource()
                                        .getLocation()
                                        .getPath())
                                .toString())),
                HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Create Partida", description = "Create Partida")
    public ResponseEntity<PartidaOut> create(PartidaIn in) {
        // parser
        Partida partida = PartidaParser.to(in);
        // insert using service
        partida = PartidaService.create(partida);
        // return
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(partida.id())
                        .toUri())
                .body(PartidaParser.to(partida));
    }

    @Override
    @Operation(summary = "Update", description = "Update Partida")
    public ResponseEntity<PartidaOut> update(String id, PartidaIn in) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

   
    @Override
    @Operation(summary = "Read", description = "Read Partida")
    public ResponseEntity<PartidaOut> read(String idPartida) {
        Partida partida = PartidaService.read(idPartida);

        if (partida == null) {
            return ResponseEntity.notFound().build();
        }

        PartidaOut partidaOut = PartidaParser.to(partida);
        return ResponseEntity.ok(partidaOut);
    }

}
