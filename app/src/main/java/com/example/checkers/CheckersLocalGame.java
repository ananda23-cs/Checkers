/**
 * @author Aashish Anand, Anand Gogoi, Caitlin Ching, Cian Murray
 * Local game class - creates a new game state, implement the move,
 * capture, and turn to king actions, and update it to the new game state
 *
 * CS301A
 * @version 04/11/2021
 */

package com.example.checkers;

import com.example.checkers.game.GameFramework.LocalGame;
import com.example.checkers.game.GameFramework.actionMessage.GameAction;
import com.example.checkers.game.GameFramework.players.GamePlayer;

public class CheckersLocalGame extends LocalGame {



    /**
     * Constructor for the CheckersLocalGame.
     */
    public CheckersLocalGame() {
        super();
        super.state = new CheckersGameState();

    }

    /**
     * Constructor for the CheckersLocalGame with loaded checkersGameState.
     * @param checkersGameState
     */
    public CheckersLocalGame(CheckersGameState checkersGameState) {
        super();
        super.state = new CheckersGameState(checkersGameState);
    }

    /**
     * method sendUpdatedStateTo
     * notifies the players that the state of the game has changed
     * should involve sending a GameInfo object to the player
     *
     *
     * @param p
     * 			the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new CheckersGameState((CheckersGameState) state));
    } //sendUpdatedStateTo

    /**
     * method canMove
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return
     * 		true iff the player is allowed to move
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == ((CheckersGameState) state).getPlayerTurn();
    } //canMove

    /**
     * method checkIfGameOver
     * checks if the game is over. If it's over, return a message
     * showing who won the game. If not, return null
     *
     * @return
     *          a String message showing the winner of the game
     *          or null
     */
    @Override
    protected String checkIfGameOver() {
        CheckersGameState state = (CheckersGameState) super.state;
        if (state.getP1NumPieces() == 0) {
            return "Player 2 won!";
        } else if (state.getP2NumPieces() == 0) {
            return "Player 1 won!";
        } else {
            return null;
        }
    } //checkIfGameOver

    /**
     * Makes a move on behalf of a player.
     *
     * @param action
     * 			The move that the player has sent to the game
     * @return
     * 			Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //this lets the user pick another piece.
        if(action instanceof CheckersCancelMoveAction){
            CheckersGameState state = (CheckersGameState) super.state;
            state.setPieceSelectedPieceAndPieceSelectedBoolean();
            state.setMessage("Choose another piece " + " P2 Pieces = " +state.getP2NumPieces() + " P1 Pieces = " + state.getP1NumPieces());
            return true;
        }

        //if they send a choose action
        else if(action instanceof CheckersMoveAction3){

            CheckersMoveAction3 ca = (CheckersMoveAction3) action;
            CheckersGameState state = (CheckersGameState) super.state;

            int x = ca.x;
            int y = ca.y;

            //chooses a piece
            if(!state.isPieceSelectedBoolean()){
                // checks if spot is empty
                if(state.isEmpty(x,y)){
                    state.setMessage("This tile is empty. Pick another square");
                    return false;
                }
                else {
                    // checks if piece belongs to player
                    if (state.hasEnemyPieces(x,y)) {
                        state.setMessage("This piece is not yours. Please try again.");
                    }
                    // if conditions are true, piece is chosen
                    else{
                        state.setMessage("This piece can be moved. Click on the spot where you want to move it." );
                        state.setPieceSelectedPieceAndPieceSelectedBoolean(x,y);
                        return true;
                    }
                }
            }

            //lets a player move a piece
            else{
                //distance as well as direction of where the piece is going
                int xDire=x-state.getPieceSelectedPiece().getXcoordinate();
                int yDire=y-state.getPieceSelectedPiece().getYcoordinate();

                //captures a piece
                if(state.hasEnemyPieces(x,y)){
                    if(state.CaptureEnemyPiece(x,y,state.getPieceSelectedPiece().getXcoordinate(),state.getPieceSelectedPiece().getYcoordinate())){
                        return true;
                    }
                }

                //moves a piece
                if(state.canMove(state.getPieceSelectedPiece(),xDire,yDire,state.getPlayerTurn())){
                    state.move(xDire,yDire);
                    state.setMessage("This is a valid move." );
                    return true;
                }


            }
        }
        return false;
    }

}

//          if(action instanceof CheckersMoveAction2){
//              Log.e("makeMove","before"+((CheckersMoveAction2)action).getPiece());
//              if(((CheckersGameState)super.state).movePiece(((CheckersMoveAction2) action).getPiece(),((CheckersMoveAction2) action).getXDire()
//              ,((CheckersMoveAction2) action).getYDire(),((CheckersGameState) super.state).getPlayerTurn())){
//                  ((CheckersGameState)super.state).setPieceSelectedPieceAndPieceSelectedBoolean();
//                  Log.e("makeMove","after"+((CheckersMoveAction2)action).getPiece());
//                  return true;
//              }
//              else {
//                  return false;
//              }
//
//
//          }
//          else{
//              return false;
//          }



//        Log.e("Make Move", "this happened");
//
//        if(action instanceof CheckersCancelMoveAction){
//            CheckersCancelMoveAction cancelMoveAction = (CheckersCancelMoveAction) action;
//            CheckersGameState state = (CheckersGameState) super.state;
//            int cancelRow = cancelMoveAction.getSelectedRow();
//            int cancelCol = cancelMoveAction.getSelectedCol();
//            state.setPieceSelectedPieceAndPieceSelectedBoolean(cancelRow,cancelCol);
//            state.setMessage("The piece at " + cancelRow + ", " + cancelCol +
//                                " has been unselected. Please select another piece.");
//            return true;
//        }
//        else if (action instanceof CheckersMoveAction2){
//            if(action.getPlayer() instanceof CheckersComputerPlayer1){
//
//            }
//            CheckersMoveAction2 moveAction = (CheckersMoveAction2) action;
//            CheckersGameState state = (CheckersGameState) super.state;
//            int playerId = state.getPlayerTurn();
//            int xDir = moveAction.getXDire();
//            int yDir = moveAction.getYDire();
//            CheckersPiece piece = moveAction.getPiece();
//            Log.e("new one", "Xcord"+piece.getXcoordinate());
//
//            Log.e("xl23",""+moveAction.getPiece());
//            if(state.canMove(piece,xDir,yDir,state.getPlayerTurn())) {
//                state.setPieceSelectedPieceAndPieceSelectedBoolean(piece.getXcoordinate(),
//                        piece.getYcoordinate());
//
//               ((CheckersGameState) super.state).movePiece(moveAction.getPiece(), xDir, yDir, playerId);
//
//                if (state.getPlayerTurn() == 0) {
//                    state.setMessage("That move was valid. Player two please choose a piece");
//                } else {
//                    state.setMessage("That move was valid. Player one please choose a piece");
//                }
//                if(true) {
//                    state.setPlayerTurn(1 - playerId);
//                }
//                return true;
//            }
//
//            else{
//                state.setMessage("Invalid Move. Try again." + state.getPlayerTurn() + moveAction.getPiece());
//                return false;
//            }
//
//        }
//        else if (action instanceof CheckersCaptureAction){
//            CheckersCaptureAction captureAction = (CheckersCaptureAction) action;
//            CheckersGameState state = (CheckersGameState) super.state;
//            int playerId = state.getPlayerTurn();
//            int xDir = captureAction.getXDire();
//            int yDir = captureAction.getYDire();
//            CheckersPiece piece = captureAction.getCheckersPiece();
//            if (canMove(playerId)){
//                if(playerId == 0){
//                    state.capturepiece(piece,playerId,state.p2Pieces,xDir,yDir);
//                    state.setP2NumPieces(state.getP2NumPieces()-1);
//                }
//                else{
//                    state.capturepiece(piece,playerId,state.p1Pieces,xDir,yDir);
//                    state.setP1NumPieces(state.getP1NumPieces()-1);
//                }
//                state.setPlayerTurn(1-playerId);
//                return true;
//            }
//            else{ return false; }
//        }
//        return false;

//if(state.hasEnemyPieces(x,y)){
//    if(state.CaptureEnemyPieceCP(x,y,state.getPieceSelectedPiece().getXcoordinate(),state.getPieceSelectedPiece().getYcoordinate())){
//        return true;
//   }
//}
//                if(state.getPlayerTurn() == 0) {
//                    Log.e("makeMoveXLR8 ","thisJusthappend" );
//                    if (state.hasEnemyPieces(x, y)) {
//
//                        if (state.capturepiece(state.getPieceSelectedPiece(), state.getPlayerTurn(),state.p2Pieces , xDire, yDire)) {
//                            state.setMessage("A piece was just captured!" );
//                            return true;
//                        }
//                    }
//                }
//                else {
//                    return false;
//              }
//if(state.getPlayerTurn() == 0){

