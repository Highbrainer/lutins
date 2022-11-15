package org.borgoltz.lutins.family;

import javax.persistence.Entity;

@Entity
public class Profiteur extends FamilyMember {

    public Profiteur() {
        this.setOrganisateur(false);
    }

    public Profiteur(long id, String fullname, String card, String image) {
        super(id, fullname, card, image, false, null);
    }
}
