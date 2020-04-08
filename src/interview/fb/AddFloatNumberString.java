package interview.fb;

public class AddFloatNumberString {

    public String add(String a, String b) {
        int lenA = a.length(), lenB = b.length();
        int decPointA = a.indexOf("."), decPointB = b.indexOf(".");
        if (decPointA == -1) {
            a = a + ".";
            decPointA = lenA++;
        }
        if (decPointB == -1) {
            b = b + ".";
            decPointB = lenB++;
        }

        int maxFractionalLen = Math.max(lenA - decPointA - 1, lenB - decPointB - 1), maxIntegralLen = Math.max(decPointA, decPointB);
        int carry = 0, sum;
        StringBuilder sb = new StringBuilder();
        for (int i = maxFractionalLen; i >= -maxIntegralLen; i--) {
            int idxA = decPointA + i, idxB = decPointB + i;
            if (idxA >= 0 && idxA < lenA && a.charAt(idxA) == '.') {
                sb.append(".");
                continue;
            };
            sum = carry;
            sum += (idxA >= lenA || idxA < 0) ? 0 : a.charAt(idxA) - '0';
            sum += (idxB >= lenB || idxB < 0) ? 0 : b.charAt(idxB) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) sb.append(1);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        AddFloatNumberString sol = new AddFloatNumberString();
        System.out.println(sol.add("321.096", "0.9266"));
    }
}
