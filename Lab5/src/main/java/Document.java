import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Document {
    private String id;
    private String name;
    private String location;
    private Map<String, String> tags;

    //JsonProperty indica ce date merg in ce camp in momentul in care se face save
    public Document(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("location") String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.tags = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void addTag(String name, String value) {
        tags.put(name, value);
    }

    public String toString() {
        return name;
    }
}
