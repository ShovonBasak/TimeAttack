package Root.Settings;


public class Sound {
    private double MaxSound;
    private Boolean SFX;
    private Boolean BGM;

    public double getMaxSound() {
        return MaxSound;
    }

    public void setMaxSound(double maxSound) {
        MaxSound = maxSound;
    }

    public Boolean getSFX() {
        return SFX;
    }

    public void setSFX(Boolean SFX) {
        this.SFX = SFX;
    }

    public Boolean getBGM() {
        return BGM;
    }

    public void setBGM(Boolean BGM) {
        this.BGM = BGM;
    }
}
