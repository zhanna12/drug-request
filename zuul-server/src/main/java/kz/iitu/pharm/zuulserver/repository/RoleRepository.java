package kz.iitu.pharm.zuulserver.repository;

import kz.iitu.pharm.zuulserver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String role);
}
