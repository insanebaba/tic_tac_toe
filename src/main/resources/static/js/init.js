var boxes ,player;
var clicked=[];
var items = {};

for(i=0;i<3;i++){
    for(j=0;j<3;j++){
        items[i+","+"j"]="";
    }
}
window.onload = function() {
    //prepare Dialog box
    var dialog = document.querySelector('dialog');
    if (!dialog.showModal) {
      dialogPolyfill.registerDialog(dialog);
    }
    dialog.querySelector('.closeDialog')
          .addEventListener("click",function(){
            dialog.close();
          });

    dialog.querySelector(".resetGame").addEventListener("click",function(){
        dialog.close();
        resetTicTacToe();
    });

    boxes   = document.getElementsByClassName('tictactoe');
    player  = true;

    for(var i=0; i<boxes.length; i++) {
    items[parseInt(i/3)+","+i%3]=boxes[i];
        boxes[i].indeterminate = true;
        boxes[i].onclick = function(e) {
            this.checked = player;
            this.disabled = true;
            player = !player;
            clicked.push(this);
            if(isMatchFinished()){
                dialog.showModal();
            }

            if(!player && clicked.length < 8 ){
            var compMoved=false;

            while(!compMoved){
                var num=Math.floor(Math.random() * 9);
                if(!clicked.includes(boxes[num])){
                    var compBox=boxes[num];
                    compBox.click();
                    compMoved=true;
                }
            }}
        }
    }
}

function isMatchFinished(){
    return clicked.length>8;
}

function resetTicTacToe(){
    for(i=0;i<boxes.length;i++){
        boxes[i].indeterminate=true;
        boxes[i].removeAttribute("disabled");
        boxes[i].removeAttribute("checked");
    }
    clicked=[];
    player=true;

}

function showMessage(message){
    var messageLabel   = document.getElementById('messageLabel');
    messageLabel.innerHTML =message;

}
