package com.example.autocodetemplate.ohter.practice.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class MyAgentProgram {
    private static Logger logger = LoggerFactory.getLogger(MyAgentProgram.class);

    public static void preMain(String agentOps, Instrumentation inst) {
        logger.debug("preMain start");
        logger.debug(agentOps);
        logger.debug("preMain end");
    }

    public static void preMain(String agentOps) {
        logger.debug("preMain2 start");
        logger.debug(agentOps);
        logger.debug("preMain2 end");
    }

    public static void main(String[] args) {

    }
}
