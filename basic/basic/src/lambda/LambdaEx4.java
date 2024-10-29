package lambda;

import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// 함수형 인터페이스(@FunctionalInterface)
// 메소드는 프로트타입이 거의 일정하기 때문에 자주 쓰이는 형식의 메소드를 가지고 있는 함수형 인터페이스 선언 가능
// => java.util.function 패키지에 정의함
public class LambdaEx4 {
    public static void main(String[] args) {
        Supplier<Integer> s = () -> (int) (Math.random() * 100) + 1;
        System.out.println("1~100 사이의 임의의 수 " + s.get());

        // System.out.println(LocalDateTime.now());
        Supplier<LocalDateTime> s1 = () -> LocalDateTime.now();
        System.out.println(s1.get());

        Consumer<Integer> c = (i) -> System.out.println(i * i);
        c.accept(5);

        // String => 화면 출력
        Consumer<String> c1 = (str) -> System.out.println(str);
        c1.accept("안녕하세요");

        Consumer<LocalDateTime> c2 = (ldt) -> System.out.println(ldt);
        c2.accept(LocalDateTime.now());

        Function<Integer, Integer> f1 = (i) -> i * i;
        System.out.println(f1.apply(10));

        // 일의 자리 없앤 후 리턴
        f1 = (i) -> i / 10 * 10;
        System.out.println(f1.apply(561));

        // Function<String, Integer> f2 = (i) -> Integer.parseInt(i);
        // Function<String, Integer> f2 = (i) -> Integer.valueOf(i);
        Function<String, Integer> f2 = Integer::parseInt;
        System.out.println(f2.apply("78"));

        Predicate<Integer> p1 = (i) -> i > 10;
        System.out.println(p1.test(24) ? "10보다 큼" : "10보다 작음");

        // 문자열 길이가 6자리 보다 큰가
        Predicate<String> p2 = (i) -> i.length() > 6;
        System.out.println(p2.test("abcdefg") ? "문자열의 6자리 초과" : "문자열의 6자리 미만");

        // 문자열에 대문자 A 의 포함여부
        p2 = (str) -> str.contains("a");
        System.out.println(p2.test("abcdefg") ? "A 포함" : "A 미포함");

        BiFunction<Integer, Integer, Integer> function = (x, y) -> x * y;
        System.out.println(function.apply(5, 7));

        function = (x, y) -> x > y ? x : y;
        System.out.println(function.apply(5, 3));

        // 두 개의 String을 받아서 연결 후 리턴
        BiFunction<String, String, String> function1 = (x, y) -> x.concat(y); // x+y
        System.out.println(function1.apply("java8", "람다"));

    }

}
