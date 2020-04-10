package com.sayee.sxsy.newModules.utils;

public class CaseUtils {

    public String[] DISPUTE_TYPE;
    public String[] MAJOR_TYPE;
    public String[] CORE_SYSTEM_TYPE;

    public CaseUtils(String[] DISPUTE_TYPE, String[] MAJOR_TYPE, String[] CORE_SYSTEM_TYPE) {
        this.DISPUTE_TYPE = DISPUTE_TYPE;
        this.MAJOR_TYPE = MAJOR_TYPE;
        this.CORE_SYSTEM_TYPE = CORE_SYSTEM_TYPE;
    }

    public CaseUtils() {
    }

    public String[] getDISPUTE_TYPE() {
        return DISPUTE_TYPE;
    }

    public void setDISPUTE_TYPE(String[] DISPUTE_TYPE) {
        this.DISPUTE_TYPE = DISPUTE_TYPE;
    }

    public String[] getMAJOR_TYPE() {
        return MAJOR_TYPE;
    }

    public void setMAJOR_TYPE(String[] MAJOR_TYPE) {
        this.MAJOR_TYPE = MAJOR_TYPE;
    }

    public String[] getCORE_SYSTEM_TYPE() {
        return CORE_SYSTEM_TYPE;
    }

    public void setCORE_SYSTEM_TYPE(String[] CORE_SYSTEM_TYPE) {
        this.CORE_SYSTEM_TYPE = CORE_SYSTEM_TYPE;
    }
}
