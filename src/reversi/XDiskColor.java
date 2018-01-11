/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author OfekSegal
 */
public enum XDiskColor {
    Black {
        @Override
        public String toString() {
            return "Black";
        }
    },
    TedMosby {
        @Override
        public String toString() {
            return "Ted Mosby";
        }
    },
    BarneyStinson {
        @Override
        public String toString() {
            return "Barney Stinson";
        }
    },
    LilyAldrin {
        @Override
        public String toString() {
            return "Lily Aldrin";
        }
    },
    MarshallErikson {
        @Override
        public String toString() {
            return "Marshall Erikson";
        }
    },
    RobinScherbatsky {
        @Override
        public String toString() {
            return "Robin Scherbatsky";
        }
    },
    PurpleFlower {
        @Override
        public String toString() {
            return "Purple Flower";
        }
    },
    CPU {
        @Override
        public String toString() {
            return "CPU";
        }
    },
    JavaLogo {
        @Override
        public String toString() {
            return "Java Logo";
        }
    };
    
    public Image getDisk() {
        switch (this) {
            case Black:             return new Image(getClass().getResourceAsStream("res/BlackDisk.png"));
            case TedMosby:          return new Image(getClass().getResourceAsStream("res/TedMosbyBlackDisk.png"));
            case BarneyStinson:     return new Image(getClass().getResourceAsStream("res/BarneyStinsonBlackDisk.png"));
            case LilyAldrin:        return new Image(getClass().getResourceAsStream("res/LilyAldrinBlackDisk.png"));
            case MarshallErikson:   return new Image(getClass().getResourceAsStream("res/MarshallEriksonBlackDisk.png"));
            case RobinScherbatsky:  return new Image(getClass().getResourceAsStream("res/RobinScherbatskyBlackDisk.png"));
            case PurpleFlower:      return new Image(getClass().getResourceAsStream("res/PurpleFlowerBlackDisk.png"));
            case CPU:               return new Image(getClass().getResourceAsStream("res/CPUBlackDisk.png"));
            case JavaLogo:          return new Image(getClass().getResourceAsStream("res/JavaLogoBlackDisk.png"));
            
            default:                return new Image(getClass().getResourceAsStream("res/BlackDisk.png"));
        }
    }
    
    public Image getCell() {
        switch (this) {
            case Black:             return new Image(getClass().getResourceAsStream("res/BlackDiskCell.png"));
            case TedMosby:          return new Image(getClass().getResourceAsStream("res/TedMosbyBlackDiskCell.png"));
            case BarneyStinson:     return new Image(getClass().getResourceAsStream("res/BarneyStinsonBlackDiskCell.png"));
            case LilyAldrin:        return new Image(getClass().getResourceAsStream("res/LilyAldrinBlackDiskCell.png"));
            case MarshallErikson:   return new Image(getClass().getResourceAsStream("res/MarshallEriksonBlackDiskCell.png"));
            case RobinScherbatsky:  return new Image(getClass().getResourceAsStream("res/RobinScherbatskyBlackDiskCell.png"));
            case PurpleFlower:      return new Image(getClass().getResourceAsStream("res/PurpleFlowerBlackDiskCell.png"));
            case CPU:               return new Image(getClass().getResourceAsStream("res/CPUBlackDiskCell.png"));
            case JavaLogo:          return new Image(getClass().getResourceAsStream("res/JavaLogoBlackDiskCell.png"));
            
            default:                return new Image(getClass().getResourceAsStream("res/BlackDiskCell.png"));
        }
    }
    
    public static XDiskColor getEnum(String s) {
        switch (s) {
            case "Black":               return Black;
            case "Ted Mosby":           return TedMosby;
            case "Barney Stinson":      return BarneyStinson;
            case "Lily Aldrin":         return LilyAldrin;
            case "Marshall Erikson":    return MarshallErikson;
            case "Robin Scherbatsky":   return RobinScherbatsky;
            case "Purple Flower":       return PurpleFlower;
            case "CPU":                 return CPU;
            case "Java Logo":           return JavaLogo;
            
            default:                    return Black;
        }
    }
    
    public static ArrayList<String> getStrings() {
        XDiskColor[] colors = XDiskColor.values();
        ArrayList<String> toReturn = new ArrayList<>();
        for (XDiskColor color : colors) {
            toReturn.add(color.toString());
        }
        return toReturn;
    }
}
