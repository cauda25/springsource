package ch1;

// 변수

// 1) 맴버 변수 : 클래스 안에 선언되 변수
//                객체인 경우 null로 초기화
//                기본 타입 변수인 경우 0 or '' or 0.0 ,false 초기화
// 2) 지역 변수 : 메소드나 {} 안에 선언된 변수
// 3) 매개 변수 : powerOn(int x) => x를 부를 때

public class SamsungTV implements TV {

    // 맴버변수(==인스턴스 변수, 필드,특성,속성)
    private Speaker speaker;

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void powerOn() {
        System.out.println("전원 on");
    }

    @Override
    public void powerOff() {
        System.out.println("전원 off");
    }

    @Override
    public void volumup() {
        // System.out.println("음량 up");
        speaker.volumup();
    }

    @Override
    public void volumDown() {
        // System.out.println("음량 down");
        speaker.volumDown();
    }

}
