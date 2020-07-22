package src.models;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private Float size;
    private List<Vertex> relatives;
    public Vertex(Float size) {
        this.size = size;
        this.relatives = new ArrayList<>();
    }
    public void addRelative(Vertex relative) {
        if (relative != null) { relatives.add(relative); }
    }
    public void removeRelative(Vertex relative) {
        if (relative != null) { relatives.remove(relative); }
    }
    public List<Vertex> getRelatives() {
        return relatives;
    }
    public void setRelatives(List<Vertex> relatives) { this.relatives = relatives; }
    public Float getSize() { return size; }

    @Override
    public String toString() {
        String str = "Edge with size: " + size;
        if (relatives.isEmpty()) { return str; }
        str += "; Relatives -";
        for (Vertex n : relatives) {
            str += "(" + n.getSize() + ")" + "-";
        }
        return str;
    }
}
