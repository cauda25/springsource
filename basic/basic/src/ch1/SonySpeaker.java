package ch1;

public class SonySpeaker implements Speaker {

    @Override
    public void volumup() {
        System.out.println("SonySpeaker volumup");
    }

    @Override
    public void volumDown() {
        System.out.println("SonySpeaker volumDown");
    }

}
