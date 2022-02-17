package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 735. 行星碰撞
 *
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 * 思路1 转换为链表结构双指针
 */
public class L735PlanetCollision {

    public static void main(String[] args) {

        asteroidCollision(new int[]{10,2, -5});
    }

    public static int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length < 2) {
            return asteroids;
        }

        int len = asteroids.length;
        int left = 0;
        int right = 1;
        while (left > 0 && right < len) {
            if (asteroids[left] <= 0) {
                left++;
                right++;
                continue;
            }

            if (asteroids[left] > 0 && asteroids[right] > 0) {
                left++;
                right++;
                continue;
            }

            if (asteroids[left] + asteroids[right] > 0) {
                asteroids[right] = 0;
                right++;
            } else if (asteroids[left] + asteroids[right] < 0) {
                asteroids[left] = 0;
                left--;
            } else {
                asteroids[left] = 0;
                asteroids[right] = 0;
                left--;
                right++;
            }
        }

        int size = 0;
        for (int asteroid : asteroids) {
            if (asteroid > 0) {
                size++;
            }
        }

        int[] results = new int[size];
        int i = -1;
        for (int asteroid : asteroids) {
            if (asteroid != 0) {
                results[++i] = asteroid;
            }
        }

        return results;
    }

//    public int[] asteroidCollision(int[] asteroids) {
//        if (asteroids == null || asteroids.length < 2) {
//            return asteroids;
//        }
//
//        Node firstNode = new Node(asteroids[0]);
//        Node curNode = firstNode;
//        for (int asteroid : asteroids) {
//            Node node = new Node(asteroid);
//            curNode.next = node;
//            node.pre = curNode;
//            curNode = node;
//        }
//
//        curNode = firstNode;
//        while (true) {
//            if (curNode.next == null) {
//                break;
//            }
//
//            if (curNode.val > 0 && curNode.next.val > 0) {
//                curNode = curNode.next;
//                continue;
//            }
//
//            // 两者相反
//            if (curNode.val + curNode.next.val > 0) {
//                curNode.next = curNode.next.next;
//                curNode.next.pre = curNode;
//            } else if (curNode.val + curNode.next.val < 0) {
//                curNode.pre.next = curNode.next;
//                curNode.next.pre = curNode.pre;
//                curNode = curNode.pre;
//            } else {
//                Node tempNode = curNode.pre;
//                curNode.pre.next = curNode.next.next;
//                curNode.next.next.pre = tempNode;
//                curNode = tempNode;
//            }
//        }
//
//
//        List<Integer> result = new ArrayList<>();
//        while (firstNode != null) {
//            result.add(firstNode.val);
//        }
//
//        Integer[] resInts = result.toArray(new Integer[0]);
//        return resInts;
//    }

    class Node implements Comparable {
        public Node(Integer val) {
            this.val = val;
        }

        private Integer val;
        private Node next;
        private Node pre;

        @Override
        public int compareTo(Object o) {
            Node cmpNode = ((Node) o);
            if (cmpNode.val == this.val) {
                return 0;
            } else if (this.val > cmpNode.val) {
                return 1;
            }

            return -1;
        }

        public Integer getVal() {
            return val;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }
    }

}
