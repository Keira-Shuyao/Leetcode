import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public static void main(String[] args) {
        String input = "25525511135";
        List<String> result = restoreIpAddresses(input);
        System.out.println(result);
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> address = new ArrayList<>();
        StringBuilder tempAddress = new StringBuilder();
        doRestore(0, tempAddress, address, s);
        return address;
    }

    public static void doRestore(int k, StringBuilder tempAddress, List<String> address, String s) {
        // Check if we have formed 4 segments or the string is empty
        if (k == 4 || s.length() == 0) {
            if (k == 4 && s.length() == 0) {
                address.add(tempAddress.toString());
            }
            return;
        }

        // This limits the loop to a maximum of 3 iterations, ensuring that the part variable can only hold strings of length 1 to 3,
        for (int i = 0; i < Math.min(s.length(), 3); i++) {
            String part = s.substring(0, i + 1);

            // Check for leading zeros and valid range
            if (part.length() > 1 && part.charAt(0) == '0') {
                break; // skip this part as it's invalid
            }

            // Check if the part is a valid integer before parsing
            if (Integer.parseInt(part) <= 255) {
                if (tempAddress.length() != 0) {
                    part = "." + part;
                }
                tempAddress.append(part);
                doRestore(k + 1, tempAddress, address, s.substring(i + 1));
                tempAddress.delete(tempAddress.length() - part.length(), tempAddress.length());
            }
        }
    }
}
