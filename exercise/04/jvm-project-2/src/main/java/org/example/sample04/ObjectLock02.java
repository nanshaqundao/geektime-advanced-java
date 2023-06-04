package org.example.sample04;

import org.openjdk.jol.info.ClassLayout;

public class ObjectLock02 {
    public static void main(String[] args) {
        Hero a = new Hero();
        System.out.println("new hero: " + ClassLayout.parseInstance(a).toPrintable());
        a.setFlag(true);
        a.setI(1);
        a.setStr("hello");
        System.out.println("hero after set: " + ClassLayout.parseInstance(a).toPrintable());
    }

    static class Hero {
        private boolean flag;
        private boolean flag2;
        private int i;
        private String str;

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public void setI(int i) {
            this.i = i;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
