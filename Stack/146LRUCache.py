class Node:
    def __init__(self, key, val):
        self.key, self.val = key, val
        self.prev = self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.cap = capacity
        self.cache={} # map key to node
        #left = LRU least recent used, right = most recent used
        self.left, self.right = Node(0,0), Node(0,0);
        self.left.next, self.right.prev = self.right, self.left
    #因为删除node不是从左边来的，所以不从左边开始
    def remove(self, node):
        prev = node.prev
        nxt = node.next
        prev.next = nxt
        nxt.prev = prev

    #insert node at right 从右边添加新node，因为右边代表most recent used
    def insert(self, node):
        prev = self.right.prev
        nxt = self.right
        prev.next = nxt.prev = node
        node.next = nxt
        node.prev = prev


    def get(self, key: int) -> int:
        if key in self.cache:
            #update most recent used remove and insert helper function
            #先remove 再insert 保证用过的在最前面
            self.remove(self.cache[key])
            self.insert(self.cache[key])
            return self.cache[key].val
        return -1;

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            #先remove 再insert 保证用过的在最前面
            self.remove(self.cache[key])
        self.cache[key] = Node(key, value)
        self.insert(self.cache[key])

        #each time insert, think about capacity and if exceed, remove LRU
        if len(self.cache)>self.cap:
            #remove LRU from both hashmap and list
            lru = self.left.next
            self.remove(lru)
            del self.cache[lru.key] # remember to delete this key use del function



# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)