package org.borgoltz.lutins.family;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FamilyMemberDAO extends MongoRepository<FamilyMember, Long> {
	List<FamilyMember> findAll();

	List<FamilyMember> findByOrganisateur(boolean o);

	FamilyMember findById(long id);

	FamilyMember findByNameIgnoreCase(String username);
}
