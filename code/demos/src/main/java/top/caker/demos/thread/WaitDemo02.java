package top.caker.demos.thread;

/**
 * @author cakeralter
 * @date 2020/4/22
 */
public class WaitDemo02 {

    public static void main(String[] args) {
        Site site = new Site();

        for (int i = 0; i < 5; i++) {
            new Thread(new User(String.valueOf(i), site)).start();
        }

        new Thread(new Programmer(site)).start();
    }

    static class Site {

        private final Object lock = new Object();
        private volatile boolean status;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public Object getLock() {
            return lock;
        }
    }

    static class Programmer implements Runnable {

        private final Site site;

        public Programmer(Site site) {
            this.site = site;
        }

        @Override
        public void run() {
            synchronized (site.getLock()) {
                System.out.println("程序员获取权限，正在加班修复");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                site.setStatus(true);
                site.getLock().notifyAll();
                System.out.println("程序员修复完毕，释放权限");
            }
        }
    }

    static class User implements Runnable {

        private final String name;
        private final Site site;

        public User(String name, Site site) {
            this.name = name;
            this.site = site;
        }

        @Override
        public void run() {
            synchronized (site.getLock()) {
                System.out.println(name + "获取权限");
                while (!site.isStatus()) {
                    try {
                        site.getLock().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name + "浏览完毕，释放权限");
            }
        }
    }
}
