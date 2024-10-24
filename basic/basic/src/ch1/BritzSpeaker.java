package ch1;

public class BritzSpeaker implements Speaker {

    @Override
    public void volumup() {
        System.out.println("BritzSpeaker volumup");
    }

    @Override
    public void volumDown() {
        System.out.println("BritzSpeaker volumDown");
    }

}
