package chapter01.example03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Package : chapter01.example03 <p>
 * Class   : IOBoundExample <p>
 * Description :  <p>
 *
 * <pre>
 *  수정일					  수정자				  수정 내용
 *  ---------------           ------------        -----------------
 *  2024-08-20				  cmpark			     최초작성
 *
 *
 * </pre>
 *
 * @author cmpark
 * @version 1.0.0
 * @since 2024-08-20
 */
public class IOBoundExample {

    public static void main(String[] args) {
        int threadCount = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount); // thread 생성

        for (int i = 0; i < threadCount; i++) { // CPU 연산 작업이 많은 경우 모든 스레드를 활용
            executorService.submit(() -> {
                try {
                    // I/O가 집중되는 작업
                    for (int j = 0; j < 5; j++) {
                        // I/O 바운드 일 경우 CPU는 대기, 그러므로 작업 갯수를 더 주어서 CPU가 Idle 되지 않도록 해야 함 (동시적으로 처리)
                        Files.readAllLines(Path.of("sumarry/4. CPU Bound & IO Bound.md"));
                        // I/O Bound 일 때 Context Switching
                        System.out.println("스레드 : " + Thread.currentThread().getName() + ", " + j);
                    }

                    // 아주 빠른 CPU 연산
                    int result = 0;
                    for (int j = 0; j < 10; j++) {
                        result += j;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}
