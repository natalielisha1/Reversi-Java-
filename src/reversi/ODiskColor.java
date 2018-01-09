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
public enum ODiskColor {
    White {
        @Override
        public String toString() {
            return "White";
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
    };
    
    public Image getDisk() {
        switch (this) {
            case White:         return new Image(getClass().getResourceAsStream("res/WhiteDisk.png"));
            case TedMosby:      return new Image(getClass().getResourceAsStream("res/TedMosbyWhiteDisk.png"));
            case BarneyStinson: return new Image(getClass().getResourceAsStream("res/BarneyStinsonWhiteDisk.png"));
            case LilyAldrin:    return new Image(getClass().getResourceAsStream("res/LilyAldrinWhiteDisk.png"));
            
            default:            return new Image(getClass().getResourceAsStream("res/WhiteDisk.png"));
        }
    }
    
    public Image getCell() {
        switch (this) {
            case White:         return new Image(getClass().getResourceAsStream("res/WhiteDiskCell.png"));
            case TedMosby:      return new Image(getClass().getResourceAsStream("res/TedMosbyWhiteDiskCell.png"));
            case BarneyStinson: return new Image(getClass().getResourceAsStream("res/BarneyStinsonWhiteDiskCell.png"));
            case LilyAldrin:    return new Image(getClass().getResourceAsStream("res/LilyAldrinWhiteDiskCell.png"));
            
            default:            return new Image(getClass().getResourceAsStream("res/WhiteDiskCell.png"));
        }
    }
    
    public static ODiskColor getEnum(String s) {
        switch (s) {
            case "White":           return White;
            case "Ted Mosby":       return TedMosby;
            case "Barney Stinson":  return BarneyStinson;
            case "Lily Aldrin":     return LilyAldrin;
            
            default:                return White;
        }
    }
    
    public static ArrayList<String> getStrings() {
        ODiskColor[] colors = ODiskColor.values();
        ArrayList<String> toReturn = new ArrayList<>();
        for (ODiskColor color : colors) {
            toReturn.add(color.toString());
        }
        return toReturn;
    }
}
