var index = 0;
window.onload = function(){
    slideShow();
}
function slideShow(){
    var i;
    var x = $('.slide1');
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";
    }
    index++;
    if (index > x.length) {
        index = 1;
    }
    x[index-1].style.display = "block";
    setTimeout(slideShow, 4000);
}