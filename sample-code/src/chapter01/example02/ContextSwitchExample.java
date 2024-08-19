package chapter01.example02;

/**
 * Package : chapter01.example02 <p>
 * Class   : ContextSwitchExample <p>
 * Description :  <p>
 *
 * <pre>
 *  수정일					  수정자				  수정 내용
 *  ---------------           ------------        -----------------
 *  2024-08-19				  cmpark			     최초작성
 *
 *
 * </pre>
 *
 * @author cmpark
 * @version 1.0.0
 * @since 2024-08-19
 */
public class ContextSwitchExample {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1 : " + i);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 2 : " + i);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 3 : " + i);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // CPU에 의해서 실행되기 때문에 스레드1, 스레드2, 스레드3이 순서대로 실행되지 않음
        // CPU가 컨텍스트 스위칭을 일으켜 가면서 수행
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
