package main.java.com;

public enum STATUS {
    SUCCESS("success", 1),
    FAIL("fail",0);
    private final int value;
    private final String desc;
    STATUS(String desc, int value)
    {
        this.desc = desc;
        this.value =value;
    }


    }
