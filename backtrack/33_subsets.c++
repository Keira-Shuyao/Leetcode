#include <iostream>
#include <vector>
using namespace std;

class Solution {
    public:
        vector<vector<int>> subsets(vector<int>& nums) {
            vector<vector<int>> subs;
            vector<int> sub;
            subsets(nums, 0, sub, subs);
            return subs;
        }
    
    private:
        void subsets(vector<int>& nums, int i, vector<int>& sub, vector<vector<int>>& subs) {
            subs.push_back(sub);
            for (int j = i; j < nums.size(); j++) {
                sub.push_back(nums[j]);
                subsets(nums, j + 1, sub, subs);
                sub.pop_back();
            }
        }
};

// Main function to test the subsets function
int main() {
    Solution solution;

    // Example 1
    vector<int> nums1 = {1, 2, 3};
    vector<vector<int>> result1 = solution.subsets(nums1);
    cout << "Input: nums = [1,2,3]" << endl;
    cout << "Output: [";
    for (const auto& subset : result1) {
        cout << "[";
        for (int num : subset) {
            cout << num << (num == subset.back() ? "" : ",");
        }
        cout << "]";
    }
    cout << "]" << endl;

    // Example 2
    vector<int> nums2 = {0};
    vector<vector<int>> result2 = solution.subsets(nums2);
    cout << "Input: nums = [0]" << endl;
    cout << "Output: [";
    for (const auto& subset : result2) {
        cout << "[";
        for (int num : subset) {
            cout << num << (num == subset.back() ? "" : ",");
        }
        cout << "]";
    }
    cout << "]" << endl;

    return 0;
}
