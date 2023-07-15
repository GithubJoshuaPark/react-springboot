package com.soromiso.jwtex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soromiso.jwtex.domain.token.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{
    @Query(value = "select t from Token t inner join User u on t.user.id = u.id where u.id = :id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(Integer id);
    Optional<Token> findByToken(String token);
}
