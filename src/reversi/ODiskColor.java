/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public enum ODiskColor {
    White {
        /**
         * The function returns the toString version
         * of White object
         * @return a string
         */
        @Override
        public String toString() {
            return "White";
        }
    },
    TedMosby {
        /**
         * The function returns the toString version
         * of TedMosby object
         * @return a string
         */
        @Override
        public String toString() {
            return "Ted Mosby";
        }
    },
    BarneyStinson {
        /**
         * The function returns the toString version
         * of BarneyStinson object
         * @return a string
         */
        @Override
        public String toString() {
            return "Barney Stinson";
        }
    },
    LilyAldrin {
        /**
         * The function returns the toString version
         * of LilyAldrin object
         * @return a string
         */
        @Override
        public String toString() {
            return "Lily Aldrin";
        }
    },
    MarshallErikson {
        /**
         * The function returns the toString version
         * of MarshallErikson object
         * @return a string
         */
        @Override
        public String toString() {
            return "Marshall Erikson";
        }
    },
    RobinScherbatsky {
        /**
         * The function returns the toString version
         * of RobinScherbatsky object
         * @return a string
         */
        @Override
        public String toString() {
            return "Robin Scherbatsky";
        }
    },
	YellowUmbrella {
        /**
         * The function returns the toString version
         * of YellowUmbrella object
         * @return a string
         */
		@Override
		public String toString() {
			return "Yellow Umbrella";
		}
	},
	BlueFrenchHorn {
        /**
         * The function returns the toString version
         * of BlueFrenchHorn object
         * @return a string
         */
		@Override
		public String toString() {
			return "Blue French Horn";
		}
	},
    PurpleFlower {
        /**
         * The function returns the toString version
         * of PurpleFlower object
         * @return a string
         */
        @Override
        public String toString() {
            return "Purple Flower";
        }
    },
    CPU {
        /**
         * The function returns the toString version
         * of CPU object
         * @return a string
         */
        @Override
        public String toString() {
            return "CPU";
        }
    },
    JavaLogo {
        /**
         * The function returns the toString version
         * of JavaLogo object
         * @return a string
         */
        @Override
        public String toString() {
            return "Java Logo";
        }
    };
    
    /**
     * The function returns the image of the O disk
     * @return an image
     */
    public Image getDisk() {
        switch (this) {
            case White:             return new Image(getClass().getResourceAsStream("res/WhiteDisk.png"));
            case TedMosby:          return new Image(getClass().getResourceAsStream("res/TedMosbyWhiteDisk.png"));
            case BarneyStinson:     return new Image(getClass().getResourceAsStream("res/BarneyStinsonWhiteDisk.png"));
            case LilyAldrin:        return new Image(getClass().getResourceAsStream("res/LilyAldrinWhiteDisk.png"));
            case MarshallErikson:   return new Image(getClass().getResourceAsStream("res/MarshallEriksonWhiteDisk.png"));
            case RobinScherbatsky:  return new Image(getClass().getResourceAsStream("res/RobinScherbatskyWhiteDisk.png"));
			case YellowUmbrella:    return new Image(getClass().getResourceAsStream("res/YellowUmbrellaWhiteDisk.png"));
			case BlueFrenchHorn:    return new Image(getClass().getResourceAsStream("res/BlueFrenchHornWhiteDisk.png"));
            case PurpleFlower:      return new Image(getClass().getResourceAsStream("res/PurpleFlowerWhiteDisk.png"));
            case CPU:               return new Image(getClass().getResourceAsStream("res/CPUWhiteDisk.png"));
            case JavaLogo:          return new Image(getClass().getResourceAsStream("res/JavaLogoWhiteDisk.png"));
            
            default:                return new Image(getClass().getResourceAsStream("res/WhiteDisk.png"));
        }
    }
    
    /**
     * The function returns the image of the
     * cell of the O disk
     * @return an image
     */
    public Image getCell() {
        switch (this) {
            case White:             return new Image(getClass().getResourceAsStream("res/WhiteDiskCell.png"));
            case TedMosby:          return new Image(getClass().getResourceAsStream("res/TedMosbyWhiteDiskCell.png"));
            case BarneyStinson:     return new Image(getClass().getResourceAsStream("res/BarneyStinsonWhiteDiskCell.png"));
            case LilyAldrin:        return new Image(getClass().getResourceAsStream("res/LilyAldrinWhiteDiskCell.png"));
            case MarshallErikson:   return new Image(getClass().getResourceAsStream("res/MarshallEriksonWhiteDiskCell.png"));
            case RobinScherbatsky:  return new Image(getClass().getResourceAsStream("res/RobinScherbatskyWhiteDiskCell.png"));
			case YellowUmbrella:    return new Image(getClass().getResourceAsStream("res/YellowUmbrellaWhiteDiskCell.png"));
			case BlueFrenchHorn:    return new Image(getClass().getResourceAsStream("res/BlueFrenchHornWhiteDiskCell.png"));
            case PurpleFlower:      return new Image(getClass().getResourceAsStream("res/PurpleFlowerWhiteDiskCell.png"));
            case CPU:               return new Image(getClass().getResourceAsStream("res/CPUWhiteDiskCell.png"));
            case JavaLogo:          return new Image(getClass().getResourceAsStream("res/JavaLogoWhiteDiskCell.png"));
            
            default:                return new Image(getClass().getResourceAsStream("res/WhiteDiskCell.png"));
        }
    }
    
    /**
     * The function returns the enum that's bound
     * to the given string
     * @param s - a string
     * @return ODiskColor enum
     */
    public static ODiskColor getEnum(String s) {
        switch (s) {
            case "White":               return White;
            case "Ted Mosby":           return TedMosby;
            case "Barney Stinson":      return BarneyStinson;
            case "Lily Aldrin":         return LilyAldrin;
            case "Marshall Erikson":    return MarshallErikson;
            case "Robin Scherbatsky":   return RobinScherbatsky;
			case "Yellow Umbrella":     return YellowUmbrella;
			case "Blue French Horn":    return BlueFrenchHorn;
            case "Purple Flower":       return PurpleFlower;
            case "CPU":                 return CPU;
            case "Java Logo":           return JavaLogo;
            
            default:                    return White;
        }
    }
    
    /**
     * The function returns all the strings
     * that represent a kind of disk
     * @return an array list of strings
     */
    public static ArrayList<String> getStrings() {
        ODiskColor[] colors = ODiskColor.values();
        ArrayList<String> toReturn = new ArrayList<>();
        for (ODiskColor color : colors) {
            toReturn.add(color.toString());
        }
        return toReturn;
    }
}
