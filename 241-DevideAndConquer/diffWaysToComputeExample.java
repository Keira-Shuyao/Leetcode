import java.util.ArrayList;
import java.util.List;

public class diffWaysToComputeExample {
    public static void main(String[] args) {
        diffWaysToComputeExample example = new diffWaysToComputeExample();
        String input1 = "2-1-1";
        List <Integer> result1 = example.diffWaysToCompute(input1);
        System.out.println(result1);

        System.out.println();
        String input2 = "2*3-4*5";
        List <Integer> result2 = example.diffWaysToCompute(input2);
        System.out.println(result2);
    }
    

    public List<Integer> diffWaysToCompute(String input){
        
        List <Integer> ways = new ArrayList<>();
        for(int i = 0; i < input.length(); i ++){
            char c = input.charAt(i);
            if(c =='+' || c == '-' || c =='*'){
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1));

                for(int l: left){
                    for(int r:right){
                        switch (c) {
                            case '+':
                                ways.add(l+r);
                                break;

                            case '-':
                                ways.add(l-r);
                                break;
                            
                            case '*':
                                ways.add(l*r);
                                break;
                        }
                    }
                }
            }
        }


        if (ways.size() == 0) {
            ways.add(Integer.valueOf(input));
        }
        
        return ways;
        }
}