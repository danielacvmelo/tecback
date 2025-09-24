package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado com o ID: " + id));
    }

    @Transactional
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public Endereco atualizar(Long id, Endereco endereco) {
        if (!enderecoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Endereço não encontrado com o ID: " + id);
        }
        endereco.setId(id);
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public void excluir(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Endereço não encontrado com o ID: " + id);
        }
        enderecoRepository.deleteById(id);
    }
}