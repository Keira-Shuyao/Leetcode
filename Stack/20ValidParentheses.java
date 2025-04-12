import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        char[] characters = s.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();
        for (char c : characters) {
            if (map.containsKey(c)) { // Check if it's an opening bracket
                stack.push(c);
            } else { // It's a closing bracket
                if (stack.isEmpty() || map.get(stack.pop()) != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String test1 = "()";
        String test2 = "()[]{}";
        String test3 = "(]";
        String test4 = "([)]";
        String test5 = "{[]}";

        System.out.println("Test 1: " + solution.isValid(test1)); // true
        System.out.println("Test 2: " + solution.isValid(test2)); // true
        System.out.println("Test 3: " + solution.isValid(test3)); // false
        System.out.println("Test 4: " + solution.isValid(test4)); // false
        System.out.println("Test 5: " + solution.isValid(test5)); // true
    }
}