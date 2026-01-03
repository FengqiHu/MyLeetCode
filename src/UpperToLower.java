public class UpperToLower {


    public static String switchCase(String str) {
        String newStr = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 65 && c <= 90) {
                c = (char) (c + 32);
            } else {
                c = (char) (c - 32);
            }
            newStr += c;
        }
        return newStr;
    }

    /**
     * 13. Roman to Integer
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        int result = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == 'I') {
                if (i != length - 1 && s.charAt(i + 1) == 'V') {
                    result += 4;
                    i++;
                    continue;
                } else if (i != length - 1 && s.charAt(i + 1) == 'X') {
                    result += 9;
                    i++;
                    continue;
                }
                result += 1;
            } else if (c == 'V') {
                result += 5;
            } else if (c == 'X') {
                if (i != length - 1 && s.charAt(i + 1) == 'L') {
                    result += 40;
                    i++;
                    continue;
                } else if (i != length - 1 && s.charAt(i + 1) == 'C') {
                    result += 90;
                    i++;
                    continue;
                }
                result += 10;
            } else if (c == 'L') {
                result += 50;
            } else if (c == 'C') {
                if (i != length - 1 && s.charAt(i + 1) == 'D') {
                    result += 400;
                    i++;
                    continue;
                } else if (i != length - 1 && s.charAt(i + 1) == 'M') {
                    result += 900;
                    i++;
                    continue;
                }
                result += 100;
            } else if (c == 'D') {
                result += 500;
            } else if (c == 'M') {
                result += 1000;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "AbdchHJD";
        System.out.println(romanToInt("MCMXCIV"));
    }
}
