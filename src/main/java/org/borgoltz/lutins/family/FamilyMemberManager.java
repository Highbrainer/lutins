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
		
		if (!force && dao.count() > 22) {
			System.out.println("Member table already populated !");
			return;
		}
		
		dao.deleteAll();

		int i = 0;

		FamilyMember christian = new Organisateur(++i, "Christian", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/christian.webp", "christian.theou@gmail.com");
		FamilyMember francoise = new Profiteur(++i, "Françoise", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/francoise.webp");
		FamilyMember magali = new Organisateur(++i, "Magali", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/magali.webp", "magali.theou@gmail.com");
		FamilyMember alexandre = new Organisateur(++i, "Alexandre", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/alexandre.webp", "alexandre@borgoltz.com");
		FamilyMember maiann = new Organisateur(++i, "Maiann", "/images/flowers/%02d.webp".formatted(i), "/images/members/maiann.webp",
				"maiann.borgoltz@gmail.com");
		FamilyMember louis = new Profiteur(++i, "Louis", "/images/flowers/%02d.webp".formatted(i), "/images/members/louis.webp");
		FamilyMember francois = new Organisateur(++i, "François", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/francois.webp", "f.theou@netc.fr");
		FamilyMember colin = new Profiteur(++i, "Colin", "/images/flowers/%02d.webp".formatted(i), "/images/members/colin.webp");
		FamilyMember elsa = new Profiteur(++i, "Elsa", "/images/flowers/%02d.webp".formatted(i), "/images/members/elsa.webp");
		FamilyMember delphine = new Organisateur(++i, "Delphine", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/delphine.webp", "delf.theou@orange.fr");
		FamilyMember benjamin = new Organisateur(++i, "Benjamin", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/benjamin.webp", "benjamin.casimir@orange.fr");
		FamilyMember chiara = new Organisateur(++i, "Chiara", "/images/flowers/%02d.webp".formatted(i), "/images/members/chiara.webp", "chiaravrin@gmail.com");
		FamilyMember raphael = new Profiteur(++i, "Raphaël", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/raphael.webp");
		FamilyMember titouan = new Profiteur(++i, "Titouan", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/titouan.webp");
		FamilyMember dun = new Profiteur(++i, "Dunvael", "/images/flowers/%02d.webp".formatted(i), "/images/members/dun.webp");
		FamilyMember philippe = new Organisateur(++i, "Philippe", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/philippe.webp", "keltia56@gmail.com");
		FamilyMember colette = new Organisateur(++i, "Colette", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/colette.webp", "donadiocolette@gmail.com");
		FamilyMember maelle = new Profiteur(++i, "Maëlle", "/images/flowers/%02d.webp".formatted(i), "/images/members/maelle.webp");
		FamilyMember lorenzo = new Profiteur(++i, "Lorenzo", "/images/flowers/%02d.webp".formatted(i), "/images/members/lorenzo.webp");
		FamilyMember jb = new Organisateur(++i, "Jean-Baptiste", "/images/flowers/%02d.webp".formatted(i), "/images/members/jb.webp",
				"jb.theou@gmail.com");
		FamilyMember rebecca = new Organisateur(++i, "Rebecca", "/images/flowers/%02d.webp".formatted(i),
				"/images/members/rebecca.webp", "rebeccag000@gmail.com");
//		FamilyMember elena = new Profiteur(++i, "Elena", "/images/flowers/%02d.webp".formatted(i), "/images/members/elena.webp");
		FamilyMember olivier = new Profiteur(++i, "Olivier", "/images/flowers/%02d.webp".formatted(i), "/images/members/olivier.webp");
		FamilyMember maxime = new Profiteur(++i, "Maxime", "/images/flowers/%02d.webp".formatted(i), "/images/members/maxime.webp");

//		dao.deleteAll();

		christian.setNbMissions(3);
		maiann.setNbMissions(1);
		chiara.setNbMissions(1);
		
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

		jb.addIncompatible(rebecca, /*elena,*/ olivier, maxime);
		rebecca.addIncompatible(jb, /*elena,*/ olivier, maxime);

		philippe.addIncompatible(colette, maelle, lorenzo);
		colette.addIncompatible(philippe, maelle, lorenzo);

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
		// List<FamilyMember> ret = this.jdbcTemplate.query("select * from " +
		// this.tablename_members + "",
		// new BeanPropertyRowMapper<FamilyMember>(FamilyMember.class));
		// return ret;
		return dao.findAll();
	}
}
