package org.borgoltz.lutins.family;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FamilyMember {
    @Id
    private long id;

    private String name;

    private String card;

    private String image;

    private boolean organisateur = true;

    private boolean admin = false;

    private String email;

    private int nbMissions = 0;

    @Reference(FamilyMember.class)
    private Set<FamilyMember> incompatibleMembers = new HashSet<>();

	@Reference(FamilyMember.class)
    private Set<FamilyMember> missions = new HashSet<>();

    public FamilyMember() {

    }

    public FamilyMember(long id, String fullname, String card, String image, boolean organisateur, String email) {
        this.id = id;
        this.name = fullname;
        this.card = card;
        this.image = image;
        this.organisateur = organisateur;
        this.nbMissions = organisateur ? 2 : 0;
        this.email = email;
        if (organisateur && !isValidEmail(email)) {
            throw new IllegalArgumentException("Email invalide pour " + fullname + " : '" + email + "'");
        }
    }

    private boolean isValidEmail(String email) {
        return null != email && !email.isBlank() && email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<FamilyMember> getIncompatibleMembers() {
        return incompatibleMembers;
    }

    public Set<FamilyMember> getMissions() {
        return missions;
    }

    public String getName() {
        return name;
    }

    public void setName(String fullname) {
        this.name = fullname;
    }

    public int getNbMissions() {
        return nbMissions;
    }

    public void setNbMissions(int nbMission) {
        this.nbMissions = nbMission;
    }

    public boolean isOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(boolean organisateur) {
        this.organisateur = organisateur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addIncompatible(FamilyMember... members) {
        getIncompatibleMembers().addAll(Arrays.asList(members));
    }

    public void addMission(FamilyMember... missions) {
        getMissions().addAll(Arrays.asList(missions));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object other) {
        if (null==other) return false;
        if(!(other instanceof FamilyMember)) return false;
        return id==((FamilyMember)other).getId();
    }

}
