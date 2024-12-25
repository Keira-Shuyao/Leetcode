class Solution {

    private static final String[] KEYS = {"", "","abc","def","ghi","jkl","mno","pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {

        List<String> list = new ArrayList<>();
        if(digits == null || digits.length() == 0){
            return list;
        }

        doCombination(new StringBuilder(), list, digits);
        return list;
    }

    //下面这里比较难，做了两遍还不会
    private void doCombination(StringBuilder prefix, List<String> list, final String digits){
        //base case,当prefix的长度和digits的长度相同时，说明匹配完成，需要把prefix转化成string 放到list里面
        if(prefix.length() == digits.length()){
            list.add(prefix.toString());
            return;
        }
        int curDigits = digits.charAt(prefix.length()) -'0';//因为input的digit是string
        String letters = KEYS[curDigits];
        for(char c: letters.toCharArray()){
            prefix.append(c);                       //增加
            doCombination(prefix, list, digits);
            prefix.deleteCharAt(prefix.length()-1); //删除
        }

    }
}

/**
难点：
1.每个键盘字母怎么储存？ 用private static final String[] KEYS = {"", "","abc","def","ghi","jkl","mno","pqrs", "tuv", "wxyz"};
2.需要combine 不同的char的时候，要用StringBuilder()
3.StringBuilder()使用完之后，要变成String,use: .toString();
3.Backtrack 基本逻辑，先添加当前元素，recursion，再删除当前元素
4.String怎么对每个char做循环?: char c: letters.toCharArray()
5.String怎么取单个Char?: digits.charAt(i)
6.数字char如何变成int?: int digit = digits.charAt(i) -'0';
7.这题的trick, Use array index to match digit to letters. Empty strings for 0 and 1这样遇到数字2， digits[2]="abc"
 */