package main.java.com;

public class Example {
    public static void main(String[] args) {
        /*int arr [] = {2,4,3};
        int res[] =   arrayChallenge(3,arr);
        for(int i =0;i<3;i++)
        {
            System.out.print(res[i]);
        }*/
       String s ="";
        for (String t :s.split(","))
        {
            if(t.trim().isEmpty())
            {
                continue;
            }
            System.out.println("hello");
        }
    }


    public static int[] arrayChallenge(int n, int arr[]) {
        int[] res = new int[n];
        int counter = 0;
        for (int i = 0; i < n; i++) {
            counter = 0;
            int num = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > num) {
                    counter = counter - Math.abs(num - arr[j]);
                } else {
                    counter = counter + Math.abs(num - arr[j]);
                }
            }
            res[i] = counter;
        }
        return res;
    }

}
