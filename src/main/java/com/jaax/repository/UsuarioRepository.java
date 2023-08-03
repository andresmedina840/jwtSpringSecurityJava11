package com.jaax.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jaax.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByUsuario(String usuario);

}
