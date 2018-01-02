/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

/**
 *
 * @author ofek_
 */
public enum Direction {
    Up,
    Down,
    Left,
    Right,
    UpLeft,
    UpRight,
    DownLeft,
    DownRight;
    
    public Direction reverseDir() {
        switch (this) {
            case Up:        return Down;
            case Down:      return Up;
            case Right:     return Left;
            case Left:      return Right;
            case DownLeft:  return UpRight;
            case DownRight: return UpLeft;
            case UpLeft:    return DownRight;
            case UpRight:   return DownLeft;
            default:        return Up;
	}
    }
}
