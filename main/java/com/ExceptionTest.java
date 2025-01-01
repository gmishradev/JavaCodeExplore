package com;

public class ExceptionTest {

    public static void someFunction(String input) throws Exception {
        try {
            if (input.equals("ABC")) {
                System.out.println("Matched");
            }
        } catch (Exception e) {
            System.out.println("before exception");
            throw new Exception(e);
        } finally {
            System.out.println("Input Is " + input + " Finally Executed!!!");
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            System.out.println("********* Test with VALUE ********* ");
            someFunction("ABC");
            System.out.println("\r\n********* Test with NULL  ********* ");
            someFunction(null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
