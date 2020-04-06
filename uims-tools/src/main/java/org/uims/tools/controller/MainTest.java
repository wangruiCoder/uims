package org.uims.tools.controller;

public class MainTest {
    public static void main(String[] args) {
        String src = "北京分行|00001|98.5|456|1980|3439993101|Gtxxcs|nifi";
        String target = "|";
        String replacement = ",";

        long startTime = System.currentTimeMillis();
        for (int i=0;i<10000000;i++){
            src.replace("|",",");
            replaceAllStr(src,target,replacement);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗费:"+(endTime-startTime));
    }

    public static String replaceAllStr(String src, String target, String replacement) {
        if (src == null || target == null || replacement == null) return src;
        int idx = src.indexOf(target);
        if (idx == -1) return src;
        int pst = 0;
        char[] cs = src.toCharArray();
        char[] rs = new char[src.length() - target.length() + replacement.length()];
        for (int i = 0; i < cs.length; i ++) {
            if (i == idx) {
                for (char c : replacement.toCharArray()) {
                    rs[pst] = c;
                    pst ++;
                }
                continue;
            }
            if (i > idx && i < idx + target.length()) continue;
            rs[pst] = cs[i];
            pst ++;
        }
        return replaceAllStr(new String(rs), target, replacement);
    }
}