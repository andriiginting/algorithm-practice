class Solution {
    val list = mutableListOf<MutableList<Int>>()

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if(root == null) {
            return emptyList()
        }

        helper(root, 0)

        return list
    }

    fun helper(root: TreeNode?, position: Int) {
        if(list.size == position) {
            list.add(mutableListOf())
        }

        list[position].add(root?.`val` ?: 0)

        if(root?.left != null) {
            helper(root.left, list.size + 1)
        }
        if(root?.right != null) {
            helper(root.right, list.size + 1)
        }
    }
}