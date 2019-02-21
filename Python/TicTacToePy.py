import random as r

def main() :
    playState = True
    playLoop = True
    playTurn = int(r.random() * 2)
    newX = 0
    newY = 0

    print()
    while playLoop :
        board = [[" "," "," "],[" "," "," "],[" "," "," "]]
        printBoard(board)
        while playState :
            if playTurn == 0 :
                print("Your turn")
                newX = input("Row: ")
                newY = input("Column: ")
            else :
                cMove = minMax(board, "O")
                newX = cMove["x"]
                newY = cMove["y"]
            if checkFull(newX, newY, board) :
                print("This square is already full. Please choose another square.")
            else :
                if playTurn == 0 :
                    board[NewX][newY] = "X"
                    playTurn = 1
                else :
                    board[newX][newY] = "O"
                    playTurn = 0
                printBoard(board)
                print()
                if checkWin(board) == 1 :
                    print("You won!")
                    playState = False
                elif checkWin(board) == -1 :
                    print("Computer won")
                    playState = False
                elif checkWin(board) == 0 :
                    print("Draw")
                    playState = False
        answer = input("Play again? ")
        if (answer != "yes") :
            playLoop = False
        playState = True

def printBoard(board) :
    for i in range(3) :
        for j in range(3) :
            print(" " + board[i][j], end = " ")
            if j < 2 :
                print("|", end = "")
        print()
        if i < 2 :
            print("---------")

def checkFull(x, y, board) :
    if not board[x][y][0:] != " " :
        return True
    return False

def checkWin(board) :
    count = 0
    if((board[0][0] == "X" and board[0][1] == "X" and board[0][2] == "X") or (board[1][0] == "X" and board[1][1] == "X" and board[1][2] == "X") or (board[2][0] == "X" and board[2][1] == "X" and board[2][2] == "X") or (board[0][0]== "X" and board[1][0]== "X" and board[2][0]== "X") or (board[0][1]== "X" and board[1][1]== "X" and board[2][1]== "X") or (board[0][2]== "X" and board[1][2]== "X" and board[2][2]== "X") or (board[0][0]== "X" and board[1][1]== "X" and board[2][2]== "X") or (board[0][2]== "X" and board[1][1]== "X" and board[2][0]== "X")) :
        return 1
    elif ((board[0][0] == "O" and board[0][1] == "O" and board[0][2] == "O") or (board[1][0] == "O" and board[1][1] == "O" and board[1][2] == "O") or (board[2][0] == "O" and board[2][1] == "O" and board[2][2] == "O") or (board[0][0]== "O" and board[1][0]== "O" and board[2][0]== "O") or (board[0][1]== "O" and board[1][1]== "O" and board[2][1]== "O") or (board[0][2]== "O" and board[1][2]== "O" and board[2][2]== "O") or (board[0][0]== "O" and board[1][1]== "O" and board[2][2]== "O") or (board[0][2]== "O" and board[1][1]== "O" and board[2][0]== "O")) :
        return -1
    else :
        for i in range(3) :
            for j in range (3) :
                if board[i][j] == " " :
                    count+=1
        if count == 0 :
            return 0
        return 2

def minMax(newBoard, player) :
    hPlayer = "X"
    cPlayer = "O"
    if checkWin(newBoard) == -1 :
        m = {"x":-1,"y":-1,"score":10}
        return m
    elif checkWin(newBoard) == 1 :
        m = {"x":-1,"y":-1,"score":-10}
        return m
    elif checkWin(newBoard) == 0 :
        m = {"x":-1,"y":-1,"score":0}
        return m
    moves = ["","","","","","","","",""]
    for i in range(3) :
        for j in range(3) :
            if not checkFull(i,j,newBoard) :
                move = {"x":i,"y":j,"score":-1}
                newBoard[i][j] = player
                if player == cPlayer :
                    result = {"x":i,"y":j,"score":-1}
                    result = minMax(newBoard,hPlayer)
                    move["score"] = result["score"]
                else :
                    result = {"x":i,"y":j,"score":-1}
                    result = minMax(newBoard,cPlayer)
                    move["score"] = result["score"]
                newBoard[i][j] = " "
                moves.remove("")
                moves.append(move)
    bestMove = 0
    if player == cPlayer :
        bestScore = -2
        for i in range(len(moves)) :
            if moves[i]["score"] > bestScore :
                bestScore = moves[i]["score"]
                print(bestScore)
                bestMove = i
                print(bestMove)
    else :
        bestScore = 2
        for i in range(len(moves)) :
            if moves[i]["score"] < bestScore :
                bestScore = moves[i]["score"]
                print(bestScore)
                bestMove = i
                print(BestMove)
    return moves[bestMove]

main()
