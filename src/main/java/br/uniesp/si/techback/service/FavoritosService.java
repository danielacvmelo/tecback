package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Favoritos;
import br.uniesp.si.techback.repository.FavoritosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritosService {

    private final FavoritosRepository favoritosRepository;

    public List<Favoritos> listar() {
        return favoritosRepository.findAll();
    }

    public Favoritos buscarPorId(Long id) {
        return favoritosRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Favorito não encontrado com o ID: " + id));
    }

    @Transactional
    public Favoritos salvar(Favoritos favoritos) {
        return favoritosRepository.save(favoritos);
    }

    @Transactional
    public Favoritos atualizar(Long id, Favoritos favoritos) {
        if (!favoritosRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Favorito não encontrado com o ID: " + id);
        }
        favoritos.setId(id);
        return favoritosRepository.save(favoritos);
    }

    @Transactional
    public void excluir(Long id) {
        if (!favoritosRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Favorito não encontrado com o ID: " + id);
        }
        favoritosRepository.deleteById(id);
    }
}