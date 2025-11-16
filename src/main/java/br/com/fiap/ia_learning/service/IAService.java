package br.com.fiap.ia_learning.service;

import br.com.fiap.ia_learning.entity.IA;
import br.com.fiap.ia_learning.repository.IARepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IAService {

    private final IARepository iaRepository;

    public IA criar(IA ia) {
        return iaRepository.save(ia);
    }

    public IA buscarPorId(Long id) {
        return iaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("IA não encontrada"));
    }

    public List<IA> listar() {
        return iaRepository.findAll();
    }

    public IA atualizar(Long id, IA dados) {
        IA ia = buscarPorId(id);

        ia.setNome(dados.getNome());
        ia.setDescricao(dados.getDescricao());
        ia.setTipo(dados.getTipo());
        ia.setEcoScore(dados.getEcoScore());

        return iaRepository.save(ia);
    }

    public void deletar(Long id) {
        iaRepository.delete(buscarPorId(id));
    }
}
