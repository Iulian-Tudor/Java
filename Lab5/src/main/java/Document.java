import java.util.HashMap;
import java.util.Map;

class Document {
    private String id;
    private String name;
    private String location;
    private Map<String, String> tags;

    public Document(String id, String name, String location, String... tagPairs) {
        this.id = id;
        this.name = name;
        this.location = location;
        tags = new HashMap<>();
        for (String tagPair : tagPairs) {
            String[] parts = tagPair.split(":");
            if (parts.length == 2) {
                tags.put(parts[0], parts[1]);
            }
        }
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID: %s, Name: %s, Location: %s\n", id, name, location));
        for (String key : tags.keySet()) {
            sb.append(String.format("%s: %s\n", key, tags.get(key)));
        }
        return sb.toString();
    }
}