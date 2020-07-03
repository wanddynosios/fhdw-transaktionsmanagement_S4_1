package util;

public class Semaphore {
        private long count;
        public Semaphore(long count){
            this.count = count;
        }
        public synchronized void up(){
            count++;
            this.notify();
        }
        public synchronized void down() throws InterruptedException {
            while (count == 0){
                this.wait();
            }
            count--;
        }

}
