package com.revature.services;

import com.revature.dao.interfaces.ProfessionDAOInterface;
import com.revature.models.Profession;

public class ProfessionService {
  private final ProfessionDAOInterface proDAO;

  public ProfessionService(ProfessionDAOInterface proDAO) {
    this.proDAO = proDAO;
  }

  public Profession getProfession(int id) {
    if (id > 0) {
      return proDAO.getProfession(id);
    }

    return null;
  }
}
