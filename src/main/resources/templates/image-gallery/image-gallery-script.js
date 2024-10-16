var list = document.querySelector('.slider .list');
var items = document.querySelectorAll('.slider .item');
var prev = document.getElementById('prev');
var next = document.getElementById('next');
var body = document.querySelector('body');
var active = 0;
var lengthItems = items.length-1;
next.onclick = function () {
    if (active+1 >lengthItems) {
        active = 0;
    }else{
        active=active+1;
    }

    console.log(items.length);
    reloadSlider();
    //this can track the active image such that you know which info of image is being displayed
    console.log('active '+active  +' Length '+lengthItems);

}
prev.onclick = function () {
    if (active -1 < 0) {
        active= lengthItems;
    }
    else{
        active = active - 1;
    }
    reloadSlider();
    //this can track the active image such that you know which info of image is being displayed
    console.log('active '+active +' Length '+lengthItems);
}
function reloadSlider() {
    var checkLeft = items[active].offsetLeft;
    list.style.left = -checkLeft + 'px';
   
}
