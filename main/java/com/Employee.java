package com;

import java.math.BigDecimal;

public class Employee {

    public static void main(String[] args) {
       // "overall_percentage": 7.329246935201401e-01,
       double data =  new BigDecimal("7.329246935201401e-01").doubleValue()*100;
       System.out.println(data);
    }
}
