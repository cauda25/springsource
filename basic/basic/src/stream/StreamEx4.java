package stream;

import java.util.List;
import java.util.stream.IntStream;

public class StreamEx4 {

    List<String> list;

    public static void main(String[] args) {
        IntStream stream = IntStream.rangeClosed(1, 10);
        // 2의 배수 개수
        Long cnt = stream.filter(i -> i % 2 == 0).count();
        System.out.println("2의 배수 개수 " + cnt);

        stream = IntStream.rangeClosed(1, 10);
        System.out.println("2의 배수 합" + stream.filter(i -> i % 2 == 0).sum());

        stream = IntStream.rangeClosed(1, 10);
        System.out.println("2의 배수 합" + stream.filter(i -> i % 2 == 0).average());

        stream = IntStream.rangeClosed(1, 10);
        System.out.println("2의 배수 합" + stream.filter(i -> i % 2 == 0).max());

        stream = IntStream.rangeClosed(1, 10);
        System.out.println("2의 배수 합" + stream.filter(i -> i % 2 == 0).min());

        // Optional : NullPointerException

    }
}
