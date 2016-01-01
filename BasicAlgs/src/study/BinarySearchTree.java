package study;
/**
 * this is the simple implementation of binary search tree: 
 * in-order traverse, post-order traverse, pre-order traverse
 * add node
 * delete node
 * */
public class BinarySearchTree {
    Node root;
    
    //add a node to the binary search tree
    public void addNewNode(int key, String data){
    	Node newNode=new Node(key, data);
    	if(root==null){
    		root=newNode;
    	}else{
    		Node focusNode=root;
    		Node parent;
    		while(true){
    			parent=focusNode;//set the parent node to the root as start point
    			if(key<focusNode.key){
    				focusNode=focusNode.leftChild;
    				if(focusNode==null){//no left child there
    					parent.leftChild=newNode;// add newnode tobe the leftchild of the parent
    					return;
    				}
    			}else{
    				focusNode=focusNode.rightChild;
    				if(focusNode==null){//no right child there
    					parent.rightChild=newNode;// add newnode tobe the rightchild of the parent
    					return;
    				}
    			}
    		}
    	}
    }
    
    //inorder traverse order: left, parent, right
    public void inOrderTraverse(Node focusNode){
    	if(focusNode!=null){
    		inOrderTraverse(focusNode.leftChild);
    		System.out.println(focusNode);
    		inOrderTraverse(focusNode.rightChild);
    	}
    }
    
    //preorder traverse order: parent, left, right
    public void preOrderTraverse(Node focusNode){
    	if(focusNode!=null){
    		System.out.println(focusNode);
    		preOrderTraverse(focusNode.leftChild);
    		preOrderTraverse(focusNode.rightChild);
    	}
    }
    
    //postorder traverse order:  left, right, parent,
    public void postOrderTraverse(Node focusNode){
    	if(focusNode!=null){
    		
    		postOrderTraverse(focusNode.leftChild);
    		postOrderTraverse(focusNode.rightChild);
    		System.out.println(focusNode);
    	}
    }
    
    //find a node
    public Node findNode(int key){
    	Node focusNode=root;
    	while(focusNode.key!=key){
    		if(key<focusNode.key){
    			focusNode=focusNode.leftChild;
    		}else{
    			focusNode=focusNode.rightChild;
    		}
    		if(focusNode==null) return null;
    	}
    	return focusNode;
    }
    
    //delete a node   
    public boolean removeNode(int key){
    	Node focusNode=root;
    	Node parent =root;
    	boolean isItLeftChild=true;
    	while(focusNode.key!=key){
    		parent=focusNode;
    		if(key<focusNode.key){
    			isItLeftChild=true;
    			focusNode=focusNode.leftChild;
    		}else{
    			isItLeftChild=false;
    			focusNode=focusNode.rightChild;
    		}
    		
    		if(focusNode==null) return false;
    	}
    	
    	if(focusNode.leftChild==null && focusNode.rightChild==null){
    		if(focusNode==root) root=null;
    		else if(isItLeftChild) parent.leftChild=null;
    		else parent.rightChild=null;
    	}
    	else if(focusNode.rightChild==null){
    		if(focusNode==root)  root=focusNode.leftChild;
    		else if(isItLeftChild) parent.leftChild=focusNode.leftChild;
    		else      parent.rightChild=focusNode.leftChild;
    	}
    	else if(focusNode.leftChild==null){
    		if(focusNode==root)  root=focusNode.rightChild;
    		else if(isItLeftChild) parent.leftChild=focusNode.rightChild;
    		else      parent.rightChild=focusNode.leftChild;
    	}
    	else{
    		Node replacement =getReplacementNode(focusNode);
    		if(focusNode==root)   			root=replacement;
    		else if(isItLeftChild)
    			parent.leftChild=replacement;
    		else
    			parent.rightChild=replacement;
    		
    		replacement.leftChild=focusNode.leftChild;
    	}
    	
    	return true;

    }
    
    public Node getReplacementNode(Node replacedNode){
    	Node replacementParent=replacedNode;
    	Node replacement =replacedNode;
    	Node focusNode=replacedNode.rightChild;
    	while(focusNode!=null){
    		replacementParent=replacement;
    		replacement=focusNode;
    		focusNode=focusNode.leftChild;
    	}
    	if(replacement!=replacedNode.rightChild){
    		replacementParent.leftChild=replacement.rightChild;
    		replacement.rightChild=replacedNode.rightChild;
    	}
    	return replacement;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        BinarySearchTree tree=new BinarySearchTree();
        
        //add new node
        tree.addNewNode(1, "Boss");
        tree.addNewNode(2, "President");
        tree.addNewNode(3, "Head");
        tree.addNewNode(4, "Manager");
        tree.addNewNode(5, "Secretary");
        tree.addNewNode(6, "Salesman");
        
        //check traverse implementation
        //tree.inOrderTraverse(tree.root);
        //tree.preOrderTraverse(tree.root);
        tree.postOrderTraverse(tree.root);
        
        //System.out.println("search data with key 3");
        //System.out.println(tree.findNode(3));
        
        System.out.println("remove data with key 3");
        System.out.println(tree.removeNode(3));
        tree.postOrderTraverse(tree.root);
	}

}

//define the nodes in the binary search tree
class Node{
	int key;
	String data;
	
	Node leftChild;
	Node rightChild;
	
	Node(int key, String data){
		this.key=key;
		this.data=data;
	}
	
	public String toString(){
		return this.data+" has a key "+ this.key;
	}
}