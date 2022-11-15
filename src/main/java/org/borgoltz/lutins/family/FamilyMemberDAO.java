package org.borgoltz.lutins.family;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMemberDAO extends JpaRepository<FamilyMember, Long> {
    List<FamilyMember> findAll();
    List<FamilyMember> findByOrganisateur(boolean o);
    FamilyMember findById(long id);
    FamilyMember findByNameIgnoreCase(String username);
}
