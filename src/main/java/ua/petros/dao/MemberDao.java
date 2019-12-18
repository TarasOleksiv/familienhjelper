package ua.petros.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petros.model.Member;

import java.util.UUID;

public interface MemberDao extends JpaRepository<Member, UUID> {
    Member findByName(String name);
}
