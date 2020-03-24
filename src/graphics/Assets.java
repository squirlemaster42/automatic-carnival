package graphics;

import java.util.HashMap;
import java.util.Map;

public class Assets {

    private static Assets instance;

    public Assets getInstance(){
        if(instance == null){
            instance = new Assets();
        }
        return instance;
    }

    private final Map<String, Asset> assets;

    private Assets(){
        assets = new HashMap<>();
    }

    public void addAsset(final Asset asset){
        assets.put(asset.getName(), asset);
    }
 
}
