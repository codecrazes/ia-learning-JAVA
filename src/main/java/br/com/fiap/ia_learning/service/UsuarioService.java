package br.com.fiap.ia_learning.service;

import br.com.fiap.ia_learning.dto.UsuarioDto;
import br.com.fiap.ia_learning.entity.Usuario;
import br.com.fiap.ia_learning.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @CacheEvict(value = "usuarios", allEntries = true)
    public Usuario criar(UsuarioDto dto) {

        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setSenha(passwordEncoder.encode(dto.getSenha()));
        u.setProfissao(dto.getProfissao());
        u.setRole("USER");

        return usuarioRepository.save(u);
    }

    @Cacheable(value = "usuarios", key = "#id")
    public Usuario buscarPorId(Long id) {
        System.out.println("🔍 BUSCANDO NO BANCO...");
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    @Cacheable(value = "usuariosList")
    public List<Usuario> listar() {
        System.out.println("🗂️ LISTANDO NO BANCO...");
        return usuarioRepository.findAll();
    }

    @CacheEvict(value = { "usuarios", "usuariosList" }, allEntries = true)
    public Usuario atualizar(Long id, UsuarioDto dto) {
        Usuario usuario = buscarPorId(id);

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setProfissao(dto.getProfissao());

        return usuarioRepository.save(usuario);
    }

    @CacheEvict(value = { "usuarios", "usuariosList" }, allEntries = true)
    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email não encontrado"));
    }
}
