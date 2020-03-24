package graphics;

public abstract class Asset {

    private final String name;

    public Asset(final String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
