package br.com.fiap.ia_learning.service;

import br.com.fiap.ia_learning.dto.UsuarioDto;
import br.com.fiap.ia_learning.entity.Usuario;
import br.com.fiap.ia_learning.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    private String msg(String codigo) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(codigo, null, locale);
    }

    public Usuario criar(UsuarioDto dto) {
        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setSenha(passwordEncoder.encode(dto.getSenha()));
        u.setProfissao(dto.getProfissao());
        u.setRole("USER");
        return usuarioRepository.save(u);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                msg("usuario.nao.encontrado")
                        )
                );
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizar(Long id, UsuarioDto dto) {
        Usuario usuario = buscarPorId(id);

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setProfissao(dto.getProfissao());

        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                msg("usuario.nao.encontrado")
                        )
                );
    }
}
