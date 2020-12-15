package kz.iitu.pharm.zuulserver;

import kz.iitu.pharm.zuulserver.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String role);
}
