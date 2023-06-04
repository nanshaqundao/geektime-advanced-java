package org.example.sample04;

import org.openjdk.jol.info.ClassLayout;

import java.util.Arrays;

public class ObjectLock03 {
    public static void main(String[] args) {
        ObjectLock03.Hero a = new ObjectLock03.Hero();
        System.out.println("new hero: " + ClassLayout.parseInstance(a).toPrintable());
        a.setFlag(true);
        a.setI(1);
        a.setStr("hello");
        boolean[] flagArray = a.getFlagArray();
        flagArray[0] = true;
        System.out.println("hero after set: " + ClassLayout.parseInstance(a).toPrintable());
    }

    static class Hero {
        private boolean flag;
        private boolean[] flagArray = new boolean[100];
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

        public boolean isFlag() {
            return flag;
        }

        public boolean[] getFlagArray() {
            return flagArray;
        }

        public int getI() {
            return i;
        }

        public String getStr() {
            return str;
        }

        public void setFlagArray(boolean[] flagArray) {
            this.flagArray = flagArray;
        }

        public void addFlagArray(boolean flagToAdd){
            Arrays.fill(flagArray, flagToAdd);
        }
    }
}
