package com.bits.mvn;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class App {

    private static Logger logger = LogManager.getLogger(App.class);

    private String name = "Hello"; // intentionally unused (Sonar issue)

    public String greet(String name){
        return "hello " + name + "!";
    }

    public static void main(String args[]){

        App app = new App();

        logger.debug(3/0); // division by zero (Sonar Bug)

        if(true){ // always true (Sonar Code Smell)
            logger.info(app.greet(args[0]));
        }
    }
}

