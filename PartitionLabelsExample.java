import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartitionLabelsExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for input
        System.out.println("Enter a string of lowercase letters:");
        String input = scanner.nextLine();
        
        // Create an instance of the class containing the partitionLabels method
        PartitionLabelsExample example = new PartitionLabelsExample();
        
        // Call the method and get the result
        List<Integer> result = example.partitionLabels(input);
        
        // Print the result
        System.out.println("Partition sizes: " + result);
        
        scanner.close();
    }

    public List<Integer> partitionLabels(String s) {
        int[] lastIndexsOfChar = new int[26];

        for(int i = 0; i < s.length(); i++){
            lastIndexsOfChar[char2Index(s.charAt(i))] = i;
        }
        
        List<Integer> partitions = new ArrayList<>();
        int firstIndex = 0;
        while (firstIndex < s.length()) {
            int lastIndex = firstIndex;
            for (int i = firstIndex; i < s.length() && i <= lastIndex; i++) {
                int index = lastIndexsOfChar[char2Index(s.charAt(i))];
                if (index > lastIndex) {
                    lastIndex = index;
                }
            }
            partitions.add(lastIndex - firstIndex + 1);
            firstIndex = lastIndex + 1;
        }
        return partitions;
    }

    private int char2Index(char x) {
        return x - 'a';
    }
}

