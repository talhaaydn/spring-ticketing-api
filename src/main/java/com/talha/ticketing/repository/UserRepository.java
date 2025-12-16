package com.talha.ticketing.repository;

import com.talha.ticketing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Email'e göre kullanıcı bulur
     * @param email Kullanıcı email adresi
     * @return Optional<User> - Bulunan kullanıcı veya boş
     */
    Optional<User> findByEmail(String email);

    /**
     * Email'in veritabanında var olup olmadığını kontrol eder
     * @param email Kullanıcı email adresi
     * @return boolean - Email varsa true, yoksa false
     */
    boolean existsByEmail(String email);

    /**
     * İsme göre kullanıcı bulur (büyük/küçük harf duyarsız)
     * @param name Kullanıcı adı
     * @return Optional<User> - Bulunan kullanıcı veya boş
     */
    Optional<User> findByNameIgnoreCase(String name);
}

