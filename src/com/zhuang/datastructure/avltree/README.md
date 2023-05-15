### 2. AVL 树

前面介绍过，如果一棵二叉搜索树长的不平衡，那么查询的效率会受到影响，如下图

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6c38909d248044fbae88899415bc1124~tplv-k3u1fbpfcp-zoom-1.image)

通过旋转可以让树重新变得平衡，并且不会改变二叉搜索树的性质（即左边仍然小，右边仍然大）

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b2afc3230bbb427eb715e573960ca8a6~tplv-k3u1fbpfcp-zoom-1.image)

#### 如何判断失衡？

> 如果一个节点的**左右孩子，高度差超过 1**，则此节点失衡，才需要旋转

#### 处理高度

如何得到节点高度？一种方式之前做过的一道题目：E05. 求二叉树的最大深度（高度），但由于求高度是一个非常频繁的操作，因此将高度作为节点的一个属性，将来新增或删除时及时更新，默认为 1（按力扣说法）

```java
static class AVLNode {
    int height = 1;
    int key;
    Object value;
    AVLNode left;
    AVLNode right;
    // ...
}
```

**求高度代码**

这里加入了 height 函数方便求节点为 null 时的高度

```java
private int height(AVLNode node) {
    return node == null ? 0 : node.height;
}
```

**更新高度代码**

将来新增、删除、旋转时，高度都可能发生变化，需要更新。下面是更新高度的代码

```java
private void updateHeight(AVLNode node) {
    node.height = Integer.max(height(node.left), height(node.right)) + 1;
}
```

#### 何时触发失衡判断？

定义平衡因子（balance factor）如下
$$
平衡因子 = 左子树高度 - 右子树高度
$$
当平衡因子

*   bf = 0，1，-1 时，表示左右平衡
*   bf > 1 时，表示左边太高
*   bf < -1 时，表示右边太高

对应代码

```java
private int bf(AVLNode node) {
    return height(node.left) - height(node.right);
}
```

当插入新节点，或删除节点时，引起高度变化时，例如

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5d4f52177eb347ec803c467f680d756d~tplv-k3u1fbpfcp-zoom-1.image)

目前此树平衡，当再插入一个 4 时，节点们的高度都产生了相应的变化，8 节点失衡了

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/83d40833c4054b78aac22ea18dc0c77b~tplv-k3u1fbpfcp-zoom-1.image)

在比如说，下面这棵树一开始也是平衡的

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1873059a94eb454c978cd2237bf2b846~tplv-k3u1fbpfcp-zoom-1.image)

当删除节点 8 时，节点们的高度都产生了相应的变化，6 节点失衡了
![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/73d4eb4ca5fd402391321994a74534c7~tplv-k3u1fbpfcp-zoom-1.image)

#### 失衡的四种情况

**LL**

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1a8c6feab1564af4aa3747ae7b494dd7~tplv-k3u1fbpfcp-zoom-1.image)

*   失衡节点（图中 8 红色）的 bf > 1，即左边更高
*   失衡节点的左孩子（图中 6）的 bf >= 0 即左孩子这边也是左边更高或等高

**LR**

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/270d617c0d604a27945dd0878badde7d~tplv-k3u1fbpfcp-zoom-1.image)

*   失衡节点（图中 8）的 bf > 1，即左边更高
*   失衡节点的左孩子（图中 6 红色）的 bf < 0 即左孩子这边是右边更高

对称的还有两种情况

**RL**

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d4ba3fe4eeb24d8c832f7299b5ccb3d1~tplv-k3u1fbpfcp-zoom-1.image)

*   失衡节点（图中 3）的 bf <-1，即右边更高
*   失衡节点的右孩子（图中 6 红色）的 bf > 0，即右孩子这边左边更高

**RR**

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ec628da127364fc2bc1ad744f9e4b22b~tplv-k3u1fbpfcp-zoom-1.image)

*   失衡节点（图中 3）的 bf <-1，即右边更高
*   失衡节点的右孩子（图中 6 红色）的 bf <= 0，即右孩子这边右边更高或等高

#### 解决失衡

失衡可以通过树的旋转解决。什么是树的旋转呢？它是在不干扰元素顺序的情况下更改结构，通常用来让树的高度变得平衡。

观察下面一棵二叉搜索树，可以看到，旋转后，并未改变树的左小右大特性，但根、父、孩子节点都发生了变化

          4                                   2
         / \             4 right             / \
        2   5      -------------------->    1   4
       / \         <--------------------       / \
      1   3              2 left               3   5

**右旋**

旋转前

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7273e5874cb1499eb3b1dae9ec4c30e3~tplv-k3u1fbpfcp-zoom-1.image)

*   红色节点，旧根（失衡节点）
*   黄色节点，旧根的左孩子，将来作为新根，旧根是它右孩子
*   绿色节点，新根的右孩子，将来要换爹作为旧根的左孩子

旋转后

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/eb7c05c610f9420bbaeebc6523ad1b52~tplv-k3u1fbpfcp-zoom-1.image)

代码

```java
private AVLNode rightRotate(AVLNode red) {
    AVLNode yellow = red.left;
    AVLNode green = yellow.right;
    yellow.right = red;
    red.left = green;
    return yellow;
}
```

**左旋**

旋转前

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/23c025511b8f45a5a464bbeccc799512~tplv-k3u1fbpfcp-zoom-1.image)

*   红色节点，旧根（失衡节点）
*   黄色节点，旧根的右孩子，将来作为新根，旧根是它左孩子
*   绿色节点，新根的左孩子，将来要换爹作为旧根的右孩子

旋转后

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8e03c8e16b784219853e84833ff511fa~tplv-k3u1fbpfcp-zoom-1.image)

代码

```java
private AVLNode leftRotate(AVLNode red) {
    AVLNode yellow = red.right;
    AVLNode green = yellow.left;
    yellow.left = red;
    red.right = green;
    return yellow;
}
```

**左右旋**

指先左旋左子树，再右旋根节点（失衡），这时一次旋转并不能解决失衡

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/62fa59af78ea474892245b2a15a14bc3~tplv-k3u1fbpfcp-zoom-1.image)

左子树旋转后

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2d287db4c0ef4612907de6bb404c91e9~tplv-k3u1fbpfcp-zoom-1.image)

根右旋前

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d4dfb154eb3546ceb68b3a1c11120b3a~tplv-k3u1fbpfcp-zoom-1.image)

根右旋后

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c3fbbb3fdb034d7690c3f9b9f74907e0~tplv-k3u1fbpfcp-zoom-1.image)

代码

```java
private AVLNode leftRightRotate(AVLNode root) {
    root.left = leftRotate(root.left);
    return rightRotate(root);
}
```

**右左旋**

指先右旋右子树，再左旋根节点（失衡）

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3438a0b1a12242f78d423ea50cacb521~tplv-k3u1fbpfcp-zoom-1.image)

右子树右旋后

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/06b9bfa5608a4cc6b6a34596b0647c94~tplv-k3u1fbpfcp-zoom-1.image)

根左旋前

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2f4c5b6b49bb46278c79c825fdc515ba~tplv-k3u1fbpfcp-zoom-1.image)

根左旋后

![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/dd8d278444814d56aef4c3840ce8e65d~tplv-k3u1fbpfcp-zoom-1.image)

代码

```java
private AVLNode rightLeftRotate(AVLNode root) {
    root.right = rightRotate(root.right);
    return leftRotate(root);
}
```

**判断及调整平衡代码**

```java
private AVLNode balance(AVLNode node) {
    if (node == null) {
        return null;
    }
    int bf = bf(node);
    if (bf > 1 && bf(node.left) >= 0) {
        return rightRotate(node);
    } else if (bf > 1 && bf(node.left) < 0) {
        return rightLeftRotate(node);
    } else if (bf < -1 && bf(node.right) > 0) {
        return leftRightRotate(node);
    } else if (bf < -1 && bf(node.right) <= 0) {
        return rightRotate(node);
    }
    return node;
}
```

以上四种旋转代码里，都需要更新高度，需要更新的节点是红色、黄色，而绿色节点高度不变

#### 新增

```java
public void put(int key, Object value) {
    root = doPut(root, key, value);
}

private AVLNode doPut(AVLNode node, int key, Object value) {
    if (node == null) {
        return new AVLNode(key, value);
    }
    if (key == node.key) {
        node.value = value;
        return node;
    }
    if (key < node.key) {
        node.left = doPut(node.left, key, value);
    } else {
        node.right = doPut(node.right, key, value);
    }
    updateHeight(node);
    return balance(node);
}
```

#### 删除

```java
public void remove(int key) {
    root = doRemove(root, key);
}

private AVLNode doRemove(AVLNode node, int key) {
    if (node == null) {
        return null;
    }
    if (key < node.key) {
        node.left = doRemove(node.left, key);
    } else if (node.key < key) {
        node.right = doRemove(node.right, key);
    } else {
        if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        } else {
            AVLNode s = node.right;
            while (s.left != null) {
                s = s.left;
            }
            s.right = doRemove(node.right, s.key);
            s.left = node.left;
            node = s;
        }
    }
    if (node == null) {
        return null;
    }
    updateHeight(node);
    return balance(node);
}
```

完整代码备份

```java
public class AVLTree {
    static class AVLNode {
        int height = 1;
        int key;
        Object value;
        AVLNode left;
        AVLNode right;

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    AVLNode root;

    private AVLNode leftRotate(AVLNode p) {
        AVLNode r = p.right;
        AVLNode b = r.left;
        r.left = p;
        p.right = b;
        updateHeight(p);
        updateHeight(r);
        return r;
    }

    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    private AVLNode rightRotate(AVLNode r) {
        AVLNode a = r.left;
        AVLNode b = a.right;
        a.right = r;
        r.left = b;
        updateHeight(r);
        updateHeight(a);
        return a;
    }

    private AVLNode leftRightRotate(AVLNode p) {
        AVLNode r = p.left;
        p.left = leftRotate(r);
        return rightRotate(p);
    }

    private AVLNode rightLeftRotate(AVLNode p) {
        AVLNode r = p.right;
        p.right = rightRotate(r);
        return leftRotate(p);
    }

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }



    public void remove(int key) {
        root = doRemove(root, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (node.key < key) {
            node.right = doRemove(node.right, key);
        } else {
            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }
        if (node == null) {
            return null;
        }
        updateHeight(node);
        return balance(node);
    }

    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }

    private AVLNode doPut(AVLNode node, int key, Object value) {
        if (node == null) {
            return new AVLNode(key, value);
        }
        if (key == node.key) {
            node.value = value;
            return node;
        }
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else {
            node.right = doPut(node.right, key, value);
        }
        updateHeight(node);
        return balance(node);
    }

    private int bf(AVLNode node) {
        return height(node.left) - height(node.right);
    }

    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = bf(node);
        if (bf > 1 && bf(node.left) >= 0) {
            return rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) {
            return rightLeftRotate(node);
        } else if (bf < -1 && bf(node.right) > 0) {
            return leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) <= 0) {
            return rightRotate(node);
        }
        return node;
    }
}
```
