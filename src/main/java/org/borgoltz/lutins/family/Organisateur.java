package org.borgoltz.lutins.family;

import javax.persistence.Entity;

@Entity
public class Organisateur extends FamilyMember {

    public Organisateur() {
        setOrganisateur(true);
    }

    public Organisateur(long id, String fullname, String card, String image, String email) {
        super(id, fullname, card, image, true, email);
    }
}
