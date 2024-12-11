public class NextGreatestLetter {
    public static void main(String[] args) {
        // char[] letters = {'c','f','j'};
        // char target = 'a';
        // System.out.println(nextGreatestLetter(letters, target));
        char[] letters1 = {'x','x','y','y','y','z','z'};
        char target1 = 'y';
        System.out.println(nextGreatestLetter(letters1, target1));
    }

    public static char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0, h = letters.length - 1;
        while(l <= h) {
            int m = l + (h - l)/2;
            if(letters[m] <= target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l < n ? letters[l] : letters[0];
    }
}
