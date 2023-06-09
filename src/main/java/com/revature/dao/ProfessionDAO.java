package com.revature.dao;

import com.revature.dao.interfaces.ProfessionDAOInterface;
import com.revature.models.Profession;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessionDAO implements ProfessionDAOInterface {
  private static final Logger logger =
    LoggerFactory.getLogger(ProfessionDAO.class);

  @Override
  public Profession getProfession(int id) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      getProfession(id, conn);
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get profession by ID***");
    }
    return null;
  }

  @Override
  public Profession getProfession(int id, Connection conn) throws SQLException {
    String sql = "SELECT profession FROM professions WHERE id=?";
    PreparedStatement query = conn.prepareStatement(sql);

    query.setInt(1, id);

    ResultSet rs = query.executeQuery();

    if (rs.next()) {
      return new Profession(
        id,
        rs.getString("profession")
      );
    }

    return null;
  }

  @Override
  public Profession getAllProfessions() {
    try (Connection conn = ConnectionUtility.getConnection()) {
      //TODO code
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get ALL professions***");
    }
    return null;
  }

  @Override
  public Profession createProfession(Profession p) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      //TODO code
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to create profession***");
    }
    return null;
  }

  @Override
  public Profession updateProfession(Profession p) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      //TODO code
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to update profession***");
    }
    return null;
  }

  @Override
  public Profession deleteProfession(int pid) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      //TODO code
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to delete profession***");
    }
    return null;
  }
}
