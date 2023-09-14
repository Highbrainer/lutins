package org.borgoltz.lutins;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.borgoltz.lutins.family.FamilyMember;
import org.borgoltz.lutins.family.FamilyMemberDAO;
import org.borgoltz.lutins.family.FamilyMemberManager;
import org.borgoltz.lutins.family.Organisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LutinsController {

    private Map<String, LocalDateTime> usersLastAccess = new HashMap<>();

    @Autowired
    private FamilyMemberManager familyMemberManager;

    @Autowired
    private FamilyMemberDAO dao;

    @GetMapping({ "/", "/home" })
    public String getCurrentUser(@AuthenticationPrincipal User user, Model model) {
        String username = user.getUsername();

        FamilyMember connected = dao.findByNameIgnoreCase(username);

        model.addAttribute("username", username);
        model.addAttribute("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        model.addAttribute("user", connected);
        model.addAttribute("lastAccess", usersLastAccess.get(username));

        usersLastAccess.put(username, LocalDateTime.now());

        return "home";
    }

    @RequestMapping("/login")
    String login() {
        return "login";
    }

    @RequestMapping("/init")
    String init(Map<String, Object> model, @RequestParam(defaultValue = "false") boolean force) {
        this.familyMemberManager.initDatabase(force);
        return this.list(model);
    }

    @RequestMapping("/list")
    String list(Map<String, Object> model) {
        List<FamilyMember> family = this.familyMemberManager.listFamilyMembers();
        model.put("family", family);
        model.put("comparator",
                Comparator.comparing(FamilyMember::isOrganisateur).reversed().thenComparing(FamilyMember::getName));
        return "list";
    }

    @RequestMapping("/missions")
    String organisateurs(@AuthenticationPrincipal User user, Model model) {

        getCurrentUser(user, model);

        List<FamilyMember> family = this.dao.findByOrganisateur(true);
        model.addAttribute("organisateurs", family);
        model.addAttribute("comparator",
                Comparator.comparing(FamilyMember::getName));
        return "missions";
    }

    private void addMission(Organisateur org, FamilyMember profiteur) {
        if (null == profiteur) {
            throw new IllegalArgumentException("Profiteur invalide (null)");
        }
        if (org.getMissions().contains(profiteur)) {
            System.err
                    .println("Mission déjà affectée. Orga : " + org.getName() + ", profiteur : " + profiteur.getName());
        } else {
            boolean missionAlreadyAffected = dao.findByOrganisateur(true).stream().map(FamilyMember::getMissions)
                    .flatMap(Collection::stream).anyMatch(profiteur::equals);
            if (missionAlreadyAffected) {
                throw new IllegalStateException("Ce membre de la famille a déjà été choisi par quelqu'un d'autre !");
            }
        }
        org.getMissions().add(profiteur);
        dao.save(org);
    }

    @RequestMapping("/choose")
    String choose(@AuthenticationPrincipal User user, Model model, @RequestParam(required = true) long profiteur_id) {
        String home = getCurrentUser(user, model);
        Organisateur org = (Organisateur) model.getAttribute("user");
        System.out.println(profiteur_id);
        var prof = dao.findById(profiteur_id);
        addMission(org, prof);
        return "redirect:"+home + "?#missions";
    }

    @RequestMapping("/choice")
    String choice(@AuthenticationPrincipal User user, Model model) {
        getCurrentUser(user, model);
        FamilyMember me = (FamilyMember) model.getAttribute("user");
        List<FamilyMember> family = this.familyMemberManager.listFamilyMembers();
        Set<FamilyMember> alredayAffected = family.stream().map(FamilyMember::getMissions).flatMap(Collection::stream).collect(Collectors.toSet());
        family.removeAll(alredayAffected);
        family.remove(me);
        family.removeAll(me.getIncompatibleMembers());
        family.removeAll(me.getMissions());


        Collections.shuffle(family);

        model.addAttribute("family", family);
        model.addAttribute("comparator",
                Comparator.comparing(FamilyMember::isOrganisateur).reversed().thenComparing(FamilyMember::getName));
        return "choice";
    }

}
