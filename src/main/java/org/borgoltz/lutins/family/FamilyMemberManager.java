package org.borgoltz.lutins.family;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyMemberManager {

	// @Autowired
	// private DataSource dataSource = null;
	@Autowired
	private FamilyMemberDAO dao;

	// @Autowired
	// JdbcTemplate jdbcTemplate = null;

	// @PostConstruct
	// public void afterPropertiesSet() {
	// simpleJdbcInsert = new
	// SimpleJdbcInsert(this.dataSource).withTableName(this.tablename_members);
	// }

	public FamilyMemberManager() {
	}

	public FamilyMember addFamilyMember(FamilyMember emp) {
		// Map<String, Object> parameters = new HashMap<String, Object>();
		// parameters.put("ID", emp.getId());
		// parameters.put("FIRST_NAME", emp.getFirstName());
		// parameters.put("LAST_NAME", emp.getLastName());
		// parameters.put("ADDRESS", emp.getAddress());

		return dao.save(emp);

		// return simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(emp));
	}

	public void initDatabase(boolean force) {
		
		if (!force && dao.count() > 23) {
			System.out.println("Member table already populated !");
			return;
		}
		
		dao.deleteAll();

		int i = 0;

		FamilyMember jb = new Organisateur(++i, "Jean-Baptiste", "/images/calendrier/%02d.webp".formatted(i), "/images/members/jb.webp", "jb.theou@gmail.com");
		FamilyMember chiara = new Profiteur(++i, "Chiara", "/images/calendrier/%02d.webp".formatted(i), "/images/members/chiara.webp"/*, "chiaravrin@gmail.com"*/);
		FamilyMember philippe = new Organisateur(++i, "Philippe", "/images/calendrier/%02d.webp".formatted(i), "/images/members/philippe.webp", "keltia56@gmail.com");
		FamilyMember louis = new Profiteur(++i, "Louis", "/images/calendrier/%02d.webp".formatted(i), "/images/members/louis.webp");
		FamilyMember elsa = new Profiteur(++i, "Elsa", "/images/calendrier/%02d.webp".formatted(i), "/images/members/elsa.webp");
		FamilyMember dun = new Profiteur(++i, "Dunvael", "/images/calendrier/%02d.webp".formatted(i), "/images/members/dun.webp");
		FamilyMember olivier = new Profiteur(++i, "Olivier", "/images/calendrier/%02d.webp".formatted(i), "/images/members/olivier.webp");
		FamilyMember alexandre = new Organisateur(++i, "Alexandre", "/images/calendrier/%02d.webp".formatted(i), "/images/members/alexandre.webp", "alexandre@borgoltz.com");
		FamilyMember colette = new Organisateur(++i, "Colette", "/images/calendrier/%02d.webp".formatted(i), "/images/members/colette.webp", "donadiocolette@gmail.com");
		FamilyMember francoise = new Profiteur(++i, "Françoise", "/images/calendrier/%02d.webp".formatted(i), "/images/members/francoise.webp");
		FamilyMember colin = new Profiteur(++i, "Colin", "/images/calendrier/%02d.webp".formatted(i), "/images/members/colin.webp");
		FamilyMember titouan = new Profiteur(++i, "Titouan", "/images/calendrier/%02d.webp".formatted(i), "/images/members/titouan.webp");
		FamilyMember rebecca = new Organisateur(++i, "Rebecca", "/images/calendrier/%02d.webp".formatted(i), "/images/members/rebecca.webp", "rebeccag000@gmail.com");
		FamilyMember delphine = new Organisateur(++i, "Delphine", "/images/calendrier/%02d.webp".formatted(i), "/images/members/delphine.webp", "delf.theou@orange.fr");
		FamilyMember magali = new Organisateur(++i, "Magali", "/images/calendrier/%02d.webp".formatted(i),	"/images/members/magali.webp", "magali.theou@gmail.com");
		FamilyMember maelle = new Profiteur(++i, "Maëlle", "/images/calendrier/%02d.webp".formatted(i), "/images/members/maelle.webp");
		FamilyMember maxime = new Profiteur(++i, "Maxime", "/images/calendrier/%02d.webp".formatted(i), "/images/members/maxime.webp");
		FamilyMember francois = new Organisateur(++i, "François", "/images/calendrier/%02d.webp".formatted(i), "/images/members/francois.webp", "f.theou@netc.fr");
		FamilyMember christian = new Organisateur(++i, "Christian", "/images/calendrier/%02d.webp".formatted(i), "/images/members/christian.webp", "christian.theou@gmail.com");
		FamilyMember lorenzo = new Profiteur(++i, "Lorenzo", "/images/calendrier/%02d.webp".formatted(i), "/images/members/lorenzo.webp");
		FamilyMember maiann = new Organisateur(++i, "Maiann", "/images/calendrier/%02d.webp".formatted(i), "/images/members/maiann.webp", "maiann.borgoltz@gmail.com");
		FamilyMember benjamin = new Organisateur(++i, "Benjamin", "/images/calendrier/%02d.webp".formatted(i), "/images/members/benjamin.webp", "benjamin.casimir@orange.fr");
		FamilyMember mattia = new Profiteur(++i, "Mattia", "/images/calendrier/%02d.webp".formatted(i), "/images/members/mattia.webp");
		FamilyMember raphael = new Profiteur(++i, "Raphaël", "/images/calendrier/%02d.webp".formatted(i), "/images/members/raphael.webp");
		
//		FamilyMember elena = new Profiteur(++i, "Elena", "/images/calendrier/%02d.webp".formatted(i), "/images/members/elena.webp");

//		dao.deleteAll();

		christian.setNbMissions(3);
		magali.setNbMissions(3);
		maiann.setNbMissions(2);
		
		magali.setAdmin(true);
		alexandre.setAdmin(true);

		dao.save(christian);
		dao.save(francoise);
		dao.save(louis);
		dao.save(maiann);
		dao.save(magali);
		dao.save(alexandre);
		dao.save(francois);
		dao.save(colin);
		dao.save(elsa);
		dao.save(delphine);
		dao.save(benjamin);
		dao.save(chiara);
		dao.save(raphael);
		dao.save(titouan);
		dao.save(dun);
		dao.save(philippe);
		dao.save(colette);
		dao.save(maelle);
		dao.save(lorenzo);
		dao.save(jb);
		dao.save(rebecca);
//		dao.save(elena);
		dao.save(olivier);
		dao.save(maxime);
		dao.save(mattia);

		jb.addIncompatible(rebecca, /*elena,*/ olivier, maxime);
		rebecca.addIncompatible(jb, /*elena,*/ olivier, maxime);

		philippe.addIncompatible(colette, maelle, lorenzo, mattia);
		colette.addIncompatible(philippe, maelle, lorenzo, mattia);

		delphine.addIncompatible(benjamin, chiara, raphael, titouan, dun);
		benjamin.addIncompatible(delphine, chiara, raphael, titouan, dun);
		chiara.addIncompatible(delphine, benjamin, raphael, titouan, dun);

		francois.addIncompatible(colin, elsa);

		magali.addIncompatible(alexandre, maiann, louis);
		alexandre.addIncompatible(magali, maiann, louis);
		maiann.addIncompatible(alexandre, magali, louis);

		christian.addIncompatible(francoise);

		dao.save(jb);
		dao.save(rebecca);
		dao.save(philippe);
		dao.save(colette);
		dao.save(delphine);
		dao.save(benjamin);
		dao.save(chiara);
		dao.save(francois);
		dao.save(magali);
		dao.save(alexandre);
		dao.save(maiann);
		dao.save(christian);
		
		// on s'assure que les missions sont en nombre satisfaisant 
		var nbMembres = dao.count();
		int nbMissions = dao.findByOrganisateur(true).stream().map(Organisateur.class::cast).mapToInt(Organisateur::getNbMissions).sum();
		
		if (nbMembres != nbMissions) {
			throw new IllegalStateException("Il y a %d membres dans la famille vs %d missions !".formatted(nbMembres, nbMissions));
		}

	}

	public List<FamilyMember> listFamilyMembers() {
		return dao.findAll();
	}
}
