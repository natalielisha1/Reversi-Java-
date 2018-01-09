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
    };
    
    public Image getDisk() {
        switch (this) {
            case Black:         return new Image(getClass().getResourceAsStream("res/BlackDisk.png"));
            case TedMosby:      return new Image(getClass().getResourceAsStream("res/TedMosbyBlackDisk.png"));
            case BarneyStinson: return new Image(getClass().getResourceAsStream("res/BarneyStinsonBlackDisk.png"));
            case LilyAldrin:    return new Image(getClass().getResourceAsStream("res/LilyAldrinBlackDisk.png"));
            
            default:            return new Image(getClass().getResourceAsStream("res/BlackDisk.png"));
        }
    }
    
    public Image getCell() {
        switch (this) {
            case Black:         return new Image(getClass().getResourceAsStream("res/BlackDiskCell.png"));
            case TedMosby:      return new Image(getClass().getResourceAsStream("res/TedMosbyBlackDiskCell.png"));
            case BarneyStinson: return new Image(getClass().getResourceAsStream("res/BarneyStinsonBlackDiskCell.png"));
            case LilyAldrin:    return new Image(getClass().getResourceAsStream("res/LilyAldrinBlackDiskCell.png"));
            
            default:            return new Image(getClass().getResourceAsStream("res/BlackDiskCell.png"));
        }
    }
    
    public static XDiskColor getEnum(String s) {
        switch (s) {
            case "Black":           return Black;
            case "Ted Mosby":       return TedMosby;
            case "Barney Stinson":  return BarneyStinson;
            case "Lily Aldrin":     return LilyAldrin;
            
            default:                return Black;
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
