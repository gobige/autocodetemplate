package com.example.autocodetemplate.service.Impl;

import com.example.autocodetemplate.domain.TableStructure;
import com.example.autocodetemplate.filter.GenerateTempFilter;
import com.example.autocodetemplate.service.TargetTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@Service(value = "targetTableService")
public class TargetTableServiceImpl implements TargetTableService {
    private final Logger logger = LoggerFactory.getLogger(TargetTableServiceImpl.class);

    @Override
    public Collection<TableStructure> jdbcGetTableStru(GenerateTempFilter tempFilter) {
        Collection<TableStructure> tableStructures = new ArrayList<>();
        try {
            Class.forName(tempFilter.getJdbcDriver());
            logger.info("加载驱动成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.info("加载驱动失败！");
        }

        Connection con = null;
        try {
            //获取数据库连接
            con = DriverManager.getConnection(tempFilter.getDbUrl(), tempFilter.getDbUserName(), tempFilter.getDbPassword());
            logger.info("获取数据库连接成功！");

            Statement statement = con.createStatement();

            ResultSet rSet = statement.executeQuery("desc " + tempFilter.getTableName());

            while (rSet.next()) {
                TableStructure tableStructure = new TableStructure();
                tableStructure.setField(rSet.getString("Field"));
                tableStructure.setType(rSet.getString("Type"));
                tableStructures.add(tableStructure);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("获取数据库连接失败！");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tableStructures;
    }
}
