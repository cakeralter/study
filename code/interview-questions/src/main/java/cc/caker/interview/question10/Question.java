package cc.caker.interview.question10;

/**
 * @author cakeralter
 * @date 2020/8/1
 */
public class Question {

    public static void main(String[] args) {
        String str = null;
        StringBuffer buffer = new StringBuffer();
        buffer.append(str);

        // 4
        System.out.println(buffer.length());
        // null
        System.out.println(buffer);

        /*
            public StringBuffer(String str) {
                super(str.length() + 16);
                append(str);
            }
         */
        // NullPointerException
        StringBuffer stringBuffer = new StringBuffer(str);
        System.out.println(stringBuffer);
    }
}
