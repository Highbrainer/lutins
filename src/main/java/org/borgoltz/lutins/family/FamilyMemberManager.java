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
		
		if (!force && dao.count() > 24) {
			System.out.println("Member table already populated !");
			return;
		}

		int i = 0;

		FamilyMember christian = new Organisateur(++i, "Christian", "/images/sports/arc.png",
				"/images/members/christian.gif", "christian.theou@gmail.com");
		FamilyMember francoise = new Profiteur(++i, "Françoise", "/images/sports/badminton.png",
				"/images/members/francoise.gif");
		FamilyMember magali = new Organisateur(++i, "Magali", "/images/sports/baseball.png",
				"/images/members/magali.gif", "magali.theou@gmail.com");
		FamilyMember alexandre = new Organisateur(++i, "Alexandre", "/images/sports/basket.png",
				"/images/members/alexandre.gif", "alexandre@borgoltz.com");
		FamilyMember maiann = new Organisateur(++i, "Maiann", "/images/sports/bmx.png", "/images/members/maiann.gif",
				"maiann.borgoltz@gmail.com");
		FamilyMember louis = new Profiteur(++i, "Louis", "/images/sports/escalade.png", "/images/members/louis.gif");
		FamilyMember francois = new Organisateur(++i, "François", "/images/sports/danse.png",
				"/images/members/francois.gif", "f.theou@netc.fr");
		FamilyMember chlouchlou = new Organisateur(++i, "Chlouchlou", "/images/sports/yoga.png",
				"/images/members/chlouchlou.gif", "chloe.lahaye@free.fr");
		FamilyMember colin = new Profiteur(++i, "Colin", "/images/sports/foot.png", "/images/members/colin.gif");
		FamilyMember elsa = new Profiteur(++i, "Elsa", "/images/sports/golf.png", "/images/members/elsa.gif");
		FamilyMember delphine = new Organisateur(++i, "Delphine", "/images/sports/grs.png",
				"/images/members/delphine.gif", "delf.theou@orange.fr");
		FamilyMember benjamin = new Organisateur(++i, "Benjamin", "/images/sports/hand.png",
				"/images/members/benjamin.gif", "benjamin.casimir@orange.fr");
		FamilyMember chiara = new Profiteur(++i, "Chiara", "/images/sports/hauteur.png", "/images/members/chiara.gif");
		FamilyMember raphael = new Profiteur(++i, "Raphaël", "/images/sports/hockey.png",
				"/images/members/raphael.gif");
		FamilyMember titouan = new Profiteur(++i, "Titouan", "/images/sports/karate.png",
				"/images/members/titouan.gif");
		FamilyMember dun = new Profiteur(++i, "Dunvael", "/images/sports/muscu.png", "/images/members/dun.gif");
		FamilyMember philippe = new Organisateur(++i, "Philippe", "/images/sports/natation.png",
				"/images/members/philippe.gif", "keltia56@gmail.com");
		FamilyMember colette = new Organisateur(++i, "Colette", "/images/sports/patinage.png",
				"/images/members/colette.gif", "donadiocolette@gmail.com");
		FamilyMember maelle = new Profiteur(++i, "Maëlle", "/images/sports/perche.png", "/images/members/maelle.gif");
		FamilyMember lorenzo = new Profiteur(++i, "Lorenzo", "/images/sports/skate.png", "/images/members/lorenzo.gif");
		FamilyMember jb = new Organisateur(++i, "Jean-Baptiste", "/images/sports/ski.png", "/images/members/jb.gif",
				"jb.theou@gmail.com");
		FamilyMember rebecca = new Organisateur(++i, "Rebecca", "/images/sports/surf.png",
				"/images/members/rebecca.gif", "rebeccag000@gmail.com");
		FamilyMember elena = new Profiteur(++i, "Elena", "/images/sports/tennis.png", "/images/members/elena.gif");
		FamilyMember olivier = new Profiteur(++i, "Olivier", "/images/sports/velo.png", "/images/members/olivier.gif");
		FamilyMember maxime = new Profiteur(++i, "Maxime", "/images/sports/volley.png", "/images/members/maxime.gif");

//		dao.deleteAll();

		christian.setNbMissions(3);

		magali.setAdmin(true);
		alexandre.setAdmin(true);

		dao.save(christian);
		dao.save(francoise);
		dao.save(louis);
		dao.save(maiann);
		dao.save(magali);
		dao.save(alexandre);
		dao.save(francois);
		dao.save(chlouchlou);
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
		dao.save(elena);
		dao.save(olivier);
		dao.save(maxime);

		jb.addIncompatible(rebecca, elena, olivier, maxime);
		rebecca.addIncompatible(jb, elena, olivier, maxime);

		philippe.addIncompatible(colette, maelle, lorenzo);
		colette.addIncompatible(philippe, maelle, lorenzo);

		delphine.addIncompatible(benjamin, chiara, raphael, titouan, dun);
		benjamin.addIncompatible(delphine, chiara, raphael, titouan, dun);

		francois.addIncompatible(chlouchlou, colin, elsa);
		chlouchlou.addIncompatible(francois, colin, elsa);

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
		dao.save(francois);
		dao.save(chlouchlou);
		dao.save(magali);
		dao.save(alexandre);
		dao.save(maiann);
		dao.save(christian);

	}

	public List<FamilyMember> listFamilyMembers() {
		// List<FamilyMember> ret = this.jdbcTemplate.query("select * from " +
		// this.tablename_members + "",
		// new BeanPropertyRowMapper<FamilyMember>(FamilyMember.class));
		// return ret;
		return dao.findAll();
	}
}
