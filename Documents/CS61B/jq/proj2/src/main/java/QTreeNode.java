import java.util.*;

/**
 * Created by Kevin on 7/16/16.
 */
public class QTreeNode {

    public static void main(String[] args) {
    }

    public QTreeNode(){}


    public String name;
    public HashMap<String, Double> coords;
    public QTreeNode one;
    public QTreeNode two;
    public QTreeNode three;
    public QTreeNode four;



    public QTreeNode(String item) {
        this.name = item;
        one = two = three = four = null;
        coords = new HashMap<>();
    }

    public QTreeNode(String item, QTreeNode first,
                     QTreeNode second, QTreeNode third, QTreeNode fourth, QTreeNode parent) {
        name = item;
        one = first;
        two = second;
        three = third;
        four = fourth;
        parent = parent;
    }

    public boolean reachedLimit() {
        if (name.length() >= 7) {
            return true;
//        } else if (name.charAt(name.length() - 1) == '4' && name.length() == 7) {
//            return true;
        } else {
            return false;
        }
    }

    public double tileRatio() {
        double temp = coords.get("lrlon") - coords.get("ullon");
        temp = temp / 256;
//        System.out.println("current tile is : " + name);
//        System.out.println("current tile ratio is : " + temp);
        return temp;
    }

    public ArrayList<QTreeNode> getAllTiles(Map<String, Double> input, double target) {
        ArrayList<QTreeNode> result = new ArrayList<QTreeNode>();
//        System.out.println("getAllTiles ullon = " + input.get("ullon"));
//        System.out.println("getAllTiles ullat = " + input.get("ullat"));
//        System.out.println("getAllTiles lrlon = " + input.get("lrlon"));
//        System.out.println("getAllTiles lrlat = " + input.get("lrlat"));
        search(input, target, result);
        return result;
    }

    public void sort(ArrayList<QTreeNode> list) {
        Collections.sort(list, blackMagic);
        ArrayList<String> result = new ArrayList();
    }
//        double ullon = input.get("ullon");
//        double ullat = input.get("ullat");
//        double lrlon = input.get("lrlon");
//        double lrlat = input.get("lrlat");
//        double height = ullat - lrlat;
//        double width = lrlon - ullon;
//        double tileHeight = list.get(0).coords.get("ullat") - list.get(0).coords.get("lrlat");
//        double tileWidth = list.get(0).coords.get("lrlon") - list.get(0).coords.get("ullon");
//        System.out.println("ullon = " + ullon);
//        System.out.println("ullat = " + ullat);
//        System.out.println("lrlon = " + lrlon);
//        System.out.println("lrlat = " + lrlat);
//        System.out.println("tileHeight = " + tileHeight);
//        System.out.println("tileWidth = " + tileWidth);
//        System.out.println("box height = " + height);
//        System.out.println("box width = " + width);
//        System.out.println(width / tileWidth);
//        System.out.println(rowCounter);
//        for (int x = 0; x < list.size(); x++) {
//            System.out.println(list.get(x).name);
//        }

    // use sublist to split up the rows and then use the other comparator to order all of them then bring the
    //lists back together.
    public Comparator<QTreeNode> sortLon = new Comparator<QTreeNode>() {
        @Override
        public int compare(QTreeNode o1, QTreeNode o2) {
            double lon1 = o1.coords.get("ullon");
            double lon2 = o2.coords.get("ullon");
            if (lon1 > lon2) {
                return 1;
            } if (lon2 > lon1) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    public Comparator<QTreeNode> sortLat = new Comparator<QTreeNode>() {
        @Override
        public int compare(QTreeNode o1, QTreeNode o2) {
            double lat1 = o1.coords.get("ullat");
            double lat2 = o2.coords.get("ullat");
            if (lat1 > lat2) {
                return -1;
            } if (lat2 > lat1) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    public Comparator<QTreeNode> blackMagic = new Comparator<QTreeNode>() {
        @Override
        public int compare(QTreeNode o1, QTreeNode o2) {
            if (o1.coords.get("ullon") == o2.coords.get("ullon")) {
                if (o1.coords.get("ullat") > o2.coords.get("ullat")) {
                    return -1;
                } else if (o1.coords.get("ullat") < o2.coords.get("ullat")) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (o1.coords.get("ullat") == o2.coords.get("ullat")) {
                if (o1.coords.get("ullon") < o2.coords.get("ullon")) {
                    return -1;
                } else if (o1.coords.get("ullon") > o2.coords.get("ullon")) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (o1.coords.get("ullat") > o2.coords.get("ullat")) {
                return -1;
            } else if (o1.coords.get("ullat") < o2.coords.get("ullat")) {
                return 1;
            } else {
                return 0;
            }
        }
    };



    public void search(Map<String, Double> input, double target, ArrayList<QTreeNode> tiles) {
        if (this.contains(input)) {
            if (tileRatio() <= target || one == null) {
                if (!tiles.contains(this)) {
//                    System.out.println("added tile : " + name);
                    tiles.add(this);
                    return;
                }
            }
        }
        if (one != null) {
            if (one.contains(input)) {
                one.search(input, target, tiles);
            }
        }
        if (two != null) {
            if (two.contains(input)) {
                two.search(input, target, tiles);
            }
        }
        if (three != null) {
            if (three.contains(input)) {
                three.search(input, target, tiles);
            }
        }
        if (four != null) {
            if (four.contains(input)) {
                four.search(input, target, tiles);
            }
        }
        return;
    }

//    public boolean containsTopLeft(Map<String, Double> input) {
//        if (coords.get("ullon") < input.get("ullon") && coords.get("lrlon") > input.get("ullon")) {
//            if (coords.get("ullat") > input.get("ullat") && coords.get("lrlat") < input.get("ullat")) {
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean contains(Map<String, Double> input) {
//        System.out.println("coord ullon = " + coords.get("ullon"));
//        System.out.println("coord ullat = " + coords.get("ullat"));
//        System.out.println("coord lrlon = " + coords.get("lrlon"));
//        System.out.println("coord lrlat = " + coords.get("lrlat"));
//        System.out.println("input ullon = " + input.get("ullon"));
//        System.out.println("input ullat = " + input.get("ullat"));
//        System.out.println("input lrlon = " + input.get("lrlon"));
//        System.out.println("input lrlat = " + input.get("lrlat"));
//        System.out.println("");

        double queryUllon = input.get("ullon");
        double queryUllat = input.get("ullat");
        double queryLrlon = input.get("lrlon");
        double queryLrLat = input.get("lrlat");
        double ullon = coords.get("ullon");
        double ullat = coords.get("ullat");
        double lrlon = coords.get("lrlon");
        double lrlat = coords.get("lrlat");
        if (ullon > queryLrlon || queryUllon > lrlon) {
            return false;
        }
        if (ullat < queryLrLat || queryUllat < lrlat) {
            return false;
        }
        return true;
    }

//        contains querybox inside
//        if (input.get("ullon") >= coords.get("ullon") && input.get("ullat") <= coords.get("ullat")) {
//            if (input.get("lrlon") <= coords.get("lrlon") && input.get("lrlat") >= coords.get("lrlat")) {
//                return true;
//            }
//        }
//        double lrlon = coords.get("lrlon");
//        double lrlat = co

//        if (coords.get("lrlon") > input.get("ullon") && coords.get("lrlon") < input.get("lrlon") &&
//        coords.get("lrlat") > input.get("lrlat") && coords.get("lrlat") < input.get("ullat")) {
//            return true;
//        }
//
//        if (coords.get("lrlon") > input.get("ullon") && coords.get("lrlon") < input.get("lrlon") &&
//                coords.get("ullat") > input.get("lrlat") && coords.get("ullat") < input.get("ullat")) {
//            return true;
//        }
//
//        if (coords.get("ullon") > input.get("ullon") && coords.get("ullon") < input.get("lrlon") &&
//                coords.get("lrlat") > input.get("lrlat") && coords.get("lrlat") < input.get("ullat")) {
//            return true;
//        }
//
//        if (coords.get("ullon") > input.get("ullon") && coords.get("ullon") < input.get("lrlon") &&
//                coords.get("ullat") > input.get("lrlat") && coords.get("ullat") < input.get("ullat")) {
//            return true;
//        }
//        if (coords.get("lrlon") > input.get("ullon") && coords.get("lrlon") < input.get("lrlon") &&
//                coords.get("lrlat") > input.get("lrlat") && coords.get("lrlat") < input.get("ullat")) {
//            return true;
//        }
//
//        if (coords.get("lrlon") > input.get("ullon") && coords.get("lrlon") < input.get("lrlon") &&
//                coords.get("ullat") > input.get("lrlat") && coords.get("ullat") < input.get("ullat")) {
//            return true;
//        }
//
//        if (coords.get("ullon") > input.get("ullon") && coords.get("ullon") < input.get("lrlon") &&
//                coords.get("lrlat") > input.get("lrlat") && coords.get("lrlat") < input.get("ullat")) {
//            return true;
//        }
//        System.out.println(coords.get("ullon") > input.get("ullon"));
//        System.out.println(coords.get("ullon") < input.get("lrlon"));
//        System.out.println(coords.get("ullat") > input.get("lrlat"));
//        System.out.println(coords.get("ullat") < input.get("ullat"));
//
//        if (coords.get("ullon") > input.get("ullon") && coords.get("ullon") < input.get("lrlon") &&
//                coords.get("ullat") > input.get("lrlat") && coords.get("ullat") < input.get("ullat")) {
//            return true;
//        }


//        //top left
//        if (coords.get("lrlat") <= input.get("ullat") && coords.get("lrlat") >= input.get("lrlat") &&
//                coords.get("lrlon") >= input.get("ullon") && coords.get("lrlon") <= input.get("lrlon")) {
//                return true;
//        }
//        //top right
//        if (coords.get("lrlat") <= input.get("ullat") && coords.get("lrlat") >= input.get("lrlat")) {
//            if (coords.get("ullon") >= input.get("ullon") && coords.get("ullon") <= input.get("lrlon")) {
//                return true;
//            }
//        }
//        //bottom left
//        if (coords.get("ullat") >= input.get("lrlat") && coords.get("ullat") <= input.get("ullat")) {
//            if (coords.get("lrlon") >= input.get("ullon") && coords.get("lrlon") <= input.get("lrlon")) {
//                return true;
//            }
//        }
//        //bottom right
//        if (coords.get("ullon") >= input.get("ullon") && coords.get("ullon") <= input.get("lrlon")) {
//            if (coords.get("ullat") >= input.get("lrlat") && coords.get("ullat") <= input.get("ullat")) {
//                return true;
//            }
//        }



//        // input top left
//        if (input.get("ullon") >= coords.get("ullon") && input.get("ullon") <= coords.get("lrlon")) {
//            if (input.get("ullat") <= coords.get("ullat") && input.get("ullat") >= coords.get("lrlat")) {
//                return true;
//            }
//        }
//
//        //input top right
//        if (input.get("lrlon") >= coords.get("ullon") && input.get("lrlon") <= coords.get("lrlon")) {
//            if (input.get("ullat") <= coords.get("ullat") && input.get("ullat") >= coords.get("lrlat")) {
//                return true;
//            }
//        }
//
//        //input bottom left
//        if (input.get("ullon") >= coords.get("ullon") && input.get("ullon") <= coords.get("lrlon")) {
//            if (input.get("lrlat") <= coords.get("ullat") && input.get("lrlat") >= coords.get("lrlat")) {
//                return true;
//            }
//        }
//
//        //input bottom right
//        if (input.get("lrlon") >= coords.get("ullon") && input.get("lrlon") <= coords.get("lrlat")) {
//            if (input.get("lrlat") <= coords.get("ullat") && input.get("lrlat") >= coords.get("lrlat")) {
//                return true;
//            }
//        }


//        //top middle
//        if (input.get("lrlon") > coords.get("ullon") && input.get("lrlon") < coords.get("ullon")) {
//            if (input.get("lrlat") < coords.get("ullat") && input.get("lrlat") > coords.get("lrlat")) {
//                return true;
//            }
//        }
//        //left middle
//        if (input.get("lrlon") > coords.get("ullon") && input.get("lrlon") < coords.get("lrlon")) {
//            if (input.get("lrlat") < coords.get("ullat") && input.get("lrlat") > coords.get("lrlat")) {
//                return true;
//            }
//        }
//        //bottom middle
//        if (input.get("ullon") > coords.get("ullon") && input.get("ullon") < coords.get("lrlon")) {
//            if (input.get("ullat") < coords.get("ullat") && input.get("ullat") > coords.get("lrlat")) {
//                return true;
//            }
//        }
//        //right middle
//        if (input.get("ullon") > coords.get("ullon") && input.get("ullon") < coords.get("lrlon")) {
//            if (input.get("ullat") < coords.get("ullat") && input.get("ullat") > coords.get("lrlon")) {
//                return true;
//            }
//        }



//        if (coords.get("ullon") < input.get("ullon") && coords.get("lrlon") > input.get("lrlon")) {
//            if (coords.get("ullat") > input.get("ullat") && coords.get("lrlat") < input.get("lrlat")) {
//                return true;
//            }
//        }
//        if (coords.get("ullon") > input.get("ullon") && coords.get("ullon") < input.get("lrlon")) {
//            if (coords.get("lrlat") < input.get("ullat") && coords.get("lrlat") > input.get("lrlat")) {
//                return true;
//            }
//        } else if (coords.get("ullat") < input.get("ullat") && coords.get("ullat") > input.get("lrlat") ) {
//            if (coords.get("lrlon") > input.get("ullon") && coords.get("lrlon") < input.get("lrlon")) {
//                return true;
//            }
//        }
//        return false;


    public void instantiate() {
        if (reachedLimit() == false) {
            double middlelon = coords.get("lrlon") - coords.get("ullon");
            double middlelat = coords.get("ullat") - coords.get("lrlat");
            middlelon = middlelon / 2;
            middlelat = middlelat / 2;
            middlelon = middlelon + coords.get("ullon");
            middlelat = middlelat + coords.get("lrlat");
            one = new QTreeNode(name + "1");
            one.coords.put("ullon", coords.get("ullon"));
            one.coords.put("ullat", coords.get("ullat"));
            one.coords.put("lrlon", middlelon);
            one.coords.put("lrlat", middlelat);
            one.instantiate();

            two = new QTreeNode(name + "2");
            two.coords.put("ullon", middlelon);
            two.coords.put("ullat", coords.get("ullat" ));
            two.coords.put("lrlon", coords.get("lrlon"));
            two.coords.put("lrlat", middlelat);
            two.instantiate();

            three = new QTreeNode(name + "3");
            three.coords.put("ullon", coords.get("ullon"));
            three.coords.put("ullat", middlelat);
            three.coords.put("lrlon", middlelon);
            three.coords.put("lrlat", coords.get("lrlat"));
            three.instantiate();

            four = new QTreeNode(name + "4");
            four.coords.put("ullon", middlelon);
            four.coords.put("ullat" , middlelat);
            four.coords.put("lrlon", coords.get("lrlon"));
            four.coords.put("lrlat", coords.get("lrlat"));
            four.instantiate();
        }
    }

    public void print(int indent) {
        if (one == null && two == null && three == null && four == null) {
            println(name, indent);
            return;
        }
        if (one != null) {
            one.print(indent + 1);
        }
        if (two != null) {
            two.print(indent + 1);
        }
        println (name, indent);
        if (three != null) {
            three.print(indent + 1);
        }
        if (four != null) {
            four.print(indent + 1);
        }
    }

    private static void println(Object obj, int indent) {
        for (int k=0; k<indent; k++) {
            System.out.print("    ");
        }
        System.out.println(obj);
    }

}

