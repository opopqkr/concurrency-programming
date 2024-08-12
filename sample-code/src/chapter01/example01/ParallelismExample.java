package chapter01.example01;

import java.util.ArrayList;
import java.util.List;

/**
 * Package : chapter01.example01 <p>
 * Class   : ParallelismExample <p>
 * Description :  <p>
 *
 * <pre>
 *  수정일					  수정자				  수정 내용
 *  ---------------           ------------        -----------------
 *  2024-08-12				  cmpark			     최초작성
 *
 *
 * </pre>
 *
 * @author cmpark
 * @version 1.0.0
 * @since 2024-08-12
 */
public class ParallelismExample {

    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        System.out.println("현재 CPU CORE 수: " + cpuCores);

        // CPU Core 수 만큼 작업 생성
        List<Integer> jobs = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            jobs.add(i);
        }

        long startTime = System.currentTimeMillis();

        // CPU Core 갯수만큼 데이터를 병렬 처리
        long sum = jobs.parallelStream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return ((long) i * i);
                }).sum();

        long endTime = System.currentTimeMillis();

        System.out.println("CPU 갯수만큼 데이터를 병렬로 처리하는 시간: " + (endTime - startTime) + "ms");
        System.out.println("결과: " + sum);
    }
}
