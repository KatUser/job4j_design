package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j2 {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j2.class.getName());

    public static void main(String[] args) {
        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
