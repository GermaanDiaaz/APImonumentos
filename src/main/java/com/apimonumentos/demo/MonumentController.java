package com.apimonumentos.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MonumentController {

    private final MonumentRepository monumentRepository;


    @GetMapping("/Monument")
    public ResponseEntity<List<Monument>> getAllMonuments(){
        List<Monument> result = monumentRepository.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/Monument/{id}")
    public ResponseEntity<Monument> getMonumentById(@PathVariable Long id){
        Optional<Monument> result = monumentRepository.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }


    @PostMapping
    public ResponseEntity<Monument> addMonument(@RequestBody Monument monument){
        if(StringUtils.hasText(monument.getNombreMonumento())){

            return ResponseEntity.status(201).body(monumentRepository.save(monument));
        }
        return ResponseEntity.badRequest().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Monument> updateMonument(
            @PathVariable Long id,
            @RequestBody Monument monument){

        return monumentRepository.findById(id)
                .map(m -> {
                    m.setCiudad(monument.getCiudad());
                    m.setDescripcion(monument.getDescripcion());
                    m.setLocalizacion(monument.getLocalizacion());
                    m.setNombreMonumento(monument.getNombreMonumento());
                    m.setNombrePais(monument.getNombrePais());
                    m.setUrl(monument.getUrl());

                    return ResponseEntity.ok(monumentRepository.save(m));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonument(@PathVariable Long id){
        monumentRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
