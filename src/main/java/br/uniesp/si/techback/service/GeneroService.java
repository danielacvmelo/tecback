package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Genero;
import br.uniesp.si.techback.repository.GeneroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    public List<Genero> listar() {
        return generoRepository.findAll();
    }

    /**
     * Busca um gênero por ID.
     *
     * @param id o ID do gênero.
     * @return o gênero encontrado, ou lança uma exceção {@link EntidadeNaoEncontradaException} se o gênero não existir.
     */
    public Genero buscarPorId(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id));
    }

    /**
     * Salva um novo gênero.
     *
     * @param genero o gênero a ser salvo.
     * @return o gênero salvo.
     */
    @Transactional
    public Genero salvar(Genero genero) {
        return generoRepository.save(genero);
    }

    /**
     * Atualiza um gênero existente.
     *
     * @param id      o ID do gênero a ser atualizado.
     * @param filme   o gênero com as informações atualizadas.
     * @return o genero atualizado.
     */
    @Transactional
    public Genero atualizar(Long id, Genero genero) {
        // Verifica se o gênero existe
        if (!generoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id);
        }

        // Atualiza o ID do gênero para o valor recebido
        genero.setId(id);

        // Salva o gênero atualizado
        return generoRepository.save(genero);
    }

    /**
     * Exclui um gênero existente.
     *
     * @param id o ID do gênero a ser excluído.
     * @throws EntidadeNaoEncontradaException se o gênero não existir.
     */
    @Transactional
    public void excluir(Long id) {
        // Verifica se o gênero existe
        if (!generoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id);
        }

        // Exclui o gênero
        generoRepository.deleteById(id);
    }
}

