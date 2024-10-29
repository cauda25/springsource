package lambda;

// interface 선언
// new 를 직접적으로 할 수 없음
// new 를 못하면 
// 1) 구현 클래스 사용
// 2) 익명 구현 클래스 사용

@FunctionalInterface // 일반 메소드가 2개 이상 들어오는 걸 컴파일 단계에서 체크
interface MyFunctionallnterface1 {
    void method();

    // void print();
    static void print1() {
    }

    default void print2() {
    }
}

public class LambdaEx2 {
    public static void main(String[] args) {
        // MyFunctionallnterface1 obj = new MyFunctionallnterface1() {

        // @Override
        // public void method() {
        // System.out.println("인터페이스 구현");
        // }
        // };
        // obj.method();

        // 익명 구현 객체를 식으로 작성 : 람다식
        MyFunctionallnterface1 obj = () -> System.out.println("인터페이스 구현");
        obj.method();

        obj = () -> {
            int i = 10;
            System.out.println(i * i);
        };
        obj.method();
    }
}
