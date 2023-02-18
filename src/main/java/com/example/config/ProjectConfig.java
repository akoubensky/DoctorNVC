package com.example.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.h2.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import javax.sql.PooledConnection;

@Configuration
public class ProjectConfig {

  @Value("${datasource.url}")
  private String datasourceUrl;

  @Value("${datasource.driverClass}")
  private String datasourceDriverClass;

  @Value("${datasource.username}")
  private String datasourceUsername;

  @Value("${datasource.password}")
  private String datasourcePassword;

  @Bean
  @SneakyThrows
  public DataSource dataSource() {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    dataSource.setDriverClass((Class<? extends java.sql.Driver>)Class.forName(datasourceDriverClass));
    dataSource.setUrl(datasourceUrl);
    dataSource.setUsername(datasourceUsername);
    dataSource.setPassword(datasourcePassword);
    //dataSource.setTimeout(1000);
    return dataSource;
  }
}
