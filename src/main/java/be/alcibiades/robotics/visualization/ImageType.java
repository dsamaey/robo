package be.alcibiades.robotics.visualization;

public enum ImageType {

    PNG("png");

    private String typeName;

    ImageType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
