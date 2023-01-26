package org.borgoltz.lutins.family;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class FamilyMemberManager {

    // @Autowired
    // private DataSource dataSource = null;
    @Autowired
    private FamilyMemberDAO dao;

    // @Autowired
    // JdbcTemplate jdbcTemplate = null;

    private String tablename_members = "family_member";
    private String tablename_incompatibilites = "family_member__incompatibility";

    private SimpleJdbcInsert simpleJdbcInsert;

    // @PostConstruct
    // public void afterPropertiesSet() {
    //     simpleJdbcInsert = new SimpleJdbcInsert(this.dataSource).withTableName(this.tablename_members);
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

    public void initDatabase() {
        // this.jdbcTemplate.execute("DROP TABLE IF EXISTS " + this.tablename_members + "");
        // this.jdbcTemplate.execute(
        //         "CREATE TABLE IF NOT EXISTS " + this.tablename_members + " (id integer PRIMARY KEY, name text, card text, image text, incompatibles integer[])");
        int i = 0;

        FamilyMember christian = new Organisateur(++i, "Christian", "/images/pokemons/bulbizarre.png", "/images/members/christian.png", "christian.theou@gmail.com");
        FamilyMember francoise = new Profiteur(++i, "Françoise", "/images/pokemons/caninos.png", "/images/members/francoise.png");
        FamilyMember magali = new Organisateur(++i, "Magali", "/images/pokemons/carapuce.png", "/images/members/magali.png", "magali.theou@gmail.com");
        FamilyMember alexandre = new Organisateur(++i, "Alexandre", "/images/pokemons/chenipan.png", "/images/members/alexandre.png", "alexandre@borgoltz.com");
        FamilyMember maiann = new Organisateur(++i, "Maiann", "/images/pokemons/electrode.png", "/images/members/maiann.png", "maiann.borgoltz@gmail.com");
        FamilyMember louis = new Profiteur(++i, "Louis", "/images/pokemons/evoli.png", "/images/members/louis.png");
        FamilyMember francois = new Organisateur(++i, "François", "/images/pokemons/hypotrempe.png", "/images/members/francois.png", "f.theou@netc.fr");
        FamilyMember chlouchlou = new Organisateur(++i, "Chlouchlou", "/images/pokemons/leveinard.png", "/images/members/chlouchlou2.png", "chloe.lahaye@free.fr");
        FamilyMember colin = new Profiteur(++i, "Colin", "/images/pokemons/m.mime.png", "/images/members/colin.png");
        FamilyMember elsa = new Profiteur(++i, "Elsa", "/images/pokemons/machoc.png", "/images/members/elsa.png");
        FamilyMember delphine = new Organisateur(++i, "Delphine", "/images/pokemons/magicarpe.png", "/images/members/delphine.png", "delf.theou@orange.fr");
        FamilyMember benjamin = new Organisateur(++i, "Benjamin", "/images/pokemons/magmar.png", "/images/members/benjamin.png", "benjamin.casimir@orange.fr");
        FamilyMember chiara = new Profiteur(++i, "Chiara", "/images/pokemons/magneti.png", "/images/members/chiara.png");
        FamilyMember raphael = new Profiteur(++i, "Raphaël", "/images/pokemons/minidraco.png", "/images/members/raphael.png");
        FamilyMember titouan = new Profiteur(++i, "Titouan", "/images/pokemons/psykokwak.png", "/images/members/titouan.png");
        FamilyMember dun = new Profiteur(++i, "Dunvael", "/images/pokemons/osselait.png", "/images/members/dun.png");
        FamilyMember philippe = new Organisateur(++i, "Philippe", "/images/pokemons/pikachu.png", "/images/members/philippe.png", "keltia56@gmail.com");
        FamilyMember colette = new Organisateur(++i, "Colette", "/images/pokemons/poissirène.png", "/images/members/colette.png", "donadiocolette@gmail.com");
        FamilyMember maelle = new Profiteur(++i, "Maëlle", "/images/pokemons/ponyta.png", "/images/members/maelle.png");
        FamilyMember lorenzo = new Profiteur(++i, "Lorenzo", "/images/pokemons/ptitard.png", "/images/members/lorenzo.png");
        FamilyMember jb = new Organisateur(++i, "Jean-Baptiste", "/images/pokemons/rhinocorne.png", "/images/members/jb.png", "jb.theou@gmail.com");
        FamilyMember rebecca = new Organisateur(++i, "Rebecca", "/images/pokemons/rondoudou.png", "/images/members/rebecca.png",  "rebeccag000@gmail.com");
        FamilyMember elena = new Profiteur(++i, "Elena", "/images/pokemons/salamèche.png", "/images/members/elena.png");
        FamilyMember olivier = new Profiteur(++i, "Olivier", "/images/pokemons/tygnon.png", "/images/members/olivier.png");
        FamilyMember maxime = new Profiteur(++i, "Maxime", "/images/pokemons/mystherbe.png", "/images/members/maxime.png");

        if (dao.count()>24) {
            System.out.println("Member table already populated !");
            return;
        }

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

        jb.addIncompatible(rebecca,elena,olivier,maxime);
        rebecca.addIncompatible(jb,elena,olivier,maxime);
        
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
        // List<FamilyMember> ret = this.jdbcTemplate.query("select * from " + this.tablename_members + "",
        //         new BeanPropertyRowMapper<FamilyMember>(FamilyMember.class));
        // return ret;
        return dao.findAll();
    }
}
