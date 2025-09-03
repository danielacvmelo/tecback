package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Filme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/genero")
public class GeneroController {

    private List<Genero> lista = new ArrayList<>();

    private long contador = 1;

    @PostMapping
    public Genero incluir(Genero genero){
        genero.setId(contador);
        ++contador;
        lista.add(genero);
        return genero;

    }

    @GetMapping
    public List<Genero> listar(){
        return genero;
    }

}