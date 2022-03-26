package foobar;

public class FuelInjection {
    public static void main(String[] args) {
        System.out.println(solution("15"));
    }

    public static int solution(String x) {

        if(x == "0") {
            return 0;
        }

        int fuel = Integer.getInteger(x);
        /*
        - add fuel pallet
        - remove one fuel pallet
        - divide by 2
        */

        if(fuel % 2 != 0) {
            fuel -= 1;
        }

        while(fuel < 0) {
            fuel /= 2;
        }

        return fuel;
    }
}
