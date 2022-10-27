    var charNum = $("#characterId").val(),
    level   = $("#level").val();
    if(level > 0) {

        var pics_src = new Array("/img/"+charNum+".svg","/img/"+charNum+"-1.svg");
        var num = -1;

        move();
        function move(){
            if(num == 1){
                num = 0;
            }else{
                num ++;
            }
            document.getElementById("characterImg").src=pics_src[num];
            setTimeout("move()",1500);
        }

    }
