package main.java.com;

public class Test {


    static final int  a = 3;
    public static void main(String[] args) {

        for(int i=0;i<3;i++)
        {
            switch (i)
            {
                case a:
                    System.out.println(10);
                case a-1:
                    System.out.println(1010);
                default:
                    System.out.println(111);
            }
        }
    }
}
