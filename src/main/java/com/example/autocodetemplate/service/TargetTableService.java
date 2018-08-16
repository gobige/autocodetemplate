package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.TableStructure;
import com.example.autocodetemplate.filter.GenerateTempFilter;
import org.apache.ibatis.annotations.Param;

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
public interface TargetTableService {
    /**
     * @param tempFilter
     * @return
     */
    Collection<TableStructure> descTableStru(GenerateTempFilter tempFilter);

    /**
     * @param tempFilter
     * @return
     */
    Collection<TableStructure> jdbcGetTableStru(GenerateTempFilter tempFilter);
}
