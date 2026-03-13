/**
 * @author louishu
 * @date 1/14/26 19:59
 * @description
 */
public class Approches {
    public static int MOD = 1000000007;

    /**
     * quick pow to get mod
     *
     * @param base
     * @param exp
     * @return
     */
    public static long pow(long base, long exp, long mod) {
        // example = 2^44
        // simulate binary operation: 44 = 101100  = > 2^32 * 2^8 * 2^4
        long res = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                // res: 2^4 * 1(100) -> 2^8 * 2^4(1100) -> 2^32 * 2^12 (101100)
                res = (res * base) % mod;
            }
            // Keep the largest quadratic (100000)
            // base always one step ahead of res, prepare for the next 0
            base = (base * base) % mod;
            exp /= 2;
        }
        return (long) res;
    }

    public static void main(String[] args) {
        System.out.println(pow(2, 32, 1000000007));
        System.out.println(Math.pow(2, 32));
    }
}
