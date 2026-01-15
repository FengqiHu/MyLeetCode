/**
 * @author louishu
 * @date 1/14/26 19:59
 * @description
 */
public class Approches {
    public static int MOD = 1000000007;
    /**
     * quick pow
     * @param base
     * @param exp
     * @return
     */
    public static int pow(long base, long exp, long  mod){
        // example = 2^44
        // simulate binary operation: 44 = 101100  = > 2^32 + 2^8 * 2^4
        long res = 1;
        while(exp > 0){
            if(exp % 2 == 1){
                // res: 2^4 * 1(100) -> 2^8 * 2^4(1100) -> 2^32 * 2^12 (101100)
                res = (res * base);
            }
            // Keep the largest quadratic (100000)
            // base always one step ahead of res
            base = (base * base);
            exp /= 2;
        }
        return (int)res;
    }
    public static void main(String[] args) {
        System.out.println(pow(2,40, 1000000007));
        System.out.println((int)Math.pow(2,32)%1000000007);
    }
}
