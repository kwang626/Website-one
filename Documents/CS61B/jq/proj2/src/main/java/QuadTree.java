
import java.util.*;


/**
 * Created by Kevin on 7/15/16.
 */
public class QuadTree extends QTreeNode{

    protected QTreeNode root;
    public static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;


    public QuadTree() {
        root = new QTreeNode("root");
        root.coords.put("ullat", ROOT_ULLAT);
        root.coords.put("ullon", ROOT_ULLON);
        root.coords.put("lrlat", ROOT_LRLAT);
        root.coords.put("lrlon", ROOT_LRLON);
        double middlelon = root.coords.get("lrlon") - root.coords.get("ullon");
        double middlelat = root.coords.get("ullat") - root.coords.get("lrlat");
        middlelon = middlelon / 2;
        middlelat = middlelat / 2;
        middlelon = middlelon + root.coords.get("ullon");
        middlelat = middlelat + root.coords.get("lrlat");
        root.one = new QTreeNode("1");
        root.one.coords.put("ullon", root.coords.get("ullon"));
        root.one.coords.put("ullat", root.coords.get("ullat"));
        root.one.coords.put("lrlon", middlelon);
        root.one.coords.put("lrlat", middlelat);
        root.one.instantiate();

        root.two = new QTreeNode("2");
        root.two.coords.put("ullon", middlelon);
        root.two.coords.put("ullat", root.coords.get("ullat" ));
        root.two.coords.put("lrlon", root.coords.get("lrlon"));
        root.two.coords.put("lrlat", middlelat);
        root.two.instantiate();

        root.three = new QTreeNode("3");
        root.three.coords.put("ullon", root.coords.get("ullon"));
        root.three.coords.put("ullat", middlelat);
        root.three.coords.put("lrlon", middlelon);
        root.three.coords.put("lrlat", root.coords.get("lrlat"));
        root.three.instantiate();

        root.four = new QTreeNode("4");
        root.four.coords.put("ullon", middlelon);
        root.four.coords.put("ullat", middlelat);
        root.four.coords.put("lrlon", root.coords.get("lrlon"));
        root.four.coords.put("lrlat", root.coords.get("lrlat"));
        root.four.instantiate();
    }

    public void print() {
        if (root != null) {
            root.print(0);
        }
    }



}
