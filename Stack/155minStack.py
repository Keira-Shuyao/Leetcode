class MinStack:

    def __init__(self):
        #initial two stack, first stack used store number, second stack use stock min number
        self.stack = [];
        self.minStack = [];

    def push(self, val: int) -> None:
        self.stack.append(val);
        #compare val and minStack's top(minStack[-1] in python) if it is non-empty
        #other wise, takes val
        val = min(val, self.minStack[-1] if self.minStack else val);
        #means: val = (A, B if B != empty, else A)
        self.minStack.append(val);

    def pop(self) -> None:
        self.stack.pop();
        self.minStack.pop();

    def top(self) -> int:
        return self.stack[-1];
        return self.minStack[-1];

    def getMin(self) -> int:
        return self.minStack[-1];


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()


# Example usage
if __name__ == "__main__":
    # Create an instance of MinStack
    obj = MinStack()
    
    # Push values
    obj.push(5)
    obj.push(3)
    obj.push(7)
    obj.push(2)
    
    # Get the minimum value
    print("Minimum:", obj.getMin())  # Output: 2
    
    # Pop the top value
    obj.pop()
    
    # Get the top value
    print("Top:", obj.top())  # Output: 7
    
    # Get the minimum value
    print("Minimum:", obj.getMin())  # Output: 3