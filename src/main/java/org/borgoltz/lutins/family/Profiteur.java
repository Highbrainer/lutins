package org.borgoltz.lutins.family;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Profiteur extends FamilyMember {

	public Profiteur() {
		this.setOrganisateur(false);
	}

	public Profiteur(long id, String fullname, String card, String image) {
		super(id, fullname, card, image, false, null);
	}
}
