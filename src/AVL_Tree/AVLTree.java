//Name: Cameron Bartlett
//Date: 02/25/2025
//Lab 5 - Data Structures - AVL Trees
//Description: AVL Tree class

package AVL_Tree;

// define the AVL Tree with various operations
class AVLTree {

    // inner node class
    class AVLNode {
        
        int key, height;
        AVLNode left, right;
    
        AVLNode(int data) {
            
            key = data;
            height = 1; // height of leaf node is 1
        
        }
    }

    // get the height of the node
    int height(AVLNode node) {return (node == null) ? 0 : node.height;}

    // calculate balance factor of the node
    int getBalance(AVLNode node) {return (node == null) ? 0 : height(node.left) - height(node.right);}

    // right rotate subtree rooted with y
    AVLNode rightRotate(AVLNode y) {
        
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // perform rotation
        x.right = y;
        y.left = T2;

        // update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // return new root
        return x;
    
    }

    // left rotate subtree rooted with x
    AVLNode leftRotate(AVLNode x) {
        
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // perform rotation
        y.left = x;
        x.right = T2;

        // update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // return new root
        return y;
    
    }

    // insert a node into the AVL tree and balance it
    AVLNode insert(AVLNode node, int key) {
        
        // perform normal BST insertion
        if (node == null)
            return new AVLNode(key);
 
        if (key < node.key)            
            node.left = insert(node.left, key);
        
        else if (key > node.key)            
            node.right = insert(node.right, key);
        
        else            
            return node; // duplicate keys are not allowed in BST

        // update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // get the balance factor
        int balance = getBalance(node);

        // if the node is unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            
            node.left = leftRotate(node.left);
            return rightRotate(node);
        
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            
            node.right = rightRotate(node.right);
            return leftRotate(node);
        
        }

        return node; // return the (unchanged) node pointer
    
    }

    // delete a node into the AVL tree and balance it
    AVLNode delete(AVLNode root, int key) {

        if (root == null)
            return root;

        // perform normal BST delete
        if (key < root.key)
            
            root.left = delete(root.left, key);
        
        else if (key > root.key)

            root.right = delete(root.right, key);
        
        else {

            // node with only one child or no child
            if (root.left == null || root.right == null) {

                AVLNode temp = (root.left != null) ? root.left : root.right;

                // no child case
                if (temp == null) {

                    temp = root;
                    root = null;

                } else {root = temp;}

            } else {

                // node with two children: get inorder successor
                AVLNode temp = minValueNode(root.right);

                // copy the inorder successor's data to this node
                root.key = temp.key;

                // delete the inorder successor
                root.right = delete(root.right, temp.key);

            }

        }

        // if the tree had only one node then return
        if (root == null)
            return root;

        // update height
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // get balance factor
        int balance = getBalance(root);

        // balance the tree
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
        
        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            
            root.left = leftRotate(root.left);
            return rightRotate(root);
        
        }
        
        // Right Right Case
        if (balance > 1 && getBalance(root.right) <= 0)
            return rightRotate(root);
        
        // Right Left Case
        if (balance > 1 && getBalance(root.left) > 0) {
            
            root.right = rightRotate(root.right);
            return rightRotate(root);
        
        }
        
        return root;
    
    }

    // helper function to find the smallest node in a subtree (inorder successor)
    AVLNode minValueNode(AVLNode node) {

        AVLNode current = node;
        while (current.left != null)

            current = current.left;
        
        return current;

    }

    // inorder traversal of the AVL tree
    void inorderTraversal(AVLNode node) {
        if (node != null) {
            
            inorderTraversal(node.left);
            System.out.print(node.key + " ");
            inorderTraversal(node.right);
        
        }
    }

    // preorder traversal of the AVL tree
    void preorderTraversal(AVLNode node) {
        if (node != null) {
            
            System.out.print(node.key + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        
        }
    }

    // postorder traversal of the AVL tree
    void postorderTraversal(AVLNode node) {
        if (node != null) {
            
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.key + " ");
        
        }
    }
}