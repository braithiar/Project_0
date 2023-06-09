package com.revature.dao.interfaces;

import com.revature.models.Enchantment;

import java.sql.Connection;
import java.sql.SQLException;

public interface EnchantmentDAOInterface {
  Enchantment getEnchantment(int id);
  Enchantment getEnchantment(int id, Connection conn) throws SQLException;
}
