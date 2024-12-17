package fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
