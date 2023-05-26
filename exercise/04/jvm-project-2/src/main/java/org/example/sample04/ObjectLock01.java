package org.example.sample04;

import org.openjdk.jol.info.ClassLayout;

public class ObjectLock01 {
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println("new object: " + ClassLayout.parseInstance(object).toPrintable());
    }
}
