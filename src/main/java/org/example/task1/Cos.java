package org.example.task1;

public class Cos {
    public static double cos(double x, int n){
        double result = 1;
        double PI2 = Math.PI * 2;

        if (x >= 0){
            while(x > PI2){
                x-= PI2;
            }
        } else {
            while(x < PI2){
                x += PI2;
            }
        }

        double xx = x * x;
        int sign = -1;
        double factorial = 1;
        double dividend = xx;

        for(int i = 2; i < n; i += 2){
            factorial /= i;
            result += sign * dividend * factorial;
            sign = -1 * sign;
            factorial /= (i+1);
            dividend *= xx;
        }
        return result;
    }
}
