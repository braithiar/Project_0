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
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get profession by ID***");
    }
    return null;
  }
}
