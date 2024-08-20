package chapter01.example03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Package : chapter01.example03 <p>
 * Class   : CPUBoundExample <p>
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
public class CPUBoundExample {

    public static void main(String[] args) {
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount); // thread 생성

        long startTime = System.currentTimeMillis();
        List<Future<?>> futures = new ArrayList<>(); // thread 작업 수행 후 마지막 결과를 반환하는 클래스

        for (int i = 0; i < threadCount; i++) {
            Future<?> future = executorService.submit(() -> {

                // CPU 연산이 집중되고 왜 걸리는 작업이라고 가정
                long result = 0;
                for (long j = 0; j < 1000000000L; j++) {
                    result += j;
                }

                // 잠시 대기
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // CPU Bound 일 때 Context Switching
                System.out.println("스레드 : " + Thread.currentThread().getName() + ", " + result);
            });
            futures.add(future);
        }

        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        long endTime = System.currentTimeMillis();
        System.out.println("CPU 개수를 초과하는 데이터를 병렬로 처리하는 데 걸린 시간 : " + (endTime - startTime) + "ms");
        executorService.shutdown();
    }
}
