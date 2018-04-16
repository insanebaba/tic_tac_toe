var boxes ,player;
var clicked=[];
var player_checked=[];
var com_checked=[];

var initTime=new Date();
window.onload = function() {
    //prepare Dialog box
    var dialog = document.querySelector('dialog');
    if (!dialog.showModal) {
      dialogPolyfill.registerDialog(dialog);
    }
    // allow dialog to close
    dialog.querySelector('.closeDialog')
          .addEventListener("click",function(){
            dialog.close();
          });

    //close and reset game
    dialog.querySelector(".resetGame").addEventListener("click",function(){
        dialog.close();
        resetTicTacToe();
    });

    //select all tictactoe elements
    boxes   = document.getElementsByClassName('tictactoe');

    // is this player's turn ?
    player  = true;

    //set click function on each tic tac toe
    for(var i=0; i<boxes.length; i++) {

        //reset all check boxes
        boxes[i].indeterminate = true;

        // what to do when user clicks one of the tic tac toe blocks
        boxes[i].onclick = function(e) {
            this.checked = player;
            this.disabled = true;

            //based on whose turn is it, add it to corresponding array
            player ? player_checked.push(this.id) : com_checked.push(this.id);

            player = !player;

            if(isMatchFinished()){

                if(whoWon()>0){
                    var score=Math.floor(Math.pow(10,7)/(new Date() - initTime))+Math.floor(200*15/player_checked.length);
                    dialog.querySelector('p#score').innerHTML="Good Job!! Your score:"+score;
                }
                else dialog.querySelector('p#score').innerHTML="Bad luck!!";
                dialog.showModal();
            }

            if( !isMatchFinished() && !player ){
                var compMoved=false;

                while(!compMoved){
                    var num=Math.floor(Math.random() * 9);
                    if(!player_checked.includes("c"+num)
                    && !com_checked.includes("c"+num)){
                        var compBox=boxes[num];
                        compBox.click();
                        compMoved=true;
                    }
                }
           }
        }
    }
}

function isMatchFinished(){
    return  whoWon()  ||
            (player_checked.length + com_checked.length) > 8;
}
function whoWon(){
 return     doesArraysContain("c0","c1","c2") ||
            doesArraysContain("c3","c4","c5") ||
            doesArraysContain("c6","c7","c8") ||
            doesArraysContain("c0","c3","c6") ||
            doesArraysContain("c1","c4","c7") ||
            doesArraysContain("c2","c5","c8") ||
            doesArraysContain("c0","c4","c8") ||
            doesArraysContain("c2","c4","c6") ;
}
function doesArraysContain(a,b,c){
    if (Array.isArray(player_checked) && player_checked.includes(a) && player_checked.includes(b) && player_checked.includes(c))
        return 1;
    else if (Array.isArray(com_checked) && com_checked.includes(a) && com_checked.includes(b) && com_checked.includes(c))
        return -1;
    return 0;
}
//reset the entire game
function resetTicTacToe(){
    for(i=0;i<boxes.length;i++){
        boxes[i].indeterminate=true;
        boxes[i].removeAttribute("disabled");
        boxes[i].removeAttribute("checked");
    }
    player_checked=[];
    com_checked=[];
    player=true;
    initTime=new Date();
}
