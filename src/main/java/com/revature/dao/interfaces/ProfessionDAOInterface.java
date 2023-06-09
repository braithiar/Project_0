package com.revature.dao.interfaces;

import com.revature.models.Profession;

import java.sql.Connection;
import java.sql.SQLException;

public interface ProfessionDAOInterface {
  Profession getProfession(int id);

  Profession getProfession(int id, Connection conn) throws SQLException;

  Profession getAllProfessions();

  Profession createProfession(Profession p);

  Profession updateProfession(Profession p);

  Profession deleteProfession(int pid);
}
