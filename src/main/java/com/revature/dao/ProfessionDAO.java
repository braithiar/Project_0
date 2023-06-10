package com.revature.dao;

import com.revature.dao.interfaces.ProfessionDAOInterface;
import com.revature.models.Profession;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessionDAO implements ProfessionDAOInterface {
  private static final Logger logger =
    LoggerFactory.getLogger(ProfessionDAO.class);

  @Override
  public Profession getProfession(int pid) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      return getProfession(pid, conn);
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get profession by ID***");
    }
    return null;
  }

  @Override
  public Profession getProfession(int pid, Connection conn) throws SQLException {
    String sql = "SELECT profession FROM professions WHERE id=?";
    PreparedStatement query = conn.prepareStatement(sql);

    query.setInt(1, pid);

    ResultSet rs = query.executeQuery();

    if (rs.next()) {
      return new Profession(
        pid,
        rs.getString("profession")
      );
    }

    return null;
  }

  @Override
  public List<Profession> getAllProfessions() {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "SELECT id, profession FROM professions";
      Statement query = conn.createStatement();

      ResultSet rs = query.executeQuery(sql);
      List<Profession> profs = new ArrayList<>();

      while (rs.next()) {
        profs.add(
          new Profession(
            rs.getInt("id"),
            rs.getString("profession")
          )
        );
      }

      if (profs != null) {
        logger.info("Retrieved all professions");
        return profs;
      }
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
      String sql = "INSERT INTO professions (profession) VALUES (?) RETURNING *";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setString(1, p.getProf());

      ResultSet rs = query.executeQuery();

      if (rs.next()) {
        logger.info("Added new profession " + p.getProf());
        return new Profession(
          rs.getInt("id"),
          p.getProf()
        );
      }
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
      String sql = "UPDATE professions SET profession=? WHERE id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setString(1, p.getProf());
      query.setInt(2, p.getId());

      if (query.executeUpdate() > 0) {
        logger.info(
          "Updated profession id: " + p.getId() + " to " + p.getProf());
        return p;
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to update profession***");
    }
    return null;
  }

  @Override
  public Profession deleteProfession(int pid) {
    /*try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "DELETE FROM professions WHERE id=? RETURNING *";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, pid);

      ResultSet rs = query.executeQuery();

      if (rs.next()) {
        logger.info("Deleted profession with id: " + pid + ", " +
                    rs.getString("profession"));
        return new Profession(
          pid,
          rs.getString("profession")
        );
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to delete profession***");
    }*/
    return null;
  }
}
