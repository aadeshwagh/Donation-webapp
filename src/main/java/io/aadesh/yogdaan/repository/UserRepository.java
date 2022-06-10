package io.aadesh.yogdaan.repository;

import io.aadesh.yogdaan.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser,String> {
}
