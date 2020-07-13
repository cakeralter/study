package top.caker.demos.thread;

/**
 * @author cakeralter
 * @date 2020/5/12
 */
public class Test {

    static class Demo {
        private boolean flag = false;

        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println(Thread.currentThread().getName());
        }

        public boolean isFlag() {
            return flag;
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(demo::run).start();

        while (true) {
            if (demo.isFlag()) {
                System.out.println("--------");
                break;
            }
        }
    }
}
