package com.revature.services;

import com.revature.dao.interfaces.ProfessionDAOInterface;
import com.revature.models.Profession;

import java.util.List;

public class ProfessionService {
  private final ProfessionDAOInterface pDAO;

  public ProfessionService(ProfessionDAOInterface pDAO) {
    this.pDAO = pDAO;
  }

  public Profession getProfession(int pid) {
    if (pid > 0) {
      return pDAO.getProfession(pid);
    }

    return null;
  }

  public List<Profession> getAllProfessions() {
    return pDAO.getAllProfessions();
  }

  public Profession createProfession(Profession p) {
    if (p != null) {
      return pDAO.createProfession(p);
    }

    return null;
  }

  public Profession updateProfession(Profession p) {
    if (p != null) {
      return pDAO.updateProfession(p);
    }

    return null;
  }

  /*public Profession deleteProfession(int pid) {
    if (pid > 0) {
      return pDAO.deleteProfession(pid);
    }

    return null;
  }*/
}
