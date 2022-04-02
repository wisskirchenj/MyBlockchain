package topics.patterns.abstractfactory.phone;

class IphoneSecurity implements Security {
    @Override
    public String toString() {
        return "FaceID";
    }
}
