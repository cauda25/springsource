package ch1;

public class TVMain {
    public static void main(String[] args) {
        // LgTV lgTV = new LgTV();
        // lgTV.setBritzSpeaker(new BritzSpeaker());

        // lgTV.powerOn();
        // lgTV.volumup();
        // lgTV.volumDown();
        // lgTV.powerOff();

        // SamsungTV samsungTV = new SamsungTV();
        // samsungTV.setBritzSpeaker(new BritzSpeaker());
        // samsungTV.powerOn();
        // samsungTV.volumup();
        // samsungTV.volumDown();
        // samsungTV.powerOff();

        TV tv = new SamsungTV();
        // ((SamsungTV) tv).setSpeaker(new SonySpeaker());

        tv = new LgTV();
        ((LgTV) tv).setSpeaker(new SonySpeaker());

        tv.powerOn();
        tv.volumup();
        tv.volumDown();
        tv.powerOff();
    }
}
