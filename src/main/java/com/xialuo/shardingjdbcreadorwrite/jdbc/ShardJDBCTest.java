package com.xialuo.shardingjdbcreadorwrite.jdbc;

import io.shardingsphere.api.algorithm.masterslave.RoundRobinMasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class ShardJDBCTest {
  public static void main(String[] args) throws SQLException {
    // 配置真实数据源
    Map<String, DataSource> dataSourceMap = new HashMap<>();

    // 配置第一个数据源
    BasicDataSource dataSource1 = new BasicDataSource();
    dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource1.setUrl("jdbc:mysql://59.110.213.162:3339/shard0");
    dataSource1.setUsername("root");
    dataSource1.setPassword("123456");
    dataSourceMap.put("master", dataSource1);

    // 配置第二个数据源
    BasicDataSource dataSource2 = new BasicDataSource();
    dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource2.setUrl("jdbc:mysql://59.110.213.162:3340/shard0");
    dataSource2.setUsername("root");
    dataSource2.setPassword("123456");
    dataSourceMap.put("slave0", dataSource2);
    // 配置第三个数据源
    BasicDataSource dataSource3 = new BasicDataSource();
    dataSource3.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource3.setUrl("jdbc:mysql://59.110.213.162:3341/shard0");
    dataSource3.setUsername("root");
    dataSource3.setPassword("123456");
    dataSourceMap.put("slave1", dataSource3);

    // 配置读写分离规则
    // MasterSlaveRuleConfiguration masterSlaveRuleConfig = new
    // MasterSlaveRuleConfiguration("ds_master_slave", "ds_master", Arrays.asList("ds_slave0",
    // "ds_slave1"));
    MasterSlaveRuleConfiguration masterSlaveRuleConfig =
        new MasterSlaveRuleConfiguration(
            "ds_master_slave",
            "master",
            Arrays.asList("slave0", "slave1"),
            new RoundRobinMasterSlaveLoadBalanceAlgorithm());

    // 获取数据源对象
    DataSource dataSource =
        MasterSlaveDataSourceFactory.createDataSource(
            dataSourceMap, masterSlaveRuleConfig, new HashMap<String, Object>(), new Properties());

    Map<String, Object> map = new HashMap<>();

    String sql = "SELECT * from user WHERE user_id=?";
    try (Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setInt(1, 1);
      System.out.println();
      try (ResultSet rs = preparedStatement.executeQuery()) {
        while (rs.next()) {
          // %2结果，路由到 shard1.order1
          System.out.println("user_id---------" + rs.getInt(1));
          System.out.println("name---------" + rs.getString(2));
          System.out.println("age---------" + rs.getInt(3));
        }
      }
    }
  }
}
