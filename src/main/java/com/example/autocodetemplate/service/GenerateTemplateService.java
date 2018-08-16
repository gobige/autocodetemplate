package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.TableStructure;

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
public interface GenerateTemplateService {

    /**
     * @param tableName
     */
    Boolean generateTemplateByTable(String tableName, Collection<TableStructure> tableStructures);

    /**
     * @param tableName
     * @return
     */
    Boolean generateDomain(String tableName, Collection<TableStructure> tableStructures);

    /**
     * @param tableName
     * @return
     */
    Boolean generateMapper(String tableName, Collection<TableStructure> tableStructures);

    /**
     * @param tableName
     * @return
     */
    Boolean generateDao(String tableName);

    /**
     * @param tableName
     * @return
     */
    Boolean generateService(String tableName);
}
